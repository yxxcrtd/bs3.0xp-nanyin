package com.joyintech.tams.exchange.common.constant.instruction;

/**
 * @Author: think
 * @Description:
 * @Date: Created in 16:08 2020/10/22
 */
public enum BusinessType {
    FIXED_INCOME_BUY("上交所固定收益平台债券买入", "01"),
    FIXED_INCOME_SELL("上交所固定收益平台债券卖出", "02");

    public final String value;
    public final String name;

    BusinessType(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
