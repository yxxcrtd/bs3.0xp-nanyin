/*
 * 文件名：BaseFinProductCashInputDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：刘二通
 * 修改时间：2018年11月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinCashResetPlan;
import com.joyintech.tams.common.base.entity.FinRateInfo;

/**
 * 金融产品现金流相关信息
 *
 * @author 刘二通
 * @version 1.0 2018年11月2日
 * @see BaseFinProductCashInputDto
 * @since 1.0
 */
public class BaseFinProductCashInputDto {

    /**
     * List<现金流信息Entity>
     */
    private List<FinCashInfo> finCashInfoList;

    /**
     * List<现金流支付规则表Entity>
     */
    private List<FinCashPayRule> finCashPayRuleList;

    /**
     * List<现金流计算规则表Entity>
     */
    private List<FinCashCalcRule> finCashCalcRuleList;

    /**
     * List<现金流利率信息表Entity>
     */
    private List<FinRateInfo> finRateInfoList;

    /**
     * List<现金流计划表Entity>
     */
    private List<FinCashPlan> finCashPlanList;

    /**
     * List<利率重置计划表Entity>
     */
    private List<FinCashResetPlan> finCashResetPlanList;
    
    /**
     * 获取 finCashInfoList
     *
     * @return finCashInfoList.
     */
    public List<FinCashInfo> getFinCashInfoList() {
        return finCashInfoList;
    }

    /**
     * 设置 finCashInfoList
     *
     * @param finCashInfoList finCashInfoList
     */
    public void setFinCashInfoList(List<FinCashInfo> finCashInfoList) {
        this.finCashInfoList = finCashInfoList;
    }

    /**
     * 获取 finCashPayRuleList
     *
     * @return finCashPayRuleList.
     */
    public List<FinCashPayRule> getFinCashPayRuleList() {
        return finCashPayRuleList;
    }

    /**
     * 设置 finCashPayRuleList
     *
     * @param finCashPayRuleList finCashPayRuleList
     */
    public void setFinCashPayRuleList(List<FinCashPayRule> finCashPayRuleList) {
        this.finCashPayRuleList = finCashPayRuleList;
    }

    /**
     * 获取 finCashCalcRuleList
     *
     * @return finCashCalcRuleList.
     */
    public List<FinCashCalcRule> getFinCashCalcRuleList() {
        return finCashCalcRuleList;
    }

    /**
     * 设置 finCashCalcRuleList
     *
     * @param finCashCalcRuleList finCashCalcRuleList
     */
    public void setFinCashCalcRuleList(List<FinCashCalcRule> finCashCalcRuleList) {
        this.finCashCalcRuleList = finCashCalcRuleList;
    }

    /**
     * 获取 finRateInfoList
     *
     * @return finRateInfoList.
     */
    public List<FinRateInfo> getFinRateInfoList() {
        return finRateInfoList;
    }

    /**
     * 设置 finRateInfoList
     *
     * @param finRateInfoList finRateInfoList
     */
    public void setFinRateInfoList(List<FinRateInfo> finRateInfoList) {
        this.finRateInfoList = finRateInfoList;
    }

    /**
     * 获取 finCashPlanList
     *
     * @return finCashPlanList.
     */
    public List<FinCashPlan> getFinCashPlanList() {
        return finCashPlanList;
    }

    /**
     * 设置 finCashPlanList
     *
     * @param finCashPlanList finCashPlanList
     */
    public void setFinCashPlanList(List<FinCashPlan> finCashPlanList) {
        this.finCashPlanList = finCashPlanList;
    }

	/**
	 * 获取 finCashResetPlanList
	 * @return finCashResetPlanList.
	 */
	public List<FinCashResetPlan> getFinCashResetPlanList() {
		return finCashResetPlanList;
	}

	/**
	 * 设置 finCashResetPlanList
	 * @param finCashResetPlanList finCashResetPlanList
	 */
	public void setFinCashResetPlanList(List<FinCashResetPlan> finCashResetPlanList) {
		this.finCashResetPlanList = finCashResetPlanList;
	}

}
