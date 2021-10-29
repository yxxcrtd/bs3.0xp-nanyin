package com.joyintech.tams.invest.common.transmatch.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.common.base.entity.EtrEntrust;

import java.math.BigDecimal;

public class ExchangeTransMatchCommonEntrustDto extends EtrEntrust {
    /**
     * 已匹配份额
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal marchQty;

    /**
     * 已匹配金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal marchAmt;

    /**
     * 指令已匹配份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal marchQtyEn;

    /**
     * 指令已匹配金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal marchAmtEn;

    /**
     * 目标类型
     */
    private String targetType;

    public BigDecimal getMarchQty() {
        return marchQty;
    }

    public void setMarchQty(BigDecimal marchQty) {
        this.marchQty = marchQty;
    }

    public BigDecimal getMarchAmt() {
        return marchAmt;
    }

    public void setMarchAmt(BigDecimal marchAmt) {
        this.marchAmt = marchAmt;
    }

    public BigDecimal getMarchQtyEn() {
        return marchQtyEn;
    }

    public void setMarchQtyEn(BigDecimal marchQtyEn) {
        this.marchQtyEn = marchQtyEn;
    }

    public BigDecimal getMarchAmtEn() {
        return marchAmtEn;
    }

    public void setMarchAmtEn(BigDecimal marchAmtEn) {
        this.marchAmtEn = marchAmtEn;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        targetType = targetType;
    }
}
