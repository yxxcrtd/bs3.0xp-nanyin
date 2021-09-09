package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field;


import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.base.UInt32Field;

/**
 * 消息体长度
 * uInt32
 */
public class SendingTime extends UInt32Field {

    public SendingTime(long length) {
        super(length);
    }
}
