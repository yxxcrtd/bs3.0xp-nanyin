package com.joyintech.service.report.poc.portfolio.ptlmock.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class MockTestNetTrend extends MockTestNetTrendKey {
    /**
     * 净值/万份收益
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netUnitValue;

    /**
     * 涨跌幅
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal valChg;

    /**
     * 业绩基准_转换
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal baseRuleChg;

    /**
     * 七日年化收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sevenDayRate;

    /**
     * 业绩基准
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal baseRule;

    /**
     * 取得 净值/万份收益
     * @return 净值/万份收益
     */
    public BigDecimal getNetUnitValue() {
        return netUnitValue;
    }

    /**
     * 设置 净值/万份收益
     * @param netUnitValue 净值/万份收益
     */
    public void setNetUnitValue(BigDecimal netUnitValue) {
        this.netUnitValue = netUnitValue;
    }

    /**
     * 取得 涨跌幅
     * @return 涨跌幅
     */
    public BigDecimal getValChg() {
        return valChg;
    }

    /**
     * 设置 涨跌幅
     * @param valChg 涨跌幅
     */
    public void setValChg(BigDecimal valChg) {
        this.valChg = valChg;
    }

    /**
     * 取得 业绩基准_转换
     * @return 业绩基准_转换
     */
    public BigDecimal getBaseRuleChg() {
        return baseRuleChg;
    }

    /**
     * 设置 业绩基准_转换
     * @param baseRuleChg 业绩基准_转换
     */
    public void setBaseRuleChg(BigDecimal baseRuleChg) {
        this.baseRuleChg = baseRuleChg;
    }

    /**
     * 取得 七日年化收益率
     * @return 七日年化收益率
     */
    public BigDecimal getSevenDayRate() {
        return sevenDayRate;
    }

    /**
     * 设置 七日年化收益率
     * @param sevenDayRate 七日年化收益率
     */
    public void setSevenDayRate(BigDecimal sevenDayRate) {
        this.sevenDayRate = sevenDayRate;
    }

    /**
     * 取得 业绩基准
     * @return 业绩基准
     */
    public BigDecimal getBaseRule() {
        return baseRule;
    }

    /**
     * 设置 业绩基准
     * @param baseRule 业绩基准
     */
    public void setBaseRule(BigDecimal baseRule) {
        this.baseRule = baseRule;
    }
}