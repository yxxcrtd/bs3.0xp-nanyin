/*
 * 文件名：MoneyProdValuationDataDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.moneyvaluationdata.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 货币型产品估值数据Dto
 * @author chendong
 * @version 1.0 2018年11月19日
 * @see MoneyProdValuationDataDto
 * @since 1.0
 */
public class MoneyProdValuationDataDto {
	/**
     * 账套代码
     */
	private String bookSetId;
	/**
     * 当前周期年化收益率
     */
	@JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yrrate;
	/**
     * 产品代码
     */
	private String prodId;
	
	/**
     * 分层代码
     */
    private String layerId;
	
	/**
     * 估值日期
     */
	private String valDate;
	
	/**
     * 产品总份额
     */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal prodAllAmt;
	
	/**
     * 当日万份收益
     */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal nowDayRate;
	
	/**
     * 累计万份收益
     */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal totalRate;
	
	/**
     * 当日应付利润发生值
     */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal nowDayProfitValue;
	
	/**
     * 七日年化收益率
     */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal sevenDayRate;
	
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
     * 获取 valDate
     * @return valDate.
     */
    public String getValDate() {
        return valDate;
    }

    
    /**
     * 设置 valDate
     * @param valDate valDate
     */
    public void setValDate(String valDate) {
        this.valDate = valDate;
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

	/**
	 * 获取 nowDayRate
	 * @return nowDayRate.
	 */
	public BigDecimal getNowDayRate() {
		return nowDayRate;
	}

	/**
	 * 设置 nowDayRate
	 * @param nowDayRate nowDayRate
	 */
	public void setNowDayRate(BigDecimal nowDayRate) {
		this.nowDayRate = nowDayRate;
	}

	/**
	 * 获取 totalRate
	 * @return totalRate.
	 */
	public BigDecimal getTotalRate() {
		return totalRate;
	}

	/**
	 * 设置 totalRate
	 * @param totalRate totalRate
	 */
	public void setTotalRate(BigDecimal totalRate) {
		this.totalRate = totalRate;
	}

	/**
	 * 获取 nowDayProfitValue
	 * @return nowDayProfitValue.
	 */
	public BigDecimal getNowDayProfitValue() {
		return nowDayProfitValue;
	}

	/**
	 * 设置 nowDayProfitValue
	 * @param nowDayProfitValue nowDayProfitValue
	 */
	public void setNowDayProfitValue(BigDecimal nowDayProfitValue) {
		this.nowDayProfitValue = nowDayProfitValue;
	}

	/**
	 * 获取 sevenDayRate
	 * @return sevenDayRate.
	 */
	public BigDecimal getSevenDayRate() {
		return sevenDayRate;
	}

	/**
	 * 设置 sevenDayRate
	 * @param sevenDayRate sevenDayRate
	 */
	public void setSevenDayRate(BigDecimal sevenDayRate) {
		this.sevenDayRate = sevenDayRate;
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

	public String getBookSetId() {
		return bookSetId;
	}

	public void setBookSetId(String bookSetId) {
		this.bookSetId = bookSetId;
	}
    /**
	 * 获取 yrrate
	 * @return yrrate.
	 */
	public BigDecimal getYrrate() {
		return yrrate;
	}

	/**
	 * 设置 yrrate
	 * @param yrrate yrrate
	 */
	public void setYrrate(BigDecimal yrrate) {
		this.yrrate = yrrate;
	}
	
}
