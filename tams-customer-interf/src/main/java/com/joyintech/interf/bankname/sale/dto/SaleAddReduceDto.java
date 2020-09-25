package com.joyintech.interf.bankname.sale.dto;


import java.math.BigDecimal;
import java.util.Date;

public class SaleAddReduceDto {
	
	
	private String finprodCode; //产品代码
	
	private Date tradedate; //确认日
	
	private Date cpricedate; //净值日期
	
	private BigDecimal valuation; //单位净值
	
	private String prodstyle; //产品款型
	
	private BigDecimal shareamt; //交易份额
	
	private BigDecimal tradeamt; //交易金额
	
	private String ps; //交易类型

	
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

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public String getFinprodCode() {
		return finprodCode;
	}

	public void setFinprodCode(String finprodCode) {
		this.finprodCode = finprodCode;
	}
	
}
