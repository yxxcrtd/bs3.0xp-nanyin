package com.joyintech.interf.bankname.sale.dto;

import java.util.Date;

public class InfProdCommonDto {
	private String finprodMarketId;
	private String finprodName	  ;
	private String finprodAbbr	  ;
	private String finprodId	  ;
	private String unitvalueDec	  ;
	private String purSpeed		  ;
	private String ccy			  ;
	private Date vDate		  ;
	private Date mDate		  ;
	private String isCycle		  ;
	private String operationType  ;
	private String profitType	  ;
	private String portfolioId	  ;
	private String calendarId	  ;
	//产品经理
	private String prodmanager    ;
	//计息基础
	private String basis    ;
	public String getFinprodMarketId() {
		return finprodMarketId;
	}
	public void setFinprodMarketId(String finprodMarketId) {
		this.finprodMarketId = finprodMarketId;
	}
	public String getFinprodName() {
		return finprodName;
	}
	public void setFinprodName(String finprodName) {
		this.finprodName = finprodName;
	}
	public String getFinprodAbbr() {
		return finprodAbbr;
	}
	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}
	public String getFinprodId() {
		return finprodId;
	}
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}
	public String getUnitvalueDec() {
		return unitvalueDec;
	}
	public void setUnitvalueDec(String unitvalueDec) {
		this.unitvalueDec = unitvalueDec;
	}
	public String getPurSpeed() {
		return purSpeed;
	}
	public void setPurSpeed(String purSpeed) {
		this.purSpeed = purSpeed;
	}
	public String getCcy() {
		return ccy;
	}
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	public Date getvDate() {
		return vDate;
	}
	public void setvDate(Date vDate) {
		this.vDate = vDate;
	}
	public Date getmDate() {
		return mDate;
	}
	public void setmDate(Date mDate) {
		this.mDate = mDate;
	}
	public String getIsCycle() {
		return isCycle;
	}
	public void setIsCycle(String isCycle) {
		this.isCycle = isCycle;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getProfitType() {
		return profitType;
	}
	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}
	public String getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}
	public String getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}
	public String getProdmanager() {
		return prodmanager;
	}
	public void setProdmanager(String prodmanager) {
		this.prodmanager = prodmanager;
	}
	public String getBasis() {
		return basis;
	}
	public void setBasis(String basis) {
		this.basis = basis;
	}
}
