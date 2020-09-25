/*
 * 文件名：TemplateCheckDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年11月15日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.datatransplant.dto;

/**
 * 校验信息dto
 * 
 * @author niuqianyu
 * @version 1.0 2018年11月15日
 * @see TemplateCheckDto
 * @since 1.0
 */
public class TemplateCheckDto {

    /**
     * 警告/错误行号
     */
    private int errorLineNum;

    /**
     * 警告/错误
     */
    private String errorType;

    /**
     * 警告/错误信息
     */
    private String errorMessage;

    /**
     * 获取 errorLineNum
     * 
     * @return errorLineNum.
     */
    public int getErrorLineNum() {
        return errorLineNum;
    }

    /**
     * 设置 errorLineNum
     * 
     * @param errorLineNum errorLineNum
     */
    public void setErrorLineNum(int errorLineNum) {
        this.errorLineNum = errorLineNum;
    }

    /**
     * 获取 errorType
     * 
     * @return errorType.
     */
    public String getErrorType() {
        return errorType;
    }

    /**
     * 设置 errorType
     * 
     * @param errorType errorType
     */
    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    /**
     * 获取 errorMessage
     * 
     * @return errorMessage.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * 设置 errorMessage
     * 
     * @param errorMessage errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
