package com.joyintech.tams.exchange.common.bean;


/**
 * @ClassName IntegratedFixedIncomeQuoteBean
 * @Description 固收行情
 * @Author liujianping
 * @Date 2020/9/21
 * @Version
 **/
public class IntegratedFixedIncomeQuoteBean {
    private int errorCode;
    private String errorMsg;

    private String orderNo;
    private String stockCode;
    private String stockName;
    private String agencyName;
    private String entrustBs;
    private double entrustPrice;
    private double fullPrice;
    private int entrustVolume;
    private double expireIncomeRate;//到期收益率 lastrate 到期年收益率
    private double interest;//债券应计利息 bondintr
    private int reportTime;

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getEntrustBs() {
        return entrustBs;
    }

    public void setEntrustBs(String entrustBs) {
        this.entrustBs = entrustBs;
    }

    public double getEntrustPrice() {
        return entrustPrice;
    }

    public void setEntrustPrice(double entrustPrice) {
        this.entrustPrice = entrustPrice;
    }

    public double getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(double fullPrice) {
        this.fullPrice = fullPrice;
    }

    public int getEntrustVolume() {
        return entrustVolume;
    }

    public void setEntrustVolume(int entrustVolume) {
        this.entrustVolume = entrustVolume;
    }

    public double getExpireIncomeRate() {
        return expireIncomeRate;
    }

    public void setExpireIncomeRate(double expireIncomeRate) {
        this.expireIncomeRate = expireIncomeRate;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public int getReportTime() {
        return reportTime;
    }

    public void setReportTime(int reportTime) {
        this.reportTime = reportTime;
    }
}
