package com.jixu.journal.obj;

import java.io.Serializable;
import java.util.Date;
/*证券清算款估值*/


public class SecCashObj implements Serializable {

	private static final long serialVersionUID = -6306801574358268636L;
	
	private double cash = 0.0;// 证券清算款本币
	private double FuncCash = 0.0;// 证券清算款原币  //暂时不用
	private double fxrate = 0.0; //交易时的汇率
	private Date ledgerDate;

	private int type = 0; //1： 冲销的证券清算款。0：未结清证券清算款。
	
	private String dbcr;  //D:表示应付。C:表示应收
	private String cusip;
	
	public String getCusip() {
		return cusip;
	}
	public void setCusip(String cusip) {
		this.cusip = cusip;
	}
	public Date getLedgerDate() {
		return ledgerDate;
	}
	public void setLedgerDate(Date ledgerDate) {
		this.ledgerDate = ledgerDate;
	}
	
	public String getDbcr() {
		return dbcr;
	}
	public void setDbcr(String dbcr) {
		this.dbcr = dbcr;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getFxrate() {
		return fxrate;
	}
	public void setFxrate(double fxrate) {
		this.fxrate = fxrate;
	}
	public double getCash() {
		return cash;
	}
	public void setCash(double cash) {
		this.cash = cash;
	}
	public double getFuncCash() {
		return FuncCash;
	}
	public void setFuncCash(double funcCash) {
		FuncCash = funcCash;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
