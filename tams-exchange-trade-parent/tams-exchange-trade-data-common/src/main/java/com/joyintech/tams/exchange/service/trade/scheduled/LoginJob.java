package com.joyintech.tams.exchange.service.trade.scheduled;

import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.db.domain.Account;
import com.joyintech.tams.exchange.common.db.domain.TerminalMapping;
import com.joyintech.tams.exchange.service.trade.server.agent.TradeAgent;

import java.util.List;
import java.util.Map;

public interface LoginJob {
    ResultBean loginAccount(Map<String, TradeAgent> tradingAgents, List<TerminalMapping> terminalMappings, Account account);
}
