package com.joyintech.tams.valuation.accounadjustmanage.checkcommon.util;

import java.math.BigDecimal;

/**
 * 字符串转BigDecimal
 * 
 * @author 杨智
 * @version 1.0 2017年5月3日
 * @see BigDecimalConverterUtilForValuareconcy
 * @since 1.0
 */
public class BigDecimalConverterUtilForValuareconcy {

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
