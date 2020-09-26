/*
 * 文件名：InterestBasisDaysParameter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.common.calculation.entity;

import java.util.Date;

public class InterestBasisDaysParameter {
    /**
     * 付息开始日，A/A 必须输入
     */
    private Date payDateBegin;
    
    /**
     * 资产起息日，A/A 必须输入
     */
    private Date vdate;

    /**
     * 资产到期日，A/A 必须输入
     */
    private Date mdate;

    /**
     * 首次付息日，A/A且 推算方法 = "首次付息日"时，必须输入
     */
    private Date firstPayDate;

    /**
     * 计息基础，必须输入
     */
    private String basis;

    /**
     * 推算方法，必须输入
     */
    private String calMethod;
    
    public InterestBasisDaysParameter(String 计息基础){
        this.basis = 计息基础;
    }

    public InterestBasisDaysParameter(Date 付息开始日, Date 资产起息日, Date 资产到期日, Date 首次付息日, String 计息基础, String 推算方法) {
        this.payDateBegin = 付息开始日;
        this.vdate = 资产起息日;
        this.mdate = 资产到期日;
        this.firstPayDate = 首次付息日;
        this.basis = 计息基础;
        this.calMethod = 推算方法;
    }

    
    public Date getPayDateBegin() {
        return payDateBegin;
    }

    
    public void setPayDateBegin(Date 付息开始日) {
        this.payDateBegin = 付息开始日;
    }

    
    public Date getVdate() {
        return vdate;
    }

    
    public void setVdate(Date 资产起息日) {
        this.vdate = 资产起息日;
    }

    
    public Date getMdate() {
        return mdate;
    }

    
    public void setMdate(Date 资产到期日) {
        this.mdate = 资产到期日;
    }

    
    public Date getFirstPayDate() {
        return firstPayDate;
    }

    
    public void setFirstPayDate(Date 首次付息日) {
        this.firstPayDate = 首次付息日;
    }

    
    public String getBasis() {
        return basis;
    }

    
    public void setBasis(String 计息基础) {
        this.basis = 计息基础;
    }

    
    public String getCalMethod() {
        return calMethod;
    }

    
    public void setCalMethod(String 推算方法) {
        this.calMethod = 推算方法;
    }
}
