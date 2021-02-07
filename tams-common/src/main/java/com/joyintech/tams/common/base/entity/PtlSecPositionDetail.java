package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;

public class PtlSecPositionDetail extends PtlSecPositionDetailKey {
    /**
     * 数量
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal amount;

    /**
     * 币种，资产对应的币种
     */
    private String ccy;

    /**
     * 取得 数量
     * @return 数量
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置 数量
     * @param amount 数量
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 取得 币种，资产对应的币种
     * @return 币种，资产对应的币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种，资产对应的币种
     * @param ccy 币种，资产对应的币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }
}