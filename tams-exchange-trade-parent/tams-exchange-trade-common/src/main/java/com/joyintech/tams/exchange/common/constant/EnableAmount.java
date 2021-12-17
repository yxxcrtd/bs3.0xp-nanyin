package com.joyintech.tams.exchange.common.constant;

/**
 * create By MAXX on 2020/3/26
 */
public enum EnableAmount {
    ZERO("最大交易数量获取为空", 0),
    ERROR("最大交易数量获取失败", -1);

    public final int value;
    public final String name;

    EnableAmount(String name, int value) {
        this.name = name;
        this.value = value;
    }
}
