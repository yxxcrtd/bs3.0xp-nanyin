package com.jixu.journal.obj;

import java.io.Serializable;

public class InterestObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2086652754521843589L;
	
	private String interestDate; //起息日
	private double interestRatio; //利率
	private int interestdays = 360;  //30/360 or acct/acct
	
	public int getInterestdays() {
		return interestdays;
	}
	public void setInterestdays(int interestdays) {
		this.interestdays = interestdays;
	}
	public String getInterestDate() {
		return interestDate;
	}
	public void setInterestDate(String interestDate) {
		this.interestDate = interestDate;
	}
	public double getInterestRatio() {
		return interestRatio;
	}
	public void setInterestRatio(double interestRatio) {
		this.interestRatio = interestRatio;
	}
}
