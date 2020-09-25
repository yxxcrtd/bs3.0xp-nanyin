/*
 * 文件名：BigDecimalConverterUtil.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：杨智
 * 修改时间：2017年5月3日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.interf.interf.common.util;

import java.math.BigDecimal;

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
        catch (Exception e) {
        }
        return ret;
    }
}
