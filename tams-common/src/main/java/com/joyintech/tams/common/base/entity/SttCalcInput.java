package com.joyintech.tams.common.base.entity;

public class SttCalcInput extends SttCalcInputKey {
    /**
     * 参数值,常量时使用
     */
    private String paramValue;

    /**
     * 参数类型，数据字典[参数类型]
     */
    private String paramType;

    /**
     * 取得 参数值,常量时使用
     * @return 参数值,常量时使用
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * 设置 参数值,常量时使用
     * @param paramValue 参数值,常量时使用
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue == null ? null : paramValue.trim();
    }

    /**
     * 取得 参数类型，数据字典[参数类型]
     * @return 参数类型，数据字典[参数类型]
     */
    public String getParamType() {
        return paramType;
    }

    /**
     * 设置 参数类型，数据字典[参数类型]
     * @param paramType 参数类型，数据字典[参数类型]
     */
    public void setParamType(String paramType) {
        this.paramType = paramType == null ? null : paramType.trim();
    }
}