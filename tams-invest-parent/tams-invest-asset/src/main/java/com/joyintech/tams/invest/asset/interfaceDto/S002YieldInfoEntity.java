package com.joyintech.tams.invest.asset.interfaceDto;

import java.math.BigDecimal;
import java.util.Date;

public class S002YieldInfoEntity {
    private String EFF_DATE;//  生效日
    private  String BASIS;//  计息基础
    private BigDecimal YIELD;//  票面利率
    private BigDecimal BUYOUT_RATE;//  税后利率

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

    public BigDecimal getYIELD() {
        return YIELD;
    }

    public void setYIELD(BigDecimal YIELD) {
        this.YIELD = YIELD;
    }

    public BigDecimal getBUYOUT_RATE() {
        return BUYOUT_RATE;
    }

    public void setBUYOUT_RATE(BigDecimal BUYOUT_RATE) {
        this.BUYOUT_RATE = BUYOUT_RATE;
    }
}
