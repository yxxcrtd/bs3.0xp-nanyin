package com.joyintech.tams.exchange.common.constant;

/**
 * create By MAXX on 2020/3/18
 */
public enum ExchangeType {
    ZJ("资金", "0"),
    SH("上海", "1"),
    SZ("深圳", "2"),

    TZA("特转A", "9"),
    TZB("特转B", "A"),
    HB("沪Ｂ", "D"),
    HHK("沪HK", "G"),
    SB("深Ｂ", "H");


    public final String value;
    public final String name;

    ExchangeType(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
