package com.joyintech.tams.exchange.common.bean;

public class EnableQryBean {

    private String stockCode;

    private String entrustProp;
    private String entrustBs;

    private double entrustPrice;
    private String accountId;

    public EnableQryBean() {
    }

    public EnableQryBean(String stockCode, String entrustProp, String entrustBs, double entrustPrice, String accountId) {
        this.stockCode = stockCode;
        this.entrustProp = entrustProp;
        this.entrustBs = entrustBs;
        this.entrustPrice = entrustPrice;
        this.accountId = accountId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }


    public String getEntrustProp() {
        return entrustProp;
    }

    public void setEntrustProp(String entrustProp) {
        this.entrustProp = entrustProp;
    }

    public String getEntrustBs() {
        return entrustBs;
    }

    public void setEntrustBs(String entrustBs) {
        this.entrustBs = entrustBs;
    }

    public double getEntrustPrice() {
        return entrustPrice;
    }

    public void setEntrustPrice(double entrustPrice) {
        this.entrustPrice = entrustPrice;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
