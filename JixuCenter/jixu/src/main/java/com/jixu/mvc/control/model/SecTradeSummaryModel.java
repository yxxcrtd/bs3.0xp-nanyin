package com.jixu.mvc.control.model;

import com.jixu.common.util.FieldMeta;

public class SecTradeSummaryModel {
	
	@FieldMeta(desc="内部交易编码")
	private String tanum;
	
	@FieldMeta(desc="交易类型编码")
	private int trantypeCode;
	
	@FieldMeta(desc="交易类型")
	private String trantype;
	
	private String postType;
	
	@FieldMeta(desc="券商名称")
	private String securityName;
	
	@FieldMeta(desc="数量")
	private double amount;
	
	@FieldMeta(desc="交易日期")
	private String tradeDate;
	
	@FieldMeta(desc="结算日期")
	private String setDate;
	
	@FieldMeta(desc="证券代码")
	private String secID;
	
	@FieldMeta(desc="成本")
	private double cost;
	
	@FieldMeta(desc="利息")
	private double interest;
	
	@FieldMeta(desc="费用")
	private double fee;
	
	@FieldMeta(desc="货币")
	private String currency;
	
	@FieldMeta(desc="利率")
	private double rate;
	
	@FieldMeta(desc="银行账户")
	private String bankAcct;
	
	@FieldMeta(desc="空方数量")
	private double amount2;
	
	@FieldMeta(desc="空方成本")
	private double cost2;
	
	@FieldMeta(desc="空方货币")
	private String currency2;
	
	@FieldMeta(desc="空方利率")
	private double rate2;
	
	@FieldMeta(desc="空方银行账户")
	private String bankAcct2;
	
	@FieldMeta(desc="科目号")
	private String glAccount;
	
	@FieldMeta(desc="借贷")
	private String dbcr;
	
	@FieldMeta(desc="交易描述")
	private String tranDesc;

	@FieldMeta(desc="产品名称")
	private String portname;
	
	@FieldMeta(desc="合同编号")
	private String contract;
	
	public String getTanum() {
		return tanum;
	}
	public void setTanum(String tanum) {
		this.tanum = tanum;
	}
	
	public int getTrantypeCode() {
		return trantypeCode;
	}
	public void setTrantypeCode(int trantypeCode) {
		this.trantypeCode = trantypeCode;
	}
	public String getTrantype() {
		return trantype;
	}
	public void setTrantype(String trantype) {
		this.trantype = trantype;
	}
	public String getPostType() {
		return postType;
	}
	public void setPostType(String postType) {
		this.postType = postType;
	}
	/**
	 * 获取 securityName
	 */
	
	public String getSecurityName() {
		return securityName;
	}
	/**
	 * 设置 securityName
	 */
	
	public void setSecurityName(String securityName) {
		this.securityName = securityName;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}
	public String getSetDate() {
		return setDate;
	}
	public void setSetDate(String setDate) {
		this.setDate = setDate;
	}
	public String getSecID() {
		return secID;
	}
	public void setSecID(String secID) {
		this.secID = secID;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public double getAmount2() {
		return amount2;
	}
	public void setAmount2(double amount2) {
		this.amount2 = amount2;
	}
	public String getCurrency2() {
		return currency2;
	}
	public void setCurrency2(String currency2) {
		this.currency2 = currency2;
	}
	public double getRate2() {
		return rate2;
	}
	public void setRate2(double rate2) {
		this.rate2 = rate2;
	}
	public String getBankAcct() {
		return bankAcct;
	}
	public void setBankAcct(String bankAcct) {
		this.bankAcct = bankAcct;
	}
	public String getBankAcct2() {
		return bankAcct2;
	}
	public void setBankAcct2(String bankAcct2) {
		this.bankAcct2 = bankAcct2;
	}
	public String getGlAccount() {
		return glAccount;
	}
	public void setGlAccount(String glAccount) {
		this.glAccount = glAccount;
	}
	public String getDbcr() {
		return dbcr;
	}
	public void setDbcr(String dbcr) {
		this.dbcr = dbcr;
	}
	public double getCost2() {
		return cost2;
	}
	public void setCost2(double cost2) {
		this.cost2 = cost2;
	}

	public String getTranDesc() {
		return tranDesc;
	}

	public void setTranDesc(String tranDesc) {
		this.tranDesc = tranDesc;
	}
	public String getPortname() {
		return portname;
	}
	public void setPortname(String portname) {
		this.portname = portname;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
}
