package com.jixu.journal.obj;

import java.io.Serializable;
import java.util.Date;

public class BankBalanceObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4195780277005595349L;
	
	private double bkbalance = 0.0;
	private Date ledgerdate;
	private double fxrate;
	
	public double getFxrate() {
		return fxrate;
	}
	public void setFxrate(double fxrate) {
		this.fxrate = fxrate;
	}
	public double getBkbalance() {
		return bkbalance;
	}
	public void setBkbalance(double bkbalance) {
		this.bkbalance = bkbalance;
	}
	public Date getLedgerdate() {
		return ledgerdate;
	}
	public void setLedgerdate(Date ledgerdate) {
		this.ledgerdate = ledgerdate;
	}
}
