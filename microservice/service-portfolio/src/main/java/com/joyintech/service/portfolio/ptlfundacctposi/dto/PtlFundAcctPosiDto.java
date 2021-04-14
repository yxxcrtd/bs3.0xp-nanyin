/*
 * 文件名：PtlFundAcctPosiDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2019年2月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlfundacctposi.dto;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.PtlCashPosition;

public class PtlFundAcctPosiDto extends PtlCashPosition {

	/**
	 * 组合名称
	 */
	private String portfolioName;
	/**
	 * 开户行
	 */
	private String bankDep;
	/**
	 * 账户名称
	 */
	private String acctName;
	/**
	 * 大额网点号
	 */
	private String largePayno;
	/**
	 * swiftCode
	 */
	private String swiftCode;
	/**
	 * 父节点
	 */
	private String upperPortfolioCode;
	/**
	 * 子节点
	 */
	private String hasChildren;
	
	/**
	 * 
	 */
	private String portfolioId1;
	
	/**
	 * 当日发生额
	 */
	private BigDecimal amount1;
	
	/**
	 * 业务编号
	 */
	private String busiNo;
	
	/**
	 * 业务类型
	 */
	private String busiType;
	/**
	 * 产品/资产代码
	 */
	private String finprodId;
	/**
	 * 产品/资产名称
	 */
	private String finprodName;
	/**
	 * 交易方向
	 */
	private String ps;
	
	private String isSummary;

	public String getFinprodId() {
		return finprodId;
	}

	public PtlFundAcctPosiDto setFinprodId(String finprodId) {
		this.finprodId = finprodId;
		return this;
	}

	public String getFinprodName() {
		return finprodName;
	}

	public PtlFundAcctPosiDto setFinprodName(String finprodName) {
		this.finprodName = finprodName;
		return this;
	}

	/**
	 * 产品/资产代码
	 */
	private String finprodMarketId;
	
	/**
	 * 产品/资产名称
	 */
	private String finprodAbbr;
	
	
	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public String getBankDep() {
		return bankDep;
	}

	public void setBankDep(String bankDep) {
		this.bankDep = bankDep;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getLargePayno() {
		return largePayno;
	}

	public void setLargePayno(String largePayno) {
		this.largePayno = largePayno;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	public String getUpperPortfolioCode() {
		return upperPortfolioCode;
	}

	public void setUpperPortfolioCode(String upperPortfolioCode) {
		this.upperPortfolioCode = upperPortfolioCode;
	}

	public String getHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(String hasChildren) {
		this.hasChildren = hasChildren;
	}

	public String getPortfolioId1() {
		return portfolioId1;
	}

	public void setPortfolioId1(String portfolioId1) {
		this.portfolioId1 = portfolioId1;
	}

	public BigDecimal getAmount1() {
		return amount1;
	}

	public void setAmount1(BigDecimal amount1) {
		this.amount1 = amount1;
	}

	public String getBusiNo() {
		return busiNo;
	}

	public void setBusiNo(String busiNo) {
		this.busiNo = busiNo;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	/**
	 * @return the finprodMarketId
	 */
	public String getFinprodMarketId() {
		return finprodMarketId;
	}

	/**
	 * @param finprodMarketId the finprodMarketId to set
	 */
	public void setFinprodMarketId(String finprodMarketId) {
		this.finprodMarketId = finprodMarketId;
	}

	/**
	 * @return the finprodAbbr
	 */
	public String getFinprodAbbr() {
		return finprodAbbr;
	}

	/**
	 * @param finprodAbbr the finprodAbbr to set
	 */
	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public String getIsSummary() {
		return isSummary;
	}

	public void setIsSummary(String isSummary) {
		this.isSummary = isSummary;
	}
}
