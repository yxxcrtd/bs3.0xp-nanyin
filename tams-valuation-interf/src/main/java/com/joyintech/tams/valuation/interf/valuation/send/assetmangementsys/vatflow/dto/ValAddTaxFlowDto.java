/*
 * 文件名：ValAddTaxFlowDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2019年4月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.vatflow.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangqidong
 * @version 1.0 2019年4月23日
 * @see ValAddTaxFlowDto
 * @since 1.0
 */
public class ValAddTaxFlowDto extends BaseEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 发生日期
     */
    private Date happenDate;
    
    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 产品名称
     */
    private String finprodAbbr;
    
    /**
     * 资产代码
     */
    private String busiId;
    
    /**
     * 资产名称
     */
    private String assetName;
    
    /**
     * 本金收益特征
     */
    private String capiIncomeFeature;
    
    /**
     * 业务类型
     */
    private String taxPType;
    
    /**
     * 收入类型
     */
    private String valType;
    
    /**
     * 应税标识
     */
    private String calcType;
    
    /**
     * 应税税率
     */
    private BigDecimal taxRate;
    
    /**
     * 销售额
     */
    private BigDecimal happenAmt;
    
    /**
     * 含税销售额
     */
    private BigDecimal happenAmtTax;
    
    /**
     * 增值税额
     */
    private BigDecimal balAmt;
    
    /**
     * 投资目的
     */
    
    private String invAim;

    
    /**
     * 获取 happenDate
     * @return happenDate.
     */
    public Date getHappenDate() {
        return happenDate;
    }

    
    /**
     * 设置 happenDate
     * @param happenDate happenDate
     */
    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    
    /**
     * 获取 finprodId
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    
    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    
    /**
     * 获取 finprodAbbr
     * @return finprodAbbr.
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    
    /**
     * 设置 finprodAbbr
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    
    /**
     * 获取 busiId
     * @return busiId.
     */
    public String getBusiId() {
        return busiId;
    }

    
    /**
     * 设置 busiId
     * @param busiId busiId
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    
    /**
     * 获取 assetName
     * @return assetName.
     */
    public String getAssetName() {
        return assetName;
    }

    
    /**
     * 设置 assetName
     * @param assetName assetName
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    
    /**
     * 获取 capiIncomeFeature
     * @return capiIncomeFeature.
     */
    public String getCapiIncomeFeature() {
        return capiIncomeFeature;
    }

    
    /**
     * 设置 capiIncomeFeature
     * @param capiIncomeFeature capiIncomeFeature
     */
    public void setCapiIncomeFeature(String capiIncomeFeature) {
        this.capiIncomeFeature = capiIncomeFeature;
    }

    
    /**
     * 获取 taxPType
     * @return taxPType.
     */
    public String getTaxPType() {
        return taxPType;
    }

    
    /**
     * 设置 taxPType
     * @param taxPType taxPType
     */
    public void setTaxPType(String taxPType) {
        this.taxPType = taxPType;
    }

    
    /**
     * 获取 valType
     * @return valType.
     */
    public String getValType() {
        return valType;
    }

    
    /**
     * 设置 valType
     * @param valType valType
     */
    public void setValType(String valType) {
        this.valType = valType;
    }

    
    /**
     * 获取 calcType
     * @return calcType.
     */
    public String getCalcType() {
        return calcType;
    }

    
    /**
     * 设置 calcType
     * @param calcType calcType
     */
    public void setCalcType(String calcType) {
        this.calcType = calcType;
    }

    
    /**
     * 获取 taxRate
     * @return taxRate.
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    
    /**
     * 设置 taxRate
     * @param taxRate taxRate
     */
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    
    /**
     * 获取 happenAmt
     * @return happenAmt.
     */
    public BigDecimal getHappenAmt() {
        return happenAmt;
    }

    
    /**
     * 设置 happenAmt
     * @param happenAmt happenAmt
     */
    public void setHappenAmt(BigDecimal happenAmt) {
        this.happenAmt = happenAmt;
    }

    
    /**
     * 获取 happenAmtTax
     * @return happenAmtTax.
     */
    public BigDecimal getHappenAmtTax() {
        return happenAmtTax;
    }

    
    /**
     * 设置 happenAmtTax
     * @param happenAmtTax happenAmtTax
     */
    public void setHappenAmtTax(BigDecimal happenAmtTax) {
        this.happenAmtTax = happenAmtTax;
    }

    
    /**
     * 获取 balAmt
     * @return balAmt.
     */
    public BigDecimal getBalAmt() {
        return balAmt;
    }

    
    /**
     * 设置 balAmt
     * @param balAmt balAmt
     */
    public void setBalAmt(BigDecimal balAmt) {
        this.balAmt = balAmt;
    }

    
    /**
     * 获取 invAim
     * @return invAim.
     */
    public String getInvAim() {
        return invAim;
    }

    
    /**
     * 设置 invAim
     * @param invAim invAim
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }
    
}




