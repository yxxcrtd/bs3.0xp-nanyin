package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class CostPositionDb extends CostPositionDbKey {
    /**
     * 持仓单位成本(综合成本法)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal costPosition;

    /**
     * 持仓数量(综合成本法)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal costPositionNum;

    /**
     * 取得 持仓单位成本(综合成本法)
     * @return 持仓单位成本(综合成本法)
     */
    public BigDecimal getCostPosition() {
        return costPosition;
    }

    /**
     * 设置 持仓单位成本(综合成本法)
     * @param costPosition 持仓单位成本(综合成本法)
     */
    public void setCostPosition(BigDecimal costPosition) {
        this.costPosition = costPosition;
    }

    /**
     * 取得 持仓数量(综合成本法)
     * @return 持仓数量(综合成本法)
     */
    public BigDecimal getCostPositionNum() {
        return costPositionNum;
    }

    /**
     * 设置 持仓数量(综合成本法)
     * @param costPositionNum 持仓数量(综合成本法)
     */
    public void setCostPositionNum(BigDecimal costPositionNum) {
        this.costPositionNum = costPositionNum;
    }
}