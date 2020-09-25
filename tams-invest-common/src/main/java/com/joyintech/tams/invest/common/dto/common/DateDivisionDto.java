/*
 * 文件名：DateDivisionDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年9月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.dto.common;

import java.util.Date;

import com.joyintech.framework.util.DateUtil;

/**
 * 日期区间分割
 * 
 * @author 何阳阳
 * @version 1.0 2018年9月21日
 * @see DateDivisionDto
 * @since 1.0
 */
public class DateDivisionDto implements Comparable<DateDivisionDto> {

    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 结束日期
     */
    private Date endDate;

    /**
     * 获取 startDate
     * 
     * @return startDate.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置 startDate
     * 
     * @param startDate startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取 endDate
     * 
     * @return endDate.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置 endDate
     * 
     * @param endDate endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(DateDivisionDto o) {
        return DateUtil.compareDate(getEndDate(), o.getEndDate());
    }
}
