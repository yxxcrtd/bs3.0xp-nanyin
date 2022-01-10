package com.joyintech.tams.exchange.common.constant;

public enum RiskStatus {
    // 0:通过、1:预警、2:审批、3:禁止
    PASS("通过", "0"),
    ALERT("预警", "1"),
    NEED_APPROVE("审批", "2"),
    FORBIDDEN("禁止", "3"),
    AVOID("禁止", "9"),
    ABNORMAL("异常", "4");
    public final String value;
    public final String name;

    RiskStatus(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
