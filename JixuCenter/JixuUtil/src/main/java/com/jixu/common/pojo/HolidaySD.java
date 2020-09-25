package com.jixu.common.pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/** 
 * @ClassName 		Coaa
 * @author  		Pan Wei 
 * @Date 			2015年12月8日 下午4:08:03 	
 * @Description		[HolidaySD和HolidayLS两表组成了节假日配置。HolidaySD是定义了一个节假日的名称，投资组合可以选择HolidaySD。而HolidayLS是记录了HolidaySD下面具体所有的节假日期]  
 */

@Entity 
@Table(name="JXU_HOLIDAYSD") 
public class HolidaySD extends BasicEntity implements Serializable{

	private static final long serialVersionUID = 5477980163530655566L;

	private String id;
	
	private String code;
	
	private String name;
	
	private Set<HolidayLS> holidayLS;
	
	public HolidaySD() {
		// TODO Auto-generated constructor stub
	}
	
	public HolidaySD(String id) {
		this.id=id;
	}

	@Id
	@GeneratedValue(generator = "holidaySDID")
	@GenericGenerator(name = "holidaySDID", strategy = "com.jixu.common.tool.GenCustomCharIDByDate", parameters={
			@Parameter(name = "seq_name", value = "JXU_HolidaySD_ID_SEQ")})
	@Column(name="ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "holidaySD", cascade = CascadeType.REMOVE)
	public Set<HolidayLS> getHolidayLS() {
		return holidayLS;
	}

	public void setHolidayLS(Set<HolidayLS> holidayLS) {
		this.holidayLS = holidayLS;
	}
}
