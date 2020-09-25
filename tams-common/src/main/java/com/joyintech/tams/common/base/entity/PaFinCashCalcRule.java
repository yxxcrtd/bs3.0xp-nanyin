package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class PaFinCashCalcRule extends PaFinCashCalcRuleKey {

    /**
     * 借款利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal loadRate;

    /**
     * 净投资收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal investRate;

    /**
     * 取得 借款利率
     * @return 借款利率
     */
    public BigDecimal getLoadRate() {
        return loadRate;
    }

    /**
     * 设置 借款利率
     * @param loadRate 借款利率
     */
    public void setLoadRate(BigDecimal loadRate) {
        this.loadRate = loadRate;
    }

    /**
     * 取得 净投资收益率
     * @return 净投资收益率
     */
    public BigDecimal getInvestRate() {
        return investRate;
    }

    /**
     * 设置 净投资收益率
     * @param investRate 净投资收益率
     */
    public void setInvestRate(BigDecimal investRate) {
        this.investRate = investRate;
    }
}