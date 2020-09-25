package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class PtlInvStrategyDetail extends BaseEntity {
    /**
     * 批次号
     */
    private String batchId;

    /**
     * 策略代码
     */
    private String strategyId;

    /**
     * 比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal ratio;

    /**
     * 取得 批次号
     * @return 批次号
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * 设置 批次号
     * @param batchId 批次号
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId == null ? null : batchId.trim();
    }

    /**
     * 取得 策略代码
     * @return 策略代码
     */
    public String getStrategyId() {
        return strategyId;
    }

    /**
     * 设置 策略代码
     * @param strategyId 策略代码
     */
    public void setStrategyId(String strategyId) {
        this.strategyId = strategyId == null ? null : strategyId.trim();
    }

    /**
     * 取得 比例
     * @return 比例
     */
    public BigDecimal getRatio() {
        return ratio;
    }

    /**
     * 设置 比例
     * @param ratio 比例
     */
    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }
}