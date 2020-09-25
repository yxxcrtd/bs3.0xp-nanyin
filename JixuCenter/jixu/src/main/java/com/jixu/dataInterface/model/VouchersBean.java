package com.jixu.dataInterface.model;

import java.sql.Date;

import com.jixu.common.util.FieldMeta;

/** 
 * @ClassName 		VouchersBean
 * @author  		Pan Wei 
 * @Date 			2018年6月1日 下午3:55:14 	
 * @Description		[]  
 */

public class VouchersBean {
	@FieldMeta(desc="凭证编号")
	private int vouchernum;
	
	@FieldMeta(desc="日期")
	private Date ledgerDate;
	
	@FieldMeta(desc="科目编号")
	private String acctnum;
	
	@FieldMeta(desc="科目名称")
	private String acctname;
	
	@FieldMeta(desc="凭证类型")
	private String journalDesc;
	
	@FieldMeta(desc="摘要")
	private String desciption;
	
	@FieldMeta(desc="借方(原币)")
	private double drLocal;
	
	@FieldMeta(desc="贷方(原币)")
	private double crLocal;
	
	@FieldMeta(desc="借方(本币)")
	private double drFunc;
	
	@FieldMeta(desc="贷方(本币)")
	private double crFunc;
	
	@FieldMeta(desc="数量")
	private double amount;

	@FieldMeta(desc="产品名称")
	private String portname;
	
	@FieldMeta(desc="证券代码")
	private String secid;
	
	private int journalCode;

	public int getVouchernum() {
		return vouchernum;
	}

	public void setVouchernum(int vouchernum) {
		this.vouchernum = vouchernum;
	}

	public Date getLedgerDate() {
		return ledgerDate;
	}

	public void setLedgerDate(Date ledgerDate) {
		this.ledgerDate = ledgerDate;
	}

	public String getAcctnum() {
		return acctnum;
	}

	public void setAcctnum(String acctnum) {
		this.acctnum = acctnum;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public double getDrLocal() {
		return drLocal;
	}

	public void setDrLocal(double drLocal) {
		this.drLocal = drLocal;
	}

	public double getCrLocal() {
		return crLocal;
	}

	public void setCrLocal(double crLocal) {
		this.crLocal = crLocal;
	}

	public double getDrFunc() {
		return drFunc;
	}

	public void setDrFunc(double drFunc) {
		this.drFunc = drFunc;
	}

	public double getCrFunc() {
		return crFunc;
	}

	public void setCrFunc(double crFunc) {
		this.crFunc = crFunc;
	}

	public String getAcctname() {
		return acctname;
	}

	public void setAcctname(String acctname) {
		this.acctname = acctname;
	}

	public String getJournalDesc() {
		return journalDesc;
	}

	public void setJournalDesc(String journalDesc) {
		this.journalDesc = journalDesc;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getJournalCode() {
		return journalCode;
	}

	public void setJournalCode(int journalCode) {
		this.journalCode = journalCode;
	}

	public String getPortname() {
		return portname;
	}

	public void setPortname(String portname) {
		this.portname = portname;
	}

	public String getSecid() {
		return secid;
	}

	public void setSecid(String secid) {
		this.secid = secid;
	}
}
