/*
 * 文件名：CalanderService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年9月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.calendar.service;

import org.springframework.stereotype.Service;

import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.base.service.RedisBaseService;
import com.joyintech.tams.invest.common.riskinfrds.calendar.entity.ICalendar;

/**
 * 日历信息Redis读写服务
 * 
 * @author zhangrui1
 * @version 1.0 2019年9月26日
 * @see CalendarService
 * @since 1.0
 */
@Service
public class CalendarService extends RedisBaseService<ICalendar> {

    /**
     * 取得 日历信息
     * 
     * @param calendarId 日历编号
     * @return 日历信息
     * @see
     * @since 1.0
     */
    public ICalendar getCalendar(String calendarId) {
        return getInfoFromRedis(RedisKeyEnum.日历信息.key(), calendarId);
    }
}
