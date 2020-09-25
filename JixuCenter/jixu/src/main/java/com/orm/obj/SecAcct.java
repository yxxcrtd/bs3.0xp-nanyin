package com.orm.obj;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.jixu.common.pojo.BasicEntity;

/** 
 * @ClassName 		SecAcct
 * @author  		Pan Wei 
 * @Date 			2016年9月3日 下午11:02:51 	
 * @Description		[证券账户表。每个现金账户都挂在某一个证券账户下面，用户交易时先选择证券账户,再选择证券账户下面的现金账户。用不同的证券账户投资不同的证券，生成不同的头寸]  
 */

@Entity 
@Table(name="JXB_SECACCT") 
public class SecAcct extends BasicEntity implements Serializable{

	private static final long serialVersionUID = 4515721115196357681L;

	private String acctID;
	private String acctCode;
	private String acctDesc;
	private String portfolio;
	private CoaaGroup glGrop;
	private String brokerCode;
	private String seatNumber;
	
	/**
	 * 该证券账户默认的现金账户
	 */
	private int defaultCashAcct;
	
	private Set<TranBasic> tranBasics;
	
	public SecAcct(String acctID) {
		this.setAcctID(acctID);
	}
	
	public SecAcct() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(generator = "myID")
	@GenericGenerator(name = "myID", strategy = "com.jixu.common.func.GenCustomTBID_Joyin", parameters={
			@Parameter(name = "seq_name", value = "SecAcct_ID_SEQ"),
			@Parameter(name = "short_name", value = "JXS")})
	@Column(name="AcctID")	
	public String getAcctID() {
		return acctID;
	}
	public void setAcctID(String acctID) {
		this.acctID = acctID;
	}
	
	@Column(name="acctCode")
	public String getAcctCode() {
		return acctCode;
	}
	public void setAcctCode(String acctCode) {
		this.acctCode = acctCode;
	}
	
	@Column(name="acctDesc")
	public String getAcctDesc() {
		return acctDesc;
	}
	public void setAcctDesc(String acctDesc) {
		this.acctDesc = acctDesc;
	}
	
	@Column(name="portfolio")
	public String getPortfolio() {
		return portfolio;
	}
	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GlGrop")
	public CoaaGroup getGlGrop() {
		return glGrop;
	}
	public void setGlGrop(CoaaGroup glGrop) {
		this.glGrop = glGrop;
	}
	
	@Column(name="BrokerCode")
	public String getBrokerCode() {
		return brokerCode;
	}
	public void setBrokerCode(String brokerCode) {
		this.brokerCode = brokerCode;
	}

	@OneToMany(mappedBy = "secacct", cascade = CascadeType.REMOVE)
	public Set<TranBasic> getTranBasics() {
		return tranBasics;
	}

	public void setTranBasics(Set<TranBasic> tranBasics) {
		this.tranBasics = tranBasics;
	}

	@Column(name="defaultCashAcct")
	public int getDefaultCashAcct() {
		return defaultCashAcct;
	}

	public void setDefaultCashAcct(int defaultCashAcct) {
		this.defaultCashAcct = defaultCashAcct;
	}
	
	@Column(name="seatNumber")
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
}
