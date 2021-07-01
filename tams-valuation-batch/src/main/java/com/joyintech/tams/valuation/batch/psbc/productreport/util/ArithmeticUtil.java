/*
 * 文件名：AmountGroup.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：资管系统计算引擎
 * 修改人：何阳阳
 * 修改时间：2017年1月5日
 * 修改内容：新建
 */

package com.joyintech.tams.valuation.batch.psbc.productreport.util;

import com.joyintech.calculation.config.NumberEnum;
import com.joyintech.calculation.util.EngineContext;

import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * 用于高精确处理常用的数学运算
 * 
 * @author 何阳阳
 * @version 1.0 2017年1月5日
 * @see ArithmeticUtil
 * @since 1.0
 */
public final class ArithmeticUtil {
    
    /**
     * 提供精确的加法运算，如果某个参数为空，则不作为加算数据
     * 
     * @param datas 数据（数值）
     * @return 加算结果（默认为0）
     */
    public static BigDecimal doAdd(BigDecimal... datas) {
        return doAdd(EngineContext.getInstance().getConfig().defalutScale(), RoundingMode.HALF_UP, datas);
    }
    
    /**
     * 提供精确的加法运算，如果某个参数为空，则不作为加算数据
     * 
     * @param datas 数据（数值）
     * @return 加算结果（默认为0）
     */
    public static BigDecimal doAdd(int scale, RoundingMode roundingMode, BigDecimal... datas) {
        BigDecimal result = BigDecimal.ZERO;
        for (BigDecimal data : datas) {
            if (null != data) {
                result = result.add(data);
            }
        }
        return round(result, scale, roundingMode);
    }

    /**
     * 供精确的减法运算，参数为空的作为0处理 连减
     * 第一个参数为被减数
     *
     * @param datas 数据（数值）
     * @return 计算结果
     * @see
     * @since 1.0
     */
    public static BigDecimal doSub(BigDecimal... datas) {
        BigDecimal result = BigDecimal.ZERO;
        int dataIndex = 0;
        for (BigDecimal data : datas) {
            if (dataIndex == 0) {
                result = data;
            }
            else {
                result = subtract(result, data);
            }

            dataIndex++;
        }

        return result;
    }
    
    /**
     * 提供精确的减法运算，参数为空的作为0处理
     * 
     * @param data1 被减数（数值）
     * @param data2 减数（数值）
     * @return 加算结果（默认为0）
     */
    public static BigDecimal subtract(BigDecimal data1, BigDecimal data2) {
        BigDecimal result = BigDecimal.ZERO;
        if (null != data1) {
            result = result.add(data1);
        }
        if (null != data2) {
            result = result.subtract(data2);
        }
        return result;
    }

    /**
     * 提供精确的减法运算，参数为空的作为0处理
     * 
     * @param data1 被减数（数值）
     * @param data2 减数（数值）
     * @return 加算结果（默认为0）
     */
    public static BigDecimal subtract(BigDecimal data1, BigDecimal data2, int scale, RoundingMode roundingMode) {
        return round(subtract(data1, data2), scale, roundingMode);
    }

    /**
     * 数组连乘
     * @param values 运算数组
     * @return 连乘结果
     */
    public static BigDecimal multiply(BigDecimal... values) {
        if (values != null && values.length > 0) {
            BigDecimal result = BigDecimal.ONE;
            for(BigDecimal v : values) {
                if(v != null) {
                    // 乘法用全精度计算，否则精度过大时计算速度很慢
		    if (result == null) {
		    	result = BigDecimal.ONE;
		    }
                    result = round(result.multiply(round(v, RoundingMode.HALF_UP)), RoundingMode.HALF_UP);
                }
            }
            return result;
        }
        return null;
    }

    /**
     * 数组连乘
     * @param values 运算数组
     * @return 连乘结果
     */
    public static BigDecimal multiply(int scale, RoundingMode roundingMode, BigDecimal... values) {
        if (values != null && values.length > 0) {
            return round(multiply(values), scale, roundingMode);
        }
        return null;
    }
    
