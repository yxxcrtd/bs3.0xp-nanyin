package com.joyintech.tams.invest.common.autoperiod.ptlreceinfo.dto;

import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinTradeProduct;

import java.util.Date;
import java.util.List;

public class PtlReceInfo extends FinTradeProduct {

    /**
     * 调整后的计划支付日
     */
    private Date payDate;
    /**
     * 现金流代码
     */
    private String cashId;
    /**
     * 现金流计划表Entity
     */
    private List<FinCashPlan> finCashPlanList;

    /**
     * 现金流计算规则表Entity
     */
    private List<FinCashCalcRule> finCashCalcRuleList;

    /**
     * 获取 调整后的计划支付日
     *
     * @return payDate 调整后的计划支付日
     */
    public Date getPayDate() {
        return this.payDate;
    }

    /**
     * 设置 调整后的计划支付日
     *
     * @param payDate 调整后的计划支付日
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * 获取 现金流计划表Entity
     *
     * @return finCashPlanList 现金流计划表Entity
     */
    public List<FinCashPlan> getFinCashPlanList() {
        return this.finCashPlanList;
    }

    /**
     * 设置 现金流计划表Entity
     *
     * @param finCashPlanList 现金流计划表Entity
     */
    public void setFinCashPlanList(List<FinCashPlan> finCashPlanList) {
        this.finCashPlanList = finCashPlanList;
    }

    /**
     * 获取 现金流计算规则表Entity
     *
     * @return finCashCalcRuleList 现金流计算规则表Entity
     */
    public List<FinCashCalcRule> getFinCashCalcRuleList() {
        return this.finCashCalcRuleList;
    }

    /**
     * 设置 现金流计算规则表Entity
     *
     * @param finCashCalcRuleList 现金流计算规则表Entity
     */
    public void setFinCashCalcRuleList(List<FinCashCalcRule> finCashCalcRuleList) {
        this.finCashCalcRuleList = finCashCalcRuleList;
    }

    /**
     * 获取 现金流代码
     *
     * @return cashId 现金流代码
     */
    public String getCashId() {
        return this.cashId;
    }

    /**
     * 设置 现金流代码
     *
     * @param cashId 现金流代码
     */
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }
}
