/*
 * 文件名：InvRepaySchedule.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.dto.calc;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 还本计划 
 * 
 * @author 王波
 * @version 1.0 2018年6月1日
 * @see InvRepaySchedule
 * @since 1.0
 */
public class InvRepaySchedule extends InvRepayScheduleKey {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 展期代码
     */
    private String exhPerId;

    /**
     * 资产类型 参照数据字典
     */
    private String assetType;

    /**
     * 是否带息 参照数据字典
     */
    private String isPayInt;

    /**
     * 理论起始日
     */
    private Date theVdate;

    /**
     * 理论结束日
     */
    private Date theMdate;

    /**
     * 实际起始日
     */
    private Date actVdate;

    /**
     * 实际结束日
     */
    private Date actMdate;

    /**
     * 调整前结束日
     */
    private Date badjEndDate;

    /**
     * 还本日
     */
    private Date repayDate;

    /**
     * 应计利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intAmt;

    /**
     * 预计收息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal expInAmt;

    /**
     * 当前本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curPrin;

    /**
     * 提前还本金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal elyRepayAmt;

    /**
     * 按期还本金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal schRepayAmt;

    /**
     * 还本金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repayAmt;

    /**
     * 预计总金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal preTalAmt;

    /**
     * 当期最新利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal newRate;

    /**
     * 支付状态 参照数据字典
     */
    private String payStatus;

    /**
     * 录入方式 参照数据字典
     */
    private String inputType;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 不收息时应计利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tempIntAmt;

    /**
     * 不收息时预计收息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tempExpInAmt;

    /**
     * 取得 资产代码
     * @return 资产代码
     */
    public String getAssetId() {
        return assetId;
    }

    /**
     * 设置 资产代码
     * @param assetId 资产代码
     */
    public void setAssetId(String assetId) {
        this.assetId = assetId == null ? null : assetId.trim();
    }

    /**
     * 取得 展期代码
     * @return 展期代码
     */
    public String getExhPerId() {
        return exhPerId;
    }

    /**
     * 设置 展期代码
     * @param exhPerId 展期代码
     */
    public void setExhPerId(String exhPerId) {
        this.exhPerId = exhPerId == null ? null : exhPerId.trim();
    }

    /**
     * 取得 资产类型 参照数据字典
     * @return 资产类型 参照数据字典
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * 设置 资产类型 参照数据字典
     * @param assetType 资产类型 参照数据字典
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
    }

    /**
     * 取得 是否带息 参照数据字典
     * @return 是否带息 参照数据字典
     */
    public String getIsPayInt() {
        return isPayInt;
    }

    /**
     * 设置 是否带息 参照数据字典
     * @param isPayInt 是否带息 参照数据字典
     */
    public void setIsPayInt(String isPayInt) {
        this.isPayInt = isPayInt == null ? null : isPayInt.trim();
    }

    /**
     * 取得 理论起始日
     * @return 理论起始日
     */
    public Date getTheVdate() {
        return theVdate;
    }

    /**
     * 设置 理论起始日
     * @param theVdate 理论起始日
     */
    public void setTheVdate(Date theVdate) {
        this.theVdate = theVdate;
    }

    /**
     * 取得 理论结束日
     * @return 理论结束日
     */
    public Date getTheMdate() {
        return theMdate;
    }

    /**
     * 设置 理论结束日
     * @param theMdate 理论结束日
     */
    public void setTheMdate(Date theMdate) {
        this.theMdate = theMdate;
    }

    /**
     * 取得 实际起始日
     * @return 实际起始日
     */
    public Date getActVdate() {
        return actVdate;
    }

    /**
     * 设置 实际起始日
     * @param actVdate 实际起始日
     */
    public void setActVdate(Date actVdate) {
        this.actVdate = actVdate;
    }

    /**
     * 取得 实际结束日
     * @return 实际结束日
     */
    public Date getActMdate() {
        return actMdate;
    }

    /**
     * 设置 实际结束日
     * @param actMdate 实际结束日
     */
    public void setActMdate(Date actMdate) {
        this.actMdate = actMdate;
    }

    /**
     * 取得 调整前结束日
     * @return 调整前结束日
     */
    public Date getBadjEndDate() {
        return badjEndDate;
    }

    /**
     * 设置 调整前结束日
     * @param badjEndDate 调整前结束日
     */
    public void setBadjEndDate(Date badjEndDate) {
        this.badjEndDate = badjEndDate;
    }

