package com.joyintech.tams.valuation.transformation.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.tams.common.base.entity.TrsSubjectRelation;

public class AssetBalanceDto {

	/**
	 * 账套代码
	 */
	private String booksetId;
	
	/**
	 * 资产类型
	 */
	private String assetType;
	
	/**
	 * 资产代码
	 */
	private String assetCode;
	
	/**
	 * 初始化本金
	 */
	private BigDecimal principal;
	
	
	/**
	 * 当前余额（份额）
	 */
	private BigDecimal share;
	
	
	/**
	 * 币种
	 */
	private String oCcy;
	
	
	/**
	 * 查询日
	 */
	private Date cdate;
	
	
	/**
	 * 应收利息
	 */
	private BigDecimal intAmt;
	
	
	/**
	 * 资产估值（成本法）
	 */
	private BigDecimal assetValuationCB;
	
	/**
	 * 资产估值（市价法）
	 */
	private BigDecimal assetValuationSJ;
	
	/**
	 * 公允价值
	 */
	private BigDecimal fairValue;
	
	/**
	 * 面值
	 */
	private BigDecimal netValue;

	/**
	 * @return the booksetId
	 */
	public String getBooksetId() {
		return booksetId;
	}

	/**
	 * @param booksetId the booksetId to set
	 */
	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}

	/**
	 * @return the assetType
	 */
	public String getAssetType() {
		return assetType;
	}

	/**
	 * @param assetType the assetType to set
	 */
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	/**
	 * @return the assetCode
	 */
	public String getAssetCode() {
		return assetCode;
	}

	/**
	 * @param assetCode the assetCode to set
	 */
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	/**
	 * @return the principal
	 */
	public BigDecimal getPrincipal() {
		return principal;
	}

	/**
	 * @param principal the principal to set
	 */
	public void setPrincipal(BigDecimal principal) {
		this.principal = principal;
	}

	/**
	 * @return the share
	 */
	public BigDecimal getShare() {
		return share;
	}

	/**
	 * @param share the share to set
	 */
	public void setShare(BigDecimal share) {
		this.share = share;
	}

	/**
	 * @return the oCcy
	 */
	public String getoCcy() {
		return oCcy;
	}

	/**
	 * @param oCcy the oCcy to set
	 */
	public void setoCcy(String oCcy) {
		this.oCcy = oCcy;
	}

	/**
	 * @return the cdate
	 */
	public Date getCdate() {
		return cdate;
	}

	/**
	 * @param cdate the cdate to set
	 */
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	/**
	 * @return the intAmt
	 */
	public BigDecimal getIntAmt() {
		return intAmt;
	}

	/**
	 * @param intAmt the intAmt to set
	 */
	public void setIntAmt(BigDecimal intAmt) {
		this.intAmt = intAmt;
	}

	/**
	 * @return the assetValuationCB
	 */
	public BigDecimal getAssetValuationCB() {
		return assetValuationCB;
	}

	/**
	 * @param assetValuationCB the assetValuationCB to set
	 */
	public void setAssetValuationCB(BigDecimal assetValuationCB) {
		this.assetValuationCB = assetValuationCB;
	}

	/**
	 * @return the assetValuationSJ
	 */
	public BigDecimal getAssetValuationSJ() {
		return assetValuationSJ;
	}

	/**
	 * @param assetValuationSJ the assetValuationSJ to set
	 */
	public void setAssetValuationSJ(BigDecimal assetValuationSJ) {
		this.assetValuationSJ = assetValuationSJ;
	}

	/**
	 * @return the fairValue
	 */
	public BigDecimal getFairValue() {
		return fairValue;
	}

	/**
	 * @param fairValue the fairValue to set
	 */
	public void setFairValue(BigDecimal fairValue) {
		this.fairValue = fairValue;
	}
	
	/**
	 * 境内外
	 */
	private String blnArea;
	
	/**
	 * 核算分类
	 */
	private String type2;
	
	/**
	 * 投资目的
	 */
	private String invAim;
	
	/**
	 * 投资目的名称
	 */
	private String invAimName;
	
	/**
	 * 收益类型
	 */
	private String profitType;
	
	/**
	 * 资产类型名称
	 */
	private String assetTypeName;

	/**
	 * @return the blnArea
	 */
	public String getBlnArea() {
		return blnArea;
	}

	/**
	 * @param blnArea the blnArea to set
	 */
	public void setBlnArea(String blnArea) {
		this.blnArea = blnArea;
	}

	/**
	 * @return the type2
	 */
	public String getType2() {
		return type2;
	}

	/**
	 * @param type2 the type2 to set
	 */
	public void setType2(String type2) {
		this.type2 = type2;
	}

	/**
	 * @return the invAim
	 */
	public String getInvAim() {
		return invAim;
	}

	/**
	 * @param invAim the invAim to set
	 */
	public void setInvAim(String invAim) {
		this.invAim = invAim;
	}

	/**
	 * @return the profitType
	 */
	public String getProfitType() {
		return profitType;
	}

	/**
	 * @param profitType the profitType to set
	 */
	public void setProfitType(String profitType) {
		this.profitType = profitType;
	}

	/**
	 * @return the invAimName
	 */
	public String getInvAimName() {
		return invAimName;
	}

	/**
	 * @param invAimName the invAimName to set
	 */
	public void setInvAimName(String invAimName) {
		this.invAimName = invAimName;
	}

	/**
	 * @return the assetTypeName
	 */
	public String getAssetTypeName() {
		return assetTypeName;
	}

	/**
	 * @param assetTypeName the assetTypeName to set
	 */
	public void setAssetTypeName(String assetTypeName) {
		this.assetTypeName = assetTypeName;
	}
	
	
	/**
     * 模板账套代码
     */
    private String booksetIdTmpl;

	/**
	 * @return the booksetIdTmpl
	 */
	public String getBooksetIdTmpl() {
		return booksetIdTmpl;
	}

	/**
	 * @param booksetIdTmpl the booksetIdTmpl to set
	 */
	public void setBooksetIdTmpl(String booksetIdTmpl) {
		this.booksetIdTmpl = booksetIdTmpl;
	}
    /**
     * 数据移植科目对应关系
     */
    private TrsSubjectRelation trsSubjectRelation;

	/**
	 * @return the trsSubjectRelation
	 */
	public TrsSubjectRelation getTrsSubjectRelation() {
		return trsSubjectRelation;
	}

	/**
	 * @param trsSubjectRelation the trsSubjectRelation to set
	 */
	public void setTrsSubjectRelation(TrsSubjectRelation trsSubjectRelation) {
		this.trsSubjectRelation = trsSubjectRelation;
	}
	
	/**
	 * 交易场所
	 */
	private String tradeMarket;

	/**
	 * @return the tradeMarket
	 */
	public String getTradeMarket() {
		return tradeMarket;
	}

	/**
	 * @param tradeMarket the tradeMarket to set
	 */
	public void setTradeMarket(String tradeMarket) {
		this.tradeMarket = tradeMarket;
	}
	
	/**
	 * 模板中的资产类型
	 */
    private String templateAssetType;

	/**
	 * @return the templateAssetType
	 */
	public String getTemplateAssetType() {
		return templateAssetType;
	}

	/**
	 * @param templateAssetType the templateAssetType to set
	 */
	public void setTemplateAssetType(String templateAssetType) {
		this.templateAssetType = templateAssetType;
	}

	/**
	 * @return the netValue
	 */
	public BigDecimal getNetValue() {
		return netValue;
	}

	/**
	 * @param netValue the netValue to set
	 */
	public void setNetValue(BigDecimal netValue) {
		this.netValue = netValue;
	}
    
    
	
	
}
