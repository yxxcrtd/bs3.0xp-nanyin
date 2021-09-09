package com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.base.StringField;

/**
 * 接收方代码
 * char[20]
 */
public class TargetCompId extends StringField {

    public static int FIELD_LENGTH = 20;

    public TargetCompId(String targetCompId) {
        super(targetCompId,FIELD_LENGTH);
    }
}
