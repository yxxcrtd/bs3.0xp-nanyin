package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field;

import com.joyintech.tams.exchange.quoteterminal.sse.binary.enums.SSEBinaryMsgTypeEnum;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.base.StringField;

/**
 * 消息类型
 * char[4]
 */
public class MsgType extends StringField {

    public static int FIELD_LENGTH = 4;

    public MsgType(String type) {
        super(type, FIELD_LENGTH);
    }

    private SSEBinaryMsgTypeEnum msgTypeEnum;

    public MsgType(SSEBinaryMsgTypeEnum msgTypeEnum) {
        super(msgTypeEnum.getValue(), FIELD_LENGTH);
        this.msgTypeEnum = msgTypeEnum;
    }

    public SSEBinaryMsgTypeEnum getEnumValue(){
        return msgTypeEnum;
    }
}
