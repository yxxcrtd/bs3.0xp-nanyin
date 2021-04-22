package com.joyintech.tams.exchange.common.constant;

public enum MarketType {
    SA("深A", "0"),
    HA("沪A", "1"),
    SB("深B", "2"),
    HB("沪B", "3"),
    HGT("沪港通", "5"),
    GZA("股转A", "6"),
    GZB("股转B", "7"),
    KFSJJ("开放式基金", "J"),
    SGT("深港通", "S");

    public final String value;
    public final String name;

    MarketType(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
