package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field;


import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.base.StringField;

/**
 * 协议版本
 * char[8]
 */
public class ApplVerID extends StringField {

    public static int FIELD_LENGTH = 8;

    public ApplVerID(String version) {
        super(version, FIELD_LENGTH);
    }
}
