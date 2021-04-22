package com.joyintech.tams.exchange.service.trade.controller;

import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.service.trade.management.service.FuturesManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liujianping
 * @ClassName FuturesController
 * @Description
 * @date 2020/11/23 13:42
 * @Version
 **/
@RestController
@RequestMapping("/service/joyin")
public class FuturesController {
    @Autowired
    FuturesManagement futuresManagement;

    /**
     * 期货保证金查询(账户所有)
     *
     * @param accountId 账户
     * @return ResultBean
     */
    @RequestMapping(value = "/futures/marginAll", method = RequestMethod.POST)
    public ResultBean queryFuturesInstrumentMarginRateByAccountId(@RequestParam(value = "account_id") String accountId) {
        return futuresManagement.queryFuturesInstrumentMarginRateByAccountId(accountId);
    }

    /**
     * 期货保证金查询
     *
     * @param accountID 账户
     * @param stockCode 合约
     * @param hedgeFlag 投机套保标志
     * @return ResultBean
     */
    @RequestMapping(value = "/futures/margin", method = RequestMethod.POST)
    public ResultBean queryFuturesInstrumentMarginRateByAccountIdStockCodeAndHedgeFlag(@RequestParam(value = "account_id") String accountID,
                                                                                       @RequestParam(value = "stock_code") String stockCode,
                                                                                       @RequestParam(value = "hedge_flag", required = false, defaultValue = "") String hedgeFlag) {
        return futuresManagement.queryFuturesInstrumentMarginRateByAccountIdStockCodeAndHedgeFlag(accountID, stockCode, hedgeFlag);
    }

    /**
     * 期货保证金刷新
     *
     * @return ResultBean
     */
    @RequestMapping(value = "/futures/margin/flush", method = RequestMethod.GET)
    public ResultBean FuturesInstrumentMarginRateFlush() {
        return futuresManagement.flushFuturesInstrumentMarginRate();
    }

    /**
     * 期货手续费刷新
     *
     * @return ResultBean
     */
    @RequestMapping(value = "/futures/commission/flush", method = RequestMethod.GET)
    public ResultBean FuturesInstrumentCommissionRateFlush() {
        return futuresManagement.flushFuturesInstrumentCommissionRate();
    }
}
