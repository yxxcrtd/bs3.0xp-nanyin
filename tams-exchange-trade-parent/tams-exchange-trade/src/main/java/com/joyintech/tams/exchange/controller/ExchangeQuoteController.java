package com.joyintech.tams.exchange.controller;


import com.alibaba.fastjson.JSON;
import com.joyintech.tams.exchange.common.bean.*;
import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.service.ExchangeQuoteService;
import com.joyintech.tams.exchange.service.trade.management.service.IntegratedQuoteManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/joyin")
public class ExchangeQuoteController {

    @Autowired
    private ExchangeQuoteService quoteService;

    @Autowired
    private IntegratedQuoteManagement integratedQuoteManagement;

    /**
     * 行情查询
     *
     * @param stockCode 合约代码； stockCode填 * 查询全部，数据量巨大，不推荐此方式
     * @return ResultBean
     */
    @RequestMapping(value = "/quote/tick", method = RequestMethod.GET)
    public ResultBean sendIntegratedEntrust(@RequestParam(value = "stock_code") String stockCode) {
        String param = "?stock_code=" + stockCode;
        return quoteService.queryQuoteTick(param);
    }

    /**
     * 行情信息转换
     *
     * @return ResultBean
     */
    @RequestMapping(value = "/quote/tick/convert", method = RequestMethod.POST)
    public ResultBean queryQuoteTickConvert(@RequestBody InterfQuoteBean interfQuoteBean) {
        String data = JSON.toJSONString(interfQuoteBean.getData());
        InterfQuoteStrBean interfQuoteStrBean = new InterfQuoteStrBean();
        interfQuoteStrBean.setSrc(interfQuoteBean.getSrc());
        interfQuoteStrBean.setData(data);
        HttpEntity<InterfQuoteStrBean> request = new HttpEntity<>(interfQuoteStrBean);
        return quoteService.queryQuoteTickConvert(request);
    }

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
                                               @RequestParam(value = "entrust_bs", required = false) String entrustBs,
                                               @RequestParam(value = "account_id") String accountId,
                                               @RequestParam(value = "page_size", required = false, defaultValue = "1000") int pageSize) {
//        String param = "?stock_code=" + stockCode + "&account_id=" + accountId + "&page_size=" + pageSize;
//        if (!Strings.isNullOrEmpty(entrustProp))
//            param = param + "&entrust_prop=" + entrustProp;
//        if (!Strings.isNullOrEmpty(entrustBs))
//            param = param + "&entrust_bs=" + entrustBs;
//        return quoteService.queryIntegratedBulkQuote(param);
//////////////////////////兼容新UI后端
        IntegratedBulkQuoteQryBean integratedBulkQuoteQryBean = new IntegratedBulkQuoteQryBean(stockCode, entrustProp, entrustBs, accountId, pageSize);
        return new ResultBean(ErrorCode.SUCCESS, integratedQuoteManagement.queryIntegratedBulkQuote(integratedBulkQuoteQryBean));
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
                                                    @RequestParam(value = "page_size", required = false, defaultValue = "1000") int pageSize) {
//        String param = "?stock_code=" + stockCode + "&exchange_type=" + exchangeType + "&account_id=" + accountId + "&page_size=" + pageSize;
//        return quoteService.queryIntegratedBulkCloseQuote(param);
//////////////////////////兼容新UI后端
        IntegratedBulkCloseQuoteQryBean integratedBulkCloseQuoteQryBean = new IntegratedBulkCloseQuoteQryBean(stockCode, exchangeType, accountId, pageSize);
        return new ResultBean(ErrorCode.SUCCESS, integratedQuoteManagement.queryIntegratedBulkCloseQuote(integratedBulkCloseQuoteQryBean));
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
//        String param = "?action_in=" + actionIn + "&account_id=" + accountId + "&page_size=" + pageSize;
//        if (!Strings.isNullOrEmpty(entrustProp))
//            param = param + "&entrust_prop=" + entrustProp;
//        if (!Strings.isNullOrEmpty(accountId))
//            param = param + "&account_id=" + accountId;
//        return quoteService.queryIntegratedRepurchaseQuote(param);
/////////////////////////兼容新UI后端
        IntegratedRepurchaseQuoteQryBean integratedRepurchaseQuoteQryBean = new IntegratedRepurchaseQuoteQryBean(actionIn, entrustProp, accountId, pageSize);
        return new ResultBean(ErrorCode.SUCCESS, integratedQuoteManagement.queryIntegratedRepurchaseQuote(integratedRepurchaseQuoteQryBean));
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
                                                      @RequestParam(value = "page_size", required = false, defaultValue = "1000") int pageSize) {
//        String param = "?stock_code=" + stockCode + "&account_id=" + accountId + "&page_size=" + pageSize;
//        if (Objects.nonNull(exchangeType)) param += "&exchange_type=" + exchangeType;
//        return quoteService.queryIntegratedFixedIncomeQuote(param);
/////////////////////////兼容新UI后端
        IntegratedFixedIncomeQuoteQryBean integratedFixedIncomeQuoteQryBean = new IntegratedFixedIncomeQuoteQryBean(stockCode, exchangeType, accountId, pageSize);
        return new ResultBean(ErrorCode.SUCCESS, integratedQuoteManagement.queryIntegratedFixedIncomeQuote(integratedFixedIncomeQuoteQryBean));
    }
}