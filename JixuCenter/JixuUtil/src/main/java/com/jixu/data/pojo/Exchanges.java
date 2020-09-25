package com.jixu.data.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * @ClassName 		Exchanges
 * @author  		Pan Wei 
 * @Date 			2017年11月29日 下午4:06:59 	
 * @Description		[]  
 * @return  
 */
@Entity 
@Table(name="Exchanges")
public class Exchanges implements Serializable {

	private static final long serialVersionUID = -4299660721285588330L;
	
	/**
	 * 交易所ID
	 */
	private String exgID;
	
	/**
	 * 交易所代码
	 */
	private String exgCode;
	
	/**
	 * 交易所名称
	 */
	private String exgName;
	
	/**
	 * 国家ID
	 */
	private int countryID;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ExgID")
	public String getExgID() {
		return exgID;
	}

	public void setExgID(String exgID) {
		this.exgID = exgID;
	}

	@Column(name="exgCode")
	public String getExgCode() {
		return exgCode;
	}

	public void setExgCode(String exgCode) {
		this.exgCode = exgCode;
	}

	@Column(name="exgName")
	public String getExgName() {
		return exgName;
	}

	public void setExgName(String exgName) {
		this.exgName = exgName;
	}

	@Column(name="countryID")
	public int getCountryID() {
		return countryID;
	}

	public void setCountryID(int countryID) {
		this.countryID = countryID;
	}
	
	
}
