package com.joyintech.tams.exchange.common.enums;

/**
 * ETF 申赎方式
 *
 * @author swwan
 * @date 2020/12/2
 */
public enum DeliveryMethodEnum {
    PHYSICAL_DELIVERY("实物交割", "01"),
    CASH_DELIVERY("现金交割", "02");

    public final String name;
    public final String value;

    DeliveryMethodEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
