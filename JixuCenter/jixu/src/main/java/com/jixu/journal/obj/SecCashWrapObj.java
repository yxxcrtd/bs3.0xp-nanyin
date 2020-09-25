package com.jixu.journal.obj;

import java.io.Serializable;
import java.util.List;

public class SecCashWrapObj implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1316249358405050997L;
	
	private List<SecCashObj> cashbalanceList;//证券清算款列表
	private String currcode; //清算款币种
	private int bankCashNo; //银行现金账户ID.
	private int groupnum; //所属总账组
	private String currShortCode;
	private int tranType;
	private String tanum;
	private String issueType;	//证券类型
	private String secid;
	private boolean mergeFlag;

	public int getGroupnum() {
		return groupnum;
	}
	public void setGroupnum(int groupnum) {
		this.groupnum = groupnum;
	}
	public int getBankCashNo() {
		return bankCashNo;
	}
	public void setBankCashNo(int bankCashNo) {
		this.bankCashNo = bankCashNo;
	}
	public String getCurrcode() {
		return currcode;
	}
	public void setCurrcode(String currcode) {
		this.currcode = currcode;
	}
	public List<SecCashObj> getCashbalanceList() {
		return cashbalanceList;
	}
	public void setCashbalanceList(List<SecCashObj> cashbalanceList) {
		this.cashbalanceList = cashbalanceList;
	}
	public String getCurrShortCode() {
		return currShortCode;
	}
	public void setCurrShortCode(String currShortCode) {
		this.currShortCode = currShortCode;
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
	public boolean isMergeFlag() {
		return mergeFlag;
	}
	public void setMergeFlag(boolean mergeFlag) {
		this.mergeFlag = mergeFlag;
	}
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	public String getSecid() {
		return secid;
	}
	public void setSecid(String secid) {
		this.secid = secid;
	}
}
