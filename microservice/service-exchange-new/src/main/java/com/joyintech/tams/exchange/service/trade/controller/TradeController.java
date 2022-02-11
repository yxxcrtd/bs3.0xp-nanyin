package com.joyintech.tams.exchange.service.trade.controller;

import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.service.trade.management.service.TradeManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("TradeController_dtx")
@RequestMapping("/service/joyin")
public class TradeController {
    @Autowired
    private TradeManagement tradeManagement;

    /**
     * 查询交易信息
     *
     * @param userId        用户Id
     * @param entrustId     委托Id
     * @param instructionId 指令Id
     * @param accountId     账户Id
     * @param orderId       订单Id
     * @return ResultBean
     */
    @RequestMapping(value = "/trade", method = RequestMethod.GET)
    public ResultBean queryTradeAll(@RequestParam(value = "user_id") String userId,
                                    @RequestParam(value = "entrust_id", required = false) String entrustId,
                                    @RequestParam(value = "instruction_id", required = false) String instructionId,
                                    @RequestParam(value = "account_id", required = false) String accountId,
                                    @RequestParam(value = "order_id", required = false) String orderId) {
        return tradeManagement.queryTrade(userId, entrustId, instructionId, accountId, orderId);
    }

    /**
     * @param startDay  起始日期
     * @param endDay    结束日期
     * @param accountId 账户Id
     * @param postStr   上一页最后一条数据的定位串,第一页为空
     * @param pageSize  每页数量
     * @return ResultBean
     */
    @RequestMapping(value = "/trade/history/{pageSize}", method = RequestMethod.GET)
    public ResultBean queryHistoryTrade(@PathVariable(value = "pageSize") int pageSize,
                                        @RequestParam(value = "postStr", defaultValue = "") String postStr,
                                        @RequestParam(value = "startDay") String startDay,
                                        @RequestParam(value = "endDay") String endDay,
                                        @RequestParam(value = "user_id") String userId,
                                        @RequestParam(value = "account_id") String accountId) {
        return tradeManagement.queryHistoryTrade(startDay, endDay, accountId, postStr, pageSize, userId);
    }

    /**
     * @param pageNum
     * @param pageSize
     * @param historyType
     * @param userId
     * @param startDay
     * @param endDay
     * @param accountId
     * @param market
     * @param stockCode
     * @param entrustProp
     * @return
     */
    @RequestMapping(value = "/trade/page", method = RequestMethod.GET)
    public ResultBean queryAllForPage(@RequestParam(value = "page_num", required = false, defaultValue = "0") int pageNum,
                                      @RequestParam(value = "page_size", required = false, defaultValue = "0") int pageSize,
                                      @RequestParam(value = "history_type") int historyType,
                                      @RequestParam(value = "user_id") String userId,
                                      @RequestParam(value = "start_day", required = false) String startDay,
                                      @RequestParam(value = "end_day", required = false) String endDay,
                                      @RequestParam(value = "account_id", required = false) String accountId,
                                      @RequestParam(value = "market", required = false) String market,
                                      @RequestParam(value = "stock_code", required = false) String stockCode,
                                      @RequestParam(value = "entrust_prop", required = false) String entrustProp,
                                      @RequestParam(value = "entrust_id", required = false) String entrustId) {
        return tradeManagement.queryAllForPage(pageNum, pageSize, historyType, userId, startDay, endDay, accountId, market, stockCode, entrustProp, entrustId);
    }
}
