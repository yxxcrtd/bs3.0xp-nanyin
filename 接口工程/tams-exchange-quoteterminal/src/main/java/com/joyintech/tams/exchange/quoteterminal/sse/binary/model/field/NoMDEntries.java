package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field;

import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.base.UInt16Field;

/**
 * 行情条目个数
 * uint16 N5
 */
public class NoMDEntries extends UInt16Field {

    public NoMDEntries(int value) {
        super(value);
    }
}
