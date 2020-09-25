package com.orm.obj;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		BkCashRcpy
 * @author  		Pan Wei 
 * @Date 			2015年12月8日 下午4:08:03 	
 * @Description		[银行的基本信息]  
 */

@Entity 
@Table(name="JXB_BK_INFO") 
public class BkInfo extends BasicEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String bankID;
	
	private String bankCode;
	
	private String bankDesc;
	
	private String country;
	
	private String address;
	
	private String phoneNum;
	
	private String phoneFax;
	
	private String contractor;
	
	private Set<BkCashAcct> bkCashAcct;
	
	private Set<BkInteAcct> bkInteAcct;
	
	public BkInfo() {
	}
	
	public BkInfo(String bankID){
		this.setBankID(bankID);
	}
	
	@Id
	@GeneratedValue(generator = "myBANKID")
	@GenericGenerator(name = "myBANKID", strategy = "com.jixu.common.tool.GenCustomCharIDByDate", parameters={
			@Parameter(name = "seq_name", value = "BkInfo_ID_SEQ")})
	@Column(name="BANKID")
	public String getBankID() {
		return bankID;
	}

	public void setBankID(String bankID) {
		this.bankID = bankID;
	}

	@Column(name="BANKCODE")
	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	@Column(name="BANKDESC")
	public String getBankDesc() {
		return bankDesc;
	}

	public void setBankDesc(String bankDesc) {
		this.bankDesc = bankDesc;
	}

	@OneToMany(mappedBy = "bkinfo", cascade = CascadeType.REMOVE)
	public Set<BkCashAcct> getBkCashAcct() {
		return bkCashAcct;
	}

	public void setBkCashAcct(Set<BkCashAcct> bkCashAcct) {
		this.bkCashAcct = bkCashAcct;
	}

	@OneToMany(mappedBy = "bkinfo", cascade = CascadeType.REMOVE)
	public Set<BkInteAcct> getBkInteAcct() {
		return bkInteAcct;
	}

	public void setBkInteAcct(Set<BkInteAcct> bkInteAcct) {
		this.bkInteAcct = bkInteAcct;
	}

	@Column(name="COUNTRY")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name="address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name="phoneNum")
	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	@Column(name="phoneFax")
	public String getPhoneFax() {
		return phoneFax;
	}

	public void setPhoneFax(String phoneFax) {
		this.phoneFax = phoneFax;
	}

	@Column(name="contractor")
	public String getContractor() {
		return contractor;
	}

	public void setContractor(String contractor) {
		this.contractor = contractor;
	}
}
