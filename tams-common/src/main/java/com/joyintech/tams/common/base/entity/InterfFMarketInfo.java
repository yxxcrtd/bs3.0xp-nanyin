package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class InterfFMarketInfo extends InterfFMarketInfoKey {
    /**
     * 前收盘价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal closePricePre;

    /**
     * 收盘价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal closePrice;

    /**
     * 平均价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal averagePrice;

    /**
     * 最高价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal maxPrice;

    /**
     * 最低价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal minPrice;

    /**
     * 成交量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal volume;

    /**
     * 成交金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal turnover;

    /**
     * 涨跌
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal change;

    /**
     * 涨跌幅
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal chg;

    /**
     * 振幅
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amplitude;

    /**
     * 换手率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal turnoverrate;

    /**
     * 折价率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal discountrate;

    /**
     * 买一价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyOnePrice;

    /**
     * 卖一价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sellOnePrice;

    /**
     * 取得 前收盘价
     * @return 前收盘价
     */
    public BigDecimal getClosePricePre() {
        return closePricePre;
    }

    /**
     * 设置 前收盘价
     * @param closePricePre 前收盘价
     */
    public void setClosePricePre(BigDecimal closePricePre) {
        this.closePricePre = closePricePre;
    }

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
     * 取得 成交量
     * @return 成交量
     */
    public BigDecimal getVolume() {
        return volume;
    }

    /**
     * 设置 成交量
     * @param volume 成交量
     */
    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    /**
     * 取得 成交金额
     * @return 成交金额
     */
    public BigDecimal getTurnover() {
        return turnover;
    }

    /**
     * 设置 成交金额
     * @param turnover 成交金额
     */
    public void setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
    }

    /**
     * 取得 涨跌
     * @return 涨跌
     */
    public BigDecimal getChange() {
        return change;
    }

    /**
     * 设置 涨跌
     * @param change 涨跌
     */
    public void setChange(BigDecimal change) {
        this.change = change;
    }

    /**
     * 取得 涨跌幅
     * @return 涨跌幅
     */
    public BigDecimal getChg() {
        return chg;
    }

    /**
     * 设置 涨跌幅
     * @param chg 涨跌幅
     */
    public void setChg(BigDecimal chg) {
        this.chg = chg;
    }

    /**
     * 取得 振幅
     * @return 振幅
     */
    public BigDecimal getAmplitude() {
        return amplitude;
    }

    /**
     * 设置 振幅
     * @param amplitude 振幅
     */
    public void setAmplitude(BigDecimal amplitude) {
        this.amplitude = amplitude;
    }

    /**
     * 取得 换手率
     * @return 换手率
     */
    public BigDecimal getTurnoverrate() {
        return turnoverrate;
    }

    /**
     * 设置 换手率
     * @param turnoverrate 换手率
     */
    public void setTurnoverrate(BigDecimal turnoverrate) {
        this.turnoverrate = turnoverrate;
    }

    /**
     * 取得 折价率
     * @return 折价率
     */
    public BigDecimal getDiscountrate() {
        return discountrate;
    }

    /**
     * 设置 折价率
     * @param discountrate 折价率
     */
    public void setDiscountrate(BigDecimal discountrate) {
        this.discountrate = discountrate;
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
}