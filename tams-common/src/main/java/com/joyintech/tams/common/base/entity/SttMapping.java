package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttMapping extends BaseEntity {
    /**
     * 映射代码
     */
    private String mappingId;

    /**
     * 目标代码
     */
    private String tgtCode;

    /**
     * 目标类型
     */
    private String tgtType;

    /**
     * 目标值
     */
    private String tgtValue;

    /**
     * 字典代码
     */
    private String dictCode;

    /**
     * 参数说明
     */
    private String remark;

    /**
     * 取得 映射代码
     * @return 映射代码
     */
    public String getMappingId() {
        return mappingId;
    }

    /**
     * 设置 映射代码
     * @param mappingId 映射代码
     */
    public void setMappingId(String mappingId) {
        this.mappingId = mappingId == null ? null : mappingId.trim();
    }

    /**
     * 取得 目标代码
     * @return 目标代码
     */
    public String getTgtCode() {
        return tgtCode;
    }

    /**
     * 设置 目标代码
     * @param tgtCode 目标代码
     */
    public void setTgtCode(String tgtCode) {
        this.tgtCode = tgtCode == null ? null : tgtCode.trim();
    }

    /**
     * 取得 目标类型
     * @return 目标类型
     */
    public String getTgtType() {
        return tgtType;
    }

    /**
     * 设置 目标类型
     * @param tgtType 目标类型
     */
    public void setTgtType(String tgtType) {
        this.tgtType = tgtType == null ? null : tgtType.trim();
    }

    /**
     * 取得 目标值
     * @return 目标值
     */
    public String getTgtValue() {
        return tgtValue;
    }

    /**
     * 设置 目标值
     * @param tgtValue 目标值
     */
    public void setTgtValue(String tgtValue) {
        this.tgtValue = tgtValue == null ? null : tgtValue.trim();
    }

    /**
     * 取得 字典代码
     * @return 字典代码
     */
    public String getDictCode() {
        return dictCode;
    }

    /**
     * 设置 字典代码
     * @param dictCode 字典代码
     */
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode == null ? null : dictCode.trim();
    }

    /**
     * 取得 参数说明
     * @return 参数说明
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 参数说明
     * @param remark 参数说明
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}