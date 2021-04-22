package com.joyintech.tams.exchange.common.bean;

public class PledgeDiscount {
    private String stockCode;
    private String stockName;
    private String market;
    private String stockType;
    private double discount;//折算率
    private int bondEnable;//质押可用
    private int bondlastbal;//上次余额
    private int bondbal;//质押余额
    private String stockAccount;//证券账号
    private String financeAccount;//资金账户
    private String accountId;
    private String finprodMarketId;//市场代码

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getBondEnable() {
        return bondEnable;
    }

    public void setBondEnable(int bondEnable) {
        this.bondEnable = bondEnable;
    }

    public String getStockAccount() {
        return stockAccount;
    }

    public void setStockAccount(String stockAccount) {
        this.stockAccount = stockAccount;
    }

    public int getBondlastbal() {
        return bondlastbal;
    }

    public void setBondlastbal(int bondlastbal) {
        this.bondlastbal = bondlastbal;
    }

    public int getBondbal() {
        return bondbal;
    }

    public void setBondbal(int bondbal) {
        this.bondbal = bondbal;
    }

    public String getFinanceAccount() {
        return financeAccount;
    }

    public void setFinanceAccount(String financeAccount) {
        this.financeAccount = financeAccount;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }
}
