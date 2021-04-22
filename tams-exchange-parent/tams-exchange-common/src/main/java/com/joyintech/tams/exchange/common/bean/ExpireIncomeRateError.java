package com.joyintech.tams.exchange.common.bean;

import java.io.Serializable;

/**
 * create By MAXX on 2020/5/12
 */
public class ExpireIncomeRateError implements Serializable {
    private String field;
    private String message;
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
