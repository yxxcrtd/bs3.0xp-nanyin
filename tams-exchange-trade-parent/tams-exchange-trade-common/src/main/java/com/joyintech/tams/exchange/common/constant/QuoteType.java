package com.joyintech.tams.exchange.common.constant;

public enum QuoteType {
    BULK_QUOTE("大宗行情", 1),
    AFTER_BULK_QUOTE("盘后大宗行情", 2),
    NON_PUBLIC_PROTOCOL_BACK_QUOTE("上海债券协议回购非公开报价行情", 3),
    PUBLIC_PROTOCOL_BACK_QUOTE("上海债券协议回购公开报价行情", 4),
    FIXED_INCOME_QUOTE("上海固收平台报价行情", 5);

    public final int value;
    public final String name;

    QuoteType(String name, int value) {
        this.name = name;
        this.value = value;
    }
}
