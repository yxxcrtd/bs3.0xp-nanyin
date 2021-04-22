package com.joyintech.tams.exchange.common.bean;

public class InstrumentQryBean {
    private String market;
    private String accountId;
    private String postStr;
    private int rowCount;

    public InstrumentQryBean() {
    }

    public InstrumentQryBean(String market, String accountId, String postStr, int rowCount) {
        this.market = market;
        this.accountId = accountId;
        this.postStr = postStr;
        this.rowCount = rowCount;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPostStr() {
        return postStr;
    }

    public void setPostStr(String postStr) {
        this.postStr = postStr;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
}
