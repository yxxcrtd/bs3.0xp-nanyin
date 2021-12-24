package com.joyintech.tams.exchange.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "历史成交信息", description = "历史成交信息")
public class HistoryTrade implements Serializable {
    
    private static final long serialVersionUID = 7037506087690811868L;
    /**
     * 定位串
     */
    @ApiModelProperty(value = "定位串")
    private String postStr;

    @ApiModelProperty(value = "网点号:资金账户编号")
    private String accountId;
    /**
     * 清算日
     */
    @ApiModelProperty(value = "清算日")
    private String clearDay;
    /**
     * 资金发生数
     */
    @ApiModelProperty(value = "资金发生数")
    private double financeAmount;

    @ApiModelProperty(value = "交易日期")
    private String tradingDay;

    @ApiModelProperty(value = "交易时间")
    private String tradeTime;

    /**
     * 用户流水号--委托序号
     */
    @ApiModelProperty(value = "用户流水号--委托序号")
    private String serialNo;
    /**
     * 资金账户
     */
    @ApiModelProperty(value = "资金账户")
    private String financeAccount;
    /**
     * 财富账户(保留项)
     */
    @ApiModelProperty(value = "财富账户(保留项)")
    private String fortuneAccount;

    /**
     * 机构编码(保留项)
     */
    @ApiModelProperty(value = "机构编码(保留项)")
    private String orgId;

    /**
     * 产品编码(保留项)
     */
    @ApiModelProperty(value = "产品编码(保留项)")
    private String portfolioId;
    /**
     * 股东代码
     */
    @ApiModelProperty(value = "股东代码")
    private String stockHolderId;
    /**
     * 交易行为
     */
    @ApiModelProperty(value = "交易行为")
    private String bsFlag;
    @ApiModelProperty(value = "交易场所")
    private String market;
    /**
     * 申报合同序号
     */
    @ApiModelProperty(value = "申报合同序号")
    private String orderId;

    @ApiModelProperty(value = "证券编号")
    private String stockCode;
    @ApiModelProperty(value = "证券名称")
    private String stockName;
    @ApiModelProperty(value = "证券类型")
    private String stockType;
    @ApiModelProperty(value = "委托价格")
    private double entrustPrice;
    @ApiModelProperty(value = "委托数量")
    private long entrustVolume;
    @ApiModelProperty(value = "成交价格")
    private double tradePrice;
    @ApiModelProperty(value = "成交数量")
    private long tradeVolume;

    @ApiModelProperty(value = "已成交金额")
    private double tradedBalance;
    /**
     * 成交编号
     */
    @ApiModelProperty(value = "成交编号")
    private String brokerTradeId;
    /**
     * 股份余额
     */
    @ApiModelProperty(value = "股份余额")
    private long stockResidue;
    /**
     * 前台费用
     */
    @ApiModelProperty(value = "前台费用")
    private double frontCost;
    /**
     * 手续费
     */
    @ApiModelProperty(value = "手续费")
    private double procedureCost;
    /**
     * 印花税
     */
    @ApiModelProperty(value = "印花税")
    private double printingCost;
    /**
     * 证管费
     */
    @ApiModelProperty(value = "证管费")
    private double secCost;
    /**
     * 经手费
     */
    @ApiModelProperty(value = "经手费")
    private double handleCost;
    /**
     * 过户费
     */
    @ApiModelProperty(value = "过户费")
    private double transferCost;
    /**
     * 清算费
     */
    @ApiModelProperty(value = "清算费")
    private double clearCost;
    /**
     * 其他费
     */
    @ApiModelProperty(value = "其他费")
    private double otherCost;
    /**
     * 交易规费
     */
    @ApiModelProperty(value = "交易规费")
    private double tradeRuleCost;
    /**
     * 使用金额(保留)
     */
    @ApiModelProperty(value = "使用金额(保留)")
    private double useMoney;
    /**
     * 债券应计利息（保留）
     */
    @ApiModelProperty(value = "债券应计利息（保留）")
    private double bondInterest;

    /**
     * 净手续费
     *
     * @return
     */
    @ApiModelProperty(value = "净手续费")
    private double jinShouXuFei;

    public String getPostStr() {
        return postStr;
    }

