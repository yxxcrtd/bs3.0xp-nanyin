package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field;

import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.base.StringField;

/**
 * 产品代码
 * char[8]
 */
public class SecurityId extends StringField {

    public static int FIELD_LENGTH = 8;

    public SecurityId(String securityId) {
        super(securityId, FIELD_LENGTH);
    }
}
