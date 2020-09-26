/*
 * 文件名：ValMngMnfPayPlan.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2019年1月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

public class ValMngMnfPayPlan extends BaseEntity{
	

	/**
	 * 资产代码
	 */
	private String assetId;
	/**
	 * 现金流序号
	 */
	private BigDecimal orderNo;
	/**
	 * 数据动作标志
	 */
	private String dataActionSign;
	/**
	 * 支付方式
	 */
	private String payType;
	/**
	 * 是否已支付
	 */
	private String payStatus;
	/**
	 * 付息日
	 */
	private Date payDate;
	/**
	 * 计息开始日
	 */
	private Date vdate;
	/**
	 * 计息结束日
	 */
	private Date mdate;

	
	/**
	 * 获取 资产代码
	 * @return 资产代码
	 */
	public String getAssetId() {
		return assetId;
	}

	/**
	 * 设置 资产代码
	 * @param assetId 资产代码
	 */
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	/**
	 * 获取 现金流序号
	 * @return 现金流序号
	 */
	public BigDecimal getOrderNo() {
		return orderNo;
	}

	/**
	 * 设置 现金流序号
	 * @param orderNo 现金流序号
	 */
	public void setOrderNo(BigDecimal orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 获取 数据动作标志
	 * @return 数据动作标志
	 */
	public String getDataActionSign() {
		return dataActionSign;
	}

	/**
	 * 设置 数据动作标志
	 * @param dataActionSign 数据动作标志
	 */
	public void setDataActionSign(String dataActionSign) {
		this.dataActionSign = dataActionSign;
	}

	/**
	 * 获取 支付方式
	 * @return 支付方式
	 */
	public String getPayType() {
		return payType;
	}

	/**
	 * 设置 支付方式
	 * @param payType 支付方式
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}

	/**
	 * 获取 是否已支付
	 * @return 是否已支付
	 */
	public String getPayStatus() {
		return payStatus;
	}

	/**
	 * 设置 是否已支付
	 * @param payStatus 是否已支付
	 */
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	/**
	 * 获取 付息日
	 * @return 付息日
	 */
	public Date getPayDate() {
		return payDate;
	}

	/**
	 * 设置 付息日
	 * @param payDate 付息日
	 */
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	/**
	 *  取得 计息开始日
	 *  @return 计息开始日
	 */
	public Date getVdate() {
	   return vdate;
	}

	/**
	 *  设置 计息开始日
	 *  @param vdate 计息开始日
	 */
	public void setVdate( Date vdate)  {
	   this.vdate = vdate;
	}

	/**
	 *  取得 计息结束日
	 *  @return 计息结束日
	 */
	public Date getMdate() {
	   return mdate;
	}

	/**
	 *  设置 计息结束日
	 *  @param mdate 计息结束日
	 */
	public void setMdate( Date mdate)  {
	   this.mdate = mdate;
	}



}
