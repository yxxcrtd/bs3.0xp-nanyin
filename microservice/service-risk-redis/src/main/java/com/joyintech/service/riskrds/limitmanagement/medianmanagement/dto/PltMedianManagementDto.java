package com.joyintech.service.riskrds.limitmanagement.medianmanagement.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.common.base.entity.RskIndicatorParaCalcdetail;


/**
 * 指标参数中间值管理
 * @author zhengjunping
 * @version 1.0 2019年11月19日
 * @see PltMedianManagementDto
 * @since 1.0
 */
public class PltMedianManagementDto extends RskIndicatorParaCalcdetail {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 8559576346600595510L;
    
    /**
     * 实占编号（交易代码）
     */
    private String eventId;

    /**
     * 事件类型(1：投资交易、2：交易拆分)
     */
    private String eventType;
    
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 资产代码
     */
    private String tassetCode;
    
    public String getTassetCode() {
		return tassetCode;
	}

	public void setTassetCode(String tassetCode) {
		this.tassetCode = tassetCode;
	}
	
	/**
	 * 事件类型
	 */
	private String typey;

	/**
     * 金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amount;

    /**
     * 价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal priceAmt;

    /**
     * 数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tradeNum;
    
    /**
     * 资产类型
     */
    private String assetType;
    
    /**
     * 交易方向
     */
    private String ps;
    
    /**
     * 事件日期
     */
    private Date eventDate;
    
    /**
     * 序号
     */
    private String num;
    
    /**
     * 参数名称
     */
    private String paraName;
    
    /**
     * 交易类型
     */
    private String itemValue;
    
    /**
     * 产品名称
     */
    private String portfolioName;
    
    /**
     * 事件类型前半部分
     */
    private String typey1;
    
    /**
     * 事件类型后半部分
     */
    private String typey2;
    
    public String getTypey1() {
		return typey1;
	}

	public void setTypey1(String typey1) {
		this.typey1 = typey1;
	}

	public String getTypey2() {
		return typey2;
	}

	public void setTypey2(String typey2) {
		this.typey2 = typey2;
	}

	public String getPortfolioName() {
		return portfolioName;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public String getTypey() {
		return typey;
	}

	public void setTypey(String typey) {
		this.typey = typey;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getPriceAmt() {
		return priceAmt;
	}

	public void setPriceAmt(BigDecimal priceAmt) {
		this.priceAmt = priceAmt;
	}

	public BigDecimal getTradeNum() {
		return tradeNum;
	}

	public void setTradeNum(BigDecimal tradeNum) {
		this.tradeNum = tradeNum;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getParaName() {
		return paraName;
	}

	public void setParaName(String paraName) {
		this.paraName = paraName;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}
}
