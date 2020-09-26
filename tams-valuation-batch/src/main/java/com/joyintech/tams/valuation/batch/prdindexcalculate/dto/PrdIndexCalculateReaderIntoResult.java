/*
 * 文件名：PrdIndexCalculateReaderResultDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年12月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.prdindexcalculate.dto;

import java.io.Serializable;
import java.util.Date;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * 信息披露-产品指标计算(数据插入)-reader返回值
 * 
 * @author yangjian
 * @version 1.0 2018年12月21日
 * @see PrdIndexCalculateReaderIntoResult
 * @since 1.0
 */
public class PrdIndexCalculateReaderIntoResult extends NeedOperateEntity implements Serializable  {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 账套代码
	 */
	private String booksetId;

	/**
	 * 账套核算主体代码 - 产品代码
	 */
	private String prdId;

	/**
	 * 入账日
	 */
	private Date bookDate;

	/**
	 * 核算方式
	 */
	private String acctMode;

	/**
	 * 本位币
	 */
	private String standardCcy;

	public String getBooksetId() {
		return booksetId;
	}

	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}

	public String getPrdId() {
		return prdId;
	}

	public void setPrdId(String prdId) {
		this.prdId = prdId;
	}

	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	public String getAcctMode() {
		return acctMode;
	}

	public void setAcctMode(String acctMode) {
		this.acctMode = acctMode;
	}

	public String getStandardCcy() {
		return standardCcy;
	}

	public void setStandardCcy(String standardCcy) {
		this.standardCcy = standardCcy;
	}

}
