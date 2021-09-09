package com.joyintech.tams.exchange.terminal.constant;

/**
 * @Author: think
 * @Description:
 * @Date: Created in 18:04 2020/12/24
 */
public enum ServiceReissueStatus {
    SERVICE_REISSUING_START(1, "交易端比对数组组组装开始"),
    SERVICE_REISSUING_FINNISH(2, "交易端比对数组组组装完成");

    ServiceReissueStatus(int value, String msg) {
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
