/*
 * 文件名：UnitEnum.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年7月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.order.exchange.common.constant;

import java.math.BigDecimal;

import com.joyintech.background.component.common.pack.common.util.ArithmeticUtil;
import com.joyintech.background.component.common.pack.common.util.StringUtil;

/**
 * 投资指令组件-单位枚举
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月11日
 * @see UnitEnum
 * @since 1.0
 */
public enum UnitEnum {

    /**
     * 单位_股
     */
    股(
        "股"
    ),
    /**
     * 单位_份
     */
    份(
        "份"
    ),
    /**
     * 单位_张
     */
    张(
        "张"
    ),
    /**
     * 单位_手
     */
    手(
        "手"
    ),
    /**
     * 单位_元
     */
    元(
        "元"
    ),
    /**
     * 单位_万股
     */
    万股(
        10000, "万股"
    ),
    /**
     * 单位_万份
     */
    万份(
        10000, "万份"
    ),
    /**
     * 单位_万张
     */
    万张(
        10000, "万张"
    ),
    /**
     * 单位_万元
     */
    万元(
        10000, "万元"
    ),;

    /**
     * 系数
     */
    private int ratioVal;

    /**
     * 单位名称
     */
    private String unitName;

    private UnitEnum(int ratioVal, String unitName) {
        this.ratioVal = ratioVal;
        this.unitName = unitName;
    }

    private UnitEnum(String unitName) {
        this.ratioVal = 1;
        this.unitName = unitName;
    }

    /**
     * 获取 单位名称
     * 
     * @return 单位名称.
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * 数据转化为单位显示方式
     * 
     * @param val 数据
     * @param scale 小数位
     * @return 带单位的字符串
     */
    public String showMeWithUnit(BigDecimal val, int scale) {
        if (val == null) {
            return unitName;
        }
        BigDecimal temp = ArithmeticUtil.divide(val, BigDecimal.valueOf(ratioVal), scale);
        return StringUtil.numberToString(temp) + unitName;
    }
}
