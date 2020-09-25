package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class EtrEntrustPledge extends EtrEntrustPledgeKey {
    /**
     * 折算比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal scale;

    /**
     * 取得 折算比例
     * @return 折算比例
     */
    public BigDecimal getScale() {
        return scale;
    }

    /**
     * 设置 折算比例
     * @param scale 折算比例
     */
    public void setScale(BigDecimal scale) {
        this.scale = scale;
    }
}