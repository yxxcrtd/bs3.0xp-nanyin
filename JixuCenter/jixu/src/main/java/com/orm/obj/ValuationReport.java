package com.orm.obj;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/** 
 * @ClassName 		ValuationReport
 * @author  		Pan Wei 
 * @Date 			2018年1月15日 下午4:12:02 	
 * @Description		[]  
 * @return  
 */

@Entity 
@Table(name="JXV_VALUATIONREPORT") 
public class ValuationReport implements Serializable{

	private static final long serialVersionUID = 3191517453821052514L;
	
	public ValuationReport() {
		this.portnum="";
		this.accntName="";
	}
	
	private int id;
	
	/**
	 * 会计系统portid
	 */
	private String portnum;
	
	/**
	 * 余额表日期
	 */
	private Date asOfDate;
	
	/**
	 * 投资组合代码
	 */
	private String portCode;
	
	/**
	 * 投资组合名称
	 */
	private String portName;
	
	/**
	 * 科目号
	 */
	private String accntNum;
	
	/**
	 * 科目名
	 */
	private String accntName;
	
	/**
	 * 第几级科目
	 */
	private int accntLevel;
	
	/**
	 * 科目类型,资产、负债等
	 */
	private int accntCategory;
	
	/**
	 * 是否末级科目
	 */
	private int accntIsLeaf;
	
	/**
	 * 上一级科目号
	 */
	private String lastLevelAccntNum;
	
	/**
	 * 货币编号
	 */
	private String currency;
	
	/**
	 * 货币名称
	 */
	private String currencyCode;
	
	/**
	 * 数据中心证券id
	 * 如果在系统中找不到匹配的证券，暂时这个字段为0
	 */
	private String secMasterID;
	
	/**
	 * 证券ID
	 */
	private String securityID;
	
	/**
	 * 汇率
	 */
	private double fxRate;
	
	/**
	 * 数量
	 */
	private double amount;
	
	/**
	 * 单位成本原币
	 */
	private double costPerUnitLocal;
	
	/**
	 * 单位成本本币
	 */
	private double costPerUnitFunc;
	
	/**
	 * 原币成本
	 */
	private double costLocal;
	
	/**
	 * 本币成本
	 */
	private double costFunc;
	
	private double costPercent;
	
	/**
	 * 估值行情
	 */
	private double priceLocal;
	
	private double priceFunc;
	
	private double marketValueLocal;
	
	private double marketValueFunc;
	
	private double marketValuePercent;
	
	private double gainLossLocal;
	
	private double gainLossFunc;
	
	private String securityStatusDesc;
	
	private String interestInfo;
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="ValuationReport_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="portnum")
	public String getPortnum() {
		return portnum;
	}

	public void setPortnum(String portnum) {
		this.portnum = portnum;
	}

	@Column(name="asOfDate")
	public Date getAsOfDate() {
		return asOfDate;
	}

	public void setAsOfDate(Date asOfDate) {
		this.asOfDate = asOfDate;
	}

	@Column(name="portCode")
	public String getPortCode() {
		return portCode;
	}

	public void setPortCode(String portCode) {
		this.portCode = portCode;
	}

	@Column(name="portName")
	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	@Column(name="accntNum")
	public String getAccntNum() {
		return accntNum;
	}

	public void setAccntNum(String accntNum) {
		this.accntNum = accntNum;
	}

	@Column(name="accntName")
	public String getAccntName() {
		return accntName;
	}

	public void setAccntName(String accntName) {
		this.accntName = accntName;
	}

	@Column(name="accntLevel")
	public int getAccntLevel() {
		return accntLevel;
	}

	public void setAccntLevel(int accntLevel) {
		this.accntLevel = accntLevel;
	}

	@Column(name="accntCategory")
	public int getAccntCategory() {
		return accntCategory;
	}

	public void setAccntCategory(int accntCategory) {
		this.accntCategory = accntCategory;
	}

	@Column(name="accntIsLeaf")
	public int getAccntIsLeaf() {
		return accntIsLeaf;
	}

