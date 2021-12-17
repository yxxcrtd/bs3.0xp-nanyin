package com.joyintech.tams.exchange.terminal.trade;


import com.joyintech.tams.exchange.common.bean.*;
import com.joyintech.tams.exchange.common.db.domain.Account;
import com.joyintech.tams.exchange.common.db.domain.Entrust;
import com.joyintech.tams.exchange.common.db.domain.IntegratedEntrust;
import com.joyintech.tams.exchange.common.db.domain.Trade;
import com.joyintech.tams.exchange.terminal.agent.ITerminalMsgNotify;

import java.util.List;
import java.util.Map;

public interface ITradeCommand {

    ResultBean reqLogin(Account account);

    List<Entrust> reqEntrusts(String accountId);

    List<Trade> reqTrades(String accountId);

    Entrust entrust(Entrust entrust);

    IntegratedEntrust integratedEntrust(IntegratedEntrust integratedEntrust);

    Entrust cancelEntrust(Entrust entrust);

    IntegratedEntrust cancelIntegratedEntrust(IntegratedEntrust integratedEntrust);

    List<IntegratedQuote> queryIntegratedQuotes(IntegratedQuote integratedQuote);

    void init(Map<ServerExtendInfo, List<Account>> accountMap, ITerminalMsgNotify iTerminalMsgNotify);

    int queryEnable(EnableQryBean qryBean);

    List<HistoryTrade> queryHistoryTrade(HistoryQryBean historyQryBean);

    List<AccountFinance> queryCustomerFinance(String accountId);
}
