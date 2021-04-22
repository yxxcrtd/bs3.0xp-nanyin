package com.joyintech.tams.exchange.common.bean;


/**
 * @ClassName IntegratedBulkQuoteBean
 * @Description 大宗行情
 * @Author liujianping
 * @Date 2020/9/21
 * @Version
 **/

public class IntegratedBulkQuoteBean {
    private int errorCode;
    private String errorMsg;

    private String orderNo;
    private String seatNo;
    private String entrustProp;
    private String entrustBs;
    private String stockCode;
    private String assetType;
    private String stockName;
    private double entrustPrice;
    private int entrustAmount;
    private int businessAmount;
    private int compactTerm;
    private String relationName;
    private String relationTel;
    private String cbpconferId;
    private String reportTime;
    private String positionStr;
    private String remark;

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

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public String getEntrustProp() {
        return entrustProp;
    }

    public void setEntrustProp(String entrustProp) {
        this.entrustProp = entrustProp;
    }

    public String getEntrustBs() {
        return entrustBs;
    }

    public void setEntrustBs(String entrustBs) {
        this.entrustBs = entrustBs;
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

    public double getEntrustPrice() {
        return entrustPrice;
    }

    public void setEntrustPrice(double entrustPrice) {
        this.entrustPrice = entrustPrice;
    }

    public int getEntrustAmount() {
        return entrustAmount;
    }

    public void setEntrustAmount(int entrustAmount) {
        this.entrustAmount = entrustAmount;
    }

    public int getBusinessAmount() {
        return businessAmount;
    }

    public void setBusinessAmount(int businessAmount) {
        this.businessAmount = businessAmount;
    }

    public int getCompactTerm() {
        return compactTerm;
    }

    public void setCompactTerm(int compactTerm) {
        this.compactTerm = compactTerm;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public String getRelationTel() {
        return relationTel;
    }

    public void setRelationTel(String relationTel) {
        this.relationTel = relationTel;
    }

    public String getCbpconferId() {
        return cbpconferId;
    }

    public void setCbpconferId(String cbpconferId) {
        this.cbpconferId = cbpconferId;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getPositionStr() {
        return positionStr;
    }

    public void setPositionStr(String positionStr) {
        this.positionStr = positionStr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
