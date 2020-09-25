package com.orm.obj;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		Coaahdr
 * @author  		Pan Wei 
 * @Date 			2015年12月8日 下午4:08:03 	
 * @Description		[国家]  
 */

@Entity 
@Table(name="JXM_COUNTRY") 
public class Country extends BasicEntity implements Serializable{

	private static final long serialVersionUID = 3215004049412322232L;

	private String countryID;
	
	private String countryName;
	
	private String countryCode;
	
	private int active;
	
	public Country() {
		// TODO Auto-generated constructor stub
	}
	
	public Country(String countryID) {
		this.setCountryID(countryID);
	}
	
	@Id
	//@SequenceGenerator(name="seq_generator", sequenceName="Country_ID_SEQ", allocationSize=1, initialValue=40)
	//@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="COUNTRYID")
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

	@Column(name="active")
	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
}
