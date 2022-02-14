package com.joyintech.tams.exchange.common.constant;

public enum FullPriceConst {

    FULL_PRICE("Y", "全价"),

    NET_PRICE("N", "非全价");

    FullPriceConst(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public final String value;
    public final String name;

}
