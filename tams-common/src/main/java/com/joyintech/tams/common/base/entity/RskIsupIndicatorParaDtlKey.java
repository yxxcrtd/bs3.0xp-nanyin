package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskIsupIndicatorParaDtlKey extends BaseEntity {
    /**
     * 参数代码
     */
    private String paraCode;

    /**
     * 参数粒度(预占，实占，日间，日终等)
     */
    private String paraGranularity;

    /**
     * 取得 参数代码
     * @return 参数代码
     */
    public String getParaCode() {
        return paraCode;
    }

    /**
     * 设置 参数代码
     * @param paraCode 参数代码
     */
    public void setParaCode(String paraCode) {
        this.paraCode = paraCode == null ? null : paraCode.trim();
    }

    /**
     * 取得 参数粒度(预占，实占，日间，日终等)
     * @return 参数粒度(预占，实占，日间，日终等)
     */
    public String getParaGranularity() {
        return paraGranularity;
    }

    /**
     * 设置 参数粒度(预占，实占，日间，日终等)
     * @param paraGranularity 参数粒度(预占，实占，日间，日终等)
     */
    public void setParaGranularity(String paraGranularity) {
        this.paraGranularity = paraGranularity == null ? null : paraGranularity.trim();
    }
}