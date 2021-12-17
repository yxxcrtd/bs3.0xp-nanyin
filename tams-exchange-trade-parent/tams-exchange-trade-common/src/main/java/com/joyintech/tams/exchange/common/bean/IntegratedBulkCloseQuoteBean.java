package com.joyintech.tams.exchange.common.bean;


/**
 * @ClassName IntegratedBulkCloseQuoteBean
 * @Description 盘后大宗行情
 * @Author liujianping
 * @Date 2020/9/21
 * @Version
 **/
public class IntegratedBulkCloseQuoteBean {
    private int errorCode;
    private String errorMsg;

    private String exchangeType;
    private String stockCode;
    private String assetType;
    private String stockName;
    private double businessBalance;
    private int businessAmount;
    private double closingPrice;
    private double buyAmount1;
    private double saleAmount1;
    private double weightavgPrice;
    private double buyAmount2;
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
