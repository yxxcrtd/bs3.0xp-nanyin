package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class MstCalendarInfo extends BaseEntity {
    /**
     * 日历编号
     */
    private String calendarId;

    /**
     * 日历名称
     */
    private String calendarName;

    /**
     * 第三方日历编号，比如万得的交易场所
     */
    private String otherId;

    /**
     * 是否交易日历
     */
    private String isTradeCalendar;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 日历编号
     * @return 日历编号
     */
    public String getCalendarId() {
        return calendarId;
    }

    /**
     * 设置 日历编号
     * @param calendarId 日历编号
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId == null ? null : calendarId.trim();
    }

    /**
     * 取得 日历名称
     * @return 日历名称
     */
    public String getCalendarName() {
        return calendarName;
    }

    /**
     * 设置 日历名称
     * @param calendarName 日历名称
     */
    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName == null ? null : calendarName.trim();
    }

    /**
     * 取得 第三方日历编号，比如万得的交易场所
     * @return 第三方日历编号，比如万得的交易场所
     */
    public String getOtherId() {
        return otherId;
    }

    /**
     * 设置 第三方日历编号，比如万得的交易场所
     * @param otherId 第三方日历编号，比如万得的交易场所
     */
    public void setOtherId(String otherId) {
        this.otherId = otherId == null ? null : otherId.trim();
    }

    /**
     * 取得 是否交易日历
     * @return 是否交易日历
     */
    public String getIsTradeCalendar() {
        return isTradeCalendar;
    }

    /**
     * 设置 是否交易日历
     * @param isTradeCalendar 是否交易日历
     */
    public void setIsTradeCalendar(String isTradeCalendar) {
        this.isTradeCalendar = isTradeCalendar == null ? null : isTradeCalendar.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}