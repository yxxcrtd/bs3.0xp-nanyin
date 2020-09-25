package com.joyintech.tams.invest.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.client.utils.DateUtils;

import com.joyintech.framework.util.DateUtil;


public class PtlDateUtil {
	
	 /**
     * 每周的第一天
     * @param dataStr
     * @param dateFormat
     * @param resultDateFormat
     * @return
     * @throws ParseException
     */
    public static Date getCurrWeekDate(Date date) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int d = 0;
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            d = -6;
        } else {
            d = 2 - cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);
        // 所在周开始日期
        return cal.getTime();

    }
	
	/**
	 * 获取当月第一天
	 * @param dataStr
	 * @param dateFormat
	 * @param resultDateFormat
	 * @return
	 * @throws ParseException
	 */
    public static Date getCurrMonthDate(Date date) throws ParseException {
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        return c.getTime();
    }
    
    

	/**
	 * 获取当前季度第一天
	 *
	 */
	public static Date getCurrQuarterDate(Date date) {
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(cal.MONTH) + 1;
		int quarter = 0;
		if (month >= 1 && month <= 3) {
			quarter = 1;
		} else if (month >= 4 && month <= 6) {
			quarter = 2;
		} else if (month >= 7 && month <= 9) {
			quarter = 3;
		} else {
			quarter = 4;
		}
		return getCurrQuarter(quarter);
	}
	
	/**
	 * 获取当前年的第一天
	 * @param date
	 * @return
	 */
	public static Date getCurrYearDate(Date date){
		String str = DateUtils.formatDate(date, "yyyy-MM-dd");
		str = str.substring(0, str.length() - 5) + "01-01";
		return DateUtil.parse(str);
	}
	
	

	public static Date getCurrQuarter(int num) {
		String str = "";
		// 设置本年的季
		Calendar quarterCalendar = null;
		switch (num) {
			case 1: // 本年到现在经过了一个季度，在加上前4个季度
				quarterCalendar = Calendar.getInstance();
				quarterCalendar.set(Calendar.MONTH, 3);
				quarterCalendar.set(Calendar.DATE, 1);
				quarterCalendar.add(Calendar.DATE, -1);
				str = DateUtils.formatDate(quarterCalendar.getTime(), "yyyy-MM-dd");
				str = str.substring(0, str.length() - 5) + "01-01";
				break;
			case 2: // 本年到现在经过了二个季度，在加上前三个季度
				quarterCalendar = Calendar.getInstance();
				quarterCalendar.set(Calendar.MONTH, 6);
				quarterCalendar.set(Calendar.DATE, 1);
				quarterCalendar.add(Calendar.DATE, -1);
				str = DateUtils.formatDate(quarterCalendar.getTime(), "yyyy-MM-dd");
				str = str.substring(0, str.length() - 5) + "04-01";
				break;
			case 3:// 本年到现在经过了三个季度，在加上前二个季度
				quarterCalendar = Calendar.getInstance();
				quarterCalendar.set(Calendar.MONTH, 9);
				quarterCalendar.set(Calendar.DATE, 1);
				quarterCalendar.add(Calendar.DATE, -1);
				str = DateUtils.formatDate(quarterCalendar.getTime(), "yyyy-MM-dd");
				str = str.substring(0, str.length() - 5) + "07-01";
				break;
			case 4:// 本年到现在经过了四个季度，在加上前一个季度
				quarterCalendar = Calendar.getInstance();
				str = DateUtils.formatDate(quarterCalendar.getTime(), "yyyy-MM-dd");
				str = str.substring(0, str.length() - 5) + "10-01";
				break;
		}
		return DateUtil.parse(str);
	}
    
    
}
