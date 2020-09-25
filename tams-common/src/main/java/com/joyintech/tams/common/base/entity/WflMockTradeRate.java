package com.joyintech.tams.common.base.entity;

public class WflMockTradeRate extends WflMockTradeRateKey {
    /**
     * 基准
     */
    private String indexId;

    /**
     * 基准来源
     */
    private String indexSource;

    /**
     * 系数
     */
    private String coefficient;

    /**
     * 利差
     */
    private String spreadRate;

    /**
     * 数据状态，新建
     */
    private String workflowStatus;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 取得 基准
     * @return 基准
     */
    public String getIndexId() {
        return indexId;
    }

    /**
     * 设置 基准
     * @param indexId 基准
     */
    public void setIndexId(String indexId) {
        this.indexId = indexId == null ? null : indexId.trim();
    }

    /**
     * 取得 基准来源
     * @return 基准来源
     */
    public String getIndexSource() {
        return indexSource;
    }

    /**
     * 设置 基准来源
     * @param indexSource 基准来源
     */
    public void setIndexSource(String indexSource) {
        this.indexSource = indexSource == null ? null : indexSource.trim();
    }

    /**
     * 取得 系数
     * @return 系数
     */
    public String getCoefficient() {
        return coefficient;
    }

    /**
     * 设置 系数
     * @param coefficient 系数
     */
    public void setCoefficient(String coefficient) {
        this.coefficient = coefficient == null ? null : coefficient.trim();
    }

    /**
     * 取得 利差
     * @return 利差
     */
    public String getSpreadRate() {
        return spreadRate;
    }

    /**
     * 设置 利差
     * @param spreadRate 利差
     */
    public void setSpreadRate(String spreadRate) {
        this.spreadRate = spreadRate == null ? null : spreadRate.trim();
    }

    /**
     * 取得 数据状态，新建
     * @return 数据状态，新建
     */
    public String getWorkflowStatus() {
        return workflowStatus;
    }

    /**
     * 设置 数据状态，新建
     * @param workflowStatus 数据状态，新建
     */
    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus == null ? null : workflowStatus.trim();
    }

    /**
     * 取得 工作流代码
     * @return 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }
}