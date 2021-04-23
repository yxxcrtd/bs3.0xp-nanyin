package com.joyintech.tams.invest.entrust.etrtransactiondetail.dto;

import java.math.BigDecimal;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;

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
	 * 组合名称
	 */
	private String portfolioName;

	/**
	 * 金融产品全称
	 */
	private String finprodName;

	/**
	 * 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
	 */
	private String finprodType2;

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
	 * 券面总额
	 */
	private BigDecimal bondTotalFace;

	/**
	 * <功能详细描述>
	 * 
	 * @return
	 */
	public String getEntrustDateStr() {
		return DateUtil.format(super.getEntrustDate());
	}

	/**
	 * <功能详细描述>
	 * 
	 * @return
	 */
	public String getVdateStr() {
		return DateUtil.format(super.getVdate());
	}

	/**
	 * <功能详细描述>
	 * 
	 * @return
	 */
	public String getMdateStr() {
		return DateUtil.format(super.getMdate());
	}

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

	public String getAssetTypeStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.平安资产类型, this.getAssetType());
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
	    if (StringUtil.equals(DictCodeListExt3.平安资产类型_期货.getValue(), getAssetType())
	        && StringUtil.isNotBlank(finprodMarketId)) {
	        finprodMarketId = StringUtil.splitToList(".", finprodMarketId).get(0);
	    }
	    
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

	public String getEntrustStatusStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.委托状态, super.getEntrustStatus());
	}

	public String getTradeMarketStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.交易场所, super.getTradeMarket());
	}

	public String getTradePlatStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.交易平台, super.getTradePlat());
	}

	public String getCcyStr() {
		return SysDataUtil.getNameFromDataDict(DictCode.币种, super.getCcy());
	}

	// 券面总额
	public BigDecimal getBondTotalFace() {
		return this.bondTotalFace;
	}

	public void setBondTotalFace(BigDecimal bondTotalFace) {
		this.bondTotalFace = bondTotalFace;
	}

	// 指令金额
	public BigDecimal getEntrustAmtStr() {
		return super.getEntrustAmt();
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

	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}

	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	// 资产简称
	public String getFinprodAbbr() {
		return this.finprodName;
	}

	// 期限品种
	public String getTermVriety() {
		return this.finprodMarketId;
	}
	
	//交易数量
	public BigDecimal getTradeNum(){
		return this.shareAmt;
	}
	
	//交易价格
	public BigDecimal getTradePrice(){
		return this.unitCprice;
	}
	
	//回购利率
	public BigDecimal getBackRate(){
		return this.unitCprice;
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
