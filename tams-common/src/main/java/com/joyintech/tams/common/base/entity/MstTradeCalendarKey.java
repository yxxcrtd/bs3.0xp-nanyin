package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class MstTradeCalendarKey extends BaseEntity {
    /**
     * 交易日历代码
     */
    private String tradeCalendarId;

    /**
     * 日历编码
     */
    private String calendarId;

    /**
     * 取得 交易日历代码
     * @return 交易日历代码
     */
    public String getTradeCalendarId() {
        return tradeCalendarId;
    }

    /**
     * 设置 交易日历代码
     * @param tradeCalendarId 交易日历代码
     */
    public void setTradeCalendarId(String tradeCalendarId) {
        this.tradeCalendarId = tradeCalendarId == null ? null : tradeCalendarId.trim();
    }

    /**
     * 取得 日历编码
     * @return 日历编码
     */
    public String getCalendarId() {
        return calendarId;
    }

    /**
     * 设置 日历编码
     * @param calendarId 日历编码
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId == null ? null : calendarId.trim();
    }
}