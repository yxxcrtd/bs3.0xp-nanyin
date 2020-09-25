package com.joyintech.service.report.poc.portfolio.ptlmock.entity;

public class MockTradeRate extends MockTradeRateKey {
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
     * 数据状态，有效
     */
    private String dataStatus;

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
     * 取得 数据状态，有效
     * @return 数据状态，有效
     */
    public String getDataStatus() {
        return dataStatus;
    }

    /**
     * 设置 数据状态，有效
     * @param dataStatus 数据状态，有效
     */
    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus == null ? null : dataStatus.trim();
    }
}