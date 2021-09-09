package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field;

import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.base.StringField;

/**
 * 产品简称
 * char[8]
 */
public class Symbol extends StringField {

    public static int FIELD_LENGTH = 8;

    public Symbol(String value) {
        super(value, FIELD_LENGTH);
    }
}
