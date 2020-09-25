/*
 * 文件名：MockPtlPortfolioValuationDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2020年4月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.portfolio;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.PtlPortfolioValuation;

/**
 * 组合估值DTO
 * 组合估值查询 返回结果
 * 
 * @author etliu
 * @version 1.0 2020年4月13日
 * @see MockPtlPortfolioValuationDto
 * @since 1.0
 */
public class MockPtlPortfolioValuationDto extends PtlPortfolioValuation {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 7933571009038915892L;

    /**
     * 资产类合计
     */
    private BigDecimal assetSum;

    /**
     * 负债类合计
     */
    private BigDecimal liabilitiesSum;

    /**
     * 获取 assetSum
     * 
     * @return assetSum.
     */
    public BigDecimal getAssetSum() {
        return assetSum;
    }

    /**
     * 设置 assetSum
     * 
     * @param assetSum assetSum
     */
    public void setAssetSum(BigDecimal assetSum) {
        this.assetSum = assetSum;
    }

    /**
     * 获取 liabilitiesSum
     * 
     * @return liabilitiesSum.
     */
    public BigDecimal getLiabilitiesSum() {
        return liabilitiesSum;
    }

    /**
     * 设置 liabilitiesSum
     * 
     * @param liabilitiesSum liabilitiesSum
     */
    public void setLiabilitiesSum(BigDecimal liabilitiesSum) {
        this.liabilitiesSum = liabilitiesSum;
    }

}
