package com.joyintech.tams.exchange.common.constant;

public enum ActionIn {
    PUBLIC_QUOTE("公开行情查询", 0),
    NON_PUBLIC_QUOTE("非公开行情查询", 1);

    public final int value;
    public final String name;

    ActionIn(String name, int value) {
        this.name = name;
        this.value = value;
    }
}
