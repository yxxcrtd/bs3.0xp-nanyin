/*
 * 文件名：ImplOcpTrend.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年8月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.redisinit.dto;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.invest.common.riskinfrds.indic.entity.IOcpTrend;


/**
 * 预占趋势信息
 * 
 * @author zhangrui1
 * @version 1.0 2020年8月12日
 * @see ImplOcpTrend
 * @since 1.0
 */
public class ImplOcpTrend implements IOcpTrend {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 7779876201228654065L;

    /**
     * 预占实占编号
     */
    private String ocpCode;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getOcpCode() {
        return ocpCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOcpCode(String ocpCode) {
        this.ocpCode = ocpCode;
    }

    /**
     * 趋势
     */
    private int trend;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTrend(int trend) {
        this.trend = trend;
    }

    public int getTrend() {
        return trend;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTrendBad() {
        return trend < 0;
    }

    @Override
    public String toString() {
        return "<" + ocpCode + GlobalConstant.COLON + trend + ">";
    }
}
