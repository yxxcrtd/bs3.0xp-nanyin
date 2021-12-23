package com.joyintech.tams.exchange.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "到期收益率计算接口返回报文", description = "到期收益率计算接口返回报文")
public class ExpireIncomeRateRet implements Serializable {
    private static final long serialVersionUID = -4184293437942470878L;

    @ApiModelProperty(required = false, name = "sortable", value = "排序")
    private String sortable;

    @ApiModelProperty(required = false, name = "statuCode", value = "状态")
    private String statuCode;

    @ApiModelProperty(required = false, name = "messageType", value = "类型")
    private String messageType;

    @ApiModelProperty(required = false, name = "data", value = "计算结果")
    private ExpireIncomeRateBean data;

    @ApiModelProperty(required = false, name = "errorList", value = "错误信息")
    private List<ExpireIncomeRateError> errorList;

    public String getSortable() {
        return sortable;
    }

    public void setSortable(String sortable) {
        this.sortable = sortable;
    }

    public String getStatuCode() {
        return statuCode;
    }

    public void setStatuCode(String statuCode) {
        this.statuCode = statuCode;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public ExpireIncomeRateBean getData() {
        return data;
    }

    public void setData(ExpireIncomeRateBean data) {
        this.data = data;
    }

    public List<ExpireIncomeRateError> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<ExpireIncomeRateError> errorList) {
        this.errorList = errorList;
    }
}
