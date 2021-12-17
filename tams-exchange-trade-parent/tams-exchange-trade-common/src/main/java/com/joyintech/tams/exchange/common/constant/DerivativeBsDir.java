package com.joyintech.tams.exchange.common.constant;

public enum DerivativeBsDir {
    BUY("买入", "T01"),
    SELL("卖出", "T02");

    public final String value;
    public final String name;

    DerivativeBsDir(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
