package com.jixu.common.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/** 
 * @ClassName 		Coaa
 * @author  		Pan Wei 
 * @Date 			2015年12月8日 下午4:08:03 	
 * @Description		[HolidaySD和HolidayLS两表组成了节假日配置。HolidaySD是定义了一个节假日的名称，投资组合可以选择HolidaySD。而HolidayLS是记录了HolidaySD下面具体所有的节假日期]  
 */

@Entity 
@Table(name="JXU_HOLIDAYLS") 
public class HolidayLS extends BasicEntity implements Serializable{

	private static final long serialVersionUID = 2278387169432986416L;
	private int id;
	private String holidaySD;
	private Date holidayDate;
	private String holidayName;
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="JXU_HolidayLS_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="HOLIDAYSD")
	public String getHolidaySD() {
		return holidaySD;
	}
	public void setHolidaySD(String holidaySD) {
		this.holidaySD = holidaySD;
	}
	
	@Column(name="holidayDate")
	public Date getHolidayDate() {
		return holidayDate;
	}
	public void setHolidayDate(Date holidayDate) {
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
