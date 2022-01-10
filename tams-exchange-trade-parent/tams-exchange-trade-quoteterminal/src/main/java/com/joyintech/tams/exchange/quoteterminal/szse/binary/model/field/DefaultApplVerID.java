package com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.base.StringField;

/**
 * 二进制协议版本
 * char[32]
 */
public class DefaultApplVerID extends StringField {

    public static int FIELD_LENGTH = 32;

    public DefaultApplVerID(String version) {
        super(version, FIELD_LENGTH);
    }
}
