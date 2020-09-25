package com.jixu.msg.dto;

import com.jixu.common.valid.NotEmptyJixu;
import com.jixu.framework.msg.BaseMsgDto;


public class IssuerDto extends BaseMsgDto{

	private static final long serialVersionUID = 1L;
	
	private String id;

	@NotEmptyJixu(keymsg="名称")
	private String name;
	
	@NotEmptyJixu(keymsg="长名")
	private String lname;
	
	@NotEmptyJixu(keymsg="国家")
	private String country;
	
	private String type;
	
	private String major;
	
	private String minor;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getMinor() {
		return minor;
	}

	public void setMinor(String minor) {
		this.minor = minor;
	}
}
