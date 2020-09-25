/*
 * 文件名：CustomerReportConfig002Dto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：陈卓
 * 修改时间：2018年1月11日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.service.valuation.accounadjustmanage.booksetdateswitch.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.system.customerreport.config.dto.CustomerReportConfig002Dto;
import com.joyintech.service.valuation.accounadjustmanage.booksetdateswitch.validator.ValBooksetDateSwitch002Validator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 报表定义一览的Dto
 * 
 * @author 陈卓
 * @version 1.0 2018年1月11日
 * @see CustomerReportConfig002Dto
 * @since 1.0
 */
@ValidLogic(targetClass = ValBooksetDateSwitch002Validator.class)
public class ValBooksetDateSwitch002Dto extends BaseEntity implements ProcessEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 账套代码
     */
    @PrimaryKey(value=Field.PK_1)
    @NotEmpty(fieldName = "ValZhangDateSwitch001.booksetId")
    private String booksetId;

    /**
     * 账套日期
     */
    @NotEmpty(fieldName = "ValZhangDateSwitch001.booksetDate")
    private String booksetDate;
    
    @NotEmpty(fieldName = "ValZhangDateSwitch001.booksetName")
    private String booksetName;
    
    
    /**
     * 获取 booksetName
     * @return booksetName.
     */
    public String getBooksetName() {
        return booksetName;
    }

    
    /**
     * 设置 booksetName
     * @param booksetName booksetName
     */
    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName;
    }

    /**
     * 获取 账套代码
     * 
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * 
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    /**
     * 获取 账套日期
     * 
     * @return 账套日期
     */
    public String getBooksetDate() {
        return booksetDate;
    }

    /**
     * 设置 账套日期
     * 
     * @param booksetDate 账套日期
     */
    public void setBooksetDate(String booksetDate) {
        this.booksetDate = booksetDate;
    }

}
