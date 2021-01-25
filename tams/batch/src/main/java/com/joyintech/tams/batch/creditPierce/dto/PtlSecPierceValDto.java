package com.joyintech.tams.batch.creditPierce.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
* 授信估值穿透数据接入实体类
*/
public class PtlSecPierceValDto {
	private static final long serialVersionUID = 1L;
	/**日期*/
	private Date cdate;
	/**组合代码*/
	private String portfolioId;
	/**金融产品代码*/
	private String finprodId;
	/**币种*/
	private String ccy;
	/**数量*/
	private BigDecimal shareAmt;
	/**摊销总成本*/
	private BigDecimal dscCostAmt;
	/**摊销成本净价*/
	private BigDecimal dscCleanPrice;
	/**买入总成本*/
	private BigDecimal buyCostAmt;
	/**买入成本净价*/
	private BigDecimal buyCleanPrice;
	/**市值*/
	private BigDecimal marketValue;
	/**市价净价*/
	private BigDecimal marketCleanPrice;
	/**当日应计利息*/
	private BigDecimal tdyIntincexp;
	/**当日发生摊销收入*/
	private BigDecimal tdy_dscincexpAdd;
	/**当日发生利息收入*/
	private BigDecimal tdyIntincexpAdd;
	/**当日发生价差收入*/
	private BigDecimal tdy_dsclossAdd;
	/**当日发生浮动盈亏*/
	private BigDecimal tdyFloatIngplAdd;
	/**当日发生费用支出*/
	private BigDecimal tdyFeeAdd;
	/**累计单位净值*/
	private BigDecimal accuNetValue;
	/**科目代码*/
	private String subCode;
	/**资管计划代码*/
	private String zgplanId;
	/**组织机构代码*/
	private String organId;
	/**品种类型1*/
	private String varietyType1;
	/**品种类型2*/
	private String varietyType2;
	/** 授信配置代码 */
	private String creditMstId;
	/** 资产类型 */
	private String assetType;

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getFinprodId() {
		return finprodId;
	}

	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public BigDecimal getShareAmt() {
		return shareAmt;
	}

	public void setShareAmt(BigDecimal shareAmt) {
		this.shareAmt = shareAmt;
	}

	public BigDecimal getDscCostAmt() {
		return dscCostAmt;
	}

	public void setDscCostAmt(BigDecimal dscCostAmt) {
		this.dscCostAmt = dscCostAmt;
	}

	public BigDecimal getDscCleanPrice() {
		return dscCleanPrice;
	}

	public void setDscCleanPrice(BigDecimal dscCleanPrice) {
		this.dscCleanPrice = dscCleanPrice;
	}

	public BigDecimal getBuyCostAmt() {
		return buyCostAmt;
	}

	public void setBuyCostAmt(BigDecimal buyCostAmt) {
		this.buyCostAmt = buyCostAmt;
	}

	public BigDecimal getBuyCleanPrice() {
		return buyCleanPrice;
	}

	public void setBuyCleanPrice(BigDecimal buyCleanPrice) {
		this.buyCleanPrice = buyCleanPrice;
	}

	public BigDecimal getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(BigDecimal marketValue) {
		this.marketValue = marketValue;
	}

	public BigDecimal getMarketCleanPrice() {
		return marketCleanPrice;
	}

	public void setMarketCleanPrice(BigDecimal marketCleanPrice) {
		this.marketCleanPrice = marketCleanPrice;
	}

	public BigDecimal getTdyIntincexp() {
		return tdyIntincexp;
	}

	public void setTdyIntincexp(BigDecimal tdyIntincexp) {
		this.tdyIntincexp = tdyIntincexp;
	}

	public BigDecimal getTdy_dscincexpAdd() {
		return tdy_dscincexpAdd;
	}

	public void setTdy_dscincexpAdd(BigDecimal tdy_dscincexpAdd) {
		this.tdy_dscincexpAdd = tdy_dscincexpAdd;
	}

	public BigDecimal getTdyIntincexpAdd() {
		return tdyIntincexpAdd;
	}

	public void setTdyIntincexpAdd(BigDecimal tdyIntincexpAdd) {
		this.tdyIntincexpAdd = tdyIntincexpAdd;
	}

	public BigDecimal getTdy_dsclossAdd() {
		return tdy_dsclossAdd;
	}

	public void setTdy_dsclossAdd(BigDecimal tdy_dsclossAdd) {
		this.tdy_dsclossAdd = tdy_dsclossAdd;
	}

	public BigDecimal getTdyFloatIngplAdd() {
		return tdyFloatIngplAdd;
	}

	public void setTdyFloatIngplAdd(BigDecimal tdyFloatIngplAdd) {
		this.tdyFloatIngplAdd = tdyFloatIngplAdd;
	}

	public BigDecimal getTdyFeeAdd() {
		return tdyFeeAdd;
	}

	public void setTdyFeeAdd(BigDecimal tdyFeeAdd) {
		this.tdyFeeAdd = tdyFeeAdd;
	}

	public BigDecimal getAccuNetValue() {
		return accuNetValue;
	}

	public void setAccuNetValue(BigDecimal accuNetValue) {
		this.accuNetValue = accuNetValue;
	}

	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public String getSubCode() {
		return subCode;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	public String getZgplanId() {
		return zgplanId;
	}

	public void setZgplanId(String zgplanId) {
		this.zgplanId = zgplanId;
	}

	public String getVarietyType1() {
		return varietyType1;
	}

	public void setVarietyType1(String varietyType1) {
		this.varietyType1 = varietyType1;
	}

	public String getVarietyType2() {
		return varietyType2;
	}

	public void setVarietyType2(String varietyType2) {
		this.varietyType2 = varietyType2;
	}

	public String getCreditMstId() {
		return creditMstId;
	}

	public void setCreditMstId(String creditMstId) {
		this.creditMstId = creditMstId;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
}
