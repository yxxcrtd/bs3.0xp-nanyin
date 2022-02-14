package com.joyintech.tams.exchange.common.constant;

public enum AlgorithmStatus {
    PROCESSING("进行中", "0"),
    FINISHED("已完成", "1"),
    CANCELED("已撤销", "2");

    public final String name;
    public final String value;

    AlgorithmStatus(String name, String value) {
        this.value = value;
        this.name = name;
    }
}
