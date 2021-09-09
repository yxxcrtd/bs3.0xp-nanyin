package com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field;

import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.base.StringField;

/**
 * 行情类别
 * char[3]
 */
public class MDStreamId extends StringField {

    public static int FIELD_LENGTH = 3;

    public MDStreamId(String value) {
        super(value, 3);
    }
}
