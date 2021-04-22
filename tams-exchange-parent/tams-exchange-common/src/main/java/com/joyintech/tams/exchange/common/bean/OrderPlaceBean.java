package com.joyintech.tams.exchange.common.bean;

import com.joyintech.tams.exchange.common.db.domain.BatchInfo;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderPlaceBean implements Serializable {
    private String instructionId;
    private String portfolioId;
    private String userId;
    private String username;
    private String accountId;
    private String algorithmId;
    private String bsFlag;
    private String stockCode;
    private String entrustProp;
    private String entrustBs;
    private long entrustVolume;
    private BigDecimal entrustPrice;//净价价格
    private BigDecimal fullPrice;//全价价格
    private BatchInfo batchInfo;

    private String offsetFlag;

    private String hedgeFlag;

    public String getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(String instructionId) {
        this.instructionId = instructionId;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAlgorithmId() {
        return algorithmId;
    }

    public void setAlgorithmId(String algorithmId) {
        this.algorithmId = algorithmId;
    }

    public String getBsFlag() {
        return bsFlag;
    }

    public void setBsFlag(String bsFlag) {
        this.bsFlag = bsFlag;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public long getEntrustVolume() {
        return entrustVolume;
    }

    public void setEntrustVolume(long entrustVolume) {
        this.entrustVolume = entrustVolume;
    }

    public BigDecimal getEntrustPrice() {
        return entrustPrice;
    }

    public void setEntrustPrice(BigDecimal entrustPrice) {
        this.entrustPrice = entrustPrice;
    }

    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(BigDecimal fullPrice) {
        this.fullPrice = fullPrice;
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

    public BatchInfo getBatchInfo() {
        return batchInfo;
    }

    public void setBatchInfo(BatchInfo batchInfo) {
        this.batchInfo = batchInfo;
    }

    public String getOffsetFlag() {
        return offsetFlag;
    }

    public void setOffsetFlag(String offsetFlag) {
        this.offsetFlag = offsetFlag;
    }

    public String getHedgeFlag() {
        return hedgeFlag;
    }

    public void setHedgeFlag(String hedgeFlag) {
        this.hedgeFlag = hedgeFlag;
    }
}
