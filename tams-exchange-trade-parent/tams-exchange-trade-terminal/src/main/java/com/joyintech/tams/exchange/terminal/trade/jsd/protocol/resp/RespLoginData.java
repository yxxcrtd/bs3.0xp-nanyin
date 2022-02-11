package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp;

//登录返回消息体
public class RespLoginData {
    public char flag;
    public String source;
    public String reqId;
    public char market;
    public String marketName;
    public String gddm;
    public char moneyType;
    public String moneyName;

    public char getMarket() {
        return market;
    }
}