    public void setPostStr(String postStr) {
        this.postStr = postStr;
    }

    public double getJinShouXuFei() {
        return jinShouXuFei;
    }

    public void setJinShouXuFei(double jinShouXuFei) {
        this.jinShouXuFei = jinShouXuFei;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getClearDay() {
        return clearDay;
    }

    public void setClearDay(String clearDay) {
        this.clearDay = clearDay;
    }

    public double getFinanceAmount() {
        return financeAmount;
    }

    public void setFinanceAmount(double financeAmount) {
        this.financeAmount = financeAmount;
    }

    public String getTradingDay() {
        return tradingDay;
    }

    public void setTradingDay(String tradingDay) {
        this.tradingDay = tradingDay;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getFinanceAccount() {
        return financeAccount;
    }

    public void setFinanceAccount(String financeAccount) {
        this.financeAccount = financeAccount;
    }

    public String getFortuneAccount() {
        return fortuneAccount;
    }

    public void setFortuneAccount(String fortuneAccount) {
        this.fortuneAccount = fortuneAccount;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getStockHolderId() {
        return stockHolderId;
    }

    public void setStockHolderId(String stockHolderId) {
        this.stockHolderId = stockHolderId;
    }

    public String getBsFlag() {
        return bsFlag;
    }

    public void setBsFlag(String bsFlag) {
        this.bsFlag = bsFlag;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public double getEntrustPrice() {
        return entrustPrice;
    }

    public void setEntrustPrice(double entrustPrice) {
        this.entrustPrice = entrustPrice;
    }

    public long getEntrustVolume() {
        return entrustVolume;
    }

    public void setEntrustVolume(long entrustVolume) {
        this.entrustVolume = entrustVolume;
    }

    public double getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(double tradePrice) {
        this.tradePrice = tradePrice;
    }

    public long getTradeVolume() {
        return tradeVolume;
    }

    public void setTradeVolume(long tradeVolume) {
        this.tradeVolume = tradeVolume;
    }

    public double getTradedBalance() {
        return tradedBalance;
    }

    public void setTradedBalance(double tradedBalance) {
        this.tradedBalance = tradedBalance;
    }

    public String getBrokerTradeId() {
        return brokerTradeId;
    }

    public void setBrokerTradeId(String brokerTradeId) {
        this.brokerTradeId = brokerTradeId;
    }

    public long getStockResidue() {
        return stockResidue;
    }

    public void setStockResidue(long stockResidue) {
        this.stockResidue = stockResidue;
    }

    public double getFrontCost() {
        return frontCost;
    }

    public void setFrontCost(double frontCost) {
        this.frontCost = frontCost;
    }

    public double getProcedureCost() {
        return procedureCost;
    }

    public void setProcedureCost(double procedureCost) {
        this.procedureCost = procedureCost;
    }

    public double getPrintingCost() {
        return printingCost;
    }

    public void setPrintingCost(double printingCost) {
        this.printingCost = printingCost;
    }

    public double getSecCost() {
        return secCost;
    }

    public void setSecCost(double secCost) {
        this.secCost = secCost;
    }

    public double getHandleCost() {
        return handleCost;
    }

    public void setHandleCost(double handleCost) {
        this.handleCost = handleCost;
    }

    public double getTransferCost() {
        return transferCost;
    }

    public void setTransferCost(double transferCost) {
        this.transferCost = transferCost;
    }

    public double getClearCost() {
        return clearCost;
    }

    public void setClearCost(double clearCost) {
        this.clearCost = clearCost;
    }

    public double getOtherCost() {
        return otherCost;
    }

    public void setOtherCost(double otherCost) {
        this.otherCost = otherCost;
    }

    public double getTradeRuleCost() {
        return tradeRuleCost;
    }

    public void setTradeRuleCost(double tradeRuleCost) {
        this.tradeRuleCost = tradeRuleCost;
    }

    public double getUseMoney() {
        return useMoney;
    }

    public void setUseMoney(double useMoney) {
        this.useMoney = useMoney;
    }

    public double getBondInterest() {
        return bondInterest;
    }

    public void setBondInterest(double bondInterest) {
        this.bondInterest = bondInterest;
    }
}
