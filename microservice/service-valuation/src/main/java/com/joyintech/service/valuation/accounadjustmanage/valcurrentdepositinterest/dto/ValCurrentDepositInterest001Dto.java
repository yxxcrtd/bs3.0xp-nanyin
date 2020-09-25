/*
 * 文件名：ValCurrentDepositInterest001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2019年1月30日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.valcurrentdepositinterest.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.validation.annotation.NotEmpty;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author zhangzhao
 * @version 1.0 2019年1月30日
 * @see ValCurrentDepositInterest001Dto
 * @since 1.0
 */
public class ValCurrentDepositInterest001Dto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -2016667813772620305L;

    /**
     * 查询开始日
     */
    private String queryStart;

    /**
     * 查询结束日
     */
    private String queryEnd;
    /**
     * 年
     */
    @NotEmpty(fieldName = "ValCurrentDepositInterest.year")
    private String year;

    /**
     * 季度
     */
    @NotEmpty(fieldName = "ValCurrentDepositInterest.quarter")
    private String quarter;

    /**
     *  产品简称	
     */
    private String prodId;

    /**
     *  产品代码
     */
    private String prodName;

    /**
     *  结息金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal interestAmount;

    /**
     *  现金余额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal cashBalance;

    /**
     *  产品状态
     */
    private String status;

	

	

	/**
	 * 获取 queryStart
	 * @return queryStart.
	 */
	public String getQueryStart() {
		return queryStart;
	}

	/**
	 * 设置 queryStart
	 * @param queryStart queryStart
	 */
	public void setQueryStart(String queryStart) {
		this.queryStart = queryStart;
	}

	/**
	 * 获取 queryEnd
	 * @return queryEnd.
	 */
	public String getQueryEnd() {
		return queryEnd;
	}

	/**
	 * 设置 queryEnd
	 * @param queryEnd queryEnd
	 */
	public void setQueryEnd(String queryEnd) {
		this.queryEnd = queryEnd;
	}


	/**
	 * 获取 year
	 * @return year.
	 */
	public String getYear() {
		return year;
	}

	/**
	 * 设置 year
	 * @param year year
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * 获取 quarter
	 * @return quarter.
	 */
	public String getQuarter() {
		return quarter;
	}

	/**
	 * 设置 quarter
	 * @param quarter quarter
	 */
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}


	/**
	 * 获取 prodId
	 * @return prodId.
	 */
	public String getProdId() {
		return prodId;
	}

	/**
	 * 设置 prodId
	 * @param prodId prodId
	 */
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}


	/**
	 * 获取 prodName
	 * @return prodName.
	 */
	public String getProdName() {
		return prodName;
	}

	/**
	 * 设置 prodName
	 * @param prodName prodName
	 */
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	/**
	 * 获取 interestAmount
	 * @return interestAmount.
	 */
	public BigDecimal getInterestAmount() {
		return interestAmount;
	}

	/**
	 * 设置 interestAmount
	 * @param interestAmount interestAmount
	 */
	public void setInterestAmount(BigDecimal interestAmount) {
		this.interestAmount = interestAmount;
	}

	/**
	 * 获取 cashBalance
	 * @return cashBalance.
	 */
	public BigDecimal getCashBalance() {
		return cashBalance;
	}

	/**
	 * 设置 cashBalance
	 * @param cashBalance cashBalance
	 */
	public void setCashBalance(BigDecimal cashBalance) {
		this.cashBalance = cashBalance;
	}

	/**
	 * 获取 status
	 * @return status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 设置 status
	 * @param status status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	

    }
