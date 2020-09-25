/*
 * 文件名：PtlIncomeIndexDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangJin
 * 修改时间：2019年10月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlinvportfoliostrategy.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.tams.common.base.entity.PtlIncomeIndexFloat;

/**
 * 评价指标列表
 * 
 * @author zhangJin
 * @version 1.0 2019年10月15日
 * @see PtlIncomeIndexDto
 * @since 1.0
 */
public class PtlIncomeIndexDto {

    /**
     * 指标名称
     */
    private String evalIndex;

    /**
     * 目标类型
     */
    private String incomeType;

    /**
     * 目标值
     */
    private String incomeTarget;

    /**
     * 目标值代码形式
     */
    private String incomeTargetCode;

    /**
     * 目标值上限
     */
    private BigDecimal maxIncomeTarget;

    /**
     * 目标值下限
     */
    private BigDecimal minIncomeTarget;

    /**
     * 利差
     */
    private BigDecimal spreadRate;

    /**
     * 浮动类型的详情列表
     */
    private List<PtlIncomeIndexFloat> floatList;

    /**
     * 获取 spreadRate
     * 
     * @return spreadRate.
     */
    public BigDecimal getSpreadRate() {
        return spreadRate;
    }

    /**
     * 设置 spreadRate
     * 
     * @param spreadRate spreadRate
     */
    public void setSpreadRate(BigDecimal spreadRate) {
        this.spreadRate = spreadRate;
    }

    /**
     * 获取 floatList
     * 
     * @return floatList.
     */
    public List<PtlIncomeIndexFloat> getFloatList() {
        return floatList;
    }

    /**
     * 设置 floatList
     * 
     * @param floatList floatList
     */
    public void setFloatList(List<PtlIncomeIndexFloat> floatList) {
        this.floatList = floatList;
    }

    /**
     * 获取 evalIndex
     * 
     * @return evalIndex.
     */
    public String getEvalIndex() {
        return evalIndex;
    }

    /**
     * 设置 evalIndex
     * 
     * @param evalIndex evalIndex
     */
    public void setEvalIndex(String evalIndex) {
        this.evalIndex = evalIndex;
    }

    /**
     * 获取 incomeType
     * 
     * @return incomeType.
     */
    public String getIncomeType() {
        return incomeType;
    }

    /**
     * 设置 incomeType
     * 
     * @param incomeType incomeType
     */
    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    /**
     * 获取 incomeTarget
     * 
     * @return incomeTarget.
     */
    public String getIncomeTarget() {
        return incomeTarget;
    }

    /**
     * 设置 incomeTarget
     * 
     * @param incomeTarget incomeTarget
     */
    public void setIncomeTarget(String incomeTarget) {
        this.incomeTarget = incomeTarget;
    }

    /**
     * 获取 maxIncomeTarget
     * 
     * @return maxIncomeTarget.
     */
    public BigDecimal getMaxIncomeTarget() {
        return maxIncomeTarget;
    }

    /**
     * 设置 maxIncomeTarget
     * 
     * @param maxIncomeTarget maxIncomeTarget
     */
    public void setMaxIncomeTarget(BigDecimal maxIncomeTarget) {
        this.maxIncomeTarget = maxIncomeTarget;
    }

    /**
     * 获取 minIncomeTarget
     * 
     * @return minIncomeTarget.
     */
    public BigDecimal getMinIncomeTarget() {
        return minIncomeTarget;
    }

    /**
     * 设置 minIncomeTarget
     * 
     * @param minIncomeTarget minIncomeTarget
     */
    public void setMinIncomeTarget(BigDecimal minIncomeTarget) {
        this.minIncomeTarget = minIncomeTarget;
    }

    /**
     * 获取 incomeTargetCode
     * 
     * @return incomeTargetCode.
     */
    public String getIncomeTargetCode() {
        return incomeTargetCode;
    }

    /**
     * 设置 incomeTargetCode
     * 
     * @param incomeTargetCode incomeTargetCode
     */
    public void setIncomeTargetCode(String incomeTargetCode) {
        this.incomeTargetCode = incomeTargetCode;
    }

}
