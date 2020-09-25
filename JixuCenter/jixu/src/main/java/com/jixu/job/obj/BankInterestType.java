package com.jixu.job.obj;

import com.jixu.journal.obj.BankBalanceWrapObj;
import com.orm.obj.BkCashAcct;

/** 
 * @ClassName 		InterestType
 * @author  		Pan Wei 
 * @Date 			2016年11月20日 下午11:18:20 	
 * @Description		[]  
 */

public class BankInterestType {
	public BankBalanceWrapObj bkObj;
	public double totalInterestOrig; //当天为止的利息(本币全精度)。
	public double priordayInterestOrig; //前一天的累计利息(本币ROUND后)	
	public double totalInterestFxOrig; //当天为止的利息(原币全精度)。
	public double priordayInterestFxOrig; //前一天的累计利息(原币ROUND后)
	public double totalBalance;
	public double todayFxRate;
	
	public BankBalanceWrapObj getBkObj() {
		return bkObj;
	}
	public void setBkObj(BankBalanceWrapObj bkObj) {
		this.bkObj = bkObj;
	}
	public double getTotalInterestOrig() {
		return totalInterestOrig;
	}
	public void setTotalInterestOrig(double totalInterestOrig) {
		this.totalInterestOrig = totalInterestOrig;
	}
	public double getPriordayInterestOrig() {
		return priordayInterestOrig;
	}
	public void setPriordayInterestOrig(double priordayInterestOrig) {
		this.priordayInterestOrig = priordayInterestOrig;
	}
	public double getTotalInterestFxOrig() {
		return totalInterestFxOrig;
	}
	public void setTotalInterestFxOrig(double totalInterestFxOrig) {
		this.totalInterestFxOrig = totalInterestFxOrig;
	}
	public double getPriordayInterestFxOrig() {
		return priordayInterestFxOrig;
	}
	public void setPriordayInterestFxOrig(double priordayInterestFxOrig) {
		this.priordayInterestFxOrig = priordayInterestFxOrig;
	}
	public double getTotalBalance() {
		return totalBalance;
	}
	public void setTotalBalance(double totalBalance) {
		this.totalBalance = totalBalance;
	}
	public double getTodayFxRate() {
		return todayFxRate;
	}
	public void setTodayFxRate(double todayFxRate) {
		this.todayFxRate = todayFxRate;
	}
}
