package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class TaxCfBokConfig extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 配置项代码
     */
    private String configId;

    /**
     * 配置项值
     */
    private String configValue;

    /**
     * 记账分类配置
     */
    private String bokConfigId;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流水号
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 配置项代码
     * @return 配置项代码
     */
    public String getConfigId() {
        return configId;
    }

    /**
     * 设置 配置项代码
     * @param configId 配置项代码
     */
    public void setConfigId(String configId) {
        this.configId = configId == null ? null : configId.trim();
    }

    /**
     * 取得 配置项值
     * @return 配置项值
     */
    public String getConfigValue() {
        return configValue;
    }

    /**
     * 设置 配置项值
     * @param configValue 配置项值
     */
    public void setConfigValue(String configValue) {
        this.configValue = configValue == null ? null : configValue.trim();
    }

    /**
     * 取得 记账分类配置
     * @return 记账分类配置
     */
    public String getBokConfigId() {
        return bokConfigId;
    }

    /**
     * 设置 记账分类配置
     * @param bokConfigId 记账分类配置
     */
    public void setBokConfigId(String bokConfigId) {
        this.bokConfigId = bokConfigId == null ? null : bokConfigId.trim();
    }
}