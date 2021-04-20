package com.joyintech.service.bankuplink.common.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zhangnian
 * @ClassName CfetsQuotedTradeCommonDto
 * @description TODO
 * @date 2020/12/31 0031 11:10
 **/
public class CfetsQuotedTradeCommonDto extends BaseEntity {

    /**
     * 是否跨托管
     */
    private String isKtg;

    private List<SecurityEntity> seList;

    /**
     * 抵押品信息
     */
    private List<NoUnderlyings> noUnderlyings;

    /**
     * 收益率
    */
    private List<NoStipulations> noStipulations;

    private List<NoPartyID>  noPartyIDs;

    /**
     * 跨托管信息
     */
    private List<NoCrossCustodians> noCrossCustodians;

    /**
     * 报文类型
     */
    private String msgType;

    /**
     * 报文序列号
     */
    private String msgSeqNum;

    /**
     * 发送时间
     */
    private String sendingTime;

    /**
     * 报文最初发送方
     */
    private String onBehalfOfCompID;

    /**
     * 业务发生时间
     */
    private String transactTime;

    /**
     * 成交状态
     */
    private String execType;

    /**
     * 应计利息总额（精度：2位）
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal accruedInterestTotalAmt;
    /**
     * 补充条款标识（报文为N时登录false，为Y时登录true）
     */
    private String remarkIndicator;

    /**
     * 数据类型标识
     */
    private String dataCategoryIndicator;


    /**
     *客户端参与编号
    */
    private String clOrdID;

    /**
     *成交编号
    */
    private String execID;

    /**
     * 请求报价编号
     */
    private String reference;

    /**
     *结算币种
    */
    private String settlCurrency;

    /**
     * 订单编号
     */
    private String orderID;

    /**
     * 券面总额合计
    */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal lastQty;

    /**
     * 回购方式
    */
    private String repoMethod;

    /**
     * 回购利率
    */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal price;

    /**
     *交易品种  债券代码
    */
    private String securityID;

    /**
     * 债券名称
     */
    private String symbol;

    /**
     * 交易方向
    */
    private String  side;

    /**
     * 补充条款
    */
    private String text;


    /**
     * 首次结算日
    */
    private String settlDate;

    /**
     *成交日期
    */
    private String tradeDate;

    /**
     *报价编号
    */
    private String quoteID;


    /**
     * 应计利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal accruedInterestAmt;


    /**
     * 到期结算日
    */
    private String settlDate2;

    /**
     * 首次结算方式
    */
    private String deliveryType;


    /**
     * 实际占款天数
    */
    private Integer cashHoldingDays;

    /**
     * 到期结算方式
    */
    private String  deliveryType2;

    /**
     * 清算方式
    */
    private String clearingMethod;

    /**
     * 续作标识
    */
    private String sequelIndicator;

    /**
     * 原交易编号
    */
    private String origTradeID;

    /**
     * 应计标识
    */
    private String contingencyIndicator;

    /**
     *  全价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dirtyPrice;

    /**
     * 报文动作
    */
    private String dealTransType;

    /**
     * 市场标识
    */
    private String marketIndicator;

    /**
     * 待偿期
     */
    private String termToMaturity;


    /**
     * 是否现券
     */
    private String cashBondIndicator;

    /**
     * 结算金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal settlCurrAmt;

    /**
     * 到期结算金额
    */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal settlCurrAmt2;

    /**
     * 交易金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tradeCashAmt;

    /**
     * 回购期限
     */
    private Integer tradeLimitDays;

    /**
     * 交易方式
    */
    private String tradeMethod;

    /*
     * 成交时间
    */
    private String tradeTime;

    /*
     * 成交类别
     */
    private String tradeType;

    /**
     * 清算速度
     */
    private String settlType;


    /**
     * 债券代码
     */
    private String underlyingSecurityID;

    /**
     * 债券名称
     */
    private String underlyingSymbol;

