/*
 * 文件名：BokValTableDataForIndexDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年12月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.batch.disclosure.informationdisclourse.disreportprodindex.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 估值表数据 - 针对产品指标
 * 
 * @author yangjian
 * @version 1.0 2018年12月21日
 * @see BokValTableDataForIndexDto
 * @since 1.0
 */
public class BokValTableDataForIndexDto {

	/**
	 * 账套id
	 */
	private String booksetId;

	/**
	 * 估值日期
	 */
	private Date valDate;

	/**
	 * 科目代码
	 */
	private String subjectNo;

	/**
	 * 科目名称
	 */
	private String subjectName;

	/**
	 * 成本
	 */
	private BigDecimal cost;

	/**
	 * 估值
	 */
	private BigDecimal marketValue;

	public String getBooksetId() {
		return booksetId;
	}

	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}

	public Date getValDate() {
		return valDate;
	}

	public void setValDate(Date valDate) {
		this.valDate = valDate;
	}

	public String getSubjectNo() {
		return subjectNo;
	}

	public void setSubjectNo(String subjectNo) {
		this.subjectNo = subjectNo;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(BigDecimal marketValue) {
		this.marketValue = marketValue;
	}

}
