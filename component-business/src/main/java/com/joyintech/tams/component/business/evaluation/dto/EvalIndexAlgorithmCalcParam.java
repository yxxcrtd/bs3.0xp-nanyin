/*
 * 文件名：EvalIndexAlgorithmCalcParam.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangJin
 * 修改时间：2019年9月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.evaluation.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 评价指标计算参数
 * 
 * @author zhangJin
 * @version 1.0 2019年9月30日
 * @see EvalIndexAlgorithmCalcParam
 * @since 1.0
 */
public class EvalIndexAlgorithmCalcParam {

    /**
     * 区间开始日
     */
    private Date startDate;

    /**
     * 区间结束日
     */
    private Date endDate;

    /**
     * 区间开始日累计单位净值
     */
    private BigDecimal sAccuNetValue;

    /**
     * 区间结束日累计单位净值
     */
    private BigDecimal eAccuNetValue;

    /**
     * 区间开始日单位净值
     */
    private BigDecimal sNetValue;

    /**
     * 区间每日投资收益率
     */
    private List<DailyYieldDto> dailyInvestYieldList;

    /**
     * 区间业绩基准列表
     */
    private List<DailyYieldDto> dailyBasicRateList;

    /**
     * 区间累计单位净值列表
     */
    private List<BigDecimal> accuNetValueList;

    /**
     * 获取 accuNetValueList
     * 
     * @return accuNetValueList.
     */
    public List<BigDecimal> getAccuNetValueList() {
        return accuNetValueList;
    }

    /**
     * 设置 accuNetValueList
     * 
     * @param accuNetValueList accuNetValueList
     */
    public void setAccuNetValueList(List<BigDecimal> accuNetValueList) {
        this.accuNetValueList = accuNetValueList;
    }

    /**
     * 获取 startDate
     * 
     * @return startDate.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置 startDate
     * 
     * @param startDate startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取 endDate
     * 
     * @return endDate.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置 endDate
     * 
     * @param endDate endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取 sAccuNetValue
     * 
     * @return sAccuNetValue.
     */
    public BigDecimal getsAccuNetValue() {
        return sAccuNetValue;
    }

    /**
     * 设置 sAccuNetValue
     * 
     * @param sAccuNetValue sAccuNetValue
     */
    public void setsAccuNetValue(BigDecimal sAccuNetValue) {
        this.sAccuNetValue = sAccuNetValue;
    }

    /**
     * 获取 eAccuNetValue
     * 
     * @return eAccuNetValue.
     */
    public BigDecimal geteAccuNetValue() {
        return eAccuNetValue;
    }

    /**
     * 设置 eAccuNetValue
     * 
     * @param eAccuNetValue eAccuNetValue
     */
    public void seteAccuNetValue(BigDecimal eAccuNetValue) {
        this.eAccuNetValue = eAccuNetValue;
    }

    /**
     * 获取 sNetValue
     * 
     * @return sNetValue.
     */
    public BigDecimal getsNetValue() {
        return sNetValue;
    }

    /**
     * 设置 sNetValue
     * 
     * @param sNetValue sNetValue
     */
    public void setsNetValue(BigDecimal sNetValue) {
        this.sNetValue = sNetValue;
    }

    /**
     * 获取 dailyInvestYieldList
     * 
     * @return dailyInvestYieldList.
     */
    public List<DailyYieldDto> getDailyInvestYieldList() {
        return dailyInvestYieldList;
    }

    /**
     * 设置 dailyInvestYieldList
     * 
     * @param dailyInvestYieldList dailyInvestYieldList
     */
    public void setDailyInvestYieldList(List<DailyYieldDto> dailyInvestYieldList) {
        this.dailyInvestYieldList = dailyInvestYieldList;
    }

    /**
     * 获取 dailyBasicRateList
     * 
     * @return dailyBasicRateList.
     */
    public List<DailyYieldDto> getDailyBasicRateList() {
        return dailyBasicRateList;
    }

    /**
     * 设置 dailyBasicRateList
     * 
     * @param dailyBasicRateList dailyBasicRateList
     */
    public void setDailyBasicRateList(List<DailyYieldDto> dailyBasicRateList) {
        this.dailyBasicRateList = dailyBasicRateList;
    }

}
