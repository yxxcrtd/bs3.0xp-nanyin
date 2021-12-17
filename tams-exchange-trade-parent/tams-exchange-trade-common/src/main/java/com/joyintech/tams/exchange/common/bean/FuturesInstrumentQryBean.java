package com.joyintech.tams.exchange.common.bean;

public class FuturesInstrumentQryBean {
    private String accountId;
    private String stockCode;

    public FuturesInstrumentQryBean(String accountId, String stockCode) {
        this.accountId = accountId;
        this.stockCode = stockCode;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }
}
