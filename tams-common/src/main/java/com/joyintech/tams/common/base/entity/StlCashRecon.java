package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class StlCashRecon extends StlCashReconKey {
    /**
     * 资金余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal zybs;

    /**
     * 取得 资金余额
     * @return 资金余额
     */
    public BigDecimal getZybs() {
        return zybs;
    }

    /**
     * 设置 资金余额
     * @param zybs 资金余额
     */
    public void setZybs(BigDecimal zybs) {
        this.zybs = zybs;
    }
}