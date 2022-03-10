package com.joyintech.tams.business.common.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 组合数据集
 */
public class PositionCashSecData {

    private BigDecimal cashT; // 可用

    private BigDecimal realCash ;  // 实际现金（账户现金）


    public BigDecimal getCashT() {
        return cashT;
    }

    public void setCashT(BigDecimal cashT) {
        this.cashT = cashT;
    }

    public BigDecimal getRealCash() {
        return realCash;
    }

    public void setRealCash(BigDecimal realCash) {
        this.realCash = realCash;
    }
}
