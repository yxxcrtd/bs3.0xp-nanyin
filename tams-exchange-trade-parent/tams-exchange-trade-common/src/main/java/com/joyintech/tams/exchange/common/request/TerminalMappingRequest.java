package com.joyintech.tams.exchange.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author swwan
 * @date 2021/12/22 - 16:06
 */
@ApiModel(value = "查询账户终端映射信息请求对象", description = "查询账户终端映射信息请求对象")
public class TerminalMappingRequest extends BaseRequest {

    @ApiModelProperty(required = false, name = "accountId", value = "资金账户编号", example = "A1:A0001")
    private String accountId;

    @ApiModelProperty(required = false, name = "terminalName", value = "终端名称", example = "terminal-01")
    private String terminalName;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }
}
