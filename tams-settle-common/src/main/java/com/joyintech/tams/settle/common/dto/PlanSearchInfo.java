/*
 * 文件名：PlanSearchInfo.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年7月8日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */
 
package com.joyintech.tams.settle.common.dto;

/**
 * 方案查询信息
 * @author 王波
 * @version 1.0 2020年7月8日
 * @see PlanSearchInfo
 * @since 1.0
 */
public class PlanSearchInfo {

	/**
	 * 交易平台
	 */
	private String tradePlatform;

	/**
	 * 交易场所
	 */
	private String tradeMarket;

	/**
	 * 费用类型
	 */
	private String feeType;

	/**
	 * 登记托管机构
	 */
	private String registOrg;

	/**
	 * 交易品种
	 */
	private String tradeType;

	/**
	 * 交易方向
	 */
	private String ps;

	/**
	 * 期限品种
	 */
	private String termType;

	/**
	 * 证券账户代码
	 */
	private String secAcctId;

	/**
	 * 券商代码
	 */
	private String brokerageNo;

	/**
	 * 证券分类1
	 */
	private String assetClassify1;

	/**
	 * 证券分类2
	 */
	private String assetClassify2;

	/**
	 * 组合代码
	 */
	private String portfolioId;

	public String getTradePlatform() {
		return tradePlatform;
	}

	public void setTradePlatform(String tradePlatform) {
		this.tradePlatform = tradePlatform;
	}

	public String getTradeMarket() {
		return tradeMarket;
	}

	public void setTradeMarket(String tradeMarket) {
		this.tradeMarket = tradeMarket;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getRegistOrg() {
		return registOrg;
	}

	public void setRegistOrg(String registOrg) {
		this.registOrg = registOrg;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	public String getSecAcctId() {
		return secAcctId;
	}

	public void setSecAcctId(String secAcctId) {
		this.secAcctId = secAcctId;
	}

	public String getBrokerageNo() {
		return brokerageNo;
	}

	public void setBrokerageNo(String brokerageNo) {
		this.brokerageNo = brokerageNo;
	}

	public String getAssetClassify1() {
		return assetClassify1;
	}

	public void setAssetClassify1(String assetClassify1) {
		this.assetClassify1 = assetClassify1;
	}

	public String getAssetClassify2() {
		return assetClassify2;
	}

	public void setAssetClassify2(String assetClassify2) {
		this.assetClassify2 = assetClassify2;
	}

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}
}
