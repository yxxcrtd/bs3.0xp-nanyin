package com.joyintech.tams.exchange.common.constant;

/**
 * create By MAXX on 2020/6/11
 */
public enum OthCodeType {

    HSDM("回售代码", "01"),
    ZYDM("质押代码", "02"),
    KZZXYSGDM("可转债信用申购代码", "03"),
    KZZZGDM("可转债转股代码", "04"),
    PZDM("配债代码", "05"),
    PGDM("配股代码", "06"),
    XGSGDM("新股申购代码", "07");

    public final String value;
    public final String name;

    OthCodeType(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
