package com.joyintech.tams.exchange.common.constant.instruction;

import java.math.BigDecimal;

/**
 * @Author: think
 * @Description:
 * @Date: Created in 15:55 2020/10/22
 */
public enum SettleSpeed {
    SETTLE_SPEED_ZERO("T+0", BigDecimal.ZERO),
    SETTLE_SPEED_ONE("T+1", BigDecimal.ONE);

    public final BigDecimal value;
    public final String name;

    SettleSpeed(String name, BigDecimal value) {
        this.name = name;
        this.value = value;
    }
}
