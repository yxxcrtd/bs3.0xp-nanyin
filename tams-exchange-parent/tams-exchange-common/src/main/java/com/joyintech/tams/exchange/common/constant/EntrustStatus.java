package com.joyintech.tams.exchange.common.constant;

public enum EntrustStatus {

    CREATED("新建", -10),
    PENDING_APPROVAL("待审批", -9),
    PENDING_REVIEW("待复核", -8),
    PENDING_REVOKE("待撤审", -6),
    UNREPORTED("未报", 0),
    TOBE_REPORTED("待报", 1),
    REPORTED("已报", 2),
    REPORTED_TOBE_CANCEL("已报待撤", 3),
    PART_TRADED_TOBE_CANCELED("部成待撤", 4),
    PART_SUCCESS("部成", 5),
    PART_TRADED_CANCELED("部撤", 6),
    CANCELED("已撤", 7),
    SUCCEEDED("已成", 8),
    INVALID("柜台废单", 9),
    SYSTEM_INVALID("内部废单", 10),
    ABNORMAL_CANCELED("异常已撤", 11),
    NOT_APPROVAL("审批拒绝", 12),
    NOT_REVIEW("复核拒绝", 13),
    NOT_REVOKE("撤销拒绝", 14),
    NOT_RISK("风控拒绝", 15);

    public final int value;
    public final String name;

    EntrustStatus(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // 是否处于审批中状态
    public static boolean isPendingStatus(int entrustStatus) {
        return entrustStatus < EntrustStatus.PENDING_REVOKE.value && entrustStatus > EntrustStatus.CREATED.value;
    }

    // 是否为在途委托
    public static boolean isOnOrder(int entrustStatus) {
        return EntrustStatus.PENDING_REVOKE.value < entrustStatus
                && EntrustStatus.PART_TRADED_CANCELED.value > entrustStatus;
    }

    public static boolean isFinished(int status) {
        return status > PART_SUCCESS.value;
    }

    // 判断是否为撤单未完成的委托
    public static boolean isUnCompleteCancelEntrust(int entrustStatus) {
        return entrustStatus > TOBE_REPORTED.value && entrustStatus < PART_TRADED_CANCELED.value;
    }

    // 判断是否为异常委托
    public static boolean isExceptionEntrust(int status) {
        return status == TOBE_REPORTED.value
                || status == REPORTED_TOBE_CANCEL.value
                || status == PART_TRADED_TOBE_CANCELED.value;
    }

}
