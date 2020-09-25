/*
 * 文件名：DefaultConfig.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：何阳阳
 * 修改时间：2017年1月20日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.disclosure.busicommon.common.config;

import com.joyintech.tams.disclosure.busicommon.common.util.Configuration;
import com.joyintech.tams.disclosure.busicommon.common.util.GlobalConstant;

/**
 * 默认配置
 * @author 何阳阳
 * @version 1.0 2017年1月20日
 * @see DefaultConfig
 * @since 1.0
 */
public class DefaultConfig implements Configuration {

    /**
     * {@inheritDoc}
     */
    @Override
    public int getForwadCashTermScale() {
        return 20;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAllPrecision() {
        return 20;
    }

    /**
     * {@inheritDoc}
     * update by xr  精度改为14位
     */
    @Override
    public int defalutScale() {
        return 14;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double dicPrecision() {
        return 1e-18;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int dicTotalTimes() {
        return 100;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double dicStartRateLow() {
        return -1D;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double dicStartRateHigh() {
        return 1D;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String disDebtScheme() {
        return GlobalConstant.DIS_DEBT_SCHEME_B;
    }

}
