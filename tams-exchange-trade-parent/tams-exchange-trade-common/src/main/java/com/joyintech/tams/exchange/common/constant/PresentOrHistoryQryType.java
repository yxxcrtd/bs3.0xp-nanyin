package com.joyintech.tams.exchange.common.constant;

public enum PresentOrHistoryQryType {
    HISTORY("历史查询", 0),
    PRESENT("当前查询", 1);

    public final int value;
    public final String name;

    PresentOrHistoryQryType(String name, int value) {
        this.name = name;
        this.value = value;
    }
}
