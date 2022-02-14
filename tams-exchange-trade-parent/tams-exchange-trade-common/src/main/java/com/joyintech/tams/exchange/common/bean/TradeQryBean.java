package com.joyintech.tams.exchange.common.bean;

import java.io.Serializable;
import java.util.List;

public class TradeQryBean implements Serializable {
    private static final long serialVersionUID = 8500092620600203086L;

    private String entrustId;
    private String startDay;
    private String endDay;
    private String accountId;
    private String market;
    private String stockCode;
    private String entrustProp;
    private List<String> portfolioList;

    public TradeQryBean() {
      
    }

    public TradeQryBean(String accountId, String market, String stockCode, String entrustProp, List<String> portfolioList) {
        this.accountId = accountId;
        this.market = market;
        this.stockCode = stockCode;
        this.entrustProp = entrustProp;
        this.portfolioList = portfolioList;
    }

    public TradeQryBean(String accountId, String market, String stockCode, String entrustProp, List<String> portfolioList, String startDay, String endDay) {
        this.accountId = accountId;
        this.market = market;
        this.stockCode = stockCode;
        this.entrustProp = entrustProp;
        this.portfolioList = portfolioList;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
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

    public String getEntrustProp() {
        return entrustProp;
    }

    public void setEntrustProp(String entrustProp) {
        this.entrustProp = entrustProp;
    }

    public List<String> getPortfolioList() {
        return portfolioList;
    }

    public void setPortfolioList(List<String> portfolioList) {
        this.portfolioList = portfolioList;
    }
}
