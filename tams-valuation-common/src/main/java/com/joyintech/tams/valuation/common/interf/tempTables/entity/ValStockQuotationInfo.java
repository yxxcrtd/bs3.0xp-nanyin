package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName ValStockQuotationInfo
 * @Description: 股票行情接口
 * @Author yanya
 * @Date 2021-08-31
 * @Version V1.0
 **/
public class ValStockQuotationInfo extends BaseEntity {

    /**股票代码:市场代码**/
    private String sharesId;
    /**交易日**/
    private Date dealDate;
    /**前收盘价**/
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal preClosePrice;
    /**开盘价**/
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal openPrice;
    /**最高价**/
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal highPrice;
    /**最低价**/
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lowPrice;
    /**收盘价**/
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal closePrice;
    /**交易状态**/
    private String tradeStatus;

    public String getSharesId() {
        return sharesId;
    }

    public void setSharesId(String sharesId) {
        this.sharesId = sharesId;
    }

    public Date getDealDate() {
        return dealDate;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }

    public BigDecimal getPreClosePrice() {
        return preClosePrice;
    }

    public void setPreClosePrice(BigDecimal preClosePrice) {
        this.preClosePrice = preClosePrice;
    }

    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(BigDecimal openPrice) {
        this.openPrice = openPrice;
    }

    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(BigDecimal closePrice) {
        this.closePrice = closePrice;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }
}
