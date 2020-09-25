package com.jixu.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
	private static ThreadLocal<DateFormat> yyyymmdd = new ThreadLocal<DateFormat>() {
        @Override
        protected synchronized DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };
    
  	private static ThreadLocal<DateFormat> yyyy_mm_dd = new ThreadLocal<DateFormat>() {
          @Override
          protected synchronized DateFormat initialValue() {
              return new SimpleDateFormat("yyyy-MM-dd");
          }
  	};
  	
  	private static ThreadLocal<DateFormat> yyyymmddChina = new ThreadLocal<DateFormat>() {
        @Override
        protected synchronized DateFormat initialValue() {
            return new SimpleDateFormat("yyyy年MM月dd日");
        }
	};
  	
  	private static ThreadLocal<DateFormat> mm_dd_yy2 = new ThreadLocal<DateFormat>() {
        @Override
        protected synchronized DateFormat initialValue() {
            return new SimpleDateFormat("MM/dd/yy");
        }
    };
    
    private static ThreadLocal<DateFormat> yyyy_mm_dd2 = new ThreadLocal<DateFormat>() {
        @Override
        protected synchronized DateFormat initialValue() {
            return new SimpleDateFormat("yyyy/MM/dd");
        }
    };

    private static ThreadLocal<DateFormat> hhmmss = new ThreadLocal<DateFormat>() {
        @Override
        protected synchronized DateFormat initialValue() {
            return new SimpleDateFormat("HH:mm:ss");
        }
    };
	
    private static ThreadLocal<DateFormat> yyyymmddhhmmss = new ThreadLocal<DateFormat>() {
        @Override
        protected synchronized DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
	
    private static ThreadLocal<DateFormat> yyyymmddhhmmss2 = new ThreadLocal<DateFormat>() {
        @Override
        protected synchronized DateFormat initialValue() {
            return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        }
    };
    
    private static ThreadLocal<DateFormat> yyyymmddhhmmss3 = new ThreadLocal<DateFormat>() {
        @Override
        protected synchronized DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        }
    };
	
    
    /**
     * yyyy-mm-dd hh:mm:ss
     * @param time
     * @return
     */
	public static Date convertStr2Time(String time){
		try {
			return yyyymmddhhmmss.get().parse(time);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * yyyy/mm/dd hh:mm:ss
	 * @param time
	 * @return
	 */
	public static Date convertStr2Time2(String time){
		try {
			return yyyymmddhhmmss2.get().parse(time);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * yyyymmdd hh:mm:ss
	 * @param time
	 * @return
	 */
	public static Date convertStr2Time3(String time){
		try {
			return yyyymmddhhmmss3.get().parse(time);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Date convertStr2DateHHMMSS(String date){
		try {
			return hhmmss.get().parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Date convertStr2DateYYYY_MM_DD2(String date){
		try {
			return yyyy_mm_dd2.get().parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static String convertDate2StrHHMMSS(Date date){
		return hhmmss.get().format(date);
	}
	
	public static String convertD2YYYYMMDD(Date date) {
		return yyyymmdd.get().format(date);
	}
	
	public static String convertD2YYYY_MM_DD(Date date) {
		return yyyy_mm_dd.get().format(date);
	}
	
	public static String convertD2YYYYMMDDChina(Date date) {
		return yyyymmddChina.get().format(date);
	}
	
	public static String convertD2YYYY_MM_DDHHMMSS(Date date){
		return yyyymmddhhmmss.get().format(date);
	}

	public static Date convertStr2DateMM_dd_yy2(String date){
		try {
			return mm_dd_yy2.get().parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Date convertStr2Date(String date) {
		try {
			return yyyymmdd.get().parse(date);
		} catch (ParseException pe) {
			return null;
		}
	}
	
	/**
	 * yyyy-mm-dd
	 * @param date
	 * @return
	 */
	public static Date convertStr2Date2(String date) {
		try {
			return yyyy_mm_dd.get().parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Date convertStr2DateChina(String date) {
		try {
			return yyyymmddChina.get().parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 转换yyyy-mm-dd,yyyymmdd格式的日期
	 * @param date
	 * @return
	 */
	public static Date convertStr2DateAll(String date){
		try{
			return yyyymmdd.get().parse(date.replaceAll("-", ""));
		}catch (ParseException e) {
			return null;
		}
	}

	public static String getPriorDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, -1);
		return yyyymmdd.get().format(c.getTime());
	}

	public static String getNextDay(String date) {
		Calendar c = Calendar.getInstance();
		if (date.length() != 8) {
			return "20000101";
		}
		c.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date
				.substring(4, 6)) - 1, Integer.parseInt(date.substring(6, 8)));
		c.add(Calendar.DAY_OF_MONTH, 1);
		return yyyymmdd.get().format(c.getTime());
	}
	
	public static String getPriorDay(String date) { // yyyyMMdd
		Calendar c = Calendar.getInstance();
		if (date.length() != 8) {
			return "20000101";
		}
		c.set(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date
				.substring(4, 6)) - 1, Integer.parseInt(date.substring(6, 8)));
		c.add(Calendar.DAY_OF_MONTH, -1);
		return yyyymmdd.get().format(c.getTime());
	}
	
	public static Date addMillisecond(Date begtinTime, int addMillisecond){
		Date afterDate = new Date(begtinTime.getTime() + addMillisecond);
		return afterDate;
	}
	
	public static Date getNextDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(c.DATE, 1);
		return c.getTime();
	}
	
	public static Date getNextyear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, 1);
		return c.getTime();
	}
	
	public static java.sql.Date getNextSQLDate(java.sql.Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(c.DATE, 1);
		return new java.sql.Date(c.getTime().getTime());
	}
	
	public static java.sql.Date getPriorSQLDate(java.sql.Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(c.DATE, -1);
		return new java.sql.Date(c.getTime().getTime());
	}
	
	public static Date getLastYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(c.YEAR, -1);
		return c.getTime();
	}
	
	public static Date getNextNDays(Date date, int nextDays) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(c.DATE, nextDays);
		return c.getTime();
	}
	
	public static Date getNextNMonths(Date date, int nextNMonths) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(c.MONTH, nextNMonths);
		return c.getTime();
	}
	
	public static Date getLastDay(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(c.DATE, -1);
		return c.getTime();
	}
	
	public static java.sql.Date getLastSqlDay(java.sql.Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(c.DATE, -1);
		return new java.sql.Date(c.getTime().getTime());
	}
	
	public static Date getNextBusinessDay(Date date, List<Date> holidayList){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(c.DATE, 1);
		while(true){
			if(c.get(Calendar.DAY_OF_WEEK) != 1 && c.get(Calendar.DAY_OF_WEEK) != 7){
				boolean isHoliday = false;
				for(Date holiday:holidayList){
					if(c.getTime().compareTo(holiday)==0)
						isHoliday = true;
				}
				if(!isHoliday)
					break;
			}
			c.add(c.DATE, 1);
		}
		return c.getTime();
	}
	
	public static Date getNextNBusinessDays(Date date, int nextDays, List<Date> holidayList){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int cycleDays=Math.abs(nextDays);
		for(int k=0;k<cycleDays;k++){
			if(nextDays>=0){
				c.add(c.DATE, 1);
			}else{
				c.add(c.DATE, -1);
			}
			while(true){
				if(c.get(Calendar.DAY_OF_WEEK) != 1 && c.get(Calendar.DAY_OF_WEEK) != 7){
					boolean isHoliday = false;
					for(Date holiday:holidayList){
						if(c.getTime().compareTo(holiday)==0)
							isHoliday = true;
					}
					if(!isHoliday)
						break;
				}
				if(nextDays>=0){
					c.add(c.DATE, 1);
				}else{
					c.add(c.DATE, -1);
				}
			}
		}
		return c.getTime();
	}
	
	/**
	 * 获取上一个工作日
	 * @param date
	 * @return
	 */
	public static Date getLastBusinessDay(Date date, List<Date> holidayList){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(c.DATE, -1);
		while(true){
			if(c.get(Calendar.DAY_OF_WEEK) != 1 && c.get(Calendar.DAY_OF_WEEK) != 7){
				boolean isHoliday = false;
				for(Date holiday:holidayList){
					if(c.getTime().compareTo(holiday)==0)
						isHoliday = true;
				}
				if(!isHoliday)
					break;
			}
			c.add(c.DATE, -1);
		}
		return c.getTime();
	}
	
	/**
	 * 获取上一个工作日
	 * @param date
	 * @return
	 */
	public static java.sql.Date getLastBusinessSqlDate(java.sql.Date date, List<java.sql.Date> holidayList){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(c.DATE, -1);
		while(true){
			if(c.get(Calendar.DAY_OF_WEEK) != 1 && c.get(Calendar.DAY_OF_WEEK) != 7){
				boolean isHoliday = false;
				for(Date holiday:holidayList){
					if(c.getTime().compareTo(holiday)==0)
						isHoliday = true;
				}
				if(!isHoliday)
					break;
			}
			c.add(c.DATE, -1);
		}
		return new java.sql.Date(c.getTime().getTime());
	}
	
	/**
	 * 获取几秒钟之后的时间
	 * @param datetime
	 * @param seconds
	 * @return
	 */
	public static Date getNextNSeconds(Date datetime, int seconds){
		Calendar c = Calendar.getInstance();
		c.setTime(datetime);
		c.add(c.SECOND, seconds);
		return c.getTime();
	}
	
	public static Date getCurrectHHMMSS(){
		try {
			return hhmmss.get().parse(hhmmss.get().format(System.currentTimeMillis()));
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 把时间转换为 时:分:秒
	 * @param dateTime
	 * @return
	 */
	public static Date convertHHMMSS(Date dateTime){
		try {
			return hhmmss.get().parse(hhmmss.get().format(dateTime.getTime()));
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 把时间转换为年月日
	 * @param dateTime
	 * @return
	 */
	public static Date convert2YYYYMMDD(Date dateTime){
		try {
			return yyyymmdd.get().parse(yyyymmdd.get().format(dateTime.getTime()));
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 返回值1,date1>date2
	 * 返回值0,date1=date2
	 * 返回值-1,date1<date2
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compareTime(Date date1, Date date2){
		return date1.compareTo(date2);
	}
	
	/**
	 * 查看现在是否为工作日
	 * @param date
	 * @return
	 */
	public static boolean isBusinessDay(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if(c.get(Calendar.DAY_OF_WEEK) != 1 && c.get(Calendar.DAY_OF_WEEK) != 7)
			return true;
		else
			return false;
	}
	
	public static boolean isBusinessDay(Date date, List<Date> holidayList){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if(c.get(Calendar.DAY_OF_WEEK) != 1 && c.get(Calendar.DAY_OF_WEEK) != 7){
			for(Date holiday:holidayList){
				if(c.getTime().compareTo(holiday)==0){
					return false;
				}
			}
		}else{
			return false;
		}
		return true;
	}
	
	public static boolean isBusinessDaySQLDate(Date date, List<java.sql.Date> holidayList){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if(c.get(Calendar.DAY_OF_WEEK) != 1 && c.get(Calendar.DAY_OF_WEEK) != 7){
			for(Date holiday:holidayList){
				if(c.getTime().compareTo(holiday)==0){
					return false;
				}
			}
		}else{
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param startDate	开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public static int getDiffDays(Date startDate, Date endDate){
		long diff=startDate.getTime()-endDate.getTime();
		long diffDays=diff/86400000;
		return Math.abs((int)diffDays);
	}
	
	/**
	 * 查看现在是否为工作日
	 * @param date
	 * @return
	 */
	public static int getYear(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}
	
	/**
	 * 查看现在是否为工作日
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH);
	}
	
	public static String convertStr2Str2(String date){
		String year=date.substring(0,4);
		String month=date.substring(4,6);
		String day=date.substring(6,8);
		return year+"-"+month+"-"+day;
	}
	
	public static Date defaultDate(){
		return convertStr2Date("20000101");
	}
	
	public static java.sql.Date ConvertUtil2Sql(java.util.Date date){
		java.sql.Date sqldate=new java.sql.Date(date.getTime());
		return sqldate;
	}
	
	public static java.sql.Date minDate(java.sql.Date date1, java.sql.Date date2){
		if(date1.compareTo(date2)==-1){
			return date1;
		}else{
			return date2;
		}
	}
	
	public static java.sql.Date maxDate(java.sql.Date date1, java.sql.Date date2){
		if(date1.compareTo(date2)==1){
			return date1;
		}else{
			return date2;
		}
	}

	public static List<Date> getBetweenDates(String startDate, String endDate) {
		Date start=new Date(DateUtil.convertStr2Date2(startDate).getTime());
		Date end=new Date(DateUtil.convertStr2Date2(endDate).getTime());
		
		List<Date> result = new ArrayList<Date>();
	    Calendar tempStart = Calendar.getInstance();
	    tempStart.setTime(start);
	    tempStart.add(Calendar.DAY_OF_YEAR, 1);
	    
	    Calendar tempEnd = Calendar.getInstance();
	    tempEnd.setTime(end);
	    while (tempStart.before(tempEnd)) {
	        result.add(tempStart.getTime());
	        tempStart.add(Calendar.DAY_OF_YEAR, 1);
	    }
	    return result;
	}
	
	/**
	 * 判断是否是一个月最后一天
	 * @param date
	 * @return
	 */
	public static boolean isLastDayOfMonth(java.sql.Date date){
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE,(c.get(Calendar.DATE)+1));
		if(c.get(Calendar.DAY_OF_MONTH)==1){
			return true;
		}
		return false;
	}
	
	public static Date getInitDate(){
		return convertStr2Date("20000101");
	}
}
