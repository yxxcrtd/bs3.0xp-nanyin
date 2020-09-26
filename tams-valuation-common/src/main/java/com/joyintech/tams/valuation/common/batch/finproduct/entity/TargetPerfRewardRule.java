/*
 * 文件名：TargetPerfRewardRule.java
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
 * 业绩报酬计提规则 - 实体类
 * 
 * @author yangjian
 * @version 1.0 2018年5月25日
 * @see TargetPerfRewardRule
 * @since 1.0
 */
public class TargetPerfRewardRule {

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型
     */
    private String finprodType;

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
     * 分支序号
     */
    private int branch;
    /**
     * 生效日期
     */
    private Date effDate;
    
    /**
     * 计提类型
     */
    private String accrType;
    
    /**
     * 计提频率
     */
    private int accrCycle;
    
    /**
     * 计提频率单位
     */
    private String accrCycleUnit;
    
    /**
     * 计提频率类型
     */
    private String accrDayType;
    
    /**
     * 计提频率值
     */
    private String accrDayValue;
    
    /**
     * 计提营业日准则
     */
    private String accrTdayRule;
    
    /**
     * 首次计提日
     */
    private Date fAccrDate;

    /**
     * 首次结束日
     */
    private Date eAccrDate;

    /**
     * 首次计费基数类型
     */
    private String firstBaseType;

    
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
     * 获取 accrType
     * @return accrType.
     */
    public String getAccrType() {
        return accrType;
    }

    
    /**
     * 设置 accrType
     * @param accrType accrType
     */
    public void setAccrType(String accrType) {
        this.accrType = accrType;
    }

    
    /**
     * 获取 accrCycle
     * @return accrCycle.
     */
    public int getAccrCycle() {
        return accrCycle;
    }

    
    /**
     * 设置 accrCycle
     * @param accrCycle accrCycle
     */
    public void setAccrCycle(int accrCycle) {
        this.accrCycle = accrCycle;
    }

    
    /**
     * 获取 accrCycleUnit
     * @return accrCycleUnit.
     */
    public String getAccrCycleUnit() {
        return accrCycleUnit;
    }

    
    /**
     * 设置 accrCycleUnit
     * @param accrCycleUnit accrCycleUnit
     */
    public void setAccrCycleUnit(String accrCycleUnit) {
        this.accrCycleUnit = accrCycleUnit;
    }

    
    /**
     * 获取 accrDayType
     * @return accrDayType.
     */
    public String getAccrDayType() {
        return accrDayType;
    }

    
    /**
     * 设置 accrDayType
     * @param accrDayType accrDayType
     */
    public void setAccrDayType(String accrDayType) {
        this.accrDayType = accrDayType;
    }

    
    /**
     * 获取 accrDayValue
     * @return accrDayValue.
     */
    public String getAccrDayValue() {
        return accrDayValue;
    }

    
    /**
     * 设置 accrDayValue
     * @param accrDayValue accrDayValue
     */
    public void setAccrDayValue(String accrDayValue) {
        this.accrDayValue = accrDayValue;
    }

    
    /**
     * 获取 accrTdayRule
     * @return accrTdayRule.
     */
    public String getAccrTdayRule() {
        return accrTdayRule;
    }

    
    /**
     * 设置 accrTdayRule
     * @param accrTdayRule accrTdayRule
     */
    public void setAccrTdayRule(String accrTdayRule) {
        this.accrTdayRule = accrTdayRule;
    }

    
    /**
     * 获取 fAccrDate
     * @return fAccrDate.
     */
    public Date getfAccrDate() {
        return fAccrDate;
    }

    
    /**
     * 设置 fAccrDate
     * @param fAccrDate fAccrDate
     */
    public void setfAccrDate(Date fAccrDate) {
        this.fAccrDate = fAccrDate;
    }

    /**
     * 获取 eAccrDate
     * @return eAccrDate.
     */
    public Date geteAccrDate() {
        return eAccrDate;
    }

    /**
     * 设置 eAccrDate
     * @param eAccrDate eAccrDate
     */
    public void seteAccrDate(Date eAccrDate) {
        this.eAccrDate = eAccrDate;
    }

    /**
     * 获取 firstBaseType
     * @return firstBaseType.
     */
    public String getFirstBaseType() {
        return firstBaseType;
    }

    /**
     * 设置 firstBaseType
     * @param firstBaseType firstBaseType
     */
    public void setFirstBaseType(String firstBaseType) {
        this.firstBaseType = firstBaseType;
    }
}
