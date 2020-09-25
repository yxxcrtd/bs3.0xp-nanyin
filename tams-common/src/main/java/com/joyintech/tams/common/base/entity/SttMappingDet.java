package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttMappingDet extends BaseEntity {
    /**
     * 明细代码
     */
    private String detId;

    /**
     * 映射代码
     */
    private String mappingId;

    /**
     * 源代码
     */
    private String souceCode;

    /**
     * 源类型
     */
    private String souceType;

    /**
     * 源值
     */
    private String souceValue;

    /**
     * 字典代码
     */
    private String dictCode;

    /**
     * 级别
     */
    private Long level;

    /**
     * 父明细代码
     */
    private String fDetId;

    /**
     * 参数说明
     */
    private String remark;

    /**
     * 取得 明细代码
     * @return 明细代码
     */
    public String getDetId() {
        return detId;
    }

    /**
     * 设置 明细代码
     * @param detId 明细代码
     */
    public void setDetId(String detId) {
        this.detId = detId == null ? null : detId.trim();
    }

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
     * 取得 源代码
     * @return 源代码
     */
    public String getSouceCode() {
        return souceCode;
    }

    /**
     * 设置 源代码
     * @param souceCode 源代码
     */
    public void setSouceCode(String souceCode) {
        this.souceCode = souceCode == null ? null : souceCode.trim();
    }

    /**
     * 取得 源类型
     * @return 源类型
     */
    public String getSouceType() {
        return souceType;
    }

    /**
     * 设置 源类型
     * @param souceType 源类型
     */
    public void setSouceType(String souceType) {
        this.souceType = souceType == null ? null : souceType.trim();
    }

    /**
     * 取得 源值
     * @return 源值
     */
    public String getSouceValue() {
        return souceValue;
    }

    /**
     * 设置 源值
     * @param souceValue 源值
     */
    public void setSouceValue(String souceValue) {
        this.souceValue = souceValue == null ? null : souceValue.trim();
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
     * 取得 级别
     * @return 级别
     */
    public Long getLevel() {
        return level;
    }

    /**
     * 设置 级别
     * @param level 级别
     */
    public void setLevel(Long level) {
        this.level = level;
    }

    /**
     * 取得 父明细代码
     * @return 父明细代码
     */
    public String getfDetId() {
        return fDetId;
    }

    /**
     * 设置 父明细代码
     * @param fDetId 父明细代码
     */
    public void setfDetId(String fDetId) {
        this.fDetId = fDetId == null ? null : fDetId.trim();
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