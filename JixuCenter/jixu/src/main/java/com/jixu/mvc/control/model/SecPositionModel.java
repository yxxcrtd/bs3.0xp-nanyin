package com.jixu.mvc.control.model;

import com.jixu.common.util.FieldMeta;


public class SecPositionModel {
	/**
	 * 证券代码
	 */
	@FieldMeta(desc="证券代码")
	private String secID;
	
	/**
	 * 市场描述
	 */
	@FieldMeta(desc="市场分类")
	private String marketDesc;
	
	@FieldMeta(desc="证券类型")
	private String secType;
	
	@FieldMeta(desc="证券描述")
	private String secDesc;
	
	@FieldMeta(desc="利率")
	private double ratio;
	
	@FieldMeta(desc="截至日期")
	private String expDate;
	
	@FieldMeta(desc="数量")
	private double amount;
	
	@FieldMeta(desc="成本(原币)")
	private double cost;
	
	@FieldMeta(desc="成本(本币)")
	private double funcCost;
	
	@FieldMeta(desc="市值(原币)")
	private double mkValue;
	
	@FieldMeta(desc="市值(本币)")
	private double funMkValue;
	
	@FieldMeta(desc="货币")
	private String curCode;
	
	@FieldMeta(desc="汇率")
	private double fxRate;

	@FieldMeta(desc="货币代码")
	private String funcCurCode;
	
	@FieldMeta(desc="摊余成本")
	private double amortValue;
	
	/*@FieldMeta(desc="计息日")
	private String altDate;
	
	@FieldMeta(desc="计息比率")
	private String altRate;*/
	
	@FieldMeta(desc="利息(原币)")
	private double accrued;
	
	@FieldMeta(desc="利息(本币)")
	private double accruedFunc;
	
	@FieldMeta(desc="产品名称")
	private String portname;
	
	@FieldMeta(desc="日期")
	private String asOfDate;
	
	//通过lotnum区分交易
	private int lotnum;
	
	private String cusip;
	
	//多空类型
	private int lsindicator;
	
	public String getSecID() {
		return secID;
	}

	public void setSecID(String secID) {
		this.secID = secID;
	}

	public String getMarketDesc() {
		return marketDesc;
	}

	public void setMarketDesc(String marketDesc) {
		this.marketDesc = marketDesc;
	}

	public String getSecType() {
		return secType;
	}

	public void setSecType(String secType) {
		this.secType = secType;
	}

	public String getSecDesc() {
		return secDesc;
	}

	public void setSecDesc(String secDesc) {
		this.secDesc = secDesc;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getFuncCost() {
		return funcCost;
	}

	public void setFuncCost(double funcCost) {
		this.funcCost = funcCost;
	}

	public double getMkValue() {
		return mkValue;
	}

	public void setMkValue(double mkValue) {
		this.mkValue = mkValue;
	}

	public double getFunMkValue() {
		return funMkValue;
	}

	public void setFunMkValue(double funMkValue) {
		this.funMkValue = funMkValue;
	}

	public String getCurCode() {
		return curCode;
	}

	public void setCurCode(String curCode) {
		this.curCode = curCode;
	}

	public double getFxRate() {
		return fxRate;
	}

	public void setFxRate(double fxRate) {
		this.fxRate = fxRate;
	}

	public String getFuncCurCode() {
		return funcCurCode;
	}

	public void setFuncCurCode(String funcCurCode) {
		this.funcCurCode = funcCurCode;
	}

	/*public String getAltDate() {
		return altDate;
	}

	public void setAltDate(String altDate) {
		this.altDate = altDate;
	}

	public String getAltRate() {
		return altRate;
	}

	public void setAltRate(String altRate) {
		this.altRate = altRate;
	}*/
	public int getLotnum() {
		return lotnum;
	}

	public double getAccrued() {
		return accrued;
	}

	public void setAccrued(double accrued) {
		this.accrued = accrued;
	}

	public double getAccruedFunc() {
		return accruedFunc;
	}

	public void setAccruedFunc(double accruedFunc) {
		this.accruedFunc = accruedFunc;
	}

	public void setLotnum(int lotnum) {
		this.lotnum = lotnum;
	}

	public String getCusip() {
		return cusip;
	}

	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	public double getAmortValue() {
		return amortValue;
	}

	public void setAmortValue(double amortValue) {
		this.amortValue = amortValue;
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

	public int getLsindicator() {
		return lsindicator;
	}

	public void setLsindicator(int lsindicator) {
		this.lsindicator = lsindicator;
	}
}
