package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttModelInputKey extends BaseEntity {
    /**
     * 参数代码
     */
    private String paramCode;

    /**
     * 模型代码
     */
    private String modelId;

    /**
     * 取得 参数代码
     * @return 参数代码
     */
    public String getParamCode() {
        return paramCode;
    }

    /**
     * 设置 参数代码
     * @param paramCode 参数代码
     */
    public void setParamCode(String paramCode) {
        this.paramCode = paramCode == null ? null : paramCode.trim();
    }

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
}