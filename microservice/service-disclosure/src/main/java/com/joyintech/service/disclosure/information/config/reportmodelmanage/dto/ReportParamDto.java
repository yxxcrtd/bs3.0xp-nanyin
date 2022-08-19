package com.joyintech.service.disclosure.information.config.reportmodelmanage.dto;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeExt1;

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
    public String getParamTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.报表数据格式类型,getParamType());
    }

    public String getParamMultiFlagStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.是否,getParamMultiFlag());
    }
}
