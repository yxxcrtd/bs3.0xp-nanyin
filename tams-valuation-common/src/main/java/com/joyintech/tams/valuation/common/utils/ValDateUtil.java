package com.joyintech.tams.valuation.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * @author maodongjin
 *	2019-08-23
 *	为实现与report包的解耦，将需要的工具方法添加到估值中
 */
public class ValDateUtil extends com.joyintech.framework.util.DateUtil {

    /**
     * 根据格式化字符串格式化日期
     *
     * @param date 格式化前日期
     * @return 格式化后日期字符串（yyyy-MM-dd）
     */
    public static String format(Date date) {
        String s = "";
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT_EN);
            s = sdf.format(date);
        }
        return s;
    }

    /**
     * 根据格式化字符串格式化日期
     *
     * @param date 格式化前日期
     * @param pattern 格式化字符串,如：yyyy-MM-dd
     * @return 格式化后日期字符串
     */
    public static String format(Date date, String pattern) {
        String s = "";
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            s = sdf.format(date);
        }
        return s;
    }


    /**
     * 返回指定日期的季的最后一天
     *
     * @param date 日期
     * @return 日期字符串
     * @throws ParseException 异常
     */
    public static Date getLastDayOfQuarter(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int quarterOfYear = calendar.get(Calendar.MONTH) / 3 + 1;
        int month = 0;
        switch (quarterOfYear) {
            case 1:
                month = 2;
                break;
            case 2:
                month = 5;
                break;
            case 3:
                month = 8;
                break;
            case 4:
                month = 11;
                break;
            default:
                month = calendar.get(Calendar.MONTH);
        }
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 主要功能:获取年初日期 注意事项:无
     *
     * @param date 日期
     * @return 月初日期
     */
    public static Date getLastDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        // 把平台日期赋值给Calendar
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 返回指定日期的季的第一天
     *
     * @param date 日期
     * @return 日期字符串
     * @throws ParseException 异常
     */
    public static Date getFirstDayOfQuarter(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int quarterOfYear = calendar.get(Calendar.MONTH) / 3 + 1;
        int month = 0;
        switch (quarterOfYear) {
            case 1:
                month = 0;
                break;
            case 2:
                month = 3;
                break;
            case 3:
                month = 6;
                break;
            case 4:
                month = 9;
                break;
            default:
                month = calendar.get(Calendar.MONTH);
        }
        calendar.set(calendar.get(Calendar.YEAR), month, 1);
        return calendar.getTime();
    }

}
