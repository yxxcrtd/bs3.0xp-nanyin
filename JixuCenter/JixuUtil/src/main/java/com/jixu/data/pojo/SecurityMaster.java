package com.jixu.data.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/** 
 * @ClassName 		SecurityMaster
 * @author  		Pan Wei 
 * @Date 			2017年11月29日 下午1:58:08 	
 * @Description		[]  
 * @return  
 */

@Entity 
@Table(name="SecurityMaster")
public class SecurityMaster implements Serializable {

	private static final long serialVersionUID = -7976122234540397364L;

	private String id;
	
	/**
	 * 股票代码
	 */
	private String securityID;
	
	/**
	 * 资产类别,在这里区分股票,债券,回购等
	 */
	private String issueType;
	
	/**
	 * 交易所,通过交易所查询证券在哪个市场,哪个国家放行
	 */
	private String exchangeID;
	
	/**
	 * 基准乘数。例如:在债券中为合约数
	 */
	private double contractSize;
	
	/**
	 *  发行的公司在系统内部的ID,关联issuer表
	 */
	private String issuerID;
	
	/**
	 * 本金货币
	 */
	private String principalCurID;
	
	/**
	 * 收入货币
	 */
	private String incomeCurID;
	
	private String securityName;
	
	private String countryID;

	@Id
	@GeneratedValue(generator = "myID")
	@GenericGenerator(name = "myID", strategy = "com.jixu.common.util.GenerateMyID", parameters={
			@Parameter(name = "idInitValue", value = "2"), // 步长，每次id增加多少
			@Parameter(name = "idLength", value = "10")}) // 初始值	会计系统,初始值1
	@Column(name="ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="securityID")
	public String getSecurityID() {
		return securityID;
	}

	public void setSecurityID(String securityID) {
		this.securityID = securityID;
	}

	@Column(name="issueType")
	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	@Column(name="exchangeID")
	public String getExchangeID() {
		return exchangeID;
	}

	public void setExchangeID(String exchangeID) {
		this.exchangeID = exchangeID;
	}

	@Column(name="contractSize")
	public double getContractSize() {
		return contractSize;
	}

	public void setContractSize(double contractSize) {
		this.contractSize = contractSize;
	}

	@Column(name="issuerID")
	public String getIssuerID() {
		return issuerID;
	}

	public void setIssuerID(String issuerID) {
		this.issuerID = issuerID;
	}

	@Column(name="principalCurID")
	public String getPrincipalCurID() {
		return principalCurID;
	}

	public void setPrincipalCurID(String principalCurID) {
		this.principalCurID = principalCurID;
	}

	@Column(name="incomeCurID")
	public String getIncomeCurID() {
		return incomeCurID;
	}

	public void setIncomeCurID(String incomeCurID) {
		this.incomeCurID = incomeCurID;
	}

	@Column(name="securityName")
	public String getSecurityName() {
		return securityName;
	}

	public void setSecurityName(String securityName) {
		this.securityName = securityName;
	}

	@Column(name="countryID")
	public String getCountryID() {
		return countryID;
	}

	public void setCountryID(String countryID) {
		this.countryID = countryID;
	}
	
}
