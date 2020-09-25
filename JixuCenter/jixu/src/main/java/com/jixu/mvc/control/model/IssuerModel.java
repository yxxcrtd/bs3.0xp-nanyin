package com.jixu.mvc.control.model;
/** 
 * @ClassName 		IssuerModel
 * @author  		Pan Wei 
 * @Date 			2016年9月11日 下午1:31:08 	
 * @Description		[]  
 */

public class IssuerModel {
	private String id;
	private String issuercode;
	private String issuername;
	private String countryCode;
	private int industryType3;
	private String industryType2;
	private String industrype1;
	
	public IssuerModel(String id,String issuercode,String issuername,String countryCode,int industryType3) {
		this.setId(id);
		this.setIssuercode(issuercode);
		this.setIssuername(issuername);
		this.setCountryCode(countryCode);
		this.setIndustryType3(industryType3);
	}
	
	public IssuerModel(){
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIssuercode() {
		return issuercode;
	}
	public void setIssuercode(String issuercode) {
		this.issuercode = issuercode;
	}
	public String getIssuername() {
		return issuername;
	}
	public void setIssuername(String issuername) {
		this.issuername = issuername;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public int getIndustryType3() {
		return industryType3;
	}
	public void setIndustryType3(int industryType3) {
		this.industryType3 = industryType3;
	}
	public String getIndustryType2() {
		return industryType2;
	}
	public void setIndustryType2(String industryType2) {
		this.industryType2 = industryType2;
	}
	public String getIndustrype1() {
		return industrype1;
	}
	public void setIndustrype1(String industrype1) {
		this.industrype1 = industrype1;
	}
}
