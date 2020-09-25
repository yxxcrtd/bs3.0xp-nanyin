package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class MstBondValuationInfo extends MstBondValuationInfoKey {
    /**
     * 剩余期限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lastPeriod;

    /**
     * 市价全价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal price;

    /**
     * 市价净价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netprice;

    /**
     * 市价久期
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mDuration;

    /**
     * 市价凸性
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mConvexity;

    /**
     * 基点价值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bpvalue;

    /**
     * 利差久期
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sduration;

    /**
     * 利差凸性
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal scnvxty;

    /**
     * 利率久期
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal interestDuration;

    /**
     * 利率凸性
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal interestCnvxty;

    /**
     * 市价收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bpyield;

    /**
     * 录入方式，接口、手工等
     */
    private String inputType;

    /**
     * 取得 剩余期限
     * @return 剩余期限
     */
    public BigDecimal getLastPeriod() {
        return lastPeriod;
    }

    /**
     * 设置 剩余期限
     * @param lastPeriod 剩余期限
     */
    public void setLastPeriod(BigDecimal lastPeriod) {
        this.lastPeriod = lastPeriod;
    }

    /**
     * 取得 市价全价
     * @return 市价全价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置 市价全价
     * @param price 市价全价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 取得 市价净价
     * @return 市价净价
     */
    public BigDecimal getNetprice() {
        return netprice;
    }

    /**
     * 设置 市价净价
     * @param netprice 市价净价
     */
    public void setNetprice(BigDecimal netprice) {
        this.netprice = netprice;
    }

    /**
     * 取得 市价久期
     * @return 市价久期
     */
    public BigDecimal getmDuration() {
        return mDuration;
    }

    /**
     * 设置 市价久期
     * @param mDuration 市价久期
     */
    public void setmDuration(BigDecimal mDuration) {
        this.mDuration = mDuration;
    }

    /**
     * 取得 市价凸性
     * @return 市价凸性
     */
    public BigDecimal getmConvexity() {
        return mConvexity;
    }

    /**
     * 设置 市价凸性
     * @param mConvexity 市价凸性
     */
    public void setmConvexity(BigDecimal mConvexity) {
        this.mConvexity = mConvexity;
    }

    /**
     * 取得 基点价值
     * @return 基点价值
     */
    public BigDecimal getBpvalue() {
        return bpvalue;
    }

    /**
     * 设置 基点价值
     * @param bpvalue 基点价值
     */
    public void setBpvalue(BigDecimal bpvalue) {
        this.bpvalue = bpvalue;
    }

    /**
     * 取得 利差久期
     * @return 利差久期
     */
    public BigDecimal getSduration() {
        return sduration;
    }

    /**
     * 设置 利差久期
     * @param sduration 利差久期
     */
    public void setSduration(BigDecimal sduration) {
        this.sduration = sduration;
    }

    /**
     * 取得 利差凸性
     * @return 利差凸性
     */
    public BigDecimal getScnvxty() {
        return scnvxty;
    }

    /**
     * 设置 利差凸性
     * @param scnvxty 利差凸性
     */
    public void setScnvxty(BigDecimal scnvxty) {
        this.scnvxty = scnvxty;
    }

    /**
     * 取得 利率久期
     * @return 利率久期
     */
    public BigDecimal getInterestDuration() {
        return interestDuration;
    }

    /**
     * 设置 利率久期
     * @param interestDuration 利率久期
     */
    public void setInterestDuration(BigDecimal interestDuration) {
        this.interestDuration = interestDuration;
    }

    /**
     * 取得 利率凸性
     * @return 利率凸性
     */
    public BigDecimal getInterestCnvxty() {
        return interestCnvxty;
    }

    /**
     * 设置 利率凸性
     * @param interestCnvxty 利率凸性
     */
    public void setInterestCnvxty(BigDecimal interestCnvxty) {
        this.interestCnvxty = interestCnvxty;
    }

    /**
     * 取得 市价收益率
     * @return 市价收益率
     */
    public BigDecimal getBpyield() {
        return bpyield;
    }

    /**
     * 设置 市价收益率
     * @param bpyield 市价收益率
     */
    public void setBpyield(BigDecimal bpyield) {
        this.bpyield = bpyield;
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