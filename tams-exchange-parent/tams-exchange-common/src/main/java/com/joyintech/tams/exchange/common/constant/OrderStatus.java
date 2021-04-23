package com.joyintech.tams.exchange.common.constant;

public enum OrderStatus {

    CREATE("新建", 0),
    PENDING_REVIEW("待复核", 1),
    NOT_REVIEW("复核拒绝", 2),
    PROGRESSING("进行中", 10),
    ENTRUST_COMPLETE("委托完成", 20),
    STOP("已停止", 30);


    public final int value;
    public final String name;

    OrderStatus(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static boolean isFinished(int orderStatus) {
        return orderStatus == STOP.value
                || orderStatus == ENTRUST_COMPLETE.value;
    }
}
