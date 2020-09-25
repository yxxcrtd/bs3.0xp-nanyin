package com.jixu.journal.obj;

import java.io.Serializable;

public class CashBalanceObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6785606677048033236L;

	
	private double avgrate = 0.0;  //银行存款平均汇率
	private double balance = 0.0; //余额
	
	public double getAvgrate() {
		return avgrate;
	}
	public void setAvgrate(double avgrate) {
		this.avgrate = avgrate;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}
