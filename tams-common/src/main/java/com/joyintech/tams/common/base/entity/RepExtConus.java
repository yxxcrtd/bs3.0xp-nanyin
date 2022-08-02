package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class RepExtConus extends RepExtConusKey {
    /**
     * 行情信息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal marketInfo;

    /**
     * 取得 行情信息
     * @return 行情信息
     */
    public BigDecimal getMarketInfo() {
        return marketInfo;
    }

    /**
     * 设置 行情信息
     * @param marketInfo 行情信息
     */
    public void setMarketInfo(BigDecimal marketInfo) {
        this.marketInfo = marketInfo;
    }
}