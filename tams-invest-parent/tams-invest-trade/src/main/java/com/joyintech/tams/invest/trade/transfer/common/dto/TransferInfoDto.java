package com.joyintech.tams.invest.trade.transfer.common.dto;

import java.math.BigDecimal;

public class TransferInfoDto {
    /**
     * 组合代码
     */
    private  String portfolioId;

    /**
     * 交易金额
     */
    private BigDecimal tradeAmt;

    /**
     * Gets the value of portfolioId.
     *
     * @return the value of portfolioId
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * Sets the portfolioId.
     *
     * <p>You can use getPortfolioId() to get the value of portfolioId</p>
     *
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * Gets the value of tradeAmt.
     *
     * @return the value of tradeAmt
     */
    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    /**
     * Sets the tradeAmt.
     *
     * <p>You can use getTradeAmt() to get the value of tradeAmt</p>
     *
     * @param tradeAmt tradeAmt
     */
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }
}
