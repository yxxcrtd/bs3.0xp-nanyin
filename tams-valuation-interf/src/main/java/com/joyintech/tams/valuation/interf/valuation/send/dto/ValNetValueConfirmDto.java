/*
 * 文件名：ValFinprodTradeQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.interf.valuation.send.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 发送净值给资管系统dto
 * @see ValNetValueConfirmDto
 * @since 1.0
 */
public class ValNetValueConfirmDto extends BaseEntity {
	
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
	/**
     * 帐套编号
     */
    private String bookId;
	
    /**
     * 金融产品代码/基金代码
     */
    private String finprodId;
    /**
     * 金融产品名称/基金名称
     */
    private String finprodAbbr;
    /**
     * 估值日期                 
     */
    private String valDate;
   
    /**
     * 资产市值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal assetValue;
   
    /**
     * 万份收益    
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netUnitValue;
   
    /**
     * 7日年化收益     
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal annualIncomeSevenDay;
    
    /**
     * 年化收益     
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal annualIncome;
    
    /**
     * 计息基础
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal basis;

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public String getFinprodAbbr() {
		return finprodAbbr;
	}

	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}

	public String getValDate() {
		return valDate;
	}

	public void setValDate(String valDate) {
		this.valDate = valDate;
	}

	public BigDecimal getAssetValue() {
		return assetValue;
	}

	public void setAssetValue(BigDecimal assetValue) {
		this.assetValue = assetValue;
	}

	public BigDecimal getNetUnitValue() {
		return netUnitValue;
	}

	public void setNetUnitValue(BigDecimal netUnitValue) {
		this.netUnitValue = netUnitValue;
	}

	public BigDecimal getAnnualIncomeSevenDay() {
		return annualIncomeSevenDay;
	}

	public void setAnnualIncomeSevenDay(BigDecimal annualIncomeSevenDay) {
		this.annualIncomeSevenDay = annualIncomeSevenDay;
	}

	public BigDecimal getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(BigDecimal annualIncome) {
		this.annualIncome = annualIncome;
	}

	public BigDecimal getBasis() {
		return basis;
	}

	public void setBasis(BigDecimal basis) {
		this.basis = basis;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
    

}     				
      			
      