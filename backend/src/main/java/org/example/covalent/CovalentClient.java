package org.example.covalent;

import org.example.model.Transaction;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static java.lang.String.format;

public class CovalentClient {

    private final static String TEMPLATE_URI_STRING = "https://api.covalenthq.com/v1/%s/address/%s/transactions_v2/?quote-currency=USD&format=JSON&block-signed-at-asc=false&no-logs=false&page-size=10000&key=%s";

    public static List<Transaction> getResponse(int chainId, String address, String apiKey) throws URISyntaxException, IOException, InterruptedException {
        final var request = HttpRequest.newBuilder()
            .uri(new URI(format(TEMPLATE_URI_STRING, chainId, address, apiKey)))
            .GET()
            .build();

        final var response = HttpClient.newBuilder()
            .build()
            .send(request, HttpResponse.BodyHandlers.ofString());

        return CovalentParser.parseTransactions(response.body());
    }

}
