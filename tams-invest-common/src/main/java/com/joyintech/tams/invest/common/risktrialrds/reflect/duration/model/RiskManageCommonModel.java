package com.joyintech.tams.invest.common.risktrialrds.reflect.duration.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * <b>类名：</b>RiskManageCommonModel<br>
 * <b>描述：</b>债券久期计算所需数据MODEL<br>
 * <b>时间：</b>2016年8月10日 上午10:05:18<br>
 * 
 */
public class RiskManageCommonModel {

	//产品代码
	private String prodId;
	//资产代码
	private String finprodId;
	//资产类型
	private String assetType;
	//开放类型(开放式封闭式)
	private String operationType;
	//申购开放、赎回开放、申赎开放
	private String openType;
	//开放天数类型,每天，固定天数
	private String openDaysType;
	//开放模式，定期开放、高频开放、周期开放
	private String openMode;
	//封闭期限(天)
	private BigDecimal closeDays;
	//开放周期
	private BigDecimal openCycle;
	//开放周期单位
	private BigDecimal openCycleUnit;

	// 资产代码(wind)
	private String finprodMarketId;
	// 一级资产类型
	private String type6;
	// 二级资产类型
	private String type7;
	// 息票品种
	private String couponSpecies;
	// 利率类型
	private String intType;
	//是否永续债
	private String isSus;
	//是否行权
	private String isRight;
	// 票面利率（现金流规则表）
	private BigDecimal paperYield;

	private Date mdate;// 到期日
	private String prodType;// 产品类型
	private BigDecimal mDuration;// 债券市价久期
	private BigDecimal sDuration;// 债券利差久期
	private BigDecimal interestDuration;// 债券利率久期

	//市场 IB 银行间 SH 上交所 SZ 深交所
	private String tradeMarket;

	// 本金到期日
	private Date prinAmtMdate;
	// 本金
	private BigDecimal prinAmt;
	
	//产品最大赎回比例
	private BigDecimal maxRedRate;


	public Date getPrinAmtMdate() {
		return prinAmtMdate;
	}

	public void setPrinAmtMdate(Date prinAmtMdate) {
		this.prinAmtMdate = prinAmtMdate;
	}

	public BigDecimal getPrinAmt() {
		return prinAmt;
	}

	public void setPrinAmt(BigDecimal prinAmt) {
		this.prinAmt = prinAmt;
	}

	public BigDecimal getMaxRedRate() {
		return maxRedRate;
	}

	public void setMaxRedRate(BigDecimal maxRedRate) {
		this.maxRedRate = maxRedRate;
	}

	public Date getMdate() {
		return mdate;
	}

	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}

	/**
	 * @return the prodType
	 */
	public String getProdType() {
		return prodType;
	}

	/**
	 * @param prodType the prodType to set
	 */
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	
	/**
	 * 产品系列
	 */
	private String prodSeries;

	public String getProdSeries() {
		return prodSeries;
	}

	public void setProdSeries(String prodSeries) {
		this.prodSeries = prodSeries;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getOpenType() {
		return openType;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
	}

	public String getOpenDaysType() {
		return openDaysType;
	}

	public void setOpenDaysType(String openDaysType) {
		this.openDaysType = openDaysType;
	}

	public String getOpenMode() {
		return openMode;
	}

	public void setOpenMode(String openMode) {
		this.openMode = openMode;
	}

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public BigDecimal getCloseDays() {
		return closeDays;
	}

	public void setCloseDays(BigDecimal closeDays) {
		this.closeDays = closeDays;
	}

	public BigDecimal getOpenCycle() {
		return openCycle;
	}

	public void setOpenCycle(BigDecimal openCycle) {
		this.openCycle = openCycle;
	}

	public BigDecimal getOpenCycleUnit() {
		return openCycleUnit;
	}

	public void setOpenCycleUnit(BigDecimal openCycleUnit) {
		this.openCycleUnit = openCycleUnit;
	}

	public String getFinprodMarketId() {
		return finprodMarketId;
	}

	public void setFinprodMarketId(String finprodMarketId) {
		this.finprodMarketId = finprodMarketId;
	}

	public String getType6() {
		return type6;
	}

	public void setType6(String type6) {
		this.type6 = type6;
	}

	public String getType7() {
		return type7;
	}

	public void setType7(String type7) {
		this.type7 = type7;
	}

	public String getCouponSpecies() {
		return couponSpecies;
	}

	public void setCouponSpecies(String couponSpecies) {
		this.couponSpecies = couponSpecies;
	}

	public String getIntType() {
		return intType;
	}

	public void setIntType(String intType) {
		this.intType = intType;
	}

	public String getIsSus() {
		return isSus;
	}

	public void setIsSus(String isSus) {
		this.isSus = isSus;
	}

	public String getIsRight() {
		return isRight;
	}

	public void setIsRight(String isRight) {
		this.isRight = isRight;
	}

	public BigDecimal getPaperYield() {
		return paperYield;
	}

	public void setPaperYield(BigDecimal paperYield) {
		this.paperYield = paperYield;
	}

	public BigDecimal getmDuration() {
		return mDuration;
	}

	public void setmDuration(BigDecimal mDuration) {
		this.mDuration = mDuration;
	}

	public BigDecimal getsDuration() {
		return sDuration;
	}

	public void setsDuration(BigDecimal sDuration) {
		this.sDuration = sDuration;
	}

	public BigDecimal getInterestDuration() {
		return interestDuration;
	}

	public void setInterestDuration(BigDecimal interestDuration) {
		this.interestDuration = interestDuration;
	}

	public String getTradeMarket() {
		return tradeMarket;
	}

	public void setTradeMarket(String tradeMarket) {
		this.tradeMarket = tradeMarket;
	}
}
