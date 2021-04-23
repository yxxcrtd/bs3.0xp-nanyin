package com.joyintech.tams.exchange.service.trade.scheduled.quote;

import com.google.common.base.Strings;
import com.joyintech.tams.exchange.common.bean.IntegratedRepurchaseQuoteBean;
import com.joyintech.tams.exchange.common.constant.ActionIn;
import com.joyintech.tams.exchange.common.db.domain.Account;
import com.joyintech.tams.exchange.service.trade.processor.others.TerminalHelper;
import com.joyintech.tams.exchange.service.trade.server.agent.AccountManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @Author: think
 * @Description: 对于投管行情查询，需要查询到全量的行情信息，单柜台接口部分未同步接口，可能存在请求频率过高而阻塞的问题，故采用定时任务缓存行情信息
 * @Date: Created in 14:38 2021/3/9
 */
@Component
public class QuoteJob {
    private static final Logger logger = LoggerFactory.getLogger(QuoteJob.class);

    @Autowired
    private AccountManager accountManager;
    @Autowired
    private TerminalHelper terminalHelper;

    private Map<Integer, List<IntegratedRepurchaseQuoteBean>> quoteMap = new ConcurrentHashMap<>();

    private List<IntegratedRepurchaseQuoteBean> integratedRepurchaseQuoteForMock = new ArrayList<>();// 手动录入行情信息保存

    @PostConstruct
    public void clearQuoteMap() {
        quoteMap.clear();
        integratedRepurchaseQuoteForMock.clear();
    }

    @Scheduled(fixedRateString = "${scheduled.quote.fixedRate:120000}")
    private void cacheIntegratedQuote() {
        List<Account> accounts = accountManager.getAccounts();
        if (accounts.isEmpty()) {
            logger.info("无已登录资金账户信息，不能查询行情");
            return;
        }
        Map<String, Account> brokerAccountMap = accountManager.getBrokerAccountMap();
        List<IntegratedRepurchaseQuoteBean> integratedRepurchaseQuoteBeanList = new ArrayList<>();
        brokerAccountMap.forEach((brokerId, account) -> {
            logger.debug("查询协议回购非公开报价行情 brokerId {}  brokerName {} accountId {}", brokerId, account.getBrokerName(), account.getId());
            List<IntegratedRepurchaseQuoteBean> integratedRepurchaseQuoteBeanBrokerList = queryRepurchaseQuote(account, ActionIn.NON_PUBLIC_QUOTE.value);
            integratedRepurchaseQuoteBeanList.addAll(integratedRepurchaseQuoteBeanBrokerList);
        });
        // mock数据添加到非公开行情 --
        if (!integratedRepurchaseQuoteForMock.isEmpty()) {
            integratedRepurchaseQuoteBeanList.addAll(integratedRepurchaseQuoteForMock);
        }
        //
        quoteMap.put(ActionIn.NON_PUBLIC_QUOTE.value, integratedRepurchaseQuoteBeanList);
        List<IntegratedRepurchaseQuoteBean> integratedRepurchaseQuoteBeanListPublic = queryRepurchaseQuote(accounts.iterator().next(), ActionIn.PUBLIC_QUOTE.value);
        quoteMap.put(ActionIn.PUBLIC_QUOTE.value, integratedRepurchaseQuoteBeanListPublic);
    }

    private List<IntegratedRepurchaseQuoteBean> queryRepurchaseQuote(Account account, int actionIn) {
        List<IntegratedRepurchaseQuoteBean> integratedRepurchaseQuoteBeans = terminalHelper.sendIntegratedRepurchaseQuote(actionIn, null, account.getId(), 1000);
        integratedRepurchaseQuoteBeans.forEach(integratedRepurchaseQuoteBean -> {
            integratedRepurchaseQuoteBean.setBrokerId(account.getBrokerId());
            integratedRepurchaseQuoteBean.setBrokerName(account.getBrokerName());
        });
        return integratedRepurchaseQuoteBeans;
    }

    public List<IntegratedRepurchaseQuoteBean> getIntegratedRepurchaseQuoteBeanList(int actionIn, String entrustProp) {
        List<IntegratedRepurchaseQuoteBean> integratedRepurchaseQuoteBeanList = quoteMap.getOrDefault(actionIn, new ArrayList<>());
        if (!Strings.isNullOrEmpty(entrustProp)) {
            return integratedRepurchaseQuoteBeanList.stream()
                    .filter(integratedRepurchaseQuoteBean -> entrustProp.equals(integratedRepurchaseQuoteBean.getEntrustProp()))
                    .collect(Collectors.toList());
        }
        logger.info("协议回购行情信息查询成功，actionIn {} entrustProp {} integratedRepurchaseQuoteBeanList.size {}", actionIn, entrustProp, integratedRepurchaseQuoteBeanList.size());
        return integratedRepurchaseQuoteBeanList;
    }

    public void addIntegratedRepurchaseQuote(IntegratedRepurchaseQuoteBean repurchaseQuoteBean) {
        integratedRepurchaseQuoteForMock.add(repurchaseQuoteBean);
        List<IntegratedRepurchaseQuoteBean> integratedRepurchaseQuoteBeanList = quoteMap.getOrDefault(ActionIn.NON_PUBLIC_QUOTE.value, new ArrayList<>());
        integratedRepurchaseQuoteBeanList.add(repurchaseQuoteBean);
        quoteMap.put(ActionIn.NON_PUBLIC_QUOTE.value, integratedRepurchaseQuoteBeanList);
    }
}
