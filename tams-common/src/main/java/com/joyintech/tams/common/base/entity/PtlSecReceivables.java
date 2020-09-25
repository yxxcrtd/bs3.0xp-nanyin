package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class PtlSecReceivables extends PtlSecReceivablesKey {
    /**
     * 原币
     */
    private String ccy;

    /**
     * 应收款
T+1交易，资金清算金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal receAmt;

    /**
     * 罚息金额,账目余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal penaltyIntAmt;

    /**
     * 累计计提罚息金额,每日计提的累计值，未扣除支付的
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal penaltyIntTotAmt;

    /**
     * 生成方式
     */
    private String genType;

    /**
     * 取得 原币
     * @return 原币
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 原币
     * @param ccy 原币
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 应收款
T+1交易，资金清算金额
     * @return 应收款
T+1交易，资金清算金额
     */
    public BigDecimal getReceAmt() {
        return receAmt;
    }

    /**
     * 设置 应收款
T+1交易，资金清算金额
     * @param receAmt 应收款
T+1交易，资金清算金额
     */
    public void setReceAmt(BigDecimal receAmt) {
        this.receAmt = receAmt;
    }

    /**
     * 取得 罚息金额,账目余额
     * @return 罚息金额,账目余额
     */
    public BigDecimal getPenaltyIntAmt() {
        return penaltyIntAmt;
    }

    /**
     * 设置 罚息金额,账目余额
     * @param penaltyIntAmt 罚息金额,账目余额
     */
    public void setPenaltyIntAmt(BigDecimal penaltyIntAmt) {
        this.penaltyIntAmt = penaltyIntAmt;
    }

    /**
     * 取得 累计计提罚息金额,每日计提的累计值，未扣除支付的
     * @return 累计计提罚息金额,每日计提的累计值，未扣除支付的
     */
    public BigDecimal getPenaltyIntTotAmt() {
        return penaltyIntTotAmt;
    }

    /**
     * 设置 累计计提罚息金额,每日计提的累计值，未扣除支付的
     * @param penaltyIntTotAmt 累计计提罚息金额,每日计提的累计值，未扣除支付的
     */
    public void setPenaltyIntTotAmt(BigDecimal penaltyIntTotAmt) {
        this.penaltyIntTotAmt = penaltyIntTotAmt;
    }

    /**
     * 取得 生成方式
     * @return 生成方式
     */
    public String getGenType() {
        return genType;
    }

    /**
     * 设置 生成方式
     * @param genType 生成方式
     */
    public void setGenType(String genType) {
        this.genType = genType == null ? null : genType.trim();
    }
}