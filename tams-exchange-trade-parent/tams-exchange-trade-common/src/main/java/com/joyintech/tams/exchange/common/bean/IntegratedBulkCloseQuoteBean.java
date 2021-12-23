package com.joyintech.tams.exchange.common.bean;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName IntegratedBulkCloseQuoteBean
 * @Description 盘后大宗行情
 * @Author liujianping
 * @Date 2020/9/21
 * @Version
 **/
@ApiModel(value = "盘后大宗行情返回报文", description = "盘后大宗行情返回报文")
public class IntegratedBulkCloseQuoteBean {
    @ApiModelProperty(name = "errorCode", value = "错误码")
    private int errorCode;
    @ApiModelProperty(name = "errorMsg", value = "错误描述")
    private String errorMsg;

    @ApiModelProperty(name = "exchangeType", value = "交易类别")
    private String exchangeType;
    @ApiModelProperty(name = "stockCode", value = "证券代码")
    private String stockCode;
    @ApiModelProperty(name = "assetType", value = "资产类型")
    private String assetType;
    @ApiModelProperty(name = "stockName", value = "证券名称")
    private String stockName;
    @ApiModelProperty(name = "businessBalance", value = "成交金额")
    private double businessBalance;
    @ApiModelProperty(name = "businessAmount", value = "成交数量")
    private int businessAmount;
    @ApiModelProperty(name = "closingPrice", value = "当日收盘价")
    private double closingPrice;
    @ApiModelProperty(name = "buyAmount1", value = "买持仓量")
    private double buyAmount1;
    @ApiModelProperty(name = "saleAmount1", value = "卖持仓量")
    private double saleAmount1;
    @ApiModelProperty(name = "weightavgPrice", value = "加权平均价")
    private double weightavgPrice;
    @ApiModelProperty(name = "buyAmount2", value = "买持仓量2")
    private double buyAmount2;
    @ApiModelProperty(name = "saleAmount2", value = "卖持仓量2")
    private double saleAmount2;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getBusinessBalance() {
        return businessBalance;
    }

    public void setBusinessBalance(double businessBalance) {
        this.businessBalance = businessBalance;
    }

    public int getBusinessAmount() {
        return businessAmount;
    }

    public void setBusinessAmount(int businessAmount) {
        this.businessAmount = businessAmount;
    }

    public double getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(double closingPrice) {
        this.closingPrice = closingPrice;
    }

    public double getBuyAmount1() {
        return buyAmount1;
    }

    public void setBuyAmount1(double buyAmount1) {
        this.buyAmount1 = buyAmount1;
    }

    public double getSaleAmount1() {
        return saleAmount1;
    }

    public void setSaleAmount1(double saleAmount1) {
        this.saleAmount1 = saleAmount1;
    }

    public double getWeightavgPrice() {
        return weightavgPrice;
    }

    public void setWeightavgPrice(double weightavgPrice) {
        this.weightavgPrice = weightavgPrice;
    }

    public double getBuyAmount2() {
        return buyAmount2;
    }

    public void setBuyAmount2(double buyAmount2) {
        this.buyAmount2 = buyAmount2;
    }

    public double getSaleAmount2() {
        return saleAmount2;
    }

    public void setSaleAmount2(double saleAmount2) {
        this.saleAmount2 = saleAmount2;
    }
}
