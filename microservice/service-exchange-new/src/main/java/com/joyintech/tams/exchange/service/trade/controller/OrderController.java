package com.joyintech.tams.exchange.service.trade.controller;

import com.joyintech.tams.exchange.common.bean.*;
import com.joyintech.tams.exchange.common.db.domain.Algorithm;
import com.joyintech.tams.exchange.common.db.domain.IntegratedEntrust;
import com.joyintech.tams.exchange.service.trade.management.service.OrderManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("OrderController_dtx")
@RequestMapping("/service/joyin")
public class OrderController {

    @Autowired
    private OrderManagement orderManagement;

    /**
     * 查询订单信息
     *
     * @param userId        用户Id
     * @param instructionId 指令Id
     * @param accountId     账户Id
     * @return ResultBean
     */
//    @RequestMapping(value = {"/order"}, method = RequestMethod.GET)
//    public ResultBean queryAll(@RequestParam(value = "user_id") String userId,
//                               @RequestParam(value = "instruction_id", required = false, defaultValue = "") String instructionId,
//                               @RequestParam(value = "account_id", required = false, defaultValue = "") String accountId) {
//        return orderManagement.queryOrder(userId, instructionId, accountId);
//    }

    /**
     * 下单
     *
     * @param userId 用户id
     * @param order  下单信息
     * @return ResultBean
     */
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ResultBean sendOrder(@RequestParam(value = "user_id") String userId,
                                @RequestBody OrderPlaceBean order) {
        return orderManagement.sendOrder(userId, order);
    }

    /**
     * 综合业务下单
     *
     * @param userId            用户Id
     * @param integratedEntrust 综合订单信息
     * @return ResultBean
     */
    @RequestMapping(value = "/order/integratedEntrust", method = RequestMethod.POST)
    public ResultBean sendIntegratedEntrust(@RequestParam(value = "user_id") String userId,
                                            @RequestBody IntegratedEntrust integratedEntrust) {
        return orderManagement.sendIntegratedEntrust(userId, integratedEntrust);
    }

    /**
     * 撤单
     *
     * @param userId  用户Id
     * @param orderId 订单Id
     * @return ResultBean
     */
    @RequestMapping(value = "/order/cancel", method = RequestMethod.POST)
    public ResultBean cancelOrder(@RequestParam(value = "user_id") String userId,
                                  @RequestParam(value = "order_id") String orderId) {
        return orderManagement.cancelOrder(userId, orderId);
    }

    @RequestMapping(value = "/order/review", method = RequestMethod.POST)
    public ResultBean sendOrderWithReview(@RequestParam(value = "user_id") String userId,
                                          @RequestBody ReviewDto dto) {
        return orderManagement.sendOrderWithReview(userId, dto);
    }

    @RequestMapping(value = "/order/integratedEntrust/review", method = RequestMethod.POST)
    public ResultBean sendOrderWithReview(@RequestParam(value = "user_id") String userId,
                                          @RequestBody TrdExchangeDto dto) {
        return orderManagement.sendIntegratedEntrustWithReview(userId, dto);
    }

    /**
     * 算法单创建
     *
     * @param userId    用户id
     * @param algorithm 算法母单信息
     * @return ResultBean
     */
    @RequestMapping(value = "/order/algorithm", method = RequestMethod.POST)
    public ResultBean createAlgorithm(@RequestParam(value = "user_id") String userId,
                                      @RequestBody AlgorithmPlaceBean algorithm) {
        return orderManagement.createAlgorithm(userId, algorithm);
    }

    /**
     * 算法单取消
     * 取消之后停止算法执行，未完成的委托需要单独撤单
     *
     * @param algorithmId 算法母单id
     * @return ResultBean
     */
    @RequestMapping(value = "/order/algorithm/cancel", method = RequestMethod.POST)
    public ResultBean cancelAlgorithm(@RequestParam(value = "algorithm_id") String algorithmId) {
        return orderManagement.cancelAlgorithm(algorithmId);
    }

//    @RequestMapping(value = "/order/cancel/revoke", method = RequestMethod.POST)
//    public ResultBean cancelOrderWithRevoke( String userId,
//                                             @RequestBody RevokeDto dto) {
//        return orderManagement.cancelOrderWithRevoke(userId, dto);
//    }
}
