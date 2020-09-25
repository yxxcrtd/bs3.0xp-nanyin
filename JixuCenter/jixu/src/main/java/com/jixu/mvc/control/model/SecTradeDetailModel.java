package com.jixu.mvc.control.model;

import java.util.Date;

/** 
 * @ClassName 		SecTradeDetailModel
 * @author  		Pan Wei 
 * @Date 			2016年8月18日 下午2:17:04 	
 * @Description		[]  
 */

public class SecTradeDetailModel {
	private String tanum;
	private String portnum;
	private String portnum2;
	private String secID;
	private String tradeDate;
	private String setDate;
	private int tranType;
	private String brokerCode;
	private int bankCode;
	private int cashacct;
	private int cashacct2;
	private double units;
	private double price;
	private int feeConfigNum;
	private String fxcurrency;
	private String fxCurCode;
	private double principal;
	private String setCurID;
	private String setcurCode;
	private double setCash;
	private double setFxRate;
	private String setcurCode2;
	private double setCash2;
	private double setFxRate2;
	private double fxrate;
	private double fxrate2;
	private int manager;
	private String secacct;
	private String secacct2;
	private int tradeMarket;
	private String ledgerCurrency;
	private String ledgerCurCode;
	private double cash;
	private String secType;
	private double feeTotal;
	private double feeTotal2;
	private String alDate;
	private double alRate;
	private double amount;
	private double amount2;
	private double intatmat;
	private boolean setIntatmat;
	private double intatmat2;
	private boolean setIntatmat2;
	private String fxcurrency2;
	private String fxCurCode2;
	private int dbcr;
	private String desc;
	private String glacct;
	private int glcol;
	private Date divBaseDate;
	private double cash2;
	private String secID2;
	private double principal2;
	private double price2;
	private String settleDate2;
	private int intent;
	private String contract;
	private int hiloCode;
	private int specificLotID;
	/**
	 * 在swap交易中，判定前后端是否结转现金的标记
	 * 无/不结转, 前端结转，后端结转，前后和后端结转
	 */
	private int swapExchangeInd;
	
	
	public String getTanum() {
		return tanum;
	}
	public void setTanum(String tanum) {
		this.tanum = tanum;
	}
	public String getPortnum() {
		return portnum;
	}
	public void setPortnum(String portnum) {
		this.portnum = portnum;
	}
	
