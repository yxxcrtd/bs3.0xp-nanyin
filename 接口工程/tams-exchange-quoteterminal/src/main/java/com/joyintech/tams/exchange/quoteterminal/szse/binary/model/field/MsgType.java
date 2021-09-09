package com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field;

import com.joyintech.tams.exchange.quoteterminal.szse.binary.enums.SZSEBinaryMsgType;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.base.UInt32Field;

/**
 * 消息类型
 * uInt32
 */
public class MsgType extends UInt32Field {

    public MsgType(long value) {
        super(value);
    }

    private SZSEBinaryMsgType msgTypeEnum;

    public MsgType(SZSEBinaryMsgType msgTypeEnum) {
        super(msgTypeEnum.getCode());
        this.msgTypeEnum = msgTypeEnum;
    }

    public SZSEBinaryMsgType getEnumValue(){
        return msgTypeEnum;
    }
}
