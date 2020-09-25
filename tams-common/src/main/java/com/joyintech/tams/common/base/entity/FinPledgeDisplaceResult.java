package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class FinPledgeDisplaceResult extends FinPledgeDisplaceResultKey {
    /**
     * 质押券面总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pledgeAmt;

    /**
     * 折算比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal scale;

    /**
     * 首次净价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal firstNetprice;

    /**
     * 取得 质押券面总额
     * @return 质押券面总额
     */
    public BigDecimal getPledgeAmt() {
        return pledgeAmt;
    }

    /**
     * 设置 质押券面总额
     * @param pledgeAmt 质押券面总额
     */
    public void setPledgeAmt(BigDecimal pledgeAmt) {
        this.pledgeAmt = pledgeAmt;
    }

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

    /**
     * 取得 首次净价
     * @return 首次净价
     */
    public BigDecimal getFirstNetprice() {
        return firstNetprice;
    }

    /**
     * 设置 首次净价
     * @param firstNetprice 首次净价
     */
    public void setFirstNetprice(BigDecimal firstNetprice) {
        this.firstNetprice = firstNetprice;
    }
}