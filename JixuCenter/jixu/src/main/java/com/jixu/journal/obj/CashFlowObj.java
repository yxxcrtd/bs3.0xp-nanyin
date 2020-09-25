package com.jixu.journal.obj;

import java.io.Serializable;

import com.orm.obj.BkCashAcct;
import com.orm.obj.BkCashRcpy;
import com.orm.obj.IssueInfo;

public class CashFlowObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2516064246708061656L;

	private double cash = 0.0;// 原币
	private double asofdayRate;  
	private IssueInfo cusip;
	private double amount; //暂时不用。
	private BkCashAcct bkCashAcct; //银行现金账号ID.
	private String dbcr; //支出(D)/收入(C)
	private double origcash; //本币
	private double bkavgrate; //银行平均汇率
	private String glacctnum; //冲销科目号
	private BkCashRcpy bkCashRcpy;
	private int tranType;
	private String tanum;
	private int postType;

	public String getGlacctnum() {
		return glacctnum;
	}
	public void setGlacctnum(String glacctnum) {
		this.glacctnum = glacctnum;
	}
	public double getBkavgrate() {
		return bkavgrate;
	}
	public void setBkavgrate(double bkavgrate) {
		this.bkavgrate = bkavgrate;
	}
	public double getOrigcash() {
		return origcash;
	}
	public void setOrigcash(double origcash) {
		this.origcash = origcash;
	}
	public String getDbcr() {
		return dbcr;
	}
	public void setDbcr(String dbcr) {
		this.dbcr = dbcr;
	}
	public BkCashAcct getBkCashAcct() {
		return bkCashAcct;
	}
	public void setBkCashAcct(BkCashAcct bkCashAcct) {
		this.bkCashAcct = bkCashAcct;
	}
	public IssueInfo getCusip() {
		return cusip;
	}
	public void setCusip(IssueInfo cusip) {
		this.cusip = cusip;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getCash() {
		return cash;
	}
	public void setCash(double cash) {
		this.cash = cash;
	}
	public double getAsofdayRate() {
		return asofdayRate;
	}
	public void setAsofdayRate(double asofdayRate) {
		this.asofdayRate = asofdayRate;
	}
	public BkCashRcpy getBkCashRcpy() {
		return bkCashRcpy;
	}
	public void setBkCashRcpy(BkCashRcpy bkCashRcpy) {
		this.bkCashRcpy = bkCashRcpy;
	}
	public int getTranType() {
		return tranType;
	}
	public void setTranType(int tranType) {
		this.tranType = tranType;
	}
	public String getTanum() {
		return tanum;
	}
	public void setTanum(String tanum) {
		this.tanum = tanum;
	}
	public int getPostType() {
		return postType;
	}
	public void setPostType(int postType) {
		this.postType = postType;
	}
}
