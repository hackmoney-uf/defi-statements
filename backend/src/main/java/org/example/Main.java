package org.example;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.example.contract.Statements;
import org.example.util.Json;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static org.web3j.protocol.core.DefaultBlockParameterName.EARLIEST;
import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

public class Main {

    public static final String NODE_PROVIDER_URL = "url";
    public static final String CONTRACT_ADDRESS = "address";
    public static final String APP_PRIVATE_KEY = "key";
    public static final long CHAIN_ID = 80001;
    public static final String IPFS_USER = "user";
    public static final String IPFS_PASSWORD = "password";
    public static final String IPFS_UPLOAD_URL = "url";

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
                // TODO build statement
                final var file = File.createTempFile("statement", ".csv");
                try {
                    PrintWriter out = new PrintWriter(file);
                    out.println("!test content for hack money! " + log.requestor + " " + log.index);
                    out.close();

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