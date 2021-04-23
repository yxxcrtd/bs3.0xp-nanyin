package com.joyintech.tams.exchange.common.bean;

import com.joyintech.tams.exchange.common.db.domain.Entrust;
import com.joyintech.tams.exchange.common.db.domain.Order;

import java.io.Serializable;
import java.util.List;

public class OrderAndEntrustQryResult implements Serializable {

    private List<Order> orderList;

    private List<Entrust> entrustList;

    public OrderAndEntrustQryResult() {
    }

    public OrderAndEntrustQryResult(List<Order> orderList, List<Entrust> entrustList) {
        this.orderList = orderList;
        this.entrustList = entrustList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<Entrust> getEntrustList() {
        return entrustList;
    }

    public void setEntrustList(List<Entrust> entrustList) {
        this.entrustList = entrustList;
    }
}
