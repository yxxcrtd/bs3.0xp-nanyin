package com.jixu.journal.obj;

import java.io.Serializable;
import java.util.Date;

public class TransactionObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5043608176716406316L;
	
	private double gcost = 0.0;
	private double gcostFunc = 0.0;
	private double amount = 0.0;
	private double fxrate;
	private int trantype = 0;
	private int lotnum;
	private int trannum;
	private Date ledgerdate;

	public double getFxrate() {
		return fxrate;
	}

	public void setFxrate(double fxrate) {
		this.fxrate = fxrate;
	}

	public TransactionObj(double gcost, double gcostFunc, double amount,
			int trantype, Date ledgerDate, int lotnum, int trannum, double fxrate) {
		setGcost(gcost);
		setGcostFunc(gcostFunc);
		setAmount(amount);
		setTrantype(trantype);
		setLedgerdate(ledgerDate);
		setLotnum(lotnum);
		setTrannum(trannum);
		setFxrate(fxrate);
	}
	
	public int getLotnum() {
		return lotnum;
	}

	public void setLotnum(int lotnum) {
		this.lotnum = lotnum;
	}

	public int getTrannum() {
		return trannum;
	}

	public void setTrannum(int trannum) {
		this.trannum = trannum;
	}
	
	public Date getLedgerdate() {
		return ledgerdate;
	}
	public void setLedgerdate(Date ledgerdate) {
		this.ledgerdate = ledgerdate;
	}
	public int getTrantype() {
		return trantype;
	}
	public void setTrantype(int trantype) {
		this.trantype = trantype;
	}
	public double getGcost() {
		return gcost;
	}
	public void setGcost(double gcost) {
		this.gcost = gcost;
	}
	public double getGcostFunc() {
		return gcostFunc;
	}
	public void setGcostFunc(double gcostFunc) {
		this.gcostFunc = gcostFunc;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	
}
