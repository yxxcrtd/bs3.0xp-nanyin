package com.jixu.data.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * @ClassName 		Holidays
 * @author  		Pan Wei 
 * @Date 			2018年2月12日 上午10:40:08 	
 * @Description		[]  
 * @return  
 */

@Entity 
@Table(name="Holidays") 
public class Holidays implements Serializable{
	
	private static final long serialVersionUID = 9149231409026753562L;
	
	private long id;
	private String holidayID;
	private java.sql.Date holidayDate;
	private String holidayName;
	
	@Id
	@Column(name="ID")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="holidayID")
	public String getHolidayID() {
		return holidayID;
	}
	public void setHolidayID(String holidayID) {
		this.holidayID = holidayID;
	}
	
	@Column(name="holidayDate")
	public java.sql.Date getHolidayDate() {
		return holidayDate;
	}
	public void setHolidayDate(java.sql.Date holidayDate) {
		this.holidayDate = holidayDate;
	}
	
	@Column(name="holidayName")
	public String getHolidayName() {
		return holidayName;
	}
	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}
	
	
}
