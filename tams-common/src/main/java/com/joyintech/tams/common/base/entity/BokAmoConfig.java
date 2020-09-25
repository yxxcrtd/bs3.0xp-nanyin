package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class BokAmoConfig extends BokAmoConfigKey {
    /**
     * 重算方式
     */
    private String recalType;

    /**
     * 实际日利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dailydscYield;

    /**
     * 取得 重算方式
     * @return 重算方式
     */
    public String getRecalType() {
        return recalType;
    }

    /**
     * 设置 重算方式
     * @param recalType 重算方式
     */
    public void setRecalType(String recalType) {
        this.recalType = recalType == null ? null : recalType.trim();
    }

    /**
     * 取得 实际日利率
     * @return 实际日利率
     */
    public BigDecimal getDailydscYield() {
        return dailydscYield;
    }

    /**
     * 设置 实际日利率
     * @param dailydscYield 实际日利率
     */
    public void setDailydscYield(BigDecimal dailydscYield) {
        this.dailydscYield = dailydscYield;
    }
}