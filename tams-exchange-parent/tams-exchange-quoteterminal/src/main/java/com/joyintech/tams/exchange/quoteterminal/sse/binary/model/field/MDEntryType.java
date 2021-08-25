package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field;


import com.joyintech.tams.exchange.quoteterminal.sse.binary.enums.SSEBinaryMDEntryTypeEnum;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.base.StringField;

/**
 * 行情条目类别
 * char[2]
 */
public class MDEntryType extends StringField {

    public static int FIELD_LENGTH = 2;

    private final SSEBinaryMDEntryTypeEnum mdEntryTypeEnum;

    public MDEntryType(SSEBinaryMDEntryTypeEnum mdEntryTypeEnum) {
        super(mdEntryTypeEnum.getMDEntryType(), FIELD_LENGTH);
        this.mdEntryTypeEnum = mdEntryTypeEnum;
    }

    public MDEntryType(String mdEntryType) {
        super(mdEntryType, FIELD_LENGTH);
        this.mdEntryTypeEnum = SSEBinaryMDEntryTypeEnum.lookup(super.value).orElse(SSEBinaryMDEntryTypeEnum.UNKNOWN);
    }

    public SSEBinaryMDEntryTypeEnum getMdEntryTypeEnum() {
        return mdEntryTypeEnum;
    }
}
