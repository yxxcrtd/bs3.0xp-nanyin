/*
 * 文件名：CustomerReportConfig001Dto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：陈卓
 * 修改时间：2018年1月11日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.service.valuation.accounadjustmanage.booksetdateswitch.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * @author 陈卓
 * @version 1.0 2018年1月11日
 * @see CustomerReportConfig001Dto
 * @since 1.0
 */
public class ValBooksetDateSwitch001Dto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 账套名称
     */
    private String booksetName;

    /**
     * 日期
     */
    private String booksetDate;
    
    /**
     * 账套开始日
     */
    private String startDate;
    
    /**
     * 账套结束日
     */
    private String endDate;

    /**
     * 获取 booksetId
     * 
     * @return booksetId.
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 booksetId
     * 
     * @param booksetId booksetId
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    /**
     * 获取 booksetDate
     * 
     * @return booksetDate.
     */
    public String getBooksetDate() {
        return booksetDate;
    }

    /**
     * 设置 booksetDate
     * 
     * @param booksetDate booksetDate
     */
    public void setBooksetDate(String booksetDate) {
        this.booksetDate = booksetDate;
    }

    /**
     * 获取 账套名称
     * 
     * @return 账套名称
     */
    public String getBooksetName() {
        return booksetName;
    }

    /**
     * 设置 账套名称
     * 
     * @param booksetName 账套名称
     */
    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName;
    }

    /**
     * 获取账套开始日
     * @return
     * @see
     * @since 1.0
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * 设置账套开始日
     * @param startDate
     * @see
     * @since 1.0
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取账套结束日
     * @return
     * @see
     * @since 1.0
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * 设置账套结束日
     * @param endDate
     * @see
     * @since 1.0
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    

}
