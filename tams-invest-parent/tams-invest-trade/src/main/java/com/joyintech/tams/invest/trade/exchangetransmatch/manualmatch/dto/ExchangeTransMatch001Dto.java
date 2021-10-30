package com.joyintech.tams.invest.trade.exchangetransmatch.manualmatch.dto;

import com.joyintech.background.component.common.pack.common.base.BaseEntity;

import java.util.Date;

public class ExchangeTransMatch001Dto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -3553548823534945106L;
    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 交易平台
     */
    private String tradePlat;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 证券账号
     */
    private String secAcctNo;

    /**
     * 申请编号
     */
    private String applyNo;

    /**
     * 资产代码
     */
    private String finprodId;

    /**
     * 资产类型
     */
    private String finprodType2;

    /**
     * 交易日期
     */
    private Date tradeDate;

    /**
     * 匹配状态
     */
    private String matchStatus;

    /**
     * 交易金额1
     */
    private String tradeAmt1;

    /**
     * 交易金额2
     */
    private String tradeAmt2;

    /**
     * 匹配方式
     */
    private String matchType;

    /**
     * 成交编号
     */
    private String dealId;

    /**
     * 业务编号
     */
    private String busiCode;

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public String getTradePlat() {
        return tradePlat;
    }

    public void setTradePlat(String tradePlat) {
        this.tradePlat = tradePlat;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getSecAcctNo() {
        return secAcctNo;
    }

    public void setSecAcctNo(String secAcctNo) {
        this.secAcctNo = secAcctNo;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodType2() {
        return finprodType2;
    }

    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

    public String getTradeAmt1() {
        return tradeAmt1;
    }

    public void setTradeAmt1(String tradeAmt1) {
        this.tradeAmt1 = tradeAmt1;
    }

    public String getTradeAmt2() {
        return tradeAmt2;
    }

    public void setTradeAmt2(String tradeAmt2) {
        this.tradeAmt2 = tradeAmt2;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getDealId() {
        return dealId;
    }

    public void setDealId(String dealId) {
        this.dealId = dealId;
    }

    public String getBusiCode() {
        return busiCode;
    }

    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }
}
