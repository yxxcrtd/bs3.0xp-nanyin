package com.joyintech.tams.exchange.common.constant;

/**
 * create By MAXX on 2020/3/24
 */
public enum CbptransType {
    YXMR("HB", "意向买"),
    YXMC("HS", "意向卖"),
    DJMR("OB", "定价买"),
    DJMC("OS", "定价卖");

    public final String value;
    public final String name;

    private CbptransType(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
