package com.joyintech.service.trade.exchange.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class Entrust extends BaseEntity implements EntrustInterface {

    private static Entrust templateEntrust = new Entrust();
    private String id;
    private String positionStr;
    private String parentOrderId;
    private String instructionId;
    private String portfolioId;
    private String userId;
    private String username;
    private String accountId;
    private String financeAccount;
    private String accountName;
    private String assetType;
    private String market;
    private String stockCode;
    private String stockName;
    private String entrustType;
    private String entrustProp;
    private String entrustBs;
    private String bsFlag;
    private long entrustVolume;
    private BigDecimal entrustPrice;//净价价格
    private BigDecimal fullPrice;//全价价格
    private BigDecimal expireYearRate;//到期收益率
    private BigDecimal frozenBalance;
    private long frozenVolume;
    private BigDecimal tradedBalance;
    private long tradedVolume;
    private BigDecimal tradedPrice;
    private long cancelVolume;
    private String reportTime;
    private String tradeTime;
    private String cancelTime;
    private int errorCode;
    private String errorMsg;
    private String tradingDay;
    private String localEntrustId;
    private String brokerEntrustId;
    private String ipMac;
    private String remark;
    private Date createTime;
    private Date updateTime;
    private int entrustStatus;
    private String origEntrustId;
    private int count;
    private String isFullPrice;//Y:是 ， N：否
    private String reportNo;//柜台报盘编号
    private boolean noCancelEntrust = false;
    /**
     * 操作人员ID 执行撤销异常委托操作人员ID
     */
    private String operateUser;
    private String offsetFlag;
    private String hedgeFlag;
    private BigDecimal entrustBalance;

    public Entrust() {
        this.entrustPrice = new BigDecimal("0.00");
        this.fullPrice = new BigDecimal("0.00");
        this.expireYearRate = new BigDecimal("0.00");
        this.frozenBalance = new BigDecimal("0.00");
        this.tradedBalance = new BigDecimal("0.00");
        this.tradedPrice = new BigDecimal("0.00");
        this.entrustBalance = new BigDecimal("0.00");
    }

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
    @Override
    public boolean isNoCancelEntrust() {
        return noCancelEntrust;
    }
    @Override
    public void setNoCancelEntrust(boolean noCancelEntrust) {
        this.noCancelEntrust = noCancelEntrust;
    }

    @Override
    public String getId() {
        return id;
    }
    @Override
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
    @Override
    public String getParentOrderId() {
        return parentOrderId;
    }
    @Override
    public void setParentOrderId(String parentOrderId) {
        this.parentOrderId = parentOrderId == null ? null : parentOrderId.trim();
    }
    @Override
    public String getInstructionId() {
        return instructionId;
    }
    @Override
    public void setInstructionId(String instructionId) {
        this.instructionId = instructionId == null ? null : instructionId.trim();
    }
    @Override
    public String getPortfolioId() {
        return portfolioId;
    }
    @Override
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }
    @Override
    public String getUserId() {
        return userId;
    }
    @Override
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }
    @Override
    public String getAccountId() {
        return accountId;
    }
    @Override
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }
    @Override
    public String getFinanceAccount() {
        return financeAccount;
    }
    @Override
    public void setFinanceAccount(String financeAccount) {
        this.financeAccount = financeAccount == null ? null : financeAccount.trim();
    }
    @Override
    public String getAccountName() {
        return accountName;
    }
    @Override
    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }
    @Override
    public String getAssetType() {
        return assetType;
    }
    @Override
    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
    }
    @Override
    public String getMarket() {
        return market;
    }
    @Override
    public void setMarket(String market) {
        this.market = market == null ? null : market.trim();
    }
    @Override
    public String getStockCode() {
        return stockCode;
    }
    @Override
    public void setStockCode(String stockCode) {
        this.stockCode = stockCode == null ? null : stockCode.trim();
    }
    @Override
    public String getStockName() {
        return stockName;
    }
    @Override
    public void setStockName(String stockName) {
        this.stockName = stockName == null ? null : stockName.trim();
    }
    @Override
    public String getEntrustType() {
        return entrustType;
    }
    @Override
    public void setEntrustType(String entrustType) {
        this.entrustType = entrustType == null ? null : entrustType.trim();
    }
    @Override
    public String getEntrustProp() {
        return entrustProp;
    }
    @Override
    public void setEntrustProp(String entrustProp) {
        this.entrustProp = entrustProp == null ? null : entrustProp.trim();
    }
    @Override
    public String getEntrustBs() {
        return entrustBs;
    }
    @Override
    public void setEntrustBs(String entrustBs) {
        this.entrustBs = entrustBs == null ? null : entrustBs.trim();
    }
    @Override
    public String getBsFlag() {
        return bsFlag;
    }
    @Override
    public void setBsFlag(String bsFlag) {
        this.bsFlag = bsFlag == null ? null : bsFlag.trim();
    }
    @Override
    public long getEntrustVolume() {
        return entrustVolume;
    }
    @Override
    public void setEntrustVolume(long entrustVolume) {
        this.entrustVolume = entrustVolume;
    }
    @Override
    public BigDecimal getEntrustPrice() {
        return entrustPrice;
    }
    @Override
    public void setEntrustPrice(BigDecimal entrustPrice) {
        this.entrustPrice = entrustPrice;
    }
    @Override
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
    @Override
    public BigDecimal getFrozenBalance() {
        return frozenBalance;
    }
    @Override
    public void setFrozenBalance(BigDecimal frozenBalance) {
        this.frozenBalance = frozenBalance;
    }
    @Override
    public long getFrozenVolume() {
        return frozenVolume;
    }
    @Override
    public void setFrozenVolume(long frozenVolume) {
        this.frozenVolume = frozenVolume;
    }
    @Override
    public BigDecimal getTradedBalance() {
        return tradedBalance;
    }
    @Override
    public void setTradedBalance(BigDecimal tradedBalance) {
        this.tradedBalance = tradedBalance;
    }
    @Override
    public long getTradedVolume() {
        return tradedVolume;
    }
    @Override
    public void setTradedVolume(long tradedVolume) {
        this.tradedVolume = tradedVolume;
    }
    @Override
    public BigDecimal getTradedPrice() {
        return tradedPrice;
    }
    @Override
    public void setTradedPrice(BigDecimal tradedPrice) {
        this.tradedPrice = tradedPrice;
    }
    @Override
    public long getCancelVolume() {
        return cancelVolume;
    }
    @Override
    public void setCancelVolume(long cancelVolume) {
        this.cancelVolume = cancelVolume;
    }
    @Override
    public String getReportTime() {
        return reportTime;
    }
    @Override
    public void setReportTime(String reportTime) {
        this.reportTime = reportTime == null ? null : reportTime.trim();
    }
    @Override
    public String getTradeTime() {
        return tradeTime;
    }
    @Override
    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime == null ? null : tradeTime.trim();
    }
    @Override
    public String getCancelTime() {
        return cancelTime;
    }
    @Override
    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime == null ? null : cancelTime.trim();
    }
    @Override
    public int getErrorCode() {
        return errorCode;
    }
    @Override
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
    @Override
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg == null ? null : errorMsg.trim();
    }
    @Override
    public String getTradingDay() {
        return tradingDay;
    }
    @Override
    public void setTradingDay(String tradingDay) {
        this.tradingDay = tradingDay == null ? null : tradingDay.trim();
    }
    @Override
    public String getLocalEntrustId() {
        return localEntrustId;
    }
    @Override
    public void setLocalEntrustId(String localEntrustId) {
        this.localEntrustId = localEntrustId == null ? null : localEntrustId.trim();
    }
    @Override
    public String getBrokerEntrustId() {
        return brokerEntrustId;
    }
    @Override
    public void setBrokerEntrustId(String brokerEntrustId) {
        this.brokerEntrustId = brokerEntrustId == null ? null : brokerEntrustId.trim();
    }
    @Override
    public String getIpMac() {
        return ipMac;
    }
    @Override
    public void setIpMac(String ipMac) {
        this.ipMac = ipMac == null ? null : ipMac.trim();
    }
    @Override
    public String getRemark() {
        return remark;
    }
    @Override
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
    @Override
    public int getEntrustStatus() {
        return entrustStatus;
    }
    @Override
    public void setEntrustStatus(int entrustStatus) {
        this.entrustStatus = entrustStatus;
    }
    @Override
    public String getOrigEntrustId() {
        return origEntrustId;
    }
    @Override
    public void setOrigEntrustId(String origEntrustId) {
        this.origEntrustId = origEntrustId == null ? null : origEntrustId.trim();
    }

    public String getPositionStr() {
        return positionStr;
    }

    public void setPositionStr(String positionStr) {
        this.positionStr = positionStr;
    }
    @Override
    public String getReportNo() {
        return reportNo;
    }
    @Override
    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    @Override
    public String getOffsetFlag() {
        return offsetFlag;
    }
    @Override
    public void setOffsetFlag(String offsetFlag) {
        this.offsetFlag = offsetFlag;
    }
    @Override
    public String getHedgeFlag() {
        return hedgeFlag;
    }
    @Override
    public void setHedgeFlag(String hedgeFlag) {
        this.hedgeFlag = hedgeFlag;
    }

    public String getIsFullPrice() {
        return isFullPrice;
    }

    public void setIsFullPrice(String isFullPrice) {
        this.isFullPrice = isFullPrice;
    }

    public Entrust cloneEntrust() {
        try {
            return (Entrust) this.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
    @Override
    public String getOperateUser() {
        return operateUser;
    }
    @Override
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