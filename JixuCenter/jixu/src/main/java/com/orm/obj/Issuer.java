package com.orm.obj;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		IssueInfo
 * @author  		Pan Wei 
 * @Date 			2015年12月8日 下午4:08:03 	
 * @Description		[证券公司表,一个证券公司可以发行多个证券]  
 */


@Entity 
@Table(name="JXS_ISSUER") 
public class Issuer extends BasicEntity implements Serializable{

	private static final long serialVersionUID = 3917404054346669297L;

	private String issuerID;
	private String issuerCode;
	private String issuerName;
	private String issuerShortName;
	private String country;
	private Date updateTime;
	private Set<IssueInfo> cusip;
	private RPSecType rpSecType;
	
	//@Id
	//@SequenceGenerator(name="seq_generator", sequenceName="Issuer_ID_SEQ", allocationSize=1, initialValue=14619)
	//@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Id
	@GeneratedValue(generator = "myID")
	@GenericGenerator(name = "myID", strategy = "com.jixu.common.func.GenCustomTBID_Joyin", parameters={
			@Parameter(name = "seq_name", value = "Issuer_ID_SEQ")})
	@Column(name="issuerID")
	public String getIssuerID() {
		return issuerID;
	}
	
	public void setIssuerID(String issuerID) {
		this.issuerID = issuerID;
	}
	
	@Column(name="issuerName")
	public String getIssuerName() {
		return issuerName;
	}
	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}
	
	@Column(name="COUNTRY")
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Column(name="updateTime")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@OneToMany(mappedBy = "issuer", cascade = CascadeType.REMOVE)
	public Set<IssueInfo> getCusip() {
		return cusip;
	}

	public void setCusip(Set<IssueInfo> cusip) {
		this.cusip = cusip;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="rpSecType")
	public RPSecType getRpSecType() {
		return rpSecType;
	}

	public void setRpSecType(RPSecType rpSecType) {
		this.rpSecType = rpSecType;
	}

	@Column(name="issuerShortName")
	public String getIssuerShortName() {
		return issuerShortName;
	}

	public void setIssuerShortName(String issuerShortName) {
		this.issuerShortName = issuerShortName;
	}

	@Column(name="issuerCode")
	public String getIssuerCode() {
		return issuerCode;
	}

	public void setIssuerCode(String issuerCode) {
		this.issuerCode = issuerCode;
	}
}
