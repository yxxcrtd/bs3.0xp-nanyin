package com.joyintech.tams.exchange.entrust.etrexchange.position.bean;

import com.joyintech.tams.exchange.entrust.etrexchange.common.dto.EtrExchangeTradePortfolioRequest;

import java.math.BigDecimal;

/**
 * @author swwan
 * @date 2022/2/22 - 10:49
 */
public class PositionRequestBaseBean {

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 证券代码
     */
    private String finprodId;

    /**
     * 证券管理账户
     */
    private String secAcctId;

    /**
     * 业务编号
     */
    private String tradeId;

    /**
     * 指令编号
     */
    private String etrId;

    /**
     * 数量
     */
    private BigDecimal secAmount;

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getSecAcctId() {
        return secAcctId;
    }

    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getEtrId() {
        return etrId;
    }

    public void setEtrId(String etrId) {
        this.etrId = etrId;
    }

    public BigDecimal getSecAmount() {
        return secAmount;
    }

    public void setSecAmount(BigDecimal secAmount) {
        this.secAmount = secAmount;
    }

    public PositionRequestBaseBean() {
    }

    public PositionRequestBaseBean(EtrExchangeTradePortfolioRequest request) {
        this.portfolioId = request.getPortfolioId();
        this.finprodId = request.getFinprodId();
        this.secAcctId = request.getSecAcctId();
        this.etrId = request.getEntrustId();
    }
}
