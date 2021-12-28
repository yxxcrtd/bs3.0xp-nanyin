package com.joyintech.tams.exchange.service.trade.server.agent;

import java.util.Map;

import com.joyintech.tams.exchange.common.db.domain.Account;

public interface TradeAgent extends IAgentCommand {
	public Map<String, Account> getAccountMap();
	public String getTerminalName();
}
