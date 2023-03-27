package com.msg.microservices.shared;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.math.BigDecimal;

public class FalseToZeroBigDecimalDeserializer extends JsonDeserializer<BigDecimal> {

    @Override
    public BigDecimal deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext)
            throws IOException {
        JsonToken current = jsonParser.getCurrentToken();

        if (current == JsonToken.VALUE_FALSE) {
            return BigDecimal.ZERO;
        }
        return jsonParser.getDecimalValue();
    }
}