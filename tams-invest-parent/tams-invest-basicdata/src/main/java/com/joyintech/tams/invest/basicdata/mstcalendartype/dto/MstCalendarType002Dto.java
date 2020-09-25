package com.joyintech.tams.invest.basicdata.mstcalendartype.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;

/**
 * 日历类型dto
 * 
 * @author liuwei
 * @version 1.0 2018年8月15日
 * @see MstCalendarType001Dto
 * @since 1.0
 */
public class MstCalendarType002Dto extends BaseEntity {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 日历名称
     */
    private String calendarName;
    /**
     * 是否是交易日历
     */
    private String isTradeCalendar;
    /**
     * 节假日标志
     */
    private String isHoliday ;
    /**
     * 日期
     */
    private Date cDate ;
    /**
     * 开始日期
     */
    private String startCDate;

    /**
     * 结束日期
     */
    private String endCDate;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 获取 calendarName
     *
     * @return calendarName
     */
    public String getCalendarName() {
        return calendarName;
    }

    /**
     * 设置 calendarName
     *
     * @param calendarName calendarName
     */
    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

    /**
     * 获取 isTradeCalendar
     *
     * @return isTradeCalendar
     */
    public String getIsTradeCalendar() {
        return isTradeCalendar;
    }

    /**
     * 设置 isTradeCalendar
     *
     * @param isTradeCalendar isTradeCalendar
     */
    public void setIsTradeCalendar(String isTradeCalendar) {
        this.isTradeCalendar = isTradeCalendar;
    }

    /**
     * 获取 isHoliday
     *
     * @return isHoliday
     */
    public String getIsHoliday() {
        return isHoliday;
    }

    /**
     * 设置 isHoliday
     *
     * @param isHoliday isHoliday
     */
    public void setIsHoliday(String isHoliday) {
        this.isHoliday = isHoliday;
    }

    /**
     * 获取 cDate
     *
     * @return cDate
     */
    public Date getcDate() {
        return cDate;
    }

    /**
     * 设置 cDate
     *
     * @param cDate cDate
     */
    public void setcDate(Date cDate) {
        this.cDate = cDate;
    }

    /**
     * 获取 startCDate
     *
     * @return startCDate
     */
    @NotEmpty(fieldName = "MstCalendarType004.cDate",groups={ValidateGroupEdit.class})
    public String getStartCDate() {
        return startCDate;
    }

    /**
     * 设置 startCDate
     *
     * @param startCDate startCDate
     */
    public void setStartCDate(String startCDate) {
        this.startCDate = startCDate;
    }

    /**
     * 获取 endCDate
     *
     * @return endCDate
     */
    public String getEndCDate() {
        return endCDate;
    }

    /**
     * 设置 endCDate
     *
     * @param endCDate endCDate
     */
    public void setEndCDate(String endCDate) {
        this.endCDate = endCDate;
    }

    /**
     * 获取 operateType
     *
     * @return operateType
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     *
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
}
