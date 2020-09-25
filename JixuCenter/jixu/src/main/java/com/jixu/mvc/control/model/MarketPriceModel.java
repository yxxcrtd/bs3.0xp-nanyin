package com.jixu.mvc.control.model;

import java.util.Date;

public class MarketPriceModel {
	//List<String> titles = Arrays.asList("数据编号", "数据来源", "证券代码", "证券名称", "货币", "价格");
	
	public MarketPriceModel(int priceid,int sourceid,String sourcename,String secid,String secname,String currency,double price, Date priceDate,int priceto, double yield) {
		setPriceid(priceid);
		setSourceid(sourceid);
		setSourcename(sourcename);
		setSecid(secid);
		setSecname(secname);
		setCurency(currency);
		setPrice(price);
		setPriceDate(priceDate);
		setPriceto(priceto);
		setYield(yield);
	}
	
	public MarketPriceModel(){
		
	}
	
	private int priceid;
	private int sourceid;
	private String sourcename;
	private String secid;
	private String curency;
	private double price;
	private Date priceDate;
	private String secname;
	private int priceto;
	private double yield;
	
	
	public int getPriceid() {
		return priceid;
	}
	public void setPriceid(int priceid) {
		this.priceid = priceid;
	}
	public String getSourcename() {
		return sourcename;
	}
	public void setSourcename(String sourcename) {
		this.sourcename = sourcename;
	}
	public String getSecid() {
		return secid;
	}
	public void setSecid(String secid) {
		this.secid = secid;
	}
	public String getCurency() {
		return curency;
	}
	public void setCurency(String curency) {
		this.curency = curency;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getPriceDate() {
		return priceDate;
	}
	public void setPriceDate(Date priceDate) {
		this.priceDate = priceDate;
	}
	public String getSecname() {
		return secname;
	}
	public void setSecname(String secname) {
		this.secname = secname;
	}
	public int getSourceid() {
		return sourceid;
	}
	public void setSourceid(int sourceid) {
		this.sourceid = sourceid;
	}
	public int getPriceto() {
		return priceto;
	}
	public void setPriceto(int priceto) {
		this.priceto = priceto;
	}
	public double getYield() {
		return yield;
	}
	public void setYield(double yield) {
		this.yield = yield;
	}
}
