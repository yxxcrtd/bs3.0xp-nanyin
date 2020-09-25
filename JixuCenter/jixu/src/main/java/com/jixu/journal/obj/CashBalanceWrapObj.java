package com.jixu.journal.obj;

import java.io.Serializable;

import com.orm.obj.BkCashAcct;

/*银行存款估值*/

public class CashBalanceWrapObj implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8709510672828537717L;
	private BkCashAcct bkCashAcct; //银行现金账号ID
	private CashBalanceObj priorDayBalance = new CashBalanceObj();
	private CashBalanceObj asofdayBalance = new CashBalanceObj();
	private double inCash;
	private double outCash;
	
	public BkCashAcct getBkCashAcct() {
		return bkCashAcct;
	}

	public void setBkCashAcct(BkCashAcct bkCashAcct) {
		this.bkCashAcct = bkCashAcct;
	}

	public CashBalanceObj getPriorDayBalance() {
		return priorDayBalance;
	}

	public void setPriorDayBalance(CashBalanceObj priorDayBalance) {
		this.priorDayBalance = priorDayBalance;
	}

	public CashBalanceObj getAsofdayBalance() {
		return asofdayBalance;
	}

	public void setAsofdayBalance(CashBalanceObj asofdayBalance) {
		this.asofdayBalance = asofdayBalance;
	}

	public double getInCash() {
		return inCash;
	}

	public void setInCash(double inCash) {
		this.inCash = inCash;
	}

	public double getOutCash() {
		return outCash;
	}

	public void setOutCash(double outCash) {
		this.outCash = outCash;
	}
}
