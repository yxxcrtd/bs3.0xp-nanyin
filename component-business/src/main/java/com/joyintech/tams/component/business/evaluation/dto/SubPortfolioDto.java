/*
 * 文件名：SubPortfolioDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangJin
 * 修改时间：2019年10月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.evaluation.dto;

import java.math.BigDecimal;

/**
 * 子组合查询结果
 * 
 * @author zhangJin
 * @version 1.0 2019年10月9日
 * @see SubPortfolioDto
 * @since 1.0
 */
public class SubPortfolioDto {

    /**
     * 投资组合代码
     */
    private String portfolioId;

    /**
     * 子组合代码
     */
    private String subPortfolioId;

    /**
     * 收益类型
     */
    private String profitType;

    /**
     * 继承比例
     */
    private BigDecimal disPercent;

    /**
     * 获取 portfolioId
     * 
     * @return portfolioId.
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 portfolioId
     * 
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 subPortfolioId
     * 
     * @return subPortfolioId.
     */
    public String getSubPortfolioId() {
        return subPortfolioId;
    }

    /**
     * 设置 subPortfolioId
     * 
     * @param subPortfolioId subPortfolioId
     */
    public void setSubPortfolioId(String subPortfolioId) {
        this.subPortfolioId = subPortfolioId;
    }

    /**
     * 获取 profitType
     * 
     * @return profitType.
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 profitType
     * 
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    /**
     * 获取 disPercent
     * 
     * @return disPercent.
     */
    public BigDecimal getDisPercent() {
        return disPercent;
    }

    /**
     * 设置 disPercent
     * 
     * @param disPercent disPercent
     */
    public void setDisPercent(BigDecimal disPercent) {
        this.disPercent = disPercent;
    }
}
