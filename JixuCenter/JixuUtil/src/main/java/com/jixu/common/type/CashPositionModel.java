package com.jixu.common.type;

import com.jixu.common.util.FieldMeta;


public class CashPositionModel {
	//银行", "账户", "账户名称", "货币", "当前余额", "当前价值
	
	private int cashacctID;
	
	@FieldMeta(desc="银行")
	private String bank;
	
	@FieldMeta(desc="银行帐号")
	private String bankAcct;
	
	@FieldMeta(desc="银行帐号名称")
	private String bankAcctName;
	
	@FieldMeta(desc="原币货币")
	private String localCurCode;
	
	@FieldMeta(desc="原币金额")
	private double localBalance;
	
	@FieldMeta(desc="本币货币")
	private String funcCurCode;
	
	@FieldMeta(desc="本币金额")
	private double funcBalance;
	
	@FieldMeta(desc="产品名称")
	private String portname;
	
	@FieldMeta(desc="截至日期")
	private String asOfDate;
	
	//汇率
	private double fxRate;
	
	//货币id
	private String curCode;
	
	//利息收入
	private double interestIncome;
	
	public CashPositionModel(int cashacctID,String bank,String bankAcct,String bankAcctName,String localCurCode,double localBalance,String curCode){
		setCashacctID(cashacctID);
		setBankAcct(bank);
		setBankAcct(bankAcct);
		setBankAcctName(bankAcctName);
		setLocalCurCode(localCurCode);
		setLocalBalance(localBalance);
		setCurCode(curCode);
	}
	
	public CashPositionModel(){
		
	}
	
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBankAcct() {
		return bankAcct;
	}
	public void setBankAcct(String bankAcct) {
		this.bankAcct = bankAcct;
	}
	public String getBankAcctName() {
		return bankAcctName;
	}
	public void setBankAcctName(String bankAcctName) {
		this.bankAcctName = bankAcctName;
	}
	public String getLocalCurCode() {
		return localCurCode;
	}
	public void setLocalCurCode(String localCurCode) {
		this.localCurCode = localCurCode;
	}
	public double getLocalBalance() {
		return localBalance;
	}
	public void setLocalBalance(double localBalance) {
		this.localBalance = localBalance;
	}
	public String getFuncCurCode() {
		return funcCurCode;
	}
	public void setFuncCurCode(String funcCurCode) {
		this.funcCurCode = funcCurCode;
	}
	public double getFuncBalance() {
		return funcBalance;
	}
	public void setFuncBalance(double funcBalance) {
		this.funcBalance = funcBalance;
	}
	public int getCashacctID() {
		return cashacctID;
	}
	public void setCashacctID(int cashacctID) {
		this.cashacctID = cashacctID;
	}
	public double getFxRate() {
		return fxRate;
	}
	public void setFxRate(double fxRate) {
		this.fxRate = fxRate;
	}
	public String getCurCode() {
		return curCode;
	}
	public void setCurCode(String curCode) {
		this.curCode = curCode;
	}
	public String getPortname() {
		return portname;
	}
	public void setPortname(String portname) {
		this.portname = portname;
	}
	public String getAsOfDate() {
		return asOfDate;
	}
	public void setAsOfDate(String asOfDate) {
		this.asOfDate = asOfDate;
	}
	public double getInterestIncome() {
		return interestIncome;
	}
	public void setInterestIncome(double interestIncome) {
		this.interestIncome = interestIncome;
	}
}
