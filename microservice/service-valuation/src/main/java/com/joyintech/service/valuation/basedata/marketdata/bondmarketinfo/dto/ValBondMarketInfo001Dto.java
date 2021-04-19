/*
 * 文件名：ValBondValuationInfo001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.basedata.marketdata.bondmarketinfo.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 每日债券行情 dto
 * 
 * @author 张兴
 * @version 1.0 2018年7月18日
 * @see ValBondMarketInfo001Dto
 * @since 1.0
 */
public class ValBondMarketInfo001Dto extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1334757426923117768L;

	/**
	 * 资产代码
	 */
	private String finprodId;
	/**
	 * 债券简称
	 */
	private String finprodAbbr;

	/**
	 * 估值来源
	 */
	private String valueSource;

	/**
	 * 估值日
	 */
	private String valueDate;

	/**
	 * 剩余期限
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal lastPeriod;

	/**
	 * 收盘价
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal cloPrice;

	/**
	 * 平均价
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal avgPrice;

	/**
	 * 买一价
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal buyOnePrice;

	/**
	 * 卖一价
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal sellOnePrice;

	/**
	 * 最高阶
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal maxPrice;

	/**
	 * 最低价
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal minPrice;
	//全价和净价分开 add by lishuping 20191125 start
	/**
	 * 收盘价全价
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal cloPriceFull;

	/**
	 * 平均价全价
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal avgPriceFull;

	/**
	 * 买一价全价
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal buyOnePriceFull;

	/**
	 * 卖一价全价
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal sellOnePriceFull;

	/**
	 * 最高价全价
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal maxPriceFull;

	/**
	 * 最低价全价
	 */
	@JsonSerialize(using = BigDecimalSerializer.class)
	private BigDecimal minPriceFull;
	
	/**
	 * 行号
	 */
	private String excelNum;

	/**
	 * 获取 cloPriceFull
	 * @return cloPriceFull
	 */
	public BigDecimal getCloPriceFull() {
		return cloPriceFull;
	}

	/**
	 * cloPriceFull
	 * @param cloPriceFull cloPriceFull
	 */
	public void setCloPriceFull(BigDecimal cloPriceFull) {
		this.cloPriceFull = cloPriceFull;
	}

	/**
	 * 获取 avgPriceFull
	 * @return avgPriceFull
	 */
	public BigDecimal getAvgPriceFull() {
		return avgPriceFull;
	}

	/**
	 * avgPriceFull
	 * @param avgPriceFull avgPriceFull
	 */
	public void setAvgPriceFull(BigDecimal avgPriceFull) {
		this.avgPriceFull = avgPriceFull;
	}

	/**
	 * 获取 buyOnePriceFull
	 * @return buyOnePriceFull
	 */
	public BigDecimal getBuyOnePriceFull() {
		return buyOnePriceFull;
	}

	/**
	 * buyOnePriceFull
	 * @param buyOnePriceFull buyOnePriceFull
	 */
	public void setBuyOnePriceFull(BigDecimal buyOnePriceFull) {
		this.buyOnePriceFull = buyOnePriceFull;
	}

	/**
	 * 获取 buyOnePriceFull
	 * @return buyOnePriceFull
	 */
	public BigDecimal getSellOnePriceFull() {
		return sellOnePriceFull;
	}

	/**
	 * sellOnePriceFull
	 * @param sellOnePriceFull sellOnePriceFull
	 */
	public void setSellOnePriceFull(BigDecimal sellOnePriceFull) {
		this.sellOnePriceFull = sellOnePriceFull;
	}

	/**
	 * 获取 maxPriceFull
	 * @return maxPriceFull
	 */
	public BigDecimal getMaxPriceFull() {
		return maxPriceFull;
	}

	/**
	 * maxPriceFull
	 * @param maxPriceFull maxPriceFull
	 */
	public void setMaxPriceFull(BigDecimal maxPriceFull) {
		this.maxPriceFull = maxPriceFull;
	}

	/**
	 * 获取 minPriceFull
	 * @return minPriceFull
	 */
	public BigDecimal getMinPriceFull() {
		return minPriceFull;
	}

	/**
	 * minPriceFull
	 * @param minPriceFull minPriceFull
	 */
	public void setMinPriceFull(BigDecimal minPriceFull) {
		this.minPriceFull = minPriceFull;
	}
	//全价和净价分开 add by lishuping 20191125 end

	/**
	 * 获取 finprodId
	 * 
	 * @return finprodId.
	 */
	public String getFinprodId() {
		return finprodId;
	}

	/**
	 * 设置 finprodId
	 * 
	 * @param finprodId
	 *            finprodId
	 */
	public void setFinprodId(String finprodId) {
		this.finprodId = finprodId;
	}

	/**
	 * 获取 finprodAbbr
	 * 
	 * @return finprodAbbr.
	 */
	public String getFinprodAbbr() {
		return finprodAbbr;
	}

	/**
	 * 设置 finprodAbbr
	 * 
	 * @param finprodAbbr
	 *            finprodAbbr
	 */
	public void setFinprodAbbr(String finprodAbbr) {
		this.finprodAbbr = finprodAbbr;
	}

    /**
     * 获取 valueSource
     * @return valueSource.
     */
    public String getValueSource() {
        return valueSource;
    }

    /**
	 * 设置 估值来源
	 * 
	 * @param valueSource
	 *            估值来源
	 */
	public void setValueSource(String valueSource) {
		this.valueSource = valueSource == null ? null : valueSource.trim();
	}

	/**
	 * 取得 估值日
	 * 
	 * @return 估值日
	 */
	public String getValueDate() {
		return DateUtil.formatDateAddSplit(valueDate);
	}

	/**
	 * 设置 估值日
	 * 
	 * @param valueDate
	 *            估值日
	 */
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	/**
	 * 取得 剩余期限
	 * 
	 * @return 剩余期限
	 */
	public BigDecimal getLastPeriod() {
		return lastPeriod;
	}

	/**
	 * 设置 剩余期限
	 * 
	 * @param lastPeriod
	 *            剩余期限
	 */
	public void setLastPeriod(BigDecimal lastPeriod) {
		this.lastPeriod = lastPeriod;
	}

	/**
	 * 取得 收盘价
	 * 
	 * @return 收盘价
	 */
	public BigDecimal getCloPrice() {
		return cloPrice;
	}

	/**
	 * 设置收盘价
	 * 
	 * @param cloPrice
	 */
	public void setCloPrice(BigDecimal cloPrice) {
		this.cloPrice = cloPrice;
	}

	/**
	 * 取得 平均价
	 * 
	 * @return 平均价价
	 */
	public BigDecimal getAvgprice() {
		return avgPrice;
	}

	/**
	 * 设置平均价
	 * 
	 * @param avgPrice
	 *            平均价
	 */
	public void setAvgprice(BigDecimal avgPrice) {
		this.avgPrice = avgPrice;
	}

	/**
	 * 取得 买一价
	 * 
	 * @return 买一价
	 */
	public BigDecimal getBuyOnePrice() {
		return buyOnePrice;
	}

	/**
	 * 设置买一价
	 * 
	 * @param buyOnePrice
	 *            买一价
	 */
	public void setBuyOnePrice(BigDecimal buyOnePrice) {
		this.buyOnePrice = buyOnePrice;
	}

