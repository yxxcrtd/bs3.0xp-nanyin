package com.jixu.mvc.control.model;

import java.util.Date;

public class MarketFxRateModel {
	private int fxid;
	private int sourceid;
	private String soucename;
	private String curID;
	private String currency;
	private String baseCurID;
	private String baseCurrency;
	private Date rateDate;
	private double rate;

	public MarketFxRateModel(int fxid,int sourceid,String sourcename,String curID,String baseCurID,Date rateDate,double rate) {
		setFxid(fxid);
		setSourceid(sourceid);
		setSoucename(sourcename);
		setCurID(curID);
		setBaseCurID(baseCurID);
		setRateDate(rateDate);
		setRate(rate);
	}
	public MarketFxRateModel(){
		
	}
			
			
	public int getFxid() {
		return fxid;
	}
	public void setFxid(int fxid) {
		this.fxid = fxid;
	}
	public int getSourceid() {
		return sourceid;
	}
	public void setSourceid(int sourceid) {
		this.sourceid = sourceid;
	}
	public String getSoucename() {
		return soucename;
	}
	public void setSoucename(String soucename) {
		this.soucename = soucename;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Date getRateDate() {
		return rateDate;
	}
	public void setRateDate(Date rateDate) {
		this.rateDate = rateDate;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}
	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}
	public String getCurID() {
		return curID;
	}
	public void setCurID(String curID) {
		this.curID = curID;
	}
	public String getBaseCurID() {
		return baseCurID;
	}
	public void setBaseCurID(String baseCurID) {
		this.baseCurID = baseCurID;
	}
}
