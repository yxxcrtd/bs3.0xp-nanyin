package com.joyintech.tams.exchange.service.trade.management.service;

import com.alibaba.fastjson.JSONArray;
import com.google.common.base.Strings;
import com.joyintech.tams.exchange.common.bean.*;
import com.joyintech.tams.exchange.common.constant.*;
import com.joyintech.tams.exchange.common.db.domain.FuturesInstrument;
import com.joyintech.tams.exchange.common.db.domain.FuturesInstrumentMarginRateBean;
import com.joyintech.tams.exchange.common.db.domain.Instrument;
import com.joyintech.tams.exchange.common.db.domain.Terminal;
import com.joyintech.tams.exchange.common.utils.InvestmentUtils;
import com.joyintech.tams.exchange.datacommon.db.service.FuturesInstrumentMarginRateService;
import com.joyintech.tams.exchange.datacommon.db.service.FuturesInstrumentService;
import com.joyintech.tams.exchange.service.trade.processor.others.TerminalHelper;
import com.joyintech.tams.exchange.service.trade.server.agent.AccountManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author liujianping
 * @ClassName FuturesManagement
 * @Description
 * @date 2020/11/23 13:57
 * @Version
 **/
@Component
public class FuturesManagement {
    private Logger logger = LoggerFactory.getLogger(FuturesManagement.class);
    @Autowired
    private TerminalHelper terminalHelper;
    @Autowired
    FuturesInstrumentMarginRateService futuresInstrumentMarginRateService;
    @Autowired
    private AccountManager accountManager;

    public ResultBean queryFuturesInstrumentMarginRateByAccountId(String accountId) {
        List<FuturesInstrumentMarginRateBean> futuresInstrumentMarginRateBeanList = futuresInstrumentMarginRateService.queryAllByAccountId(accountId);
        return new ResultBean(ErrorCode.SUCCESS, futuresInstrumentMarginRateBeanList);
    }

    public ResultBean queryFuturesInstrumentMarginRateByAccountIdStockCodeAndHedgeFlag(String accountId, String stockCode, String hedgeFlag) {
        FuturesInstrumentMarginRateBean instrumentMarginRateBean = futuresInstrumentMarginRateService.queryByAccountIdStockCodeAndHedgeFlag(accountId, stockCode, hedgeFlag);
        return new ResultBean(ErrorCode.SUCCESS, instrumentMarginRateBean);
    }

    public ResultBean flushFuturesInstrumentMarginRate() {
        try {
            Set<String> accounts = accountManager.getAccountIds();
            if (CollectionUtils.isEmpty(accounts)) {
                logger.warn("flushFuturesInstrumentMarginRate accounts us Empty!");
                return new ResultBean(ErrorCode.ACCOUNT_MARGIN_RATE_FLUSH_ERROR);
            }
            if (Objects.nonNull(accounts)) {
                accounts.forEach(acc -> {
                    terminalHelper.queryFuturesInstrumentMarginRate(acc, "");
                });
            }
        } catch (Exception e) {
            logger.error("flushFuturesInstrumentMarginRate:{}", e);
            return new ResultBean(ErrorCode.API_RETURN_ERROR);
        }
        logger.info("flushFuturesInstrumentMarginRate end");
        return new ResultBean(ErrorCode.SUCCESS);
    }

    public ResultBean flushFuturesInstrumentCommissionRate() {
        try {
            Set<String> accounts = accountManager.getAccountIds();
            if (Objects.nonNull(accounts)) {
                accounts.forEach(acc -> {
                    terminalHelper.queryFuturesInstrumentCommissionRate(acc, "");
                });
            }
        } catch (Exception e) {
            logger.error("flushFuturesInstrumentCommissionRate:{}", e);
            return new ResultBean(ErrorCode.API_RETURN_ERROR);
        }
        logger.info("flushFuturesInstrumentCommissionRate end");
        return new ResultBean(ErrorCode.SUCCESS);
    }
}
