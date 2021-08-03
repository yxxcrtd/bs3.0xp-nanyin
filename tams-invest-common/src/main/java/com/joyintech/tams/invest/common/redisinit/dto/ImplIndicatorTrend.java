/*
 * 文件名：ImplIndicatorTrend.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2020年8月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.redisinit.dto;

import java.util.HashSet;
import java.util.Set;

import com.joyintech.tams.invest.common.riskinfrds.indic.entity.IIndicatorTrend;
import com.joyintech.tams.invest.common.riskinfrds.indic.entity.IOcpTrend;


/**
 * 指标趋势信息
 * 
 * @author zhangrui1
 * @version 1.0 2020年8月12日
 * @see ImplIndicatorTrend
 * @since 1.0
 */
public class ImplIndicatorTrend implements IIndicatorTrend {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -3073106848272089033L;

    /**
     * 指标代码
     */
    private String indCode;

    /**
     * 设置 指标代码
     * 
     * @param indCode 指标代码
     */
    public void setIndCode(String indCode) {
        this.indCode = indCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIndCode() {
        return indCode;
    }

    /**
     * 组合代码
     */
    private String portfolioCode;

    /**
     * 设置 组合代码
     * 
     * @param portfolioCode 组合代码
     */
    public void setPortfolioCode(String portfolioCode) {
        this.portfolioCode = portfolioCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPortfolioCode() {
        return portfolioCode;
    }

    /**
     * 预占实占相关指标趋势列表
     */
    private Set<IOcpTrend> ocpSet;

    /**
     * 设置 预占实占相关指标趋势列表
     * 
     * @param ocpSet 预占实占相关指标趋势列表
     */
    public void setOcpSet(Set<IOcpTrend> ocpSet) {
        this.ocpSet = ocpSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<IOcpTrend> getOcpSet() {
        return ocpSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addOcpTrend(IOcpTrend trend) {
        if (ocpSet == null) {
            ocpSet = new HashSet<>();
        }
        ocpSet.add(trend);
    }

}
