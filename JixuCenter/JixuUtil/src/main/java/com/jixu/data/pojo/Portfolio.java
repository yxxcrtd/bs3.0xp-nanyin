package com.jixu.data.pojo;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.jixu.common.type.PortCreateMethodType;

/** 
 * @ClassName 		SecPosition
 * @author  		Pan Wei 
 * @Date 			2017年10月28日 下午9:36:06 	
 * @Description		[]  
 * @return  
 */

@Entity 
@Table(name="portfolio") 
public class Portfolio implements Serializable{

	private static final long serialVersionUID = 7502190436140521286L;
	
	//会计系统的投资组合id
	private int portID;
	
    private String portName;
	
	private String singleLot;
	
	private int chartNum;
	
	private int currencyCode;
	
	private int  glGroup;
	
	private int holidaySD;
	
	private int classID;
	
	//基金在系统的创建日
	private Date inceptionDate;
	
	private int portfolioClient;
	
	private int portfolioType;
	
	private String shortCode;
	
	private String shortName;
	
	private int province;
	
	private int navPrecision;
	
	private int bookNBDays;
	
	private int lotSelection;
	
	private int wkndValuationClose;
	
	private int tradeSettleLedger;
	
	private int managerGroup;
	
	private int defaultSecAcct;
	
	private String clientName;
	
	private String proName;
	
	private String currencyName;
	
	private int countryID;
	
	//创建方式,1. 系统做账	2.估值表上传
	private int createMethod;
	
	public Portfolio() {
		this.createMethod=PortCreateMethodType.valuationReport;
	}
	
	/**
	 * @param createMethod	创建方式 1.系统创建 2.估值表上传
	 */
	public Portfolio(int createMethod) {
		this.createMethod=createMethod;
	}
	
	@Id
	@GeneratedValue(generator = "myID")
	@GenericGenerator(name = "myID", strategy = "com.jixu.common.util.GenerateMyID", parameters={
			@Parameter(name = "idInitValue", value = "2"), // 步长，每次id增加多少
			@Parameter(name = "idLength", value = "10")}) // 初始值	会计系统,初始值1
	@Column(name="portID")
	public int getPortID() {
		return portID;
	}

	public void setPortID(int portID) {
		this.portID = portID;
	}

	@Column(name="portName")
	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}
	
	@Column(name="singleLot")
	public String getSingleLot() {
		return singleLot;
	}

	public void setSingleLot(String singleLot) {
		this.singleLot = singleLot;
	}

	@Column(name="chartNum")
	public int getChartNum() {
		return chartNum;
	}

	public void setChartNum(int chartNum) {
		this.chartNum = chartNum;
	}

	@Column(name="currencyCode")
	public int getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(int currencyCode) {
		this.currencyCode = currencyCode;
	}

	@Column(name="glGroup")
	public int getGlGroup() {
		return glGroup;
	}

	public void setGlGroup(int glGroup) {
		this.glGroup = glGroup;
	}

	@Column(name="holidaySD")
	public int getHolidaySD() {
		return holidaySD;
	}

	public void setHolidaySD(int holidaySD) {
		this.holidaySD = holidaySD;
	}

	@Column(name="classID")
	public int getClassID() {
		return classID;
	}

	public void setClassID(int classID) {
		this.classID = classID;
	}

	@Column(name="inceptionDate", columnDefinition="Date default '2018-01-01'")
	public Date getInceptionDate() {
		return inceptionDate;
	}

	public void setInceptionDate(Date inceptionDate) {
		this.inceptionDate = inceptionDate;
	}

	@Column(name="portfolioClient")
	public int getPortfolioClient() {
		return portfolioClient;
	}

	public void setPortfolioClient(int portfolioClient) {
		this.portfolioClient = portfolioClient;
	}

	@Column(name="portfolioType")
	public int getPortfolioType() {
		return portfolioType;
	}

	public void setPortfolioType(int portfolioType) {
		this.portfolioType = portfolioType;
	}

	@Column(name="shortCode")
	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	@Column(name="shortName")
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name="province")
	public int getProvince() {
		return province;
	}

	public void setProvince(int province) {
		this.province = province;
	}

	@Column(name="navPrecision")
	public int getNavPrecision() {
		return navPrecision;
	}

	public void setNavPrecision(int navPrecision) {
		this.navPrecision = navPrecision;
	}

	@Column(name="bookNBDays")
	public int getBookNBDays() {
		return bookNBDays;
	}

	public void setBookNBDays(int bookNBDays) {
		this.bookNBDays = bookNBDays;
	}

	@Column(name="lotSelection")
	public int getLotSelection() {
		return lotSelection;
	}

	public void setLotSelection(int lotSelection) {
		this.lotSelection = lotSelection;
	}

	@Column(name="wkndValuationClose")
	public int getWkndValuationClose() {
		return wkndValuationClose;
	}

	public void setWkndValuationClose(int wkndValuationClose) {
		this.wkndValuationClose = wkndValuationClose;
	}

	@Column(name="tradeSettleLedger")
	public int getTradeSettleLedger() {
		return tradeSettleLedger;
	}

	public void setTradeSettleLedger(int tradeSettleLedger) {
		this.tradeSettleLedger = tradeSettleLedger;
	}

	@Column(name="managerGroup")
	public int getManagerGroup() {
		return managerGroup;
	}

	public void setManagerGroup(int managerGroup) {
		this.managerGroup = managerGroup;
	}

	@Column(name="defaultSecAcct")
	public int getDefaultSecAcct() {
		return defaultSecAcct;
	}

	public void setDefaultSecAcct(int defaultSecAcct) {
		this.defaultSecAcct = defaultSecAcct;
	}

	@Column(name="clientName")
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	@Column(name="proName")
	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	@Column(name="currencyName")
	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	@Column(name="countryID")
	public int getCountryID() {
		return countryID;
	}

	public void setCountryID(int countryID) {
		this.countryID = countryID;
	}

	@Column(name="createMethod", columnDefinition="int default 1")
	public int getCreateMethod() {
		return createMethod;
	}

	public void setCreateMethod(int createMethod) {
		this.createMethod = createMethod;
	}
}
