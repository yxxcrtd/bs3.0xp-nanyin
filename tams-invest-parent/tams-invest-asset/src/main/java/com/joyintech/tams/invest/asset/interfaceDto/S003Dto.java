package com.joyintech.tams.invest.asset.interfaceDto;

import com.joyintech.framework.base.BaseEntity;

import java.util.List;

/**
 * 债券现金流计划 DTO
 */
public class S003Dto  {
    private  String FINPROD_ID; //资产代码
    private  String FINPROD_MARKET_ID;//市场代码
    private  String FINPROD_NAME;//资产名称
    private List<S003CashPlanEntity> CASH_PLAN;//现金流计划

    public String getFINPROD_ID() {
        return FINPROD_ID;
    }

    public void setFINPROD_ID(String FINPROD_ID) {
        this.FINPROD_ID = FINPROD_ID;
    }

    public String getFINPROD_MARKET_ID() {
        return FINPROD_MARKET_ID;
    }

    public void setFINPROD_MARKET_ID(String FINPROD_MARKET_ID) {
        this.FINPROD_MARKET_ID = FINPROD_MARKET_ID;
    }

    public String getFINPROD_NAME() {
        return FINPROD_NAME;
    }

    public void setFINPROD_NAME(String FINPROD_NAME) {
        this.FINPROD_NAME = FINPROD_NAME;
    }

    public List<S003CashPlanEntity> getCASH_PLAN() {
        return CASH_PLAN;
    }

    public void setCASH_PLAN(List<S003CashPlanEntity> CASH_PLAN) {
        this.CASH_PLAN = CASH_PLAN;
    }
}
