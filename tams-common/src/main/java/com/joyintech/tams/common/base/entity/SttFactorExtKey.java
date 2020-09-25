package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttFactorExtKey extends BaseEntity {
    /**
     * 因子代码
     */
    private String ftrId;

    /**
     * 配置代码
     */
    private String configId;

    /**
     * 取得 因子代码
     * @return 因子代码
     */
    public String getFtrId() {
        return ftrId;
    }

    /**
     * 设置 因子代码
     * @param ftrId 因子代码
     */
    public void setFtrId(String ftrId) {
        this.ftrId = ftrId == null ? null : ftrId.trim();
    }

    /**
     * 取得 配置代码
     * @return 配置代码
     */
    public String getConfigId() {
        return configId;
    }

    /**
     * 设置 配置代码
     * @param configId 配置代码
     */
    public void setConfigId(String configId) {
        this.configId = configId == null ? null : configId.trim();
    }
}