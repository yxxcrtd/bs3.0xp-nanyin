/*
 * 文件名：Rsk007InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.event.rsk007.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * RSK007接口-输入
 * 
 * @author zhangrui1
 * @version 1.0 2019年9月11日
 * @see Rsk007InEntity
 * @since 1.0
 */
public class Rsk007InEntity implements Serializable {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 487559159048332291L;

    /**
     * 事件代码
     */
    private String eventId;

    /**
     * 产品代码
     */
    private String portfolioCode;

    /**
     * 委托开始日
     */
    private String startDate;

    /**
     * 委托结束日
     */
    private String endDate;

    /**
     * 交易日
     */
    private String tradeDate;

    /**
     * 交割日
     */
    private String deliveryDate;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 资产代码
     */
    private String assetCode;

    /**
     * 资产名称
     */
    private String assetName;

    /**
     * 交易场所
     */
    private String market;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 价格
     */
    private BigDecimal priceAmt;

    /**
     * 数量
     */
    private BigDecimal tradeNum;

    /**
     * 净价
     */
    private BigDecimal netPrice;

    /**
     * 全价
     */
    private BigDecimal allPrice;

    /**
     * 单位净值
     */
    private BigDecimal unitNet;

    /**
     * 资产利率
     */
    private BigDecimal assetRate;

    /**
     * 交易员
     */
    private String trader;

    /**
     * 投资经理
     */
    private String manager;

    /**
     * 经办人
     */
    private String createUser;

    /**
     * 复核人
     */
    private String approveUser;

    /**
     * 市场代码
     */
    private String marketCode;

    /**
     * 事件类型
     */
    private String eventType;

    /**
     * 取得 事件代码
     * 
     * @return 事件代码
     */
    public String getEventId() {
        return this.eventId;
    }

    /**
     * 设置 事件代码
     * 
     * @param eventId 事件代码
     */
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    /**
     * 取得 产品代码
     * 
     * @return 产品代码
     */
    public String getPortfolioCode() {
        return this.portfolioCode;
    }

    /**
     * 设置 产品代码
     * 
     * @param portfolioCode 产品代码
     */
    public void setPortfolioCode(String portfolioCode) {
        this.portfolioCode = portfolioCode;
    }

    /**
     * 取得 委托开始日
     * 
     * @return 委托开始日
     */
    public String getStartDate() {
        return this.startDate;
    }

    /**
     * 设置 委托开始日
     * 
     * @param startDate 委托开始日
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * 取得 委托结束日
     * 
     * @return 委托结束日
     */
    public String getEndDate() {
        return this.endDate;
    }

    /**
     * 设置 委托结束日
     * 
     * @param endDate 委托结束日
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * 取得 交易日
     * 
     * @return 交易日
     */
    public String getTradeDate() {
        return this.tradeDate;
    }

    /**
     * 设置 交易日
     * 
     * @param tradeDate 交易日
     */
    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * 取得 交割日
     * 
     * @return 交割日
     */
    public String getDeliveryDate() {
        return this.deliveryDate;
    }

    /**
     * 设置 交割日
     * 
     * @param deliveryDate 交割日
     */
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * 取得 交易方向
     * 
     * @return 交易方向
     */
    public String getPs() {
        return this.ps;
    }

    /**
     * 设置 交易方向
     * 
     * @param ps 交易方向
     */
    public void setPs(String ps) {
        this.ps = ps;
    }

    /**
     * 取得 资产代码
     * 
     * @return 资产代码
     */
    public String getAssetCode() {
        return this.assetCode;
    }

    /**
     * 设置 资产代码
     * 
     * @param assetCode 资产代码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    /**
     * 取得 资产名称
     * 
     * @return 资产名称
     */
    public String getAssetName() {
        return this.assetName;
    }

    /**
     * 设置 资产名称
     * 
     * @param assetName 资产名称
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    /**
     * 取得 交易场所
     * 
     * @return 交易场所
     */
    public String getMarket() {
        return this.market;
    }

