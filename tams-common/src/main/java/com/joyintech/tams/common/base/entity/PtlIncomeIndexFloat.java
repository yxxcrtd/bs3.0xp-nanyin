package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class PtlIncomeIndexFloat extends PtlIncomeIndexFloatKey {
    /**
     * null
     */
    private String baseRuleId;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal coefficient;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal spreadRate;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ratio;

    /**
     * 取得 null
     * @return null
     */
    public String getBaseRuleId() {
        return baseRuleId;
    }

    /**
     * 设置 null
     * @param baseRuleId null
     */
    public void setBaseRuleId(String baseRuleId) {
        this.baseRuleId = baseRuleId == null ? null : baseRuleId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getCoefficient() {
        return coefficient;
    }

    /**
     * 设置 null
     * @param coefficient null
     */
    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getSpreadRate() {
        return spreadRate;
    }

    /**
     * 设置 null
     * @param spreadRate null
     */
    public void setSpreadRate(BigDecimal spreadRate) {
        this.spreadRate = spreadRate;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getRatio() {
        return ratio;
    }

    /**
     * 设置 null
     * @param ratio null
     */
    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }
}