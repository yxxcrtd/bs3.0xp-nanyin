package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class PtlInvStrategyDetailClassKey extends BaseEntity {
    /**
     * 策略明细代码
     */
    private String strategyDetId;

    /**
     * 策略代码
     */
    private String batchId;

    /**
     * 取得 策略明细代码
     * @return 策略明细代码
     */
    public String getStrategyDetId() {
        return strategyDetId;
    }

    /**
     * 设置 策略明细代码
     * @param strategyDetId 策略明细代码
     */
    public void setStrategyDetId(String strategyDetId) {
        this.strategyDetId = strategyDetId == null ? null : strategyDetId.trim();
    }

    /**
     * 取得 策略代码
     * @return 策略代码
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * 设置 策略代码
     * @param batchId 策略代码
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId == null ? null : batchId.trim();
    }
}