package com.joyintech.tams.exchange.common.db.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "账户终端映射信息", description = "账户终端映射信息")
public class TerminalMapping {

    @ApiModelProperty(value = "主键ID")
    private String id;

    @ApiModelProperty(value = "资金账户编号")
    private String accountId;

    @ApiModelProperty(value = "终端名称")
    private String terminalName;

    @ApiModelProperty(value = "终端权重")
    private int terminalWeight;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}