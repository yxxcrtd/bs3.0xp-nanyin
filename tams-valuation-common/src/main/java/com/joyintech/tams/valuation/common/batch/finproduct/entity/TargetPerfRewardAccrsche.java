/*
 * 文件名：TargetPerfRewardAccrsche.java
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
 * 业绩报酬计提计划 - 实体类
 * 
 * @author yangjian
 * @version 1.0 2018年5月25日
 * @see TargetPerfRewardAccrsche
 * @since 1.0
 */
public class TargetPerfRewardAccrsche {

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
     * 计提日
     */
    private Date accrDate;

    /**
     * 调整前结束日
     */
    private Date valDate;
    /**
     * 分支序号
     */
    private int branch;
    
    /**
     * 区间开始日
     */
    private Date accrStrDate;
    
    
    /**
     * 区间结束日
     */
    private Date accrEndDate; 
    
    
    
    
    /**
     * 获取 区间开始日
     * @return accrStrDate.
     */
    public Date getAccrStrDate() {
        return accrStrDate;
    }

    
    /**
     * 设置 区间开始日
     * @param accrStrDate 区间开始日
     */
    public void setAccrStrDate(Date accrStrDate) {
        this.accrStrDate = accrStrDate;
    }

    
    /**
     * 获取 区间结束日
     * @return accrEndDate.
     */
    public Date getAccrEndDate() {
        return accrEndDate;
    }

    
    /**
     * 设置 区间结束日
     * @param accrEndDate 区间结束日
     */
    public void setAccrEndDate(Date accrEndDate) {
        this.accrEndDate = accrEndDate;
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
     * 获取 accrDate
     * @return accrDate.
     */
    public Date getAccrDate() {
        return accrDate;
    }
    
    /**
     * 设置 accrDate
     * @param accrDate accrDate
     */
    public void setAccrDate(Date accrDate) {
        this.accrDate = accrDate;
    }
    
    /**
     * 获取 valDate
     * @return valDate.
     */
    public Date getValDate() {
        return valDate;
    }
    
    /**
     * 设置 valDate
     * @param valDate valDate
     */
    public void setValDate(Date valDate) {
        this.valDate = valDate;
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
