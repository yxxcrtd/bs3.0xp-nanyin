package com.joyintech.tams.common.base.entity;

public class SttObjectAttr extends SttObjectAttrKey {
    /**
     * 参数类型
     */
    private String paramType;

    /**
     * 参数值
     */
    private String paramValue;

    /**
     * 字典代码
     */
    private String dictCode;

    /**
     * 参数说明
     */
    private String remark;

    /**
     * 取得 参数类型
     * @return 参数类型
     */
    public String getParamType() {
        return paramType;
    }

    /**
     * 设置 参数类型
     * @param paramType 参数类型
     */
    public void setParamType(String paramType) {
        this.paramType = paramType == null ? null : paramType.trim();
    }

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