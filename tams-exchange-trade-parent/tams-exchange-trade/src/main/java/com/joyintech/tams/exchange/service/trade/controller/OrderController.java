package com.joyintech.tams.exchange.service.trade.controller;

import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.exchange.common.bean.AlgorithmPlaceBean;
import com.joyintech.tams.exchange.common.bean.OrderPlaceBean;
import com.joyintech.tams.exchange.common.bean.ReviewDto;
import com.joyintech.tams.exchange.common.bean.TrdExchangeDto;
import com.joyintech.tams.exchange.common.constant.EntrustBsProp;
import com.joyintech.tams.exchange.common.constant.joyin.InstructionPriceMode;
import com.joyintech.tams.exchange.common.db.domain.Algorithm;
import com.joyintech.tams.exchange.common.db.domain.Instruction;
import com.joyintech.tams.exchange.common.db.domain.IntegratedEntrust;
import com.joyintech.tams.exchange.common.domain.Message;
import com.joyintech.tams.exchange.datacommon.db.service.InstructionService;
import com.joyintech.tams.exchange.service.trade.management.service.OrderManagement;
import com.joyintech.tams.exchange.util.EntrustPropUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("OrderController_dtx")
@RequestMapping("/trade/joyin")
@Api(tags = "场内交易-委托操作接口")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderManagement orderManagement;
    @Autowired
    private InstructionService instructionService;

//    /**
//     * 查询订单信息
//     *
//     * @param userId        用户Id
//     * @param instructionId 指令Id
//     * @param accountId     账户Id
//     * @return ResultBean
//     */
//    @RequestMapping(value = {"/order"}, method = RequestMethod.GET)
//    public Message<List<Order>> queryAll(@RequestParam(value = "userId") String userId,
//                               @RequestParam(value = "instructionId", required = false, defaultValue = "") String instructionId,
//                               @RequestParam(value = "accountId", required = false, defaultValue = "") String accountId) {
//        return new Message<List<Order>>(orderManagement.queryOrder(userId, instructionId, accountId));
//    }

    /**
     * 普通下单
     *
     * @param order 下单信息
     * @return Message
     */
    @ApiOperation(value = "普通下单（竞价下单）", notes = "普通下单（竞价下单）")
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public Message<OrderPlaceBean> sendOrder(@RequestBody OrderPlaceBean order) {
        logger.info("普通下单（竞价下单）：order entrustBs is [{}]", order.getEntrustBs());
        order.setEntrustBs(EntrustBsProp.getEntrustBs(order.getEntrustBs()));

        Instruction instruction = instructionService.findById(order.getInstructionId());
        String entrustProp;
        if (StringUtils.equals(InstructionPriceMode.MARKET.value, instruction.getPriceMode())) {
            entrustProp = order.getEntrustProp();
        } else {
            entrustProp = EntrustPropUtils.getEntrustProp(instruction);
        }
        logger.info("普通下单（竞价下单）：order entrustProp is [{}]", entrustProp);
        order.setEntrustProp(entrustProp);

        return new Message<OrderPlaceBean>(orderManagement.sendOrder(order.getUserId(), order));
    }

    /**
     * 综合业务下单
     *
     * @param integratedEntrust 综合订单信息
     * @return Message
     */
    @ApiOperation(value = "综合业务下单", notes = "综合业务下单")
    @RequestMapping(value = "/order/integratedEntrust", method = RequestMethod.POST)
    public Message<IntegratedEntrust> sendIntegratedEntrust(@RequestBody IntegratedEntrust integratedEntrust) {
        Instruction instruction = instructionService.findById(integratedEntrust.getInstructionId());
        logger.info("综合业务下单：integratedEntrust entrustBs is [{}]", instruction.getPs());
        integratedEntrust.setEntrustBs(EntrustBsProp.getEntrustBs(instruction.getPs()));
        String entrustProp;
        if (StringUtils.equals(DictCodeListExt3.申报类型_不指定.getValue(), instruction.getDealMode())) {
            entrustProp = integratedEntrust.getEntrustProp();
        } else {
            entrustProp = EntrustPropUtils.getEntrustProp(instruction);
        }
        logger.info("综合业务下单：order entrustProp is [{}]", entrustProp);
        integratedEntrust.setEntrustProp(entrustProp);

        return new Message<IntegratedEntrust>(orderManagement.sendIntegratedEntrust(integratedEntrust.getUserId(), integratedEntrust));
    }

    /**
     * 委托撤单
     *
     * @param userId  用户Id
     * @param orderId 订单Id
     * @return Message
     */
    @ApiOperation(value = "委托撤单", notes = "委托撤单")
    @RequestMapping(value = "/order/cancel", method = RequestMethod.POST)
    public Message<Boolean> cancelOrder(@RequestParam String userId, @RequestParam String orderId) {
        return new Message<Boolean>(orderManagement.cancelOrder(userId, orderId));
    }

    @ApiOperation(value = "普通委托复核", notes = "普通委托复核", hidden = true)
    @RequestMapping(value = "/order/review", method = RequestMethod.POST)
    public Message<ReviewDto> sendOrderWithReview(@RequestParam String userId, @RequestBody ReviewDto dto) {
        return new Message<ReviewDto>(orderManagement.sendOrderWithReview(userId, dto));
    }

    @ApiOperation(value = "综合业务委托复核", notes = "综合业务委托复核", hidden = true)
    @RequestMapping(value = "/order/integratedEntrust/review", method = RequestMethod.POST)
    public Message<TrdExchangeDto> sendOrderWithReview(@RequestParam String userId, @RequestBody TrdExchangeDto dto) {
        return new Message<TrdExchangeDto>(orderManagement.sendIntegratedEntrustWithReview(userId, dto));
    }

    /**
     * 算法单创建
     *
     * @param userId    用户id
     * @param algorithm 算法母单信息
     * @return ResultBean
     */
    @ApiOperation(value = "算法单创建", notes = "算法单创建", hidden = true)
    @RequestMapping(value = "/order/algorithm", method = RequestMethod.POST)
    public Message<Algorithm> createAlgorithm(@RequestParam String userId, @RequestBody AlgorithmPlaceBean algorithm) {
        return new Message<Algorithm>(orderManagement.createAlgorithm(userId, algorithm));
    }

    /**
     * 算法单取消
     * 取消之后停止算法执行，未完成的委托需要单独撤单
     *
     * @param algorithmId 算法母单id
     * @return ResultBean
     */
    @ApiOperation(value = "算法单取消", notes = "算法单取消", hidden = true)
    @RequestMapping(value = "/order/algorithm/cancel", method = RequestMethod.POST)
    public Message<Algorithm> cancelAlgorithm(@RequestParam String algorithmId) {
        return new Message<Algorithm>(orderManagement.cancelAlgorithm(algorithmId));
    }

//    @RequestMapping(value = "/order/cancel/revoke", method = RequestMethod.POST)
//    public ResultBean cancelOrderWithRevoke( String userId,
//                                             @RequestBody RevokeDto dto) {
//        return orderManagement.cancelOrderWithRevoke(userId, dto);
//    }

}
