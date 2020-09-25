package com.joyintech.tams.invest.product.productduration.addreduce.dto;

import java.math.BigDecimal;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: code
 * @Package com.joyintech.tams.invest.product.common.dto
 * @Description: TODO
 * @date Date : 2018年09月13日 11:38
 */
public class ProdOtherData {

    /**
     * 销售部门
     */
    private String saleDepartment;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 简称
     */
    private String shortName;

    /**
     * 收益类型
     */
    private String profitType;

    /**
     * 单位净值小数位
     */
    private BigDecimal unitvalueDec;

    /**
     * 单位净值尾数处理方式
     */
    private String unitvalueMode;

    /**
     * 投资组合
     */
    private String ptlId;

    /**
     * 本金收益特征
     */
    private String capiIncomeFeature;

    /**
     * 实际到期日
     */
    private String actmdate;

    /**
     * 起息日
     */
    private String vdate;

    /**
     * 获取 saleDepartment
     *
     * @return saleDepartment
     */
    public String getSaleDepartment() {
        return saleDepartment;
    }

    /**
     * 设置 saleDepartment
     *
     * @param saleDepartment saleDepartment
     */
    public void setSaleDepartment(String saleDepartment) {
        this.saleDepartment = saleDepartment;
    }

    /**
     * 获取 ccy
     *
     * @return ccy
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 ccy
     *
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    /**
     * 获取 name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 name
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 shortName
     *
     * @return shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 设置 shortName
     *
     * @param shortName shortName
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * 获取 profitType
     *
     * @return profitType
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 profitType
     *
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    /**
     * 获取 unitvalueDec
     *
     * @return unitvalueDec
     */
    public BigDecimal getUnitvalueDec() {
        return unitvalueDec;
    }

    /**
     * 设置 unitvalueDec
     *
     * @param unitvalueDec unitvalueDec
     */
    public void setUnitvalueDec(BigDecimal unitvalueDec) {
        this.unitvalueDec = unitvalueDec;
    }

    /**
     * 获取 unitvalueMode
     *
     * @return unitvalueMode
     */
    public String getUnitvalueMode() {
        return unitvalueMode;
    }

    /**
     * 设置 unitvalueMode
     *
     * @param unitvalueMode unitvalueMode
     */
    public void setUnitvalueMode(String unitvalueMode) {
        this.unitvalueMode = unitvalueMode;
    }

    /**
     * 获取 ptlId
     *
     * @return ptlId
     */
    public String getPtlId() {
        return ptlId;
    }

    /**
     * 设置 ptlId
     *
     * @param ptlId ptlId
     */
    public void setPtlId(String ptlId) {
        this.ptlId = ptlId;
    }

    /**
     * 获取 capiIncomeFeature
     *
     * @return capiIncomeFeature
     */
    public String getCapiIncomeFeature() {
        return capiIncomeFeature;
    }

    /**
     * 设置 capiIncomeFeature
     *
     * @param capiIncomeFeature capiIncomeFeature
     */
    public void setCapiIncomeFeature(String capiIncomeFeature) {
        this.capiIncomeFeature = capiIncomeFeature;
    }

    /**
     * 获取 actmdate
     *
     * @return actmdate
     */
    public String getActmdate() {
        return actmdate;
    }

    /**
     * 设置 actmdate
     *
     * @param actmdate actmdate
     */
    public void setActmdate(String actmdate) {
        this.actmdate = actmdate;
    }

    /**
     * 获取 vdate
     *
     * @return vdate
     */
    public String getVdate() {
        return vdate;
    }

    /**
     * 设置 vdate
     *
     * @param vdate vdate
     */
    public void setVdate(String vdate) {
        this.vdate = vdate;
    }
}
