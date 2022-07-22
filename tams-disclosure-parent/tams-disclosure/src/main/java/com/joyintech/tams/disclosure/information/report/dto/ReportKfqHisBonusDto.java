package com.joyintech.tams.disclosure.information.report.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 开放、净值及分红公告-历史净值
 */
public class ReportKfqHisBonusDto {
    /**资产净值**/
    private BigDecimal assetsValue;
    /**期末余额**/
    private BigDecimal capital;
    /*运营起始日--周期开始日*/
    private Date operationStartDate;
    /*赎回确认日--基准日*/
    private Date autoRedeemDate;
    /*红利发放日*/
    private Date paymentDate;
    /*上次收益分配基准日*/
    private Date lastSettDate;
    /*上次累计净值*/
    private BigDecimal lastUnitValueT;

    /**
     * 以下的字段是报表展示字段
     */
    /*申购/赎回确认日*/
    private String curBaseDate;
    /*期末每份额净值（元）*/
    private String munitValue;
    /*每份额累计净值（元）*/
    private String munitValueT;
    /*赎回价格（元/份）*/
    private String redValue;
    /*申购价格（元/份）*/
    private String subValue;
    /*本期投资运作参考年化收益率*/
    private String yield;
    /*当期封闭期*/
    private String curRange;

    public BigDecimal getAssetsValue() {
        return assetsValue;
    }

    public void setAssetsValue(BigDecimal assetsValue) {
        this.assetsValue = assetsValue;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public Date getOperationStartDate() {
        return operationStartDate;
    }

    public void setOperationStartDate(Date operationStartDate) {
        this.operationStartDate = operationStartDate;
    }

    public Date getAutoRedeemDate() {
        return autoRedeemDate;
    }

    public void setAutoRedeemDate(Date autoRedeemDate) {
        this.autoRedeemDate = autoRedeemDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getLastSettDate() {
        return lastSettDate;
    }

    public void setLastSettDate(Date lastSettDate) {
        this.lastSettDate = lastSettDate;
    }

    public BigDecimal getLastUnitValueT() {
        return lastUnitValueT;
    }

    public void setLastUnitValueT(BigDecimal lastUnitValueT) {
        this.lastUnitValueT = lastUnitValueT;
    }

    public String getCurBaseDate() {
        return curBaseDate;
    }

    public void setCurBaseDate(String curBaseDate) {
        this.curBaseDate = curBaseDate;
    }

    public String getMunitValue() {
        return munitValue;
    }

    public void setMunitValue(String munitValue) {
        this.munitValue = munitValue;
    }

    public String getMunitValueT() {
        return munitValueT;
    }

    public void setMunitValueT(String munitValueT) {
        this.munitValueT = munitValueT;
    }

    public String getRedValue() {
        return redValue;
    }

    public void setRedValue(String redValue) {
        this.redValue = redValue;
    }

    public String getSubValue() {
        return subValue;
    }

    public void setSubValue(String subValue) {
        this.subValue = subValue;
    }

    public String getYield() {
        return yield;
    }

    public void setYield(String yield) {
        this.yield = yield;
    }

    public String getCurRange() {
        return curRange;
    }

    public void setCurRange(String curRange) {
        this.curRange = curRange;
    }
}
