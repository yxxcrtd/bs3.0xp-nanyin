package com.joyintech.service.trade.transfer.trdassetsproject.dto;

import java.math.BigDecimal;

public class ProjSplitInfoDto {
    /**
     * 已拆分应计利息总额
     */
    private BigDecimal alSplitIntAmt;
    /**
     * 已拆分净价总额
     */
    private BigDecimal alSplitCprice;
    /**
     * 已拆交易份额
     */
    private BigDecimal alSplitPrinAmt;
    /**
     * 已拆分实付金额
     */
    private BigDecimal alSplitTradeAmt;
    /**
     * 已拆分通道费
     */
    private BigDecimal alSplitChannelFee;
    /**
     * 已拆分交易手续费
     */
    private BigDecimal alSplitProcedureAmt;

    /**
     * Gets the value of alSplitIntAmt.
     *
     * @return the value of alSplitIntAmt
     */
    public BigDecimal getAlSplitIntAmt() {
        return alSplitIntAmt;
    }

    /**
     * Sets the alSplitIntAmt.
     *
     * <p>You can use getAlSplitIntAmt() to get the value of alSplitIntAmt</p>
     *
     * @param alSplitIntAmt alSplitIntAmt
     */
    public void setAlSplitIntAmt(BigDecimal alSplitIntAmt) {
        this.alSplitIntAmt = alSplitIntAmt;
    }

    /**
     * Gets the value of alSplitCprice.
     *
     * @return the value of alSplitCprice
     */
    public BigDecimal getAlSplitCprice() {
        return alSplitCprice;
    }

    /**
     * Sets the alSplitCprice.
     *
     * <p>You can use getAlSplitCprice() to get the value of alSplitCprice</p>
     *
     * @param alSplitCprice alSplitCprice
     */
    public void setAlSplitCprice(BigDecimal alSplitCprice) {
        this.alSplitCprice = alSplitCprice;
    }

    /**
     * Gets the value of alSplitPrinAmt.
     *
     * @return the value of alSplitPrinAmt
     */
    public BigDecimal getAlSplitPrinAmt() {
        return alSplitPrinAmt;
    }

    /**
     * Sets the alSplitPrinAmt.
     *
     * <p>You can use getAlSplitPrinAmt() to get the value of alSplitPrinAmt</p>
     *
     * @param alSplitPrinAmt alSplitPrinAmt
     */
    public void setAlSplitPrinAmt(BigDecimal alSplitPrinAmt) {
        this.alSplitPrinAmt = alSplitPrinAmt;
    }

    /**
     * Gets the value of alSplitTradeAmt.
     *
     * @return the value of alSplitTradeAmt
     */
    public BigDecimal getAlSplitTradeAmt() {
        return alSplitTradeAmt;
    }

    /**
     * Sets the alSplitTradeAmt.
     *
     * <p>You can use getAlSplitTradeAmt() to get the value of alSplitTradeAmt</p>
     *
     * @param alSplitTradeAmt alSplitTradeAmt
     */
    public void setAlSplitTradeAmt(BigDecimal alSplitTradeAmt) {
        this.alSplitTradeAmt = alSplitTradeAmt;
    }

    /**
     * Gets the value of alSplitChannelFee.
     *
     * @return the value of alSplitChannelFee
     */
    public BigDecimal getAlSplitChannelFee() {
        return alSplitChannelFee;
    }

    /**
     * Sets the alSplitChannelFee.
     *
     * <p>You can use getAlSplitChannelFee() to get the value of alSplitChannelFee</p>
     *
     * @param alSplitChannelFee alSplitChannelFee
     */
    public void setAlSplitChannelFee(BigDecimal alSplitChannelFee) {
        this.alSplitChannelFee = alSplitChannelFee;
    }

    /**
     * Gets the value of alSplitProcedureAmt.
     *
     * @return the value of alSplitProcedureAmt
     */
    public BigDecimal getAlSplitProcedureAmt() {
        return alSplitProcedureAmt;
    }

    /**
     * Sets the alSplitProcedureAmt.
     *
     * <p>You can use getAlSplitProcedureAmt() to get the value of alSplitProcedureAmt</p>
     *
     * @param alSplitProcedureAmt alSplitProcedureAmt
     */
    public void setAlSplitProcedureAmt(BigDecimal alSplitProcedureAmt) {
        this.alSplitProcedureAmt = alSplitProcedureAmt;
    }
}
