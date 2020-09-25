package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttStressInddata extends BaseEntity {
    /**
     * 指标值代码
     */
    private String seqNo;

    /**
     * 模板代码
     */
    private String testId;

    /**
     * 对象代码
     */
    private String objectId;

    /**
     * 元素代码
     */
    private String elementId;

    /**
     * 情景代码
     */
    private String sceneId;

    /**
     * 指标代码
     */
    private String indId;

    /**
     * 指标值结构，数据字典【值结构】
     */
    private String valueStruct;

    /**
     * 指标值
     */
    private String value;

    /**
     * 指标值类型，数据字典【参数类型】
     */
    private String valueType;

    /**
     * 取得 指标值代码
     * @return 指标值代码
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 指标值代码
     * @param seqNo 指标值代码
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 模板代码
     * @return 模板代码
     */
    public String getTestId() {
        return testId;
    }

    /**
     * 设置 模板代码
     * @param testId 模板代码
     */
    public void setTestId(String testId) {
        this.testId = testId == null ? null : testId.trim();
    }

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
     * 取得 元素代码
     * @return 元素代码
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置 元素代码
     * @param elementId 元素代码
     */
    public void setElementId(String elementId) {
        this.elementId = elementId == null ? null : elementId.trim();
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
     * 取得 指标代码
     * @return 指标代码
     */
    public String getIndId() {
        return indId;
    }

    /**
     * 设置 指标代码
     * @param indId 指标代码
     */
    public void setIndId(String indId) {
        this.indId = indId == null ? null : indId.trim();
    }

    /**
     * 取得 指标值结构，数据字典【值结构】
     * @return 指标值结构，数据字典【值结构】
     */
    public String getValueStruct() {
        return valueStruct;
    }

    /**
     * 设置 指标值结构，数据字典【值结构】
     * @param valueStruct 指标值结构，数据字典【值结构】
     */
    public void setValueStruct(String valueStruct) {
        this.valueStruct = valueStruct == null ? null : valueStruct.trim();
    }

    /**
     * 取得 指标值
     * @return 指标值
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置 指标值
     * @param value 指标值
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    /**
     * 取得 指标值类型，数据字典【参数类型】
     * @return 指标值类型，数据字典【参数类型】
     */
    public String getValueType() {
        return valueType;
    }

    /**
     * 设置 指标值类型，数据字典【参数类型】
     * @param valueType 指标值类型，数据字典【参数类型】
     */
    public void setValueType(String valueType) {
        this.valueType = valueType == null ? null : valueType.trim();
    }
}