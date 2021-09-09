package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field;


import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.base.UInt16Field;

/**
 * 价位揭示委托笔数
 * uint16,N5
 */
public class NoOrders extends UInt16Field {
    public NoOrders(int noOrders) {
        super(noOrders);
    }
}
