/*
 * 文件名：TimePointStrategyDto.java
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
 * 时点策略执行有效性实体类
 * 
 * @author zhangJin
 * @version 1.0 2019年10月9日
 * @see TimePointStrategyDto
 * @since 1.0
 */
public class TimePointStrategyDto {

    /**
     * 批次号
     */
    private String batchId;

    /**
     * 某一交易日组合持仓比例
     */
    private BigDecimal xDayPtlAmtRatio;

    /**
     * 时点策略执行有效性
     */
    private BigDecimal timePointEff;

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
     * 获取 xDayPtlAmtRatio
     * 
     * @return xDayPtlAmtRatio.
     */
    public BigDecimal getxDayPtlAmtRatio() {
        return xDayPtlAmtRatio;
    }

    /**
     * 设置 xDayPtlAmtRatio
     * 
     * @param xDayPtlAmtRatio xDayPtlAmtRatio
     */
    public void setxDayPtlAmtRatio(BigDecimal xDayPtlAmtRatio) {
        this.xDayPtlAmtRatio = xDayPtlAmtRatio;
    }

    /**
     * 获取 timePointEff
     * 
     * @return timePointEff.
     */
    public BigDecimal getTimePointEff() {
        return timePointEff;
    }

    /**
     * 设置 timePointEff
     * 
     * @param timePointEff timePointEff
     */
    public void setTimePointEff(BigDecimal timePointEff) {
        this.timePointEff = timePointEff;
    }

}
