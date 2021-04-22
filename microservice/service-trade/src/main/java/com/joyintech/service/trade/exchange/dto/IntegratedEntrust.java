package com.joyintech.service.trade.exchange.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class IntegratedEntrust extends BaseEntity implements EntrustInterface {

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

    private BigDecimal entrustPrice;

    private BigDecimal fullPrice;

    private BigDecimal entrustBalance;

    private BigDecimal discount;

    private String transCode;//借用字段 质权人类型 协议回购到期续作指令必填 0-非第三方 1-第三方

    private BigDecimal expireYearRate;

    private long pretermYearRate;

    private String liaisonName;

    private String liaisonTel;

    private String propSeatNo;

    private String sellerNo;

    private String propSeatName;

    private String contractId;

    private String firstSettleDate;

    private String backDate;

    private int compactTerm;

    private BigDecimal backBalance;

    private String origSerialNo;

    private String reductionType;

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

    public IntegratedEntrust() {
        this.fullPrice = new BigDecimal("0.00");
        this.entrustPrice = new BigDecimal("0.00");
        this.entrustBalance = new BigDecimal("0.00");
        this.discount = new BigDecimal("0.00");
        this.expireYearRate = new BigDecimal("0.00");
        this.backBalance = new BigDecimal("0.00");
        this.frozenBalance = new BigDecimal("0.00");
        this.tradedBalance = new BigDecimal("0.00");
        this.tradedPrice = new BigDecimal("0.00");
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

    public BigDecimal getEntrustBalance() {
        return entrustBalance;
    }

    public void setEntrustBalance(BigDecimal entrustBalance) {
        this.entrustBalance = entrustBalance;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode == null ? null : transCode.trim();
    }

    public BigDecimal getExpireYearRate() {
        return expireYearRate;
    }

    public void setExpireYearRate(BigDecimal expireYearRate) {
        this.expireYearRate = expireYearRate;
    }

    public long getPretermYearRate() {
        return pretermYearRate;
    }

    public void setPretermYearRate(long pretermYearRate) {
        this.pretermYearRate = pretermYearRate;
    }

    public String getLiaisonName() {
        return liaisonName;
    }

    public void setLiaisonName(String liaisonName) {
        this.liaisonName = liaisonName == null ? null : liaisonName.trim();
    }

    public String getLiaisonTel() {
        return liaisonTel;
    }

    public void setLiaisonTel(String liaisonTel) {
        this.liaisonTel = liaisonTel == null ? null : liaisonTel.trim();
    }

    public String getPropSeatNo() {
        return propSeatNo;
    }

    public void setPropSeatNo(String propSeatNo) {
        this.propSeatNo = propSeatNo == null ? null : propSeatNo.trim();
    }

    public String getSellerNo() {
        return sellerNo;
    }

    public void setSellerNo(String sellerNo) {
        this.sellerNo = sellerNo == null ? null : sellerNo.trim();
    }

    public String getPropSeatName() {
        return propSeatName;
    }

    public void setPropSeatName(String propSeatName) {
        this.propSeatName = propSeatName == null ? null : propSeatName.trim();
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId == null ? null : contractId.trim();
    }

    public String getFirstSettleDate() {
        return firstSettleDate;
    }

    public void setFirstSettleDate(String firstSettleDate) {
        this.firstSettleDate = firstSettleDate == null ? null : firstSettleDate.trim();
    }

    public String getBackDate() {
        return backDate;
    }

    public void setBackDate(String backDate) {
        this.backDate = backDate == null ? null : backDate.trim();
    }

    public int getCompactTerm() {
        return compactTerm;
    }

    public void setCompactTerm(int compactTerm) {
        this.compactTerm = compactTerm;
    }

    public BigDecimal getBackBalance() {
        return backBalance;
    }

    public void setBackBalance(BigDecimal backBalance) {
        this.backBalance = backBalance;
    }

    public String getOrigSerialNo() {
        return origSerialNo;
    }

    public void setOrigSerialNo(String origSerialNo) {
        this.origSerialNo = origSerialNo == null ? null : origSerialNo.trim();
    }

    public String getReductionType() {
        return reductionType;
    }

    public void setReductionType(String reductionType) {
        this.reductionType = reductionType == null ? null : reductionType.trim();
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
    @Override
    public String getOperateUser() {
        return operateUser;
    }
    @Override
    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser;
    }
    @Override
    public String getOffsetFlag() {
        return null;
    }
    @Override
    public void setOffsetFlag(String offsetFlag) {

    }
    @Override
    public String getHedgeFlag() {
        return null;
    }
    @Override
    public void setHedgeFlag(String hedgeFlag) {

    }
}