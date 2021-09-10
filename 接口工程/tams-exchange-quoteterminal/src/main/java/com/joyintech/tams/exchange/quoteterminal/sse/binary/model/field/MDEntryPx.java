package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field;


import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.base.PriceField;

/**
 * 价格
 * uint64 N13(5)
 */
public class MDEntryPx extends PriceField {

    public MDEntryPx(long value) {
        super(value);
    }

    @Override
    public double getDoubleValue() {
        return super.getDoubleValue() / 100000.0;
    }
}
