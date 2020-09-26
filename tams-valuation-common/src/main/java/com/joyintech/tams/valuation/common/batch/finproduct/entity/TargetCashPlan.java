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

import java.math.BigDecimal;
import java.util.Date;

/**
 * 标的现金流计划 - 实体类
 * 
 * @author yangjian
 * @version 1.0 2018年5月25日
 * @see TargetCashPlan
 * @since 1.0
 */
public class TargetCashPlan {

    /**
     * 现金流代码
     */
    private String cashId;

    /**
     * 现金流一级类型
     */
    private String cashType1;

    /**
     * 现金流二级类型
     */
    private String cashType2;
    /**
     * 未调整的计算开始日
     */
    private Date vdateUnadjust;
    /**未调整的计算结束日
     * 
     */
    private Date mdateUnadjust;
    /**
     * 未调整的计划支付日
     */
    private Date payDateUnadjust;
    /**
     * 调整后的计算开始日
     */
    private Date vdate;

    /**
     *调整后的计算结束日
     */
    private Date mdate;
    /**
     * 调整后的计划支付日
     */
    private Date payDate;
    /**
     * 计算周期天数
     */
    private int termdays;
    /**
     * 理论计息年度开始日
     */
    private Date vdateY;
    /**
     * 完整周期开始日
     */
    private Date vdateTerm;
    /**
     * 完整周期结束日
     */
    private Date mdateTerm;
    /**
     * 完整周期计息天数
     */
    private int termdaysTerm;
    /**
     * 计划支付本金
     */
    private BigDecimal prinAmt;
    /**
     * 是否支付利息
     */
    private String isPayInt;
    /**
     * 计划支付利息
     */
    private BigDecimal intAmt;
    /**
     * 计划支付现金流
     */
    private BigDecimal cashAmt;

    /**
     * 现金流单位
     */
    private String cashUnitType;

    /**
     * 计息算法
     */
    private String calcFunction;

    /**
     * 年付息次数
     */
    private int frequency;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 支付方式
     */
    private String payType;

    /**
     * 分支序号
     */
    private int branch;

    /**
     * 计息本金
     */
    private BigDecimal intPrinAmt;
    
    
    
    /**
     * 获取 intPrinAmt
     * @return intPrinAmt.
     */
    public BigDecimal getIntPrinAmt() {
        return intPrinAmt;
    }


    
    /**
     * 设置 intPrinAmt
     * @param intPrinAmt intPrinAmt
     */
    public void setIntPrinAmt(BigDecimal intPrinAmt) {
        this.intPrinAmt = intPrinAmt;
    }


