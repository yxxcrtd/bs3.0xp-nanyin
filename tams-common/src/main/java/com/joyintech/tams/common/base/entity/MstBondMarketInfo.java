package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class MstBondMarketInfo extends MstBondMarketInfoKey {
    /**
     * 收盘价净价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal closePrice;

    /**
     * 平均价净价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal averagePrice;

    /**
     * 最高价净价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal maxPrice;

    /**
     * 最低价净价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal minPrice;

    /**
     * 买一价净价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyOnePrice;

    /**
     * 卖一价净价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sellOnePrice;
    
    /**
     * 收盘价全价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal closePriceFull;

    /**
     * 平均价全价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal averagePriceFull;

    /**
     * 最高价全价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal maxPriceFull;

    /**
     * 最低价全价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal minPriceFull;

    /**
     * 买一价全价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyOnePriceFull;

    /**
     * 卖一价全价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sellOnePriceFull;

    /**
     * 录入方式
     */
    private String inputType;

    /**
     * 取得 收盘价
     * @return 收盘价
     */
    public BigDecimal getClosePrice() {
        return closePrice;
    }

    /**
     * 设置 收盘价
     * @param closePrice 收盘价
     */
    public void setClosePrice(BigDecimal closePrice) {
        this.closePrice = closePrice;
    }

    /**
     * 取得 平均价
     * @return 平均价
     */
    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    /**
     * 设置 平均价
     * @param averagePrice 平均价
     */
    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    /**
     * 取得 最高价
     * @return 最高价
     */
    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    /**
     * 设置 最高价
     * @param maxPrice 最高价
     */
    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * 取得 最低价
     * @return 最低价
     */
    public BigDecimal getMinPrice() {
        return minPrice;
    }

    /**
     * 设置 最低价
     * @param minPrice 最低价
     */
    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * 取得 买一价
     * @return 买一价
     */
    public BigDecimal getBuyOnePrice() {
        return buyOnePrice;
    }

    /**
     * 设置 买一价
     * @param buyOnePrice 买一价
     */
    public void setBuyOnePrice(BigDecimal buyOnePrice) {
        this.buyOnePrice = buyOnePrice;
    }

    /**
     * 取得 卖一价
     * @return 卖一价
     */
    public BigDecimal getSellOnePrice() {
        return sellOnePrice;
    }

    /**
     * 设置 卖一价
     * @param sellOnePrice 卖一价
     */
    public void setSellOnePrice(BigDecimal sellOnePrice) {
        this.sellOnePrice = sellOnePrice;
    }
    
    public BigDecimal getClosePriceFull() {
		return closePriceFull;
	}

	public void setClosePriceFull(BigDecimal closePriceFull) {
		this.closePriceFull = closePriceFull;
	}

	public BigDecimal getAveragePriceFull() {
		return averagePriceFull;
	}

	public void setAveragePriceFull(BigDecimal averagePriceFull) {
		this.averagePriceFull = averagePriceFull;
	}

	public BigDecimal getMaxPriceFull() {
		return maxPriceFull;
	}

	public void setMaxPriceFull(BigDecimal maxPriceFull) {
		this.maxPriceFull = maxPriceFull;
	}

	public BigDecimal getMinPriceFull() {
		return minPriceFull;
	}

	public void setMinPriceFull(BigDecimal minPriceFull) {
		this.minPriceFull = minPriceFull;
	}

	public BigDecimal getBuyOnePriceFull() {
		return buyOnePriceFull;
	}

	public void setBuyOnePriceFull(BigDecimal buyOnePriceFull) {
		this.buyOnePriceFull = buyOnePriceFull;
	}

	public BigDecimal getSellOnePriceFull() {
		return sellOnePriceFull;
	}

	public void setSellOnePriceFull(BigDecimal sellOnePriceFull) {
		this.sellOnePriceFull = sellOnePriceFull;
	}

	/**
     * 取得 录入方式
     * @return 录入方式
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置 录入方式
     * @param inputType 录入方式
     */
    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }
}