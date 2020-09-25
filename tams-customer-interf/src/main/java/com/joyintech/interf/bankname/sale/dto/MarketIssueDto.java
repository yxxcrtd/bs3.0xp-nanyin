package com.joyintech.interf.bankname.sale.dto;


import java.math.BigDecimal;
import java.util.Date;

public class MarketIssueDto {
	
	
	private String finprodid;
	private String prodstyle;
	private Date valdate;
	private BigDecimal valuation;
	private BigDecimal day7yearyield;
	private BigDecimal tdyunpaidintincexp;
	private BigDecimal totalvalue;
	private BigDecimal yield;
	public String getFinprodid() {
		return finprodid;
	}
	public void setFinprodid(String finprodid) {
		this.finprodid = finprodid;
	}
	public String getProdstyle() {
		return prodstyle;
	}
	public void setProdstyle(String prodstyle) {
		this.prodstyle = prodstyle;
	}
	public Date getValdate() {
		return valdate;
	}
	public void setValdate(Date valdate) {
		this.valdate = valdate;
	}
	public BigDecimal getValuation() {
		return valuation;
	}
	public void setValuation(BigDecimal valuation) {
		this.valuation = valuation;
	}
	public BigDecimal getDay7yearyield() {
		return day7yearyield;
	}
	public void setDay7yearyield(BigDecimal day7yearyield) {
		this.day7yearyield = day7yearyield;
	}
	public BigDecimal getTdyunpaidintincexp() {
		return tdyunpaidintincexp;
	}
	public void setTdyunpaidintincexp(BigDecimal tdyunpaidintincexp) {
		this.tdyunpaidintincexp = tdyunpaidintincexp;
	}
	public BigDecimal getTotalvalue() {
		return totalvalue;
	}
	public void setTotalvalue(BigDecimal totalvalue) {
		this.totalvalue = totalvalue;
	}
	public BigDecimal getYield() {
		return yield;
	}
	public void setYield(BigDecimal yield) {
		this.yield = yield;
	}
	
	
}
