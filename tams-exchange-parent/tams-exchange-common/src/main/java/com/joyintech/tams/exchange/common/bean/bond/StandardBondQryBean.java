package com.joyintech.tams.exchange.common.bean.bond;

import java.io.Serializable;

public class StandardBondQryBean implements Serializable {
    private String accountId;
    private String market;
    private String stockCode;

    public StandardBondQryBean() {
    }

    public StandardBondQryBean(String accountId, String market, String stockCode) {
        this.accountId = accountId;
        this.market = market;
        this.stockCode = stockCode;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }
}
