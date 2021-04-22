package com.joyintech.tams.exchange.common.bean;

import java.io.Serializable;
import java.util.List;

public class EntrustQryBean implements Serializable {
    public String userId;
    public String startDay;
    public String endDay;
    public String accountId;
    public String financeAccount;
    public String portfolioId;
    public String market;
    public String stockCode;
    public String entrustProp;
    public int entrustStatus;
    public List<String> portfolioList;

    public EntrustQryBean() {
    }

    public EntrustQryBean(String userId, String startDay, String endDay, String accountId, String financeAccount, String portfolioId, String market, String stockCode, String entrustProp, int entrustStatus, List<String> portfolioList) {
        this.userId = userId;
        this.startDay = startDay;
        this.endDay = endDay;
        this.accountId = accountId;
        this.financeAccount = financeAccount;
        this.portfolioId = portfolioId;
        this.market = market;
        this.stockCode = stockCode;
        this.entrustProp = entrustProp;
        this.entrustStatus = entrustStatus;
        this.portfolioList = portfolioList;
    }

    public EntrustQryBean(String userId, String accountId, String financeAccount, String portfolioId, String market, String stockCode, String entrustProp, int entrustStatus, List<String> portfolioList) {
        this.userId = userId;
        this.accountId = accountId;
        this.financeAccount = financeAccount;
        this.portfolioId = portfolioId;
        this.market = market;
        this.stockCode = stockCode;
        this.entrustProp = entrustProp;
        this.entrustStatus = entrustStatus;
        this.portfolioList = portfolioList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
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

    public String getEntrustProp() {
        return entrustProp;
    }

    public void setEntrustProp(String entrustProp) {
        this.entrustProp = entrustProp;
    }

    public int getEntrustStatus() {
        return entrustStatus;
    }

    public void setEntrustStatus(int entrustStatus) {
        this.entrustStatus = entrustStatus;
    }

    public String getFinanceAccount() {
        return financeAccount;
    }

    public void setFinanceAccount(String financeAccount) {
        this.financeAccount = financeAccount;
    }

    public List<String> getPortfolioList() {
        return portfolioList;
    }

    public void setPortfolioList(List<String> portfolioList) {
        this.portfolioList = portfolioList;
    }
}
