package com.joyintech.tams.exchange.common.bean;

import java.io.Serializable;

public class StandardBonds implements Serializable {
    private String stockCode; //对应的质押券代码
    private String stockName;
    private String market;
    private double discount;
    private int bondEnable;//标准券可融资数量
    private int bondFrozen;//标准券冻结
    private int bondUnFrozen;//标准券解冻
    private String stockAccount;//证券账号
    private Double netAsset;//净资产
    private int bondEnout;//可出库数量
    private String stockType;//证券类别
    private String accountId;

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

    public int getBondFrozen() {
        return bondFrozen;
    }

    public void setBondFrozen(int bondFrozen) {
        this.bondFrozen = bondFrozen;
    }

    public int getBondUnFrozen() {
        return bondUnFrozen;
    }

    public void setBondUnFrozen(int bondUnFrozen) {
        this.bondUnFrozen = bondUnFrozen;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockAccount() {
        return stockAccount;
    }

    public void setStockAccount(String stockAccount) {
        this.stockAccount = stockAccount;
    }

    public Double getNetAsset() {
        return netAsset;
    }

    public void setNetAsset(Double netAsset) {
        this.netAsset = netAsset;
    }

    public int getBondEnout() {
        return bondEnout;
    }

    public void setBondEnout(int bondEnout) {
        this.bondEnout = bondEnout;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
