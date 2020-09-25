package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class InterfCmfundnav extends InterfCmfundnavKey {
    /**
     * 公告日期
     */
    private String annDate;

    /**
     * 单位净值/万份收益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fNavUnit;

    /**
     * 最近七日收益所折算的年资产收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fInfoYearlyroe;

    /**
     * 年化收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yearlyroe;

    /**
     * 累计净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fNavAccumulated;

    /**
     * 累计分红
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fNavDivaccumulated;

    /**
     * 复权因子
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fNavAdjfactor;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 资产净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fPrtNetasset;

    /**
     * 复权单位净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fNavAdjusted;

    /**
     * 取得 公告日期
     * @return 公告日期
     */
    public String getAnnDate() {
        return annDate;
    }

    /**
     * 设置 公告日期
     * @param annDate 公告日期
     */
    public void setAnnDate(String annDate) {
        this.annDate = annDate == null ? null : annDate.trim();
    }

    /**
     * 取得 单位净值/万份收益
     * @return 单位净值/万份收益
     */
    public BigDecimal getfNavUnit() {
        return fNavUnit;
    }

    /**
     * 设置 单位净值/万份收益
     * @param fNavUnit 单位净值/万份收益
     */
    public void setfNavUnit(BigDecimal fNavUnit) {
        this.fNavUnit = fNavUnit;
    }

    /**
     * 取得 最近七日收益所折算的年资产收益率
     * @return 最近七日收益所折算的年资产收益率
     */
    public BigDecimal getfInfoYearlyroe() {
        return fInfoYearlyroe;
    }

    /**
     * 设置 最近七日收益所折算的年资产收益率
     * @param fInfoYearlyroe 最近七日收益所折算的年资产收益率
     */
    public void setfInfoYearlyroe(BigDecimal fInfoYearlyroe) {
        this.fInfoYearlyroe = fInfoYearlyroe;
    }

    /**
     * 取得 年化收益率
     * @return 年化收益率
     */
    public BigDecimal getYearlyroe() {
        return yearlyroe;
    }

    /**
     * 设置 年化收益率
     * @param yearlyroe 年化收益率
     */
    public void setYearlyroe(BigDecimal yearlyroe) {
        this.yearlyroe = yearlyroe;
    }

    /**
     * 取得 累计净值
     * @return 累计净值
     */
    public BigDecimal getfNavAccumulated() {
        return fNavAccumulated;
    }

    /**
     * 设置 累计净值
     * @param fNavAccumulated 累计净值
     */
    public void setfNavAccumulated(BigDecimal fNavAccumulated) {
        this.fNavAccumulated = fNavAccumulated;
    }

    /**
     * 取得 累计分红
     * @return 累计分红
     */
    public BigDecimal getfNavDivaccumulated() {
        return fNavDivaccumulated;
    }

    /**
     * 设置 累计分红
     * @param fNavDivaccumulated 累计分红
     */
    public void setfNavDivaccumulated(BigDecimal fNavDivaccumulated) {
        this.fNavDivaccumulated = fNavDivaccumulated;
    }

    /**
     * 取得 复权因子
     * @return 复权因子
     */
    public BigDecimal getfNavAdjfactor() {
        return fNavAdjfactor;
    }

    /**
     * 设置 复权因子
     * @param fNavAdjfactor 复权因子
     */
    public void setfNavAdjfactor(BigDecimal fNavAdjfactor) {
        this.fNavAdjfactor = fNavAdjfactor;
    }

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
     * 取得 资产净值
     * @return 资产净值
     */
    public BigDecimal getfPrtNetasset() {
        return fPrtNetasset;
    }

    /**
     * 设置 资产净值
     * @param fPrtNetasset 资产净值
     */
    public void setfPrtNetasset(BigDecimal fPrtNetasset) {
        this.fPrtNetasset = fPrtNetasset;
    }

    /**
     * 取得 复权单位净值
     * @return 复权单位净值
     */
    public BigDecimal getfNavAdjusted() {
        return fNavAdjusted;
    }

    /**
     * 设置 复权单位净值
     * @param fNavAdjusted 复权单位净值
     */
    public void setfNavAdjusted(BigDecimal fNavAdjusted) {
        this.fNavAdjusted = fNavAdjusted;
    }
}