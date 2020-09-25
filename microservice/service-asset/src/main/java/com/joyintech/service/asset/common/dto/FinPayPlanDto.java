/*
 * 文件名：FinPayInterestPlanDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinProduct;

/**
 *  支付计划Dto
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月23日
 * @see FinPayPlanDto
 * @since 1.0
 */
public class FinPayPlanDto extends FinProduct{
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 资产类别
     */
    private String type6;
    
    /**
     * 支付频率
     */
    private String payCycle;
    
    /**
     * 计息基础，A/360，A/365等
     */
    private String basis;
    
    /**
     * 发行规模
     */
    private BigDecimal issueAmt;
    
    /**
     * 付息营业日准则
     */
    private String payTdayRule;
    
    /**
     * 现金流计划
     */
    private List<FinCashPlan> finCashPlanList;

    /**
     * 现金流代码
     */
    private String cashId;


    /**
     * 获取 type6
     * @return type6.
     */
    public String getType6() {
        return type6;
    }

    
    /**
     * 设置 type6
     * @param type6 type6
     */
    public void setType6(String type6) {
        this.type6 = type6;
    }

    
    /**
     * 获取 payCycle
     * @return payCycle.
     */
    public String getPayCycle() {
        return payCycle;
    }

    
    /**
     * 设置 payCycle
     * @param payCycle payCycle
     */
    public void setPayCycle(String payCycle) {
        this.payCycle = payCycle;
    }

    
    /**
     * 获取 finCashPlanList
     * @return finCashPlanList.
     */
    public List<FinCashPlan> getFinCashPlanList() {
        return finCashPlanList;
    }

    
    /**
     * 设置 finCashPlanList
     * @param finCashPlanList finCashPlanList
     */
    public void setFinCashPlanList(List<FinCashPlan> finCashPlanList) {
        this.finCashPlanList = finCashPlanList;
    }


    
    /**
     * 获取 basis
     * @return basis.
     */
    public String getBasis() {
        return basis;
    }


    
    /**
     * 设置 basis
     * @param basis basis
     */
    public void setBasis(String basis) {
        this.basis = basis;
    }


    
    /**
     * 获取 issueAmt
     * @return issueAmt.
     */
    public BigDecimal getIssueAmt() {
        return issueAmt;
    }


    
    /**
     * 设置 issueAmt
     * @param issueAmt issueAmt
     */
    public void setIssueAmt(BigDecimal issueAmt) {
        this.issueAmt = issueAmt;
    }


    
    /**
     * 获取 payTdayRule
     * @return payTdayRule.
     */
    public String getPayTdayRule() {
        return payTdayRule;
    }


    
    /**
     * 设置 payTdayRule
     * @param payTdayRule payTdayRule
     */
    public void setPayTdayRule(String payTdayRule) {
        this.payTdayRule = payTdayRule;
    }

    public String getCashId() {
        return cashId;
    }

    public void setCashId(String cashId) {
        this.cashId = cashId;
    }
}
