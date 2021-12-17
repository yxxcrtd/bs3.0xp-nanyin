package com.joyintech.tams.exchange.common.constant;

public enum IDPrefix {
    NORMAL("普通委托", "e"),
    BULK("大宗", "i"),
    ORDER("订单", "o");

    public final String value;
    public final String name;

    IDPrefix(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
