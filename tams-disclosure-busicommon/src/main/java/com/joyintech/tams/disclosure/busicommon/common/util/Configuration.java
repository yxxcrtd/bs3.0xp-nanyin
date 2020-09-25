/*
 * 文件名：Configuration.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：何阳阳
 * 修改时间：2017年1月20日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.disclosure.busicommon.common.util;


/**
 * 引擎配置
 * @author 何阳阳
 * @version 1.0 2017年1月20日
 * @see Configuration
 * @since 1.0
 */
public interface Configuration {
    /**
     * 获取未来现金流中剩余期限计算结果的有效小位数
     * @return 有效小数位数
     */
    int getForwadCashTermScale();
    
    /**
     * 获取引擎计算中全精度的指定精度
     * @return 全精度
     */
    int getAllPrecision();
    
    /**
     * 默认小数位
     * @return 小数位
     */
    int defalutScale();

    /**
     * 二分法--精度
     */
    double dicPrecision();

    /**
     * 二分法--最大循环次数
     */
    int dicTotalTimes();

    /**
     * 二分法--起始最低收益率
     */
    double dicStartRateLow();

    /**
     * 二分法--起始最高收益率
     */
    double dicStartRateHigh();
    
    /**
     * 零息贴现债券特殊处理算法中的方案，支持A和B
     */
    String disDebtScheme();
}
