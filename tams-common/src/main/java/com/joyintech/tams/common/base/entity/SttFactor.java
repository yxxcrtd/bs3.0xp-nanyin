package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttFactor extends BaseEntity {
    /**
     * 因子代码
     */
    private String ftrId;

    /**
     * 因子名称
     */
    private String ftrName;

    /**
     * 因子类型，数据字典[因子类型]
     */
    private String ftrType;

    /**
     * 因子值结构，数据字典[值结构]
     */
    private String ftrValueStruct;

    /**
     * 值处理方式，数据字典[值处理方式]
     */
    private String valueExeType;

    /**
     * 是否展示
     */
    private String isVisible;

    /**
     * 启用状态
     */
    private String effStatus;

    /**
     * 是否全可用
     */
    private String isAllUseable;

    /**
     * 备注
     */
    private String remark;

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
     * 取得 因子名称
     * @return 因子名称
     */
    public String getFtrName() {
        return ftrName;
    }

    /**
     * 设置 因子名称
     * @param ftrName 因子名称
     */
    public void setFtrName(String ftrName) {
        this.ftrName = ftrName == null ? null : ftrName.trim();
    }

    /**
     * 取得 因子类型，数据字典[因子类型]
     * @return 因子类型，数据字典[因子类型]
     */
    public String getFtrType() {
        return ftrType;
    }

    /**
     * 设置 因子类型，数据字典[因子类型]
     * @param ftrType 因子类型，数据字典[因子类型]
     */
    public void setFtrType(String ftrType) {
        this.ftrType = ftrType == null ? null : ftrType.trim();
    }

    /**
     * 取得 因子值结构，数据字典[值结构]
     * @return 因子值结构，数据字典[值结构]
     */
    public String getFtrValueStruct() {
        return ftrValueStruct;
    }

    /**
     * 设置 因子值结构，数据字典[值结构]
     * @param ftrValueStruct 因子值结构，数据字典[值结构]
     */
    public void setFtrValueStruct(String ftrValueStruct) {
        this.ftrValueStruct = ftrValueStruct == null ? null : ftrValueStruct.trim();
    }

    /**
     * 取得 值处理方式，数据字典[值处理方式]
     * @return 值处理方式，数据字典[值处理方式]
     */
    public String getValueExeType() {
        return valueExeType;
    }

    /**
     * 设置 值处理方式，数据字典[值处理方式]
     * @param valueExeType 值处理方式，数据字典[值处理方式]
     */
    public void setValueExeType(String valueExeType) {
        this.valueExeType = valueExeType == null ? null : valueExeType.trim();
    }

    /**
     * 取得 是否展示
     * @return 是否展示
     */
    public String getIsVisible() {
        return isVisible;
    }

    /**
     * 设置 是否展示
     * @param isVisible 是否展示
     */
    public void setIsVisible(String isVisible) {
        this.isVisible = isVisible == null ? null : isVisible.trim();
    }

    /**
     * 取得 启用状态
     * @return 启用状态
     */
    public String getEffStatus() {
        return effStatus;
    }

    /**
     * 设置 启用状态
     * @param effStatus 启用状态
     */
    public void setEffStatus(String effStatus) {
        this.effStatus = effStatus == null ? null : effStatus.trim();
    }

    /**
     * 取得 是否全可用
     * @return 是否全可用
     */
    public String getIsAllUseable() {
        return isAllUseable;
    }

    /**
     * 设置 是否全可用
     * @param isAllUseable 是否全可用
     */
    public void setIsAllUseable(String isAllUseable) {
        this.isAllUseable = isAllUseable == null ? null : isAllUseable.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}