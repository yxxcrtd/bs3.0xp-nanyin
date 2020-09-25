package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class TargetDetailKey extends BaseEntity {
    /**
     * 指标明细代码
     */
    private String taregetCode;

    /**
     * 指标类型代码
     */
    private String taregetTypeCode;

    /**
     * 取得 指标明细代码
     * @return 指标明细代码
     */
    public String getTaregetCode() {
        return taregetCode;
    }

    /**
     * 设置 指标明细代码
     * @param taregetCode 指标明细代码
     */
    public void setTaregetCode(String taregetCode) {
        this.taregetCode = taregetCode == null ? null : taregetCode.trim();
    }

    /**
     * 取得 指标类型代码
     * @return 指标类型代码
     */
    public String getTaregetTypeCode() {
        return taregetTypeCode;
    }

    /**
     * 设置 指标类型代码
     * @param taregetTypeCode 指标类型代码
     */
    public void setTaregetTypeCode(String taregetTypeCode) {
        this.taregetTypeCode = taregetTypeCode == null ? null : taregetTypeCode.trim();
    }
}