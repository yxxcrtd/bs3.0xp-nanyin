package com.orm.obj;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jixu.common.pojo.BasicEntity;

@Entity 
@Table(name="JXB_BK_CASH_CLO") 
public class BkCashClo extends BasicEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	//关账日期
	private Date closeDate;
	
	//投资组合
	private String portnum;
	
	//银行现金账户ID
	private int bkCashAcctID;
	
	//账户类型
	private int accountType;

	//货币ID
	private String curID;
	
	//交易汇率
	private double fxRate;
	
	//账户余额
	private double balanceAmt;
	
	//账户余额当天变动额
	private double todayBalanceAmt;
	
	//利息收入
	private double interestIncome;
	
	//利息收入当天变动额
	private double todayIncome;

	
	@Id
	@SequenceGenerator(name="seq_generator", sequenceName="BkCashClo_ID_SEQ", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="seq_generator", strategy = GenerationType.SEQUENCE)
	@Column(name="ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	@Column(name="closeDate")
	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	@Column(name="portnum")
	public String getPortnum() {
		return portnum;
	}

	public void setPortnum(String portnum) {
		this.portnum = portnum;
	}

	@Column(name="bkCashAcctID")
	public int getBkCashAcctID() {
		return bkCashAcctID;
	}

	public void setBkCashAcctID(int bkCashAcctID) {
		this.bkCashAcctID = bkCashAcctID;
	}

	@Column(name="accountType")
	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	@Column(name="curID")
	public String getCurID() {
		return curID;
	}

	public void setCurID(String curID) {
		this.curID = curID;
	}

	@Column(name="fxRate")
	public double getFxRate() {
		return fxRate;
	}

	public void setFxRate(double fxRate) {
		this.fxRate = fxRate;
	}

	@Column(name="balanceAmt")
	public double getBalanceAmt() {
		return balanceAmt;
	}

	public void setBalanceAmt(double balanceAmt) {
		this.balanceAmt = balanceAmt;
	}

	@Column(name="interestIncome")
	public double getInterestIncome() {
		return interestIncome;
	}

	public void setInterestIncome(double interestIncome) {
		this.interestIncome = interestIncome;
	}

	@Column(name="todayBalanceAmt")
	public double getTodayBalanceAmt() {
		return todayBalanceAmt;
	}

	public void setTodayBalanceAmt(double todayBalanceAmt) {
		this.todayBalanceAmt = todayBalanceAmt;
	}

	@Column(name="todayIncome")
	public double getTodayIncome() {
		return todayIncome;
	}

	public void setTodayIncome(double todayIncome) {
		this.todayIncome = todayIncome;
	}
}
