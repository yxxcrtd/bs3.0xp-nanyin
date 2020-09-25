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
@Table(name="CashPosition") 
public class CashPosition implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7502190436140521286L;

	private int id;
	
	/**
	 * 会计系统的portid
	 */
    private String portnum;
	
    /**
	 * 银行ID
	 */
	private int bkID;
	
	/**
	 * 银行账户
	 */
	private String bankAcct;
	
	/**
	 * 银行账户名称
	 */
	private String bankAcctName;
	
	private double cash;
	
	/**
	 * 货币ID
	 */
	private String currID;
	
	/**
	 * 货币代码
	 */
	private String currCode;
	
	/**
	 * 利率
	 */
	private double fxRate;
	
	private double funcMarketValue;
	
	/**
	 * 记账日期
	 */
	private Date ledgerDate;
	

	
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

	@Column(name="portnum")
	public String getPortnum() {
		return portnum;
	}

	public void setPortnum(String portnum) {
		this.portnum = portnum;
	}

	@Column(name="bkID")
	public int getBkID() {
		return bkID;
	}

	public void setBkID(int bkID) {
		this.bkID = bkID;
	}
	
	@Column(name="bankAcct")
	public String getBankAcct() {
		return bankAcct;
	}

	public void setBankAcct(String bankAcct) {
		this.bankAcct = bankAcct;
	}

	@Column(name="bankAcctName")
	public String getBankAcctName() {
		return bankAcctName;
	}

	public void setBankAcctName(String bankAcctName) {
		this.bankAcctName = bankAcctName;
	}

	@Column(name="cash")
	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	@Column(name="currID")
	public String getCurrID() {
		return currID;
	}

	public void setCurrID(String currID) {
		this.currID = currID;
	}

	@Column(name="currCode")
	public String getCurrCode() {
		return currCode;
	}

	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}

	@Column(name="FxRate", columnDefinition="Double default 0")
	public double getFxRate() {
		return fxRate;
	}

	public void setFxRate(double fxRate) {
		this.fxRate = fxRate;
	}

	@Column(name="FuncMarketValue", columnDefinition="Double default 0")
	public double getFuncMarketValue() {
		return funcMarketValue;
	}

	public void setFuncMarketValue(double funcMarketValue) {
		this.funcMarketValue = funcMarketValue;
	}

	@Column(name="LedgerDate", columnDefinition="Date default '2000-01-01'")
	public Date getLedgerDate() {
		return ledgerDate;
	}

	public void setLedgerDate(Date ledgerDate) {
		this.ledgerDate = ledgerDate;
	}
}
