package com.joyintech.tams.invest.risk.customerrelationtree.agencydetailsinf.dto;

import java.io.Serializable;

public class MstCustomerInfoAddDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String partyType3;

	private String partyType1;

	private String companyNature;

	private String insitituteCode;

	private String registerCapital;

	private String province;

	private String city;

	private String county;

	private String industry;

	private String industry2;	
	
	private String isListedCompany;
	
	private String isGroupCustomer;
	
	private String parentCustomerName;
	
	

	public String getPartyType3() {
		return partyType3;
	}

	public void setPartyType3(String partyType3) {
		this.partyType3 = partyType3;
	}

	public String getPartyType1() {
		return partyType1;
	}

	public void setPartyType1(String partyType1) {
		this.partyType1 = partyType1;
	}

	public String getCompanyNature() {
		return companyNature;
	}

	public void setCompanyNature(String companyNature) {
		this.companyNature = companyNature;
	}

	public String getInsitituteCode() {
		return insitituteCode;
	}

	public void setInsitituteCode(String insitituteCode) {
		this.insitituteCode = insitituteCode;
	}

	public String getRegisterCapital() {
		return registerCapital;
	}

	public void setRegisterCapital(String registerCapital) {
		this.registerCapital = registerCapital;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getIndustry2() {
		return industry2;
	}

	public void setIndustry2(String industry2) {
		this.industry2 = industry2;
	}

	public String getIsListedCompany() {
		return isListedCompany;
	}

	public void setIsListedCompany(String isListedCompany) {
		this.isListedCompany = isListedCompany;
	}

	public String getIsGroupCustomer() {
		return isGroupCustomer;
	}

	public void setIsGroupCustomer(String isGroupCustomer) {
		this.isGroupCustomer = isGroupCustomer;
	}

	public String getParentCustomerName() {
		return parentCustomerName;
	}

	public void setParentCustomerName(String parentCustomerName) {
		this.parentCustomerName = parentCustomerName;
	}


}
