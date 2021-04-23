package com.joyintech.tams.exchange.common.db.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.exchange.common.bean.OrderPlaceBean;
import com.joyintech.tams.exchange.common.utils.DoubleSerializerUtils;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Entrust extends BaseEntity implements Serializable, EntrustInterface, Cloneable, KeyMethodInterface<String> {
    @NotNull
    @Size(max = 32)
    @Id
    private String id;

     private String positionStr;

    @Size(max = 32)
    private String parentOrderId;

    @NotNull
    @Size(max = 32)
    private String instructionId;

    @NotNull
    @Size(max = 18)
    private String portfolioId;

    @NotNull
    @Size(max = 18)
    private String userId;

    @NotNull
    @Size(max = 255)
    private String username;

    @NotNull
    @Size(max = 32)
    private String accountId;

    @NotNull
    @Size(max = 32)
    private String financeAccount;

    @NotNull
    @Size(max = 32)
    private String accountName;

    @NotNull
    @Size(max = 3)
    private String assetType;

    @NotNull
    @Size(max = 8)
    private String market;

    @NotNull
    @Size(max = 32)
    private String stockCode;

    @NotNull
    @Size(max = 32)
    private String stockName;

    @NotNull
    @Size(max = 1)
    private String entrustType;

    @NotNull
    @Size(max = 4)
    private String entrustProp;

    @NotNull
    @Size(max = 1)
    private String entrustBs;

    @NotNull
    @Size(max = 2)
    private String bsFlag;

    @NotNull
    @Digits(integer = 20, fraction = 0)
    private long entrustVolume;

    @NotNull
    @Digits(integer = 20, fraction = 4)
    @JsonSerialize(using = DoubleSerializerUtils.class)
    private BigDecimal entrustPrice;//净价价格

    @Digits(integer = 20, fraction = 4)
    @JsonSerialize(using = DoubleSerializerUtils.class)
    private BigDecimal fullPrice;//全价价格

    @Digits(integer = 10, fraction = 4)
    @JsonSerialize(using = DoubleSerializerUtils.class)
    private BigDecimal expireYearRate;//到期收益率

    @NotNull
    @Digits(integer = 20, fraction = 4)
    @JsonSerialize(using = DoubleSerializerUtils.class)
    private BigDecimal frozenBalance;

    @NotNull
    @Digits(integer = 20, fraction = 0)
    private long frozenVolume;

    @NotNull
    @Digits(integer = 20, fraction = 4)
    @JsonSerialize(using = DoubleSerializerUtils.class)
    private BigDecimal tradedBalance;

    @NotNull
    @Digits(integer = 20, fraction = 0)
    private long tradedVolume;

    @NotNull
    @Digits(integer = 20, fraction = 4)
    @JsonSerialize(using = DoubleSerializerUtils.class)
    private BigDecimal tradedPrice;

    @NotNull
    @Digits(integer = 20, fraction = 0)
    private long cancelVolume;

    private String reportTime;

    private String tradeTime;

    private String cancelTime;


    @NotNull
    @Digits(integer = 20, fraction = 0)
    private int errorCode;

    @Size(max = 128)
    private String errorMsg;

    @NotNull
    @Size(max = 10)
    private String tradingDay;

    @Size(max = 32)
    private String localEntrustId;

    @Size(max = 32)
    private String brokerEntrustId;

    @Size(max = 64)
    private String ipMac;

    @Size(max = 64)
    private String remark;

    private Date createTime;

    private Date updateTime;

    @NotNull
    @Digits(integer = 6, fraction = 0)
    private int entrustStatus;

    @Size(max = 32)
    private String origEntrustId;

    private int count;

    @NotNull
    private String isFullPrice;//Y:是 ， N：否

    @Size(max = 32)
    private String reportNo;//柜台报盘编号

    private boolean noCancelEntrust = false;

    /**
     * 操作人员ID 执行撤销异常委托操作人员ID
     */
    private String operateUser;

    @Size(max = 8)
    private String offsetFlag;

    @Size(max = 8)
    private String hedgeFlag;
    @NotNull
    @Digits(integer = 20, fraction = 4)
    @JsonSerialize(using = DoubleSerializerUtils.class)
    private BigDecimal entrustBalance;

    public boolean isNoCancelEntrust() {
        return noCancelEntrust;
    }

    public void setNoCancelEntrust(boolean noCancelEntrust) {
        this.noCancelEntrust = noCancelEntrust;
    }

    public Entrust() {
        this.entrustPrice = new BigDecimal("0.00");
        this.fullPrice = new BigDecimal("0.00");
        this.expireYearRate = new BigDecimal("0.00");
        this.frozenBalance = new BigDecimal("0.00");
        this.tradedBalance = new BigDecimal("0.00");
        this.tradedPrice = new BigDecimal("0.00");
        this.entrustBalance = new BigDecimal("0.00");
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParentOrderId() {
        return parentOrderId;
    }

    public void setParentOrderId(String parentOrderId) {
        this.parentOrderId = parentOrderId == null ? null : parentOrderId.trim();
    }

    public String getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(String instructionId) {
        this.instructionId = instructionId == null ? null : instructionId.trim();
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
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

    public String getEntrustType() {
        return entrustType;
    }

    public void setEntrustType(String entrustType) {
        this.entrustType = entrustType == null ? null : entrustType.trim();
    }

    public String getEntrustProp() {
        return entrustProp;
    }

    public void setEntrustProp(String entrustProp) {
        this.entrustProp = entrustProp == null ? null : entrustProp.trim();
    }

    public String getEntrustBs() {
        return entrustBs;
    }

    public void setEntrustBs(String entrustBs) {
        this.entrustBs = entrustBs == null ? null : entrustBs.trim();
    }

    public String getBsFlag() {
        return bsFlag;
    }

    public void setBsFlag(String bsFlag) {
        this.bsFlag = bsFlag == null ? null : bsFlag.trim();
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

    public BigDecimal getExpireYearRate() {
        return expireYearRate;
    }

    public void setExpireYearRate(BigDecimal expireYearRate) {
        this.expireYearRate = expireYearRate;
    }

    public BigDecimal getFrozenBalance() {
        return frozenBalance;
    }

    public void setFrozenBalance(BigDecimal frozenBalance) {
        this.frozenBalance = frozenBalance;
    }

    public long getFrozenVolume() {
        return frozenVolume;
    }

    public void setFrozenVolume(long frozenVolume) {
        this.frozenVolume = frozenVolume;
    }

    public BigDecimal getTradedBalance() {
        return tradedBalance;
    }

    public void setTradedBalance(BigDecimal tradedBalance) {
        this.tradedBalance = tradedBalance;
    }

    public long getTradedVolume() {
        return tradedVolume;
    }

    public void setTradedVolume(long tradedVolume) {
        this.tradedVolume = tradedVolume;
    }

    public BigDecimal getTradedPrice() {
        return tradedPrice;
    }

    public void setTradedPrice(BigDecimal tradedPrice) {
        this.tradedPrice = tradedPrice;
    }

    public long getCancelVolume() {
        return cancelVolume;
    }

    public void setCancelVolume(long cancelVolume) {
        this.cancelVolume = cancelVolume;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime == null ? null : reportTime.trim();
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime == null ? null : tradeTime.trim();
    }

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime == null ? null : cancelTime.trim();
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
        this.errorMsg = errorMsg == null ? null : errorMsg.trim();
    }

    public String getTradingDay() {
        return tradingDay;
    }

    public void setTradingDay(String tradingDay) {
        this.tradingDay = tradingDay == null ? null : tradingDay.trim();
    }

    public String getLocalEntrustId() {
        return localEntrustId;
    }

    public void setLocalEntrustId(String localEntrustId) {
        this.localEntrustId = localEntrustId == null ? null : localEntrustId.trim();
    }

    public String getBrokerEntrustId() {
        return brokerEntrustId;
    }

    public void setBrokerEntrustId(String brokerEntrustId) {
        this.brokerEntrustId = brokerEntrustId == null ? null : brokerEntrustId.trim();
    }

    public String getIpMac() {
        return ipMac;
    }

    public void setIpMac(String ipMac) {
        this.ipMac = ipMac == null ? null : ipMac.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public int getEntrustStatus() {
        return entrustStatus;
    }

    public void setEntrustStatus(int entrustStatus) {
        this.entrustStatus = entrustStatus;
    }

    public String getOrigEntrustId() {
        return origEntrustId;
    }

    public void setOrigEntrustId(String origEntrustId) {
        this.origEntrustId = origEntrustId == null ? null : origEntrustId.trim();
    }

    public String getPositionStr() {
        return positionStr;
    }

    public void setPositionStr(String positionStr) {
        this.positionStr = positionStr;
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    @Override
    public String getIsFullPrice() {
        return isFullPrice;
    }

    @Override
    public void setIsFullPrice(String isFullPrice) {
        this.isFullPrice = isFullPrice;
    }

    private static Entrust templateEntrust = new Entrust();

    public static Entrust createEntrust(OrderPlaceBean orderPlaceBean) {
        try {
            Entrust newOrder = (Entrust) templateEntrust.clone();
            newOrder.setInstructionId(orderPlaceBean.getInstructionId());
            newOrder.setPortfolioId(orderPlaceBean.getPortfolioId());
            newOrder.setUserId(orderPlaceBean.getUserId());
            newOrder.setUsername(orderPlaceBean.getUsername());
            newOrder.setAccountId(orderPlaceBean.getAccountId());
            newOrder.setEntrustProp(orderPlaceBean.getEntrustProp());
            newOrder.setEntrustBs(orderPlaceBean.getEntrustBs());
            newOrder.setStockCode(orderPlaceBean.getStockCode());
            newOrder.setEntrustVolume(orderPlaceBean.getEntrustVolume());
            newOrder.setEntrustPrice(orderPlaceBean.getEntrustPrice());
            newOrder.setFullPrice(orderPlaceBean.getFullPrice());
            newOrder.setOffsetFlag(orderPlaceBean.getOffsetFlag());
            newOrder.setHedgeFlag(orderPlaceBean.getHedgeFlag());
            return newOrder;
        } catch (Exception e) {
        }
        return new Entrust();
    }

    public Entrust cloneEntrust() {
        try {
            return (Entrust) this.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public String getOperateUser() {
        return operateUser;
    }

    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser;
    }

    public BigDecimal getEntrustBalance() {
        return entrustBalance;
    }

    public void setEntrustBalance(BigDecimal entrustBalance) {
        this.entrustBalance = entrustBalance;
    }
}