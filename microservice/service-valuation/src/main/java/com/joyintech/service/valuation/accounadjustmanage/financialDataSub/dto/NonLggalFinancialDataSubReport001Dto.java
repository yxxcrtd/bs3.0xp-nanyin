/*
 * 文件名：NonLggalFinancialDataSubReport001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wanwgei1
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.accounadjustmanage.financialDataSub.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

public class NonLggalFinancialDataSubReport001Dto extends BaseEntity implements ITableRow {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 账套代码
	 */
	private String booksetId;
	/**
	 * 产品全称
	 */
	private String finProdName;

	/**
	 * 产品简称
	 */
	private String finProdNameAbbr;

	/**
	 * 产品类型
	 */
	private String finProdType;

	/**
	 * 所属日期
	 */
	private  String happenDate;
	/**
	 * 发生开始日期
	 */
	private  Date happenDateBegin;
	/**
	 * 发生结束日期
	 */
	private Date happenDateEnd;

	/**
	 *净资产
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal netAmt;


	public String getBooksetId() {
		return booksetId;
	}

	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}

	public String getFinProdName() {
		return finProdName;
	}

	public void setFinProdName(String finProdName) {
		this.finProdName = finProdName;
	}

	public String getFinProdNameAbbr() {
		return finProdNameAbbr;
	}

	public void setFinProdNameAbbr(String finProdNameAbbr) {
		this.finProdNameAbbr = finProdNameAbbr;
	}

	public String getFinProdType() {
		return finProdType;
	}

	public void setFinProdType(String finProdType) {
		this.finProdType = finProdType;
	}

	public String getHappenDate() {
		return happenDate;
	}

	public void setHappenDate(String happenDate) {
		this.happenDate = happenDate;
	}

	public Date getHappenDateBegin() {
		return happenDateBegin;
	}

	public void setHappenDateBegin(Date happenDateBegin) {
		this.happenDateBegin = happenDateBegin;
	}

	public Date getHappenDateEnd() {
		return happenDateEnd;
	}

	public void setHappenDateEnd(Date happenDateEnd) {
		this.happenDateEnd = happenDateEnd;
	}

	public BigDecimal getNetAmt() {
		return netAmt;
	}

	public void setNetAmt(BigDecimal netAmt) {
		this.netAmt = netAmt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
