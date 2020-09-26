/*
 * 文件名：ErrorMessageType.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：zyf
 * 修改时间：2017年9月14日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.submitmaintenance.businessdetailed.entity;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zyf
 * @version 1.0 2017年9月14日
 * @see ValidMessage
 * @since 1.0
 */
public class ValidMessage implements java.io.Serializable{
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 4895241136274204703L;

    /**
     * jsp画面定义的元素ID或name
     */
    private String field;
    
    /**
     * 校检名称
     */
    private String checkMessageName;
    
    /**
     * 校检内容
     */
    private String checkContext;
    
    /**
     * 校检分类
     */
    private String checkType;
    
    /**
     * 错误信息
     */
    private String errorMessage;
    
    /**
     * @param field 画面元素ID
     * @param message 错误消息
     */
    public ValidMessage(String field, String errorMessage) {
        super();
        this.field = field;
        this.errorMessage = errorMessage;
    }

    /**
     * @param message 错误消息
     */
    public ValidMessage(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }

    
    
    /**
     * 获取 field
     * @return field.
     */
    public String getField() {
        return field;
    }


    
    /**
     * 设置 field
     * @param field field
     */
    public void setField(String field) {
        this.field = field;
    }


    /**
     * 获取 checkMessageName
     * @return checkMessageName.
     */
    public String getCheckMessageName() {
        return checkMessageName;
    }

    
    /**
     * 设置 checkMessageName
     * @param checkMessageName checkMessageName
     */
    public void setCheckMessageName(String checkMessageName) {
        this.checkMessageName = checkMessageName;
    }

    
    /**
     * 获取 checkContext
     * @return checkContext.
     */
    public String getCheckContext() {
        return checkContext;
    }

    
    /**
     * 设置 checkContext
     * @param checkContext checkContext
     */
    public void setCheckContext(String checkContext) {
        this.checkContext = checkContext;
    }

    
    /**
     * 获取 checkType
     * @return checkType.
     */
    public String getCheckType() {
        return checkType;
    }

    
    /**
     * 设置 checkType
     * @param checkType checkType
     */
    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    
    /**
     * 获取 errorMessage
     * @return errorMessage.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    
    /**
     * 设置 errorMessage
     * @param errorMessage errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    
}
