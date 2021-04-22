package com.joyintech.tams.exchange.common.bean;

import java.io.Serializable;
import java.util.List;

/**
 * create By MAXX on 2020/5/12
 */
public class ExpireIncomeRateRet implements Serializable {
    private String sortable;
    private String statuCode;
    private String messageType;
    private ExpireIncomeRateBean data;
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
