package com.joyintech.tams.exchange.common.bean;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "大宗行情返回报文", description = "大宗行情返回报文")
public class IntegratedBulkQuoteBean {

    @ApiModelProperty(name = "errorCode", value = "错误码")
    private int errorCode;
    @ApiModelProperty(name = "errorMsg", value = "错误描述")
    private String errorMsg;

    @ApiModelProperty(name = "orderNo", value = "记录号")
    private String orderNo;
    @ApiModelProperty(name = "seatNo", value = "席位号")
    private String seatNo;
    @ApiModelProperty(name = "entrustProp", value = "业务类别")
    private String entrustProp;
    @ApiModelProperty(name = "entrustBs", value = "买卖标志")
    private String entrustBs;
    @ApiModelProperty(name = "stockCode", value = "证券代码")
    private String stockCode;
    @ApiModelProperty(name = "assetType", value = "资产类型")
    private String assetType;
    @ApiModelProperty(name = "stockName", value = "证券名称")
    private String stockName;
    @ApiModelProperty(name = "entrustPrice", value = "申报价格")
    private double entrustPrice;
    @ApiModelProperty(name = "entrustAmount", value = "申报数量")
    private int entrustAmount;
    @ApiModelProperty(name = "businessAmount", value = "成交数量")
    private int businessAmount;
    @ApiModelProperty(name = "compactTerm", value = "合约期限")
    private int compactTerm;
    @ApiModelProperty(name = "relationName", value = "联系人名称")
    private String relationName;
    @ApiModelProperty(name = "relationTel", value = "联系方式")
    private String relationTel;
    @ApiModelProperty(name = "cbpconferId", value = "约定号")
    private String cbpconferId;
    @ApiModelProperty(name = "reportTime", value = "申报时间")
    private String reportTime;
    @ApiModelProperty(name = "positionStr", value = "定位串")
    private String positionStr;
    @ApiModelProperty(name = "remark", value = "备用文本")
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
