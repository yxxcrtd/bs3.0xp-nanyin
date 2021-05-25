package com.joyintech.tams.svreport.chbreport.investreport.chbperiodic.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * @ClassName ChbPeriodicValue
 * @Description: 定期报告净值、存续规模及收益表现
 * @Author WanShiWei
 * @Date 2021-05-07
 * @Version V1.0
 **/
public class ChbPeriodicValue extends BaseEntity {

    private static final long serialVersionUID = 109399923067388230L;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 估值日期
     */
    private String valueDate;

    /**
     * 份额总数（格式用千位分隔符，保留小数点后2位）
     */
    private String totalValue;

    /**
     * 资产净值（格式用千位分隔符，保留小数点后2位）
     */
    private String assetUnitValue;

    /**
     * 份额净值（保留小数点后4位）
     */
    private String unitValue;

    /**
     * 累计净值（保留小数点后4位）
     */
    private String assetTotalValue;

    /**
     * 期初估值日期
     */
    private String valueDateQC;

    /**
     * 期初份额总数（格式用千位分隔符，保留小数点后2位）
     */
    private String totalValueQC;

    /**
     * 期初资产净值（格式用千位分隔符，保留小数点后2位）
     */
    private String assetUnitValueQC;

    /**
     * 期初份额净值（保留小数点后4位）
     */
    private String unitValueQC;

    /**
     * 期初累计净值（保留小数点后4位）
     */
    private String assetTotalValueQC;

    /**
     * 期间累计净值增长率
     */
    private String increaseRateOfCumulativeValue;

    /**
     * 本期净值增长率
     */
    private String increaseRateOfCurrentValue;

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getValueDate() {
        return valueDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    public String getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(String totalValue) {
        this.totalValue = totalValue;
    }

    public String getAssetUnitValue() {
        return assetUnitValue;
    }

    public void setAssetUnitValue(String assetUnitValue) {
        this.assetUnitValue = assetUnitValue;
    }

    public String getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(String unitValue) {
        this.unitValue = unitValue;
    }

    public String getAssetTotalValue() {
        return assetTotalValue;
    }

    public void setAssetTotalValue(String assetTotalValue) {
        this.assetTotalValue = assetTotalValue;
    }

    public String getValueDateQC() {
        return valueDateQC;
    }

    public void setValueDateQC(String valueDateQC) {
        this.valueDateQC = valueDateQC;
    }

    public String getTotalValueQC() {
        return totalValueQC;
    }

    public void setTotalValueQC(String totalValueQC) {
        this.totalValueQC = totalValueQC;
    }

    public String getAssetUnitValueQC() {
        return assetUnitValueQC;
    }

    public void setAssetUnitValueQC(String assetUnitValueQC) {
        this.assetUnitValueQC = assetUnitValueQC;
    }

    public String getUnitValueQC() {
        return unitValueQC;
    }

    public void setUnitValueQC(String unitValueQC) {
        this.unitValueQC = unitValueQC;
    }

    public String getAssetTotalValueQC() {
        return assetTotalValueQC;
    }

    public void setAssetTotalValueQC(String assetTotalValueQC) {
        this.assetTotalValueQC = assetTotalValueQC;
    }

    public String getIncreaseRateOfCumulativeValue() {
        return increaseRateOfCumulativeValue;
    }

    public void setIncreaseRateOfCumulativeValue(String increaseRateOfCumulativeValue) {
        this.increaseRateOfCumulativeValue = increaseRateOfCumulativeValue;
    }

    public String getIncreaseRateOfCurrentValue() {
        return increaseRateOfCurrentValue;
    }

    public void setIncreaseRateOfCurrentValue(String increaseRateOfCurrentValue) {
        this.increaseRateOfCurrentValue = increaseRateOfCurrentValue;
    }
}
