package com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.base.StringField;

public class Password extends StringField {

    public static int FIELD_LENGTH = 16;

    public Password(String password) {
        super(password, FIELD_LENGTH);
    }
}
