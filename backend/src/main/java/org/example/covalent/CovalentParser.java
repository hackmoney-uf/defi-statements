package org.example.covalent;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.example.model.Transaction;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.example.model.Transaction.Builder.transaction;
import static org.example.util.Json.parseJsonNode;

public class CovalentParser {

    public static List<Transaction> parseTransactions(String input, String address, ZonedDateTime from, ZonedDateTime to) throws JsonProcessingException {
        final var jsonNode = parseJsonNode(input);
        return StreamSupport.stream(jsonNode.path("data").path("items").spliterator(), false)
            .filter(node -> {
                final var txDate = ZonedDateTime.parse(node.findValue("block_signed_at").asText());
                return from.isBefore(txDate) && txDate.isBefore(to);
            })
            .map(node -> transaction()
                .hash(node.findValue("tx_hash").asText())
                .from(node.findValue("from_address").asText())
                .to(node.findValue("to_address").asText())
                .value(node.findValue("value").asText())
                .valueQuote(node.findValue("value_quote").decimalValue())
                .feesPaid(node.findValue("fees_paid").asText())
                .signedAt(ZonedDateTime.parse(node.findValue("block_signed_at").asText()))
                .erc20Transactions(findErc20(node.withArray("log_events"), address))
                .build())
            .filter(transaction -> !transaction.value.equals("0") || !transaction.erc20Transactions.isEmpty())
            .toList();
    }

    private static List<Transaction.Erc20Transaction> findErc20(ArrayNode events, String address) {
        return StreamSupport.stream(events.spliterator(), false)
            .filter(elem ->  elem.path("sender_contract_ticker_symbol").textValue() != null)
            .filter(elem -> isValidParam(elem.path("decoded"), address))
            .map(elem -> {
                final var from = parseFieldFromLogParams(elem, "from");
                final var to = parseFieldFromLogParams(elem, "to");
                final var value = parseFieldFromLogParams(elem, "value");
                final var symbol = elem.path("sender_contract_ticker_symbol").asText();
                final var contract = elem.path("sender_address").asText();
                return new Transaction.Erc20Transaction(from, to, value, symbol, contract);
            })
            .toList();
    }

    private static boolean isValidParam(JsonNode decoded, String address) {
        if (decoded.path("name").isMissingNode() || !decoded.path("name").asText().equals("Transfer")) {
            return false;
        }
        return StreamSupport.stream(decoded.withArray("params").spliterator(), false)
            .anyMatch(params -> {
                final var value = params.findValue("value");
                return value != null && value.asText().equalsIgnoreCase(address);
            });
    }

    private static String parseFieldFromLogParams(JsonNode elem, String fieldName) {
        return StreamSupport.stream(elem.path("decoded").withArray("params").spliterator(), false)
            .filter(param -> param.findValue("name") != null && param.findValue("name").asText().equals(fieldName))
            .map(param -> param.findValue("value").asText())
            .findFirst()
            .orElseThrow();
    }
}
