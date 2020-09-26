/*
 * 文件名：BotCheckUtil.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：daiyue
 * 修改时间：2016年11月29日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.report.common.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.time.DateUtils;

import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.constant.DictCode;

/**
 * 
 * @author daiyue
 * @version 1.0 2016年11月29日
 * @see BotCheckUtil
 * @since 1.0
 */
public class BotCheckUtil {

    /**
     * 获取资源文件
     * @param key messageId
     * @param args 参数
     * @return message内容
     * @see
     * @since 1.0
     */
    public static String getMessage(String key, String... args) {
        return SpringContextUtil.getTextValue(key, args);
    }
    
    /**
     * 获取资源文件
     * @param key messageId
     * @return re内容
     * @see
     * @since 1.0
     */
    private static String[] getlabels(String... key) {
        String[] re = new String[key.length];
        for (int i = 0; i < key.length; i++ ) {
            re[i] = SpringContextUtil.getTextValue(key[i]);
        }
        return re;
    }
    
    /**
     * 不能为空
     * @param errorList 
     * @param value 传入字符
     * @param field 页面field 
     * @param labelResource msg参数
     * @return 结果
     * @see
     * @since 1.0
     */
    public static boolean notEmpty(List<ValidMessage> errorList, Object value, String field, String labelResource){
        if(value == null || "".equals(value)) {
            if (errorList != null) {
                errorList.add(new ValidMessage(field, 
                    getMessage("REPORT_COMMON_FILE_E_0001",getMessage(labelResource))));
            }
            return false;
        }
        return true;
    }
    
    /**
     * 必须不相等
     * @param eqValue 
     * @param errorList 
     * @param value 传入字符
     * @param field 页面field 
     * @param msgId msg参数
     * @param labelResource msg参数
     * @return 结果
     * @see
     * @since 1.0
     */
    public static boolean mustNotEquals(Object eqValue, List<ValidMessage> errorList, 
                                        Object value, String field, String msgId, String... labelResource){
        if(eqValue != null && StringUtil.isNotEmpty(eqValue)) {
            if(value == eqValue || eqValue.equals(value)) {
                if (errorList != null) {
                    errorList.add(new ValidMessage(field, 
                        getMessage(msgId, getlabels(labelResource))));
                }
                return false;
            }
        }
        return true;
    }

    /**
     * 必须相等
     * @param eqValue 
     * @param errorList 
     * @param value 传入字符
     * @param field 页面field 
     * @param msgId msg参数
     * @param labelResource msg参数
     * @return 结果
     * @see
     * @since 1.0
     */
    public static boolean mustEquals(Object eqValue, List<ValidMessage> errorList, Object value, String field, String msgId, String... labelResource){
        if(eqValue != null && StringUtil.isNotEmpty(eqValue)) {
            if(value != eqValue && !eqValue.equals(value)) {
                if (errorList != null) {
                    errorList.add(new ValidMessage(field, 
                        getMessage(msgId, getlabels(labelResource))));
                }
                return false;
            }
        }
        return true;
    }
    /**
     * 是否固定长度
     * @param length 长度
     * @param errorList 
     * @param value 传入字符
     * @param field 页面field 
     * @param labelResource msg参数
     * @return 结果 
     * @see
     * @since 1.0
     */
    public static boolean fixLength(int length, List<ValidMessage> errorList, String value, String field,
                                 String labelResource) {
        if(!StringUtil.isEmpty(value) && value.length() != length) {
            if (errorList != null) {
                errorList.add(new ValidMessage(field, 
                    getMessage("REPORT_COMMON_FILE_E_0004",getMessage(labelResource))));
            }
            return false;
        }
        return true;
    }
    
    /**
     * 最大长度
     * @param maxlength 长度max
     * @param errorList 
     * @param value 传入字符
     * @param field 页面field 
     * @param labelResource msg参数
     * @return 结果 
     * @see
     * @since 1.0
     */
    public static boolean maxLength(int maxlength, List<ValidMessage> errorList, String value, String field,
                                 String labelResource) {
        if(!StringUtil.isEmpty(value) && (value.length() > maxlength)) {
            if (errorList != null) {
                errorList.add(new ValidMessage(field, 
                    getMessage("REPORT_COMMON_FILE_E_0005",getMessage(labelResource))));
            }
            return false;
        }
        return true;
    }
    
    /**
     * 最小长度
     * @param minlength 长度max
     * @param errorList 
     * @param value 传入字符
     * @param field 页面field 
     * @param labelResource msg参数
     * @return 结果 
     * @see
     * @since 1.0
     */
    public static boolean minLength(int minlength, List<ValidMessage> errorList, String value, String field,
                                 String labelResource) {
        if(!StringUtil.isEmpty(value) && (value.length() < minlength)) {
            if (errorList != null) {
                errorList.add(new ValidMessage(field, 
                    getMessage("REPORT_COMMON_FILE_E_0006",getMessage(labelResource))));
            }
            return false;
        }
        return true;
    }
    
