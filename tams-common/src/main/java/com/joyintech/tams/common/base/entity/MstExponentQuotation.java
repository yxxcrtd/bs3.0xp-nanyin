package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class MstExponentQuotation extends MstExponentQuotationKey {
    /**
     * 发布日
     */
    private Date relaeseDate;

    /**
     * 前收盘价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pClosePrice;

    /**
     * 开盘价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal openPrice;

    /**
     * 收盘价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal closePrice;

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
     * 录入方式，接口、手工等
     */
    private String inputType;

    /**
     * 取得 发布日
     * @return 发布日
     */
    public Date getRelaeseDate() {
        return relaeseDate;
    }

    /**
     * 设置 发布日
     * @param relaeseDate 发布日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRelaeseDate(Date relaeseDate) {
        this.relaeseDate = relaeseDate;
    }

    /**
     * 取得 前收盘价
     * @return 前收盘价
     */
    public BigDecimal getpClosePrice() {
        return pClosePrice;
    }

    /**
     * 设置 前收盘价
     * @param pClosePrice 前收盘价
     */
    public void setpClosePrice(BigDecimal pClosePrice) {
        this.pClosePrice = pClosePrice;
    }

    /**
     * 取得 开盘价
     * @return 开盘价
     */
    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    /**
     * 设置 开盘价
     * @param openPrice 开盘价
     */
    public void setOpenPrice(BigDecimal openPrice) {
        this.openPrice = openPrice;
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
     * 取得 录入方式，接口、手工等
     * @return 录入方式，接口、手工等
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置 录入方式，接口、手工等
     * @param inputType 录入方式，接口、手工等
     */
    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }
}