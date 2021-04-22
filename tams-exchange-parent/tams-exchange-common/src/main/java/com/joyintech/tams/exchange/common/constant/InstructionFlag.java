package com.joyintech.tams.exchange.common.constant;

public enum InstructionFlag {

    SUCCESS("指令接收成功", "0"),
    ACCEPT("指令接收", "1"),
    REFUSE("指令放弃", "2");

    public final String value;
    public final String name;

    InstructionFlag(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
