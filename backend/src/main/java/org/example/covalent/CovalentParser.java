package org.example.covalent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Transaction;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.example.model.Transaction.Builder.transaction;

public class CovalentParser {

    private final static ObjectMapper MAPPER = new ObjectMapper();

    public static List<Transaction> parseTransactions(String input) throws JsonProcessingException {
        final var jsonNode = MAPPER.readTree(input);
        return StreamSupport.stream(jsonNode.path("data").path("items").spliterator(), false)
            .map(node -> transaction()
                .hash(node.path("tx_hash").asText())
                .from(node.path("from_address").asText())
                .to(node.path("to_address").asText())
                .value(node.path("value").asText())
                .valueQuote(node.path("value_quote").decimalValue())
                .feesPaid(node.path("fees_paid").asText())
                .signedAt(ZonedDateTime.parse(node.path("block_signed_at").asText()))
                .build())
            .toList();
    }

}
