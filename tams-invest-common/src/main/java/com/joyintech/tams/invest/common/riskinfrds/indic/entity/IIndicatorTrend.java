/*
 * 文件名：IIndicatorTrend.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年8月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.indic.entity;

import java.util.Set;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.base.entity.IBusiness;

/**
 * 指标趋势信息-redis存储结构
 * 
 * @author zhangrui1
 * @version 1.0 2020年8月11日
 * @see IIndicatorTrend
 * @since 1.0
 */
public interface IIndicatorTrend extends IBusiness {

    /**
     * {@inheritDoc}
     */
    @Override
    default String getKey() {
        return RedisKeyEnum.指标趋势信息.key();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default String getField() {
        return getIndCode() + GlobalConstant.COLON + getPortfolioCode();
    }

    /**
     * 取得 指标代码
     * 
     * @return 指标代码
     */
    String getIndCode();

    /**
     * 取得 组合代码
     * 
     * @return 组合代码
     */
    String getPortfolioCode();

    /**
     * 取得 预占实占相关指标趋势列表
     * 
     * @return 预占实占相关指标趋势列表
     */
    Set<IOcpTrend> getOcpSet();

    /**
     * 添加 预占实占相关指标趋势列表
     * 
     * @param trend 预占实占相关指标趋势列表
     */
    void addOcpTrend(IOcpTrend trend);
}
