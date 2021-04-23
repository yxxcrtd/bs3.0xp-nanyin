package com.joyintech.tams.exchange.common.db.domain;

import org.apache.ibatis.annotations.Options;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @author liujianping
 * @ClassName FuturesInstrumentMarginRateBean
 * @Description
 * @date 2020/11/20 10:13
 * @Version
 **/

public class FuturesInstrumentMarginRateBean implements Serializable, KeyMethodInterface<String> {
    private String id;

    private String accountId;

    private String financeAccount;

    private String market;

    private String stockCode;

    private String brokerId;

    private String investorRange;

    private String hedgeFlag;

    private double longMarginRatioByMoney;

    private double longMarginRatioByVolume;

    private double shortMarginRatioByMoney;

    private double shortMarginRatioByVolume;

    private boolean isRelative;

    private String investUnitId;

    private int count;

    @Id
    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

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

    public void setBrokerId(String brokerId) {
        this.brokerId = brokerId;
    }

    public String getInvestorRange() {
        return investorRange;
    }

    public void setInvestorRange(String investorRange) {
        this.investorRange = investorRange;
    }

    public String getHedgeFlag() {
        return hedgeFlag;
    }

    public void setHedgeFlag(String hedgeFlag) {
        this.hedgeFlag = hedgeFlag;
    }

    public double getLongMarginRatioByMoney() {
        return longMarginRatioByMoney;
    }

    public void setLongMarginRatioByMoney(double longMarginRatioByMoney) {
        this.longMarginRatioByMoney = longMarginRatioByMoney;
    }

    public double getLongMarginRatioByVolume() {
        return longMarginRatioByVolume;
    }

    public void setLongMarginRatioByVolume(double longMarginRatioByVolume) {
        this.longMarginRatioByVolume = longMarginRatioByVolume;
    }

    public double getShortMarginRatioByMoney() {
        return shortMarginRatioByMoney;
    }

    public void setShortMarginRatioByMoney(double shortMarginRatioByMoney) {
        this.shortMarginRatioByMoney = shortMarginRatioByMoney;
    }

    public double getShortMarginRatioByVolume() {
        return shortMarginRatioByVolume;
    }

    public void setShortMarginRatioByVolume(double shortMarginRatioByVolume) {
        this.shortMarginRatioByVolume = shortMarginRatioByVolume;
    }

    public boolean isRelative() {
        return isRelative;
    }

    public void setRelative(boolean relative) {
        isRelative = relative;
    }

    public String getInvestUnitId() {
        return investUnitId;
    }

    public void setInvestUnitId(String investUnitId) {
        this.investUnitId = investUnitId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}