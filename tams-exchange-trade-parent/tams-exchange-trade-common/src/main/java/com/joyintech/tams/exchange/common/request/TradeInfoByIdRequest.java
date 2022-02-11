package com.joyintech.tams.exchange.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author swwan
 * @date 2021/12/22 - 10:55
 */
@ApiModel(value = "根据编号查询成交信息请求对象", description = "根据编号查询成交信息请求对象")
public class TradeInfoByIdRequest extends BaseRequest {

    @ApiModelProperty(required = true, name = "id", value = "编号", example = "A001")
    @NotBlank(message = "查询编号不可为空")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
