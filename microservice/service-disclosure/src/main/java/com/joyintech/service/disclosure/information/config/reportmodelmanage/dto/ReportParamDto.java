package com.joyintech.service.disclosure.information.config.reportmodelmanage.dto;

public class ReportParamDto {
    /**
     * 参数编号
     */
    private int paramNum;
    /**
     * 参数名称
     */
    private String paramName;
    /**
     * 类型
     */
    private String paramType;
    /**
     * 参数中文描述
     */
    private String name;
    /**
     * 参数中文描述
     */
    private String paramMultiFlag;

    public int getParamNum() {
        return paramNum;
    }

    public void setParamNum(int paramNum) {
        this.paramNum = paramNum;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParamMultiFlag() {
        return paramMultiFlag;
    }

    public void setParamMultiFlag(String paramMultiFlag) {
        this.paramMultiFlag = paramMultiFlag;
    }
}
