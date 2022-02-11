package com.joyintech.tams.exchange.service.trade.controller;

import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.bean.RevokeDto;
import com.joyintech.tams.exchange.service.trade.management.service.EntrustManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service/joyin")
public class EntrustController {

    @Autowired
    private EntrustManagement entrustManagement;

    /**
     * 查询委托信息
     *
     * @param userId        用户Id
     * @param instructionId 指令Id
     * @param accountId     账户Id
     * @param orderId       订单Id
     * @return ResultBean
     */
    @RequestMapping(value = {"/entrust"}, method = RequestMethod.GET)
    public ResultBean queryAll(@RequestParam(value = "user_id") String userId,
                               @RequestParam(value = "instruction_id", required = false) String instructionId,
                               @RequestParam(value = "account_id", required = false) String accountId,
                               @RequestParam(value = "order_id", required = false) String orderId) {
        return entrustManagement.queryEntrust(userId, instructionId, accountId, orderId);
    }

    /**
     * 查询综合业务信息
     *
     * @param userId        用户Id
     * @param instructionId 指令Id
     * @param accountId     账户Id
     * @param orderId       订单Id
     * @return ResultBean
     */
    @RequestMapping(value = {"/entrust/integrated"}, method = RequestMethod.GET)
    public ResultBean queryIntegratedEntrustAll(@RequestParam(value = "user_id") String userId,
                                                @RequestParam(value = "instruction_id", required = false) String instructionId,
                                                @RequestParam(value = "account_id", required = false) String accountId,
                                                @RequestParam(value = "order_id", required = false) String orderId) {
        return entrustManagement.queryIntegratedEntrust(userId, instructionId, accountId, orderId);
    }

    /**
     * 撤销user下对应entrust
     *
     * @param userId    用户Id
     * @param entrustId 委托单号
     * @return ResultBean
     */
    @RequestMapping(value = "/entrust/cancel", method = RequestMethod.POST)
    public ResultBean cancelEntrust(@RequestParam(value = "user_id") String userId,
                                    @RequestParam(value = "entrust_id") String entrustId) {
        return entrustManagement.cancelEntrust(userId, entrustId);
    }

    /**
     * 强制撤销对应entrust
     *
     * @param userId    用户Id
     * @param entrustId 委托单号
     * @return ResultBean
     */
    @RequestMapping(value = "/entrust/exception/cancel", method = RequestMethod.POST)
    public ResultBean cancelExceptionEntrust(@RequestParam(value = "user_id") String userId,
                                             @RequestParam(value = "user_name") String userName,
                                             @RequestParam(value = "entrust_id") String entrustId) {
        return entrustManagement.cancelExceptionEntrust(userId, userName, entrustId);
    }

    /**
     * 撤销user下对应entrust
     *
     * @param userId 用户Id
     * @param dto    委托单号
     * @return ResultBean
     */
    @RequestMapping(value = "/entrust/cancel/revoke", method = RequestMethod.POST)
    public ResultBean cancelEntrustWithRevoke(String userId,
                                    @RequestBody RevokeDto dto) {
        return entrustManagement.cancelEntrustWithRevoke(userId, dto);
    }

    /**
     * 大宗行情查询
     *
     * @param userId    用户Id
     * @param stockCode 市场.证券代码
     * @return ResultBean
     */
    @RequestMapping(value = "/entrust/integrated/quote", method = RequestMethod.GET)
    public ResultBean queryIntegratedQuote(@RequestParam(value = "user_id", required = false) String userId,
                                           @RequestParam(value = "quote_type") int quoteType,
                                           @RequestParam(value = "account_id", required = false) String accountId,
                                           @RequestParam(value = "stock_code", required = false) String stockCode,
                                           @RequestParam(value = "opposite_liaisonTel", required = false) String oppositeLiaisonTel,
                                           @RequestParam(value = "liaisonTel", required = false) String liaisonTel,
                                           @RequestParam(value = "page_size", required = false, defaultValue = "0") int pageSize,
                                           @RequestParam(value = "posStr", required = false, defaultValue = "") String posStr) {
        return entrustManagement.queryIntegratedQuote(userId, quoteType, stockCode, accountId, oppositeLiaisonTel, liaisonTel, pageSize, posStr);
    }