	public String getPortnum2() {
		return portnum2;
	}
	public void setPortnum2(String portnum2) {
		this.portnum2 = portnum2;
	}
	public String getSecID() {
		if(secID==null){
			return "-";
		}else{
			return secID;
		}
	}
	public void setSecID(String secID) {
		this.secID = secID;
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
	public int getTranType() {
		return tranType;
	}
	public void setTranType(int tranType) {
		this.tranType = tranType;
	}
	public String getBrokerCode() {
		return brokerCode;
	}
	public void setBrokerCode(String brokerCode) {
		this.brokerCode = brokerCode;
	}
	public int getBankCode() {
		return bankCode;
	}
	public void setBankCode(int bankCode) {
		this.bankCode = bankCode;
	}
	public double getUnits() {
		return units;
	}
	public void setUnits(double units) {
		this.units = units;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getFeeConfigNum() {
		return feeConfigNum;
	}
	public void setFeeConfigNum(int feeConfigNum) {
		this.feeConfigNum = feeConfigNum;
	}
	public String getFxcurrency() {
		return fxcurrency;
	}
	public void setFxcurrency(String fxcurrency) {
		this.fxcurrency = fxcurrency;
	}
	public double getPrincipal() {
		return principal;
	}
	public void setPrincipal(double principal) {
		this.principal = principal;
	}
	public String getSetCurID() {
		return setCurID;
	}
	public void setSetCurID(String setCurID) {
		this.setCurID = setCurID;
	}
	public double getSetCash() {
		return setCash;
	}
	public void setSetCash(double setCash) {
		this.setCash = setCash;
	}
	public double getSetFxRate() {
		return setFxRate;
	}
	public void setSetFxRate(double setFxRate) {
		this.setFxRate = setFxRate;
	}
	public double getFxrate() {
		return fxrate;
	}
	public void setFxrate(double fxrate) {
		this.fxrate = fxrate;
	}
	public int getManager() {
		return manager;
	}
	public void setManager(int manager) {
		this.manager = manager;
	}
	public String getSecacct() {
		return secacct;
	}
	public void setSecacct(String secacct) {
		this.secacct = secacct;
	}
	public int getTradeMarket() {
		return tradeMarket;
	}
	public void setTradeMarket(int tradeMarket) {
		this.tradeMarket = tradeMarket;
	}
	public String getLedgerCurrency() {
		return ledgerCurrency;
	}
	public void setLedgerCurrency(String ledgerCurrency) {
		this.ledgerCurrency = ledgerCurrency;
	}
	public double getCash() {
		return cash;
	}
	public void setCash(double cash) {
		this.cash = cash;
	}
	public String getSecType() {
		return secType;
	}
	public void setSecType(String secType) {
		this.secType = secType;
	}
	public double getFeeTotal() {
		return feeTotal;
	}
	public void setFeeTotal(double feeTotal) {
		this.feeTotal = feeTotal;
	}
	
	public double getFeeTotal2() {
		return feeTotal2;
	}
	public void setFeeTotal2(double feeTotal2) {
		this.feeTotal2 = feeTotal2;
	}
	
	
	public String getAlDate() {
		return alDate;
	}
	public void setAlDate(String alDate) {
		this.alDate = alDate;
	}
	public double getAlRate() {
		return alRate;
	}
	public void setAlRate(double alRate) {
		this.alRate = alRate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getAmount2() {
		return amount2;
	}
	public void setAmount2(double amount2) {
		this.amount2 = amount2;
	}
	public double getIntatmat() {
		return intatmat;
	}
	public void setIntatmat(double intatmat) {
		this.intatmat = intatmat;
	}
	public int getCashacct() {
		return cashacct;
	}
	public void setCashacct(int cashacct) {
		this.cashacct = cashacct;
	}
	public int getCashacct2() {
		return cashacct2;
	}
	public void setCashacct2(int cashacct2) {
		this.cashacct2 = cashacct2;
	}
	public String getFxcurrency2() {
		return fxcurrency2;
	}
	public void setFxcurrency2(String fxcurrency2) {
		this.fxcurrency2 = fxcurrency2;
	}
	public int getDbcr() {
		return dbcr;
	}
	public void setDbcr(int dbcr) {
		this.dbcr = dbcr;
	}
	public String getDesc() {
		if(desc==null){
			return "-";
		}else{
			return desc;
		}
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getGlacct() {
		return glacct;
	}
	public void setGlacct(String glacct) {
		this.glacct = glacct;
	}
	public int getGlcol() {
		return glcol;
	}
	public void setGlcol(int glcol) {
		this.glcol = glcol;
	}
	public double getFxrate2() {
		return fxrate2;
	}
	public void setFxrate2(double fxrate2) {
		this.fxrate2 = fxrate2;
	}
	public String getFxCurCode() {
		return fxCurCode;
	}
	public void setFxCurCode(String fxCurCode) {
		this.fxCurCode = fxCurCode;
	}
	public String getSetcurCode() {
		return setcurCode;
	}
	public void setSetcurCode(String setcurCode) {
		this.setcurCode = setcurCode;
	}
	public String getLedgerCurCode() {
		return ledgerCurCode;
	}
	public void setLedgerCurCode(String ledgerCurCode) {
		this.ledgerCurCode = ledgerCurCode;
	}
	public String getFxCurCode2() {
		return fxCurCode2;
	}
	public void setFxCurCode2(String fxCurCode2) {
		this.fxCurCode2 = fxCurCode2;
	}
	public String getSecacct2() {
		return secacct2;
	}
	public void setSecacct2(String secacct2) {
		this.secacct2 = secacct2;
	}
	/**
	 * 获取 divBaseDate
	 */
	
	public Date getDivBaseDate() {
		return divBaseDate;
	}
	/**
	 * 设置 divBaseDate
	 */
	
	public void setDivBaseDate(Date divBaseDate) {
		this.divBaseDate = divBaseDate;
	}
	/**
	 * 获取 cash2
	 */
	
	public double getCash2() {
		return cash2;
	}
	/**
	 * 设置 cash2
	 */
	
	public void setCash2(double cash2) {
		this.cash2 = cash2;
	}
	/**
	 * 获取 secID2
	 */
	
	public String getSecID2() {
		return secID2;
	}
	/**
	 * 设置 secID2
	 */
	
	public void setSecID2(String secID2) {
		this.secID2 = secID2;
	}
	/**
	 * 获取 principal2
	 */
	
	public double getPrincipal2() {
		return principal2;
	}
	/**
	 * 设置 principal2
	 */
	
	public void setPrincipal2(double principal2) {
		this.principal2 = principal2;
	}
	/**
	 * 获取 price2
	 */
	
	public double getPrice2() {
		return price2;
	}
	/**
	 * 设置 price2
	 */
	
	public void setPrice2(double price2) {
		this.price2 = price2;
	}
	/**
	 * 获取 settleDate2
	 */
	
	public String getSettleDate2() {
		return settleDate2;
	}
	/**
	 * 设置 settleDate2
	 */
	
	public void setSettleDate2(String settleDate2) {
		this.settleDate2 = settleDate2;
	}
	public int getIntent() {
		return intent;
	}
	public void setIntent(int intent) {
		this.intent = intent;
	}
	public String getContract() {
		return contract;
	}
	public void setContract(String contract) {
		this.contract = contract;
	}
	public int getHiloCode() {
		return hiloCode;
	}
	public void setHiloCode(int hiloCode) {
		this.hiloCode = hiloCode;
	}
	public int getSpecificLotID() {
		return specificLotID;
	}
	public void setSpecificLotID(int specificLotID) {
		this.specificLotID = specificLotID;
	}
	public String getSetcurCode2() {
		return setcurCode2;
	}
	public void setSetcurCode2(String setcurCode2) {
		this.setcurCode2 = setcurCode2;
	}
	public double getSetCash2() {
		return setCash2;
	}
	public void setSetCash2(double setCash2) {
		this.setCash2 = setCash2;
	}
	public double getSetFxRate2() {
		return setFxRate2;
	}
	public void setSetFxRate2(double setFxRate2) {
		this.setFxRate2 = setFxRate2;
	}
	public double getIntatmat2() {
		return intatmat2;
	}
	public void setIntatmat2(double intatmat2) {
		this.intatmat2 = intatmat2;
	}
	public boolean isSetIntatmat() {
		return setIntatmat;
	}
	public void setSetIntatmat(boolean setIntatmat) {
		this.setIntatmat = setIntatmat;
	}
	public boolean isSetIntatmat2() {
		return setIntatmat2;
	}
	public void setSetIntatmat2(boolean setIntatmat2) {
		this.setIntatmat2 = setIntatmat2;
	}
	public int getSwapExchangeInd() {
		return swapExchangeInd;
	}
	public void setSwapExchangeInd(int swapExchangeInd) {
		this.swapExchangeInd = swapExchangeInd;
	}
}
