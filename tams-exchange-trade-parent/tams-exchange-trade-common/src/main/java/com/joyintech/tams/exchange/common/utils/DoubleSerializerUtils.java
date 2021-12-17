package com.joyintech.tams.exchange.common.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author swwan
 * @create 2020-03-25 14:39
 */
public class DoubleSerializerUtils extends JsonSerializer<BigDecimal> {
    public DoubleSerializerUtils() {
    }

    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNumber(0);
        } else {
            gen.writeNumber(value.stripTrailingZeros().doubleValue());
        }

    }
}