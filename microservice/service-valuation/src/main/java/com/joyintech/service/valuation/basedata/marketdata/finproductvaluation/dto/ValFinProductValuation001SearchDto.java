/*
 * 文件名：ValFinProductValuation001SearchDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.basedata.marketdata.finproductvaluation.dto;

import java.util.Date;

/**
 * 债券估值行情查询条件dto
 * 
 * @author niuqianyu
 * @version 1.0 2018年7月18日
 * @see ValFinProductValuation001SearchDto
 * @since 1.0
 */
public class ValFinProductValuation001SearchDto {

    /**
     * 资产代码
     */
    private String finprodId;

    /**
     * 债券简称
     */
    private String fundtype;

    /**
     * 估值来源
     */
    private String fundAbbr;

    /**
     * 估值日
     */
    private Date valuationStartDate;

    /**
     * 估值日开始时间
     */
    private Date valuationEndDate;

    /**
     * 估值日结束时间
     */
    private String finprodType;

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public String getFundtype() {
		return fundtype;
	}

	public void setFundtype(String fundtype) {
		this.fundtype = fundtype;
	}

	public String getFundAbbr() {
		return fundAbbr;
	}

	public void setFundAbbr(String fundAbbr) {
		this.fundAbbr = fundAbbr;
	}

	

	public Date getValuationStartDate() {
		return valuationStartDate;
	}

	public void setValuationStartDate(Date valuationStartDate) {
		this.valuationStartDate = valuationStartDate;
	}

	public Date getValuationEndDate() {
		return valuationEndDate;
	}

	public void setValuationEndDate(Date valuationEndDate) {
		this.valuationEndDate = valuationEndDate;
	}

	public String getFinprodType() {
		return finprodType;
	}

	public void setFinprodType(String finprodType) {
		this.finprodType = finprodType;
	}
    
    
    
   

}
