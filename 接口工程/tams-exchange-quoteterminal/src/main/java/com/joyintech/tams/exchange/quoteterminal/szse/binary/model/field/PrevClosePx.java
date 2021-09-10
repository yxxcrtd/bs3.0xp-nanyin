package com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.base.PriceField;

/**
 * 昨收价
 * int64 N13(4)
 */
public class PrevClosePx extends PriceField {

    public PrevClosePx(long value) {
        super(value);
    }

    @Override
    public double getDoubleValue() {
        return super.getDoubleValue() / 10000.0;
    }
}
