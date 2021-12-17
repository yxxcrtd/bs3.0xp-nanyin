package com.joyintech.tams.exchange.common.constant;

public enum ApproveType {
    /**
     * 审批返回
     */
    EXAMINE_APPROVAL("审核批准","1"),
    EXAMINE_REFUSE("审核拒绝","2");

    public final String value;
    public final String name;

    ApproveType(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
