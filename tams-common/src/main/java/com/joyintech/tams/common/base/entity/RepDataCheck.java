package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RepDataCheck extends BaseEntity {
    /**
     * 校验编号
     */
    private String checkId;
    /**
     * 校验方式
     */
    private String checkType;

    /**
     * 校验描述
     */
    private String checkDesc;

    /**
     * 参数值1
     */
    private String paramValue1;

    /**
     * 参数值2
     */
    private String paramValue2;

    private String paramValue;

    /**
     * 取得 校验方式
     * @return 校验方式
     */
    public String getCheckType() {
        return checkType;
    }

    /**
     * 设置 校验方式
     * @param checkType 校验方式
     */
    public void setCheckType(String checkType) {
        this.checkType = checkType == null ? null : checkType.trim();
    }

    /**
     * 取得 校验描述
     * @return 校验描述
     */
    public String getCheckDesc() {
        return checkDesc;
    }

    /**
     * 设置 校验描述
     * @param checkDesc 校验描述
     */
    public void setCheckDesc(String checkDesc) {
        this.checkDesc = checkDesc == null ? null : checkDesc.trim();
    }

    /**
     * 取得 参数值1
     * @return 参数值1
     */
    public String getParamValue1() {
        return paramValue1;
    }

    /**
     * 设置 参数值1
     * @param paramValue1 参数值1
     */
    public void setParamValue1(String paramValue1) {
        this.paramValue1 = paramValue1 == null ? null : paramValue1.trim();
    }

    /**
     * 取得 参数值2
     * @return 参数值2
     */
    public String getParamValue2() {
        return paramValue2;
    }

    /**
     * 设置 参数值2
     * @param paramValue2 参数值2
     */
    public void setParamValue2(String paramValue2) {
        this.paramValue2 = paramValue2 == null ? null : paramValue2.trim();
    }

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }
}