	public void setAccntIsLeaf(int accntIsLeaf) {
		this.accntIsLeaf = accntIsLeaf;
	}
	
	@Column(name="lastLevelAccntNum")
	public String getLastLevelAccntNum() {
		return lastLevelAccntNum;
	}

	public void setLastLevelAccntNum(String lastLevelAccntNum) {
		this.lastLevelAccntNum = lastLevelAccntNum;
	}

	@Column(name="CURRENCY")
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Column(name="currencyCode")
	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	@Column(name="securityID")
	public String getSecurityID() {
		return securityID;
	}

	public void setSecurityID(String securityID) {
		this.securityID = securityID;
	}

	@Column(name="fxRate")
	public double getFxRate() {
		return fxRate;
	}

	public void setFxRate(double fxRate) {
		this.fxRate = fxRate;
	}

	@Column(name="amount")
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Column(name="costLocal")
	public double getCostLocal() {
		return costLocal;
	}

	public void setCostLocal(double costLocal) {
		this.costLocal = costLocal;
	}

	@Column(name="costFunc")
	public double getCostFunc() {
		return costFunc;
	}

	public void setCostFunc(double costFunc) {
		this.costFunc = costFunc;
	}

	@Column(name="costPercent")
	public double getCostPercent() {
		return costPercent;
	}

	public void setCostPercent(double costPercent) {
		this.costPercent = costPercent;
	}
	
	@Column(name="costPerUnitLocal")
	public double getCostPerUnitLocal() {
		return costPerUnitLocal;
	}

	public void setCostPerUnitLocal(double costPerUnitLocal) {
		this.costPerUnitLocal = costPerUnitLocal;
	}

	@Column(name="costPerUnitFunc")
	public double getCostPerUnitFunc() {
		return costPerUnitFunc;
	}

	public void setCostPerUnitFunc(double costPerUnitFunc) {
		this.costPerUnitFunc = costPerUnitFunc;
	}

	@Column(name="priceLocal")
	public double getPriceLocal() {
		return priceLocal;
	}

	public void setPriceLocal(double priceLocal) {
		this.priceLocal = priceLocal;
	}

	@Column(name="priceFunc")
	public double getPriceFunc() {
		return priceFunc;
	}

	public void setPriceFunc(double priceFunc) {
		this.priceFunc = priceFunc;
	}

	@Column(name="marketValueLocal")
	public double getMarketValueLocal() {
		return marketValueLocal;
	}

	public void setMarketValueLocal(double marketValueLocal) {
		this.marketValueLocal = marketValueLocal;
	}

	@Column(name="marketValueFunc")
	public double getMarketValueFunc() {
		return marketValueFunc;
	}

	public void setMarketValueFunc(double marketValueFunc) {
		this.marketValueFunc = marketValueFunc;
	}

	@Column(name="marketValuePercent")
	public double getMarketValuePercent() {
		return marketValuePercent;
	}

	public void setMarketValuePercent(double marketValuePercent) {
		this.marketValuePercent = marketValuePercent;
	}

	@Column(name="gainLossLocal")
	public double getGainLossLocal() {
		return gainLossLocal;
	}

	public void setGainLossLocal(double gainLossLocal) {
		this.gainLossLocal = gainLossLocal;
	}

	@Column(name="gainLossFunc")
	public double getGainLossFunc() {
		return gainLossFunc;
	}

	public void setGainLossFunc(double gainLossFunc) {
		this.gainLossFunc = gainLossFunc;
	}

	@Column(name="securityStatusDesc")
	public String getSecurityStatusDesc() {
		return securityStatusDesc;
	}

	public void setSecurityStatusDesc(String securityStatusDesc) {
		this.securityStatusDesc = securityStatusDesc;
	}

	@Column(name="interestInfo")
	public String getInterestInfo() {
		return interestInfo;
	}

	public void setInterestInfo(String interestInfo) {
		this.interestInfo = interestInfo;
	}

	@Column(name="secMasterID")
	public String getSecMasterID() {
		return secMasterID;
	}

	public void setSecMasterID(String secMasterID) {
		this.secMasterID = secMasterID;
	}
}
