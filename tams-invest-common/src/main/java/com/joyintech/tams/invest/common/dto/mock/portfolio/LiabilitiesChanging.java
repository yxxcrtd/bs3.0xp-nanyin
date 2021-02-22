package com.joyintech.tams.invest.common.dto.mock.portfolio;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;

/**
 * @program: tams-disclosure-busicommon
 * @description: 负债变化 value
 * @author: 强成西
 * @create: 2020-04-16 22:07
 **/
public class LiabilitiesChanging extends BaseEntity {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -1344131342462796345L;
    /**
     * 实收资本
     */
    private BigDecimal actualCapital;
    /**
     * 客户收益
     */
    private BigDecimal customProfit;
    /**
     * 清算款
     */
    private BigDecimal cashFund;

    /**
     * 获取 actualCapital
     *
     * @return actualCapital
     */
    public BigDecimal getActualCapital() {
        return actualCapital;
    }

    /**
     * 设置 actualCapital
     *
     * @param actualCapital actualCapital
     */
    public void setActualCapital(BigDecimal actualCapital) {
        this.actualCapital = actualCapital;
    }

    /**
     * 获取 customProfit
     *
     * @return customProfit
     */
    public BigDecimal getCustomProfit() {
        return customProfit;
    }

    /**
     * 设置 customProfit
     *
     * @param customProfit customProfit
     */
    public void setCustomProfit(BigDecimal customProfit) {
        this.customProfit = customProfit;
    }

    /**
     * 获取 cashFund
     *
     * @return cashFund
     */
    public BigDecimal getCashFund() {
        return cashFund;
    }

    /**
     * 设置 cashFund
     *
     * @param cashFund cashFund
     */
    public void setCashFund(BigDecimal cashFund) {
        this.cashFund = cashFund;
    }
}
