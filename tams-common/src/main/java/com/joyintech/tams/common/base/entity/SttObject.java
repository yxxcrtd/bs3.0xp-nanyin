package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttObject extends BaseEntity {
    /**
     * 对象代码
     */
    private String objectId;

    /**
     * 对象名称
     */
    private String objectName;

    /**
     * 源系统代码
     */
    private String sourceId;

    /**
     * 源系统类型
     */
    private String sourceType;

    /**
     * 取得 对象代码
     * @return 对象代码
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * 设置 对象代码
     * @param objectId 对象代码
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    /**
     * 取得 对象名称
     * @return 对象名称
     */
    public String getObjectName() {
        return objectName;
    }

    /**
     * 设置 对象名称
     * @param objectName 对象名称
     */
    public void setObjectName(String objectName) {
        this.objectName = objectName == null ? null : objectName.trim();
    }

    /**
     * 取得 源系统代码
     * @return 源系统代码
     */
    public String getSourceId() {
        return sourceId;
    }

    /**
     * 设置 源系统代码
     * @param sourceId 源系统代码
     */
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId == null ? null : sourceId.trim();
    }

    /**
     * 取得 源系统类型
     * @return 源系统类型
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * 设置 源系统类型
     * @param sourceType 源系统类型
     */
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType == null ? null : sourceType.trim();
    }
}