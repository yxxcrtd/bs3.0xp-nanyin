package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class AssetConfigMonDetail extends AssetConfigMonDetailKey {
    /**
     * 当月到期金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curEndAmt;

    /**
     * 每月新投资金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curInvAmt;

    /**
     * 取得 当月到期金额
     * @return 当月到期金额
     */
    public BigDecimal getCurEndAmt() {
        return curEndAmt;
    }

    /**
     * 设置 当月到期金额
     * @param curEndAmt 当月到期金额
     */
    public void setCurEndAmt(BigDecimal curEndAmt) {
        this.curEndAmt = curEndAmt;
    }

    /**
     * 取得 每月新投资金额
     * @return 每月新投资金额
     */
    public BigDecimal getCurInvAmt() {
        return curInvAmt;
    }

    /**
     * 设置 每月新投资金额
     * @param curInvAmt 每月新投资金额
     */
    public void setCurInvAmt(BigDecimal curInvAmt) {
        this.curInvAmt = curInvAmt;
    }
}