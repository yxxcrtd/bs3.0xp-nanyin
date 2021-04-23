package com.joyintech.service.entrust.etrtransactiondetail.dto;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.EtrEntrust;

public class EtrTransactionDetailDto extends EtrEntrust {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 2542342953449761138L;

	/**
	 * 回购拆借方向
	 */
	private String busiPs;

	/**
	 * 资产类型
	 */
	private String assetType;

	/**
	 * 市场代码
	 */
	private String finprodMarketId;
	
	/**
	 * 期限品种
	 */
	private String termVriety;

	/**
	 * 组合名称
	 */
	private String portfolioName;

	/**
	 * 金融产品全称
	 */
	private String finprodName;
	
	/**
	 * 资产简称
	 */
	private String finprodAbbr;
	
	/**
	 * 交易方向文字列
	 */
	private String psStr;
	
	/**
	 * 资产一级分类
	 */
	private String assetType1;
	
	/**
	 * 交易日期
	 */
	private String tradeDate;
	
	/**
	 * 交易金额
	 */
	private BigDecimal tradeAmt;
	
	/**
	 * 交易份额
	 */
	private BigDecimal shareAmt;
	
	/**
	 * 交易价格
	 */
	private BigDecimal tradePrice;
	
	/**
	 * 券面总额
	 */
	private BigDecimal bondTotalFace;
	
	/**
	 * 净价总额
	 */
	private BigDecimal cpriceAmt;
	
	/**
	 * 全价总额
	 */
	private BigDecimal fpriceAmt;
	
	/**
	 * 单位净价
	 */
	private BigDecimal unitCprice;
	
	/**
	 * 单位全价
	 */
	private BigDecimal unitFprice;
	
	/**
	 * 交易数量
	 */
	private BigDecimal tradeNum;
	
	/**
	 * 交易编号
	 */
	private String tradeId;
	
	/**
	 * 成交编号
	 */
	private String fTradeId;
	
	/**
	 * 委托编号
	 */
	private String orderNo;
	
	/**
	 * 保证金金额
	 */
	private BigDecimal depositAmt;
	
	/**
	 * 获取 busiPs
	 * 
	 * @return busiPs.
	 */
	public String getBusiPs() {
		return busiPs;
	}

	/**
	 * 设置 busiPs
	 * 
	 * @param busiPs
	 *            busiPs
	 */
	public void setBusiPs(String busiPs) {
		this.busiPs = busiPs;
	}

	/**
	 * 获取 assetType
	 * 
	 * @return assetType.
	 */
	public String getAssetType() {
		return assetType;
	}

	/**
	 * 设置 assetType
	 * 
	 * @param assetType
	 *            assetType
	 */
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	/**
	 * 获取 finprodMarketId
	 * 
	 * @return finprodMarketId.
	 */
	public String getFinprodMarketId() {
		return finprodMarketId;
	}

	/**
	 * 设置 finprodMarketId
	 * 
	 * @param finprodMarketId
	 *            finprodMarketId
	 */
	public void setFinprodMarketId(String finprodMarketId) {
		this.finprodMarketId = finprodMarketId;
	}

	/**
	 * 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
	 */
	private String finprodType2;

	/**
	 * 获取 portfolioName
	 * 
	 * @return portfolioName.
	 */
	public String getPortfolioName() {
		return portfolioName;
	}

	/**
	 * 设置 portfolioName
	 * 
	 * @param portfolioName
	 *            portfolioName
	 */
	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	/**
	 * 获取 finprodName
	 * 
	 * @return finprodName.
	 */
	public String getFinprodName() {
		return finprodName;
	}

	/**
	 * 设置 finprodName
	 * 
	 * @param finprodName
	 *            finprodName
	 */
	public void setFinprodName(String finprodName) {
		this.finprodName = finprodName;
	}

	/**
	 * 获取 finprodType2
	 * 
	 * @return finprodType2.
	 */
	public String getFinprodType2() {
		return finprodType2;
	}

	/**
	 * 设置 finprodType2
	 * 
	 * @param finprodType2
	 *            finprodType2
	 */
	public void setFinprodType2(String finprodType2) {
		this.finprodType2 = finprodType2;
	}

	public String getPsStr() {
		return psStr;
	}

	public void setPsStr(String psStr) {
		this.psStr = psStr;
	}

	public String getAssetType1() {
		return assetType1;
	}

	public void setAssetType1(String assetType1) {
		this.assetType1 = assetType1;
	}

	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}

	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	public BigDecimal getShareAmt() {
		return shareAmt;
	}

	public void setShareAmt(BigDecimal shareAmt) {
		this.shareAmt = shareAmt;
	}

	public BigDecimal getCpriceAmt() {
		return cpriceAmt;
	}

	public void setCpriceAmt(BigDecimal cpriceAmt) {
		this.cpriceAmt = cpriceAmt;
	}

	public BigDecimal getFpriceAmt() {
		return fpriceAmt;
	}

	public void setFpriceAmt(BigDecimal fpriceAmt) {
		this.fpriceAmt = fpriceAmt;
	}

	public BigDecimal getUnitCprice() {
		return unitCprice;
	}

	public void setUnitCprice(BigDecimal unitCprice) {
		this.unitCprice = unitCprice;
	}

	public BigDecimal getUnitFprice() {
		return unitFprice;
	}

	public void setUnitFprice(BigDecimal unitFprice) {
		this.unitFprice = unitFprice;
	}

	public String getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(String tradeDate) {
		this.tradeDate = tradeDate;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getTermVriety() {
		return termVriety;
	}

	public void setTermVriety(String termVriety) {
		this.termVriety = termVriety;
	}

	public String getFinprodAbbr() {
		return finprodAbbr;
	}

	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}

	public BigDecimal getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(BigDecimal tradePrice) {
		this.tradePrice = tradePrice;
	}

	public BigDecimal getBondTotalFace() {
		return bondTotalFace;
	}

	public void setBondTotalFace(BigDecimal bondTotalFace) {
		this.bondTotalFace = bondTotalFace;
	}

	public BigDecimal getTradeNum() {
		return tradeNum;
	}

	public void setTradeNum(BigDecimal tradeNum) {
		this.tradeNum = tradeNum;
	}

	public String getfTradeId() {
		return fTradeId;
	}

	public void setfTradeId(String fTradeId) {
		this.fTradeId = fTradeId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public BigDecimal getDepositAmt() {
		return depositAmt;
	}

	public void setDepositAmt(BigDecimal depositAmt) {
		this.depositAmt = depositAmt;
	}
}
