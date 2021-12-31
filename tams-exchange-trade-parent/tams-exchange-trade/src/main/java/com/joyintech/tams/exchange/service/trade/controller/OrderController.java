package com.joyintech.tams.exchange.service.trade.controller;

import cn.hutool.core.util.StrUtil;
import com.joyintech.tams.exchange.common.BusinessResultException;
import com.joyintech.tams.exchange.common.bean.AlgorithmPlaceBean;
import com.joyintech.tams.exchange.common.bean.OrderPlaceBean;
import com.joyintech.tams.exchange.common.bean.ReviewDto;
import com.joyintech.tams.exchange.common.bean.TrdExchangeDto;
import com.joyintech.tams.exchange.common.constant.*;
import com.joyintech.tams.exchange.common.db.domain.Algorithm;
import com.joyintech.tams.exchange.common.db.domain.Instruction;
import com.joyintech.tams.exchange.common.db.domain.IntegratedEntrust;
import com.joyintech.tams.exchange.common.domain.Message;
import com.joyintech.tams.exchange.datacommon.db.service.InstructionService;
import com.joyintech.tams.exchange.service.trade.management.service.OrderManagement;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
        order.setEntrustBs(InstructionPs.getEntrustBs(order.getEntrustBs()));
        String entrustProp = getEntrustProp(order.getInstructionId());
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
        logger.info("综合业务下单：integratedEntrust entrustBs is [{}]", integratedEntrust.getEntrustBs());
        integratedEntrust.setEntrustBs(InstructionPs.getEntrustBs(integratedEntrust.getEntrustBs()));
        String entrustProp = getEntrustProp(integratedEntrust.getInstructionId());
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

    private String getEntrustProp(String instructionId) {
        Instruction instruction = instructionService.findById(instructionId);
        if (null == instruction) {
            throw BusinessResultException.create(ErrorCode.INSTRUCTIONS_NOT_EXIST);
        }
        String tradePlat = instruction.getTradePlat();
        //竞价平台
        if (StrUtil.equals(tradePlat, TradePlat.NORMAL.value)) {
            return decideEntrustProp(instruction.getPs());
        }

        //协议回购
        String firstRepoPs = instruction.getFirstRepoPs();
        if (InstructionPs.POSITIVEREPO.value.equals(firstRepoPs) || InstructionPs.REVERSEDREPO.value.equals(firstRepoPs)) {
            return decideEntrustProp(instruction.getPs(), instruction.getTradeMarket());
        }

        //大宗平台、固收平台
        String dealMode = instruction.getDealMode();
        if (StrUtil.isNotEmpty(dealMode)) {
            return decideEntrustProp(dealMode);
        }

        return null;
    }

    private String decideEntrustProp(String ps) {
        EntrustProp entrustProp = EntrustBsProp.getEntrustProp(ps);
        if (null == entrustProp) {
            throw BusinessResultException.create(ErrorCode.ENTRUST_PROP_NOT_EXIT);
        }
        return entrustProp.value;
    }

    private String decideEntrustProp(String ps, String tradeMarket) {
        RepoOperation repoOperation = getRepoOperation(ps);
        if (null == repoOperation) {
            throw BusinessResultException.create(ErrorCode.REQ_PARAMETER_ENTRUST_BS_ERROR);
        }
        boolean isSh = StrUtil.equals(tradeMarket, Market.SHSE.investValue);
        boolean isPos = StrUtil.equals(ps, InstructionPs.POSITIVEREPO.value);
        if (repoOperation == RepoOperation.ESTABLISH) {
            return isSh ? (isPos ? EntrustProp.SH_REPURCHASE_TRADE.value : EntrustProp.SH_REPURCHASE_TRADE_CONFIRM.value) : EntrustProp.SZ_REPURCHASE_BEGIN.value;
        } else if (repoOperation == RepoOperation.RENEW) {
            return isSh ? (isPos ? EntrustProp.SH_REPURCHASE_RENEWAL.value : EntrustProp.SH_REPURCHASE_RENEWAL_CONFIRM.value) : EntrustProp.SZ_REPURCHASE_RENEWAL.value;
        } else if (repoOperation == RepoOperation.RELEASE) {
            return isSh ? (isPos ? EntrustProp.SH_REPURCHASE_RELEASE.value : EntrustProp.SH_REPURCHASE_RELEASE_CONFIRM.value) : null;
        } else if (repoOperation == RepoOperation.CHANGE) {
            return isSh ? (isPos ? EntrustProp.SH_REPURCHASE_EXCHANGE.value : EntrustProp.SH_REPURCHASE_EXCHANGE_CONFIRM.value) : null;
        } else if (repoOperation == RepoOperation.PRECEDE) {
            return isSh ? (isPos ? EntrustProp.SH_REPURCHASE_TERMINATE.value : EntrustProp.SH_REPURCHASE_TERMINATE_CONFIRM.value) : EntrustProp.SZ_REPURCHASE_BUYBACK.value;
        } else if (repoOperation == RepoOperation.TERMINATE) {
            return isSh ? EntrustProp.SH_REPURCHASE_EXPIRE.value : EntrustProp.SZ_REPURCHASE_BUYBACK.value;
        }

        return null;
    }

    private RepoOperation getRepoOperation(String ps) {
        if (InstructionPs.POSITIVEREPO.value.equals(ps) || InstructionPs.REVERSEDREPO.value.equals(ps)) {
            return RepoOperation.ESTABLISH;
        }
        return RepoOperation.getRepoOperation(ps);
    }
}
