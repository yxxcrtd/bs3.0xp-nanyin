package com.joyintech.tams.exchange.common.db.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 账户风控参数
 */
@ApiModel(value="账户风控参数",description="账户风控参数")
public class AccountRiskParameter implements Serializable, KeyMethodInterface<String> {
    private static final long serialVersionUID = -8454602210516114176L;
    @ApiModelProperty(required = true, name = "accountId", value = "账户Id", example = "")
    @NotNull
    @Size(max = 32)
    @Id
    private String accountId;

    @ApiModelProperty(required = true, name = "flow", value = "下单流量控制", example = "")
    @NotNull
    @Digits(integer = 20, fraction = 0)
    private Integer flow;//下单流量控制

    @ApiModelProperty(required = true, name = "totalCount", value = "委托总次数控制", example = "")
    @NotNull
    @Digits(integer = 20, fraction = 0)
    private Integer totalCount;//委托总次数控制

    @ApiModelProperty(required = true, name = "cancelRatio", value = "报撤单比列", example = "")
    @NotNull
    @Digits(integer = 3, fraction = 2)
    private Double cancelRatio;//报撤单比列

    @ApiModelProperty(required = true, name = "selfTrade", value = "同账号自成交控制", example = "")
    @NotNull
    private Boolean selfTrade;//同账号自成交控制

    @ApiModelProperty(name = "createTime", value = "创建时间", example = "")
    @NotNull
    private Date createTime;

    @ApiModelProperty(name = "createTime", value = "更新时间", example = "")
    private Date updateTime;

    @ApiModelProperty(name = "count", value = "count", example = "")
    private int count;

    public AccountRiskParameter() {
    }

    public AccountRiskParameter(String accountId, Integer flow, Integer totalCount, Double cancelRatio, Boolean selfTrade) {
        this.accountId = accountId;
        this.flow = flow;
        this.totalCount = totalCount;
        this.cancelRatio = cancelRatio;
        this.selfTrade = selfTrade;
    }

    @Id
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Integer getFlow() {
        return flow;
    }

    public void setFlow(Integer flow) {
        this.flow = flow;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Double getCancelRatio() {
        return cancelRatio;
    }

    public void setCancelRatio(Double cancelRatio) {
        this.cancelRatio = cancelRatio;
    }

    public Boolean getSelfTrade() {
        return selfTrade;
    }

    public void setSelfTrade(Boolean selfTrade) {
        this.selfTrade = selfTrade;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String getId() {
        return accountId;
    }

    @Override
    public void setId(String id) {
        accountId = id;
    }
}
