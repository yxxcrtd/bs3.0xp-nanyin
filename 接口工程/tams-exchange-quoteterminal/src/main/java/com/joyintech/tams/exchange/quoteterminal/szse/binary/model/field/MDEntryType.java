package com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.enums.SZSEBinaryPriceType;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.base.StringField;

/**
 * 行情条目类别
 * char[2]
 */
public class MDEntryType extends StringField {

    public static int FIELD_LENGTH = 2;

    private SZSEBinaryPriceType priceTypeEnum;

    public MDEntryType(SZSEBinaryPriceType priceTypeEnum) {
        super(priceTypeEnum.getPriceType(), FIELD_LENGTH);
        this.priceTypeEnum = priceTypeEnum;
    }

    public MDEntryType(String priceType) {
        super(priceType, FIELD_LENGTH);
        this.priceTypeEnum = SZSEBinaryPriceType.lookup(super.value).orElse(SZSEBinaryPriceType.UNKNOWN);
    }

    public SZSEBinaryPriceType getPriceTypeEnum() {
        return priceTypeEnum;
    }
}
