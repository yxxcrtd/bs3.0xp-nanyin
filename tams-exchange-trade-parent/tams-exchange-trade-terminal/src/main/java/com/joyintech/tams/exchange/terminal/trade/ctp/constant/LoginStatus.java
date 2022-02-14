package com.joyintech.tams.exchange.terminal.trade.ctp.constant;

public enum LoginStatus {
    NOT_CONNECT("未连接", 0),
    CONNECTED("连接成功", 1),
    //NOT_LOGIN("未登录",1),
    LOGINED("登录成功", 2),
    REISSUE("重传成功", 3),
    NOT_TRADING_TIME("非交易时间段", 4),
    NORMAL("正常", 9);
    public final int value;
    public final String name;

    LoginStatus(String name, int value) {
        this.name = name;
        this.value = value;
    }
}
