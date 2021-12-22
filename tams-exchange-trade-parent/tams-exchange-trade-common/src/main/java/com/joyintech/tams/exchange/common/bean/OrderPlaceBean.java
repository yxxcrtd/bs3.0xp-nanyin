package com.joyintech.tams.exchange.common.bean;

import com.joyintech.tams.exchange.common.db.domain.BatchInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel(value = "普通下单请求报文", description = "普通下单请求报文")
public class OrderPlaceBean implements Serializable {
    private static final long serialVersionUID = 5892155368791512437L;

    @ApiModelProperty(required = true, name = "instructionId", value = "指令编号")
    private String instructionId;
    @ApiModelProperty(required = true, name = "portfolioId", value = "组合代码")
    private String portfolioId;
    @ApiModelProperty(required = true, name = "userId", value = "操作人ID")
    private String userId;
    @ApiModelProperty(required = true, name = "username", value = "操作人名称")
    private String username;
    @ApiModelProperty(required = true, name = "accountId", value = "网点账号")
    private String accountId;
    @ApiModelProperty(required = false, name = "algorithmId", value = "算法单ID")
    private String algorithmId;
    @ApiModelProperty(required = false, name = "bsFlag", value = "买卖类别")
    private String bsFlag;
    @ApiModelProperty(required = true, name = "stockCode", value = "证券代码")
    private String stockCode;
    @ApiModelProperty(required = false, name = "entrustProp", value = "委托属性")
    private String entrustProp;
    @ApiModelProperty(required = true, name = "entrustBs", value = "交易方向")
    private String entrustBs;
    @ApiModelProperty(required = false, name = "entrustVolume", value = "委托数量")
    private long entrustVolume;
    @ApiModelProperty(required = false, name = "entrustPrice", value = "净价价格")
    private BigDecimal entrustPrice;
    @ApiModelProperty(required = false, name = "fullPrice", value = "全价价格")
    private BigDecimal fullPrice;
    @ApiModelProperty(required = false, name = "batchInfo", value = "批量下单信息")
    private BatchInfo batchInfo;

    @ApiModelProperty(required = false, name = "offsetFlag", value = "开平仓标志")
    private String offsetFlag;

    @ApiModelProperty(required = false, name = "hedgeFlag", value = "套期保值标志")
    private String hedgeFlag;

    public String getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(String instructionId) {
        this.instructionId = instructionId;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAlgorithmId() {
        return algorithmId;
    }

    public void setAlgorithmId(String algorithmId) {
        this.algorithmId = algorithmId;
    }

    public String getBsFlag() {
        return bsFlag;
    }

    public void setBsFlag(String bsFlag) {
        this.bsFlag = bsFlag;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public long getEntrustVolume() {
        return entrustVolume;
    }

    public void setEntrustVolume(long entrustVolume) {
        this.entrustVolume = entrustVolume;
    }

    public BigDecimal getEntrustPrice() {
        return entrustPrice;
    }

    public void setEntrustPrice(BigDecimal entrustPrice) {
        this.entrustPrice = entrustPrice;
    }

    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(BigDecimal fullPrice) {
        this.fullPrice = fullPrice;
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

    public BatchInfo getBatchInfo() {
        return batchInfo;
    }

    public void setBatchInfo(BatchInfo batchInfo) {
        this.batchInfo = batchInfo;
    }

    public String getOffsetFlag() {
        return offsetFlag;
    }

    public void setOffsetFlag(String offsetFlag) {
        this.offsetFlag = offsetFlag;
    }

    public String getHedgeFlag() {
        return hedgeFlag;
    }

    public void setHedgeFlag(String hedgeFlag) {
        this.hedgeFlag = hedgeFlag;
    }
}
