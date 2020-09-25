package com.jixu.data.pojo;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
 * @ClassName 		SecPosition
 * @author  		Pan Wei 
 * @Date 			2017年10月28日 下午9:36:06 	
 * @Description		[]  
 * @return  
 */

@Entity 
@Table(name="SecPosition") 
public class SecPosition implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7502190436140521286L;

	private int id;

	/**
	 * 会计系统portid
	 */
	private String portnum;
	
	/**
	 * 投资组合名称
	 */
	private String portname;
	
	/**
	 * 关账日期 
	 */
	private Date closeDate;
	
	private String secMasterID;
	
	/**
	 * 证券securityid
	 */
	private String securityid;
	
	/**
	 * 证券类型， 股票,债券,期货等
	 */
	private String issuerType;

	/**
	 * 券商
	 */
	private String brokerID;
	
	/**
	 * 证券账号
	 */
	private String secAcctID;
	
	/**
	 * 默认银行现金账户
	 */
	private int defaultCashAcctID;
	
	/**
	 * 基金经理
	 */
	private int managerID;
	
	/**
	 * 证券名称
	 */
	private String securityname;
	
	/**
	 * 发行商名称
	 */
	private String issuerName;
	
	/**
	 * 数量
	 */
	private double amount;

	private double accrued;
	
	private double cost;
	
	private double funcCost;
	
	private double mkValue;
	
	private double funMkValue;
	
	/**
	 * 本地货币
	 */
	private String localCurID;
	
	/**
	 * 结算货币
	 */
	private String funcCurID;
	
	/**
	 * 收入货币 
	 */
	private String incomeCurID;

	private int lsindicator;
	
	/**
	 * 摊余成本
	 */
	private double amortValue;
	
	/**
	 * 获取 id
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	public int getId() {
		return id;
	}

	/**
	 * 设置 id
	 */
	
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 获取 portnum
	 */
	@Column(name="portnum")
	public String getPortnum() {
		return portnum;
	}

	/**
	 * 设置 portnum
	 */
	
	public void setPortnum(String portnum) {
		this.portnum = portnum;
	}

	/**
	 * 获取 portname
	 */
	@Column(name="portname")
	public String getPortname() {
		return portname;
	}

	/**
	 * 设置 portname
	 */
	
	public void setPortname(String portname) {
		this.portname = portname;
	}

	/**
	 * 获取 closeDate
	 */
	@Column(name="closeDate")
	public Date getCloseDate() {
		return closeDate;
	}

	/**
	 * 设置 closeDate
	 */
	
	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	/**
	 * 获取 securityid
	 */
	@Column(name="securityid")
	public String getSecurityid() {
		return securityid;
	}

	/**
	 * 设置 securityid
	 */
	
	public void setSecurityid(String securityid) {
		this.securityid = securityid;
	}

	/**
	 * 获取 securityname
	 */
	@Column(name="securityname")
	public String getSecurityname() {
		return securityname;
	}

	/**
	 * 设置 securityname
	 */
	
	public void setSecurityname(String securityname) {
		this.securityname = securityname;
	}

	/**
	 * 获取 issuerName
	 */
	@Column(name="issuerName")
	public String getIssuerName() {
		return issuerName;
	}

	/**
	 * 设置 issuerName
	 */
	
	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}

	/**
	 * 获取 amount
	 */
	@Column(name="amount")
	public double getAmount() {
		return amount;
	}

	/**
	 * 设置 amount
	 */
	
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * 获取 cost
	 */
	@Column(name="cost")
	public double getCost() {
		return cost;
	}

	/**
	 * 设置 cost
	 */
	
	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * 获取 funcCost
	 */
	@Column(name="funcCost")
	public double getFuncCost() {
		return funcCost;
	}

	/**
	 * 设置 funcCost
	 */
	
	public void setFuncCost(double funcCost) {
		this.funcCost = funcCost;
	}

	/**
	 * 获取 mkValue
	 */
	@Column(name="mkValue")
	public double getMkValue() {
		return mkValue;
	}

	/**
	 * 设置 mkValue
	 */
	
	public void setMkValue(double mkValue) {
		this.mkValue = mkValue;
	}

	@Column(name="funMkValue")
	public double getFunMkValue() {
		return funMkValue;
	}
	
	public void setFunMkValue(double funMkValue) {
		this.funMkValue = funMkValue;
	}

	@Column(name="brokerID")
	public String getBrokerID() {
		return brokerID;
	}

	public void setBrokerID(String brokerID) {
		this.brokerID = brokerID;
	}

	@Column(name="secAcctID")
	public String getSecAcctID() {
		return secAcctID;
	}

	public void setSecAcctID(String secAcctID) {
		this.secAcctID = secAcctID;
	}

	@Column(name="managerID")
	public int getManagerID() {
		return managerID;
	}

	public void setManagerID(int managerID) {
		this.managerID = managerID;
	}

	@Column(name="DefaultCashAcctID")
	public int getDefaultCashAcctID() {
		return defaultCashAcctID;
	}

	public void setDefaultCashAcctID(int defaultCashAcctID) {
		this.defaultCashAcctID = defaultCashAcctID;
	}

	@Column(name="LocalCurID")
	public String getLocalCurID() {
		return localCurID;
	}

	public void setLocalCurID(String localCurID) {
		this.localCurID = localCurID;
	}

	@Column(name="FuncCurID")
	public String getFuncCurID() {
		return funcCurID;
	}

	public void setFuncCurID(String funcCurID) {
		this.funcCurID = funcCurID;
	}

	@Column(name="IncomeCurID")
	public String getIncomeCurID() {
		return incomeCurID;
	}

	public void setIncomeCurID(String incomeCurID) {
		this.incomeCurID = incomeCurID;
	}

	@Column(name="secMasterID", columnDefinition="int default 0")
	public String getSecMasterID() {
		return secMasterID;
	}

	public void setSecMasterID(String secMasterID) {
		this.secMasterID = secMasterID;
	}

	@Column(name="issuerType")
	public String getIssuerType() {
		return issuerType;
	}

	public void setIssuerType(String issuerType) {
		this.issuerType = issuerType;
	}

	@Column(name="accrued", columnDefinition="double default 0")
	public double getAccrued() {
		return accrued;
	}

	public void setAccrued(double accrued) {
		this.accrued = accrued;
	}

	@Column(name="lsindicator", columnDefinition="double default 1")
	public int getLsindicator() {
		return lsindicator;
	}

	public void setLsindicator(int lsindicator) {
		this.lsindicator = lsindicator;
	}

	@Column(name="amortValue", columnDefinition="double default 0")
	public double getAmortValue() {
		return amortValue;
	}

	public void setAmortValue(double amortValue) {
		this.amortValue = amortValue;
	}
}
