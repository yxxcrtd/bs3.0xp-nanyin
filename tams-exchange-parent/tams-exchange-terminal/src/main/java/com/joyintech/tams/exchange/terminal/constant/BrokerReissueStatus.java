package com.joyintech.tams.exchange.terminal.constant;

/**
 * @Author: think
 * @Description:
 * @Date: Created in 18:32 2020/12/24
 */
public enum BrokerReissueStatus {
    BROKER_REISSUING_START(3, "柜台比对数组组组装开始"),
    BROKER_REISSUING_FINNISH(4, "柜台比对数组组组装完成");

    BrokerReissueStatus(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public final int value;
    public final String msg;

    public int value() {
        return this.value;
    }

    public String msg() {
        return this.msg;
    }
}
