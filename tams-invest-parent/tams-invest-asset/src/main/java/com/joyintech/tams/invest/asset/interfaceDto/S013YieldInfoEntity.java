package com.joyintech.tams.invest.asset.interfaceDto;

import java.math.BigDecimal;
import java.util.Date;

public class S013YieldInfoEntity {
    private String EFF_DATE;//  生效日
    private  String BASIS;//  计息基础
    private BigDecimal INVEST_RATE;//  净投资收益率
    private BigDecimal BUYOUT_RATE;//  买断利率
    private BigDecimal LOAD_RATE;//  借款利率

    public String getEFF_DATE() {
        return EFF_DATE;
    }

    public void setEFF_DATE(String EFF_DATE) {
        this.EFF_DATE = EFF_DATE;
    }

    public String getBASIS() {
        return BASIS;
    }

    public void setBASIS(String BASIS) {
        this.BASIS = BASIS;
    }

    public BigDecimal getINVEST_RATE() {
        return INVEST_RATE;
    }

    public void setINVEST_RATE(BigDecimal INVEST_RATE) {
        this.INVEST_RATE = INVEST_RATE;
    }

    public BigDecimal getBUYOUT_RATE() {
        return BUYOUT_RATE;
    }

    public void setBUYOUT_RATE(BigDecimal BUYOUT_RATE) {
        this.BUYOUT_RATE = BUYOUT_RATE;
    }

    public BigDecimal getLOAD_RATE() {
        return LOAD_RATE;
    }

    public void setLOAD_RATE(BigDecimal LOAD_RATE) {
        this.LOAD_RATE = LOAD_RATE;
    }
}
