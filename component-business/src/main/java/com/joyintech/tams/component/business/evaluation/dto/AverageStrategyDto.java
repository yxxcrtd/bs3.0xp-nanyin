/*
 * 文件名：AverageStrategyDto.java
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
 * 平衡策略执行有效性实体类
 * 
 * @author zhangJin
 * @version 1.0 2019年10月9日
 * @see AverageStrategyDto
 * @since 1.0
 */
public class AverageStrategyDto {

    /**
     * 批次号
     */
    private String batchId;

    /**
     * 投资期间组合平均持仓比例
     */
    private BigDecimal avgAmtRatio;

    /**
     * 获取 batchId
     * 
     * @return batchId.
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * 设置 batchId
     * 
     * @param batchId batchId
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    /**
     * 获取 avgAmtRatio
     * 
     * @return avgAmtRatio.
     */
    public BigDecimal getAvgAmtRatio() {
        return avgAmtRatio;
    }

    /**
     * 设置 avgAmtRatio
     * 
     * @param avgAmtRatio avgAmtRatio
     */
    public void setAvgAmtRatio(BigDecimal avgAmtRatio) {
        this.avgAmtRatio = avgAmtRatio;
    }

}
