package com.joyintech.tams.exchange.service.trade.server.agent;

import com.google.common.collect.Maps;
import com.joyintech.tams.exchange.common.utils.SpringContextUtils;
import com.joyintech.tams.exchange.datacommon.db.service.TerminalMappingService;
import com.joyintech.tams.exchange.service.trade.service.TerminalAgentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class TradeAgentManager {
    /**
     * 保存当前使用的终端
     */
    private Map<String, TradeAgent> loginAccountAgents = Maps.newConcurrentMap();

    private Map<String, Boolean> agentLoginStatus = Maps.newConcurrentMap();

    @Autowired
    private TerminalAgentInfoService terminalAgentInfoService;
    @Autowired
    private TerminalMappingService terminalMappingService;

    @PostConstruct
    public void init() {
        loginAccountAgents.clear();
        agentLoginStatus.clear();
    }

    public Map<String, TradeAgent> getTradingAgents() {
        return SpringContextUtils.getApplicationContext().getBeansOfType(TradeAgent.class);
    }

    public void addAccountAgent(TradeAgent tradeAgent, String accountId) {
        loginAccountAgents.put(accountId, tradeAgent);
    }

    public Map<String, TradeAgent> getAccountAgents() {
        return loginAccountAgents;
    }

    public TradeAgent findAgentByAccountId(String accountId) {
        return loginAccountAgents.get(accountId);
    }

    public TradeAgent getAccountAgent(String accountId) {
        return loginAccountAgents.get(accountId);
    }

    public void removeAccountAgent(String accountId) {
        loginAccountAgents.remove(accountId);
    }

    public void addAgentLoginStatus(String accountId, boolean loginStatus) {
        agentLoginStatus.put(accountId, loginStatus);
    }

    public Boolean getAgentLoginStatus(String accountId) {
        Boolean status = agentLoginStatus.get(accountId);
        if (null == status) {
            return false;
        }
        return status;
    }

//    public TradeAgent findAgentByAccountId(String accountId) {
//        String agentType = accountAgentType.get(accountId);
//        if (!Strings.isNullOrEmpty(agentType)) {
//            TradeAgent tradeAgent = SpringContextUtils.getBean(agentType);
//            if (null == tradeAgent) {
//                throw new RuntimeException("找不到账户对应的终端，accountId：" + accountId + "，agentType：" + agentType);
//            }
//            return tradeAgent;
//        }
//        synchronized (this) {
//            if (Strings.isNullOrEmpty(accountAgentType.get(accountId))) {
//                Set<String> agentTypes = terminalMappingService.findAll().stream()
//                        .filter(o -> StringUtils.equals(accountId, o.getAccountId()))
//                        .map(TerminalMapping::getTerminalName)
//                        .collect(Collectors.toSet());
//                if (agentTypes.isEmpty()) {
//                    throw new RuntimeException("找不到账户配置的终端类型，accountId：" + accountId);
//                } else if (agentTypes.size() > 1) {
//                    throw new RuntimeException("账户配置多个终端类型，accountId：" + accountId);
//                }
//                agentType = agentTypes.stream().findFirst().get();
//                accountAgentType.put(accountId, agentType);
//            }
//        }
//
//        return SpringContextUtils.getBean(agentType);
//    }


}
