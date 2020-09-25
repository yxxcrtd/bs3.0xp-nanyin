package com.jixu.msg.dto;

import com.jixu.common.valid.NotEmptyJixu;
import com.jixu.framework.msg.BaseMsgDto;
import com.jixu.framework.validator.JixuValidLogic;
import com.jixu.msg.validator.BrokerAcctDtoValid;

@JixuValidLogic(targetClass = BrokerAcctDtoValid.class)
public class BrokerAcctDto extends BaseMsgDto{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	@NotEmptyJixu(keymsg="券商代码")
	private String code;
	
	private String bic;
	
	private String fin;
	
	@NotEmptyJixu(keymsg="券商名称")
	private String name;
	
	private String country;
	
	private String address;
	
	private String contact;
	
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

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getFin() {
		return fin;
	}

	public void setFin(String fin) {
		this.fin = fin;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
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
