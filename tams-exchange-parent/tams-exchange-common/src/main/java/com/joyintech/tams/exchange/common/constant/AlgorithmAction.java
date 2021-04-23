package com.joyintech.tams.exchange.common.constant;

public enum AlgorithmAction {
    SUPPLEMENT("补单", 0),
    CANCEL("撤销", 1),
    STAY("挂单", 2);

    public final String name;
    public final int value;

    AlgorithmAction(String name, int value) {
        this.value = value;
        this.name = name;
    }
}
