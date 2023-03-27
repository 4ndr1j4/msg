package com.msg.microservices.shared;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FalseToZeroBigDecimalDeserializerTest {

    private ObjectMapper mapper;
    private FalseToZeroBigDecimalDeserializer deserializer;

    @BeforeEach
    public void setup() {
        mapper = new ObjectMapper();
        deserializer = new FalseToZeroBigDecimalDeserializer();
    }

    @Test
    public void false_value_deserialises_to_Double_value() {
        String json = String.format("{\"value\":%s}", "false");
        BigDecimal deserializeFalse = deserializeFalseToBigDecimal(json);
        assertThat(deserializeFalse, instanceOf(BigDecimal.class));
        assertThat(deserializeFalse, is(equalTo(BigDecimal.ZERO)));
    }
    @Test
    public void whenTrueValue_deserialized_throws_JsonParseException() {
        String json = String.format("{\"value\":%s}", "true");
        Exception exception = assertThrows(JsonParseException.class, () -> deserializeFalseToBigDecimal(json));

        String expectedMessage = "Current token (VALUE_TRUE) not numeric, can not use numeric value accessors";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @SneakyThrows({JsonParseException.class, IOException.class})
    private BigDecimal deserializeFalseToBigDecimal(String json) {
        InputStream stream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        JsonParser parser = mapper.getFactory().createParser(stream);
        DeserializationContext ctxt = mapper.getDeserializationContext();
        parser.nextToken();
        parser.nextToken();
        parser.nextToken();
        return deserializer.deserialize(parser, ctxt);
    }

}