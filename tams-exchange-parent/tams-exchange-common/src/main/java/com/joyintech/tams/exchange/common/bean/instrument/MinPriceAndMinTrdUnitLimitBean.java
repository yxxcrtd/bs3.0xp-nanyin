package com.joyintech.tams.exchange.common.bean.instrument;

import com.joyintech.tams.exchange.common.bean.MinPrice;
import com.joyintech.tams.exchange.common.bean.MinTrdUnitLimit;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: think
 * @Description: 最小价差、买卖单位及交易上下限控制bean
 * @Date: Created in 14:11 2020/11/5
 */
public class MinPriceAndMinTrdUnitLimitBean implements Serializable {
    private int errorCode;
    private String errorMsg;
    private String stockCode;
    private List<MinPrice> minPriceList;
    private List<MinTrdUnitLimit> minTrdUnitLimitList;
    private int volumeMultiple = 1;
    private String isFullPrice = "N"; //默认为净价
    public MinPriceAndMinTrdUnitLimitBean() {
    }

    public MinPriceAndMinTrdUnitLimitBean(String stockCode, int errorCode, String errorMsg) {
        this.stockCode = stockCode;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public List<MinPrice> getMinPriceList() {
        return minPriceList;
    }

    public void setMinPriceList(List<MinPrice> minPriceList) {
        this.minPriceList = minPriceList;
    }

    public List<MinTrdUnitLimit> getMinTrdUnitLimitList() {
        return minTrdUnitLimitList;
    }

    public void setMinTrdUnitLimitList(List<MinTrdUnitLimit> minTrdUnitLimitList) {
        this.minTrdUnitLimitList = minTrdUnitLimitList;
    }

    public int getVolumeMultiple() {
        return volumeMultiple;
    }

    public void setVolumeMultiple(int volumeMultiple) {
        this.volumeMultiple = volumeMultiple;
    }

    public String getIsFullPrice() {
        return isFullPrice;
    }

    public void setIsFullPrice(String isFullPrice) {
        this.isFullPrice = isFullPrice;
    }
}
