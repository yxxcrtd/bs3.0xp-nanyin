/*
 * 文件名：BussFinProductBonusPlanDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.component.business.prod.validator.BussFinProductBonusPlanValidator;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.ValidLogic;

/**
 * 产品分红计划
 * 
 * @author 何阳阳
 * @version 1.0 2018年9月20日
 * @see BussFinProductBonusPlanDto
 * @since 1.0
 */
@ValidLogic(targetClass = BussFinProductBonusPlanValidator.class)
public class BussFinProductBonusPlanDto extends FinCashPayRule {

    /**
     */
    private static final long serialVersionUID = -7293996269150791398L;
    
    /**
     * 主画面传入的pageId
     */
    private String pageId;
    
    /**
     * 分红周期单位
     */
    private String payCycleUnit;
    
    /**
     * 起息日
     */
//    @NotEmpty(fieldName = "BussFinProductPayPlan.other.vdate")
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
//    @NotEmpty(fieldName = "BussFinProductPayPlan.other.calendarId")
    private String calendarId;

    

    /**
     * 
     */
    private String entityName;

    /**
     * 现金流计划
     */
    private List<FinCashPlan> cashPlanList;
    
    /**
     * 收益类型
     */
    private String profitType;
    /**
     * 获取 pageId
     * @return pageId.
     */
    public String getPageId() {
        return pageId;
    }

    
    /**
     * 设置 pageId
     * @param pageId pageId
     */
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
    
    /**
     * 获取 payCycleUnit
     * @return payCycleUnit.
     */
    public String getPayCycleUnit() {
        return payCycleUnit;
    }
    
    /**
     * 设置 payCycleUnit
     * @param payCycleUnit payCycleUnit
     */
    public void setPayCycleUnit(String payCycleUnit) {
        this.payCycleUnit = payCycleUnit;
    }


	public Date getVdate() {
		return vdate;
	}


	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}


	public Date getMdate() {
		return mdate;
	}


	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}


	public Date getWinupDate() {
		return winupDate;
	}


	public void setWinupDate(Date winupDate) {
		this.winupDate = winupDate;
	}


	public String getCalendarId() {
		return calendarId;
	}


	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}


	public String getEntityName() {
		return entityName;
	}


	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}


	public List<FinCashPlan> getCashPlanList() {
		return cashPlanList;
	}


	public void setCashPlanList(List<FinCashPlan> cashPlanList) {
		this.cashPlanList = cashPlanList;
	}
	/**
     * {@inheritDoc}
     */
    @Override
    @Length(fieldName="BussFinProductBonusPlan.payCycleVal", max = 10)
    public Long getPayCycleVal() {
        return super.getPayCycleVal();
    }


    
    /**
     * 获取 profitType
     * @return profitType.
     */
    public String getProfitType() {
        return profitType;
    }


    
    /**
     * 设置 profitType
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }
    
}
