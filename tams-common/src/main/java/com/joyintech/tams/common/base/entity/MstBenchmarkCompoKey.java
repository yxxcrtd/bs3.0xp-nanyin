package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class MstBenchmarkCompoKey extends BaseEntity {
    /**
     * 基准编号
     */
    private String benchmarkNo;

    /**
     * 行情代码
     */
    private String intRateId;

    /**
     * 取得 基准编号
     * @return 基准编号
     */
    public String getBenchmarkNo() {
        return benchmarkNo;
    }

    /**
     * 设置 基准编号
     * @param benchmarkNo 基准编号
     */
    public void setBenchmarkNo(String benchmarkNo) {
        this.benchmarkNo = benchmarkNo == null ? null : benchmarkNo.trim();
    }

    /**
     * 取得 行情代码
     * @return 行情代码
     */
    public String getIntRateId() {
        return intRateId;
    }

    /**
     * 设置 行情代码
     * @param intRateId 行情代码
     */
    public void setIntRateId(String intRateId) {
        this.intRateId = intRateId == null ? null : intRateId.trim();
    }
}