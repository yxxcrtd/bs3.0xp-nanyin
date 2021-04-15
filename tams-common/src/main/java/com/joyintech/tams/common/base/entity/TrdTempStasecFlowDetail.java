package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class TrdTempStasecFlowDetail extends TrdTempStasecFlowDetailKey {
    /**
     * 入出库数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amt;

    /**
     * 取得 入出库数量
     * @return 入出库数量
     */
    public BigDecimal getAmt() {
        return amt;
    }

    /**
     * 设置 入出库数量
     * @param amt 入出库数量
     */
    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }
}