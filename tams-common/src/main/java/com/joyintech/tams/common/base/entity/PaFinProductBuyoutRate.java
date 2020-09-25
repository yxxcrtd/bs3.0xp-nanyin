package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class PaFinProductBuyoutRate extends PaFinProductBuyoutRateKey {
    /**
     * 买断利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal buyoutRate;

    /**
     * 取得 买断利率
     * @return 买断利率
     */
    public BigDecimal getBuyoutRate() {
        return buyoutRate;
    }

    /**
     * 设置 买断利率
     * @param buyoutRate 买断利率
     */
    public void setBuyoutRate(BigDecimal buyoutRate) {
        this.buyoutRate = buyoutRate;
    }
}