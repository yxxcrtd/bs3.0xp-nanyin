package com.joyintech.tams.exchange.common.constant;

public enum MonitorType {

    BUSI_ACCOUNT_COUNT("资金账号在线数量", "0"),
    ACCUMULATE_PERSISTENCE_COUNT("数据库队列积压数量", "1"),
    EXCEPTION_PERSISTENCE_COUNT("数据库队列异常数量", "2"),
    ACCUMULATE_TRADEMSG_COUNT("业务队列积压数量", "3"),
    EXCEPTION_TRADEMSG_COUNT("业务队列异常数量", "4");

    public final String value;
    public final String name;

    MonitorType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static String getName(String value) {
        for (MonitorType c : MonitorType.values()) {
            if (c.getValue().equals(value)) {
                return c.name;
            }
        }
        return "";
    }
}
