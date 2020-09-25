package com.joyintech.service.interf.cache.entity;

/**
 * 系统参数类
 * 
 * @author zhangrui1
 * @version 1.0 2018年12月6日
 * @see SysPara
 * @since 1.0
 */
public class SysPara extends SysParaKey {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1661447537502590973L;

    /**
     * 参数值
     */
    private String paramValue;

    /**
     * 数据类型(01:文字列,02:下拉框,03:日期)
     */
    private String dataType;

    /**
     * 选择范围
     */
    private String dataScope;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 参数值
     * 
     * @return 参数值
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * 设置 参数值
     * 
     * @param paramValue 参数值
     */
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue == null ? null : paramValue.trim();
    }

    /**
     * 取得 数据类型(01:文字列,02:下拉框,03:日期)
     * 
     * @return 数据类型(01:文字列,02:下拉框,03:日期)
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * 设置 数据类型(01:文字列,02:下拉框,03:日期)
     * 
     * @param dataType 数据类型(01:文字列,02:下拉框,03:日期)
     */
    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    /**
     * 取得 选择范围
     * 
     * @return 选择范围
     */
    public String getDataScope() {
        return dataScope;
    }

    /**
     * 设置 选择范围
     * 
     * @param dataScope 选择范围
     */
    public void setDataScope(String dataScope) {
        this.dataScope = dataScope == null ? null : dataScope.trim();
    }

    /**
     * 取得 备注
     * 
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * 
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}