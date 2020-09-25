package com.jixu.msg.dto;

import com.jixu.common.valid.NotEmptyJixu;
import com.jixu.framework.msg.BaseMsgDto;
import com.jixu.framework.validator.JixuValidLogic;
import com.jixu.msg.validator.BankInfoDtoValid;

@JixuValidLogic(targetClass = BankInfoDtoValid.class)
public class BankInfoDto extends BaseMsgDto{

	private static final long serialVersionUID = 1L;
	
	private int id;

	@NotEmptyJixu(keymsg="银行代码")
	private String code;
	
	@NotEmptyJixu(keymsg="银行名称")
	private String name;
	
	@NotEmptyJixu(keymsg="国家")
	private String country;
	
	private String contact;
	
	private String address;
	
	private String tel;
	
	private String fax;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
}