    /**
     * 最大交易数量查询
     *
     * @param userId       用户Id
     * @param stockCode    市场.证券代码
     * @param entrustProp  交易类型
     * @param entrustBs    交易方向
     * @param entrustPrice 委托价格
     * @param accountId    账户Id
     * @return ResultBean
     */
    @RequestMapping(value = "/entrust/enable", method = RequestMethod.GET)
    public ResultBean queryEnable(@RequestParam(value = "user_id") String userId,
                                  @RequestParam(value = "stock_code") String stockCode,
                                  @RequestParam(value = "entrust_prop") String entrustProp,
                                  @RequestParam(value = "entrust_bs") String entrustBs,
                                  @RequestParam(value = "entrust_price") double entrustPrice,
                                  @RequestParam(value = "account_id") String accountId) {
        return entrustManagement.queryEnable(userId, stockCode, entrustProp, entrustBs, entrustPrice, accountId);
    }

    /*** 
     * @Description: 申报代码获取
     * @Param: [finprodId, othCodeType]
     * @return: com.joyintech.tams.exchange.common.bean.ResultBean
     * @Author: MAXX
     * @Date: 2020/6/5 
     */
    @RequestMapping(value = "/entrust/getOthCode", method = RequestMethod.POST)
    public ResultBean getOthCode(@RequestParam(value = "finprod_id", required = false) String finprodId,
                                 @RequestParam(value = "oth_code_type", required = false) String othCodeType) {

        return entrustManagement.getOthCode(finprodId, othCodeType);
    }

    /**
     * @param pageNum
     * @param pageSize
     * @param historyType
     * @param userId
     * @param startDay
     * @param endDay
     * @param accountId
     * @param portfolioId
     * @param market
     * @param stockCode
     * @param entrustProp
     * @param entrustStatus
     * @return
     */
    @RequestMapping(value = "/entrust/page", method = RequestMethod.GET)
    public ResultBean queryAllEntrustForPage(@RequestParam(value = "page_num", required = false, defaultValue = "0") int pageNum,
                                             @RequestParam(value = "page_size", required = false, defaultValue = "0") int pageSize,
                                             @RequestParam(value = "history_type") int historyType,
                                             @RequestParam(value = "user_id") String userId,
                                             @RequestParam(value = "start_day", required = false) String startDay,
                                             @RequestParam(value = "end_day", required = false) String endDay,
                                             @RequestParam(value = "account_id", required = false) String accountId,
                                             @RequestParam(value = "portfolio_id", required = false) String portfolioId,
                                             @RequestParam(value = "market", required = false) String market,
                                             @RequestParam(value = "stock_code", required = false) String stockCode,
                                             @RequestParam(value = "entrust_prop", required = false) String entrustProp,
                                             @RequestParam(value = "entrust_status", required = false, defaultValue = "-100") int entrustStatus) {
        return entrustManagement.queryAllEntrustForPage(pageNum, pageSize, historyType, userId, startDay, endDay, accountId, portfolioId, market, stockCode, entrustProp, entrustStatus);
    }

    /**
     * @param pageNum
     * @param pageSize
     * @param historyType
     * @param userId
     * @param startDay
     * @param endDay
     * @param accountId
     * @param portfolioId
     * @param market
     * @param stockCode
     * @param entrustProp
     * @param entrustStatus
     * @return
     */
    @RequestMapping(value = "/entrust/integrated/page", method = RequestMethod.GET)
    public ResultBean queryIntegratedEntrustForPage(@RequestParam(value = "page_num", required = false, defaultValue = "0") int pageNum,
                                                    @RequestParam(value = "page_size", required = false, defaultValue = "0") int pageSize,
                                                    @RequestParam(value = "history_type") int historyType,
                                                    @RequestParam(value = "user_id") String userId,
                                                    @RequestParam(value = "start_day", required = false) String startDay,
                                                    @RequestParam(value = "end_day", required = false) String endDay,
                                                    @RequestParam(value = "account_id", required = false) String accountId,
                                                    @RequestParam(value = "portfolio_id", required = false) String portfolioId,
                                                    @RequestParam(value = "market", required = false) String market,
                                                    @RequestParam(value = "stock_code", required = false) String stockCode,
                                                    @RequestParam(value = "entrust_prop", required = false) String entrustProp,
                                                    @RequestParam(value = "entrust_status", required = false, defaultValue = "-100") int entrustStatus) {
        return entrustManagement.queryIntegratedEntrustForPage(pageNum, pageSize, historyType, userId, startDay, endDay, accountId, portfolioId, market, stockCode, entrustProp, entrustStatus);
    }

    @RequestMapping(value = "/entrust/exception", method = RequestMethod.GET)
    public ResultBean queryExceptionEntrust(@RequestParam(value = "user_id") String userId,
                                            @RequestParam(value = "entrust_status", required = false, defaultValue = "1,3,4") String entrustStatus) {
        return entrustManagement.queryEntrustByStatus(userId, entrustStatus);
    }
}
