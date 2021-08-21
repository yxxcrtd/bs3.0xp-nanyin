package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskIsupIndicatorPara extends BaseEntity {
    /**
     * 参数代码
     */
    private String paraCode;

    /**
     * 参数名称
     */
    private String paraName;

    /**
     * 是否禁(可)投类
     */
    private String isForbid;

    /**
     * 禁(可)投可选项
     */
    private String forbidEnum;

    /**
     * 模板代码
     */
    private String modelCode;

    /**
     * 设置的模板变量
     */
    private String modelValue;

    /**
     * 是否更新Redis
     */
    private String isUpRedis;

    /**
     * 参数数据类型
     */
    private String paramDataType;

    /**
     * null
     */
    private String paraType;

    /**
     * 参数计算(实时)方式
     */
    private String paramMathType;

    /**
     * 参数为空显示值
     */
    private String paramValueNone;

    /**
     * 取得 参数代码
     * @return 参数代码
     */
    public String getParaCode() {
        return paraCode;
    }

    /**
     * 设置 参数代码
     * @param paraCode 参数代码
     */
    public void setParaCode(String paraCode) {
        this.paraCode = paraCode == null ? null : paraCode.trim();
    }

    /**
     * 取得 参数名称
     * @return 参数名称
     */
    public String getParaName() {
        return paraName;
    }

    /**
     * 设置 参数名称
     * @param paraName 参数名称
     */
    public void setParaName(String paraName) {
        this.paraName = paraName == null ? null : paraName.trim();
    }

    /**
     * 取得 是否禁(可)投类
     * @return 是否禁(可)投类
     */
    public String getIsForbid() {
        return isForbid;
    }

    /**
     * 设置 是否禁(可)投类
     * @param isForbid 是否禁(可)投类
     */
    public void setIsForbid(String isForbid) {
        this.isForbid = isForbid == null ? null : isForbid.trim();
    }

    /**
     * 取得 禁(可)投可选项
     * @return 禁(可)投可选项
     */
    public String getForbidEnum() {
        return forbidEnum;
    }

    /**
     * 设置 禁(可)投可选项
     * @param forbidEnum 禁(可)投可选项
     */
    public void setForbidEnum(String forbidEnum) {
        this.forbidEnum = forbidEnum == null ? null : forbidEnum.trim();
    }

    /**
     * 取得 模板代码
     * @return 模板代码
     */
    public String getModelCode() {
        return modelCode;
    }

    /**
     * 设置 模板代码
     * @param modelCode 模板代码
     */
    public void setModelCode(String modelCode) {
        this.modelCode = modelCode == null ? null : modelCode.trim();
    }

    /**
     * 取得 设置的模板变量
     * @return 设置的模板变量
     */
    public String getModelValue() {
        return modelValue;
    }

    /**
     * 设置 设置的模板变量
     * @param modelValue 设置的模板变量
     */
    public void setModelValue(String modelValue) {
        this.modelValue = modelValue == null ? null : modelValue.trim();
    }

    /**
     * 取得 是否更新Redis
     * @return 是否更新Redis
     */
    public String getIsUpRedis() {
        return isUpRedis;
    }

    /**
     * 设置 是否更新Redis
     * @param isUpRedis 是否更新Redis
     */
    public void setIsUpRedis(String isUpRedis) {
        this.isUpRedis = isUpRedis == null ? null : isUpRedis.trim();
    }

    /**
     * 取得 参数数据类型
     * @return 参数数据类型
     */
    public String getParamDataType() {
        return paramDataType;
    }

    /**
     * 设置 参数数据类型
     * @param paramDataType 参数数据类型
     */
    public void setParamDataType(String paramDataType) {
        this.paramDataType = paramDataType == null ? null : paramDataType.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getParaType() {
        return paraType;
    }

    /**
     * 设置 null
     * @param paraType null
     */
    public void setParaType(String paraType) {
        this.paraType = paraType == null ? null : paraType.trim();
    }

    /**
     * 取得 参数计算(实时)方式
     * @return 参数计算(实时)方式
     */
    public String getParamMathType() {
        return paramMathType;
    }

    /**
     * 设置 参数计算(实时)方式
     * @param paramMathType 参数计算(实时)方式
     */
    public void setParamMathType(String paramMathType) {
        this.paramMathType = paramMathType == null ? null : paramMathType.trim();
    }

    /**
     * 取得 参数为空显示值
     * @return 参数为空显示值
     */
    public String getParamValueNone() {
        return paramValueNone;
    }

    /**
     * 设置 参数为空显示值
     * @param paramValueNone 参数为空显示值
     */
    public void setParamValueNone(String paramValueNone) {
        this.paramValueNone = paramValueNone == null ? null : paramValueNone.trim();
    }
}