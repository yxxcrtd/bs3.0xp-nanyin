package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field;


import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.base.StringField;

/**
 * 文本信息 可能包含中文字符
 * char[256]
 */
public class Text extends StringField {

    public static int FIELD_LENGTH = 256;

    public Text(String text) {
        super(text, FIELD_LENGTH);
    }
}
