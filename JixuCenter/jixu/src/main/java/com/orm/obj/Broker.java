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
 * @ClassName 		BkInteRatio
 * @author  		Pan Wei 
 * @Date 			2015年12月8日 下午4:08:03 	
 * @Description		[券商信息表]  
 */

@Entity 
@Table(name="JXS_BROKER") 
public class Broker extends BasicEntity implements Serializable{

	private static final long serialVersionUID = 3190453083766720052L;

	private String brokerID;

	private String brokerCode;
	
	private String brokerName;

	private String brokerDesc;

	private String bicCode;
	
	private String finCode;
	
	private String contractor;
	
	private String address;
	
	private String phoneNum;
	
	private String faxNum;
	
	private Set<TranBasic> tanum;
	
	private String country;

	
	public Broker(String brokerID) {
		this.setBrokerID(brokerID);
	}
	
	public Broker() {
		// TODO Auto-generated constructor stub
	}
	

	@Id
	@GeneratedValue(generator = "myID")
	@GenericGenerator(name = "myID", strategy = "com.jixu.common.func.GenCustomTBID_Joyin", parameters={
			@Parameter(name = "seq_name", value = "Broker_ID_SEQ")})
	@Column(name="brokerID")
	public String getBrokerID() {
		return brokerID;
	}

	public void setBrokerID(String brokerID) {
		this.brokerID = brokerID;
	}

	@Column(name="brokerName")
	public String getBrokerName() {
		return brokerName;
	}

	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}

	@Column(name="brokerDesc")
	public String getBrokerDesc() {
		return brokerDesc;
	}

	public void setBrokerDesc(String brokerDesc) {
		this.brokerDesc = brokerDesc;
	}

	@OneToMany(mappedBy = "broker", cascade = CascadeType.REMOVE)
	public Set<TranBasic> getTanum() {
		return tanum;
	}

	public void setTanum(Set<TranBasic> tanum) {
		this.tanum = tanum;
	}

	@Column(name="COUNTRY")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name="brokerCode")
	public String getBrokerCode() {
		return brokerCode;
	}

	public void setBrokerCode(String brokerCode) {
		this.brokerCode = brokerCode;
	}

	@Column(name="bicCode")
	public String getBicCode() {
		return bicCode;
	}

	public void setBicCode(String bicCode) {
		this.bicCode = bicCode;
	}

	@Column(name="finCode")
	public String getFinCode() {
		return finCode;
	}

	public void setFinCode(String finCode) {
		this.finCode = finCode;
	}

	@Column(name="contractor")
	public String getContractor() {
		return contractor;
	}

	public void setContractor(String contractor) {
		this.contractor = contractor;
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

	@Column(name="faxNum")
	public String getFaxNum() {
		return faxNum;
	}

	public void setFaxNum(String faxNum) {
		this.faxNum = faxNum;
	}
}
