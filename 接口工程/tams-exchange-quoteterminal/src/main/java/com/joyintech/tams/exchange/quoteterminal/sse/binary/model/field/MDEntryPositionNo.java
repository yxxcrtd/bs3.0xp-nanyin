package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field;


import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.base.UInt16Field;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.base.UInt8Field;

/**
 * 买卖盘档位
 * uint8 N2
 */
public class MDEntryPositionNo extends UInt8Field {
    public MDEntryPositionNo(int value) {
        super(value);
    }
}
