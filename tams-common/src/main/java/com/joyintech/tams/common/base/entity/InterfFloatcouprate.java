package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class InterfFloatcouprate extends InterfFloatcouprateKey {
    /**
     * 利率(%)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rate;

    /**
     * 取得 利率(%)
     * @return 利率(%)
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * 设置 利率(%)
     * @param rate 利率(%)
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}