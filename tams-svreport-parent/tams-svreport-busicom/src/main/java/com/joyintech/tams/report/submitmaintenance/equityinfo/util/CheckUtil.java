/*
 * 文件名：EquityInfoCheckUtil.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：Administrator
 * 修改时间：2017年9月21日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.submitmaintenance.equityinfo.util;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;


/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author Administrator
 * @version 1.0 2017年9月21日
 * @see CheckUtil
 * @since 1.0
 */
public class CheckUtil {
    /**
     * 日志
     */
    private static Logger LOGGER = LoggerFactory.getLogger(CheckUtil.class);

    /**
     * 身份证号校验
     * @param value
     * @return
     */
    public static Map<String,String> idCardValidator(String value){
        Map<String,String> result = new HashMap<>();
        result.put("resultCode","true");
        if (StringUtil.isEmpty(value)) {
            return processMessage("身份证号不能为空");
        } else {
            String[] valCodeArr = new String[]{"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
            String[] wi = new String[]{"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"};
            String ai = "";
            if (!StringUtils.isEmpty(value) && (value.length() == 15 || value.length() == 18)) {
                if (value.length() == 18) {
                    ai = value.substring(0, 17);
                } else if (value.length() == 15) {
                    ai = value.substring(0, 6) + "19" + value.substring(6, 15);
                }

                if (!StringUtils.isNumeric(ai)) {
                    LOGGER.debug("身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。【{}】", value);
                    return processMessage("身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字!");
                } else {
                    String strYear = ai.substring(6, 10);
                    String strMonth = ai.substring(10, 12);
                    String strDay = ai.substring(12, 14);
                    if (!DateUtil.isDateStr(strYear + "-" + strMonth + "-" + strDay)) {
                        LOGGER.debug("身份证生日无效。【{}】", value);
                        return processMessage("身份证生日无效!");
                    } else {
                        GregorianCalendar gc = new GregorianCalendar();

                        try {
                            if (gc.get(1) - Integer.parseInt(strYear) > 150 || gc.getTime().getTime() - DateUtil.parse(strYear + "-" + strMonth + "-" + strDay).getTime() < 0L) {
                                LOGGER.debug("身份证生日不在有效范围。【{}】", value);
                                return processMessage("身份证生日不在有效范围");
                            }
                        } catch (NumberFormatException var14) {
                            LOGGER.error(var14.getMessage());
                        }

                        if (Integer.parseInt(strMonth) <= 12 && Integer.parseInt(strMonth) != 0) {
                            if (Integer.parseInt(strDay) <= 31 && Integer.parseInt(strDay) != 0) {
                                Hashtable<String, String> h = getAreaCode();
                                if (h.get(ai.substring(0, 2)) == null) {
                                    LOGGER.debug("身份证地区编码错误。【{}】", value);
                                    return processMessage("身份证地区编码错误");
                                } else {
                                    int totalmulAiWi = 0;

                                    int modValue;
                                    for(modValue = 0; modValue < 17; ++modValue) {
                                        totalmulAiWi += Integer.parseInt(String.valueOf(ai.charAt(modValue))) * Integer.parseInt(wi[modValue]);
                                    }

                                    modValue = totalmulAiWi % 11;
                                    String strVerifyCode = valCodeArr[modValue];
                                    ai = ai + strVerifyCode;
                                    if (value.length() == 18 && !ai.equals(value)) {
                                        LOGGER.debug("身份证无效，不是合法的身份证号码。", value);
                                        return processMessage("身份证无效，不是合法的身份证号码");
                                    } else {
                                        return result;
                                    }
                                }
                            } else {
                                LOGGER.debug("身份证日期无效。【{}】", value);
                                return processMessage("身份证日期无效");
                            }
                        } else {
                            LOGGER.debug("身份证月份无效。【{}】", value);
                            return processMessage("身份证月份无效");
                        }
                    }
                }
            } else {
                LOGGER.debug("身份证号码长度应该为15位或18位。【{}】", value);
                return processMessage("身份证号码长度应该为15位或18位");
            }
        }
    }

    private static Map<String,String> processMessage(String message) {
        Map<String,String> result = new HashMap<>();
        result.put("resultCode","false");
        result.put("resultMessage",message);
        return result;
    }

    private static Hashtable<String, String> getAreaCode() {
        Hashtable<String, String> hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }
}