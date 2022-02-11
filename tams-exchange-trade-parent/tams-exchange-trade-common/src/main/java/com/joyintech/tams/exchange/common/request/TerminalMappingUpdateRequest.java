package com.joyintech.tams.exchange.common.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author swwan
 * @date 2021/12/22 - 16:06
 */
@ApiModel(value = "更新账户终端映射信息请求对象", description = "更新账户终端映射信息请求对象")
public class TerminalMappingUpdateRequest extends BaseRequest {

    @ApiModelProperty(required = true, name = "id", value = "主键ID", example = "1234")
    @NotBlank(message = "账户终端映射信息编号不可为空")
    private String id;

    @ApiModelProperty(required = true, name = "accountId", value = "资金账户编号", example = "A01:A1001")
    @NotBlank(message = "资金账户编号不可为空")
    private String accountId;

    @ApiModelProperty(required = true, name = "terminalName", value = "终端名称", example = "terminal-01")
    @NotBlank(message = "终端名称不可为空")
    private String terminalName;

    @ApiModelProperty(required = true, name = "terminalWeight", value = "终端权重", example = "10")
    @NotBlank(message = "终端权重不可为空")
    private int terminalWeight;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName == null ? null : terminalName.trim();
    }

    public int getTerminalWeight() {
        return terminalWeight;
    }

    public void setTerminalWeight(int terminalWeight) {
        this.terminalWeight = terminalWeight;
    }
}
