/*
 * 文件名：ValMngMnfBaseInfo.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2019年1月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

public class ValMngMnfBaseInfo extends BaseEntity{
	
	/**
	 * 资产代码
	 */
	private String assetId;
	/**
	 * 数据动作标志
	 */
	private String dataActionSign;
	/**
	 * 市场代码
	 */
	private String marketCode;
	/**
	 * 资产名称
	 */
	private String assetName;
	/**
	 * 资产类型
	 */
	private String assetType;
	/**
	 * 发行日
	 */
	private Date idate;
	/**
	 * 管理人
	 */
	private String managerId;
	/**
	 * 境内外
	 */
	private String blnArea;
	/**
	 * 币种
	 */
	private String ccy;
	/**
	 * 交易场所
	 */
	private String tradeMarket;
	/**
	 * 托管人
	 */
	private String trustee;
	/**
	 * 结转频率
	 */
	private String carryoverFreq;
	/**
	 * 申购确认速度
	 */
	private Long purSpeed;
	/**
	 * 赎回确认速度
	 */
	private Long redSpeed;
	/**
	 * 本金收益特征
	 */
	private String capiProfFeature;
	/**
	 * 是否按日复利
	 */
	private String isCompoundInt;
	/**
	 * 净值数据延迟天数
	 */
	private String intAccrualMode;

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
	 *  取得 市场代码
	 *  @return 市场代码
	 */
	public String getMarketCode() {
	   return marketCode;
	}

	/**
	 *  设置 市场代码
	 *  @param marketCode 市场代码
	 */
	public void setMarketCode( String marketCode)  {
	   this.marketCode = marketCode;
	}

	/**
	 *  取得 资产名称
	 *  @return 资产名称
	 */
	public String getAssetName() {
	   return assetName;
	}

	/**
	 *  设置 资产名称
	 *  @param assetName 资产名称
	 */
	public void setAssetName( String assetName)  {
	   this.assetName = assetName;
	}

	/**
	 *  取得 资产类型
	 *  @return 资产类型
	 */
	public String getAssetType() {
	   return assetType;
	}

	/**
	 *  设置 资产类型
	 *  @param assetType 资产类型
	 */
	public void setAssetType( String assetType)  {
	   this.assetType = assetType;
	}

	/**
	 *  取得 发行日
	 *  @return 发行日
	 */
	public Date getIdate() {
	   return idate;
	}

	/**
	 *  设置 发行日
	 *  @param idate 发行日
	 */
	public void setIdate( Date idate)  {
	   this.idate = idate;
	}

	/**
	 *  取得 管理人
	 *  @return 管理人
	 */
	public String getManagerId() {
	   return managerId;
	}

	/**
	 *  设置 管理人
	 *  @param managerId 管理人
	 */
	public void setManagerId( String managerId)  {
	   this.managerId = managerId;
	}

	/**
	 *  取得 境内外
	 *  @return 境内外
	 */
	public String getBlnArea() {
	   return blnArea;
	}

	/**
	 *  设置 境内外
	 *  @param blnArea 境内外
	 */
	public void setBlnArea( String blnArea)  {
	   this.blnArea = blnArea;
	}

	/**
	 *  取得 币种
	 *  @return 币种
	 */
	public String getCcy() {
	   return ccy;
	}

	/**
	 *  设置 币种
	 *  @param ccy 币种
	 */
	public void setCcy( String ccy)  {
	   this.ccy = ccy;
	}

	/**
	 *  取得 交易场所
	 *  @return 交易场所
	 */
	public String getTradeMarket() {
	   return tradeMarket;
	}

	/**
	 *  设置 交易场所
	 *  @param tradeMarket 交易场所
	 */
	public void setTradeMarket( String tradeMarket)  {
	   this.tradeMarket = tradeMarket;
	}

	/**
	 *  取得 托管人
	 *  @return 托管人
	 */
	public String getTrustee() {
	   return trustee;
	}

	/**
	 *  设置 托管人
	 *  @param trustee 托管人
	 */
	public void setTrustee( String trustee)  {
	   this.trustee = trustee;
	}

	/**
	 *  取得 结转频率
	 *  @return 结转频率
	 */
	public String getCarryoverFreq() {
	   return carryoverFreq;
	}

	/**
	 *  设置 结转频率
	 *  @param carryoverFreq 结转频率
	 */
	public void setCarryoverFreq( String carryoverFreq)  {
	   this.carryoverFreq = carryoverFreq;
	}

	/**
	 *  取得 申购确认速度
	 *  @return 申购确认速度
	 */
	public Long getPurSpeed() {
	   return purSpeed;
	}

	/**
	 *  设置 申购确认速度
	 *  @param purSpeed 申购确认速度
	 */
	public void setPurSpeed( Long purSpeed)  {
	   this.purSpeed = purSpeed;
	}

	/**
	 *  取得 赎回确认速度
	 *  @return 赎回确认速度
	 */
	public Long getRedSpeed() {
	   return redSpeed;
	}

	/**
	 *  设置 赎回确认速度
	 *  @param redSpeed 赎回确认速度
	 */
	public void setRedSpeed( Long redSpeed)  {
	   this.redSpeed = redSpeed;
	}

	/**
	 *  取得 本金收益特征
	 *  @return 本金收益特征
	 */
	public String getCapiProfFeature() {
	   return capiProfFeature;
	}

	/**
	 *  设置 本金收益特征
	 *  @param capiProfFeature 本金收益特征
	 */
	public void setCapiProfFeature( String capiProfFeature)  {
	   this.capiProfFeature = capiProfFeature;
	}

	/**
	 *  取得 是否按日复利
	 *  @return 是否按日复利
	 */
	public String getIsCompoundInt() {
	   return isCompoundInt;
	}

	/**
	 *  设置 是否按日复利
	 *  @param isCompoundInt 是否按日复利
	 */
	public void setIsCompoundInt( String isCompoundInt)  {
	   this.isCompoundInt = isCompoundInt;
	}

	/**
	 *  取得 净值数据延迟天数
	 *  @return 净值数据延迟天数
	 */
	public String getIntAccrualMode() {
	   return intAccrualMode;
	}

	/**
	 *  设置 净值数据延迟天数
	 *  @param intAccrualMode 净值数据延迟天数
	 */
	public void setIntAccrualMode( String intAccrualMode)  {
	   this.intAccrualMode = intAccrualMode;
	}

	//add by liupengfei for 多级法人改造 S
	/**
	 * 所属机构
	 */
	private String belongInstitutionCode;

	/**
	 * Gets the value of belongInstitutionCode.
	 *
	 * @return the value of belongInstitutionCode
	 */
	@Override
	public String getBelongInstitutionCode() {
		return belongInstitutionCode;
	}

	/**
	 * Sets the belongInstitutionCode.
	 *
	 * <p>You can use getBelongInstitutionCode() to get the value of belongInstitutionCode</p>
	 *
	 * @param belongInstitutionCode belongInstitutionCode
	 */
	@Override
	public void setBelongInstitutionCode(String belongInstitutionCode) {
		this.belongInstitutionCode = belongInstitutionCode;
	}
	//add by liupengfei for 多级法人改造 E

}
