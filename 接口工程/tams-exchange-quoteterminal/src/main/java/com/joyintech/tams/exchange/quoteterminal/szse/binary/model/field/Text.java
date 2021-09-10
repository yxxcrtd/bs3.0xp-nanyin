package com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.base.StringField;

/**
 * 文本信息 可能包含中文字符，表示最多 200 个字节
 * char[200]
 */
public class Text extends StringField {

    public static int FIELD_LENGTH = 200;

    public Text(String text) {
        super(text, FIELD_LENGTH);
    }
}
