/*
 * 文件名：FinCashPlanDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年06月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.trade.bondlend.dto;

import com.joyintech.tams.common.base.entity.FinCashPlan;

public class FinCashPlanDto extends FinCashPlan {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 4054124210919173171L;
    
    // 现金流一级类型
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
