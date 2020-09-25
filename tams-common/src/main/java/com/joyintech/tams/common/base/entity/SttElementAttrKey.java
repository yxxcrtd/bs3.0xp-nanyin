package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttElementAttrKey extends BaseEntity {
    /**
     * 元素代码
     */
    private String elementId;

    /**
     * 参数代码
     */
    private String paramCode;

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