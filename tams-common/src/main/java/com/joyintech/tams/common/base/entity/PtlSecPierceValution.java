package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class PtlSecPierceValution extends PtlSecPierceValutionKey {
    /**
     * 币种
     */
    private String ccy;

    /**
     * 数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal shareAmt;

    /**
     * 摊销总成本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dscCostAmt;

    /**
     * 摊销成本净价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dscCleanPrice;

    /**
     * 买入总成本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyCostAmt;

    /**
     * 买入成本净价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyCleanPrice;

    /**
     * 市值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal marketValue;

    /**
     * 市价净价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal marketCleanPrice;

    /**
     * 当日应计利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyIntincexp;

    /**
     * 当日发生摊销收入
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyDscincexpAdd;

    /**
     * 当日发生利息收入
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyIntincexpAdd;

    /**
     * 当日发生价差收入
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyDsclossAdd;

    /**
     * 当日发生浮动盈亏
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyFloatIngplAdd;

    /**
     * 当日发生费用支出
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyFeeAdd;

    /**
     * 累计单位净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal accuNetValue;

    /**
     * 科目代码
     */
    private String subCode;

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 数量
     * @return 数量
     */
    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    /**
     * 设置 数量
     * @param shareAmt 数量
     */
    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    /**
     * 取得 摊销总成本
     * @return 摊销总成本
     */
    public BigDecimal getDscCostAmt() {
        return dscCostAmt;
    }

    /**
     * 设置 摊销总成本
     * @param dscCostAmt 摊销总成本
     */
    public void setDscCostAmt(BigDecimal dscCostAmt) {
        this.dscCostAmt = dscCostAmt;
    }

    /**
     * 取得 摊销成本净价
     * @return 摊销成本净价
     */
    public BigDecimal getDscCleanPrice() {
        return dscCleanPrice;
    }

    /**
     * 设置 摊销成本净价
     * @param dscCleanPrice 摊销成本净价
     */
    public void setDscCleanPrice(BigDecimal dscCleanPrice) {
        this.dscCleanPrice = dscCleanPrice;
    }

    /**
     * 取得 买入总成本
     * @return 买入总成本
     */
    public BigDecimal getBuyCostAmt() {
        return buyCostAmt;
    }

    /**
     * 设置 买入总成本
     * @param buyCostAmt 买入总成本
     */
    public void setBuyCostAmt(BigDecimal buyCostAmt) {
        this.buyCostAmt = buyCostAmt;
    }

    /**
     * 取得 买入成本净价
     * @return 买入成本净价
     */
    public BigDecimal getBuyCleanPrice() {
        return buyCleanPrice;
    }

    /**
     * 设置 买入成本净价
     * @param buyCleanPrice 买入成本净价
     */
    public void setBuyCleanPrice(BigDecimal buyCleanPrice) {
        this.buyCleanPrice = buyCleanPrice;
    }

    /**
     * 取得 市值
     * @return 市值
     */
    public BigDecimal getMarketValue() {
        return marketValue;
    }

    /**
     * 设置 市值
     * @param marketValue 市值
     */
    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }

    /**
     * 取得 市价净价
     * @return 市价净价
     */
    public BigDecimal getMarketCleanPrice() {
        return marketCleanPrice;
    }

    /**
     * 设置 市价净价
     * @param marketCleanPrice 市价净价
     */
    public void setMarketCleanPrice(BigDecimal marketCleanPrice) {
        this.marketCleanPrice = marketCleanPrice;
    }

    /**
     * 取得 当日应计利息
     * @return 当日应计利息
     */
    public BigDecimal getTdyIntincexp() {
        return tdyIntincexp;
    }

    /**
     * 设置 当日应计利息
     * @param tdyIntincexp 当日应计利息
     */
    public void setTdyIntincexp(BigDecimal tdyIntincexp) {
        this.tdyIntincexp = tdyIntincexp;
    }

    /**
     * 取得 当日发生摊销收入
     * @return 当日发生摊销收入
     */
    public BigDecimal getTdyDscincexpAdd() {
        return tdyDscincexpAdd;
    }

    /**
     * 设置 当日发生摊销收入
     * @param tdyDscincexpAdd 当日发生摊销收入
     */
    public void setTdyDscincexpAdd(BigDecimal tdyDscincexpAdd) {
        this.tdyDscincexpAdd = tdyDscincexpAdd;
    }

    /**
     * 取得 当日发生利息收入
     * @return 当日发生利息收入
     */
    public BigDecimal getTdyIntincexpAdd() {
        return tdyIntincexpAdd;
    }

    /**
     * 设置 当日发生利息收入
     * @param tdyIntincexpAdd 当日发生利息收入
     */
    public void setTdyIntincexpAdd(BigDecimal tdyIntincexpAdd) {
        this.tdyIntincexpAdd = tdyIntincexpAdd;
    }

    /**
     * 取得 当日发生价差收入
     * @return 当日发生价差收入
     */
    public BigDecimal getTdyDsclossAdd() {
        return tdyDsclossAdd;
    }

    /**
     * 设置 当日发生价差收入
     * @param tdyDsclossAdd 当日发生价差收入
     */
    public void setTdyDsclossAdd(BigDecimal tdyDsclossAdd) {
        this.tdyDsclossAdd = tdyDsclossAdd;
    }

    /**
     * 取得 当日发生浮动盈亏
     * @return 当日发生浮动盈亏
     */
    public BigDecimal getTdyFloatIngplAdd() {
        return tdyFloatIngplAdd;
    }

    /**
     * 设置 当日发生浮动盈亏
     * @param tdyFloatIngplAdd 当日发生浮动盈亏
     */
    public void setTdyFloatIngplAdd(BigDecimal tdyFloatIngplAdd) {
        this.tdyFloatIngplAdd = tdyFloatIngplAdd;
    }

    /**
     * 取得 当日发生费用支出
     * @return 当日发生费用支出
     */
    public BigDecimal getTdyFeeAdd() {
        return tdyFeeAdd;
    }

    /**
     * 设置 当日发生费用支出
     * @param tdyFeeAdd 当日发生费用支出
     */
    public void setTdyFeeAdd(BigDecimal tdyFeeAdd) {
        this.tdyFeeAdd = tdyFeeAdd;
    }

    /**
     * 取得 累计单位净值
     * @return 累计单位净值
     */
    public BigDecimal getAccuNetValue() {
        return accuNetValue;
    }

    /**
     * 设置 累计单位净值
     * @param accuNetValue 累计单位净值
     */
    public void setAccuNetValue(BigDecimal accuNetValue) {
        this.accuNetValue = accuNetValue;
    }

    /**
     * 取得 科目代码
     * @return 科目代码
     */
    public String getSubCode() {
        return subCode;
    }

    /**
     * 设置 科目代码
     * @param subCode 科目代码
     */
    public void setSubCode(String subCode) {
        this.subCode = subCode == null ? null : subCode.trim();
    }
}