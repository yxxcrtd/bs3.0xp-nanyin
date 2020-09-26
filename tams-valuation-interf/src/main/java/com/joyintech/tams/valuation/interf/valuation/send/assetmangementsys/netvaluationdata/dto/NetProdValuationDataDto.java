/*
 * 文件名：NetValuationDataDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.netvaluationdata.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 净值型产品估值数据Dto
 * @author chendong
 * @version 1.0 2018年11月20日
 * @see NetValuationDataDto
 * @since 1.0
 */
public class NetProdValuationDataDto {
	
	/**
     * 账套代码
     */
    private String bookSetId;
	
	/**
     * 产品代码
     */
    private String prodId;
    
    /**
     * 分层代码
     */
    private String layerId;
	
	/**
     * 估值日
     */
    private String valueDate;
    
    /**
     * 单位净值
     */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal unitValue;
	
	/**
     * 累计净值
     */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal totalValue;
	
	/**
     * 累计分红净值
     */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal totalBondValue;
	
	/**
     * 当前年化收益率
     */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal nowRate;
	
	/**
     * 每日资产总价值
     */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal dailyAssetAllValue;
	
	/**
     * 每日总负债
     */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal dailyAllDebt;
	
	/**
     * 产品总份额
     */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal prodAllAmt;

	/**
	 * 获取 prodId
	 * @return prodId.
	 */
	public String getProdId() {
		return prodId;
	}

	/**
	 * 设置 prodId
	 * @param prodId prodId
	 */
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	/**
	 * 获取 layerId
	 * @return layerId.
	 */
	public String getLayerId() {
		return layerId;
	}

	/**
	 * 设置 layerId
	 * @param layerId layerId
	 */
	public void setLayerId(String layerId) {
		this.layerId = layerId;
	}

	/**
	 * 获取 valueDate
	 * @return valueDate.
	 */
	public String getValueDate() {
		return valueDate;
	}

	/**
	 * 设置 valueDate
	 * @param valueDate valueDate
	 */
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	/**
	 * 获取 unitValue
	 * @return unitValue.
	 */
	public BigDecimal getUnitValue() {
		return unitValue;
	}

	/**
	 * 设置 unitValue
	 * @param unitValue unitValue
	 */
	public void setUnitValue(BigDecimal unitValue) {
		this.unitValue = unitValue;
	}

	/**
	 * 获取 totalValue
	 * @return totalValue.
	 */
	public BigDecimal getTotalValue() {
		return totalValue;
	}

	/**
	 * 设置 totalValue
	 * @param totalValue totalValue
	 */
	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	/**
	 * 获取 totalBondValue
	 * @return totalBondValue.
	 */
	public BigDecimal getTotalBondValue() {
		return totalBondValue;
	}

	/**
	 * 设置 totalBondValue
	 * @param totalBondValue totalBondValue
	 */
	public void setTotalBondValue(BigDecimal totalBondValue) {
		this.totalBondValue = totalBondValue;
	}

	/**
	 * 获取 nowRate
	 * @return nowRate.
	 */
	public BigDecimal getNowRate() {
		return nowRate;
	}

	/**
	 * 设置 nowRate
	 * @param nowRate nowRate
	 */
	public void setNowRate(BigDecimal nowRate) {
		this.nowRate = nowRate;
	}

	/**
	 * 获取 dailyAssetAllValue
	 * @return dailyAssetAllValue.
	 */
	public BigDecimal getDailyAssetAllValue() {
		return dailyAssetAllValue;
	}

	/**
	 * 设置 dailyAssetAllValue
	 * @param dailyAssetAllValue dailyAssetAllValue
	 */
	public void setDailyAssetAllValue(BigDecimal dailyAssetAllValue) {
		this.dailyAssetAllValue = dailyAssetAllValue;
	}

	/**
	 * 获取 dailyAllDebt
	 * @return dailyAllDebt.
	 */
	public BigDecimal getDailyAllDebt() {
		return dailyAllDebt;
	}

	/**
	 * 设置 dailyAllDebt
	 * @param dailyAllDebt dailyAllDebt
	 */
	public void setDailyAllDebt(BigDecimal dailyAllDebt) {
		this.dailyAllDebt = dailyAllDebt;
	}

	/**
	 * 获取 bookSetId
	 * @return bookSetId.
	 */
	public String getBookSetId() {
		return bookSetId;
	}

	/**
	 * 设置 bookSetId
	 * @param bookSetId bookSetId
	 */
	public void setBookSetId(String bookSetId) {
		this.bookSetId = bookSetId;
	}

	/**
	 * 获取 prodAllAmt
	 * @return prodAllAmt.
	 */
	public BigDecimal getProdAllAmt() {
		return prodAllAmt;
	}

	/**
	 * 设置 prodAllAmt
	 * @param prodAllAmt prodAllAmt
	 */
	public void setProdAllAmt(BigDecimal prodAllAmt) {
		this.prodAllAmt = prodAllAmt;
	}
}
