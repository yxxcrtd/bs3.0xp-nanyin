package com.joyintech.tams.exchange.common.db.domain;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class PositionBean implements Serializable, KeyMethodInterface<String> {
    @NotNull
    @Size(max = 32)
    private String id;

    @NotNull
    @Size(max = 32)
    private String accountId;

    @Size(max = 32)
    private String financeAccount;

    @Size(max = 8)
    private String market;

    @NotNull
    @Size(max = 32)
    private String stockCode;

    @Size(max = 64)
    private String stockName;

    private double costPrice;//持仓成本价

    private double marketValue;//市值

    @Digits(integer = 20, fraction = 0)
    private long holdVolume;//股份余额

    @Digits(integer = 20, fraction = 0)
    private long enableVolume;//股份可用

    @Digits(integer = 20, fraction = 0)
    private long sellFrozenVolume;//持仓冻结 仅卖出有效

    @Digits(integer = 20, fraction = 0)
    private long todayVolume;//今仓

    private Date createTime;

    private Date updateTime;

    private String positionStr;//定位串

    private int count;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public String getFinanceAccount() {
        return financeAccount;
    }

    public void setFinanceAccount(String financeAccount) {
        this.financeAccount = financeAccount == null ? null : financeAccount.trim();
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market == null ? null : market.trim();
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode == null ? null : stockCode.trim();
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName == null ? null : stockName.trim();
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }

    public long getHoldVolume() {
        return holdVolume;
    }

    public void setHoldVolume(long holdVolume) {
        this.holdVolume = holdVolume;
    }

    public long getEnableVolume() {
        return enableVolume;
    }

    public void setEnableVolume(long enableVolume) {
        this.enableVolume = enableVolume;
    }

    public long getSellFrozenVolume() {
        return sellFrozenVolume;
    }

    public void setSellFrozenVolume(long sellFrozenVolume) {
        this.sellFrozenVolume = sellFrozenVolume;
    }

    public long getTodayVolume() {
        return todayVolume;
    }

    public void setTodayVolume(long todayVolume) {
        this.todayVolume = todayVolume;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPositionStr() {
        return positionStr;
    }

    public void setPositionStr(String positionStr) {
        this.positionStr = positionStr;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}