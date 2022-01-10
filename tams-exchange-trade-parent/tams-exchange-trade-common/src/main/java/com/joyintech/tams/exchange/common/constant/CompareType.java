package com.joyintech.tams.exchange.common.constant;

public enum CompareType {
    OVERWRITE("对比数据", 0),
    COMPARE_ALL("全量覆盖", 1),
    COMPARE_ENTRUST("委托覆盖", 2),
    COMPARE_TRADE("成交覆盖", 3);

    public final int value;
    public final String name;

    private CompareType(String name, int value) {
        this.name = name;
        this.value = value;
    }
}