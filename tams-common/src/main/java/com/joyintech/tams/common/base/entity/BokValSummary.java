package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class BokValSummary extends BokValSummaryKey {
    /**
     * 账套日期
     */
    private Date booksetDate;

    /**
     * 收益类型
     */
    private String profitType;

    /**
     * 实收资本
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal capital;

    /**
     * 当日应付利润
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal profitAmt;

    /**
     * 累计应付利润
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totProfitAmt;

    /**
     * 计提利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intRate;

    /**
     * 费前资产净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netAssetValueBef;

    /**
     * 资产净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netAssetValue;

    /**
     * 费前单位净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netUnitValueBef;

    /**
     * 单位净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netUnitValue;

    /**
     * 万份收益_费前
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pYieldBef;

    /**
     * 万份收益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pYield;

    /**
     * 当日年化收益率_费前
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyYieldBef;

    /**
     * 当日年化收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyYield;

    /**
     * 周期年化收益率_费前
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yieldTermBef;

    /**
     * 周期年化收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yieldTerm;

    /**
     * 当日7日年化收益率_费前
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield7Bef;

    /**
     * 当日7日年化收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield7;

    /**
     * 累计分红金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totBounsAmt;

    /**
     * 当日分红金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyBounsAmt;

    /**
     * 累计分红净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totBounsNet;

    /**
     * 当日分红净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyBounsNet;

    /**
     * 取得 账套日期
     * @return 账套日期
     */
    public Date getBooksetDate() {
        return booksetDate;
    }

    /**
     * 设置 账套日期
     * @param booksetDate 账套日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }

    /**
     * 取得 收益类型
     * @return 收益类型
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 收益类型
     * @param profitType 收益类型
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType == null ? null : profitType.trim();
    }

    /**
     * 取得 实收资本
     * @return 实收资本
     */
    public BigDecimal getCapital() {
        return capital;
    }

    /**
     * 设置 实收资本
     * @param capital 实收资本
     */
    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    /**
     * 取得 当日应付利润
     * @return 当日应付利润
     */
    public BigDecimal getProfitAmt() {
        return profitAmt;
    }

    /**
     * 设置 当日应付利润
     * @param profitAmt 当日应付利润
     */
    public void setProfitAmt(BigDecimal profitAmt) {
        this.profitAmt = profitAmt;
    }

    /**
     * 取得 累计应付利润
     * @return 累计应付利润
     */
    public BigDecimal getTotProfitAmt() {
        return totProfitAmt;
    }

    /**
     * 设置 累计应付利润
     * @param totProfitAmt 累计应付利润
     */
    public void setTotProfitAmt(BigDecimal totProfitAmt) {
        this.totProfitAmt = totProfitAmt;
    }

    /**
     * 取得 计提利率
     * @return 计提利率
     */
    public BigDecimal getIntRate() {
        return intRate;
    }

    /**
     * 设置 计提利率
     * @param intRate 计提利率
     */
    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }

    /**
     * 取得 费前资产净值
     * @return 费前资产净值
     */
    public BigDecimal getNetAssetValueBef() {
        return netAssetValueBef;
    }

    /**
     * 设置 费前资产净值
     * @param netAssetValueBef 费前资产净值
     */
    public void setNetAssetValueBef(BigDecimal netAssetValueBef) {
        this.netAssetValueBef = netAssetValueBef;
    }

    /**
     * 取得 资产净值
     * @return 资产净值
     */
    public BigDecimal getNetAssetValue() {
        return netAssetValue;
    }

    /**
     * 设置 资产净值
     * @param netAssetValue 资产净值
     */
    public void setNetAssetValue(BigDecimal netAssetValue) {
        this.netAssetValue = netAssetValue;
    }

    /**
     * 取得 费前单位净值
     * @return 费前单位净值
     */
    public BigDecimal getNetUnitValueBef() {
        return netUnitValueBef;
    }

    /**
     * 设置 费前单位净值
     * @param netUnitValueBef 费前单位净值
     */
    public void setNetUnitValueBef(BigDecimal netUnitValueBef) {
        this.netUnitValueBef = netUnitValueBef;
    }

    /**
     * 取得 单位净值
     * @return 单位净值
     */
    public BigDecimal getNetUnitValue() {
        return netUnitValue;
    }

    /**
     * 设置 单位净值
     * @param netUnitValue 单位净值
     */
    public void setNetUnitValue(BigDecimal netUnitValue) {
        this.netUnitValue = netUnitValue;
    }

    /**
     * 取得 万份收益_费前
     * @return 万份收益_费前
     */
    public BigDecimal getpYieldBef() {
        return pYieldBef;
    }

    /**
     * 设置 万份收益_费前
     * @param pYieldBef 万份收益_费前
     */
    public void setpYieldBef(BigDecimal pYieldBef) {
        this.pYieldBef = pYieldBef;
    }

    /**
     * 取得 万份收益
     * @return 万份收益
     */
    public BigDecimal getpYield() {
        return pYield;
    }

    /**
     * 设置 万份收益
     * @param pYield 万份收益
     */
    public void setpYield(BigDecimal pYield) {
        this.pYield = pYield;
    }

    /**
     * 取得 当日年化收益率_费前
     * @return 当日年化收益率_费前
     */
    public BigDecimal getTdyYieldBef() {
        return tdyYieldBef;
    }

    /**
     * 设置 当日年化收益率_费前
     * @param tdyYieldBef 当日年化收益率_费前
     */
    public void setTdyYieldBef(BigDecimal tdyYieldBef) {
        this.tdyYieldBef = tdyYieldBef;
    }

    /**
     * 取得 当日年化收益率
     * @return 当日年化收益率
     */
    public BigDecimal getTdyYield() {
        return tdyYield;
    }

    /**
     * 设置 当日年化收益率
     * @param tdyYield 当日年化收益率
     */
    public void setTdyYield(BigDecimal tdyYield) {
        this.tdyYield = tdyYield;
    }

    /**
     * 取得 周期年化收益率_费前
     * @return 周期年化收益率_费前
     */
    public BigDecimal getYieldTermBef() {
        return yieldTermBef;
    }

    /**
     * 设置 周期年化收益率_费前
     * @param yieldTermBef 周期年化收益率_费前
     */
    public void setYieldTermBef(BigDecimal yieldTermBef) {
        this.yieldTermBef = yieldTermBef;
    }

    /**
     * 取得 周期年化收益率
     * @return 周期年化收益率
     */
    public BigDecimal getYieldTerm() {
        return yieldTerm;
    }

    /**
     * 设置 周期年化收益率
     * @param yieldTerm 周期年化收益率
     */
    public void setYieldTerm(BigDecimal yieldTerm) {
        this.yieldTerm = yieldTerm;
    }

    /**
     * 取得 当日7日年化收益率_费前
     * @return 当日7日年化收益率_费前
     */
    public BigDecimal getYield7Bef() {
        return yield7Bef;
    }

    /**
     * 设置 当日7日年化收益率_费前
     * @param yield7Bef 当日7日年化收益率_费前
     */
    public void setYield7Bef(BigDecimal yield7Bef) {
        this.yield7Bef = yield7Bef;
    }

    /**
     * 取得 当日7日年化收益率
     * @return 当日7日年化收益率
     */
    public BigDecimal getYield7() {
        return yield7;
    }

    /**
     * 设置 当日7日年化收益率
     * @param yield7 当日7日年化收益率
     */
    public void setYield7(BigDecimal yield7) {
        this.yield7 = yield7;
    }

    /**
     * 取得 累计分红金额
     * @return 累计分红金额
     */
    public BigDecimal getTotBounsAmt() {
        return totBounsAmt;
    }

    /**
     * 设置 累计分红金额
     * @param totBounsAmt 累计分红金额
     */
    public void setTotBounsAmt(BigDecimal totBounsAmt) {
        this.totBounsAmt = totBounsAmt;
    }

    /**
     * 取得 当日分红金额
     * @return 当日分红金额
     */
    public BigDecimal getTdyBounsAmt() {
        return tdyBounsAmt;
    }

    /**
     * 设置 当日分红金额
     * @param tdyBounsAmt 当日分红金额
     */
    public void setTdyBounsAmt(BigDecimal tdyBounsAmt) {
        this.tdyBounsAmt = tdyBounsAmt;
    }

    /**
     * 取得 累计分红净值
     * @return 累计分红净值
     */
    public BigDecimal getTotBounsNet() {
        return totBounsNet;
    }

    /**
     * 设置 累计分红净值
     * @param totBounsNet 累计分红净值
     */
    public void setTotBounsNet(BigDecimal totBounsNet) {
        this.totBounsNet = totBounsNet;
    }

    /**
     * 取得 当日分红净值
     * @return 当日分红净值
     */
    public BigDecimal getTdyBounsNet() {
        return tdyBounsNet;
    }

    /**
     * 设置 当日分红净值
     * @param tdyBounsNet 当日分红净值
     */
    public void setTdyBounsNet(BigDecimal tdyBounsNet) {
        this.tdyBounsNet = tdyBounsNet;
    }
}