    /**
     * 提供精确的除法运算方法div
     * 
     * @param value1 被除数
     * @param value2 除数
     * @return 两个参数的商
     */
    public static BigDecimal divide(BigDecimal value1, BigDecimal value2) {
        return divide(value1, value2, EngineContext.getInstance().getConfig().getAllPrecision());
    }

    /**
     * 提供精确的除法运算方法div
     * 
     * @param value1 被除数
     * @param value2 除数
     * @param scale 精确范围
     * @return 两个参数的商
     */
    public static BigDecimal divide(BigDecimal value1, BigDecimal value2, int scale) {
        return divide(value1, value2, scale, RoundingMode.HALF_UP);
    }
    
    /**
     * 提供精确的除法运算方法div
     * 
     * @param value1 被除数
     * @param value2 除数
     * @param scale 精确范围
     * @return 两个参数的商
     */
    public static BigDecimal divide(BigDecimal value1, BigDecimal value2, int scale, RoundingMode roundingMode) {
        if (value1 == null || value2 == null || value2.doubleValue() == 0) {
            return BigDecimal.ZERO;
        }
        // 如果精确范围小于0，抛出异常信息
        if (scale < 0) {
            scale = 2;
        }
        return value1.divide(value2, scale, roundingMode);
    }

    /**
     * 提供精确的除法运算方法div
     * 
     * @param value1 被除数
     * @param value2 除数
     * @param scale 精确范围
     * @return 两个参数的商
     */
    public static BigDecimal divide(BigDecimal value1, int value2) {
        return divide(value1, new BigDecimal(value2), EngineContext.getInstance().getConfig().getAllPrecision());
    }
    
    /**
     * 提供精确的除法运算方法div
     * 
     * @param value1 被除数
     * @param value2 除数
     * @param scale 精确范围
     * @return 两个参数的商
     */
    public static BigDecimal divide(BigDecimal value1, int value2, int scale) {
        if (value1 == null) {
            return value1;
        }
        // 如果精确范围小于0，默认为12
        if (scale < 0) {
            scale = 12;
        }
        return divide(value1, new BigDecimal(value2), scale, RoundingMode.HALF_UP);
    }
    
    /**
     * 调整值精度
     * 
     * @param value 值
     * @param scale 精度
     * @param roundingMode 无设置默认四舍五入类型
     * @return double
     */
    public static BigDecimal round(BigDecimal value, RoundingMode roundingMode) {
        if (value == null) {
            return null;
        }
        if (roundingMode == null) {
            roundingMode = RoundingMode.HALF_UP;
        }
        return value.setScale(EngineContext.getInstance().getConfig().getAllPrecision(), roundingMode);
    }

    /**
     * 调整值精度
     * 
     * @param value 值
     * @param scale 精度
     * @param roundingMode 无设置默认四舍五入类型
     * @return double
     */
    public static BigDecimal round(BigDecimal value, int scale, RoundingMode roundingMode) {
        if (value == null) {
            return null;
        }
        if (roundingMode == null) {
            roundingMode = RoundingMode.HALF_UP;
        }
        return value.setScale(scale, roundingMode);
    }
    
    /**
     * 
     * BigDecimal比较，如果为空默认为0
     * @param val1
     * @param val2
     * @return
     * @see
     * @since 1.0
     */
    public static boolean equals(BigDecimal val1, BigDecimal val2) {
        BigDecimal v1 = val1 == null ? BigDecimal.ZERO : val1;
        BigDecimal v2 = val2 == null ? BigDecimal.ZERO : val2;
        if (v1.compareTo(v2) == 0) {
            return true;
        }
        return false;
    }
    
    /**
     * 
     * 根据类型调整精度
     * <功能详细描述>
     * @param value
     * @param numberEnum
     * @return
     * @see
     * @since 1.0
     */
    public static BigDecimal round(BigDecimal value, NumberEnum numberEnum) {
        return round(value, numberEnum.getCode(), RoundingMode.HALF_UP);
    }
}
