package com.joyintech.tams.exchange.terminal.trade;


import com.joyintech.tams.exchange.common.bean.*;
import com.joyintech.tams.exchange.common.db.domain.Account;
import com.joyintech.tams.exchange.common.db.domain.Trade;
import com.joyintech.tams.exchange.terminal.agent.ITerminalMsgNotify;
import com.joyintech.tams.exchange.terminal.bean.EntrustBean;
import com.joyintech.tams.exchange.terminal.bean.IntegratedEntrustBean;

import java.util.List;
import java.util.Map;

public interface ITradeCommand {

    ResultBean reqLogin(Account account);

    List<EntrustBean> reqEntrusts(String accountId);

    List<Trade> reqTrades(String accountId);

    EntrustBean entrust(EntrustBean entrust);

    IntegratedEntrustBean integratedEntrust(IntegratedEntrustBean integratedEntrust);

    EntrustBean cancelEntrust(EntrustBean entrust);

    IntegratedEntrustBean cancelIntegratedEntrust(IntegratedEntrustBean integratedEntrust);

    List<IntegratedQuote> queryIntegratedQuotes(IntegratedQuote integratedQuote);

    void init(Map<ServerExtendInfo, List<Account>> accountMap, ITerminalMsgNotify iTerminalMsgNotify);

    int queryEnable(EnableQryBean qryBean);

    List<HistoryTrade> queryHistoryTrade(HistoryQryBean historyQryBean);

    List<AccountFinance> queryCustomerFinance(String accountId);
}
