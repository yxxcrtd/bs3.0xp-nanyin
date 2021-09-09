package com.joyintech.tams.exchange.terminal.trade.kcbp.constant;

public enum KcbpMarket {
    SZ("深证", "0"),
    SH("上海", "1");

    public final String value;
    public final String name;

    KcbpMarket(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
