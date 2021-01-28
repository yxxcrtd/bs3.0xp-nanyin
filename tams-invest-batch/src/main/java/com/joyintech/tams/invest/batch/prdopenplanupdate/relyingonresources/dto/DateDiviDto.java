package com.joyintech.tams.invest.batch.prdopenplanupdate.relyingonresources.dto;

import java.util.Date;

import com.joyintech.framework.util.DateUtil;

/**
 * 日期区间分割
 * 
 * @author 何阳阳
 * @version 1.0 2018年9月21日
 * @see DateDiviDto
 * @since 1.0
 */
public class DateDiviDto implements Comparable<DateDiviDto> {

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
    public int compareTo(DateDiviDto o) {
        return DateUtil.compareDate(getEndDate(), o.getEndDate());
    }
}
