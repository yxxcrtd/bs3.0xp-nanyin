package com.joyintech.service.trade.exchange.dto;

import java.math.BigDecimal;
import java.sql.Date;

public class BatchInfo implements java.io.Serializable {
    private String id;
    private String orderId;
    private int priceMode;
    private BigDecimal beginPrice;
    private BigDecimal priceOffset;
    private int beginVolume;
    private int volumeOffset;
    private int entrustTimes;
    private int totalVolume;
    private int orderVolume;
    private int stepTime;
    private int completeTimes;
    private Date updateTime;

    private int count;

    public BatchInfo() {
        this.beginPrice = new BigDecimal("0.00");
        this.priceOffset = new BigDecimal("0.00");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getPriceMode() {
        return priceMode;
    }

    public void setPriceMode(int priceMode) {
        this.priceMode = priceMode;
    }

    public BigDecimal getBeginPrice() {
        return beginPrice;
    }

    public void setBeginPrice(BigDecimal beginPrice) {
        this.beginPrice = beginPrice;
    }

    public BigDecimal getPriceOffset() {
        return priceOffset;
    }

    public void setPriceOffset(BigDecimal priceOffset) {
        this.priceOffset = priceOffset;
    }

    public int getBeginVolume() {
        return beginVolume;
    }

    public void setBeginVolume(int beginVolume) {
        this.beginVolume = beginVolume;
    }

    public int getVolumeOffset() {
        return volumeOffset;
    }

    public void setVolumeOffset(int volumeOffset) {
        this.volumeOffset = volumeOffset;
    }

    public int getEntrustTimes() {
        return entrustTimes;
    }

    public void setEntrustTimes(int entrustTimes) {
        this.entrustTimes = entrustTimes;
    }

    public int getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(int totalVolume) {
        this.totalVolume = totalVolume;
    }

    public int getOrderVolume() {
        return orderVolume;
    }

    public void setOrderVolume(int orderVolume) {
        this.orderVolume = orderVolume;
    }

    public int getStepTime() {
        return stepTime;
    }

    public void setStepTime(int stepTime) {
        this.stepTime = stepTime;
    }

    public int getCompleteTimes() {
        return completeTimes;
    }

    public void setCompleteTimes(int completeTimes) {
        this.completeTimes = completeTimes;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
