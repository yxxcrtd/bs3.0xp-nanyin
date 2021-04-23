package com.joyintech.tams.exchange.service.trade.server.agent;

import com.joyintech.tams.exchange.common.bean.AccountFinance;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.bean.TerminalId;
import com.joyintech.tams.exchange.common.bean.TerminalLogin;
import com.joyintech.tams.exchange.common.bean.terminal.RTGSStatusBean;
import com.joyintech.tams.exchange.common.db.domain.*;

import java.util.List;

public interface IAgentMsgHandler {

    /**
     * 终端登录
     */
    void onTerminalLogin(TerminalLogin terminalLogin);

    /**
     * 下单请求收到确认消息
     *
     * @param terminalId
     */
    void onRspEntrust(TerminalId terminalId);

    /**
     * 综合下单请求收到消息返回
     *
     * @param terminalId
     */
    void onRspIntegratedEntrust(TerminalId terminalId);

    /**
     * 撤单确认消息
     *
     * @param cancelEntrustBean
     */
    void onRspCancelEntrust(CancelEntrustBean cancelEntrustBean);

    /**
     * 综合撤单确认消息
     *
     * @param cancelEntrustBean
     */
    void onRspCancelIntegratedEntrust(CancelEntrustBean cancelEntrustBean);

    /**
     * 异步通知的订单的详情
     *
     * @param entrust
     */
    void onRtnEntrust(Entrust entrust);

    /**
     * 异步通知的订单的详情
     *
     * @param integratedEntrust
     */
    void onRtnIntegratedEntrust(IntegratedEntrust integratedEntrust);

    /**
     * 异步通知的交易详情
     *
     * @param tradeRecord
     */
    void onRtnTrade(Trade tradeRecord);

    /**
     * 异步通知的RTGS交收信息
     *
     * @param rtgsStatusBean
     */
    void onRtnRTGSStatus(RTGSStatusBean rtgsStatusBean);

    /**
     * 账号登录返回
     *
     * @param resultBean
     */
    void onRspAccountLogin(ResultBean resultBean);

    void onRspQryCustomerFinance(List<AccountFinance> accountFinance);

    void OnRspQryMarginRate(List<FuturesInstrumentMarginRateBean> futuresInstrumentMarginRateBeans);
}
