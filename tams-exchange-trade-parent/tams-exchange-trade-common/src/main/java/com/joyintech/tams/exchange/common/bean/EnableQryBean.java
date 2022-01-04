package com.joyintech.tams.exchange.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

@ApiModel(value = "最大交易数量查询请求报文", description = "最大交易数量查询请求报文")
public class EnableQryBean {
    @ApiModelProperty(required = true, name = "userId", value = "用户ID", example = "lh")
    @NotBlank(message = "用户ID不能为空")
    private String userId;

    @ApiModelProperty(required = true, name = "instructionId", value = "指令编号", example = "FUND000121")
    @NotBlank(message = "指令编号不能为空")
    private String instructionId;

    @ApiModelProperty(name = "entrustPrice", value = "证券代码", example = "000001.SZ")
    private String stockCode;

    @ApiModelProperty(name = "entrustProp", value = "委托属性", example = "a", hidden = true)
    private String entrustProp;
    @ApiModelProperty(name = "entrustBs", value = "交易方向", example = "1", hidden = true)
    private String entrustBs;

    @ApiModelProperty(required = true, name = "entrustPrice", value = "委托价格", example = "3.45")
    @NotBlank(message = "委托价格不能为空")
    private double entrustPrice;

    @ApiModelProperty(name = "entrustPrice", value = "网点账号", example = "A001:123456")
    private String accountId;

    public EnableQryBean() {
    }

    public EnableQryBean(String stockCode, String entrustProp, String entrustBs, double entrustPrice, String accountId) {
        this.stockCode = stockCode;
        this.entrustProp = entrustProp;
        this.entrustBs = entrustBs;
        this.entrustPrice = entrustPrice;
        this.accountId = accountId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(String instructionId) {
        this.instructionId = instructionId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
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

    public double getEntrustPrice() {
        return entrustPrice;
    }

    public void setEntrustPrice(double entrustPrice) {
        this.entrustPrice = entrustPrice;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
