/*
 * 文件名：InvRpayscheDetail.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.calculation.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 还本计划明细
 * 
 * @author 王波
 * @version 1.0 2018年6月1日
 * @see InvRpayscheDetail
 * @since 1.0
 */
public class InvRpayscheDetail extends InvRpayscheDetailKey {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 付息计划代码
     */
    private String rpayScheId;

    /**
     * 区间开始日
     */
    private Date vdate;

    /**
     * 区间结束日
     */
    private Date mdate;

    /**
     * 本金
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal capi;

    /**
     * 利率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * 金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal amt;

    /**
     * 工作流代码
     */
    private String workflowId;
    
    
    /**
     * 计息基础
     */
    private String accountBasis;
    
    
    /**
     * 获取 计息基础
     * @return 计息基础.
     */
    public String getAccountBasis() {
        return accountBasis;
    }

    
    /**
     * 设置 计息基础
     * @param accountBasis 计息基础
     */
    public void setAccountBasis(String accountBasis) {
        this.accountBasis = accountBasis;
    }

    /**
     * 取得 付息计划代码
     * 
     * @return 付息计划代码
     */
    public String getRpayScheId() {
        return rpayScheId;
    }

    /**
     * 设置 付息计划代码
     * 
     * @param rpayScheId 付息计划代码
     */
    public void setRpayScheId(String rpayScheId) {
        this.rpayScheId = rpayScheId == null ? null : rpayScheId.trim();
    }

    /**
     * 取得 区间开始日
     * 
     * @return 区间开始日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 区间开始日
     * 
     * @param vdate 区间开始日
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 区间结束日
     * 
     * @return 区间结束日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 区间结束日
     * 
     * @param mdate 区间结束日
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 本金
     * 
     * @return 本金
     */
    public BigDecimal getCapi() {
        return capi;
    }

    /**
     * 设置 本金
     * 
     * @param capi 本金
     */
    public void setCapi(BigDecimal capi) {
        this.capi = capi;
    }

    /**
     * 取得 利率
     * 
     * @return 利率
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 利率
     * 
     * @param yield 利率
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 取得 金额
     * 
     * @return 金额
     */
    public BigDecimal getAmt() {
        return amt;
    }

    /**
     * 设置 金额
     * 
     * @param amt 金额
     */
    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    /**
     * 取得 工作流代码
     * 
     * @return 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码
     * 
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }
}
