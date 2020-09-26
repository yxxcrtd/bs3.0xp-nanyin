package com.joyintech.tams.valuation.common.utils;

import java.text.SimpleDateFormat;
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
	
}