    /**
     * 设置 交易场所
     * 
     * @param market 交易场所
     */
    public void setMarket(String market) {
        this.market = market;
    }

    /**
     * 取得 金额
     * 
     * @return 金额
     */
    public BigDecimal getAmount() {
        return this.amount;
    }

    /**
     * 设置 金额
     * 
     * @param amount 金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 取得 价格
     * 
     * @return 价格
     */
    public BigDecimal getPriceAmt() {
        return this.priceAmt;
    }

    /**
     * 设置 价格
     * 
     * @param priceAmt 价格
     */
    public void setPriceAmt(BigDecimal priceAmt) {
        this.priceAmt = priceAmt;
    }

    /**
     * 取得 数量
     * 
     * @return 数量
     */
    public BigDecimal getTradeNum() {
        return this.tradeNum;
    }

    /**
     * 设置 数量
     * 
     * @param tradeNum 数量
     */
    public void setTradeNum(BigDecimal tradeNum) {
        this.tradeNum = tradeNum;
    }

    /**
     * 取得 净价
     * 
     * @return 净价
     */
    public BigDecimal getNetPrice() {
        return this.netPrice;
    }

    /**
     * 设置 净价
     * 
     * @param netPrice 净价
     */
    public void setNetPrice(BigDecimal netPrice) {
        this.netPrice = netPrice;
    }

    /**
     * 取得 全价
     * 
     * @return 全价
     */
    public BigDecimal getAllPrice() {
        return this.allPrice;
    }

    /**
     * 设置 全价
     * 
     * @param allPrice 全价
     */
    public void setAllPrice(BigDecimal allPrice) {
        this.allPrice = allPrice;
    }

    /**
     * 取得 单位净值
     * 
     * @return 单位净值
     */
    public BigDecimal getUnitNet() {
        return this.unitNet;
    }

    /**
     * 设置 单位净值
     * 
     * @param unitNet 单位净值
     */
    public void setUnitNet(BigDecimal unitNet) {
        this.unitNet = unitNet;
    }

    /**
     * 取得 资产利率
     * 
     * @return 资产利率
     */
    public BigDecimal getAssetRate() {
        return this.assetRate;
    }

    /**
     * 设置 资产利率
     * 
     * @param assetRate 资产利率
     */
    public void setAssetRate(BigDecimal assetRate) {
        this.assetRate = assetRate;
    }

    /**
     * 取得 交易员
     * 
     * @return 交易员
     */
    public String getTrader() {
        return this.trader;
    }

    /**
     * 设置 交易员
     * 
     * @param trader 交易员
     */
    public void setTrader(String trader) {
        this.trader = trader;
    }

    /**
     * 取得 投资经理
     * 
     * @return 投资经理
     */
    public String getManager() {
        return this.manager;
    }

    /**
     * 设置 投资经理
     * 
     * @param manager 投资经理
     */
    public void setManager(String manager) {
        this.manager = manager;
    }

    /**
     * 取得 经办人
     * 
     * @return 经办人
     */
    public String getCreateUser() {
        return this.createUser;
    }

    /**
     * 设置 经办人
     * 
     * @param createUser 经办人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 取得 复核人
     * 
     * @return 复核人
     */
    public String getApproveUser() {
        return this.approveUser;
    }

    /**
     * 设置 复核人
     * 
     * @param approveUser 复核人
     */
    public void setApproveUser(String approveUser) {
        this.approveUser = approveUser;
    }

    /**
     * 取得 市场代码
     * 
     * @return 市场代码
     */
    public String getMarketCode() {
        return this.marketCode;
    }

    /**
     * 设置 市场代码
     * 
     * @param marketCode 市场代码
     */
    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode;
    }

    /**
     * 取得 事件类型
     * 
     * @return 事件类型
     */
    public String getEventType() {
        return this.eventType;
    }

    /**
     * 设置 事件类型
     * 
     * @param eventType 事件类型
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
