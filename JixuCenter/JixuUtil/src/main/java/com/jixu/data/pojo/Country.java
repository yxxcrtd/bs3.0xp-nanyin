package com.jixu.data.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * @ClassName 		Contry
 * @author  		Pan Wei 
 * @Date 			2017年11月29日 下午3:00:17 	
 * @Description		[]  
 * @return  
 */

@Entity 
@Table(name="Country") 
public class Country implements Serializable {

	private static final long serialVersionUID = 8280000322189638655L;
	
	/**
	 * 国家ID
	 */
	private String countryID;
	
	/**
	 * 国家名称
	 */
	private String countryName;
	
	/**
	 * 国家代码
	 */
	private String countryCode;
	
	

	@Id
	@Column(name="ID")
	public String getCountryID() {
		return countryID;
	}

	public void setCountryID(String countryID) {
		this.countryID = countryID;
	}

	@Column(name="countryName")
	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Column(name="countryCode")
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
}
