package com.joyintech.service.trade.entrustmsgbymq.dto;

import java.io.Serializable;

public class OrderFinishAmtMessage implements Cloneable, Serializable {
    private String entrustId;       // 委托代码

    private Double frozenBalance;      // 剩余未完成委托金额
    private Double tradedBalance;       // 已成交金额

    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    public Double getFrozenBalance() {
        return frozenBalance;
    }

    public void setFrozenBalance(Double frozenBalance) {
        this.frozenBalance = frozenBalance;
    }

    public Double getTradedBalance() {
        return tradedBalance;
    }

    public void setTradedBalance(Double tradedBalance) {
        this.tradedBalance = tradedBalance;
    }
}
