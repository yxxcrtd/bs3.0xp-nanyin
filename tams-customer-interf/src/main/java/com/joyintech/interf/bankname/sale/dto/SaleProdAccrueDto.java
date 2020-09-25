package com.joyintech.interf.bankname.sale.dto;


import java.math.BigDecimal;
import java.util.Date;

public class SaleProdAccrueDto {
	
	private String finprodCode; //产品代码
	private String  prodstyle; //产品款型
	private Date valdate; //估值日期
	private BigDecimal yield; //预期收益率
	private BigDecimal newprofit; //当日新增收益
	private BigDecimal unpayinterest; //累计未付利息金额
	
	
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
	public BigDecimal getYield() {
		return yield;
	}
	public void setYield(BigDecimal yield) {
		this.yield = yield;
	}
	public BigDecimal getNewprofit() {
		return newprofit;
	}
	public void setNewprofit(BigDecimal newprofit) {
		this.newprofit = newprofit;
	}
	public BigDecimal getUnpayinterest() {
		return unpayinterest;
	}
	public void setUnpayinterest(BigDecimal unpayinterest) {
		this.unpayinterest = unpayinterest;
	}
	public String getFinprodCode() {
		return finprodCode;
	}
	public void setFinprodCode(String finprodCode) {
		this.finprodCode = finprodCode;
	}
	 
	 
}
