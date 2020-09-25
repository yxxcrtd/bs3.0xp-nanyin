/*
 * 文件名：IndicatorResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.indic.entity;

/**
 * 指标计算结果存储结构
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月28日
 * @see IndicatorResult
 * @since 1.0
 */
public class IndicatorResult implements IIndicatorResult {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 1895719351374024367L;

    /**
     * 本次试算的指标结果值
     */
    private Object indicatorVal;

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getIndicatorVal() {
        return indicatorVal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIndicatorVal(Object val) {
        indicatorVal = val;
    }

    /**
     * 上次试算的指标结果值
     */
    private Object indicatorOldVal;

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getIndicatorOldVal() {
        return indicatorOldVal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIndicatorOldVal(Object val) {
        indicatorOldVal = val;
    }

    /**
     * 指标状态（正常、预警、审批、违规）
     */
    private String indStatus;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIndStatus() {
        return indStatus;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIndStatus(String status) {
        indStatus = status;
    }

    /**
     * 联合指标状态
     */
    private String uniteStatus;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUniteStatus() {
        return uniteStatus;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUniteStatus(String status) {
        uniteStatus = status;
    }

    private int indicatorTrend;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIndicatorTrend(int tred) {
        indicatorTrend = tred;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getIndicatorTrend() {
        return indicatorTrend;
    }

    private IIndividLimit individLimit;

    /**
     * {@inheritDoc}
     */
    @Override
    public IIndividLimit getIndividLimit() {
        return individLimit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setIndividLimit(IIndividLimit limit) {
        individLimit = limit;
    }
}
