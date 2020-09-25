package com.jixu.mvc.control.model;

/** 
 * @ClassName 		MarketComStock
 * @author  		Pan Wei 
 * @Date 			2016年8月14日 下午10:09:12 	
 * @Description		[]  
 */

public class MarketStockModel {
	private String cusip;
	private String sourcename;
	private String secType;
	private String secCode;
	private String secName;
	private String industryName;
	private String coaName;
	private String curName;
	private String country;
	
	public MarketStockModel(String cusip,String sourcename,String secType,String secCode,String secName,String industryName,
			String coaName,String curName,String country) {
		setCusip(cusip);
		setSourcename(sourcename);
		setSecType(secType);
		setSecCode(secCode);
		setSecName(secName);
		setIndustryName(industryName);
		setCoaName(coaName);
		setCurName(curName);
		setCountry(country);
	}
	
	public MarketStockModel(){
		
	}
			
	public String getCusip() {
		return cusip;
	}
	public void setCusip(String cusip) {
		this.cusip = cusip;
	}
	public String getSourcename() {
		return sourcename;
	}
	public void setSourcename(String sourcename) {
		this.sourcename = sourcename;
	}
	public String getSecType() {
		return secType;
	}
	public void setSecType(String secType) {
		this.secType = secType;
	}
	public String getSecCode() {
		return secCode;
	}
	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}
	public String getSecName() {
		return secName;
	}
	public void setSecName(String secName) {
		this.secName = secName;
	}
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	public String getCoaName() {
		return coaName;
	}
	public void setCoaName(String coaName) {
		this.coaName = coaName;
	}
	public String getCurName() {
		return curName;
	}
	public void setCurName(String curName) {
		this.curName = curName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
