package com.joyintech.tams.exchange.terminal.agent;

import com.joyintech.tams.exchange.common.bean.terminal.RTGSStatusBean;
import com.joyintech.tams.exchange.common.db.domain.Entrust;
import com.joyintech.tams.exchange.common.db.domain.IntegratedEntrust;
import com.joyintech.tams.exchange.common.db.domain.Trade;

public interface ITerminalMsgNotify {

    /**
     * 订单通知
     *
     * @param entrust 订单
     */
    void onEntrust(Entrust entrust);

    /**
     * 综合业务订单通知
     *
     * @param integratedEntrust 综合业务订单
     */
    void onIntegratedEntrust(IntegratedEntrust integratedEntrust);

    /**
     * 成交通知
     *
     * @param trade 成交
     */
    void onTrade(Trade trade);

    /**
     * RTGS交收状态查询
     *
     * @param rtgsStatusBean 查询结果
     */
    void onRTGSStatus(RTGSStatusBean rtgsStatusBean);
}
