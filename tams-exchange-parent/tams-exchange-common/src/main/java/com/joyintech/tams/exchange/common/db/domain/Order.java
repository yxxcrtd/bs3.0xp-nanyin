package com.joyintech.tams.exchange.common.db.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.exchange.common.bean.OrderPlaceBean;
import com.joyintech.tams.exchange.common.utils.DoubleSerializerUtils;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Order implements Serializable, Cloneable, OrderInterface, KeyMethodInterface<String> {
    @NotNull
    @Size(max = 32)
    @Id
    private String id;

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

    @Size(max = 32)
    private String accountName;

    @Size(max = 32)
    private String financeAccount;

    @NotNull
    @Size(max = 3)
    private String assetType;

    @Size(max = 32)
    private String algorithmId;

    @Size(max = 32)
    private String relativeOrderId;

    @NotNull
    @Size(max = 1)
    private String businessFlag;

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
    @Size(max = 32)
    private String stockCode;

    @NotNull
    @Size(max = 32)
    private String stockName;

    @NotNull
    @Digits(integer = 6, fraction = 0)
    private int orderStatus;

    @NotNull
    @Digits(integer = 20, fraction = 0)
    private long orderVolume;

    @Digits(integer = 20, fraction = 4)
    @JsonSerialize(using = DoubleSerializerUtils.class)
    private BigDecimal orderPrice;

    @Digits(integer = 20, fraction = 4)
    @JsonSerialize(using = DoubleSerializerUtils.class)
    private BigDecimal orderBalance;

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

    @NotNull
    @Digits(integer = 20, fraction = 4)
    @JsonSerialize(using = DoubleSerializerUtils.class)
    private BigDecimal entrustBalance;

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

    @NotNull
    @Digits(integer = 8, fraction = 0)
    private int errorCode;

    @Size(max = 2)
    private String errorMsg;

    @NotNull
    @Size(max = 10)
    private String tradingDay;

    @Size(max = 64)
    private String ipMac;

    @Size(max = 64)
    private String remark;

    private Date createTime;

    private Date updateTime;

    private String ps; //投管ps

    private int count;

    private String isFullPrice;//  Y:是 ， N：否

    private String offsetFlag;

    private String hedgeFlag;

    public Order() {
        this.orderPrice = new BigDecimal("0.00");
        this.orderBalance = new BigDecimal("0.00");
        this.entrustPrice = new BigDecimal("0.00");
        this.fullPrice = new BigDecimal("0.00");
        this.entrustBalance = new BigDecimal("0.00");
        this.frozenBalance = new BigDecimal("0.00");
        this.tradedBalance = new BigDecimal("0.00");
        this.tradedPrice = new BigDecimal("0.00");
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getFinanceAccount() {
        return financeAccount;
    }

    public void setFinanceAccount(String financeAccount) {
        this.financeAccount = financeAccount == null ? null : financeAccount.trim();
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
    }

    public String getAlgorithmId() {
        return algorithmId;
    }

    public void setAlgorithmId(String algorithmId) {
        this.algorithmId = algorithmId == null ? null : algorithmId.trim();
    }

    public String getRelativeOrderId() {
        return relativeOrderId;
    }

    public void setRelativeOrderId(String relativeOrderId) {
        this.relativeOrderId = relativeOrderId == null ? null : relativeOrderId.trim();
    }

    public String getBusinessFlag() {
        return businessFlag;
    }

    public void setBusinessFlag(String businessFlag) {
        this.businessFlag = businessFlag == null ? null : businessFlag.trim();
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

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public long getOrderVolume() {
        return orderVolume;
    }

    public void setOrderVolume(long orderVolume) {
        this.orderVolume = orderVolume;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getOrderBalance() {
        return orderBalance;
    }

    public void setOrderBalance(BigDecimal orderBalance) {
        this.orderBalance = orderBalance;
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

    @Override
    public String getIsFullPrice() {
        return isFullPrice;
    }

    public void setIsFullPrice(String isFullPrice) {
        this.isFullPrice = isFullPrice;
    }

    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(BigDecimal fullPrice) {
        this.fullPrice = fullPrice;
    }

    public BigDecimal getEntrustBalance() {
        return entrustBalance;
    }

    public void setEntrustBalance(BigDecimal entrustBalance) {
        this.entrustBalance = entrustBalance;
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

    public static Order getTemplateOrder() {
        return templateOrder;
    }

    public static void setTemplateOrder(Order templateOrder) {
        Order.templateOrder = templateOrder;
    }

    private static Order templateOrder = new Order();

    public static Order createOrder(OrderPlaceBean orderPlaceBean) {
        try {
            Order newOrder = (Order) templateOrder.clone();
            newOrder.setInstructionId(orderPlaceBean.getInstructionId());
            newOrder.setPortfolioId(orderPlaceBean.getPortfolioId());
            newOrder.setUserId(orderPlaceBean.getUserId());
            newOrder.setUsername(orderPlaceBean.getUsername());
            newOrder.setAccountId(orderPlaceBean.getAccountId());
            newOrder.setAlgorithmId(orderPlaceBean.getAlgorithmId());
            newOrder.setEntrustProp(orderPlaceBean.getEntrustProp());
            newOrder.setEntrustBs(orderPlaceBean.getEntrustBs());
            newOrder.setStockCode(orderPlaceBean.getStockCode());
            newOrder.setOrderVolume(orderPlaceBean.getEntrustVolume());
            newOrder.setOrderPrice(orderPlaceBean.getEntrustPrice());
            newOrder.setOffsetFlag(orderPlaceBean.getOffsetFlag());
            newOrder.setHedgeFlag(orderPlaceBean.getHedgeFlag());
            newOrder.setFullPrice(orderPlaceBean.getFullPrice() == null ? new BigDecimal("0.00") : orderPlaceBean.getFullPrice());
            return newOrder;
        } catch (Exception e) {
        }
        return new Order();
    }

    public Order cloneOrder() {
        try {
            return (Order) this.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}