package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field;


import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.base.StringField;

/**
 * 接收方代码
 * char[32]
 */
public class TargetCompId extends StringField {

    public static int FIELD_LENGTH = 32;

    public TargetCompId(String targetCompId) {
        super(targetCompId,FIELD_LENGTH);
    }
}
