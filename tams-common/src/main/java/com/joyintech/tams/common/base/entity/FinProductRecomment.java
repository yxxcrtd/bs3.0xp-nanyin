package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class FinProductRecomment extends FinProductRecommentKey {
    /**
     * 推荐费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal recfeeRate;

    /**
     * 取得 推荐费率
     * @return 推荐费率
     */
    public BigDecimal getRecfeeRate() {
        return recfeeRate;
    }

    /**
     * 设置 推荐费率
     * @param recfeeRate 推荐费率
     */
    public void setRecfeeRate(BigDecimal recfeeRate) {
        this.recfeeRate = recfeeRate;
    }
}