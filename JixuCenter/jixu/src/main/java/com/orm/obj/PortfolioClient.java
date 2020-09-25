package com.orm.obj;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		NewFundContent
 * @author  		Pan Wei 
 * @Date 			2017年2月14日 下午1:19:50 	
 * @Description		[客户表, 一个客户下面可以对应多个投资组合。在权限功能模块有对客户这一层级的配置,来决定基金会计能否对该客户下面的投资组合进行操作]  
 */

@Entity 
@Table(name="JXS_PORTFOLIO_CLIENT") 
public class PortfolioClient extends BasicEntity implements Serializable{

	private static final long serialVersionUID = -1549060713578072741L;

	private int clientid;
	
	private String shortName;
	
	private String clientName;
	
	private int companyType;

	private String country;
	
	private Province province;
	
	private String address;
	
	private String phoneNumber;
	
	private String postCode;
	
	private String webAddress;
	
	private String emailAddress;
	
	private Date initDate;
	
	private boolean active;
	
	public PortfolioClient() {
		// TODO Auto-generated constructor stub
	}
	
	public PortfolioClient(int clientid) {
		this.setClientid(clientid);
	}
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="PortfolioClient_ID_SEQ", allocationSize=1, initialValue=21)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="clientid")
	public int getClientid() {
		return clientid;
	}

	public void setClientid(int clientid) {
		this.clientid = clientid;
	}

	@Column(name="clientName")
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	@Column(name="shortName")
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name="companyType")
	public int getCompanyType() {
		return companyType;
	}

	public void setCompanyType(int companyType) {
		this.companyType = companyType;
	}

	@Column(name="COUNTRYID")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ProvinceID")
	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	@Column(name="address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name="phoneNumber")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name="postCode")
	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	@Column(name="webAddress")
	public String getWebAddress() {
		return webAddress;
	}

	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}

	@Column(name="emailAddress")
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Column(name="initDate")
	public Date getInitDate() {
		return initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	@Column(name="active",columnDefinition="integer",length=1)
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
