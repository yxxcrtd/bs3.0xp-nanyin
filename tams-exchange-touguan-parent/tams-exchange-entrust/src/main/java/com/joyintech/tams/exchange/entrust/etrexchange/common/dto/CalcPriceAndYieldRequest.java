package com.joyintech.tams.exchange.entrust.etrexchange.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value="计算价格和到期收益率请求参数",description="计算价格和到期收益率请求参数")
public class CalcPriceAndYieldRequest {

    @ApiModelProperty(name="finprodId",value="金融产品代码",example="A1")
    private String finprodId;

    @ApiModelProperty(name="prinAmt",value="默认使用百元券面计算",example="100")
    private BigDecimal prinAmt;

    @ApiModelProperty(required = true,name="tradeMarket",value="交易场所",example="A1")
    private String tradeMarket;


    @ApiModelProperty(name="ps",value="交易方向",example="A1")
    private String ps;

    @ApiModelProperty(name="calculateField",value="计算方式",example="A1")
    private String calculateField;

    @ApiModelProperty(name="price",value="净价",example="A1")
    private BigDecimal price;

    @ApiModelProperty(name="fprice",value="全价",example="A1")
    private BigDecimal fprice;
    @ApiModelProperty(name="yield",value="到期收益率",example="A1")
    private BigDecimal yield;


    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getCalculateField() {
        return calculateField;
    }

    public void setCalculateField(String calculateField) {
        this.calculateField = calculateField;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getFprice() {
        return fprice;
    }

    public void setFprice(BigDecimal fprice) {
        this.fprice = fprice;
    }

    public BigDecimal getYield() {
        return yield;
    }

    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }
}