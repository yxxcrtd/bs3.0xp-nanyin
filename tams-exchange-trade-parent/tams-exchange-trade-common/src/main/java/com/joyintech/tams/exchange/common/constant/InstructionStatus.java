package com.joyintech.tams.exchange.common.constant;

public enum InstructionStatus {
    CREATE("新建", "01"),
    WAIT_RECEIVE("待接收", "02"),
    ALREADY_RECEIVE("已接收", "03"),
    COMPLETE("已完成", "04"),
    RETURN("已退回", "05"),
    CLOSE("已关闭", "06"),
    RECALLED("已撤回", "07"),
    TOBE_CANCELED("待撤回", "99");

    public final String value;
    public final String name;

    InstructionStatus(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
