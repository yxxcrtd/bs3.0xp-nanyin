package com.joyintech.tams.exchange.common.bean.investment;

import java.util.List;

/**
 * create By MAXX on 2020/5/22
 */
public class ReqRiskTriggerBean {
    private List<ReqRiskTrigger> rskList;

    private String orderID;

    public List<ReqRiskTrigger> getRskList() {
        return rskList;
    }

    public void setRskList(List<ReqRiskTrigger> rskList) {
        this.rskList = rskList;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
}
