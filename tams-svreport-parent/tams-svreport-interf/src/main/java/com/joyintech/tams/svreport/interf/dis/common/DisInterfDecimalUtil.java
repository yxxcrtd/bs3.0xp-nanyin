/*
 * 文件名：DisInterfDecimalUtil.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：信息披露系统V1.0
 * 修改人：ZhangYangFei
 * 修改时间：2019年5月10日
 * 修改内容：新建
 * 系统名称：信息披露系统V1.0
 */
 
package com.joyintech.tams.svreport.interf.dis.common;

import java.math.BigDecimal;

import com.joyintech.framework.util.StringUtil;

/**
 * 信批接口数字工具类
 * 
 * @author ZhangYangFei
 * @version 1.0 2019年5月10日
 * @see DisInterfDecimalUtil
 * @since 1.0
 */
public class DisInterfDecimalUtil {
    
    /**
     * 字符串数据转换为BigDecimal
     * 
     * @param bigStr
     * @return
     * @see
     * @since 1.0
     */
    public static BigDecimal str2BigDecimal(String bigStr) {
        return StringUtil.isNotEmpty(bigStr) ? new BigDecimal(bigStr) : BigDecimal.ZERO;
    }
    
    public static Long str2Long(String bigStr) {
        return StringUtil.isNotEmpty(bigStr) ? Long.parseLong(bigStr) : new Long(0);
    }
}
