/*
 * 文件名：BigDecimalConverterUtil.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：杨智
 * 修改时间：2017年5月3日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.interf.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 字符串转BigDecimal
 * 
 * @author 杨智
 * @version 1.0 2017年5月3日
 * @see BigDecimalConverterUtil
 * @since 1.0
 */
public class BigDecimalConverterUtil {

	/**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);
    /**
     * 字符串转BigDecimal，对于非法输入返回0
     * 
     * @param str 源串
     * @return 数值
     * @see
     * @since 1.0
     */
    public static BigDecimal stringToBigDecimal(String str) {
        BigDecimal ret = BigDecimal.ZERO;
        try {
            ret = new BigDecimal(str);
        }
        catch (NumberFormatException e) {
        	logger.error(e.getMessage(),e);
        }
        return ret;
    }
    
    /**
     * 功能:数字格式化带百分比
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月25日
     * @param number 要转化的数字
     * @param scale 保留小数位数
     * @return 
     * String
     */
    public static String bigDecimalToStringWithPercentAndScale(BigDecimal number,int scale ){
        String pattern = "0";
        if (scale > 0){
            pattern += ".";
        } else if (scale < 0){
            logger.error("保留小数位数为负！");
            return "";
        }
        for (int i = 0; i < scale ; i++){
            pattern += "0";
        }
        pattern += "%";
        DecimalFormat df = new DecimalFormat(pattern);  
        return df.format(number);
    }
    
    /**
     * 功能:数字格式化带百分比默认不带小数
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月25日
     * @param number 要转化的数字
     * @return 
     * String
     */
    public static String bigDecimalToStringWithPercent(BigDecimal number){ 
        return bigDecimalToStringWithPercentAndScale(number,0);
    }
}
