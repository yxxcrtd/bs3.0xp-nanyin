package com.joyintech.tams.exchange.terminal.trade.ufx.builder;

public enum BrokeType {

    GUANGFA("GUANGFA", "广发"),
    OFFICIAL("OFFICIAL", "官方"),
    GUOJIN("GUOJIN", "国金");

    BrokeType(String value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public final String value;
    public final String msg;

    public String value() {
        return this.value;
    }

    public String msg() {
        return this.msg;
    }
}
