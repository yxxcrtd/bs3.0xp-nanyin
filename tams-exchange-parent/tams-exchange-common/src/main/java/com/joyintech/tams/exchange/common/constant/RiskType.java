package com.joyintech.tams.exchange.common.constant;

public enum RiskType {
    /**
     * 风控触发返回
     * */
    STARTUP_FAILURE("启动失败","1"),
    STARTUP_SUCCESS("启动成功","0"),

    /**
     * 审批返回
     * */
    EXAMINE_APPROVAL("审核批准","1"),
    EXAMINE_REFUSE("审核拒绝","2");

    public final String value;
    public final String name;

    RiskType(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
