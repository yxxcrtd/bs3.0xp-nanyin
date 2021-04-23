package com.joyintech.service.entrust.etrtransactiondetail.dto;

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
	 * 
	 */
	private BigDecimal backRate;
	
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
	 * 交易编号
	 */
	private String tradeId;

	public String getBusiPs() {
		return busiPs;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getFinprodMarketId() {
		return finprodMarketId;
	}

	public void setFinprodMarketId(String finprodMarketId) {
		this.finprodMarketId = finprodMarketId;
	}

	public String getTermVriety() {
		return termVriety;
	}

	public void setTermVriety(String termVriety) {
		this.termVriety = termVriety;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public String getFinprodName() {
		return finprodName;
	}

	public void setFinprodName(String finprodName) {
		this.finprodName = finprodName;
	}

	public String getFinprodAbbr() {
		return finprodAbbr;
	}

	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
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

	public BigDecimal getShareAmt() {
		return shareAmt;
	}

	public void setShareAmt(BigDecimal shareAmt) {
		this.shareAmt = shareAmt;
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

	public BigDecimal getTradeNum() {
		return tradeNum;
	}

	public void setTradeNum(BigDecimal tradeNum) {
		this.tradeNum = tradeNum;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public void setBusiPs(String busiPs) {
		this.busiPs = busiPs;
	}

	public BigDecimal getBackRate() {
		return backRate;
	}

	public void setBackRate(BigDecimal backRate) {
		this.backRate = backRate;
	}

    /**
     * 获取 fTradeId
     * @return fTradeId.
     */
    public String getfTradeId() {
        return fTradeId;
    }

    /**
     * 设置 fTradeId
     * @param fTradeId fTradeId
     */
    public void setfTradeId(String fTradeId) {
        this.fTradeId = fTradeId;
    }

    /**
     * 获取 orderNo
     * @return orderNo.
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置 orderNo
     * @param orderNo orderNo
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取 depositAmt
     * @return depositAmt.
     */
    public BigDecimal getDepositAmt() {
        return depositAmt;
    }

    /**
     * 设置 depositAmt
     * @param depositAmt depositAmt
     */
    public void setDepositAmt(BigDecimal depositAmt) {
        this.depositAmt = depositAmt;
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
}
