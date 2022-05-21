package org.example;

import org.example.contract.Statements;
import org.example.covalent.CovalentClient;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;
import static org.web3j.protocol.core.DefaultBlockParameterName.EARLIEST;
import static org.web3j.protocol.core.DefaultBlockParameterName.LATEST;

public class Main {

    public static final String NODE_PROVIDER_URL = "url";
    public static final String CONTRACT_ADDRESS = "address";
    public static final String APP_PRIVATE_KEY = "key";
    public static final long CHAIN_ID = 80001;

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
                // TODO build statement and upload
                final byte[] cid = {};

                statementsContract.markProcessed(log.requestor, log.index, cid).send();
                System.out.printf("Success! Requestor=[%s], index=[%s], cid=[%s]%n", log.requestor, log.index, new String(cid));
            },
            Throwable::printStackTrace);
    }
}