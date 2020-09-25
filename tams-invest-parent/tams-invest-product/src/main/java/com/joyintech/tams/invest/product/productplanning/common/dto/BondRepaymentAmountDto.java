package com.joyintech.tams.invest.product.productplanning.common.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;

public class BondRepaymentAmountDto {

    /**
     * 金融产品代码
     */
    private String finprodId;
    /**
     * 到期金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amount;

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
