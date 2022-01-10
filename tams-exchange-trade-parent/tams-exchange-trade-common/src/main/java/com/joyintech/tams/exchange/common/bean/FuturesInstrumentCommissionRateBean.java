package com.joyintech.tams.exchange.common.bean;

/**
 * @author liujianping
 * @ClassName FuturesCommissionRateBean
 * @Description
 * @date 2020/11/20 10:13
 * @Version
 **/

public class FuturesInstrumentCommissionRateBean {

    private String accountId;

    private String financeAccount;

    private String market;

    private String stockCode;

    private String brokerId;

    private String investorRange;

    private double openRatioByMoney;

    private double openRatioByVolume;

    private double closeRatioByMoney;

    private double closeRatioByVolume;

    private double closeTodayRatioByMoney;

    private double closeTodayRatioByVolume;

    private String bizType;

    private String investUnitId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getFinanceAccount() {
        return financeAccount;
    }

    public void setFinanceAccount(String financeAccount) {
        this.financeAccount = financeAccount;
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

    public String getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(String brokerID) {
        this.brokerId = brokerID;
    }

    public String getInvestorRange() {
        return investorRange;
    }

    public void setInvestorRange(String investorRange) {
        this.investorRange = investorRange;
    }

    public double getOpenRatioByMoney() {
        return openRatioByMoney;
    }

    public void setOpenRatioByMoney(double openRatioByMoney) {
        this.openRatioByMoney = openRatioByMoney;
    }

    public double getOpenRatioByVolume() {
        return openRatioByVolume;
    }

    public void setOpenRatioByVolume(double openRatioByVolume) {
        this.openRatioByVolume = openRatioByVolume;
    }

    public double getCloseRatioByMoney() {
        return closeRatioByMoney;
    }

    public void setCloseRatioByMoney(double closeRatioByMoney) {
        this.closeRatioByMoney = closeRatioByMoney;
    }

    public double getCloseRatioByVolume() {
        return closeRatioByVolume;
    }

    public void setCloseRatioByVolume(double closeRatioByVolume) {
        this.closeRatioByVolume = closeRatioByVolume;
    }

    public double getCloseTodayRatioByMoney() {
        return closeTodayRatioByMoney;
    }

    public void setCloseTodayRatioByMoney(double closeTodayRatioByMoney) {
        this.closeTodayRatioByMoney = closeTodayRatioByMoney;
    }

    public double getCloseTodayRatioByVolume() {
        return closeTodayRatioByVolume;
    }

    public void setCloseTodayRatioByVolume(double closeTodayRatioByVolume) {
        this.closeTodayRatioByVolume = closeTodayRatioByVolume;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getInvestUnitId() {
        return investUnitId;
    }

    public void setInvestUnitId(String investUnitId) {
        this.investUnitId = investUnitId;
    }
}