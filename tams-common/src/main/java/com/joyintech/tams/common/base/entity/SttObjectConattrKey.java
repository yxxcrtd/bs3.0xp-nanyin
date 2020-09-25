package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttObjectConattrKey extends BaseEntity {
    /**
     * 对象组代码
     */
    private String objectGrpId;

    /**
     * 构成属性
     */
    private String paramCode;

    /**
     * 构成属性值
     */
    private String paramValue;

    /**
     * 取得 对象组代码
     * @return 对象组代码
     */
    public String getObjectGrpId() {
        return objectGrpId;
    }

    /**
     * 设置 对象组代码
     * @param objectGrpId 对象组代码
     */
    public void setObjectGrpId(String objectGrpId) {
        this.objectGrpId = objectGrpId == null ? null : objectGrpId.trim();
    }

    /**
     * 取得 构成属性
     * @return 构成属性
     */
    public String getParamCode() {
        return paramCode;
    }

    /**
     * 设置 构成属性
     * @param paramCode 构成属性
     */
    public void setParamCode(String paramCode) {
        this.paramCode = paramCode == null ? null : paramCode.trim();
    }

    /**
     * 取得 构成属性值
     * @return 构成属性值
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * 设置 构成属性值
     * @param paramValue 构成属性值
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue == null ? null : paramValue.trim();
    }
}