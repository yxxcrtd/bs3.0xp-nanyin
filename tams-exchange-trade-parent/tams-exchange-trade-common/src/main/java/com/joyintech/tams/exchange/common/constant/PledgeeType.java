package com.joyintech.tams.exchange.common.constant;

public enum PledgeeType {

    UN_THIRD_PARTY("0", "非第三方"),

    THIRD_PARTY("1", "第三方");

    PledgeeType(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public final String value;
    public final String name;

}
