package com.joyintech.tams.exchange.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "到期收益率计算接口错误返回报文", description = "到期收益率计算接口错误返回报文")
public class ExpireIncomeRateError implements Serializable {
    private static final long serialVersionUID = 6806681530836120685L;

    @ApiModelProperty(required = false, name = "field", value = "")
    private String field;
    @ApiModelProperty(required = false, name = "message", value = "错误信息")
    private String message;
    @ApiModelProperty(required = false, name = "order", value = "")
    private String order;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
