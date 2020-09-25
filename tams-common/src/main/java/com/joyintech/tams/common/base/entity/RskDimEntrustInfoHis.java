package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

import java.math.BigDecimal;
import java.util.Date;

public class RskDimEntrustInfoHis extends BaseEntity {
    /**
     * 流水号
     */
    private String uuid;

    /**
     * 事件日期
     */
    private Date eventDate;

    /**
     * 委托编号
     */
    private String eventId;

    /**
     * 事件类型(0：投资委托)
     */
    private String eventType;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 资产代码
     */
    private String assetCode;

    /**
     * 系统资产大类
     */
    private String assetType;

    /**
     * 系统资产细类
     */
    private String assetType2;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amount;

    /**
     * 价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal priceAmt;

    /**
     * 数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tradeNum;

    /**
     * 净价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netPrice;

    /**
     * 全价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal allPrice;

    /**
     * 单位净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitNet;

    /**
     * 资产利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
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
     * 交割日
     */
    private Date deliveryDate;

    /**
     * 委托开始日
     */
    private Date startDate;

    /**
     * 委托结束日
     */
    private Date endDate;

    /**
     * 交易场所
     */
    private String market;

    /**
     * 市场代码
     */
    private String marketCode;

    /**
     * 发行人
     */
    private String issuer;

    /**
     * 发行人名称
     */
    private String issuerName;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置 流水号
     * @param uuid 流水号
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 取得 事件日期
     * @return 事件日期
     */
    public Date getEventDate() {
        return eventDate;
    }

    /**
     * 设置 事件日期
     * @param eventDate 事件日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * 取得 委托编号
     * @return 委托编号
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * 设置 委托编号
     * @param eventId 委托编号
     */
    public void setEventId(String eventId) {
        this.eventId = eventId == null ? null : eventId.trim();
    }

    /**
     * 取得 事件类型(0：投资委托)
     * @return 事件类型(0：投资委托)
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * 设置 事件类型(0：投资委托)
     * @param eventType 事件类型(0：投资委托)
     */
    public void setEventType(String eventType) {
        this.eventType = eventType == null ? null : eventType.trim();
    }

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 资产代码
     * @return 资产代码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 设置 资产代码
     * @param assetCode 资产代码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 取得 系统资产大类
     * @return 系统资产大类
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * 设置 系统资产大类
     * @param assetType 系统资产大类
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
    }

    /**
     * 取得 系统资产细类
     * @return 系统资产细类
     */
    public String getAssetType2() {
        return assetType2;
    }

    /**
     * 设置 系统资产细类
     * @param assetType2 系统资产细类
     */
    public void setAssetType2(String assetType2) {
        this.assetType2 = assetType2 == null ? null : assetType2.trim();
    }

    /**
     * 取得 交易方向
     * @return 交易方向
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 交易方向
     * @param ps 交易方向
     */
    public void setPs(String ps) {
        this.ps = ps == null ? null : ps.trim();
    }

    /**
     * 取得 金额
     * @return 金额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置 金额
     * @param amount 金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 取得 价格
     * @return 价格
     */
    public BigDecimal getPriceAmt() {
        return priceAmt;
    }

    /**
     * 设置 价格
     * @param priceAmt 价格
     */
    public void setPriceAmt(BigDecimal priceAmt) {
        this.priceAmt = priceAmt;
    }

    /**
     * 取得 数量
     * @return 数量
     */
    public BigDecimal getTradeNum() {
        return tradeNum;
    }

    /**
     * 设置 数量
     * @param tradeNum 数量
     */
    public void setTradeNum(BigDecimal tradeNum) {
        this.tradeNum = tradeNum;
    }

    /**
     * 取得 净价
     * @return 净价
     */
    public BigDecimal getNetPrice() {
        return netPrice;
    }

    /**
     * 设置 净价
     * @param netPrice 净价
     */
    public void setNetPrice(BigDecimal netPrice) {
        this.netPrice = netPrice;
    }

    /**
     * 取得 全价
     * @return 全价
     */
    public BigDecimal getAllPrice() {
        return allPrice;
    }

    /**
     * 设置 全价
     * @param allPrice 全价
     */
    public void setAllPrice(BigDecimal allPrice) {
        this.allPrice = allPrice;
    }

    /**
     * 取得 单位净值
     * @return 单位净值
     */
    public BigDecimal getUnitNet() {
        return unitNet;
    }

    /**
     * 设置 单位净值
     * @param unitNet 单位净值
     */
    public void setUnitNet(BigDecimal unitNet) {
        this.unitNet = unitNet;
    }

    /**
     * 取得 资产利率
     * @return 资产利率
     */
    public BigDecimal getAssetRate() {
        return assetRate;
    }

    /**
     * 设置 资产利率
     * @param assetRate 资产利率
     */
    public void setAssetRate(BigDecimal assetRate) {
        this.assetRate = assetRate;
    }

    /**
     * 取得 交易员
     * @return 交易员
     */
    public String getTrader() {
        return trader;
    }

    /**
     * 设置 交易员
     * @param trader 交易员
     */
    public void setTrader(String trader) {
        this.trader = trader == null ? null : trader.trim();
    }

    /**
     * 取得 投资经理
     * @return 投资经理
     */
    public String getManager() {
        return manager;
    }

    /**
     * 设置 投资经理
     * @param manager 投资经理
     */
    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }

    /**
     * 取得 交割日
     * @return 交割日
     */
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * 设置 交割日
     * @param deliveryDate 交割日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * 取得 委托开始日
     * @return 委托开始日
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置 委托开始日
     * @param startDate 委托开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 取得 委托结束日
     * @return 委托结束日
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置 委托结束日
     * @param endDate 委托结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 取得 交易场所
     * @return 交易场所
     */
    public String getMarket() {
        return market;
    }

    /**
     * 设置 交易场所
     * @param market 交易场所
     */
    public void setMarket(String market) {
        this.market = market == null ? null : market.trim();
    }

    /**
     * 取得 市场代码
     * @return 市场代码
     */
    public String getMarketCode() {
        return marketCode;
    }

    /**
     * 设置 市场代码
     * @param marketCode 市场代码
     */
    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode == null ? null : marketCode.trim();
    }

    /**
     * 取得 发行人
     * @return 发行人
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * 设置 发行人
     * @param issuer 发行人
     */
    public void setIssuer(String issuer) {
        this.issuer = issuer == null ? null : issuer.trim();
    }

    /**
     * 取得 发行人名称
     * @return 发行人名称
     */
    public String getIssuerName() {
        return issuerName;
    }

    /**
     * 设置 发行人名称
     * @param issuerName 发行人名称
     */
    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName == null ? null : issuerName.trim();
    }
}