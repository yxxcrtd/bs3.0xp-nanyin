/*
 * 文件名：MockFinCashPlanDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2020年4月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.dto.mock.asset;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinCashPlan;

/**
 * 现金流计划表扩展
 * 
 * @author etliu
 * @version 1.0 2020年4月14日
 * @see MockFinCashPlanDto
 * @since 1.0
 */
public class MockFinCashPlanDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 8540299313441649501L;

    /**
     * 现金流计划表Entity
     */
    private FinCashPlan finCashPlan;

    /**
     * 现金流信息Entity
     */
    private FinCashInfo finCashInfo;

    /**
     * 是否已支付
     */
    private String paidOrNot;

    /**
     * 获取 finCashPlan
     * 
     * @return finCashPlan.
     */
    public FinCashPlan getFinCashPlan() {
        return finCashPlan;
    }

    /**
     * 设置 finCashPlan
     * 
     * @param finCashPlan finCashPlan
     */
    public void setFinCashPlan(FinCashPlan finCashPlan) {
        this.finCashPlan = finCashPlan;
    }

    /**
     * 获取 finCashInfo
     * 
     * @return finCashInfo.
     */
    public FinCashInfo getFinCashInfo() {
        return finCashInfo;
    }

    /**
     * 设置 finCashInfo
     * 
     * @param finCashInfo finCashInfo
     */
    public void setFinCashInfo(FinCashInfo finCashInfo) {
        this.finCashInfo = finCashInfo;
    }

    /**
     * 获取 paidOrNot
     * 
     * @return paidOrNot.
     */
    public String getPaidOrNot() {
        return paidOrNot;
    }

    /**
     * 设置 paidOrNot
     * 
     * @param paidOrNot paidOrNot
     */
    public void setPaidOrNot(String paidOrNot) {
        this.paidOrNot = paidOrNot;
    }
}
