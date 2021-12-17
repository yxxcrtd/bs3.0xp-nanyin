package com.joyintech.tams.exchange.common.constant;

public enum BusinessFlag {
    NORMAL("普通买卖", "1"),
    BULK("大宗", "2"),
    FIXED_INCOME("固收", "3"),
    PROTOCOL_BACK("协议回购", "4"),
    FAIR("公平", "5"),
    BATCH("批量", "6"),
    SPLIT("拆单", "7"),
    ALGO("算法单", "8");

    public final String value;
    public final String name;

    BusinessFlag(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
