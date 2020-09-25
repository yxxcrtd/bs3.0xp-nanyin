package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.workflow.engine.annotation.PrimaryKey;

import java.math.BigDecimal;
import java.util.Date;

public class RateSwapInfo extends BaseEntity {

    /**
     * 曲线代码
     */
    private String curveCode;

    /**
     * 曲线日期
     */
    private Date curveDate;

    /**
     * 时刻
     */
    private String dataTime;

    /**
     * 曲线类型，定盘曲线、收盘曲线，行情曲线
     */
    private String curveType;

    /**
     * 期限品种（利率互换）
     */
    private String termTypeIrs;

    /**
     * 曲线利率类型
     */
    private String curveIntType;

    /**
     * 报买
     */
    private BigDecimal bidPrice;

    /**
     * 报卖
     */
    private BigDecimal offerPrice;

    /**
     * 均值
     */
    private BigDecimal avgPrice;

    private BigDecimal n;

    private BigDecimal k;

    /**
     * 录入类型
     */
    private String inputType;

    /**
     * 备注
     */
    private String remark;

    public String getCurveCode() {
        return curveCode;
    }

    public void setCurveCode(String curveCode) {
        this.curveCode = curveCode;
    }

    public Date getCurveDate() {
        return curveDate;
    }

    public void setCurveDate(Date curveDate) {
        this.curveDate = curveDate;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getCurveType() {
        return curveType;
    }

    public void setCurveType(String curveType) {
        this.curveType = curveType;
    }

    public String getTermTypeIrs() {
        return termTypeIrs;
    }

    public void setTermTypeIrs(String termTypeIrs) {
        this.termTypeIrs = termTypeIrs;
    }

    public String getCurveIntType() {
        return curveIntType;
    }

    public void setCurveIntType(String curveIntType) {
        this.curveIntType = curveIntType;
    }

    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(BigDecimal bidPrice) {
        this.bidPrice = bidPrice;
    }

    public BigDecimal getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(BigDecimal offerPrice) {
        this.offerPrice = offerPrice;
    }

    public BigDecimal getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(BigDecimal avgPrice) {
        this.avgPrice = avgPrice;
    }

    public BigDecimal getN() {
        return n;
    }

    public void setN(BigDecimal n) {
        this.n = n;
    }

    public BigDecimal getK() {
        return k;
    }

    public void setK(BigDecimal k) {
        this.k = k;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
