package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field;


import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.base.PriceField;

/**
 * 成交金额
 * uint64 N16(2)
 */
public class TotalValueTraded extends PriceField {

    public TotalValueTraded(long value) {
        super(value);
    }

    @Override
    public double getDoubleValue() {
        return super.getDoubleValue() / 100.0;
    }
}
