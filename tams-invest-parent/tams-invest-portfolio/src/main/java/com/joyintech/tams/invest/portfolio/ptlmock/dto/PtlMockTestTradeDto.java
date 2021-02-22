package com.joyintech.tams.invest.portfolio.ptlmock.dto;

import com.joyintech.tams.common.base.entity.MockProductDeal;

import java.math.BigDecimal;

/**
 * 模拟试算交易
 *
 * @author yjm
 * @version 1.0 2020/04/16
 * @see PtlMockTestTradeDto
 * @since 1.0
 */
public class PtlMockTestTradeDto extends MockProductDeal {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1564573843250844688L;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 市场代码
     */
    private String finprodMarketId;

    /**
     * 资产简称
     */
    private String finprodAbbr;

    /**
     * 资产类型
     */
    private String type8;

    /**
     * 交易净价
     */
    private BigDecimal tradePrice;

    /**
     * 应计利息
     */
    private BigDecimal accuir;

    /**
     * 交易数量
     */
    private BigDecimal amount;

    /**
     * 获取 portfolioName
     * 
     * @return portfolioName.
     */
    public String getPortfolioName() {
        return portfolioName;
    }

    /**
     * 设置 portfolioName
     * 
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    /**
     * 获取 finprodMarketId
     * 
     * @return finprodMarketId.
     */
    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    /**
     * 设置 finprodMarketId
     * 
     * @param finprodMarketId finprodMarketId
     */
    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    /**
     * 获取 finprodAbbr
     * 
     * @return finprodAbbr.
     */
    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    /**
     * 设置 finprodAbbr
     * 
     * @param finprodAbbr finprodAbbr
     */
    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    /**
     * 获取 type8
     * 
     * @return type8.
     */
    public String getType8() {
        return type8;
    }

    /**
     * 设置 type8
     * 
     * @param type8 type8
     */
    public void setType8(String type8) {
        this.type8 = type8;
    }

    /**
     * 获取 tradePrice
     * 
     * @return tradePrice.
     */
    public BigDecimal getTradePrice() {
        return tradePrice;
    }

    /**
     * 设置 tradePrice
     * 
     * @param tradePrice tradePrice
     */
    public void setTradePrice(BigDecimal tradePrice) {
        this.tradePrice = tradePrice;
    }

    /**
     * 获取 accuir
     * 
     * @return accuir.
     */
    public BigDecimal getAccuir() {
        return accuir;
    }

    /**
     * 设置 accuir
     * 
     * @param accuir accuir
     */
    public void setAccuir(BigDecimal accuir) {
        this.accuir = accuir;
    }

    /**
     * 获取 amount
     * 
     * @return amount.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置 amount
     * 
     * @param amount amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
