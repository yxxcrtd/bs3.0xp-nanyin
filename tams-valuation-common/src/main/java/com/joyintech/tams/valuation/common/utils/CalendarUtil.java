/*
 * 文件名：CalendarUtil.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：daiyue
 * 修改时间：2016年12月12日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.common.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author daiyue
 * @version 1.0 2016年12月12日
 * @see CalendarUtil
 * @since 1.0
 */
public class CalendarUtil {

    /**
     * 日期 年月日 加减
     * @param date 传入日期
     * @param type 年月日类型 (Calendar.YEAR。。。)
     * @param num 加减数
     * @return 处理后的日期
     * @see
     * @since 1.0
     */
    public static Date calendarcal(Date date, int type, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(type, num);
        return calendar.getTime();
    }

    /**
     * 获取年月日信息
     * @param date 传入日期
     * @param type 年月日类型 (Calendar.YEAR。。。)
     * @return 处理后的日期
     * @see
     * @since 1.0
     */
    public static int get(Date date, int type) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(type);
    }

    /**
     * 设置年月日信息
     * @param date 传入日期
     * @param type 年月日类型 (Calendar.YEAR。。。)
     * @param num 设置的值
     * @return 处理后的日期
     * @see
     * @since 1.0
     */
    public static Date set(Date date, int type, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(type, num);
        return calendar.getTime();
    }


    /**
     * 两个日期之前的天数
     * @param fDate 开始时间
     * @param eDate 结束时间
     * @return 天数
     * @see
     * @since 1.0
     */
    public static BigDecimal calendarDays(Date fDate, Date eDate) {
        if (fDate == null || eDate == null) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(DateUtil.getBetweenDayNumber(fDate, eDate));
    }

    /**
     * 判断是否是闰年
     * @param d 时间
     * @return 是否
     * @see
     * @since 1.0
     */
    public static boolean isleapyear(Date d) {
        int year = get(d, Calendar.YEAR);
        if (year % 100 == 0) {
            if (year % 400 == 0) {
                return true;
            }
        }
        else {
            if (year % 4 == 0) {
                return true;
            }
        }
        return false;
    }

	/**
	 * 系统参数转化为Calendar常量
	 *
	 * @param dict
	 *            系统参数
	 * @return Calendar常量
	 * @see
	 * @since 1.0
	 */
    public static Integer convertUnit(String dict) {
        if (DictCodeList.频率_日.getValue().equals(dict)) {
            return Calendar.DATE;
        }
        else if (DictCodeList.频率_月.getValue().equals(dict)) {
            return Calendar.MONTH;
        }
        else if (DictCodeList.频率_年.getValue().equals(dict)) {
            return Calendar.YEAR;
        }
        else {
            return Calendar.DATE;
        }
    }


//        public static void main(String[] args) {
//            System.out.println(CalendarUtil.get(new Date(),
//                Calendar.YEAR));
//        }
    /**
     * 判断是否是季初
     * 
     * @param date 日期
     * @return true:是，false：否
     * @see
     * @since 1.0
     */
    public static boolean isEarssn(Date date) {
        String datefmt = DateUtil.format(date, "yyyy-MM-dd");
        String year = DateUtil.getYearStr(datefmt);
        // 季初日期包括，1月1,4月1,7月1,10月1
        List<String> listEarssn = new ArrayList<String>();
        listEarssn.add(year + "-01-01");
        listEarssn.add(year + "-04-01");
        listEarssn.add(year + "-07-01");
        listEarssn.add(year + "-10-01");
        return listEarssn.contains(datefmt);
    }
    
    /**
     * 判断是否是月初
     * 
     * @param date 日期
     * @return true:是，false：否
     * @see
     * @since 1.0
     */
    public static boolean isFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(date);     
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) { 
            return true; 
        } 
        return false; 
    }

    /**
     * 判断是否为周期时间第一天
     * 
     * @param date 日期
     * @return true:是，false：否
     * @see
     * @since 1.0
     */
    public static boolean isFirstDayByCircle(Date date, String circle) {
        if (DictCodeList.频率_月.getValue().equals(circle)) {
            return isFirstDayOfMonth(date);
        }
        else if (DictCodeList.频率_季.getValue().equals(circle)) {
            return isEarssn(date);
        } 
        else if (DictCodeList.频率_年.getValue().equals(circle)) {
            return isYearFirst(date);
        } 
        return false; 
    }
    /**
     * 判断是否是年初
     * 
     * @param date 日期
     * @return true:是，false：否
     * @see
     * @since 1.0
     */
    public static boolean isYearFirst(Date date) {
        String datefmt = DateUtil.format(date, "yyyy-MM-dd");
        String year = DateUtil.getYearStr(datefmt);
        return (year + "-01-01").equals(datefmt);
    }
    
    /**
     * 查询大于等于当前日期的最小工作日
     * @param date
     * @param holidayList
     * @return
     */
    public static Date getDateF(Date date, List<String> holidayList) {
    	Date newDate = date;
    	for (int i = 0; i < 30; i++) {
    		String datefmt = DateUtil.format(DateUtil.addDay(date, i), "yyyy-MM-dd");
    		if (!holidayList.contains(datefmt)) {
    			newDate = DateUtil.addDay(date, i);
    			break;
    		}
    	}
    	return newDate;
    }
    
    /**
     * 查询小于等于当前日期的最大工作日
     * @param date
     * @param holidayList
     * @return
     */
    public static Date getDateP(Date date, List<String> holidayList) {
    	Date newDate = date;
    	for (int i = 0; i < 30; i++) {
    		String datefmt = DateUtil.format(DateUtil.addDay(date, -i), "yyyy-MM-dd");
    		if (!holidayList.contains(datefmt)) {
    			newDate = DateUtil.addDay(date, -i);
    			break;
    		}
    	}
    	return newDate;
    }
    
    
	/**
	 * 获取日期当前月的对应工作日
	 * 
	 * @param date
	 * @param holidayList
	 * @return
	 */
	public static Date getDateFm(Date date, List<String> holidayList) {
		Date newDate = getDateF(date, holidayList);
		if (DateUtil.getMonth(DateUtil.format(newDate, "yyyy-MM-dd")) == DateUtil
				.getMonth(DateUtil.format(date, "yyyy-MM-dd"))) {
			return newDate;
		} else {
			return getDateP(date, holidayList);
		}

	}
    
	
}
