package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttModelIndKey extends BaseEntity {
    /**
     * 模型代码
     */
    private String modelId;

    /**
     * 指标代码
     */
    private String indId;

    /**
     * 取得 模型代码
     * @return 模型代码
     */
    public String getModelId() {
        return modelId;
    }

    /**
     * 设置 模型代码
     * @param modelId 模型代码
     */
    public void setModelId(String modelId) {
        this.modelId = modelId == null ? null : modelId.trim();
    }

    /**
     * 取得 指标代码
     * @return 指标代码
     */
    public String getIndId() {
        return indId;
    }

    /**
     * 设置 指标代码
     * @param indId 指标代码
     */
    public void setIndId(String indId) {
        this.indId = indId == null ? null : indId.trim();
    }
}