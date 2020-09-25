/*
 * 文件名：PtlSubFundsAllocateDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年8月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlsubfundsmange.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;

public class PtlSubFundsAllocateDto extends BaseEntity {

	/**
	 * 汇总交易编号
	 */
	private String totleTradeId;
	/**
	 * 交易编号
	 */
	private String tradeId;
	/**
	 * 组合代码
	 */
	private String portfolioId;
	private String parentPortfolioId;
	
	public String getParentPortfolioId() {
		return parentPortfolioId;
	}

	public void setParentPortfolioId(String parentPortfolioId) {
		this.parentPortfolioId = parentPortfolioId;
	}

	/**
	 * 组合名称
	 */
	private String portfolioName;
	/**
	 * 子组合代码
	 */
	private String subPortfolioId;
	/**
	 * 子组合名称
	 */
	private String subPortfolioName;
	/**
	 * 继承比例
	 */
	private BigDecimal disPercent;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 调配日期
	 */
	private String settleDate;
	/**
	 * 币种
	 */
	private String ccy;
	/**
	 * 调配金额
	 */
	private BigDecimal tradeAmt;
	/**
	 * 调配备注
	 */
	private String deployrRemark;

	/**
	 * 实际现金
	 */
	private BigDecimal actualAmt;
	/**
	 * 可用现金
	 */
	private BigDecimal avaAmt;
	/**
	 * 调配后实际现金
	 */
	private BigDecimal ajustActualAmt;
	/**
	 * 调配后可用现金
	 */
	private BigDecimal ajustAvaAmt;
	
	
	public BigDecimal getActualAmt() {
		return actualAmt;
	}

	public void setActualAmt(BigDecimal actualAmt) {
		this.actualAmt = actualAmt;
	}

	public BigDecimal getAvaAmt() {
		return avaAmt;
	}

	public void setAvaAmt(BigDecimal avaAmt) {
		this.avaAmt = avaAmt;
	}

	public BigDecimal getAjustActualAmt() {
		return ajustActualAmt;
	}

	public void setAjustActualAmt(BigDecimal ajustActualAmt) {
		this.ajustActualAmt = ajustActualAmt;
	}

	public BigDecimal getAjustAvaAmt() {
		return ajustAvaAmt;
	}

	public void setAjustAvaAmt(BigDecimal ajustAvaAmt) {
		this.ajustAvaAmt = ajustAvaAmt;
	}

	public String getTotleTradeId() {
		return totleTradeId;
	}

	public void setTotleTradeId(String totleTradeId) {
		this.totleTradeId = totleTradeId;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
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

	public String getSubPortfolioId() {
		return subPortfolioId;
	}

	public void setSubPortfolioId(String subPortfolioId) {
		this.subPortfolioId = subPortfolioId;
	}

	public String getSubPortfolioName() {
		return subPortfolioName;
	}

	public void setSubPortfolioName(String subPortfolioName) {
		this.subPortfolioName = subPortfolioName;
	}

	public BigDecimal getDisPercent() {
		return disPercent;
	}

	public void setDisPercent(BigDecimal disPercent) {
		this.disPercent = disPercent;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}

	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	public String getDeployrRemark() {
		return deployrRemark;
	}

	public void setDeployrRemark(String deployrRemark) {
		this.deployrRemark = deployrRemark;
	}

}
