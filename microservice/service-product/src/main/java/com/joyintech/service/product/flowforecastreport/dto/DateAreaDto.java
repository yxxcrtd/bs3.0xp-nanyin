/*
 * 文件名：DateAreaDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2019年8月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.flowforecastreport.dto;

import java.util.Date;

/**
 * 日期区间Dto
 * 
 * @author weipeng
 * @version 1.0 2019年8月13日
 * @see DateAreaDto
 * @since 1.0
 */
public class DateAreaDto {

    /**
     * 开始日期
     */
    private Date beginDate;

    /**
     * 开始日期
     */
    private Date endDate;

    /**
     * 日期区间
     */
    private String dateArea;

    /**
     * 获取 开始时间
     * 
     * @return 开始时间
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * 设置 开始时间
     * 
     * @param beginDate 开始时间
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * 获取 结束时间
     * 
     * @return 结束时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置 结束时间
     * 
     * @param endDate 结束时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取 日期区间
     * 
     * @return 日期区间
     */
    public String getDateArea() {
        return dateArea;
    }

    /**
     * 设置 日期区间
     * 
     * @param dateArea 日期区间
     */
    public void setDateArea(String dateArea) {
        this.dateArea = dateArea;
    }

}
