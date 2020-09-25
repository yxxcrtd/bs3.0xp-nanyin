/*
 * 文件名：FinCashPlanDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年10月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.tams.invest.entrust.etrinvestbondinfo.dto;

import com.joyintech.tams.common.base.entity.FinCashPlan;

public class FinCashPlanDto extends FinCashPlan {
    private String cashType1;

    /**
     * 获取 cashType1
     *
     * @return cashType1
     */
    public String getCashType1() {
        return cashType1;
    }

    /**
     * 设置 cashType1
     *
     * @param cashType1 cashType1
     */
    public void setCashType1(String cashType1) {
        this.cashType1 = cashType1;
    }
}
