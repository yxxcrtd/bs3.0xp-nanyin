package com.joyintech.tams.invest.entrust.etrtransactiondetail.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;

public class TrdProductDealDetailDto extends EtrEntrust {

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
	 * 回购利率
	 */
	private BigDecimal backRate;
	
	/**
	 * 券面总额
	 */
	private BigDecimal bondTotalFace;

	/**
	 * 回购期限
	 */
	private BigDecimal term;

	/**
	 * 首次结算日
	 */
	private Date vPayDate;
	/**
	 * 到期结算日
	 */
	private Date mPayDate;

	/**
	 * 原质押券代码
	 */
	private String origRepSecId;

	/**
	 * 质押券代码
	 */
	private String repSecId;

	/**
	 * 质押券数量
	 */
	private BigDecimal repSecNum;

	/**
	 * 质押券名称
	 */
	private String repSecName;

	/**
	 * 成交编号
	 */
	private String fTradeId;

	/**
	 * 委托编号
	 */
	private String orderNo;

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
		return !DictCodeList.平安资产类型_质押式回购.getValue().equals(this.getAssetType()) ? this.finprodMarketId : null;
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
		return DictCodeListExt2.平安资产类型_股票.getValue().equals(this.getAssetType()) ? this.finprodName : null;
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
		return DictCodeList.平安资产类型_基金.getValue().equals(this.getAssetType()) ? this.shareAmt : null;
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
		return DictCodeList.平安资产类型_债券.getValue().equals(this.getAssetType()) ? this.fpriceAmt : null;
	}

	public void setFpriceAmt(BigDecimal fpriceAmt) {
		this.fpriceAmt = fpriceAmt;
	}

	public BigDecimal getUnitCprice() {
		return DictCodeList.平安资产类型_债券.getValue().equals(this.getAssetType()) ? this.unitCprice : null;
	}

	public void setUnitCprice(BigDecimal unitCprice) {
		this.unitCprice = unitCprice;
	}

	public BigDecimal getUnitFprice() {
		return DictCodeList.平安资产类型_债券.getValue().equals(this.getAssetType()) ? this.unitFprice : null;
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
		return !DictCodeList.平安资产类型_债券.getValue().equals(this.getAssetType()) ? this.tradeAmt : null;
	}

	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}

	// 资产简称
	public String getFinprodAbbr() {
		if (DictCodeList.平安资产类型_债券.getValue().equals(this.getAssetType())
				|| DictCodeList.平安资产类型_基金.getValue().equals(this.getAssetType())) {
			return finprodAbbr;
		}
		return null;
	}
	
	// 期限品种
	public void setTermVriety(String termVriety) {
		this.termVriety = termVriety;
	}

	// 期限品种
	public String getTermVriety() {
		return DictCodeList.平安资产类型_质押式回购.getValue().equals(this.getAssetType()) ? termVriety : null;
	}

	// 交易数量
	public BigDecimal getTradeNum() {
		return DictCodeListExt2.平安资产类型_股票.getValue().equals(this.getAssetType()) ? this.tradeNum : null;
	}

	// 交易价格
	public BigDecimal getTradePrice() {
		if (DictCodeListExt2.平安资产类型_股票.getValue().equals(this.getAssetType())
				|| DictCodeList.平安资产类型_基金.getValue().equals(this.getAssetType())) {
			return tradePrice;
		}
		return null;
	}

	// 回购利率
	public BigDecimal getBackRate() {
		return DictCodeList.平安资产类型_质押式回购.getValue().equals(this.getAssetType()) ? backRate : null;
	}
	
	public void setBackRate(BigDecimal backRate) {
		this.backRate = backRate;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public void setTradeNum(BigDecimal tradeNum) {
		this.tradeNum = tradeNum;
	}

	public void setTradePrice(BigDecimal tradePrice) {
		this.tradePrice = tradePrice;
	}

	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}

	public BigDecimal getBondTotalFace() {
		return DictCodeList.平安资产类型_债券.getValue().equals(this.getAssetType()) ? bondTotalFace : null;
	}

	public void setBondTotalFace(BigDecimal bondTotalFace) {
		this.bondTotalFace = bondTotalFace;
	}

	public BigDecimal getTerm() {
		return term;
	}

	public void setTerm(BigDecimal term) {
		this.term = term;
	}

	public Date getvPayDate() {
		return vPayDate;
	}

	public void setvPayDate(Date vPayDate) {
		this.vPayDate = vPayDate;
	}

	public Date getmPayDate() {
		return mPayDate;
	}

	public void setmPayDate(Date mPayDate) {
		this.mPayDate = mPayDate;
	}

	public String getOrigRepSecId() {
		return origRepSecId;
	}

	public void setOrigRepSecId(String origRepSecId) {
		this.origRepSecId = origRepSecId;
	}

	public String getRepSecId() {
		return repSecId;
	}

	public void setRepSecId(String repSecId) {
		this.repSecId = repSecId;
	}

	public BigDecimal getRepSecNum() {
		return repSecNum;
	}

	public void setRepSecNum(BigDecimal repSecNum) {
		this.repSecNum = repSecNum;
	}

	public String getRepSecName() {
		return repSecName;
	}

	public void setRepSecName(String repSecName) {
		this.repSecName = repSecName;
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
}
