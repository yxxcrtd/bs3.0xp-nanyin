package com.jixu.journal.obj;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.orm.obj.BkCashAcct;

public class BankBalanceWrapObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5658947294161703719L;
	
	private BkCashAcct bkcashAcct;
	private String currcode;
	private Date inteLastCycleEndDate; //上一轮计息的最后一天
	private Date inteStartDate;    //这轮计息的第一天
	private Date inteEndDate;		//这轮计息的最后一天
	private double lstcalInteBalance; //上次结息后一天的银行余额
	private List<BankBalanceObj> bankBalanceList;
	
	public double getLstcalInteBalance() {
		return lstcalInteBalance;
	}
	public void setLstcalInteBalance(double lstcalInteBalance) {
		this.lstcalInteBalance = lstcalInteBalance;
	}

	public Date getInteStartDate() {
		return inteStartDate;
	}
	public void setInteStartDate(Date inteStartDate) {
		this.inteStartDate = inteStartDate;
	}
	public String getCurrcode() {
		return currcode;
	}
	public void setCurrcode(String currcode) {
		this.currcode = currcode;
	}
	public BkCashAcct getBkcashAcct() {
		return bkcashAcct;
	}
	public void setBkcashAcct(BkCashAcct bkcashAcct) {
		this.bkcashAcct = bkcashAcct;
	}
	public List<BankBalanceObj> getBankBalanceList() {
		return bankBalanceList;
	}
	public void setBankBalanceList(List<BankBalanceObj> bankBalanceList) {
		this.bankBalanceList = bankBalanceList;
	}
	
	public BankBalanceWrapObj(BkCashAcct bkcashAcct) {
		setBkcashAcct(bkcashAcct);
	}
	public Date getInteLastCycleEndDate() {
		return inteLastCycleEndDate;
	}
	public void setInteLastCycleEndDate(Date inteLastCycleEndDate) {
		this.inteLastCycleEndDate = inteLastCycleEndDate;
	}
	public Date getInteEndDate() {
		return inteEndDate;
	}
	public void setInteEndDate(Date inteEndDate) {
		this.inteEndDate = inteEndDate;
	}
}
