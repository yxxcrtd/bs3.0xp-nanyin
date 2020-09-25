package com.joyintech.service.risk.customerrelationtree.agencydetailsinf.dto;

import java.io.Serializable;

public class MstCustomerInfoAddDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String partyType3;//机构类型（技术领域）

	private String partyType1;//机构类型（规模），大型企业、中小微企业

	private String companyNature;//企业性质

	private String insitituteCode;//组织机构代码/统一社会信用代码

	private String registerCapital;//注册资本（万元）

	private String province;//省

	private String city;//市

	private String county;//县

	private String industry;//国标一级分类

	private String industry2;//国标二级分类

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

	private String isListedCompany;
	private String isGroupCustomer;
	private String parentCustomerName;
}
