package com.joyintech.interf.bankname.sale.dto;


import java.math.BigDecimal;
import java.util.Date;

public class SaleBonusCollectDto {
	
	private String finprodCode; //产品代码
	private String prodstyle; //产品款型
	private Date deldate; //除权日
	private Date cpricedate; //净值日期
	private BigDecimal valuation; //单位净值
	private Date confirmdate; //确认日
	private Date bonusdate; //红利下发日/确认日
	private BigDecimal bonusamt; //派现红利金额
	private BigDecimal rebonusamt; //再投资红利金额
	private BigDecimal rebonusprice; //再投资价格
	private BigDecimal redealqty; //再投资份额
	private Date enddate; //本次计提结束日
	private Date paydate; //支付日
	private BigDecimal interest; //实付客户利息
	private String profitType; //收益类型
	
	public String getProdstyle() {
		return prodstyle;
	}
	public void setProdstyle(String prodstyle) {
		this.prodstyle = prodstyle;
	}
	public Date getDeldate() {
		return deldate;
	}
	public void setDeldate(Date deldate) {
		this.deldate = deldate;
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
	public Date getConfirmdate() {
		return confirmdate;
	}
	public void setConfirmdate(Date confirmdate) {
		this.confirmdate = confirmdate;
	}
	public Date getBonusdate() {
		return bonusdate;
	}
	public void setBonusdate(Date bonusdate) {
		this.bonusdate = bonusdate;
	}
	public BigDecimal getBonusamt() {
		return bonusamt;
	}
	public void setBonusamt(BigDecimal bonusamt) {
		this.bonusamt = bonusamt;
	}
	public BigDecimal getRebonusamt() {
		return rebonusamt;
	}
	public void setRebonusamt(BigDecimal rebonusamt) {
		this.rebonusamt = rebonusamt;
	}
	public BigDecimal getRebonusprice() {
		return rebonusprice;
	}
	public void setRebonusprice(BigDecimal rebonusprice) {
		this.rebonusprice = rebonusprice;
	}
	public BigDecimal getRedealqty() {
		return redealqty;
	}
	public void setRedealqty(BigDecimal redealqty) {
		this.redealqty = redealqty;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public Date getPaydate() {
		return paydate;
	}
	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}
	public BigDecimal getInterest() {
		return interest;
	}
	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}
	public String getFinprodCode() {
		return finprodCode;
	}
	public void setFinprodCode(String finprodCode) {
		this.finprodCode = finprodCode;
	}
	public String getProfitType() {
		return profitType;
	}
	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}
	
	
	
}
