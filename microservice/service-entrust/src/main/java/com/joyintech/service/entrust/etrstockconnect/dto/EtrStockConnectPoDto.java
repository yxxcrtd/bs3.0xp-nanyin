
package com.joyintech.service.entrust.etrstockconnect.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;

/**
 *  持仓信息
 * @see EtrStockConnectPoDto
 * @since 1.0
 */
public class EtrStockConnectPoDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -1382059388418619554L;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 证券账户
     */
    private String secAcctId;

    /**
     * 交易市场
     */
    private String tradeMarket;

    /**
     * 证券市场代码
     */
    private String finprodMarketId;

    /**
     * 资产代码
     */
    private String finprodId;

    /**
     * 资产名称
     */
    private String finprodName;

    /**
     * 港股通市场
     */
    private String hkseMarket;


    /**
     * 持仓数量
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal positionsNum;

    /**
     * 可卖数量
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal sellNum;

    /**
     * 持仓成本
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal positionsAmt;

    /**
     * 最新价
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal price;

    /**
     * 浮动盈亏（%）
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal floatingPL;
    /**
     * 持仓单位成本(综合成本法)
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal costPosition;

    /**
     * 持仓数量(综合成本法)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal costPositionNum;

    /**
     * 组合持仓.数量
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal ptlPositionsNum;


    /**
     * 获取 ptlPositionsNum
     * @return ptlPositionsNum
     */
    public BigDecimal getPtlPositionsNum() {
        return ptlPositionsNum;
    }

    /**
     * 设置 ptlPositionsNum
     *
     * @param ptlPositionsNum ptlPositionsNum
     */
    public void setPtlPositionsNum(BigDecimal ptlPositionsNum) {
        this.ptlPositionsNum = ptlPositionsNum;
    }


    public BigDecimal getCostPosition() {
        return costPosition;
    }

    public void setCostPosition(BigDecimal costPosition) {
        this.costPosition = costPosition;
    }

    public BigDecimal getCostPositionNum() {
        return costPositionNum;
    }

    public void setCostPositionNum(BigDecimal costPositionNum) {
        this.costPositionNum = costPositionNum;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getSecAcctId() {
        return secAcctId;
    }

    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
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

    public String getHkseMarket() {
        return hkseMarket;
    }

    public void setHkseMarket(String hkseMarket) {
        this.hkseMarket = hkseMarket;
    }

    public BigDecimal getPositionsNum() {
        return positionsNum;
    }

    public void setPositionsNum(BigDecimal positionsNum) {
        this.positionsNum = positionsNum;
    }

    public BigDecimal getSellNum() {
        return sellNum;
    }

    public void setSellNum(BigDecimal sellNum) {
        this.sellNum = sellNum;
    }

    public BigDecimal getPositionsAmt() {
        return positionsAmt;
    }

    public void setPositionsAmt(BigDecimal positionsAmt) {
        this.positionsAmt = positionsAmt;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getFloatingPL() {
        return floatingPL;
    }

    public void setFloatingPL(BigDecimal floatingPL) {
        this.floatingPL = floatingPL;
    }
}
