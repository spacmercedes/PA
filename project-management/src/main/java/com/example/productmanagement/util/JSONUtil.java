package com.example.productmanagement.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//creaza un JSON din obiecte
public class JSONUtil {
    /**
     * Converts an object to a JSON String
     *
     * @param input {@link Object} any kind of Object
     * @return {@link String} representing JSON of given input
     */
    public static String objectToJsonString(Object input) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.findAndRegisterModules();
            String json = mapper.writeValueAsString(input); //
            System.out.println("Resulting JSON string: " + json);
            return json;
        } catch (JsonProcessingException e) {
            System.err.println("Error processing input " + e.getMessage());
        }
        return null;
    }
}
