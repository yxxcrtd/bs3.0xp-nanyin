/*
 * 文件名：DateCalcUtil.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：何阳阳
 * 修改时间：2017年11月9日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
 
package com.joyintech.tams.report.common.util;

import java.util.Date;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;

/**
 * 日期处理共通<br>
 * @author 何阳阳
 * @version 1.0 2017年11月9日
 * @see DateCalcUtil
 * @since 1.0
 */
public class DateCalcUtil {
    
    /**
     * 获取一天的开始时间
     * <pre>
     *  按中债规定，当日的22:00至次日 01 ：00中债系统关闭，不处理上传的任何数据，也就是22:00之后不能报送。
     *  那么系统认为当日时间到22:00结束，22:00以后为次日开始时间
     * </pre>
     * @param date 日期
     * @return 计算后的日期
     */
    public static Date getStartOfDay(Date date) {
        if (date == null) {
            return date;
        }
        
        Date date1 = DateUtil.addDay(date, -1);
        String time = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, ReportGlobalConstant.SUBMIT_TIME);
        return DateUtil.parse(DateUtil.format(date1) + StringUtil.SPACE + time);
    }
    
    /**
     * 获取一天的开始时间
     * <pre>
     *  按中债规定，当日的22:00至次日 01 ：00中债系统关闭，不处理上传的任何数据，也就是22:00之后不能报送。
     *  那么系统认为当日时间到22:00结束，22:00以后为次日开始时间
     * </pre>
     * @param date 日期
     * @return 计算后的日期
     */
    public static Date getEndOfDay(Date date) {
        if (date == null) {
            return date;
        }
        
        String time = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, ReportGlobalConstant.SUBMIT_TIME);
        return DateUtil.parse(DateUtil.format(date) + StringUtil.SPACE + time);
    }
    
    /**
     * 获取一天的开始时间(字符串类型 yyyy-MM-dd)
     * <pre>
     *  按中债规定，当日的22:00至次日 01 ：00中债系统关闭，不处理上传的任何数据，也就是22:00之后不能报送。
     *  那么系统认为当日时间到22:00结束，22:00以后为次日开始时间
     * </pre>
     * @param date 日期
     * @return 计算后的日期
     */
    public static String getStartOfDay(String date) {
        String time = StringUtil.EMPTY;
        if(StringUtil.isEmpty(date)){
            return time;
        }
        String date1 = DateUtil.addDay(date, -1);
        time = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, ReportGlobalConstant.SUBMIT_TIME);
        return date1 + StringUtil.SPACE + time;
    }
    
    /**
     * 获取一天的开始时间(字符串类型 yyyy-MM-dd)
     * <pre>
     *  按中债规定，当日的22:00至次日 01 ：00中债系统关闭，不处理上传的任何数据，也就是22:00之后不能报送。
     *  那么系统认为当日时间到22:00结束，22:00以后为次日开始时间
     * </pre>
     * @param date 日期
     * @return 计算后的日期
     */
    public static String getEndOfDay(String date) {
        String time = StringUtil.EMPTY;
        if(StringUtil.isEmpty(date)){
            return time;
        }
        time = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY,
            ReportGlobalConstant.SUBMIT_TIME);
        return date + StringUtil.SPACE + time;
    }
    
    /**
     * 功能:获取一天的开始时间
     * 
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月22日
     * @param date
     *            日期
     * @param timeConfig
     *            时间单位（时分秒）配置项，取自系统参数,如果取不到则取timeConfi为HH:MM:SS
     * @return 计算后的日期 Date
     */
    public static Date getStartOfDay(Date date, String timeConfig) {
        if (date == null) {
            return date;
        }
        Date date1 = DateUtil.addDay(date, -1);
        String time = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, timeConfig);
        return DateUtil.parse(DateUtil.format(date1) + StringUtil.SPACE + time);
    }

    /**
     * 功能:获取一天的结束时间
     * 
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月22日
     * @param date
     *            日期
     * @param timeConfig
     *            时间单位（时分秒）配置项，取自系统参数,如果取不到则取timeConfi为HH:MM:SS
     * @return 计算后的日期 Date
     */
    public static Date getEndOfDay(Date date, String timeConfig) {
        if (date == null) {
            return date;
        }
        String time = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, timeConfig);
        return DateUtil.parse(DateUtil.format(date) + StringUtil.SPACE + time);
    }

    /**
     * 功能:获取一天的开始时间(字符串类型 yyyy-MM-dd)
     * 
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月22日
     * @param date
     *            日期
     * @param timeConfig
     *            时间单位（时分秒）配置项，取自系统参数,如果取不到则取timeConfi为HH:MM:SS
     * @return 计算后的日期 Date
     */
    public static String getStartOfDay(String date, String timeConfig) {
        String time = StringUtil.EMPTY;
        if (StringUtil.isEmpty(date)) {
            return time;
        }
        String date1 = DateUtil.addDay(date, -1);
        time = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, timeConfig);
        return date1 + StringUtil.SPACE + time;
    }

    /**
     * 功能:获取一天的结束时间(字符串类型 yyyy-MM-dd)
     * 
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月22日
     * @param date
     *            日期
     * @param timeConfig
     *            时间单位（时分秒）配置项，取自系统参数,如果取不到则取timeConfi为HH:MM:SS
     * @return 计算后的日期 Date
     */
    public static String getEndOfDay(String date, String timeConfig) {
        String time = StringUtil.EMPTY;
        if (StringUtil.isEmpty(date)) {
            return time;
        }
        time = CacheUtil.getAppParamCache(GlobalConstant.SYS_PARA_KEY, timeConfig);
        return date + StringUtil.SPACE + time;
    }
}
