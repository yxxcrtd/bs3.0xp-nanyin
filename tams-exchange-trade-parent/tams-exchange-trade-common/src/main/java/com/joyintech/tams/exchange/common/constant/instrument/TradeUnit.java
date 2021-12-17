package com.joyintech.tams.exchange.common.constant.instrument;

/**
 * @Author: think
 * @Description: 交易单位
 * @Date: Created in 14:00 2020/12/1
 */
public enum TradeUnit {
    ZHANG("张", "01"),
    FEN("份", "02"),
    GU("股", "03");

    public final String value;
    public final String name;

    TradeUnit(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
