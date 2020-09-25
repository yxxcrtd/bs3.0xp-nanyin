package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class PtlStrategyEvalIndex extends BaseEntity {
    /**
     * 策略评价指标代码
     */
    private String strategyEvalId;

    /**
     * 策略代码
     */
    private String strategyId;

    /**
     * 评价指标代码
     */
    private String evalIndex;

    /**
     * 表类型
     */
    private String tableType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 策略评价指标代码
     * @return 策略评价指标代码
     */
    public String getStrategyEvalId() {
        return strategyEvalId;
    }

    /**
     * 设置 策略评价指标代码
     * @param strategyEvalId 策略评价指标代码
     */
    public void setStrategyEvalId(String strategyEvalId) {
        this.strategyEvalId = strategyEvalId == null ? null : strategyEvalId.trim();
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
     * 取得 评价指标代码
     * @return 评价指标代码
     */
    public String getEvalIndex() {
        return evalIndex;
    }

    /**
     * 设置 评价指标代码
     * @param evalIndex 评价指标代码
     */
    public void setEvalIndex(String evalIndex) {
        this.evalIndex = evalIndex == null ? null : evalIndex.trim();
    }

    /**
     * 取得 表类型
     * @return 表类型
     */
    public String getTableType() {
        return tableType;
    }

    /**
     * 设置 表类型
     * @param tableType 表类型
     */
    public void setTableType(String tableType) {
        this.tableType = tableType == null ? null : tableType.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}