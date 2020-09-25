package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class FinFloatCalcRuleDetail extends FinFloatCalcRuleDetailKey {
    /**
     * 业绩基准代码，同金融产品代码
     */
    private String baseRuleId;

    /**
     * 调整系数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal coefficient;

    /**
     * 利差
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal spreadRate;

    /**
     * 银行收取比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal chargeRatio;

    /**
     * 研发部门收取比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal saleRatio;

    /**
     * 层级描述
     */
    private String levRemark;

    /**
     * 取得 业绩基准代码，同金融产品代码
     * @return 业绩基准代码，同金融产品代码
     */
    public String getBaseRuleId() {
        return baseRuleId;
    }

    /**
     * 设置 业绩基准代码，同金融产品代码
     * @param baseRuleId 业绩基准代码，同金融产品代码
     */
    public void setBaseRuleId(String baseRuleId) {
        this.baseRuleId = baseRuleId == null ? null : baseRuleId.trim();
    }

    /**
     * 取得 调整系数
     * @return 调整系数
     */
    public BigDecimal getCoefficient() {
        return coefficient;
    }

    /**
     * 设置 调整系数
     * @param coefficient 调整系数
     */
    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }

    /**
     * 取得 利差
     * @return 利差
     */
    public BigDecimal getSpreadRate() {
        return spreadRate;
    }

    /**
     * 设置 利差
     * @param spreadRate 利差
     */
    public void setSpreadRate(BigDecimal spreadRate) {
        this.spreadRate = spreadRate;
    }

    /**
     * 取得 银行收取比例
     * @return 银行收取比例
     */
    public BigDecimal getChargeRatio() {
        return chargeRatio;
    }

    /**
     * 设置 银行收取比例
     * @param chargeRatio 银行收取比例
     */
    public void setChargeRatio(BigDecimal chargeRatio) {
        this.chargeRatio = chargeRatio;
    }

    /**
     * 取得 研发部门收取比例
     * @return 研发部门收取比例
     */
    public BigDecimal getSaleRatio() {
        return saleRatio;
    }

    /**
     * 设置 研发部门收取比例
     * @param saleRatio 研发部门收取比例
     */
    public void setSaleRatio(BigDecimal saleRatio) {
        this.saleRatio = saleRatio;
    }

    /**
     * 取得 层级描述
     * @return 层级描述
     */
    public String getLevRemark() {
        return levRemark;
    }

    /**
     * 设置 层级描述
     * @param levRemark 层级描述
     */
    public void setLevRemark(String levRemark) {
        this.levRemark = levRemark == null ? null : levRemark.trim();
    }
}