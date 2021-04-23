package com.joyintech.tams.exchange.common.constant;

public enum OffsetFlag {
    Open("开仓", "0"),
    Close("平仓", "1"),
    ForceClose("强平", "2"),
    CloseToday("平今", "3"),
    CloseYesterday("平昨", "4"),
    ForceOff("强减", "5"),
    LocalForceClose("本地强平", "6");

    public final String value;
    public final String name;

    OffsetFlag(String name, String value) {
        this.name = name;
        this.value = value;
    }

}
