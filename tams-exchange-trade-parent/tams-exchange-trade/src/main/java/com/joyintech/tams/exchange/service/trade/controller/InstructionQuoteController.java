package com.joyintech.tams.exchange.service.trade.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.exchange.service.quote.service.QuoteService;
import com.joyintech.tams.exchange.common.bean.IntegratedBulkCloseQuoteBean;
import com.joyintech.tams.exchange.common.bean.IntegratedBulkCloseQuoteQryBean;
import com.joyintech.tams.exchange.common.bean.IntegratedBulkQuoteBean;
import com.joyintech.tams.exchange.common.bean.IntegratedBulkQuoteQryBean;
import com.joyintech.tams.exchange.common.bean.IntegratedFixedIncomeQuoteBean;
import com.joyintech.tams.exchange.common.bean.IntegratedFixedIncomeQuoteQryBean;
import com.joyintech.tams.exchange.common.bean.IntegratedRepurchaseQuoteBean;
import com.joyintech.tams.exchange.common.bean.IntegratedRepurchaseQuoteQryBean;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.bean.TickBean;
import com.joyintech.tams.exchange.common.domain.Message;
import com.joyintech.tams.exchange.common.request.QueryTickRequest;
import com.joyintech.tams.exchange.service.trade.management.service.EntrustManagement;
import com.joyintech.tams.exchange.service.trade.management.service.IntegratedQuoteManagement;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/service/joyin")
@Api(tags = "场内交易-指令行情查询接口")
public class InstructionQuoteController {
    private static final Logger logger = LoggerFactory.getLogger(InstructionQuoteController.class);

    @Autowired
    private QuoteService quoteService;
    
    @Autowired
    private EntrustManagement entrustManagement;
    
    @Autowired
    private IntegratedQuoteManagement integratedQuoteManagement;
    
    @ApiOperation(value = "查询行情tick信息", notes = "查询行情tick信息")
    @RequestMapping(value = {"/quote/tick"}, method = RequestMethod.POST)
    public Message<TickBean> queryTick(@RequestBody @Validated QueryTickRequest queryTickRequest) {
        return new Message<TickBean>(quoteService.getTick(queryTickRequest.getStockCode()));
    }

    @ApiOperation(value = "大宗行情查询", notes = "大宗行情查询")
    @RequestMapping(value = "/entrust/integrated/bulk_quote", method = RequestMethod.GET)
    public Message<List<IntegratedBulkQuoteBean>> queryIntegratedBulkQuote(@RequestBody @Validated IntegratedBulkQuoteQryBean integratedBulkQuoteQryBean) {
        return new Message<List<IntegratedBulkQuoteBean>>(integratedQuoteManagement.queryIntegratedBulkQuote(integratedBulkQuoteQryBean));
    }

    @ApiOperation(value = "盘后大宗行情查询", notes = "盘后大宗行情查询")
    @RequestMapping(value = "/entrust/integrated/bulk_close_quote", method = RequestMethod.GET)
    public Message<List<IntegratedBulkCloseQuoteBean>> queryIntegratedBulkCloseQuote(@RequestBody @Validated IntegratedBulkCloseQuoteQryBean integratedBulkCloseQuoteQryBean) {
        return new Message<List<IntegratedBulkCloseQuoteBean>>(integratedQuoteManagement.queryIntegratedBulkCloseQuote(integratedBulkCloseQuoteQryBean));
    }

    @ApiOperation(value = "质押式协议回购行情查询", notes = "质押式协议回购行情查询")
    @RequestMapping(value = "/entrust/integrated/repurchase_quote", method = RequestMethod.GET)
    public Message<List<IntegratedRepurchaseQuoteBean>> queryIntegratedRepurchaseQuote(@RequestBody @Validated IntegratedRepurchaseQuoteQryBean integratedRepurchaseQuoteQryBean) {
        return new Message<List<IntegratedRepurchaseQuoteBean>>(integratedQuoteManagement.queryIntegratedRepurchaseQuote(integratedRepurchaseQuoteQryBean));
    }

    @ApiOperation(value = "上海固收行情查询", notes = "上海固收行情查询")
    @RequestMapping(value = "/entrust/integrated/fixed_income_quote", method = RequestMethod.GET)
    public Message<List<IntegratedFixedIncomeQuoteBean>> queryIntegratedFixedIncomeQuote(@RequestBody @Validated IntegratedFixedIncomeQuoteQryBean integratedFixedIncomeQuoteQryBean) {
        return new Message<List<IntegratedFixedIncomeQuoteBean>>(integratedQuoteManagement.queryIntegratedFixedIncomeQuote(integratedFixedIncomeQuoteQryBean));
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

}
