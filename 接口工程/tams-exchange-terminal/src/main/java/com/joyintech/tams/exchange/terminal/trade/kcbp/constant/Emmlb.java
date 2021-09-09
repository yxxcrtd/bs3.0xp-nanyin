package com.joyintech.tams.exchange.terminal.trade.kcbp.constant;

/**
 * @ClassName Emmlb
 * @Description TODO
 * @Author liujianping
 * @Date 2020/9/25
 * @Version
 **/
public enum Emmlb {

    PHDJDZMR("1m","盘后定价大宗买"),
    PHDJDZMC("1n","盘后定价大宗卖");

    public final String value;
    public final String name;

    Emmlb(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
