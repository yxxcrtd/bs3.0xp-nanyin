package com.joyintech.interf.bankname.sale.dto;


import java.math.BigDecimal;
import java.util.Date;

public class SaleRedeemDto {
	
	 
	private String finprodCode;; //产品代码
	  
	private String ps ; //交易类型
	
	private Date tradedate; //确认日
	
	private Date subscribepaydate; //本金支付日
	
	private Date paydate; //支付日
	
	private Date profitpaydate; // 收益支付日
	
	private Date cpricedate; //净值日期
	
	private BigDecimal valuation; //单位净值
	
	private String prodstyle; //产品款型
	
	private BigDecimal shareamt; //交易份额
	
	private BigDecimal tradeamt; //交易金额（含交易费）
	
	private BigDecimal backfee; //赎回费
	
	private BigDecimal backfeeprod; //赎回费（产品留存）
	
	private BigDecimal sgfee; //申购费（后端）
	
	private BigDecimal netamt; //净金额
	
	private BigDecimal redprofit; //赎回收益
	
	private BigDecimal redcost; //赎回成本
	
	private BigDecimal profitcustomer; //实付客户收益

	private String profitType; //收益类型
	

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

	public Date getSubscribepaydate() {
		return subscribepaydate;
	}

	public void setSubscribepaydate(Date subscribepaydate) {
		this.subscribepaydate = subscribepaydate;
	}

	public Date getPaydate() {
		return paydate;
	}

	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}

	public Date getProfitpaydate() {
		return profitpaydate;
	}

	public void setProfitpaydate(Date profitpaydate) {
		this.profitpaydate = profitpaydate;
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

	public BigDecimal getBackfee() {
		return backfee;
	}

	public void setBackfee(BigDecimal backfee) {
		this.backfee = backfee;
	}

	public BigDecimal getBackfeeprod() {
		return backfeeprod;
	}

	public void setBackfeeprod(BigDecimal backfeeprod) {
		this.backfeeprod = backfeeprod;
	}

	public BigDecimal getSgfee() {
		return sgfee;
	}

	public void setSgfee(BigDecimal sgfee) {
		this.sgfee = sgfee;
	}

	public BigDecimal getNetamt() {
		return netamt;
	}

	public void setNetamt(BigDecimal netamt) {
		this.netamt = netamt;
	}

	public BigDecimal getRedprofit() {
		return redprofit;
	}

	public void setRedprofit(BigDecimal redprofit) {
		this.redprofit = redprofit;
	}

	public BigDecimal getRedcost() {
		return redcost;
	}

	public void setRedcost(BigDecimal redcost) {
		this.redcost = redcost;
	}

	public BigDecimal getProfitcustomer() {
		return profitcustomer;
	}

	public void setProfitcustomer(BigDecimal profitcustomer) {
		this.profitcustomer = profitcustomer;
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
