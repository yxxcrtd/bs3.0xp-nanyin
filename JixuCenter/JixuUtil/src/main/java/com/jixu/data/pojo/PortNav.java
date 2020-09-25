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
 * @ClassName 		PortNav
 * @author  		Pan Wei 
 * @Date 			2017年12月11日 上午9:34:09 	
 * @Description		[]  
 * @return  
 */

@Entity 
@Table(name="PortNav") 
public class PortNav implements Serializable{

	private static final long serialVersionUID = -3535789076198035466L;
	
	private int navid;
	
	/**
	 * 会计系统的portid
	 */
	private String portnum;
	
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
	
	/********份额总计**********/
	//The total shares outstanding at the end of the day
	private double sharesOutStanding;
	
	/********份额已结转**********/
	//The settled shares outstanding at the end of the day (total shares outstanding - unsettled subscriptions + unsettled redemptions).
	private double sharesSettled;
	
	/********单位净值**********/
	private double navAmount;
	
	/*********已实现收益*******/
	//The realized capital gains from the general ledger
	private double capitalRG;
	
	/*********未实现收益*******/
	//The unrealized capital gains from the general ledger
	private double capitalUnreal;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="NAVID")
	public int getNavid() {
		return navid;
	}

	public void setNavid(int navid) {
		this.navid = navid;
	}

	@Column(name="PortNum")
	public String getPortnum() {
		return portnum;
	}

	public void setPortnum(String portnum) {
		this.portnum = portnum;
	}

	@Column(name="NavDate", columnDefinition="Date default '2000-01-01'")
	public Date getNavDate() {
		return navDate;
	}

	public void setNavDate(Date navDate) {
		this.navDate = navDate;
	}

	@Column(name="Asset")
	public double getAsset() {
		return asset;
	}

	public void setAsset(double asset) {
		this.asset = asset;
	}

	@Column(name="Liabilities")
	public double getLiabilities() {
		return liabilities;
	}

	public void setLiabilities(double liabilities) {
		this.liabilities = liabilities;
	}

	@Column(name="Income")
	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	@Column(name="Expenses")
	public double getExpenses() {
		return expenses;
	}

	public void setExpenses(double expenses) {
		this.expenses = expenses;
	}

	@Column(name="Capital")
	public double getCapital() {
		return capital;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}

	@Column(name="SharesOutStanding")
	public double getSharesOutStanding() {
		return sharesOutStanding;
	}

	public void setSharesOutStanding(double sharesOutStanding) {
		this.sharesOutStanding = sharesOutStanding;
	}

	@Column(name="SharesSettled")
	public double getSharesSettled() {
		return sharesSettled;
	}

	public void setSharesSettled(double sharesSettled) {
		this.sharesSettled = sharesSettled;
	}

	@Column(name="NavAmount")
	public double getNavAmount() {
		return navAmount;
	}

	public void setNavAmount(double navAmount) {
		this.navAmount = navAmount;
	}

	@Column(name="CapitalRG")
	public double getCapitalRG() {
		return capitalRG;
	}

	public void setCapitalRG(double capitalRG) {
		this.capitalRG = capitalRG;
	}

	@Column(name="CapitalUnreal")
	public double getCapitalUnreal() {
		return capitalUnreal;
	}

	public void setCapitalUnreal(double capitalUnreal) {
		this.capitalUnreal = capitalUnreal;
	}
}
