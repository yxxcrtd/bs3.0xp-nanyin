/*
 * 文件名：TargetCashPayRule.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.batch.finproduct.entity;

import java.util.Date;

/**
 * 标的现金流支付规则 - 实体类
 * 
 * @author yangjian
 * @version 1.0 2018年5月25日
 * @see TargetCashPayRule
 * @since 1.0
 */
public class TargetCashPayRule {

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
     * 生效日期
     */
    private Date effDate;

    /**
     * 支付频率
     */
    private String payCycle;

//    /**
//     * 营业日准则
//     */
//    private String tdayRule;

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
     * 现金流单位
     */
    private String cashUnitType;

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
     * 获取 effDate
     * @return effDate.
     */
    public Date getEffDate() {
        return effDate;
    }
    
    /**
     * 设置 effDate
     * @param effDate effDate
     */
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
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
    
//    /**
//     * 获取 tdayRule
//     * @return tdayRule.
//     */
//    public String getTdayRule() {
//        return tdayRule;
//    }
//    
//    /**
//     * 设置 tdayRule
//     * @param tdayRule tdayRule
//     */
//    public void setTdayRule(String tdayRule) {
//        this.tdayRule = tdayRule;
//    }
    
    public String getEndTdayRule() {
		return endTdayRule;
	}

	public void setEndTdayRule(String endTdayRule) {
		this.endTdayRule = endTdayRule;
	}

	public String getPayTdayRule() {
		return payTdayRule;
	}

	public void setPayTdayRule(String payTdayRule) {
		this.payTdayRule = payTdayRule;
	}
    
    /**
     * 获取 calcRule
     * @return calcRule.
     */
    public String getCalcRule() {
        return calcRule;
    }
    
    /**
     * 设置 calcRule
     * @param calcRule calcRule
     */
    public void setCalcRule(String calcRule) {
        this.calcRule = calcRule;
    }
    
    /**
     * 获取 firstPayDate
     * @return firstPayDate.
     */
    public Date getFirstPayDate() {
        return firstPayDate;
    }
    
    /**
     * 设置 firstPayDate
     * @param firstPayDate firstPayDate
     */
    public void setFirstPayDate(Date firstPayDate) {
        this.firstPayDate = firstPayDate;
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
    public void setBranch(int branch) {
        this.branch = branch;
    }

}
