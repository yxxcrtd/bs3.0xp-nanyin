package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttSceneFactor extends BaseEntity {
    /**
     * 情景因子代码
     */
    private String sceneFtrId;

    /**
     * 情景因子代码
     */
    private String sceneFtrtipsId;

    /**
     * 情景代码
     */
    private String sceneId;

    /**
     * 因子代码
     */
    private String ftrId;

    /**
     * 因子值结构，数据字典[值结构]
     */
    private String ftrValueStruct;

    /**
     * 因子值
     */
    private String ftrValue;

    /**
     * 因子值类型，数据字典[参数类型]
     */
    private String ftrValueType;

    /**
     * 取得 情景因子代码
     * @return 情景因子代码
     */
    public String getSceneFtrId() {
        return sceneFtrId;
    }

    /**
     * 设置 情景因子代码
     * @param sceneFtrId 情景因子代码
     */
    public void setSceneFtrId(String sceneFtrId) {
        this.sceneFtrId = sceneFtrId == null ? null : sceneFtrId.trim();
    }

    /**
     * 取得 情景因子代码
     * @return 情景因子代码
     */
    public String getSceneFtrtipsId() {
        return sceneFtrtipsId;
    }

    /**
     * 设置 情景因子代码
     * @param sceneFtrtipsId 情景因子代码
     */
    public void setSceneFtrtipsId(String sceneFtrtipsId) {
        this.sceneFtrtipsId = sceneFtrtipsId == null ? null : sceneFtrtipsId.trim();
    }

    /**
     * 取得 情景代码
     * @return 情景代码
     */
    public String getSceneId() {
        return sceneId;
    }

    /**
     * 设置 情景代码
     * @param sceneId 情景代码
     */
    public void setSceneId(String sceneId) {
        this.sceneId = sceneId == null ? null : sceneId.trim();
    }

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
     * 取得 因子值
     * @return 因子值
     */
    public String getFtrValue() {
        return ftrValue;
    }

    /**
     * 设置 因子值
     * @param ftrValue 因子值
     */
    public void setFtrValue(String ftrValue) {
        this.ftrValue = ftrValue == null ? null : ftrValue.trim();
    }

    /**
     * 取得 因子值类型，数据字典[参数类型]
     * @return 因子值类型，数据字典[参数类型]
     */
    public String getFtrValueType() {
        return ftrValueType;
    }

    /**
     * 设置 因子值类型，数据字典[参数类型]
     * @param ftrValueType 因子值类型，数据字典[参数类型]
     */
    public void setFtrValueType(String ftrValueType) {
        this.ftrValueType = ftrValueType == null ? null : ftrValueType.trim();
    }
}