package com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.base.UInt16Field;

/**
 * 频道结束标志
 * Boolean
 */
public class EndOfChannel extends UInt16Field {

    public EndOfChannel(int endOfChannel) {
        super(endOfChannel);
    }
}
