package com.jixu.data.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * @ClassName 		Holiday
 * @author  		Pan Wei 
 * @Date 			2018年2月12日 上午10:39:14 	
 * @Description		[]  
 * @return  
 */

@Entity 
@Table(name="Holiday")
public class Holiday implements Serializable{

	private static final long serialVersionUID = 6211139637506962019L;

	private int id;
	
	private String code;
	
	private String name;

	@Id
	@Column(name="ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
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
}
