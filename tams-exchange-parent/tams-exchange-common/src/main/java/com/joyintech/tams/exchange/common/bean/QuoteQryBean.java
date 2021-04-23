package com.joyintech.tams.exchange.common.bean;

import java.io.Serializable;

public class QuoteQryBean implements Serializable {
    private int quoteType;
    private String accountId;
    // 大宗行情查询
    private String orderNo;
    private String propSeatNo;
    private String bsFlag;
    private String entrustProp;
    private String entrustBs;
    private String stockCode;
    private int pageSize;
    private String postStr;
    private String oppositeLiaisonTel; //对手方交易员代码
    private String liaisonTel; // 本方交易员代码
    private String contractId;
    private String brokerEntrustId;
    private String buyConfirmId;
    private String market;


    public int getQuoteType() {
        return quoteType;
    }

    public void setQuoteType(int quoteType) {
        this.quoteType = quoteType;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPropSeatNo() {
        return propSeatNo;
    }

    public void setPropSeatNo(String propSeatNo) {
        this.propSeatNo = propSeatNo;
    }

    public String getBsFlag() {
        return bsFlag;
    }

    public void setBsFlag(String bsFlag) {
        this.bsFlag = bsFlag;
    }

    public String getEntrustProp() {
        return entrustProp;
    }

    public void setEntrustProp(String entrustProp) {
        this.entrustProp = entrustProp;
    }

    public String getEntrustBs() {
        return entrustBs;
    }

    public void setEntrustBs(String entrustBs) {
        this.entrustBs = entrustBs;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getPostStr() {
        return postStr;
    }

    public void setPostStr(String postStr) {
        this.postStr = postStr;
    }

    public String getOppositeLiaisonTel() {
        return oppositeLiaisonTel;
    }

    public void setOppositeLiaisonTel(String oppositeLiaisonTel) {
        this.oppositeLiaisonTel = oppositeLiaisonTel;
    }

    public String getLiaisonTel() {
        return liaisonTel;
    }

    public void setLiaisonTel(String liaisonTel) {
        this.liaisonTel = liaisonTel;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getBrokerEntrustId() {
        return brokerEntrustId;
    }

    public void setBrokerEntrustId(String brokerEntrustId) {
        this.brokerEntrustId = brokerEntrustId;
    }

    public String getBuyConfirmId() {
        return buyConfirmId;
    }

    public void setBuyConfirmId(String buyConfirmId) {
        this.buyConfirmId = buyConfirmId;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }
}
