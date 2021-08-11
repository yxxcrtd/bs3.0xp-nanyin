package com.joyintech.tams.valuation.common.dto.modelDto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;

public class StockInfoDto extends BaseEntity {

    /**
     * 股票价格
     */
    private BigDecimal stockPrice;
    /**
     * 可转股flag
     */
    private int stockFlg;
    /**
     * 股权价值
     */
    private BigDecimal stockValue;
    /**
     * 债权价值
     */
    private BigDecimal bondValue;
    /**
     * 总价值
     */
    private BigDecimal allValue;

    public BigDecimal getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(BigDecimal stockPrice) {
        this.stockPrice = stockPrice;
    }

    public int getStockFlg() {
        return stockFlg;
    }

    public void setStockFlg(int stockFlg) {
        this.stockFlg = stockFlg;
    }

    public BigDecimal getStockValue() {
        return stockValue;
    }

    public void setStockValue(BigDecimal stockValue) {
        this.stockValue = stockValue;
    }

    public BigDecimal getBondValue() {
        return bondValue;
    }

    public void setBondValue(BigDecimal bondValue) {
        this.bondValue = bondValue;
    }

    public BigDecimal getAllValue() {
        return allValue;
    }

    public void setAllValue(BigDecimal allValue) {
        this.allValue = allValue;
    }
}
