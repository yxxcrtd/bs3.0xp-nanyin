package com.joyintech.tams.exchange.common.db.domain;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Algorithm implements Serializable, KeyMethodInterface<String>, OrderInterface {
    @Id
    @NotNull
    @Size(max = 32)
    private String id;

    @NotNull
    @Size(max = 32)
    private String algorithmName;

    @NotNull
    private Date beginTime;

    @NotNull
    private Date endTime;

    @NotNull
    @Size(max = 1)
    private String algorithmStatus;

    @Size(max = 1024)
    private String parameter;

    @NotNull
    @Size(max = 32)
    private String stockCode;

    @NotNull
    @Digits(integer = 10, fraction = 0)
    private Long volume;

    @NotNull
    @Size(max = 1)
    private String entrustBs;

    @NotNull
    @Size(max = 1)
    private String offsetFlag;

    @NotNull
    @Digits(integer = 8, fraction = 0)
    private Integer limitAction;

    @NotNull
    @Digits(integer = 8, fraction = 0)
    private Integer afterAction;

    @NotNull
    @Digits(integer = 10, fraction = 0)
    private Long tradedVolume = 0L;

    @Size(max = 32)
    private String externalId;

    @NotNull
    @Size(max = 32)
    private String instructionId;

    @NotNull
    @Digits(integer = 8, fraction = 0)
    private int errorCode;

    @Size(max = 64)
    private String errorMsg;

    @NotNull
    @Size(max = 10)
    private String tradingDay;

    @NotNull
    private Date createTime;

    @NotNull
    private Date updateTime;


    private int count;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName == null ? null : algorithmName.trim();
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getAlgorithmStatus() {
        return algorithmStatus;
    }

    public void setAlgorithmStatus(String algorithmStatus) {
        this.algorithmStatus = algorithmStatus == null ? null : algorithmStatus.trim();
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter == null ? null : parameter.trim();
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode == null ? null : stockCode.trim();
    }

    @Override
    public String getStockName() {
        return null;
    }

    @Override
    public void setStockName(String stockName) {

    }

    @Override
    public String getEntrustType() {
        return null;
    }

    @Override
    public void setEntrustType(String entrustType) {

    }

    @Override
    public String getEntrustProp() {
        return null;
    }

    @Override
    public void setEntrustProp(String entrustProp) {

    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public String getEntrustBs() {
        return entrustBs;
    }

    public void setEntrustBs(String entrustBs) {
        this.entrustBs = entrustBs == null ? null : entrustBs.trim();
    }

    @Override
    public String getBsFlag() {
        return null;
    }

    @Override
    public void setBsFlag(String bsFlag) {

    }

    @Override
    public long getEntrustVolume() {
        return 0;
    }

    @Override
    public void setEntrustVolume(long entrustVolume) {

    }

    @Override
    public BigDecimal getEntrustPrice() {
        return null;
    }

    @Override
    public void setEntrustPrice(BigDecimal entrustPrice) {

    }

    @Override
    public BigDecimal getFrozenBalance() {
        return null;
    }

    @Override
    public void setFrozenBalance(BigDecimal frozenBalance) {

    }

    @Override
    public long getFrozenVolume() {
        return 0;
    }

    @Override
    public void setFrozenVolume(long frozenVolume) {

    }

    @Override
    public BigDecimal getTradedBalance() {
        return null;
    }

    @Override
    public void setTradedBalance(BigDecimal tradedBalance) {

    }

    public String getOffsetFlag() {
        return offsetFlag;
    }

    public void setOffsetFlag(String offsetFlag) {
        this.offsetFlag = offsetFlag == null ? null : offsetFlag.trim();
    }

    @Override
    public String getHedgeFlag() {
        return null;
    }

    @Override
    public void setHedgeFlag(String hedgeFlag) {

    }

    public Integer getLimitAction() {
        return limitAction;
    }

    public void setLimitAction(Integer limitAction) {
        this.limitAction = limitAction;
    }

    public Integer getAfterAction() {
        return afterAction;
    }

    public void setAfterAction(Integer afterAction) {
        this.afterAction = afterAction;
    }

    public long getTradedVolume() {
        return tradedVolume;
    }

    @Override
    public BigDecimal getTradedPrice() {
        return null;
    }

    @Override
    public void setTradedPrice(BigDecimal tradedPrice) {

    }

    @Override
    public long getCancelVolume() {
        return 0;
    }

    @Override
    public void setCancelVolume(long cancelVolume) {

    }

    public void setTradedVolume(long tradedVolume) {
        this.tradedVolume = tradedVolume;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId == null ? null : externalId.trim();
    }

    public String getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(String instructionId) {
        this.instructionId = instructionId;
    }

    @Override
    public String getPortfolioId() {
        return null;
    }

    @Override
    public void setPortfolioId(String portfolioId) {

    }

    @Override
    public String getUserId() {
        return null;
    }

    @Override
    public void setUserId(String userId) {

    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public void setUsername(String username) {

    }

    @Override
    public String getAccountId() {
        return null;
    }

    @Override
    public void setAccountId(String accountId) {

    }

    @Override
    public String getFinanceAccount() {
        return null;
    }

    @Override
    public void setFinanceAccount(String financeAccount) {

    }

    @Override
    public String getAccountName() {
        return null;
    }

    @Override
    public void setAccountName(String accountName) {

    }

    @Override
    public String getAssetType() {
        return null;
    }

    @Override
    public void setAssetType(String assetType) {

    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String getTradingDay() {
        return tradingDay;
    }

    @Override
    public void setTradingDay(String tradingDay) {
        this.tradingDay = tradingDay;
    }

    @Override
    public String getIpMac() {
        return null;
    }

    @Override
    public void setIpMac(String ipMac) {

    }

    @Override
    public String getRemark() {
        return null;
    }

    @Override
    public void setRemark(String remark) {

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

    @Override
    public String getIsFullPrice() {
        return null;
    }

    @Override
    public void setIsFullPrice(String isFullPrice) {

    }

    @Override
    public BigDecimal getFullPrice() {
        return null;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}