//package com.joyintech.tams.exchange.service.trade.controller;
//
//import com.joyintech.tams.exchange.common.bean.*;
//import com.joyintech.tams.exchange.common.domain.Message;
//import com.joyintech.tams.exchange.service.trade.management.service.IntegratedQuoteManagement;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@RequestMapping("/service/joyin1")
//@Api(tags = "场内交易-行情查询接口")
//public class BulkQuoteController {
//    @Autowired
//    private IntegratedQuoteManagement integratedQuoteManagement;
//
//    @ApiOperation(value = "大宗行情查询", notes = "大宗行情查询")
//    @RequestMapping(value = "/entrust/integrated/bulk_quote", method = RequestMethod.GET)
//    public Message<List<IntegratedBulkQuoteBean>> queryIntegratedBulkQuote(@RequestBody @Validated IntegratedBulkQuoteQryBean integratedBulkQuoteQryBean) {
//        return new Message<List<IntegratedBulkQuoteBean>>(integratedQuoteManagement.queryIntegratedBulkQuote(integratedBulkQuoteQryBean));
//    }
//
//    @ApiOperation(value = "盘后大宗行情查询", notes = "盘后大宗行情查询")
//    @RequestMapping(value = "/entrust/integrated/bulk_close_quote", method = RequestMethod.GET)
//    public Message<List<IntegratedBulkCloseQuoteBean>> queryIntegratedBulkCloseQuote(@RequestBody @Validated IntegratedBulkCloseQuoteQryBean integratedBulkCloseQuoteQryBean) {
//        return new Message<List<IntegratedBulkCloseQuoteBean>>(integratedQuoteManagement.queryIntegratedBulkCloseQuote(integratedBulkCloseQuoteQryBean));
//    }
//
//    @ApiOperation(value = "质押式协议回购行情查询", notes = "质押式协议回购行情查询")
//    @RequestMapping(value = "/entrust/integrated/repurchase_quote", method = RequestMethod.GET)
//    public Message<List<IntegratedRepurchaseQuoteBean>> queryIntegratedRepurchaseQuote(@RequestBody @Validated IntegratedRepurchaseQuoteQryBean integratedRepurchaseQuoteQryBean) {
//        return new Message<List<IntegratedRepurchaseQuoteBean>>(integratedQuoteManagement.queryIntegratedRepurchaseQuote(integratedRepurchaseQuoteQryBean));
//    }
//
//    @ApiOperation(value = "上海固收行情查询", notes = "上海固收行情查询")
//    @RequestMapping(value = "/entrust/integrated/fixed_income_quote", method = RequestMethod.GET)
//    public Message<List<IntegratedFixedIncomeQuoteBean>> queryIntegratedFixedIncomeQuote(@RequestBody @Validated IntegratedFixedIncomeQuoteQryBean integratedFixedIncomeQuoteQryBean) {
//        return new Message<List<IntegratedFixedIncomeQuoteBean>>(integratedQuoteManagement.queryIntegratedFixedIncomeQuote(integratedFixedIncomeQuoteQryBean));
//    }
//
//    /**
//     * 手动增加协议回购mock行情
//     */
//    @ApiOperation(value = "手动增加协议回购mock行情", notes = "手动增加协议回购mock行情", hidden = true)
//    @RequestMapping(value = "/add/integrated/repurchase_quote", method = RequestMethod.POST)
//    public Message<Object> addIntegratedRepurchaseQuote(@RequestBody IntegratedRepurchaseQuoteBean repurchaseQuoteBean) {
//        return new Message<Object>(integratedQuoteManagement.addIntegratedRepurchaseQuote(repurchaseQuoteBean));
//    }
//    
////    /**
////     * 查询行情tick信息
////     *
////     * @param stockCode 合约代码
////     */
////    @ApiOperation(value = "查询合约行情", notes = "查询合约行情（针对普通交易）")
////    @RequestMapping(value = {"/quote/tick"}, method = RequestMethod.GET)
////    public Message<Object> queryTick(@RequestParam(value = "stock_code") String stockCode) {
////        return new Message<Object>(integratedQuoteManagement.getTick(stockCode));
////    }
//}
