package com.joyintech.tams.exchange.service.trade.server.agent;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

@Component
public class TradeAgentManager {

    // 保存当前使用的终端
    private Map<String, TradeAgent> tradingAgents = Maps.newConcurrentMap();

    private Map<String, TradeAgent> accountAgents = Maps.newConcurrentMap();

    @PostConstruct
    public void init() {
        tradingAgents.clear();
        accountAgents.clear();
    }

    public void addTradeAgent(TradeAgent tradeAgent) {
        tradingAgents.putIfAbsent(tradeAgent.getTerminalName(), tradeAgent);
    }

    public void addAccounts(TradeAgent tradeAgent, String accountId) {
        accountAgents.put(accountId, tradeAgent);
    }

    public void removeAccounts(Collection<String> accountIds) {
        accountIds.forEach(accountId -> accountAgents.remove(accountId));
    }

    public TradeAgent findAgentByAccountId(String accountId) {
        return accountAgents.get(accountId);
    }

    public void removeTradeAgent(TradeAgent tradeAgent) {
        TradeAgent masterAgent = tradingAgents.get(tradeAgent.getTerminalName());
        if (Objects.nonNull(masterAgent) && masterAgent == tradeAgent)
            tradingAgents.remove(tradeAgent.getTerminalName());
        tradeAgent.getAccountMap().keySet().forEach(accountId -> accountAgents.remove(accountId));
    }

    /**
     * 获取交易终端
     *
     * @param terminalName
     * @return
     */
    public TradeAgent getTradeAgent(String terminalName) {
        return tradingAgents.get(terminalName);
    }

    /**
     * 获取登录终端
     *
     * @return
     */
    public Map<String, TradeAgent> getTradingAgents() {
        return tradingAgents;
    }

    public Map<String, TradeAgent> getAccountAgents() {
        return accountAgents;
    }
}
