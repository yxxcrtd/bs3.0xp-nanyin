package com.joyintech.tams.exchange.common.rpc;

import com.joyintech.tams.exchange.common.bean.IntegratedQuote;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.db.domain.IntegratedEntrust;
import com.joyintech.tams.exchange.common.db.domain.Order;

import java.util.List;

public interface ITerminalRpc {
    Order sendOrder(Order order);

    IntegratedEntrust sendIntegratedEntrust(IntegratedEntrust integratedEntrust);

    boolean cancelOrder(long orderId);

    boolean cancelEntrust(long entrustId);

    ResultBean connectAccount(long accountId, int connectStatus);

    List<IntegratedQuote> queryIntegratedQuote(String stockCode, long accountId);

    int queryEnable(String stockCode, String bsFlag, double entrustPrice, long accountId);
}
