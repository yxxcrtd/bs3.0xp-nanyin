package com.joyintech.tams.invest.common.dto.common;

import com.joyintech.tams.common.base.entity.FinCashPlan;

/**
 * @program: tams-invest-parent
 * @description: 现金流计划dto
 * @author: 强成西
 * @create: 2018-11-14 13:49
 **/
public class FinCashPlanForProjIntDto extends FinCashPlan {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -6286747683901068526L;
    /**
     * 是否已付息
     */
    private String isPayed;

    /**
     * 获取 isPayed
     *
     * @return isPayed
     */
    public String getIsPayed() {
        return isPayed;
    }

    /**
     * 设置 isPayed
     *
     * @param isPayed isPayed
     */
    public void setIsPayed(String isPayed) {
        this.isPayed = isPayed;
    }
}
