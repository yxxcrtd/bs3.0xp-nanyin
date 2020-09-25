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
 * @author  		Pan Wei 
 * @Date 			2016年9月8日 下午10:53:16 	
 * @Description		[记录每天投资组合的净资产。净资产=资产-负债净资产=资本+已实现收益+未实现收益+收入-开支]  
 */


@Entity 
@Table(name="JXV_NAV") 
public class Nav extends BasicEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int navid;
	
	private PortfolioHdr portfolio;
	
	private Date navDate;
	
	/********资产**********/
	private double asset;
	
	/********负债**********/
	private double liabilities;
	
	/********收入**********/
	private double income;
	
	/********开支**********/
	private double expenses;
	
	/********资本**********/
	private double capital;
	
	//The dollar amount of any income distribution for the day from the general ledger.
	private double incomeDist;
	
	/********份额总计**********/
	//The total shares outstanding at the end of the day
	private double sharesOutStanding;
	
	/********份额已结转**********/
	//The settled shares outstanding at the end of the day (total shares outstanding - unsettled subscriptions + unsettled redemptions).
	private double sharesSettled;
	
	/********单位净值**********/
	private double navAmount;
	
	/*******利率--均等化*********/
	//If applicable, the calculated equalization rate (undistributed net investment income / total shares outstanding) for the day
	private double equalizationRate;
	
	/*******利率--日分配*********/
	private double dailyDivDistRate;
	
	/*******最高出价*********/
	//For front sales load funds, the calculated price for the day (NAV / (1 - sales load)).
	private double maxOfferingPrice;
	
	/********总计 - 日净资产********/
	//The sum of the daily net assets since the beginning of the fiscal year or the inception of the account, whichever is later
	private double sumOfDailyNetAssets;
	
	/*********天数*******/
	//The number of days associated with SumOfDailyNetAssets (the previous field).
	private int numberOfDaysInSum;
	
	/*********已实现收益*******/
	//The realized capital gains from the general ledger
	private double capitalRG;
	
	/*********未实现收益*******/
	//The unrealized capital gains from the general ledger
	private double capitalUnreal;
	
	/****************/
	//The distributions from realized capital gains from the general ledger
	private double distCG;
	
	/****************/
	//A flag indicating if this is a special record created for the purpose of initializing the database, rather than one created through normal NAV processing. If the flag indicates an Initialize record, the record is not removed through the normal reopen-period processes
	//'0' (48) - not an Initialize record 
	//other values - an Initialize record
	private int initFlag;
	
	/****************/
	//For a mutual fund portfolio with a multiple-class share structure, a system-generated code indicating the class of shares to which the record applies. Corresponds to pamhdrcl:ClassID.
	private int classID;
	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="Nav_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="NAVID")
	public int getNavid() {
		return navid;
	}

	public void setNavid(int navid) {
		this.navid = navid;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PORTFOLIO")
	public PortfolioHdr getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(PortfolioHdr portfolio) {
		this.portfolio = portfolio;
	}


	@Column(name="NAVDATE")
	public Date getNavDate() {
		return navDate;
	}

	public void setNavDate(Date navDate) {
		this.navDate = navDate;
	}
	
	@Column(name="ASSETS")
	public double getAsset() {
		return asset;
	}

	public void setAsset(double asset) {
		this.asset = asset;
	}

	@Column(name="LIABILITIES")
	public double getLiabilities() {
		return liabilities;
	}

	public void setLiabilities(double liabilities) {
		this.liabilities = liabilities;
	}

	@Column(name="INCOME")
	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	@Column(name="EXPENSES")
	public double getExpenses() {
		return expenses;
	}

	public void setExpenses(double expenses) {
		this.expenses = expenses;
	}

	@Column(name="CAPITAL")
	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}

	@Column(name="INCOMEDIST")
	public double getIncomeDist() {
		return incomeDist;
	}

	public void setIncomeDist(double incomeDist) {
		this.incomeDist = incomeDist;
	}

	@Column(name="SHARESOUTSTANDING")
	public double getSharesOutStanding() {
		return sharesOutStanding;
	}

	public void setSharesOutStanding(double sharesOutStanding) {
		this.sharesOutStanding = sharesOutStanding;
	}

	@Column(name="SHARESSETTLED")
	public double getSharesSettled() {
		return sharesSettled;
	}

	public void setSharesSettled(double sharesSettled) {
		this.sharesSettled = sharesSettled;
	}

	@Column(name="NAVAMOUNT")
	public double getNavAmount() {
		return navAmount;
	}

	public void setNavAmount(double navAmount) {
		this.navAmount = navAmount;
	}

	@Column(name="EQUALIZATIONRATE")
	public double getEqualizationRate() {
		return equalizationRate;
	}

	public void setEqualizationRate(double equalizationRate) {
		this.equalizationRate = equalizationRate;
	}

	@Column(name="DAILYDIVDISTRATE")
	public double getDailyDivDistRate() {
		return dailyDivDistRate;
	}

	public void setDailyDivDistRate(double dailyDivDistRate) {
		this.dailyDivDistRate = dailyDivDistRate;
	}

	@Column(name="MAXOFFERINGPRICE")
	public double getMaxOfferingPrice() {
		return maxOfferingPrice;
	}

	public void setMaxOfferingPrice(double maxOfferingPrice) {
		this.maxOfferingPrice = maxOfferingPrice;
	}

	@Column(name="SUMOFDAILYNETASSETS")
	public double getSumOfDailyNetAssets() {
		return sumOfDailyNetAssets;
	}

	public void setSumOfDailyNetAssets(double sumOfDailyNetAssets) {
		this.sumOfDailyNetAssets = sumOfDailyNetAssets;
	}

	@Column(name="NUMBEROFDAYSINSUM")
	public int getNumberOfDaysInSum() {
		return numberOfDaysInSum;
	}

	public void setNumberOfDaysInSum(int numberOfDaysInSum) {
		this.numberOfDaysInSum = numberOfDaysInSum;
	}

	@Column(name="CAPITALRG")
	public double getCapitalRG() {
		return capitalRG;
	}

	public void setCapitalRG(double capitalRG) {
		this.capitalRG = capitalRG;
	}

	@Column(name="CAPITALUNREAL")
	public double getCapitalUnreal() {
		return capitalUnreal;
	}

	public void setCapitalUnreal(double capitalUnreal) {
		this.capitalUnreal = capitalUnreal;
	}

	@Column(name="DISTCG")
	public double getDistCG() {
		return distCG;
	}

	public void setDistCG(double distCG) {
		this.distCG = distCG;
	}

	@Column(name="INITFLAG")
	public int getInitFlag() {
		return initFlag;
	}

	public void setInitFlag(int initFlag) {
		this.initFlag = initFlag;
	}

	@Column(name="CLASSID")
	public int getClassID() {
		return classID;
	}

	public void setClassID(int classID) {
		this.classID = classID;
	}
}
