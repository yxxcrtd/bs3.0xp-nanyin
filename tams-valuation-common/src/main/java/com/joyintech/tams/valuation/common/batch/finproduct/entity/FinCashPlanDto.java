/*
 * 文件名：TargetCashPlan.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.batch.finproduct.entity;

/**
 * 标的现金流计划 - 实体类
 * 
 * @author chengzhong
 * @version 1.0 2018年5月25日
 * @see FinCashPlanDto
 * @since 1.0
 */
public class FinCashPlanDto {

    /**
     * 是否付息截止日
     */
    private boolean fDeadline;
    
    /**
     * 现金流付息计划
     */
    private FinCashPlanEx finCashPlanEx;

    
    /**
     * 获取 fDeadline
     * @return fDeadline.
     */
    public boolean isfDeadline() {
        return fDeadline;
    }

    
    /**
     * 设置 fDeadline
     * @param fDeadline fDeadline
     */
    public void setfDeadline(boolean fDeadline) {
        this.fDeadline = fDeadline;
    }

    
    /**
     * 获取 finCashPlanEx
     * @return finCashPlanEx.
     */
    public FinCashPlanEx getFinCashPlanEx() {
        return finCashPlanEx;
    }

    
    /**
     * 设置 finCashPlanEx
     * @param finCashPlanEx finCashPlanEx
     */
    public void setFinCashPlanEx(FinCashPlanEx finCashPlanEx) {
        this.finCashPlanEx = finCashPlanEx;
    }
    
    
}
