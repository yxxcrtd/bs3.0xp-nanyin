package com.joyintech.tams.invest.entrust.common.dto;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 银行间指令共通通用Dto
 * @author
 * @version 1.0 20210716
 * @see EtrFinProductCommonDto
 * @since 1.0
 */
public class EtrFinProductCommonDto {
    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 资产名称
     */
    private String finprodName;

    /**
     * 托管户可用现金
     */
    private BigDecimal tgAcctCash;

    /**
     * 证券户可用现金
     */
    private BigDecimal secAcctCash;

    /**
     * T1可用现金
     */
    private BigDecimal t1Cash;

    /**
     * 持仓数量
     */
    private BigDecimal positionAmount;

    /**
     * 可卖数量
     */
    private BigDecimal canSellAmount;

    /**
     * 可买数量
     */
    private BigDecimal canBuyAmount;

    /**
     * 成本价
     */
    private BigDecimal costPrice;

    /**
     * 可用现金
     */
    private BigDecimal cashAmount;

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

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

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public BigDecimal getTgAcctCash() {
        return tgAcctCash;
    }

    public void setTgAcctCash(BigDecimal tgAcctCash) {
        this.tgAcctCash = tgAcctCash;
    }

    public BigDecimal getSecAcctCash() {
        return secAcctCash;
    }

    public void setSecAcctCash(BigDecimal secAcctCash) {
        this.secAcctCash = secAcctCash;
    }

    public BigDecimal getT1Cash() {
        return t1Cash;
    }

    public void setT1Cash(BigDecimal t1Cash) {
        this.t1Cash = t1Cash;
    }

    public BigDecimal getPositionAmount() {
        return positionAmount;
    }

    public void setPositionAmount(BigDecimal positionAmount) {
        this.positionAmount = positionAmount;
    }

    public BigDecimal getCanSellAmount() {
        return canSellAmount;
    }

    public void setCanSellAmount(BigDecimal canSellAmount) {
        this.canSellAmount = canSellAmount;
    }

    public BigDecimal getCanBuyAmount() {
        return canBuyAmount;
    }

    public void setCanBuyAmount(BigDecimal canBuyAmount) {
        this.canBuyAmount = canBuyAmount;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }
}
