package com.joyintech.tams.invest.asset.interfaceDto;

import com.joyintech.framework.base.BaseEntity;

import java.util.List;

/**
 * 计划类资产利率信息 DTO
 */
public class S018Dto {
    private  String FINPROD_ID; //资产代码
    private  String BRANCH; //期次号
    private  String FINPROD_MARKET_ID;//市场代码
    private  String FINPROD_NAME;//资产名称
    private List<S013YieldInfoEntity> YIELD_INFO;//利率信息

    public String getFINPROD_ID() {
        return FINPROD_ID;
    }

    public void setFINPROD_ID(String FINPROD_ID) {
        this.FINPROD_ID = FINPROD_ID;
    }

    public String getBRANCH() {
        return BRANCH;
    }

    public void setBRANCH(String BRANCH) {
        this.BRANCH = BRANCH;
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

    public List<S013YieldInfoEntity> getYIELD_INFO() {
        return YIELD_INFO;
    }

    public void setYIELD_INFO(List<S013YieldInfoEntity> YIELD_INFO) {
        this.YIELD_INFO = YIELD_INFO;
    }
}
