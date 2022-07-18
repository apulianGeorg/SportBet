package com.example.sportbet.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

public class JacksonTest {

    @Test
    public void givenJsonHasUnknownValues_whenDeserializing_thenException() throws JsonProcessingException {
        String jsonAsString =
                "{\"stringValue\":\"a\"," +
                        "\"intValue\":1," +
                        "\"booleanValue\":true," +
                        "\"stringValue2\":\"something\"}";
        /*   "}";*/

        /*        ObjectMapper mapper = new ObjectMapper();*/
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        MyDto readValue = mapper.readValue(jsonAsString, MyDto.class);

        assertEquals("a", readValue.getStringValue());
        assertEquals(1, readValue.getIntValue());
        assertTrue(readValue.isBooleanValue());
        assertNotNull(readValue);
    }

}
