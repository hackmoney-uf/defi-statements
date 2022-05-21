package org.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {

    private final static ObjectMapper MAPPER = new ObjectMapper();

    public static JsonNode parseJsonNode(String input) throws JsonProcessingException {
        return MAPPER.readTree(input);
    }

}
