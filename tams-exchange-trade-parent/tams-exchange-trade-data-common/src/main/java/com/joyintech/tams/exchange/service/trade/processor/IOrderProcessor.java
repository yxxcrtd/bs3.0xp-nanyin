package com.joyintech.tams.exchange.service.trade.processor;


import com.joyintech.tams.exchange.common.bean.OrderPlaceBean;
import com.joyintech.tams.exchange.common.db.domain.*;

public interface IOrderProcessor {
    /**
     * 处理下单
     *
     * @param order
     */
    Order handleOrder(OrderPlaceBean order);

    /**
     * 处理综合业务下单
     *
     * @param integratedEntrust
     * @return
     */
    IntegratedEntrust handIntegratedEntrust(IntegratedEntrust integratedEntrust);

    /**
     * 处理撤单
     *
     * @param orderId
     */
    boolean handleCancelOrder(String orderId);

    void onTrade(Trade tradeRecord);

    void onRtnEntrust(Entrust entrust);

    void onRtnIntegratedEntrust(IntegratedEntrust integratedEntrust);

}
