/*
 * 文件名：ImplPtlIndicator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年8月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.redisinit.dto;

import java.util.Map;
import java.util.Set;

import com.joyintech.tams.common.base.entity.RskPortfolioIndicator;
import com.joyintech.tams.invest.common.riskinfrds.prod.entity.IPtlIndicator;

/**
 * 风险产品关联指标实现类
 * 
 * @author 裴家俊
 * @version 1.0 2019年9月2日
 * @see ImplPtlIndicator
 * @since 1.0
 */
public class ImplPtlIndicator extends RskPortfolioIndicator implements IPtlIndicator {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -518350547267697762L;

    /**
     * 组合绑定的组合包，有可能为NULL
     */
    private String portfolioBagId;

    /**
     * 组合(包)绑定的指标，多个用逗号分隔
     */
    private String ptlIndicatorId;

    private String portfIdRedis;

    private Map<String, Set<String>> ptlIndicatorRedis;

    private Map<String, Object> portfolioBagIdRedis;

    /**
     * 组合绑定的组合包，有可能为NULL
     */
    private String portfolioBagName;

    private String ptlNameRedis;

    @Override
    public String getPtlCode() {
        return portfIdRedis;
    }

    @Override
    public Map<String, Object> getPtlBagIdList() {
        return portfolioBagIdRedis;
    }

    @Override
    public Map<String, Set<String>> getPtlIndicator() {
        return ptlIndicatorRedis;
    }

    public String getPortfolioBagId() {
        return portfolioBagId;
    }

    public void setPortfolioBagId(String portfolioBagId) {
        this.portfolioBagId = portfolioBagId;
    }

    public String getPtlIndicatorId() {
        return ptlIndicatorId;
    }

    public void setPtlIndicatorId(String ptlIndicatorId) {
        this.ptlIndicatorId = ptlIndicatorId;
    }

    public void setPtlIndicatorRedis(Map<String, Set<String>> ptlIndicatorRedis) {
        this.ptlIndicatorRedis = ptlIndicatorRedis;
    }

    public void setPortfolioBagIdRedis(Map<String, Object> portfolioBagIdRedis) {
        this.portfolioBagIdRedis = portfolioBagIdRedis;
    }

    public void setPortfIdRedis(String portfIdRedis) {
        this.portfIdRedis = portfIdRedis;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPtlName() {
        return ptlNameRedis;
    }

    /**
     * 设置 组合名称
     * 
     * @param ptlNameRedis 组合名称
     * @see
     * @since 1.0
     */
    public void setPtlNameRedis(String ptlNameRedis) {
        this.ptlNameRedis = ptlNameRedis;
    }

    public String getPortfolioBagName() {
        return portfolioBagName;
    }

    public void setPortfolioBagName(String portfolioBagName) {
        this.portfolioBagName = portfolioBagName;
    }
}
