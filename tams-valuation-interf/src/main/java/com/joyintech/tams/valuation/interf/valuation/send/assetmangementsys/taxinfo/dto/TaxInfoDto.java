/*
 * 文件名：taxinfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huwei
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.taxinfo.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 增值税缴纳Dto
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2018年11月19日
 * @see TaxInfoDto
 * @since 1.0
 */
public class TaxInfoDto extends BaseEntity{
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 产品代码
     */
    private String prodId;
    
    /**
     * 估值日期
     */
    private Date valDate;
    
    /**
     * 利息收入
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal interestIncome;

    /**
     * 利息收入税 
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal interestIncomeTax;
    
    /**
     * 红利收入
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dividend;
    
    /**
     * 红利收入税 
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dividendTax;
    
    /**
     * 价差收入
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal spreadIncome;
    
    /**
     * 价差收入税
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal spreadIncomeTax;
    
    /**
     * 应交增值税
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal vatPayable;
    
    /**
     * 应交增值附加税
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal supertax;
    
    /**
     * 城建税附加
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal additionalTaxCityBuild;
    
    /**
     * 教育费附加 
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal educationFeePlus;
    
    /**
     * 地方教育费附加
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal localEducationFeePlus;

    
    public String getProdId() {
        return prodId;
    }

    
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    
    public Date getValDate() {
        return valDate;
    }

    
    public void setValDate(Date valDate) {
        this.valDate = valDate;
    }

    
    public BigDecimal getInterestIncome() {
        return interestIncome;
    }

    
    public void setInterestIncome(BigDecimal interestIncome) {
        this.interestIncome = interestIncome;
    }

    
    public BigDecimal getInterestIncomeTax() {
        return interestIncomeTax;
    }

    
    public void setInterestIncomeTax(BigDecimal interestIncomeTax) {
        this.interestIncomeTax = interestIncomeTax;
    }

    
    public BigDecimal getDividend() {
        return dividend;
    }

    
    public void setDividend(BigDecimal dividend) {
        this.dividend = dividend;
    }

    
    public BigDecimal getDividendTax() {
        return dividendTax;
    }

    
    public void setDividendTax(BigDecimal dividendTax) {
        this.dividendTax = dividendTax;
    }

    
    public BigDecimal getSpreadIncome() {
        return spreadIncome;
    }

    
    public void setSpreadIncome(BigDecimal spreadIncome) {
        this.spreadIncome = spreadIncome;
    }

    
    public BigDecimal getSpreadIncomeTax() {
        return spreadIncomeTax;
    }

    
    public void setSpreadIncomeTax(BigDecimal spreadIncomeTax) {
        this.spreadIncomeTax = spreadIncomeTax;
    }

    
    public BigDecimal getVatPayable() {
        return vatPayable;
    }

    
    public void setVatPayable(BigDecimal vatPayable) {
        this.vatPayable = vatPayable;
    }

    
    public BigDecimal getSupertax() {
        return supertax;
    }

    
    public void setSupertax(BigDecimal supertax) {
        this.supertax = supertax;
    }

    public BigDecimal getAdditionalTaxCityBuild() {
        return additionalTaxCityBuild;
    }


    
    public void setAdditionalTaxCityBuild(BigDecimal additionalTaxCityBuild) {
        this.additionalTaxCityBuild = additionalTaxCityBuild;
    }


    public BigDecimal getEducationFeePlus() {
        return educationFeePlus;
    }

    
    public void setEducationFeePlus(BigDecimal educationFeePlus) {
        this.educationFeePlus = educationFeePlus;
    }

    
    public BigDecimal getLocalEducationFeePlus() {
        return localEducationFeePlus;
    }

    
    public void setLocalEducationFeePlus(BigDecimal localEducationFeePlus) {
        this.localEducationFeePlus = localEducationFeePlus;
    }

    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
}
