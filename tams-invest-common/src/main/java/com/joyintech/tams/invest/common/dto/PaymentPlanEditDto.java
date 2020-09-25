package com.joyintech.tams.invest.common.dto;

import com.joyintech.tams.common.base.entity.FinCashPlan;

import java.util.List;

/**
 * 利率互换付息计划维护DTO
 *
 * @author xkni
 * @version 1.0 20190816
 * @see BaseFinProductCashInputDto
 * @since 1.0
 */
public class PaymentPlanEditDto {

    /**
     * 固息端 List<现金流计划表Entity>
     */
    private List<FinCashPlan> fixedFinCashPlanList;
    /**
     *浮息端 List<现金流计划表Entity>
     */
    private List<FinCashPlan> floatFinCashPlanList;
    /**
     * 固息端 现金流代码
     */
    private String fixedFinCashCode;

    /**
     *浮息端 现金流代码
     */
    private String floatFinCashCode;


    public List<FinCashPlan> getFixedFinCashPlanList() {
        return fixedFinCashPlanList;
    }

    public void setFixedFinCashPlanList(List<FinCashPlan> fixedFinCashPlanList) {
        this.fixedFinCashPlanList = fixedFinCashPlanList;
    }

    public List<FinCashPlan> getFloatFinCashPlanList() {
        return floatFinCashPlanList;
    }

    public void setFloatFinCashPlanList(List<FinCashPlan> floatFinCashPlanList) {
        this.floatFinCashPlanList = floatFinCashPlanList;
    }

    public String getFixedFinCashCode() {
        return fixedFinCashCode;
    }

    public void setFixedFinCashCode(String fixedFinCashCode) {
        this.fixedFinCashCode = fixedFinCashCode;
    }

    public String getFloatFinCashCode() {
        return floatFinCashCode;
    }

    public void setFloatFinCashCode(String floatFinCashCode) {
        this.floatFinCashCode = floatFinCashCode;
    }

}
