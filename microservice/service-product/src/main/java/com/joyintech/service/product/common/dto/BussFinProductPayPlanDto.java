/*
 * 文件名：BussFinProductPayPlanDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.common.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinCashPlan;

/**
 * 产品付息计划预览参数
 * 
 * @author 何阳阳
 * @version 1.0 2018年9月20日
 * @see BussFinProductPayPlanDto
 * @since 1.0
 */
public class BussFinProductPayPlanDto extends FinCashPayRule {

    /**
     */
    private static final long serialVersionUID = -7293996269150791394L;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 清盘日
     */
    private Date winupDate;

    /**
     * 交易日历代码
     */
    private String calendarId;

    /**
     * 付息周期单位
     */
    private String payCycleUnit;

    /**
     * 
     */
    private String entityName;

    /**
     * 现金流计划
     */
    private List<FinCashPlan> cashPlanList;

    /**
     * 获取 vdate
     * 
     * @return vdate.
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 vdate
     * 
     * @param vdate vdate
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 获取 到期日
     * 
     * @return mdate.
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 到期日
     * 
     * @param mdate 到期日
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 获取 calendarId
     * 
     * @return calendarId.
     */
    public String getCalendarId() {
        return calendarId;
    }

    /**
     * 设置 calendarId
     * 
     * @param calendarId calendarId
     */
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    /**
     * 获取 payCycleUnit
     * 
     * @return payCycleUnit.
     */
    public String getPayCycleUnit() {
        return payCycleUnit;
    }

    /**
     * 设置 payCycleUnit
     * 
     * @param payCycleUnit payCycleUnit
     */
    public void setPayCycleUnit(String payCycleUnit) {
        this.payCycleUnit = payCycleUnit;
    }

    /**
     * 获取 entityName
     * 
     * @return entityName.
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * 设置 entityName
     * 
     * @param entityName entityName
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    /**
     * 获取 winupDate
     * 
     * @return winupDate.
     */
    public Date getWinupDate() {
        return winupDate;
    }

    /**
     * 设置 winupDate
     * 
     * @param winupDate winupDate
     */
    public void setWinupDate(Date winupDate) {
        this.winupDate = winupDate;
    }

    /**
     * 获取 cashPlanList
     * 
     * @return cashPlanList.
     */
    public List<FinCashPlan> getCashPlanList() {
        return cashPlanList;
    }

    /**
     * 设置 cashPlanList
     * 
     * @param cashPlanList cashPlanList
     */
    public void setCashPlanList(List<FinCashPlan> cashPlanList) {
        this.cashPlanList = cashPlanList;
    }

}
