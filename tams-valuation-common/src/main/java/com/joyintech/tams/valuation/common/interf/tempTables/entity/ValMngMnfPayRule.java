/*
 * 文件名：ValMngMnfPayRule.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2019年1月23日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

public class ValMngMnfPayRule extends BaseEntity{


	/**
	 * 资产代码
	 */
	private String assetId;
	/**
	 * 数据动作标志
	 */
	private String dataActionSign;
	/**
	 * 生效日
	 */
	private Date effDate;
	/**
	 * 支付方式
	 */
	private String payType;
	/**
	 * 付息频率
	 */
	private String payCycle;
	/**
	 * 营业日准则
	 */
	private String tdayRule;

	/**
	 *  取得 资产代码
	 *  @return 资产代码
	 */
	public String getAssetId() {
	   return assetId;
	}

	/**
	 *  设置 资产代码
	 *  @param assetId 资产代码
	 */
	public void setAssetId( String assetId)  {
	   this.assetId = assetId;
	}

	/**
	 *  取得 数据动作标志
	 *  @return 数据动作标志
	 */
	public String getDataActionSign() {
	   return dataActionSign;
	}

	/**
	 *  设置 数据动作标志
	 *  @param dataActionSign 数据动作标志
	 */
	public void setDataActionSign( String dataActionSign)  {
	   this.dataActionSign = dataActionSign;
	}

	/**
	 *  取得 生效日
	 *  @return 生效日
	 */
	public Date getEffDate() {
	   return effDate;
	}

	/**
	 *  设置 生效日
	 *  @param effDate 生效日
	 */
	public void setEffDate( Date effDate)  {
	   this.effDate = effDate;
	}

	/**
	 *  取得 支付方式
	 *  @return 支付方式
	 */
	public String getPayType() {
	   return payType;
	}

	/**
	 *  设置 支付方式
	 *  @param payType 支付方式
	 */
	public void setPayType( String payType)  {
	   this.payType = payType;
	}

	/**
	 *  取得 付息频率
	 *  @return 付息频率
	 */
	public String getPayCycle() {
	   return payCycle;
	}

	/**
	 *  设置 付息频率
	 *  @param payCycle 付息频率
	 */
	public void setPayCycle( String payCycle)  {
	   this.payCycle = payCycle;
	}

	/**
	 *  取得 营业日准则
	 *  @return 营业日准则
	 */
	public String getTdayRule() {
	   return tdayRule;
	}

	/**
	 *  设置 营业日准则
	 *  @param tdayRule 营业日准则
	 */
	public void setTdayRule( String tdayRule)  {
	   this.tdayRule = tdayRule;
	}

}