    /**
     * 参数列表check(单选)
     * @param code 参数
     * @param errorList 
     * @param value 传入字符
     * @param field 页面field 
     * @param labelResource msg参数
     * @return 结果 
     * @see
     * @since 1.0
     */
    public static boolean codeListSimpleCheck(DictCode code, List<ValidMessage> errorList, String value, String field,
                                 String labelResource) {
        if(!StringUtil.isEmpty(value)) {
            if (SysDataUtil.getNameFromDataDict(code, value) == null ) {
                if (errorList != null) {
                    errorList.add(new ValidMessage(field, 
                        getMessage("REPORT_COMMON_FILE_E_0007",getMessage(labelResource), value)));
                }
                return false;
            }
        }
        return true;
    }
    
    /**
     * 参数列表check(是否包含)
     * @param codes 参数
     * @param errorList 
     * @param value 传入字符
     * @param field 页面field 
     * @param labelResource msg参数
     * @return 结果 
     * @see
     * @since 1.0
     */
    public static boolean codeListContainsCheck(String codes, List<ValidMessage> errorList, String value, String field,
                                 String labelResource) {
        if(!StringUtil.isEmpty(value)) {
            if (!codes.contains(value)) {
                if (errorList != null) {
                    errorList.add(new ValidMessage(field, 
                        getMessage("REPORT_COMMON_FILE_E_0008",getMessage(labelResource))));
                }
                return false;
            }
        }
        return true;
    }
    
    /**
     * 参数列表check(多选)
     * @param code 参数
     * @param errorList 
     * @param value 传入字符
     * @param field 页面field 
     * @param labelResource msg参数
     * @return 结果 
     * @see
     * @since 1.0
     */
    public static String codeListMultipleCheck(DictCode code, List<ValidMessage> errorList, String value, String field,
                                 String labelResource) {
        String returnValue = "";
        if(!StringUtil.isEmpty(value)) {
            String[] values = value.split("，");
            for (int i = 0; i < values.length; i++ ) {
                for (int j = i + 1; j < values.length; j++ ) {
                    if (values[i].equals(values[j])) {
                        // 有重复
                        if (errorList != null) {
                            errorList.add(new ValidMessage(field, 
                                getMessage("REPORT_COMMON_FILE_E_0009",getMessage(labelResource), value)));
                        }
                        return value;
                    }
                }
                String valuet = values[i].split(" ")[0];
                if (SysDataUtil.getNameFromDataDict(code, valuet) == null ) {
                    if (errorList != null) {
                        errorList.add(new ValidMessage(field, 
                            getMessage("REPORT_COMMON_FILE_E_0010",getMessage(labelResource), value)));
                    }
                    return value;
                }
                else {
                    if(i==0) {
                        returnValue = valuet;
                    }
                    else {
                        returnValue = returnValue + "," + valuet;
                    }
                }
            }
        }
        return returnValue;
    }
    
    /**
     * 正则表达式
     * @param regstr 表达式字符
     * @param errorList 
     * @param value 传入字符
     * @param field 页面field 
     * @param messageId msg参数
     * @param labelResource msg参数
     * @return 结果 
     * @see
     * @since 1.0
     */
    public static boolean regMatcher(String regstr, List<ValidMessage> errorList, String value, String field, String messageId,
                                 String... labelResource) {
        if(!StringUtil.isEmpty(value)) {
            Pattern pattern = Pattern.compile(regstr);
            Matcher matcher = pattern.matcher(value);
            if (!matcher.matches()) {
                if (errorList != null) {
                    errorList.add(new ValidMessage(field, 
                        getMessage(messageId, getlabels(labelResource))));
                }
                return false;
            }
        }
        return true;
    }
    
    /**
     * 数字格式check
     * @param intlength 整数位长度
     * @param declength 小数位长度  
     * @param errorList 
     * @param value 传入字符
     * @param field 页面field 
     * @param labelResource msg参数
     * @return 结果 
     * @see
     * @since 1.0
     */
    public static boolean numberFormatCheck(int intlength, int declength, 
                                            List<ValidMessage> errorList, String value, String field,
                                 String labelResource) {
        if(StringUtil.isEmpty(value)) {
            return false;
        }
        if (value != null) {
            int iLength = intlength - declength - 1;
            String format = null;
            if(declength > 0) {
                format = "[0]" + "|" + "[-]?[1-9]\\d{0," + iLength + "}" 
                    + "|" + "[-]?[0-9]"
                    + "|" + "[-]?[1-9]\\d{0," + iLength + "}" + "[\\.]\\d{1," + declength + "}"
                    + "|" + "[-]?0[\\.]\\d{1," + declength + "}";
            } 
            else {
                format = "[-]?[1-9]\\d{0," + iLength + "}"
                    + "|" + "[-]?[0-9]";
            }
            Pattern pattern = Pattern.compile(format);
            Matcher matcher = pattern.matcher(value);
            if (!matcher.matches()) {
                if (errorList != null) {
                    errorList.add(new ValidMessage(field,
                        getMessage("REPORT_COMMON_FILE_E_0012", getMessage(labelResource))));
                }
                return false;
            }
        }
        return true;
    }
    
