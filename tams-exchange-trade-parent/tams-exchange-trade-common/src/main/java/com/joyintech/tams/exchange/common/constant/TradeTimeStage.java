package com.joyintech.tams.exchange.common.constant;

/**
 * create By MAXX on 2020/12/15
 */
public enum TradeTimeStage {
    THE_OPENING_SET("开盘集合", 1),
    THE_CONTINUOUS_AUCTION("连续竞价", 2),
    THE_CLOSED_SET("收盘集合", 3);

    public final int value;
    public final String name;

    TradeTimeStage(String name, int value) {
        this.name = name;
        this.value = value;
    }
}
