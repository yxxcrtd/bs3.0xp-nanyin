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

import com.joyintech.tams.common.base.entity.FinCashPlan;

/**
 * 标的现金流计划 - 实体类
 * 
 * @author chengzhong
 * @version 1.0 2018年5月25日
 * @see FinCashPlanEx
 * @since 1.0
 */
public class FinCashPlanEx extends FinCashPlan{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 7767220281423716240L;
    /**
     * 现金流二级类型，本金、利息、托管费、固定管理费、浮动管理费等
     */
    private String cashType1;

    
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
    public void setCashType2(String cashType1) {
        this.cashType1 = cashType1;
    }
    
    
}
