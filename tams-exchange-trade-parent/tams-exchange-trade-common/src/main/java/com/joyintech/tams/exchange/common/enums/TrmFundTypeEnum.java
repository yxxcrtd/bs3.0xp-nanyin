package com.joyintech.tams.exchange.common.enums;

public enum TrmFundTypeEnum {
    ETF("01"),
    LOF("02"),
    GRADING_FUND("03"),
    CLOSED_END_FUND("04"),
    REAL_TIME_REDEMPTION_CURRENCY_FUND("05");

    public final String code;

    TrmFundTypeEnum(String code) {
        this.code = code;
    }
}
