package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class MstYieldCurveInfo extends MstYieldCurveInfoKey {
    /**
     * 收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * 曲线类型
     */
    private String curveType;

    /**
     * 录入方式，接口、手工等
     */
    private String inputType;

    /**
     * 取得 收益率
     * @return 收益率
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 收益率
     * @param yield 收益率
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 取得 曲线类型
     * @return 曲线类型
     */
    public String getCurveType() {
        return curveType;
    }

    /**
     * 设置 曲线类型
     * @param curveType 曲线类型
     */
    public void setCurveType(String curveType) {
        this.curveType = curveType == null ? null : curveType.trim();
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

    private String curveIntType;

    private String baseIntCode;

    private String priceType;

    public String getCurveIntType() {
        return curveIntType;
    }

    public void setCurveIntType(String curveIntType) {
        this.curveIntType = curveIntType;
    }

    public String getBaseIntCode() {
        return baseIntCode;
    }

    public void setBaseIntCode(String baseIntCode) {
        this.baseIntCode = baseIntCode;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }
}