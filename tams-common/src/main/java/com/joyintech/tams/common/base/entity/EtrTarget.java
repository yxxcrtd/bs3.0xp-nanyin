package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class EtrTarget extends EtrTargetKey {
    /**
     * 标的券面总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetFaceValue;

    /**
     * 取得 标的券面总额
     * @return 标的券面总额
     */
    public BigDecimal getTargetFaceValue() {
        return targetFaceValue;
    }

    /**
     * 设置 标的券面总额
     * @param targetFaceValue 标的券面总额
     */
    public void setTargetFaceValue(BigDecimal targetFaceValue) {
        this.targetFaceValue = targetFaceValue;
    }
}