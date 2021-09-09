package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field;

import com.joyintech.tams.exchange.quoteterminal.sse.binary.enums.SSEBinaryMDStreamIDEnum;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.base.StringField;

/**
 * 行情类别
 * char[5]
 */
public class MDStreamId extends StringField {

    public static int FIELD_LENGTH = 5;

    public MDStreamId(String value) {
        super(value, FIELD_LENGTH);
    }

    private SSEBinaryMDStreamIDEnum mdStreamIDEnum;

    public MDStreamId(SSEBinaryMDStreamIDEnum mdStreamIDEnum) {
        super(mdStreamIDEnum.getValue(), FIELD_LENGTH);
        this.mdStreamIDEnum = mdStreamIDEnum;
    }

    public SSEBinaryMDStreamIDEnum getEnumValue(){
        return mdStreamIDEnum;
    }
}
