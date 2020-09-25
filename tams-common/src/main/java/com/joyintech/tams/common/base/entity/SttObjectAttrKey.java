package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttObjectAttrKey extends BaseEntity {
    /**
     * 对象代码，组合、产品、资产代码
     */
    private String objectId;

    /**
     * 参数代码
     */
    private String paramCode;

    /**
     * 取得 对象代码，组合、产品、资产代码
     * @return 对象代码，组合、产品、资产代码
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * 设置 对象代码，组合、产品、资产代码
     * @param objectId 对象代码，组合、产品、资产代码
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    /**
     * 取得 参数代码
     * @return 参数代码
     */
    public String getParamCode() {
        return paramCode;
    }

    /**
     * 设置 参数代码
     * @param paramCode 参数代码
     */
    public void setParamCode(String paramCode) {
        this.paramCode = paramCode == null ? null : paramCode.trim();
    }
}