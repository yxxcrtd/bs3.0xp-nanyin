/*
 * 文件名：checkUtil.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangJin
 * 修改时间：2019年9月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.report.regulatoryreporting.common.util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.CharMatcher;
import com.joyintech.framework.util.StringUtil;

/**
 * 报表校验Util
 * 
 * @author zhangJin
 * @version 1.0 2019年9月25日
 * @see CheckUtil
 * @since 1.0
 */
public class CheckUtil {

    /**
     * 数字表达式:整数/小数/负数
     */
    private static Pattern NUMERIC_PATTERN = Pattern.compile("^(\\-|\\+)?\\d+(\\.\\d+)?$");

    /**
     * 判断是否是数字
     * 
     * @param str 需要判断的字符串
     * @return boolean
     * @see
     * @since 1.0
     */
    public static boolean isNumeric(String str) {
        if (str != null && str.length() > 0) {
            Matcher m = NUMERIC_PATTERN.matcher(str);
            if (m.find()) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 将数字字符串转为BigDecimal
     * @param str 目标字符串
     * @return 结果
     * @see
     * @since 1.0
     */
    public static BigDecimal convertStr2Num(String str) {
        if(isNumeric(str)) {
            return new BigDecimal(str);
        }
        CharMatcher.WHITESPACE.trimFrom(str);
        return null;
    }
    
    /**
     * 去除字符串的空格
     * @param str 目标字符串
     * @return 结果
     * @see
     * @since 1.0
     */
    public static String trim(String str) {
        if (StringUtil.isNotEmpty(str)) {
            String temp = str.trim();
            String result = CharMatcher.WHITESPACE.trimFrom(temp);
            return result;
        }
        return str;
    }

}