//	/**
//	 * 取得 卖一价
//	 * 
//	 * @return 卖一价
//	 */
//	public BigDecimal getSellOnePriceMrice() {
//		return sellOnePrice;
//	}
//
//	/**
//	 * 设置卖一价
//	 * 
//	 * @param sellOnePrice
//	 *            卖一价
//	 */
//	public void setSellOneMrice(BigDecimal sellOnePrice) {
//		this.sellOnePrice = sellOnePrice;
//	}

	/**
	 * 取得 最高阶
	 * 
	 * @return 最高阶
	 */
	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	/**
	 * 设置最高阶
	 * 
	 * @param maxPrice
	 *            最高价
	 */
	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	/**
	 * 取得 最低阶
	 * 
	 * @return 最低阶
	 */
	public BigDecimal getMinPrice() {
		return minPrice;
	}

	/**
	 * 设置最低价阶
	 * 
	 * @param minPrice
	 *            最低价
	 */
	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	/**
	 * 获取 avgPrice
	 * 
	 * @return avgPrice.
	 */
	public BigDecimal getAvgPrice() {
		return avgPrice;
	}

	/**
	 * 设置 avgPrice
	 * 
	 * @param avgPrice
	 *            avgPrice
	 */
	public void setAvgPrice(BigDecimal avgPrice) {
		this.avgPrice = avgPrice;
	}

	/**
	 * 获取 sellOnePrice
	 * 
	 * @return sellOnePrice.
	 */
	public BigDecimal getSellOnePrice() {
		return sellOnePrice;
	}

	/**
	 * 设置 sellOnePrice
	 * 
	 * @param sellOnePrice
	 *            sellOnePrice
	 */
	public void setSellOnePrice(BigDecimal sellOnePrice) {
		this.sellOnePrice = sellOnePrice;
	}

	public String getExcelNum() {
		return excelNum;
	}

	public void setExcelNum(String excelNum) {
		this.excelNum = excelNum;
	}	

}
