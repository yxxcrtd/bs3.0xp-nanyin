package com.joyintech.tams.exchange.common.constant;

public enum EntrustBs {
    BUY("买入", "1"),
    SELL("卖出", "2");
    public final String value;
    public final String name;

    EntrustBs(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
