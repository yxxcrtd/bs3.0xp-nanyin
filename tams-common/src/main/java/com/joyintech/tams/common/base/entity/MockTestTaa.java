package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;

public class MockTestTaa extends MockTestTaaKey {
    /**
     * 成本
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal cost;

    /**
     * 市值
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal marketValue;

    /**
     * 应收利息
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal accruedInc;

    /**
     * 规模占比
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal amtRation;

    /**
     * 取得 成本
     * @return 成本
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * 设置 成本
     * @param cost 成本
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * 取得 市值
     * @return 市值
     */
    public BigDecimal getMarketValue() {
        return marketValue;
    }

    /**
     * 设置 市值
     * @param marketValue 市值
     */
    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }

    /**
     * 取得 应收利息
     * @return 应收利息
     */
    public BigDecimal getAccruedInc() {
        return accruedInc;
    }

    /**
     * 设置 应收利息
     * @param accruedInc 应收利息
     */
    public void setAccruedInc(BigDecimal accruedInc) {
        this.accruedInc = accruedInc;
    }

    /**
     * 取得 规模占比
     * @return 规模占比
     */
    public BigDecimal getAmtRation() {
        return amtRation;
    }

    /**
     * 设置 规模占比
     * @param amtRation 规模占比
     */
    public void setAmtRation(BigDecimal amtRation) {
        this.amtRation = amtRation;
    }
}