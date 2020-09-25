package com.jixu.mvc.control.model;

import java.util.Date;

/** 
 * @ClassName 		MarketStockDetailModel
 * @author  		Pan Wei 
 * @Date 			2016年8月15日 上午10:00:39 	
 * @Description		[]  
 */

public class MarketStockDetailModel {
	private String cusip;
	private String secID;
	private String secName;
	private String secType;
	private String issuerCode;
	private String countryID;
	private String exchangeID;
	private String prinCurID;
	private String prinCurID2;
	private String incomeCurID;
	private String incomeCurID2;
	private int coaNum;
	private int assetType3;
	private String assetType2;
	private String assetType1;
	private int sourceID;
	private Date expireDate;
	private double contractSize;
	private String underSecName;
	private int optionType;
	
	/*private int cpnType;
	private double cpnRate;
	private int cpnFeq;
	private int initCode;
	private int cpnDelayFlag;
	private int cpnDelayHoliday;
	private int cpnDelayDateType;
	private int cpnDelayDateNum;*/
	
	private String cashFlowCfgID;
	private String cashFlowCfgID2;
	private String activeDate;
	//固收类型
	private int fixIncomeType;
	//发行价
	private double startPrice;
	//到期价
	private double endPrice;
	private int swapExchangeInd;
	//多方货币
	private String longCurID;
	//多方金额
	private double longCash;
	//空方货币
	private String shortCurID;
	//空方金额
	private double shortCash;
	//行权汇率
	private double executeFX;
	//期货类型
	private int futuresType;
	//合约标的
	private int futuresTarget;
	//保证金
	private double marginRate;
	
	public String getCusip() {
		return cusip;
	}
	public void setCusip(String cusip) {
		this.cusip = cusip;
	}
	public String getSecID() {
		return secID;
	}
	public void setSecID(String secID) {
		this.secID = secID;
	}
	public String getSecType() {
		return secType;
	}
	public void setSecType(String secType) {
		this.secType = secType;
	}
	public String getIssuerCode() {
		return issuerCode;
	}
	public void setIssuerCode(String issuerCode) {
		this.issuerCode = issuerCode;
	}
	public String getCountryID() {
		return countryID;
	}
	public void setCountryID(String countryID) {
		this.countryID = countryID;
	}
	public String getExchangeID() {
		return exchangeID;
	}
	public void setExchangeID(String exchangeID) {
		this.exchangeID = exchangeID;
	}
	public String getPrinCurID() {
		return prinCurID;
	}
	public void setPrinCurID(String prinCurID) {
		this.prinCurID = prinCurID;
	}
	public String getIncomeCurID() {
		return incomeCurID;
	}
	public void setIncomeCurID(String incomeCurID) {
		this.incomeCurID = incomeCurID;
	}
	public int getCoaNum() {
		return coaNum;
	}
	public void setCoaNum(int coaNum) {
		this.coaNum = coaNum;
	}
	public int getAssetType3() {
		return assetType3;
	}
	public void setAssetType3(int assetType3) {
		this.assetType3 = assetType3;
	}
	
	public String getAssetType2() {
		return assetType2;
	}

	public void setAssetType2(String assetType2) {
		this.assetType2 = assetType2;
	}

	public String getAssetType1() {
		return assetType1;
	}

	public void setAssetType1(String assetType1) {
		this.assetType1 = assetType1;
	}

	public int getSourceID() {
		return sourceID;
	}
	public void setSourceID(int sourceID) {
		this.sourceID = sourceID;
	}

	public String getSecName() {
		return secName;
	}

	public void setSecName(String secName) {
		this.secName = secName;
	}

	public String getCashFlowCfgID() {
		return cashFlowCfgID;
	}

	public void setCashFlowCfgID(String cashFlowCfgID) {
		this.cashFlowCfgID = cashFlowCfgID;
	}

	/**
	 * 获取 expireDate
	 */
	
	public Date getExpireDate() {
		return expireDate;
	}

	/**
	 * 设置 expireDate
	 */
	
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	/**
	 * 获取 contractSize
	 */
	
	public double getContractSize() {
		return contractSize;
	}

	/**
	 * 设置 contractSize
	 */
	
	public void setContractSize(double contractSize) {
		this.contractSize = contractSize;
	}

	/**
	 * 获取 underSecName
	 */
	
	public String getUnderSecName() {
		return underSecName;
	}

	/**
	 * 设置 underSecName
	 */
	
	public void setUnderSecName(String underSecName) {
		this.underSecName = underSecName;
	}

	/**
	 * 获取 optionType
	 */
	
	public int getOptionType() {
		return optionType;
	}

	/**
	 * 设置 optionType
	 */
	
	public void setOptionType(int optionType) {
		this.optionType = optionType;
	}

	/**
	 * 获取 activeDate
	 */
	
	public String getActiveDate() {
		return activeDate;
	}

	/**
	 * 设置 activeDate
	 */
	
	public void setActiveDate(String activeDate) {
		this.activeDate = activeDate;
	}

	public int getFixIncomeType() {
		return fixIncomeType;
	}

	public void setFixIncomeType(int fixIncomeType) {
		this.fixIncomeType = fixIncomeType;
	}

	public double getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(double startPrice) {
		this.startPrice = startPrice;
	}

	public double getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(double endPrice) {
		this.endPrice = endPrice;
	}

	public String getPrinCurID2() {
		return prinCurID2;
	}

	public void setPrinCurID2(String prinCurID2) {
		this.prinCurID2 = prinCurID2;
	}

	public String getIncomeCurID2() {
		return incomeCurID2;
	}

	public void setIncomeCurID2(String incomeCurID2) {
		this.incomeCurID2 = incomeCurID2;
	}

	public String getCashFlowCfgID2() {
		return cashFlowCfgID2;
	}

	public void setCashFlowCfgID2(String cashFlowCfgID2) {
		this.cashFlowCfgID2 = cashFlowCfgID2;
	}
	public int getSwapExchangeInd() {
		return swapExchangeInd;
	}
	public void setSwapExchangeInd(int swapExchangeInd) {
		this.swapExchangeInd = swapExchangeInd;
	}
	public String getLongCurID() {
		return longCurID;
	}
	public void setLongCurID(String longCurID) {
		this.longCurID = longCurID;
	}
	public double getLongCash() {
		return longCash;
	}
	public void setLongCash(double longCash) {
		this.longCash = longCash;
	}
	public String getShortCurID() {
		return shortCurID;
	}
	public void setShortCurID(String shortCurID) {
		this.shortCurID = shortCurID;
	}
	public double getShortCash() {
		return shortCash;
	}
	public void setShortCash(double shortCash) {
		this.shortCash = shortCash;
	}
	public double getExecuteFX() {
		return executeFX;
	}
	public void setExecuteFX(double executeFX) {
		this.executeFX = executeFX;
	}
	public int getFuturesType() {
		return futuresType;
	}
	public void setFuturesType(int futuresType) {
		this.futuresType = futuresType;
	}
	public int getFuturesTarget() {
		return futuresTarget;
	}
	public void setFuturesTarget(int futuresTarget) {
		this.futuresTarget = futuresTarget;
	}
	public double getMarginRate() {
		return marginRate;
	}
	public void setMarginRate(double marginRate) {
		this.marginRate = marginRate;
	}
}
