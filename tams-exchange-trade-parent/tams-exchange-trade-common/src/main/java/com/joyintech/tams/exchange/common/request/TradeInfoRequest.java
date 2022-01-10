package com.joyintech.tams.exchange.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author swwan
 * @date 2021/12/22 - 10:55
 */
@ApiModel(value = "查询成交信息请求对象", description = "查询成交信息请求对象")
public class TradeInfoRequest extends BaseRequest {

    @ApiModelProperty(required = false, name = "entrustId", value = "委托编号", example = "A001")
    private String entrustId;

    @ApiModelProperty(required = false, name = "instructionId", value = "指令编号", example = "A001")
    private String instructionId;

    @ApiModelProperty(required = false, name = "accountId", value = "账户编号", example = "A001")
    private String accountId;

    @ApiModelProperty(required = false, name = "orderId", value = "订单编号", example = "A001")
    private String orderId;

    public TradeInfoRequest() {
    }

    public TradeInfoRequest(String entrustId, String instructionId, String accountId, String orderId) {
        this.entrustId = entrustId;
        this.instructionId = instructionId;
        this.accountId = accountId;
        this.orderId = orderId;
    }

    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    public String getInstructionId() {
        return instructionId;
    }

    public void setInstructionId(String instructionId) {
        this.instructionId = instructionId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "TradeInfoRequest{" +
                "entrustId='" + entrustId + '\'' +
                ", instructionId='" + instructionId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
