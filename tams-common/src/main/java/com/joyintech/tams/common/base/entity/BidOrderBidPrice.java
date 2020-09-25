package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class BidOrderBidPrice extends BidOrderBidPriceKey {
    /**
     * 是否中标
     */
    private String isWin;

    /**
     * 中标标位
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal winPrice;

    /**
     * 中标净价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal winCprice;

    /**
     * 理论中标券面总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal theWinPrinAmt;

    /**
     * 实际中标券面总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal winPrinAmt;

    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 取得 是否中标
     * @return 是否中标
     */
    public String getIsWin() {
        return isWin;
    }

    /**
     * 设置 是否中标
     * @param isWin 是否中标
     */
    public void setIsWin(String isWin) {
        this.isWin = isWin == null ? null : isWin.trim();
    }

    /**
     * 取得 中标标位
     * @return 中标标位
     */
    public BigDecimal getWinPrice() {
        return winPrice;
    }

    /**
     * 设置 中标标位
     * @param winPrice 中标标位
     */
    public void setWinPrice(BigDecimal winPrice) {
        this.winPrice = winPrice;
    }

    /**
     * 取得 中标净价
     * @return 中标净价
     */
    public BigDecimal getWinCprice() {
        return winCprice;
    }

    /**
     * 设置 中标净价
     * @param winCprice 中标净价
     */
    public void setWinCprice(BigDecimal winCprice) {
        this.winCprice = winCprice;
    }

    /**
     * 取得 理论中标券面总额
     * @return 理论中标券面总额
     */
    public BigDecimal getTheWinPrinAmt() {
        return theWinPrinAmt;
    }

    /**
     * 设置 理论中标券面总额
     * @param theWinPrinAmt 理论中标券面总额
     */
    public void setTheWinPrinAmt(BigDecimal theWinPrinAmt) {
        this.theWinPrinAmt = theWinPrinAmt;
    }

    /**
     * 取得 实际中标券面总额
     * @return 实际中标券面总额
     */
    public BigDecimal getWinPrinAmt() {
        return winPrinAmt;
    }

    /**
     * 设置 实际中标券面总额
     * @param winPrinAmt 实际中标券面总额
     */
    public void setWinPrinAmt(BigDecimal winPrinAmt) {
        this.winPrinAmt = winPrinAmt;
    }

    /**
     * 取得 交易编号
     * @return 交易编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 交易编号
     * @param tradeId 交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }
}