    /**
     * 获取 cashId
     * @return cashId.
     */
    public String getCashId() {
        return cashId;
    }

    
    /**
     * 设置 cashId
     * @param cashId cashId
     */
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }

    
    /**
     * 获取 cashType1
     * @return cashType1.
     */
    public String getCashType1() {
        return cashType1;
    }

    
    /**
     * 设置 cashType1
     * @param cashType1 cashType1
     */
    public void setCashType1(String cashType1) {
        this.cashType1 = cashType1;
    }

    
    /**
     * 获取 cashType2
     * @return cashType2.
     */
    public String getCashType2() {
        return cashType2;
    }

    
    /**
     * 设置 cashType2
     * @param cashType2 cashType2
     */
    public void setCashType2(String cashType2) {
        this.cashType2 = cashType2;
    }

    
    /**
     * 获取 vdateUnadjust
     * @return vdateUnadjust.
     */
    public Date getVdateUnadjust() {
        return vdateUnadjust;
    }

    
    /**
     * 设置 vdateUnadjust
     * @param vdateUnadjust vdateUnadjust
     */
    public void setVdateUnadjust(Date vdateUnadjust) {
        this.vdateUnadjust = vdateUnadjust;
    }

    
    /**
     * 获取 mdateUnadjust
     * @return mdateUnadjust.
     */
    public Date getMdateUnadjust() {
        return mdateUnadjust;
    }

    
    /**
     * 设置 mdateUnadjust
     * @param mdateUnadjust mdateUnadjust
     */
    public void setMdateUnadjust(Date mdateUnadjust) {
        this.mdateUnadjust = mdateUnadjust;
    }

    
    /**
     * 获取 payDateUnadjust
     * @return payDateUnadjust.
     */
    public Date getPayDateUnadjust() {
        return payDateUnadjust;
    }

    
    /**
     * 设置 payDateUnadjust
     * @param payDateUnadjust payDateUnadjust
     */
    public void setPayDateUnadjust(Date payDateUnadjust) {
        this.payDateUnadjust = payDateUnadjust;
    }

    
    /**
     * 获取 vdate
     * @return vdate.
     */
    public Date getVdate() {
        return vdate;
    }

    
    /**
     * 设置 vdate
     * @param vdate vdate
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    
    /**
     * 获取 mdate
     * @return mdate.
     */
    public Date getMdate() {
        return mdate;
    }

    
    /**
     * 设置 mdate
     * @param mdate mdate
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    
    /**
     * 获取 payDate
     * @return payDate.
     */
    public Date getPayDate() {
        return payDate;
    }

    
    /**
     * 设置 payDate
     * @param payDate payDate
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    
    /**
     * 获取 termdays
     * @return termdays.
     */
    public int getTermdays() {
        return termdays;
    }

    
    /**
     * 设置 termdays
     * @param termdays termdays
     */
    public void setTermdays(int termdays) {
        this.termdays = termdays;
    }

    
    /**
     * 获取 vdateY
     * @return vdateY.
     */
    public Date getVdateY() {
        return vdateY;
    }

    
    /**
     * 设置 vdateY
     * @param vdateY vdateY
     */
    public void setVdateY(Date vdateY) {
        this.vdateY = vdateY;
    }

    
    /**
     * 获取 vdateTerm
     * @return vdateTerm.
     */
    public Date getVdateTerm() {
        return vdateTerm;
    }

    
    /**
     * 设置 vdateTerm
     * @param vdateTerm vdateTerm
     */
    public void setVdateTerm(Date vdateTerm) {
        this.vdateTerm = vdateTerm;
    }

    
    /**
     * 获取 mdateTerm
     * @return mdateTerm.
     */
    public Date getMdateTerm() {
        return mdateTerm;
    }

    
    /**
     * 设置 mdateTerm
     * @param mdateTerm mdateTerm
     */
    public void setMdateTerm(Date mdateTerm) {
        this.mdateTerm = mdateTerm;
    }

    
    /**
     * 获取 termdaysTerm
     * @return termdaysTerm.
     */
    public int getTermdaysTerm() {
        return termdaysTerm;
    }

    
    /**
     * 设置 termdaysTerm
     * @param termdaysTerm termdaysTerm
     */
    public void setTermdaysTerm(int termdaysTerm) {
        this.termdaysTerm = termdaysTerm;
    }

    
    /**
     * 获取 prinAmt
     * @return prinAmt.
     */
    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    
    /**
     * 设置 prinAmt
     * @param prinAmt prinAmt
     */
    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    
    /**
     * 获取 isPayInt
     * @return isPayInt.
     */
    public String getIsPayInt() {
        return isPayInt;
    }

    
    /**
     * 设置 isPayInt
     * @param isPayInt isPayInt
     */
    public void setIsPayInt(String isPayInt) {
        this.isPayInt = isPayInt;
    }


    /**
     * 获取 intAmt
     * @return intAmt.
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }

    
    /**
     * 设置 intAmt
     * @param intAmt intAmt
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    
    /**
     * 获取 cashAmt
     * @return cashAmt.
     */
    public BigDecimal getCashAmt() {
        return cashAmt;
    }

    
    /**
     * 设置 cashAmt
     * @param cashAmt cashAmt
     */
    public void setCashAmt(BigDecimal cashAmt) {
        this.cashAmt = cashAmt;
    }

    
    /**
     * 获取 cashUnitType
     * @return cashUnitType.
     */
    public String getCashUnitType() {
        return cashUnitType;
    }

    
    /**
     * 设置 cashUnitType
     * @param cashUnitType cashUnitType
     */
    public void setCashUnitType(String cashUnitType) {
        this.cashUnitType = cashUnitType;
    }

    
    /**
     * 获取 calcFunction
     * @return calcFunction.
     */
    public String getCalcFunction() {
        return calcFunction;
    }

    
    /**
     * 设置 calcFunction
     * @param calcFunction calcFunction
     */
    public void setCalcFunction(String calcFunction) {
        this.calcFunction = calcFunction;
    }

    
    /**
     * 获取 frequency
     * @return frequency.
     */
    public int getFrequency() {
        return frequency;
    }

    
    /**
     * 设置 frequency
     * @param frequency frequency
     */
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    
    /**
     * 获取 finprodId
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    
    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    
    /**
     * 获取 finprodType
     * @return finprodType.
     */
    public String getFinprodType() {
        return finprodType;
    }

    
    /**
     * 设置 finprodType
     * @param finprodType finprodType
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    
    /**
     * 获取 payType
     * @return payType.
     */
    public String getPayType() {
        return payType;
    }

    
    /**
     * 设置 payType
     * @param payType payType
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    
    /**
     * 获取 branch
     * @return branch.
     */
    public int getBranch() {
        return branch;
    }

    
    /**
     * 设置 branch
     * @param branch branch
     */
    public void setBranch(int  branch) {
        this.branch = branch;
    }

 

   

}