    /**
     * 日期格式check
     * @param formatStr 格式
     * @param errorList 
     * @param value 传入字符
     * @param field 页面field 
     * @param labelResource msg参数
     * @return 结果 
     * @see
     * @since 1.0
     */
    public static Date dateFormatCheck(String formatStr, 
                                            List<ValidMessage> errorList, String value, String field,
                                 String labelResource) {
        if(value != null) {
            try {
                return DateUtils.parseDate(value, formatStr);
            }
            catch (ParseException e) {
                if (errorList != null) {
                    errorList.add(new ValidMessage(field, 
                        getMessage("REPORT_COMMON_FILE_E_0013",getMessage(labelResource))));
                }
                return null;
            }
        }
        return null;
    }
    
    /**
     * 数字大小check
     * @param compareValue 比较参数
     * @param errorList 
     * @param value 传入字符
     * @param field 页面field 
     * @param labelResource msg参数
     * @return 结果 
     * @see
     * @since 1.0
     */
    public static boolean numberLargeThanCheck(BigDecimal compareValue,
                                            List<ValidMessage> errorList, BigDecimal value, String field,
                                 String... labelResource) {
        if(value != null && compareValue != null) {
            if (value.compareTo(compareValue) < 0) {
                if (errorList != null) {
                    errorList.add(new ValidMessage(field, 
                        getMessage("REPORT_COMMON_FILE_E_0014",getlabels(labelResource))));
                }
                return false;
            }
        }
        return true;
    }
    
    /**
     * 数字大小betcheck
     * @param minValue 比较参数
     * @param maxValue 比较参数
     * @param errorList 
     * @param value 传入字符
     * @param field 页面field 
     * @param labelResource msgId参数
     * @param labelResource msg参数
     * @return 结果 
     * @see
     * @since 1.0
     */
    public static boolean numberBetweenCheck(BigDecimal minValue, BigDecimal maxValue,
                                            List<ValidMessage> errorList, BigDecimal value, String field, String msgId,
                                 String... labelResource) {
        if(minValue != null && maxValue != null && value != null) {
            if (value.compareTo(minValue) < 0 || value.compareTo(maxValue) > 0) {
                if (errorList != null) {
                    errorList.add(new ValidMessage(field, 
                        getMessage(msgId, getlabels(labelResource))));
                }
                return false;
            }
        }
        return true;
    }
    
    /**
     * 日期大小check 比较参数 <=
     * @param compareValue 比较参数
     * @param errorList 
     * @param value 传入字符
     * @param field 页面field 
     * @param labelResource msg参数
     * @return 结果 
     * @see
     * @since 1.0
     */
    public static boolean dateLargeThanCheck(Date compareValue,
                                            List<ValidMessage> errorList, Date value, String field,
                                 String... labelResource) {
        if(value != null && compareValue != null) {
            if (value.compareTo(compareValue) < 0) {
                if (errorList != null) {
                    errorList.add(new ValidMessage(field, 
                        getMessage("REPORT_COMMON_FILE_E_0014",getlabels(labelResource))));
                }
                return false;
            }
        }
        return true;
    }
    
//    public static void main(String[] args) {
////        int intlength = 3,declength = 2;
////        int iLength = intlength - declength - 1;
////        String format = null;
////        if(declength > 0) {
////            format = "[0]" + "|" + "[-]?[1-9]\\d{0," + iLength + "}" 
////                        + "|" + "[-]?[1-9]\\d{0," + iLength + "}" + "[\\.]\\d{1," + declength + "}"
////                        + "|" + "[-]?0[\\.]\\d{1," + declength + "}";
////        } 
////        else {
////            format = "[-]?[1-9]\\d{0," + iLength + "}";
////        }
////        Pattern pattern = Pattern.compile(format);
////        Matcher matcher = pattern.matcher("0.1");
////        System.out.println(matcher.matches());
//        System.out.println(codeListMultipleCheck(DictCode.业务分类, null, "01 利率,02 汇率", null, null));
//    }
}
