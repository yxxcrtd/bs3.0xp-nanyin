package com.joyintech.interf.bankname.sale.dto;


import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

public class SaleAlloDto extends BaseEntity{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 产品代码
     */
	private String finprodCode;
	/**
	 * 交易类型
	 */
	private String ps;
	/**
	 * 确认日
	 */
	private Date tradedate;
	/**
	 * 净值日期
	 */
	private Date cpricedate;
	/**
	 * 单位净值
	 */
	private BigDecimal valuation;
	/**
	 * 产品款型
	 */
	private String prodstyle;
	/**
	 * 交易份额
	 */
	private BigDecimal shareamt;
	/**
	 * 交易金额
	 */
	private BigDecimal tradeamt;
	/**
	 * 认申购费用
	 */
	private String fee;
	/**
	 * 收益类型
	 */
	private String profitType;
	
	
	public String getFinprodCode() {
		return finprodCode;
	}
	public void setFinprodCode(String finprodCode) {
		this.finprodCode = finprodCode;
	}
	public String getPs() {
		return ps;
	}
	public void setPs(String ps) {
		this.ps = ps;
	}
	public Date getTradedate() {
		return tradedate;
	}
	public void setTradedate(Date tradedate) {
		this.tradedate = tradedate;
	}
	public Date getCpricedate() {
		return cpricedate;
	}
	public void setCpricedate(Date cpricedate) {
		this.cpricedate = cpricedate;
	}
	public BigDecimal getValuation() {
		return valuation;
	}
	public void setValuation(BigDecimal valuation) {
		this.valuation = valuation;
	}
	public String getProdstyle() {
		return prodstyle;
	}
	public void setProdstyle(String prodstyle) {
		this.prodstyle = prodstyle;
	}
	public BigDecimal getShareamt() {
		return shareamt;
	}
	public void setShareamt(BigDecimal shareamt) {
		this.shareamt = shareamt;
	}
	public BigDecimal getTradeamt() {
		return tradeamt;
	}
	public void setTradeamt(BigDecimal tradeamt) {
		this.tradeamt = tradeamt;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getProfitType() {
		return profitType;
	}
	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}
	
}
