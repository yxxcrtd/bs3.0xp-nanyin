package com.joyintech.tams.exchange.common.constant.joyin;

public enum AutoReceiveFlag {
    AUTO("自动接收", "Y"),
    MANUAL("手动接收", "N");

    public String name;
    public String value;

    AutoReceiveFlag(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
