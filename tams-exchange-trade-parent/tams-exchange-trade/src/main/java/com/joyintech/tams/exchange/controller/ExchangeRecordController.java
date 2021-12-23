package com.joyintech.tams.exchange.controller;

import com.google.common.base.Strings;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.service.ExchangeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/joyin")
public class ExchangeRecordController {

    @Autowired
    private ExchangeRecordService recordService;

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
        String param = "?user_id=" + userId;
        if (Objects.nonNull(instructionId)) param += "&instruction_id=" + instructionId;
        if (Objects.nonNull(accountId)) param += "&account_id=" + accountId;
        if (Objects.nonNull(orderId)) param += "&order_id=" + orderId;
        if (Objects.nonNull(entrustId)) param += "&entrust_id=" + entrustId;
        return recordService.queryTradeAll(param);
    }

    /**
     * @param userId    用户Id
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
                                        @RequestParam(value = "user_id") String userId,
                                        @RequestParam(value = "startDay") int startDay,
                                        @RequestParam(value = "endDay") int endDay,
                                        @RequestParam(value = "account_id") String accountId) {
        String param = "/" + pageSize + "?postStr=" + postStr + "&user_id=" + userId + "&startDay=" + startDay + "&endDay=" + endDay + "&account_id=" + accountId;
        return recordService.queryHistoryTrade(param);
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
        String param = "?user_id=" + userId + "&history_type=" + historyType;
        if (pageNum > 0) {
            param = param + "&page_num=" + pageNum;
        }
        if (pageSize > 0) {
            param = param + "&page_size=" + pageSize;
        }
        if (!Strings.isNullOrEmpty(startDay)) {
            param = param + "&start_day=" + startDay;
        }
        if (!Strings.isNullOrEmpty(endDay)) {
            param = param + "&end_day=" + endDay;
        }
        if (!Strings.isNullOrEmpty(accountId)) {
            param = param + "&account_id=" + accountId;
        }
        if (!Strings.isNullOrEmpty(market)) {
            param = param + "&market=" + market;
        }
        if (!Strings.isNullOrEmpty(stockCode)) {
            param = param + "&stock_code=" + stockCode;
        }
        if (!Strings.isNullOrEmpty(entrustProp)) {
            param = param + "&entrust_prop=" + entrustProp;
        }
        if (!Strings.isNullOrEmpty(entrustId)) {
            param = param + "&entrust_id=" + entrustId;
        }
        return recordService.queryAllForPage(param);
    }
}
