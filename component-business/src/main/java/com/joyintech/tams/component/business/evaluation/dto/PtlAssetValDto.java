/*
 * 文件名：PtlAssetValDto.java
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
 * 组合资产估值查询结果
 * 
 * @author zhangJin
 * @version 1.0 2019年10月9日
 * @see PtlAssetValDto
 * @since 1.0
 */
public class PtlAssetValDto {

    /**
     * 持仓
     */
    private BigDecimal amt;

    /**
     * 公允价值变动
     */
    private BigDecimal floatIngpl;

    /**
     * 成本
     */
    private BigDecimal cost;

    /**
     * 获取 amt
     * 
     * @return amt.
     */
    public BigDecimal getAmt() {
        return amt;
    }

    /**
     * 设置 amt
     * 
     * @param amt amt
     */
    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    /**
     * 获取 floatIngpl
     * 
     * @return floatIngpl.
     */
    public BigDecimal getFloatIngpl() {
        return floatIngpl;
    }

    /**
     * 设置 floatIngpl
     * 
     * @param floatIngpl floatIngpl
     */
    public void setFloatIngpl(BigDecimal floatIngpl) {
        this.floatIngpl = floatIngpl;
    }

    /**
     * 获取 cost
     * 
     * @return cost.
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * 设置 cost
     * 
     * @param cost cost
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

}
