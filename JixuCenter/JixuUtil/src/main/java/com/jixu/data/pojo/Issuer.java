package com.jixu.data.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/** 
 * @ClassName 		Issuer
 * @author  		Pan Wei 
 * @Date 			2017年11月29日 下午5:00:01 	
 * @Description		[]  
 * @return  
 */
@Entity 
@Table(name="Issuer") 
public class Issuer implements Serializable{

	private static final long serialVersionUID = 5100372834565182879L;
	
	private String issuerID;
	
	/**
	 * 公司名称
	 */
	private String issuerName;
	
	/**
	 * 公司长名
	 */
	private String issuerLongName;
	
	/**
	 * 所属国家ID
	 */
	private String countryID;
	
	/**
	 * 所属省ID
	 */
	private int provinceID;
	
	/**
	 * 所属城市ID
	 */
	private int cityID;
	
	/**
	 * 注册地
	 */
	private String regAddress;
	
	/**
	 * 实际办公地
	 */
	private String officeAddress;
	
	/**
	 * 如果是true，则是指数发行商，除了可以发行普通证券，还可以发行指数
	 * false则是普通证券 
	 */
	private boolean indexIssuer;

	
	@Id
	@GeneratedValue(generator = "myID")
	@GenericGenerator(name = "myID", strategy = "com.jixu.common.util.GenerateMyID", parameters={
			@Parameter(name = "idInitValue", value = "2"), // 步长，每次id增加多少
			@Parameter(name = "idLength", value = "10")}) // 初始值	会计系统,初始值1
	@Column(name="IssuerID")
	public String getIssuerID() {
		return issuerID;
	}

	public void setIssuerID(String issuerID) {
		this.issuerID = issuerID;
	}

	@Column(name="issuerName")
	public String getIssuerName() {
		return issuerName;
	}

	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}

	@Column(name="issuerLongName")
	public String getIssuerLongName() {
		return issuerLongName;
	}

	public void setIssuerLongName(String issuerLongName) {
		this.issuerLongName = issuerLongName;
	}

	@Column(name="countryID")
	public String getCountryID() {
		return countryID;
	}

	public void setCountryID(String countryID) {
		this.countryID = countryID;
	}

	@Column(name="provinceID")
	public int getProvinceID() {
		return provinceID;
	}

	public void setProvinceID(int provinceID) {
		this.provinceID = provinceID;
	}

	@Column(name="cityID")
	public int getCityID() {
		return cityID;
	}

	public void setCityID(int cityID) {
		this.cityID = cityID;
	}

	@Column(name="regAddress")
	public String getRegAddress() {
		return regAddress;
	}

	public void setRegAddress(String regAddress) {
		this.regAddress = regAddress;
	}

	@Column(name="officeAddress")
	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	@Column(name="indexIssuer", columnDefinition="bit(1) default 0")
	public boolean isIndexIssuer() {
		return indexIssuer;
	}

	public void setIndexIssuer(boolean indexIssuer) {
		this.indexIssuer = indexIssuer;
	}
}
