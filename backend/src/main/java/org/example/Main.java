package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.example.contract.Statements;
import org.example.covalent.CovalentClient;
import org.example.model.Transaction;
import org.example.util.Json;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;

import static java.time.Instant.ofEpochMilli;
import static java.time.ZoneOffset.UTC;
import static org.web3j.protocol.core.DefaultBlockParameterName.EARLIEST;
import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

public class Main {

    public static final String COVALENT_API_KEY = "key";

    public static final String NODE_PROVIDER_URL = "url";
    public static final String CONTRACT_ADDRESS = "address";
    public static final String APP_PRIVATE_KEY = "key";
    public static final int CHAIN_ID = 80001;
    public static final String IPFS_USER = "user";
    public static final String IPFS_PASSWORD = "password";
    public static final String IPFS_UPLOAD_URL = "url";

    public static final String[] STATEMENT_HEADERS = {
        "DATE",
        "TX_HASH",
        "FROM",
        "TO",
        "VALUE",
        "VALUE_USD",
        "FEES_PAID",
        "ERC20_SYMBOL",
        "ERC20_CONTRACT"
    };

    public static void main(String[] args) {
        final var web3j = Web3j.build(new HttpService(NODE_PROVIDER_URL));

        // Get the smart contract
        final var statementsContract = Statements.load(CONTRACT_ADDRESS, web3j, new FastRawTransactionManager(web3j, Credentials.create(APP_PRIVATE_KEY), CHAIN_ID), new DefaultGasProvider());

        final var filter = new EthFilter(EARLIEST, LATEST, CONTRACT_ADDRESS);
        statementsContract.statementRequestEventFlowable(filter).subscribe(log -> {
                final var request = statementsContract.requests(log.requestor, log.index).send();
                if (request.component3()) {
                    System.out.printf("Already handled request. Requestor=[%s], index=[%s]%n", log.requestor, log.index);
                    return;
                }
                try {
                    final var from = ZonedDateTime.ofInstant(ofEpochMilli(request.component1().longValue()), UTC);
                    final var to = ZonedDateTime.ofInstant(ofEpochMilli(request.component2().longValue()), UTC);
                    final var transactions = CovalentClient.getResponse(CHAIN_ID, log.requestor, COVALENT_API_KEY, from, to);
                    final var file = makeCsv(transactions);

                    final var hash = uploadFile(file);
                    System.out.println("file uploaded to ipfs with: " + hash);

                    statementsContract.markProcessed(log.requestor, log.index, hash.getBytes()).send();
                    System.out.printf("Success! Requestor=[%s], index=[%s], cid=[%s]%n", log.requestor, log.index, new String(hash.getBytes()));
                } catch (Exception e) {
                    System.out.println("Something went wrong: " + e);
                }
            },
            Throwable::printStackTrace);
    }

    private static File makeCsv(List<Transaction> transactions) throws IOException {
        final var file = File.createTempFile("statement", ".csv");
        final var csvFormat = CSVFormat.Builder.create()
            .setHeader(STATEMENT_HEADERS)
            .build();
        try (
            final var out = new FileWriter(file);
            final var printer = new CSVPrinter(out, csvFormat)) {
            for (Transaction tx : transactions) {
                printer.printRecord(tx.signedAt, tx.hash, tx.from, tx.to, tx.value, tx.valueQuote, tx.feesPaid);
                for (int i = 0; i < tx.erc20Transactions.size(); i++) {
                    final var erc20 = tx.erc20Transactions.get(i);
                    printer.printRecord(tx.signedAt, tx.hash, erc20.from(), erc20.to(), erc20.value(), 0, "0", erc20.symbol(), erc20.contract());
                }
            }
        }
        return file;
    }

    private static String uploadFile(File file) throws IOException {
        final var credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
            AuthScope.ANY,
            new UsernamePasswordCredentials(IPFS_USER, IPFS_PASSWORD)
        );

        try (var client = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build()) {
            final var httpPost = new HttpPost(IPFS_UPLOAD_URL);

            final var builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("file", file, ContentType.DEFAULT_BINARY, "statement.ext");
            final var multipart = builder.build();
            httpPost.setEntity(multipart);
            final var response = client.execute(httpPost);
            final var result = Json.parseJsonNode(EntityUtils.toString(response.getEntity()));
            return result.path("Hash").asText();
        }
    }
}