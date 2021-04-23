package com.joyintech.tams.exchange.common.db.domain;

import java.io.Serializable;
import java.util.Set;

/**
 * create By MAXX on 2020/4/17
 */
public class PositionStr implements Serializable {
    private String protocolBackPositionStr;//协议回购定位串 大宗成交(索引成交编号)
    private String tradePositionStr;//成交主动拉取定位串  普通成交(定位串)
    private String orderPositionStr;//委托主动拉取定位串 普通委托(索引合同号)
    private String fixedPositionStr;//固收委托拉取定位串 大宗委托(索引合同号)


    private volatile Set<String> businessIdSet;//成交编号
    private volatile Set<String> entrustNoSet;//委托编号

    public String getProtocolBackPositionStr() {
        return protocolBackPositionStr;
    }

    public void setProtocolBackPositionStr(String protocolBackPositionStr) {
        this.protocolBackPositionStr = protocolBackPositionStr;
    }

    public String getTradePositionStr() {
        return tradePositionStr;
    }

    public void setTradePositionStr(String tradePositionStr) {
        this.tradePositionStr = tradePositionStr;
    }

    public String getOrderPositionStr() {
        return orderPositionStr;
    }

    public void setOrderPositionStr(String orderPositionStr) {
        this.orderPositionStr = orderPositionStr;
    }

    public Set<String> getBusinessIdSet() {
        return businessIdSet;
    }

    public void setBusinessIdSet(Set<String> businessIdSet) {
        this.businessIdSet = businessIdSet;
    }

    public Set<String> getEntrustNoSet() {
        return entrustNoSet;
    }

    public void setEntrustNoSet(Set<String> entrustNoSet) {
        this.entrustNoSet = entrustNoSet;
    }

    public String getFixedPositionStr() {
        return fixedPositionStr;
    }

    public void setFixedPositionStr(String fixedPositionStr) {
        this.fixedPositionStr = fixedPositionStr;
    }


}
