package com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.base.StringField;

/**
 * 发送方代码
 * char[20]
 */
public class SenderCompId extends StringField {

    public static int FIELD_LENGTH = 20;

    public SenderCompId(String senderCompId) {
        super(senderCompId, FIELD_LENGTH);
    }
}
