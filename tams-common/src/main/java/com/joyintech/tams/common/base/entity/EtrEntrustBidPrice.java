package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class EtrEntrustBidPrice extends EtrEntrustBidPriceKey {
    /**
     * 投标券面
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bidPrinAmt;

    /**
     * 取得 投标券面
     * @return 投标券面
     */
    public BigDecimal getBidPrinAmt() {
        return bidPrinAmt;
    }

    /**
     * 设置 投标券面
     * @param bidPrinAmt 投标券面
     */
    public void setBidPrinAmt(BigDecimal bidPrinAmt) {
        this.bidPrinAmt = bidPrinAmt;
    }
}