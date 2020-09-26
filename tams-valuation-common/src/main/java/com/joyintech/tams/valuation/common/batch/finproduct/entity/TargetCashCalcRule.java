/*
 * 文件名：TargetCashCalcRule.java
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
 * 标的现金流计算规则 - 实体类
 * 
 * @author yangjian
 * @version 1.0 2018年5月25日
 * @see TargetCashCalcRule
 * @since 1.0
 */
public class TargetCashCalcRule {

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
     * 计算类型
     */
    private String calcType;

//    /**
//     * 浮动利率编号
//     */
//    <!-- 	      	现金流计算规则表浮动利率编号删除   by huangjian 20190103 -->
//    private String intRateId;

    /**
     * 生效利率
     */
    private BigDecimal yield;

    /**
     * 计息基础
     */
    private String basis;

    /**
     * 基数类型
     */
    private String baseType;
    /**
     * 基数日期类型
     */
    private String baseDateType;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 备注
     */
    private String remark;
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
     * 获取 calcType
     * @return calcType.
     */
    public String getCalcType() {
        return calcType;
    }
    
    /**
     * 设置 calcType
     * @param calcType calcType
     */
    public void setCalcType(String calcType) {
        this.calcType = calcType;
    }
    
//    /**
//     * 获取 intRateId
//     * @return intRateId.
//     */
//    public String getIntRateId() {
//        return intRateId;
//    }
//    <!-- 	      	现金流计算规则表浮动利率编号删除   by huangjian 20190103 -->
//    /**
//     * 设置 intRateId
//     * @param intRateId intRateId
//     */
//    public void setIntRateId(String intRateId) {
//        this.intRateId = intRateId;
//    }
    
    /**
     * 获取 yield
     * @return yield.
     */
    public BigDecimal getYield() {
        return yield;
    }
    
    /**
     * 设置 yield
     * @param yield yield
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
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
     * 获取 baseType
     * @return baseType.
     */
    public String getBaseType() {
        return baseType;
    }
    
    /**
     * 设置 baseType
     * @param baseType baseType
     */
    public void setBaseType(String baseType) {
        this.baseType = baseType;
    }
    
    /**
     * 获取 baseDateType
     * @return baseDateType.
     */
    public String getBaseDateType() {
        return baseDateType;
    }
    
    /**
     * 设置 baseDateType
     * @param baseDateType baseDateType
     */
    public void setBaseDateType(String baseDateType) {
        this.baseDateType = baseDateType;
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
     * 获取 remark
     * @return remark.
     */
    public String getRemark() {
        return remark;
    }
    
    /**
     * 设置 remark
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
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
