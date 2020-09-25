package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class PrdIssuStrategyDetail extends PrdIssuStrategyDetailKey {
    /**
     * 配置规模
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cofingScale;

    /**
     * 取得 配置规模
     * @return 配置规模
     */
    public BigDecimal getCofingScale() {
        return cofingScale;
    }

    /**
     * 设置 配置规模
     * @param cofingScale 配置规模
     */
    public void setCofingScale(BigDecimal cofingScale) {
        this.cofingScale = cofingScale;
    }
}