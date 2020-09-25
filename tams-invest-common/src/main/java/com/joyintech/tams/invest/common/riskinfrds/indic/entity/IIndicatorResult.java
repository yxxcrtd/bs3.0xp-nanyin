/*
 * 文件名：IIndicatorResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.indic.entity;

import java.io.Serializable;

/**
 * 指标计算结果存储结构
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月28日
 * @see IIndicatorResult
 * @since 1.0
 */
public interface IIndicatorResult extends Serializable {

    /**
     * 取得 本次试算的指标结果值
     * 
     * @return 本次试算的指标结果值
     * @see
     * @since 1.0
     */
    Object getIndicatorVal();

    /**
     * 设置 本次试算的指标结果值
     * 
     * @param val 本次试算的指标结果值
     * @see
     * @since 1.0
     */
    void setIndicatorVal(Object val);

    /**
     * 取得 上次试算的指标结果值
     * 
     * @return 上次试算的指标结果值
     * @see
     * @since 1.0
     */
    Object getIndicatorOldVal();

    /**
     * 设置 上次试算的指标结果值
     * 
     * @param val 上次试算的指标结果值
     * @see
     * @since 1.0
     */
    void setIndicatorOldVal(Object val);

    /**
     * 取得 指标状态（正常、预警、审批、违规）
     * 
     * @return 指标状态
     * @see
     * @since 1.0
     */
    String getIndStatus();

    /**
     * 设置 指标状态
     * 
     * @param status 指标状态（正常、预警、审批、违规）
     * @see
     * @since 1.0
     */
    void setIndStatus(String status);

    /**
     * 取得 联合指标状态
     * 
     * @return 联合指标状态
     * @see
     * @since 1.0
     */
    String getUniteStatus();

    /**
     * 设置 联合指标状态
     * 
     * @param status 联合指标状态
     * @see
     * @since 1.0
     */
    void setUniteStatus(String status);

    /**
     * 设置 指标趋势
     * 
     * @param tred 指标趋势（0以上表示趋势向好，0以下表示趋势向坏）
     * @see
     * @since 1.0
     */
    void setIndicatorTrend(int tred);

    /**
     * 取得 指标趋势
     * 
     * @return 指标趋势
     * @see
     * @since 1.0
     */
    int getIndicatorTrend();

    /**
     * 取得 阈值信息
     * 
     * @return 阈值信息
     * @see
     * @since 1.0
     */
    IIndividLimit getIndividLimit();

    /**
     * 设置 阈值信息
     * 
     * @param limit 阈值信息
     * @see
     * @since 1.0
     */
    void setIndividLimit(IIndividLimit limit);
}
