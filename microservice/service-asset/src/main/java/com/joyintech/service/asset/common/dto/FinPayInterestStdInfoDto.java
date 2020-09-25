/*
 * 文件名：FinPayInterestStdInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinCashPlan;

/**
 * 付息信息
 * @author zhangnian
 * @version 1.0 2018年9月11日
 * @see FinPayInterestStdInfoDto
 * @since 1.0
 */
public class FinPayInterestStdInfoDto extends FinCashPayRule{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 操作类型
     */
    private String operateType;
    
    private String pageModel;
    /**
     * 现金流支付规则
     */
    private FinCashPayRule finCashPayRule;
    
    /**
     * 现金流支付规则List
     */
    private List<FinCashPayRule> finCashPayRuleList;

    /**
     * 现金流计划
     */
    private List<FinCashPlan> finCashPlanList;

    /**
     * 是否支付利息，针对现金流类型为本金的
     */
    private String isPayInt;
    
    /**
     * 获取 payTdayRule
     * @return payTdayRule.
     */
    public String getPayTdayRule() {
        return super.getPayTdayRule();
    }

    
    /**
     * 获取 payCycle
     * @return payCycle.
     */
    public String getPayCycle() {
        return super.getPayCycle();
    }
    
    /**
     * 获取 calcRule
     * @return calcRule.
     */
    public String getCalcRule() {
        return super.getCalcRule();
    }


    
    /**
     * 获取 firstPayDate
     * @return firstPayDate.
     */
    public Date getFirstPayDate() {
        return super.getFirstPayDate();
    }
    
    /**
     * 获取 finCashPayRule
     * @return finCashPayRule.
     */
    public FinCashPayRule getFinCashPayRule() {
        return finCashPayRule;
    }


    
    /**
     * 设置 finCashPayRule
     * @param finCashPayRule finCashPayRule
     */
    public void setFinCashPayRule(FinCashPayRule finCashPayRule) {
        this.finCashPayRule = finCashPayRule;
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
     * 获取 finCashPayRuleList
     * @return finCashPayRuleList.
     */
    public List<FinCashPayRule> getFinCashPayRuleList() {
        return finCashPayRuleList;
    }


    
    /**
     * 设置 finCashPayRuleList
     * @param finCashPayRuleList finCashPayRuleList
     */
    public void setFinCashPayRuleList(List<FinCashPayRule> finCashPayRuleList) {
        this.finCashPayRuleList = finCashPayRuleList;
    }


    
    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }


    
    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }


    
    /**
     * 获取 pageModel
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }


    
    /**
     * 设置 pageModel
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    /**
     * 获取 isPayInt
     *
     * @return isPayInt
     */
    public String getIsPayInt() {
        return isPayInt;
    }

    /**
     * 设置 isPayInt
     *
     * @param isPayInt isPayInt
     */
    public void setIsPayInt(String isPayInt) {
        this.isPayInt = isPayInt;
    }
}
