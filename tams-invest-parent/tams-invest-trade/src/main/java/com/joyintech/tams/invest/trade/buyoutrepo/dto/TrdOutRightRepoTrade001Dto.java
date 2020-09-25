/*
 * 文件名：TrdOutRightRepoTrade001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2019年01月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.trade.buyoutrepo.dto;

import com.joyintech.tams.common.base.entity.TrdProductDeal;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

public class TrdOutRightRepoTrade001Dto extends TrdProductDeal {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8621421223620044916L;

	/**
	 * 有效状态
	 */
	private String effectStatus;
	
	/**
	 * 委托代码
	 */
	private String entrustId;
	
	/**
	 * 组合代码
	 */
	private String portfolioId;
	
	/**
	 * 组合名称
	 */
	private String portfolioName;
	
	/**
	 * 资产一级分类
	 */
	private String assetOneType;
	
	/**
	 * 期限品种
	 */
	private String termType;
	
	/**
	 * 数据状态
	 */
	private String workflowStatus;
	
	/**
	 * 首次净价
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal fCprice;
	
	/**
	 * 首次收益率
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal fYield;
	
	/**
	 * 到期净价
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal mCprice;
	
	/**
	 * 到期收益率
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal mYield;
	
	/**
	 * 到期结算金额
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal mSettleAmt;
	
	/**
	 * 标的资产代码
	 */
	private String finpprodId;
	
	/**
	 * 标的资产名称
	 */
	private String finprodNAME;

	/**
	 * 简称
	 */
	private String finprodAbbr;
	
	/**
	 * 标的资产代码展示
	 */
	private String finprodIdShow;

	public String getFinprodNAME() {
		return finprodNAME;
	}

	public void setFinprodNAME(String finprodNAME) {
		this.finprodNAME = finprodNAME;
	}
	
	public String getFinprodAbbr() {
		return finprodAbbr;
	}

	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}

	public String getFinprodIdShow() {
		return finprodIdShow;
	}

	public void setFinprodIdShow(String finprodIdShow) {
		this.finprodIdShow = finprodIdShow;
	}

	public BigDecimal getfCprice() {
		return fCprice;
	}

	public void setfCprice(BigDecimal fCprice) {
		this.fCprice = fCprice;
	}

	public BigDecimal getfYield() {
		return fYield;
	}

	public void setfYield(BigDecimal fYield) {
		this.fYield = fYield;
	}

	public BigDecimal getmCprice() {
		return mCprice;
	}

	public void setmCprice(BigDecimal mCprice) {
		this.mCprice = mCprice;
	}

	public BigDecimal getmYield() {
		return mYield;
	}

	public void setmYield(BigDecimal mYield) {
		this.mYield = mYield;
	}

	public BigDecimal getmSettleAmt() {
		return mSettleAmt;
	}

	public void setmSettleAmt(BigDecimal mSettleAmt) {
		this.mSettleAmt = mSettleAmt;
	}

	public String getFinpprodId() {
		return finpprodId;
	}

	public void setFinpprodId(String finpprodId) {
		this.finpprodId = finpprodId;
	}

	public String getEffectStatus() {
		return effectStatus;
	}
	
	public String getEffectStatusStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.交易流程状态, effectStatus);
	}

	public void setEffectStatus(String effectStatus) {
		this.effectStatus = effectStatus;
	}

	public String getEntrustId() {
		return entrustId;
	}

	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public String getAssetOneType() {
		return assetOneType;
	}

	public void setAssetOneType(String assetOneType) {
		this.assetOneType = assetOneType;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	public String getWorkflowStatus() {
		return workflowStatus;
	}

	public void setWorkflowStatus(String workflowStatus) {
		this.workflowStatus = workflowStatus;
	}
	
	public String getTradeMarketStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.交易场所, super.getTradeMarket());
	}
	
	public String getPsStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向, super.getPs());
	}
	
	public String getCcyStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
	}
	
	public String getTradeDateStr(){
		return DateUtil.format(this.getTradeDate());
	}
}
