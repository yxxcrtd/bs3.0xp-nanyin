package com.joyintech.tams.exchange.common.constant;

/**
 * create By MAXX on 2020/9/15
 */
public enum JSDBsFlagName {
    CANCEL("撤", "撤");
    public final String value;
    public final String name;

    JSDBsFlagName(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
