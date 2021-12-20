package com.joyintech.tams.exchange.service.trade.controller;

import com.joyintech.tams.exchange.common.bean.IntegratedRepurchaseQuoteBean;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.service.trade.management.service.IntegratedQuoteManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName BulkQuoteController
 * @Description TODO
 * @Author liujianping
 * @Date 2020/9/21
 * @Version
 **/
@RestController
@RequestMapping("/service/joyin")
public class BulkQuoteController {
    @Autowired
    private IntegratedQuoteManagement integratedQuoteManagement;

    /**
     * @param stockCode   证券代码
     * @param entrustProp 委托类别
     * @param entrustBs   委托方向
     * @param pageSize
     * @return ResultBean
     * @description: 大宗行情查询
     * @date: 2020/9/21 17:46
     */
    @RequestMapping(value = "/entrust/integrated/bulk_quote", method = RequestMethod.GET)
    public ResultBean queryIntegratedBulkQuote(@RequestParam(value = "stock_code") String stockCode,
                                               @RequestParam(value = "entrust_prop", required = false) String entrustProp,
                                               @RequestParam(value = "entrust_bs",required = false) String entrustBs,
                                               @RequestParam(value = "account_id") String accountId,
                                               @RequestParam(value = "page_size", required = false, defaultValue = "0") int pageSize) {
        return integratedQuoteManagement.queryIntegratedBulkQuote(stockCode, entrustProp, entrustBs, accountId, pageSize);
    }

    /**
     * @param stockCode    证券代码
     * @param exchangeType 交易类别
     * @param pageSize
     * @return ResultBean
     * @description: 盘后大宗行情查询
     * @date: 2020/9/21 17:52
     */
    @RequestMapping(value = "/entrust/integrated/bulk_close_quote", method = RequestMethod.GET)
    public ResultBean queryIntegratedBulkCloseQuote(@RequestParam(value = "stock_code") String stockCode,
                                                    @RequestParam(value = "exchange_type") String exchangeType,
                                                    @RequestParam(value = "account_id") String accountId,
                                                    @RequestParam(value = "page_size", required = false, defaultValue = "0") int pageSize) {

        return integratedQuoteManagement.queryIntegratedBulkCloseQuote(stockCode, exchangeType,accountId, pageSize);
    }

    /**
     * @param actionIn    操作控制值
     * @param entrustProp 委托属性
     * @param pageSize
     * @return ResultBean
     * @description: 质押式协议回购行情查询
     * @date: 2020/9/21 17:59
     */
    @RequestMapping(value = "/entrust/integrated/repurchase_quote", method = RequestMethod.GET)
    public ResultBean queryIntegratedRepurchaseQuote(@RequestParam(value = "action_in") int actionIn,
                                                     @RequestParam(value = "entrust_prop", required = false, defaultValue = "") String entrustProp,
                                                     @RequestParam(value = "account_id", required = false) String accountId,
                                                     @RequestParam(value = "page_size", required = false, defaultValue = "1000") int pageSize) {

        return integratedQuoteManagement.queryIntegratedRepurchaseQuote(actionIn, entrustProp, accountId, pageSize);
    }

    /**
     * @param stockCode    证券代码
     * @param exchangeType 交易类别
     * @param pageSize
     * @return ResultBean
     * @description: 上海固收行情查询
     * @date: 2020/9/21 18:10
     */
    @RequestMapping(value = "/entrust/integrated/fixed_income_quote", method = RequestMethod.GET)
    public ResultBean queryIntegratedFixedIncomeQuote(@RequestParam(value = "stock_code") String stockCode,
                                                      @RequestParam(value = "exchange_type") String exchangeType,
                                                      @RequestParam(value = "account_id") String accountId,
                                                      @RequestParam(value = "page_size", required = false, defaultValue = "0") int pageSize) {

        return integratedQuoteManagement.queryIntegratedFixedIncomeQuote(stockCode, exchangeType, accountId, pageSize);
    }

    /**
     * 手动增加协议回购mock行情
     *
     * @param repurchaseQuoteBean
     * @return
     */
    @RequestMapping(value = "/add/integrated/repurchase_quote", method = RequestMethod.POST)
    public ResultBean addIntegratedRepurchaseQuote(@RequestBody IntegratedRepurchaseQuoteBean repurchaseQuoteBean) {
        return integratedQuoteManagement.addIntegratedRepurchaseQuote(repurchaseQuoteBean);
    }
}
