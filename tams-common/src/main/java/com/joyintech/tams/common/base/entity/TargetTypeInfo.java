package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class TargetTypeInfo extends BaseEntity {
    /**
     * 指标类型代码
     */
    private String taregetTypeCode;

    /**
     * 指标类型名称
     */
    private String taregetTypeName;

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

    /**
     * 取得 指标类型名称
     * @return 指标类型名称
     */
    public String getTaregetTypeName() {
        return taregetTypeName;
    }

    /**
     * 设置 指标类型名称
     * @param taregetTypeName 指标类型名称
     */
    public void setTaregetTypeName(String taregetTypeName) {
        this.taregetTypeName = taregetTypeName == null ? null : taregetTypeName.trim();
    }
}