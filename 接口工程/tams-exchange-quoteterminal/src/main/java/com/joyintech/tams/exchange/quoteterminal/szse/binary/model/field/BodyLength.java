package com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.base.UInt32Field;

/**
 * 消息体长度
 * uInt32
 */
public class BodyLength extends UInt32Field {

    public BodyLength(long length) {
        super(length);
    }
}
