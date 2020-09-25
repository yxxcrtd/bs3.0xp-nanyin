/*
 * 文件名：GlobalConstant.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：何阳阳
 * 修改时间：2017年2月15日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.disclosure.busicommon.common.util;

import java.math.BigDecimal;

/**
 * 全价常量定义
 * @author 何阳阳
 * @version 1.0 2017年2月15日
 * @see GlobalConstant
 * @since 1.0
 */
public interface GlobalConstant {
    
    /**
     * 数字100
     */
    BigDecimal NUMBER_100 = new BigDecimal("100");
    
    /**
     * 数字360
     */
    BigDecimal NUMBER_360 = new BigDecimal("360");
    
    /**
     * 数字365
     */
    BigDecimal NUMBER_365 = new BigDecimal("365");
    
    /**
     * 零息贴现债券处理方案：A
     */
    String DIS_DEBT_SCHEME_A = "A";
    
    /**
     * 零息贴现债券处理方案：B
     */
    String DIS_DEBT_SCHEME_B = "B";
}
