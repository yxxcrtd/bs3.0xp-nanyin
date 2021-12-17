package com.joyintech.tams.exchange.common.constant;

/**
 * create By MAXX on 2020/10/22
 */
public enum NetPriceFlag {
    IS_NET_PRICE_FLAG("Y", "净价交易"),
    NOT_NET_PRICE_FLAG("N", "全价交易");

    NetPriceFlag(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public final String value;
    public final String name;
}
