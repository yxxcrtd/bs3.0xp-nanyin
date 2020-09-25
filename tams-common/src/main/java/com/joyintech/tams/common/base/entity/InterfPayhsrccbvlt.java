package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class InterfPayhsrccbvlt extends InterfPayhsrccbvltKey {
    /**
     * 剩余期限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal encashYears;

    /**
     * 市价全价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mdprice;

    /**
     * 市价净价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mcprice;

    /**
     * 市价久期
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mMduration;

    /**
     * 市价凸性
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mCnvxty;

    /**
     * 市价基点价值（PVBP）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mbasisvalue;

    /**
     * 基点价值（PVBP）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal basisvalue;

    /**
     * 利差久期
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal msprdD;

    /**
     * 利差凸性
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal msprdCnvxty;

    /**
     * 利率久期
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal myieldD;

    /**
     * 利率凸性
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal myieldCnvxty;

    /**
     * 市价收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal myield;

    /**
     * 风险价值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal var;

    /**
     * 条件风险价值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cvar;

    /**
     * 取得 剩余期限
     * @return 剩余期限
     */
    public BigDecimal getEncashYears() {
        return encashYears;
    }

    /**
     * 设置 剩余期限
     * @param encashYears 剩余期限
     */
    public void setEncashYears(BigDecimal encashYears) {
        this.encashYears = encashYears;
    }

    /**
     * 取得 市价全价
     * @return 市价全价
     */
    public BigDecimal getMdprice() {
        return mdprice;
    }

    /**
     * 设置 市价全价
     * @param mdprice 市价全价
     */
    public void setMdprice(BigDecimal mdprice) {
        this.mdprice = mdprice;
    }

    /**
     * 取得 市价净价
     * @return 市价净价
     */
    public BigDecimal getMcprice() {
        return mcprice;
    }

    /**
     * 设置 市价净价
     * @param mcprice 市价净价
     */
    public void setMcprice(BigDecimal mcprice) {
        this.mcprice = mcprice;
    }

    /**
     * 取得 市价久期
     * @return 市价久期
     */
    public BigDecimal getmMduration() {
        return mMduration;
    }

    /**
     * 设置 市价久期
     * @param mMduration 市价久期
     */
    public void setmMduration(BigDecimal mMduration) {
        this.mMduration = mMduration;
    }

    /**
     * 取得 市价凸性
     * @return 市价凸性
     */
    public BigDecimal getmCnvxty() {
        return mCnvxty;
    }

    /**
     * 设置 市价凸性
     * @param mCnvxty 市价凸性
     */
    public void setmCnvxty(BigDecimal mCnvxty) {
        this.mCnvxty = mCnvxty;
    }

    /**
     * 取得 市价基点价值（PVBP）
     * @return 市价基点价值（PVBP）
     */
    public BigDecimal getMbasisvalue() {
        return mbasisvalue;
    }

    /**
     * 设置 市价基点价值（PVBP）
     * @param mbasisvalue 市价基点价值（PVBP）
     */
    public void setMbasisvalue(BigDecimal mbasisvalue) {
        this.mbasisvalue = mbasisvalue;
    }

    /**
     * 取得 基点价值（PVBP）
     * @return 基点价值（PVBP）
     */
    public BigDecimal getBasisvalue() {
        return basisvalue;
    }

    /**
     * 设置 基点价值（PVBP）
     * @param basisvalue 基点价值（PVBP）
     */
    public void setBasisvalue(BigDecimal basisvalue) {
        this.basisvalue = basisvalue;
    }

    /**
     * 取得 利差久期
     * @return 利差久期
     */
    public BigDecimal getMsprdD() {
        return msprdD;
    }

    /**
     * 设置 利差久期
     * @param msprdD 利差久期
     */
    public void setMsprdD(BigDecimal msprdD) {
        this.msprdD = msprdD;
    }

    /**
     * 取得 利差凸性
     * @return 利差凸性
     */
    public BigDecimal getMsprdCnvxty() {
        return msprdCnvxty;
    }

    /**
     * 设置 利差凸性
     * @param msprdCnvxty 利差凸性
     */
    public void setMsprdCnvxty(BigDecimal msprdCnvxty) {
        this.msprdCnvxty = msprdCnvxty;
    }

    /**
     * 取得 利率久期
     * @return 利率久期
     */
    public BigDecimal getMyieldD() {
        return myieldD;
    }

    /**
     * 设置 利率久期
     * @param myieldD 利率久期
     */
    public void setMyieldD(BigDecimal myieldD) {
        this.myieldD = myieldD;
    }

    /**
     * 取得 利率凸性
     * @return 利率凸性
     */
    public BigDecimal getMyieldCnvxty() {
        return myieldCnvxty;
    }

    /**
     * 设置 利率凸性
     * @param myieldCnvxty 利率凸性
     */
    public void setMyieldCnvxty(BigDecimal myieldCnvxty) {
        this.myieldCnvxty = myieldCnvxty;
    }

    /**
     * 取得 市价收益率
     * @return 市价收益率
     */
    public BigDecimal getMyield() {
        return myield;
    }

    /**
     * 设置 市价收益率
     * @param myield 市价收益率
     */
    public void setMyield(BigDecimal myield) {
        this.myield = myield;
    }

    /**
     * 取得 风险价值
     * @return 风险价值
     */
    public BigDecimal getVar() {
        return var;
    }

    /**
     * 设置 风险价值
     * @param var 风险价值
     */
    public void setVar(BigDecimal var) {
        this.var = var;
    }

    /**
     * 取得 条件风险价值
     * @return 条件风险价值
     */
    public BigDecimal getCvar() {
        return cvar;
    }

    /**
     * 设置 条件风险价值
     * @param cvar 条件风险价值
     */
    public void setCvar(BigDecimal cvar) {
        this.cvar = cvar;
    }
}