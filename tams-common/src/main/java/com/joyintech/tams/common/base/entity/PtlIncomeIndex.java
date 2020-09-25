package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class PtlIncomeIndex extends BaseEntity {
    /**
     * null
     */
    private String indexId;

    /**
     * null
     */
    private String incomeType;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal incomeTarget;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal maxIncomeTarget;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal minIncomeTarget;

    /**
     * 取得 null
     * @return null
     */
    public String getIndexId() {
        return indexId;
    }

    /**
     * 设置 null
     * @param indexId null
     */
    public void setIndexId(String indexId) {
        this.indexId = indexId == null ? null : indexId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getIncomeType() {
        return incomeType;
    }

    /**
     * 设置 null
     * @param incomeType null
     */
    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType == null ? null : incomeType.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getIncomeTarget() {
        return incomeTarget;
    }

    /**
     * 设置 null
     * @param incomeTarget null
     */
    public void setIncomeTarget(BigDecimal incomeTarget) {
        this.incomeTarget = incomeTarget;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getMaxIncomeTarget() {
        return maxIncomeTarget;
    }

    /**
     * 设置 null
     * @param maxIncomeTarget null
     */
    public void setMaxIncomeTarget(BigDecimal maxIncomeTarget) {
        this.maxIncomeTarget = maxIncomeTarget;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getMinIncomeTarget() {
        return minIncomeTarget;
    }

    /**
     * 设置 null
     * @param minIncomeTarget null
     */
    public void setMinIncomeTarget(BigDecimal minIncomeTarget) {
        this.minIncomeTarget = minIncomeTarget;
    }
}