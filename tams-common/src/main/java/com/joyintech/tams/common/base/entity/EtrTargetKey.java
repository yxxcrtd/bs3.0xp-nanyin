package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class EtrTargetKey extends BaseEntity {
    /**
     * 委托代码
     */
    private String entrustId;

    /**
     * 标的金融产品代码
     */
    private String targetFinprodId;

    /**
     * 取得 委托代码
     * @return 委托代码
     */
    public String getEntrustId() {
        return entrustId;
    }

    /**
     * 设置 委托代码
     * @param entrustId 委托代码
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId == null ? null : entrustId.trim();
    }

    /**
     * 取得 标的金融产品代码
     * @return 标的金融产品代码
     */
    public String getTargetFinprodId() {
        return targetFinprodId;
    }

    /**
     * 设置 标的金融产品代码
     * @param targetFinprodId 标的金融产品代码
     */
    public void setTargetFinprodId(String targetFinprodId) {
        this.targetFinprodId = targetFinprodId == null ? null : targetFinprodId.trim();
    }
}