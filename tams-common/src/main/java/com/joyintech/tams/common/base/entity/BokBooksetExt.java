package com.joyintech.tams.common.base.entity;

public class BokBooksetExt extends BokBooksetExtKey {
    /**
     * 参数值
     */
    private String paramValue;

    /**
     * 参数名
     */
    private String paramName;

    /**
     * 参数说明
     */
    private String remark;

    /**
     * 取得 参数值
     * @return 参数值
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * 设置 参数值
     * @param paramValue 参数值
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue == null ? null : paramValue.trim();
    }

    /**
     * 取得 参数名
     * @return 参数名
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * 设置 参数名
     * @param paramName 参数名
     */
    public void setParamName(String paramName) {
        this.paramName = paramName == null ? null : paramName.trim();
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