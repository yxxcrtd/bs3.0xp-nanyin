package com.joyintech.tams.svreport.chb.monthcollect.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;

public class ccyExcharge extends BaseEntity {
//    行情代码，一般为货币对
   String exchargeCode;
//开盘价
   BigDecimal openPrice;
//收盘价
   BigDecimal closePrice;
//最高价
   BigDecimal highPrice;
//最低价
   BigDecimal lowPrice;
//中间价
   BigDecimal midPrice;
   //汇率比例
   BigDecimal charge;

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    public String getExchargeCode() {
        return exchargeCode;
    }

    public void setExchargeCode(String exchargeCode) {
        this.exchargeCode = exchargeCode;
    }

    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(BigDecimal openPrice) {
        this.openPrice = openPrice;
    }

    public BigDecimal getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(BigDecimal closePrice) {
        this.closePrice = closePrice;
    }

    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getMidPrice() {
        return midPrice;
    }

    public void setMidPrice(BigDecimal midPrice) {
        this.midPrice = midPrice;
    }
}