package com.joyintech.service.trade.bond.dto;

import java.math.BigDecimal;

/**
 * 债券交易导出pdf
 * @author zengchengfa
 * @version 1.0
 * @date 2021-08-03
 */
public class TrdBondInvestTradePdfDto {

    /**
     * 资产代码
     */
    private String finprodMarketId;
    /**
     * 成交编号
     */
    private String totleTradeId;
    /**
     * 组合代码
     */
    private String portfolioId;
    /**
     * 资产简称
     */
    private String finprodAbbr;
    /**
     * 交易方向
     */
    private String psStr;
    /**
     * 本方
     */
    private String bf;
    /**
     * 交易对手
     */
    private String counterName;
    /**
     * 对手方交易员
     */
    private String counterTrader;
    /**
     * 结算日
     */
    private String settleDate;
    /**
     * 结算方式
     */
    private String deliveryType;
    /**
     * 券面
     */
    private BigDecimal shareAmt;
    /**
     * 到期收益率
     */
    private BigDecimal yield;
    /**
     * 行权收益率
     */
    private BigDecimal exerYield;
    /**
     * 利息总额
     */
    private BigDecimal intAmt;
    /**
     * 单位净价
     */
    private BigDecimal unitCprice;
    /**
     * 全价总额
     */
    private BigDecimal fpriceAmt;
    /**
     * 交易金额
     */
    private BigDecimal tradeAmt;
    /**
     * 清算速度
     */
    private String sttleSpeed;
    /**
     * 清算类型
     */
    private String clearType;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 资金账号
     */
    private String busiAcctNo;

    /**
     * 交易日期
     */
    private String tradeDate;

    /**
     * 资产类型
     */
    private String secType;

    /**
     * 委托状态
     */
    private String entrustStatus;

    /**
     * 交易平台
     */
    private String tradePlat;

    /**
     * 业务类型
     */
    private String entrustType;

    /**
     * 价格类型
     */
    private String priceModel;

    /**
     * 委托价格
     */
    private BigDecimal price;

    /**
     * 委托数量
     */
    private BigDecimal entrustNum;

    /**
     * 委托金额
     */
    private BigDecimal entrustAmt;

    /**
     * 成交进度
     */
    private String transactioncom;

    /**
     * 全价
     */
    private BigDecimal unitFprice;

    /**
     * 下达人姓名
     */
    private String traderName;

    /**
     * 指令下达人姓名
     */
    private String invManagerName;

    /**
     * 对手席号
     */
    private String counterSeat;

    /**
     * 约定号
     */
    private String counterConNo;

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getTotleTradeId() {
        return totleTradeId;
    }

    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getPsStr() {
        return psStr;
    }

    public void setPsStr(String psStr) {
        this.psStr = psStr;
    }

    public String getBf() {
        return bf;
    }

    public void setBf(String bf) {
        this.bf = bf;
    }

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    public String getCounterTrader() {
        return counterTrader;
    }

    public void setCounterTrader(String counterTrader) {
        this.counterTrader = counterTrader;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    public BigDecimal getYield() {
        return yield;
    }

    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    public BigDecimal getExerYield() {
        return exerYield;
    }

    public void setExerYield(BigDecimal exerYield) {
        this.exerYield = exerYield;
    }

    public BigDecimal getIntAmt() {
        return intAmt;
    }

    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    public BigDecimal getUnitCprice() {
        return unitCprice;
    }

    public void setUnitCprice(BigDecimal unitCprice) {
        this.unitCprice = unitCprice;
    }

    public BigDecimal getFpriceAmt() {
        return fpriceAmt;
    }

    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }

    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public String getSttleSpeed() {
        return sttleSpeed;
    }

    public void setSttleSpeed(String sttleSpeed) {
        this.sttleSpeed = sttleSpeed;
    }

    public String getClearType() {
        return clearType;
    }

    public void setClearType(String clearType) {
        this.clearType = clearType;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getBusiAcctNo() {
        return busiAcctNo;
    }

    public void setBusiAcctNo(String busiAcctNo) {
        this.busiAcctNo = busiAcctNo;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getSecType() {
        return secType;
    }

    public void setSecType(String secType) {
        this.secType = secType;
    }

    public String getEntrustStatus() {
        return entrustStatus;
    }

    public void setEntrustStatus(String entrustStatus) {
        this.entrustStatus = entrustStatus;
    }

    public String getTradePlat() {
        return tradePlat;
    }

    public void setTradePlat(String tradePlat) {
        this.tradePlat = tradePlat;
    }

    public String getEntrustType() {
        return entrustType;
    }

    public void setEntrustType(String entrustType) {
        this.entrustType = entrustType;
    }

    public String getPriceModel() {
        return priceModel;
    }

    public void setPriceModel(String priceModel) {
        this.priceModel = priceModel;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getEntrustNum() {
        return entrustNum;
    }

    public void setEntrustNum(BigDecimal entrustNum) {
        this.entrustNum = entrustNum;
    }

    public BigDecimal getEntrustAmt() {
        return entrustAmt;
    }

    public void setEntrustAmt(BigDecimal entrustAmt) {
        this.entrustAmt = entrustAmt;
    }

    public String getTransactioncom() {
        return transactioncom;
    }

    public void setTransactioncom(String transactioncom) {
        this.transactioncom = transactioncom;
    }

    public BigDecimal getUnitFprice() {
        return unitFprice;
    }

    public void setUnitFprice(BigDecimal unitFprice) {
        this.unitFprice = unitFprice;
    }

    public String getTraderName() {
        return traderName;
    }

    public void setTraderName(String traderName) {
        this.traderName = traderName;
    }

    public String getInvManagerName() {
        return invManagerName;
    }

    public void setInvManagerName(String invManagerName) {
        this.invManagerName = invManagerName;
    }

    public String getCounterSeat() {
        return counterSeat;
    }

    public void setCounterSeat(String counterSeat) {
        this.counterSeat = counterSeat;
    }

    public String getCounterConNo() {
        return counterConNo;
    }

    public void setCounterConNo(String counterConNo) {
        this.counterConNo = counterConNo;
    }
}