    /**
     * 取得 还本日
     * @return 还本日
     */
    public Date getRepayDate() {
        return repayDate;
    }

    /**
     * 设置 还本日
     * @param repayDate 还本日
     */
    public void setRepayDate(Date repayDate) {
        this.repayDate = repayDate;
    }

    /**
     * 取得 应计利息
     * @return 应计利息
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }

    /**
     * 设置 应计利息
     * @param intAmt 应计利息
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    /**
     * 取得 预计收息
     * @return 预计收息
     */
    public BigDecimal getExpInAmt() {
        return expInAmt;
    }

    /**
     * 设置 预计收息
     * @param expInAmt 预计收息
     */
    public void setExpInAmt(BigDecimal expInAmt) {
        this.expInAmt = expInAmt;
    }

    /**
     * 取得 当前本金
     * @return 当前本金
     */
    public BigDecimal getCurPrin() {
        return curPrin;
    }

    /**
     * 设置 当前本金
     * @param curPrin 当前本金
     */
    public void setCurPrin(BigDecimal curPrin) {
        this.curPrin = curPrin;
    }

    /**
     * 取得 提前还本金额
     * @return 提前还本金额
     */
    public BigDecimal getElyRepayAmt() {
        return elyRepayAmt;
    }

    /**
     * 设置 提前还本金额
     * @param elyRepayAmt 提前还本金额
     */
    public void setElyRepayAmt(BigDecimal elyRepayAmt) {
        this.elyRepayAmt = elyRepayAmt;
    }

    /**
     * 取得 按期还本金额
     * @return 按期还本金额
     */
    public BigDecimal getSchRepayAmt() {
        return schRepayAmt;
    }

    /**
     * 设置 按期还本金额
     * @param schRepayAmt 按期还本金额
     */
    public void setSchRepayAmt(BigDecimal schRepayAmt) {
        this.schRepayAmt = schRepayAmt;
    }

    /**
     * 取得 还本金额
     * @return 还本金额
     */
    public BigDecimal getRepayAmt() {
        return repayAmt;
    }

    /**
     * 设置 还本金额
     * @param repayAmt 还本金额
     */
    public void setRepayAmt(BigDecimal repayAmt) {
        this.repayAmt = repayAmt;
    }

    /**
     * 取得 预计总金额
     * @return 预计总金额
     */
    public BigDecimal getPreTalAmt() {
        return preTalAmt;
    }

    /**
     * 设置 预计总金额
     * @param preTalAmt 预计总金额
     */
    public void setPreTalAmt(BigDecimal preTalAmt) {
        this.preTalAmt = preTalAmt;
    }

    /**
     * 取得 当期最新利率
     * @return 当期最新利率
     */
    public BigDecimal getNewRate() {
        return newRate;
    }

    /**
     * 设置 当期最新利率
     * @param newRate 当期最新利率
     */
    public void setNewRate(BigDecimal newRate) {
        this.newRate = newRate;
    }

    /**
     * 取得 支付状态 参照数据字典
     * @return 支付状态 参照数据字典
     */
    public String getPayStatus() {
        return payStatus;
    }

    /**
     * 设置 支付状态 参照数据字典
     * @param payStatus 支付状态 参照数据字典
     */
    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    /**
     * 取得 录入方式 参照数据字典
     * @return 录入方式 参照数据字典
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置 录入方式 参照数据字典
     * @param inputType 录入方式 参照数据字典
     */
    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }

    /**
     * 取得 工作流代码
     * @return 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 不收息时应计利息
     * @return 不收息时应计利息
     */
    public BigDecimal getTempIntAmt() {
        return tempIntAmt;
    }

    /**
     * 设置 不收息时应计利息
     * @param tempIntAmt 不收息时应计利息
     */
    public void setTempIntAmt(BigDecimal tempIntAmt) {
        this.tempIntAmt = tempIntAmt;
    }

    /**
     * 取得 不收息时预计收息
     * @return 不收息时预计收息
     */
    public BigDecimal getTempExpInAmt() {
        return tempExpInAmt;
    }

    /**
     * 设置 不收息时预计收息
     * @param tempExpInAmt 不收息时预计收息
     */
    public void setTempExpInAmt(BigDecimal tempExpInAmt) {
        this.tempExpInAmt = tempExpInAmt;
    }
}
