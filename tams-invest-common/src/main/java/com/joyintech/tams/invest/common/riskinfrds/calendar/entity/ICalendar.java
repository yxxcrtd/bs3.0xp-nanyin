/*
 * 文件名：ICalendar.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年9月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.calendar.entity;

import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.base.entity.IBusiness;

/**
 * 日历存储格式
 * 
 * @author zhangrui1
 * @version 1.0 2019年9月26日
 * @see ICalendar
 * @since 1.0
 */
public interface ICalendar extends IBusiness {

    /**
     * {@inheritDoc}
     */
    @Override
    default String getKey() {
        return RedisKeyEnum.日历信息.key();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default String getField() {
        return getCalendarId();
    }

    /**
     * 取得 日历编号
     * 
     * @return 日历编号
     * @see
     * @since 1.0
     */
    String getCalendarId();

    /**
     * 取得 节假日列表Map<日期,是否节假日>
     * 
     * @return 节假日列表
     * @see
     * @since 1.0
     */
    Map<String, Long> getCalendarMap();

    /**
     * 计算日期
     * 
     * @param startDate 起始日期
     * @param day 计算天数
     * @param oper 操作符（+、-）
     * @param dayType 日期类型（自然日、工作日）
     * @return 计算后日期
     * @see
     * @since 1.0
     */
    default String calcDate(String startDate, int day, String oper, String dayType) {
        startDate = DateUtil.format(DateUtil.parse(startDate));
        // 节假日列表为空，则按自然日计算
        if (CollectionUtils.isEmpty(getCalendarMap())
            || CommonUtil.isType(dayType, BussinessDictCodeListRds.特征值偏移单位_自然日)) {
            return DateUtil.addDay(startDate,
                CommonUtil.isType(oper, BussinessDictCodeListRds.操作符_加) ? day : -day);
        }
        // 工作日计数
        int count = 0;
        // 计算单位
        int unit = CommonUtil.isType(oper, BussinessDictCodeListRds.操作符_加) ? 1 : -1;
        // 计算起始日期
        String date = startDate;
        while (true) {
            if (count == day) {
                break;
            }
            // 计算日期
            date = DateUtil.addDay(date, unit);
            // 是否节假日
            if (!isHoliday(date)) {
                count++ ;
            }
        }
        return date;
    }

    /**
     * 判断是否节假日
     * 
     * @return true:节假日，false:工作日
     * @see
     * @since 1.0
     */
    default boolean isHoliday(String dateStr) {
        return getCalendarMap() != null && getCalendarMap().get(dateStr) != null
               && getCalendarMap().get(dateStr) == 1L;
    }
}
