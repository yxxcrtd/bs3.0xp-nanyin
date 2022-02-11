package com.joyintech.tams.exchange.common.bean;

public class AlgorithmParameter {
    private int priceMode;
    private double priceDeviation;
    private int maxVolumePerEntrust;
    private int supplementAction;
    private int days = 1; // Vwap的参考行情天数
    private String remark;

    public int getPriceMode() {
        return priceMode;
    }

    public void setPriceMode(int priceMode) {
        this.priceMode = priceMode;
    }

    public double getPriceDeviation() {
        return priceDeviation;
    }

    public void setPriceDeviation(double priceDeviation) {
        this.priceDeviation = priceDeviation;
    }

    public int getMaxVolumePerEntrust() {
        return maxVolumePerEntrust;
    }

    public void setMaxVolumePerEntrust(int maxVolumePerEntrust) {
        this.maxVolumePerEntrust = maxVolumePerEntrust;
    }

    public int getSupplementAction() {
        return supplementAction;
    }

    public void setSupplementAction(int supplementAction) {
        this.supplementAction = supplementAction;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
