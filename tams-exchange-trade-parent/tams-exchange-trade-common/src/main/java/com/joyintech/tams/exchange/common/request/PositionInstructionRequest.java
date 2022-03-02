package com.joyintech.tams.exchange.common.request;

import com.joyintech.tams.exchange.common.db.domain.Instruction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "下单头寸请求对象", description = "下单头寸请求对象")
public class PositionInstructionRequest extends Instruction {

    @ApiModelProperty(required = true, name = "entrustNo", value = "委托编号")
    private String entrustNo;

    public String getEntrustNo() {
        return entrustNo;
    }

    public void setEntrustNo(String entrustNo) {
        this.entrustNo = entrustNo;
    }
}
