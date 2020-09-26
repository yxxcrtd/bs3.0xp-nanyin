package com.joyintech.tams.svreport.chb.datacheck.totalcheck.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;


/**
 * 功能:中债总体核查批处理工具类
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年4月19日
 * @修改记录：
 */
public class ChbTotalCheckCBUtils {

    /**
     * 功能:Object转BigDecimal类型
     * 描述:<功能详细描述>
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月19日
     * @param value 要转的object类型
     * @return 
     * BigDecimal 转成的BigDecimal类型数据
     */
    public static BigDecimal getBigDecimal(Object value) {
        BigDecimal ret = null;
        if (value != null) {
            if (value instanceof BigDecimal) {
                ret = (BigDecimal) value;
            } else if (value instanceof String) {
                ret = new BigDecimal((String) value);
            } else if (value instanceof BigInteger) {
                ret = new BigDecimal((BigInteger) value);
            } else if (value instanceof Number) {
                ret =  BigDecimal.valueOf(((Number) value).doubleValue());
            } else {
                throw new ClassCastException("Not possible to coerce [" + value + "] from class " + value.getClass() + " into a BigDecimal.");
            }
        }
        return ret;
    }
    
    /**
     * 功能：获取上一个月的最后一天
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月22日
     * @param date
     * @return 
     * Date
     */
    public static Date getLastDayOfLayMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 获取上月的最后一天
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        Date endDate = calendar.getTime();
        return endDate;
    }
    
    /**
     * 功能：获取当前月的最后一天
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月22日
     * @param date
     * @return 
     * Date
     */
    public static Date getLastDayOfMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDate = calendar.getTime();
        return endDate;
    }
    /**
     * 功能:获取上一个月的第一天
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月22日
     * @param date
     * @return 
     * Date
     */
    public static Date getFirstDayOfLayMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 获取上月的第一天
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();
        return startDate;
    }
}
