/*
 * 文件名：ImplCalendar.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年10月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.redisinit.dto;

import com.joyintech.tams.common.base.entity.MstHoliday;
import com.joyintech.tams.invest.common.riskinfrds.calendar.entity.ICalendar;

import java.util.Map;

/**
 * 日历信息实现类
 * @author 裴家俊
 * @version 1.0 2019年10月2日
 * @see ImplCalendar
 * @since 1.0
 */
public class ImplCalendar extends MstHoliday implements ICalendar {

    private Map<String, Long> calendarMap;

    @Override
    public Map<String, Long> getCalendarMap() {
        return calendarMap;
    }

    public void setCalendarMap(Map<String, Long> calendarMap) {
        this.calendarMap = calendarMap;
    }
}