    /**
     * 券面总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal underlyingQty;

    private String noUndrlyingStips;

    /**
     * 折算比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal underlyingStipType;

    /**
     * 精度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal underlyingStipValue;


    /**
     * ABS???????
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal principal;

    /**
     * ABS??????
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totalPrincipal;


    public List<NoUnderlyings> getNoUnderlyings() {
        return noUnderlyings;
    }

    public void setNoUnderlyings(List<NoUnderlyings> noUnderlyings) {
        this.noUnderlyings = noUnderlyings;
    }

    public List<SecurityEntity> getSeList() {
        return seList;
    }

    public void setSeList(List<SecurityEntity> seList) {
        this.seList = seList;
    }

    public String getClOrdID() {
        return clOrdID;
    }

    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

    public String getSettlCurrency() {
        return settlCurrency;
    }

    public void setSettlCurrency(String settlCurrency) {
        this.settlCurrency = settlCurrency;
    }

    public BigDecimal getLastQty() {
        return lastQty;
    }

    public void setLastQty(BigDecimal lastQty) {
        this.lastQty = lastQty;
    }

    public String getRepoMethod() {
        return repoMethod;
    }

    public void setRepoMethod(String repoMethod) {
        this.repoMethod = repoMethod;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSecurityID() {
        return securityID;
    }

    public void setSecurityID(String securityID) {
        this.securityID = securityID;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTransactTime() {
        return transactTime;
    }

    public void setTransactTime(String transactTime) {
        this.transactTime = transactTime;
    }

    public String getQuoteID() {
        return quoteID;
    }

    public void setQuoteID(String quoteID) {
        this.quoteID = quoteID;
    }

    public String getExecType() {
        return execType;
    }

    public void setExecType(String execType) {
        this.execType = execType;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public BigDecimal getAccruedInterestTotalAmt() {
        return accruedInterestTotalAmt;
    }

    public void setAccruedInterestTotalAmt(BigDecimal accruedInterestTotalAmt) {
        this.accruedInterestTotalAmt = accruedInterestTotalAmt;
    }

    public Integer getCashHoldingDays() {
        return cashHoldingDays;
    }

    public void setCashHoldingDays(Integer cashHoldingDays) {
        this.cashHoldingDays = cashHoldingDays;
    }

    public String getDeliveryType2() {
        return deliveryType2;
    }

    public void setDeliveryType2(String deliveryType2) {
        this.deliveryType2 = deliveryType2;
    }

    public String getClearingMethod() {
        return clearingMethod;
    }

    public void setClearingMethod(String clearingMethod) {
        this.clearingMethod = clearingMethod;
    }

    public String getSequelIndicator() {
        return sequelIndicator;
    }

    public void setSequelIndicator(String sequelIndicator) {
        this.sequelIndicator = sequelIndicator;
    }

    public String getOrigTradeID() {
        return origTradeID;
    }

    public void setOrigTradeID(String origTradeID) {
        this.origTradeID = origTradeID;
    }

    public String getContingencyIndicator() {
        return contingencyIndicator;
    }

    public void setContingencyIndicator(String contingencyIndicator) {
        this.contingencyIndicator = contingencyIndicator;
    }

    public String getDealTransType() {
        return dealTransType;
    }

    public void setDealTransType(String dealTransType) {
        this.dealTransType = dealTransType;
    }

    public String getMarketIndicator() {
        return marketIndicator;
    }

    public void setMarketIndicator(String marketIndicator) {
        this.marketIndicator = marketIndicator;
    }

    public String getRemarkIndicator() {
        return remarkIndicator;
    }

    public void setRemarkIndicator(String remarkIndicator) {
        this.remarkIndicator = remarkIndicator;
    }

    public BigDecimal getSettlCurrAmt2() {
        return settlCurrAmt2;
    }

    public void setSettlCurrAmt2(BigDecimal settlCurrAmt2) {
        this.settlCurrAmt2 = settlCurrAmt2;
    }

    public BigDecimal getTradeCashAmt() {
        return tradeCashAmt;
    }

    public void setTradeCashAmt(BigDecimal tradeCashAmt) {
        this.tradeCashAmt = tradeCashAmt;
    }

    public Integer getTradeLimitDays() {
        return tradeLimitDays;
    }

    public void setTradeLimitDays(Integer tradeLimitDays) {
        this.tradeLimitDays = tradeLimitDays;
    }

    public String getTradeMethod() {
        return tradeMethod;
    }

    public void setTradeMethod(String tradeMethod) {
        this.tradeMethod = tradeMethod;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getDataCategoryIndicator() {
        return dataCategoryIndicator;
    }

    public void setDataCategoryIndicator(String dataCategoryIndicator) {
        this.dataCategoryIndicator = dataCategoryIndicator;
    }

    public String getUnderlyingSecurityID() {
        return underlyingSecurityID;
    }

    public void setUnderlyingSecurityID(String underlyingSecurityID) {
        this.underlyingSecurityID = underlyingSecurityID;
    }

    public String getUnderlyingSymbol() {
        return underlyingSymbol;
    }

    public void setUnderlyingSymbol(String underlyingSymbol) {
        this.underlyingSymbol = underlyingSymbol;
    }

    public BigDecimal getUnderlyingQty() {
        return underlyingQty;
    }

    public void setUnderlyingQty(BigDecimal underlyingQty) {
        this.underlyingQty = underlyingQty;
    }

    public String getNoUndrlyingStips() {
        return noUndrlyingStips;
    }

    public void setNoUndrlyingStips(String noUndrlyingStips) {
        this.noUndrlyingStips = noUndrlyingStips;
    }

    public BigDecimal getUnderlyingStipType() {
        return underlyingStipType;
    }

    public void setUnderlyingStipType(BigDecimal underlyingStipType) {
        this.underlyingStipType = underlyingStipType;
    }

    public BigDecimal getUnderlyingStipValue() {
        return underlyingStipValue;
    }

    public void setUnderlyingStipValue(BigDecimal underlyingStipValue) {
        this.underlyingStipValue = underlyingStipValue;
    }

    public String getExecID() {
        return execID;
    }

    public void setExecID(String execID) {
        this.execID = execID;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getAccruedInterestAmt() {
        return accruedInterestAmt;
    }

    public void setAccruedInterestAmt(BigDecimal accruedInterestAmt) {
        this.accruedInterestAmt = accruedInterestAmt;
    }

    public BigDecimal getDirtyPrice() {
        return dirtyPrice;
    }

    public void setDirtyPrice(BigDecimal dirtyPrice) {
        this.dirtyPrice = dirtyPrice;
    }

    public String getTermToMaturity() {
        return termToMaturity;
    }

    public void setTermToMaturity(String termToMaturity) {
        this.termToMaturity = termToMaturity;
    }

    public String getCashBondIndicator() {
        return cashBondIndicator;
    }

    public void setCashBondIndicator(String cashBondIndicator) {
        this.cashBondIndicator = cashBondIndicator;
    }

    public BigDecimal getSettlCurrAmt() {
        return settlCurrAmt;
    }

    public void setSettlCurrAmt(BigDecimal settlCurrAmt) {
        this.settlCurrAmt = settlCurrAmt;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getSettlType() {
        return settlType;
    }

    public void setSettlType(String settlType) {
        this.settlType = settlType;
    }

    public List<NoStipulations> getNoStipulations() {
        return noStipulations;
    }

    public void setNoStipulations(List<NoStipulations> noStipulations) {
        this.noStipulations = noStipulations;
    }


    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgSeqNum() {
        return msgSeqNum;
    }

    public void setMsgSeqNum(String msgSeqNum) {
        this.msgSeqNum = msgSeqNum;
    }

    public String getSendingTime() {
        return sendingTime;
    }

    public void setSendingTime(String sendingTime) {
        this.sendingTime = sendingTime;
    }

    public String getOnBehalfOfCompID() {
        return onBehalfOfCompID;
    }

    public void setOnBehalfOfCompID(String onBehalfOfCompID) {
        this.onBehalfOfCompID = onBehalfOfCompID;
    }

    public List<NoPartyID> getNoPartyIDs() {
        return noPartyIDs;
    }

    public void setNoPartyIDs(List<NoPartyID> noPartyIDs) {
        this.noPartyIDs = noPartyIDs;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    public BigDecimal getTotalPrincipal() {
        return totalPrincipal;
    }

    public void setTotalPrincipal(BigDecimal totalPrincipal) {
        this.totalPrincipal = totalPrincipal;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getSettlDate() {
        return settlDate;
    }

    public void setSettlDate(String settlDate) {
        this.settlDate = settlDate;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getSettlDate2() {
        return settlDate2;
    }

    public void setSettlDate2(String settlDate2) {
        this.settlDate2 = settlDate2;
    }

    public List<NoCrossCustodians> getNoCrossCustodians() {
        return noCrossCustodians;
    }

    public void setNoCrossCustodians(List<NoCrossCustodians> noCrossCustodians) {
        this.noCrossCustodians = noCrossCustodians;
    }

    public String getIsKtg() {
        return isKtg;
    }

    public void setIsKtg(String isKtg) {
        this.isKtg = isKtg;
    }
}

