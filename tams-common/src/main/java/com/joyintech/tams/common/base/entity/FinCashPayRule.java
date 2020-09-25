package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;

import java.math.BigDecimal;
import java.util.Date;

public class FinCashPayRule extends FinCashPayRuleKey {
    /**
     * 支付频率值
     */
    private Long payCycleVal;

    /**
     * 支付频率
     */
    private String payCycle;

    /**
     * 日期类型，第几工作日、第几自然日、周几、几号等
     */
    private String dayType;

    /**
     * 日期值，可多选，逗号分隔
     */
    private String dayValue;

    /**
     * 截止日营业日准则
     */
    private String endTdayRule;

    /**
     * 支付日营业日准则
     */
    private String payTdayRule;

    /**
     * 推算方式
     */
    private String calcRule;

    /**
     * 首次支付日
     */
    private Date firstPayDate;

    /**
     * 现金流单位，百元法、总额法
     */
    private String cashUnitType;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型（估值核算），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    private String finprodType;

    /**
     * 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    private String finprodType2;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 支付方式，现金、红利再投等
     */
    private String payType;

    /**
     * 备注
     */
    private String remark;

    /**

/**
     * 支付月，可多选，逗号分隔
     */
    private String payMonth;

    /**
     * 是否还本不带息
     */
    private String isPayIntFlag;

    /**
     * 还本不带息的利息金额
     */
    private BigDecimal repayWithoutInt;


    /**
     * 取得 支付频率值
     * @return 支付频率值
     */
    public Long getPayCycleVal() {
        return payCycleVal;
    }

    /**
     * 设置 支付频率值
     * @param payCycleVal 支付频率值
     */
    public void setPayCycleVal(Long payCycleVal) {
        this.payCycleVal = payCycleVal;
    }

    /**
     * 取得 支付频率
     * @return 支付频率
     */
    public String getPayCycle() {
        return payCycle;
    }

    /**
     * 设置 支付频率
     * @param payCycle 支付频率
     */
    public void setPayCycle(String payCycle) {
        this.payCycle = payCycle == null ? null : payCycle.trim();
    }

    /**
     * 取得 日期类型，第几工作日、第几自然日、周几、几号等
     * @return 日期类型，第几工作日、第几自然日、周几、几号等
     */
    public String getDayType() {
        return dayType;
    }

    /**
     * 设置 日期类型，第几工作日、第几自然日、周几、几号等
     * @param dayType 日期类型，第几工作日、第几自然日、周几、几号等
     */
    public void setDayType(String dayType) {
        this.dayType = dayType == null ? null : dayType.trim();
    }

    /**
     * @return 日期值，可多选，逗号分隔
     */
    public String getDayValue() {
        return dayValue;
    }

    /**
     * @param dayValue 日期值，可多选，逗号分隔
     */
    public void setDayValue(String dayValue) {
        this.dayValue = dayValue == null ? null : dayValue.trim();
    }

    /**
     * 取得 截止日营业日准则
     * @return 截止日营业日准则
     */
    public String getEndTdayRule() {
        return endTdayRule;
    }

    /**
     * 设置 截止日营业日准则
     * @param endTdayRule 截止日营业日准则
     */
    public void setEndTdayRule(String endTdayRule) {
        this.endTdayRule = endTdayRule == null ? null : endTdayRule.trim();
    }

    /**
     * 取得 支付日营业日准则
     * @return 支付日营业日准则
     */
    public String getPayTdayRule() {
        return payTdayRule;
    }

    /**
     * 设置 支付日营业日准则
     * @param payTdayRule 支付日营业日准则
     */
    public void setPayTdayRule(String payTdayRule) {
        this.payTdayRule = payTdayRule == null ? null : payTdayRule.trim();
    }

    /**
     * 取得 推算方式
     * @return 推算方式
     */
    public String getCalcRule() {
        return calcRule;
    }

    /**
     * 设置 推算方式
     * @param calcRule 推算方式
     */
    public void setCalcRule(String calcRule) {
        this.calcRule = calcRule == null ? null : calcRule.trim();
    }

    /**
     * 取得 首次支付日
     * @return 首次支付日
     */
    public Date getFirstPayDate() {
        return firstPayDate;
    }

    /**
     * 设置 首次支付日
     * @param firstPayDate 首次支付日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setFirstPayDate(Date firstPayDate) {
        this.firstPayDate = firstPayDate;
    }

    /**
     * 取得 现金流单位，百元法、总额法
     * @return 现金流单位，百元法、总额法
     */
    public String getCashUnitType() {
        return cashUnitType;
    }

    /**
     * 设置 现金流单位，百元法、总额法
     * @param cashUnitType 现金流单位，百元法、总额法
     */
    public void setCashUnitType(String cashUnitType) {
        this.cashUnitType = cashUnitType == null ? null : cashUnitType.trim();
    }

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 金融产品类型（估值核算），债券、理财产品、基金、理财产品模板、理财产品分层
     * @return 金融产品类型（估值核算），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 金融产品类型（估值核算），债券、理财产品、基金、理财产品模板、理财产品分层
     * @param finprodType 金融产品类型（估值核算），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 取得 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     * @return 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    public String getFinprodType2() {
        return finprodType2;
    }

    /**
     * 设置 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     * @param finprodType2 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2 == null ? null : finprodType2.trim();
    }

    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 取得 支付方式，现金、红利再投等
     * @return 支付方式，现金、红利再投等
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置 支付方式，现金、红利再投等
     * @param payType 支付方式，现金、红利再投等
     */
    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
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
     * 获取 isPayIntFlag
     *
     * @return isPayIntFlag
     */
    public String getIsPayIntFlag() {
        return isPayIntFlag;
    }

    /**
     * 设置 isPayIntFlag
     *
     * @param isPayIntFlag isPayIntFlag
     */
    public void setIsPayIntFlag(String isPayIntFlag) {
        this.isPayIntFlag = isPayIntFlag;
    }

    /**
     * 获取 repayWithoutInt
     *
     * @return repayWithoutInt
     */
    public BigDecimal getRepayWithoutInt() {
        return repayWithoutInt;
    }

    /**
     * 设置 repayWithoutInt
     *
     * @param repayWithoutInt repayWithoutInt
     */
    public void setRepayWithoutInt(BigDecimal repayWithoutInt) {
        this.repayWithoutInt = repayWithoutInt;
    }

    public String getPayMonth() {
        return payMonth;
    }

    public void setPayMonth(String payMonth) {
        this.payMonth = payMonth;
    }
}