package com.joyintech.tams.exchange.service.trade.processor;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.joyintech.framework.rabbitmq.annotation.MessageSender;
import com.joyintech.framework.rabbitmq.entity.SimpleRabbitMessage;
import com.joyintech.tams.exchange.common.bean.investment.ProgressMessage;
import com.joyintech.tams.exchange.common.bean.investment.TradeMessage;
import com.joyintech.tams.exchange.common.constant.BusinessFlag;
import com.joyintech.tams.exchange.common.constant.EntrustBs;
import com.joyintech.tams.exchange.common.constant.EntrustProp;
import com.joyintech.tams.exchange.common.constant.InvestmentAssetType;
import com.joyintech.tams.exchange.common.constant.instruction.BusinessType;
import com.joyintech.tams.exchange.common.constant.joyin.InvestmentAssetType1;
import com.joyintech.tams.exchange.common.db.domain.*;
import com.joyintech.tams.exchange.common.utils.IntegratedEntrustUtils;
import com.joyintech.tams.exchange.common.utils.JsonUtils;
import com.joyintech.tams.exchange.common.utils.TradeUtils;
import com.joyintech.tams.exchange.datacommon.db.service.InstructionPledgeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Service
public class MessageSenderService {
    private static final Logger logger = LoggerFactory.getLogger(MessageSenderService.class);
    @Autowired
    private InstructionPledgeService instructionPledgeService;

    @MessageSender(exchange = "exchange.fanout.exchange.trade", routingKey = "exchange.trade.use")
    public SimpleRabbitMessage pushTradeToMQ(Trade trade, Instruction instruction, EntrustInterface entrust) {
        if (Objects.nonNull(trade) && !Strings.isNullOrEmpty(trade.getId())) {
            logger.info("pushTradeToMQ trade {}", trade.getId());
            TradeMessage tradeMessage = TradeMessage.createTradeMessage(trade);
            if (Objects.nonNull(instruction) && Objects.nonNull(tradeMessage)) {
                createTradeMsg(trade, instruction, entrust, tradeMessage);
                //todo 期货保证金
                if (InvestmentAssetType.FUTURES.value.equals(instruction.getAssetType())) {
                    tradeMessage.setDealMargin(TradeUtils.multiply(trade.getTradedBalance(), BigDecimal.valueOf(0.1), 0));
                }
            } else {
                logger.warn("instruction not found: {}, trade id :{}", trade.getInstructionId(), trade.getId());
            }
            SimpleRabbitMessage defaultRabbitMessage = new SimpleRabbitMessage();
            defaultRabbitMessage.setId(trade.getId());
            String tradeMessageJson = JsonUtils.getJsonFromObject(tradeMessage);
            defaultRabbitMessage.setMessage(tradeMessageJson);
            logger.info("推送成交消息 {}", JsonUtils.getJsonFromObject(tradeMessageJson));
            return defaultRabbitMessage;
        }
        return null;
    }

    @MessageSender(exchange = "exchange.fanout.exchange.manualSettleTrade", routingKey = "exchange.manualSettleTrade.use")
    public SimpleRabbitMessage pushTradeToMQForFixedIncome(Trade trade, Instruction instruction, EntrustInterface entrust) {
        if (Objects.nonNull(trade) && !Strings.isNullOrEmpty(trade.getId())) {
            TradeMessage tradeMessage = TradeMessage.createTradeMessage(trade);
            if (Objects.nonNull(instruction) && Objects.nonNull(tradeMessage)) {
                createTradeMsg(trade, instruction, entrust, tradeMessage);
                tradeMessage.setSettleDay(trade.getTradingDay());
                if (EntrustBs.BUY.value.equals(trade.getEntrustBs())) {
                    tradeMessage.setBusiType(BusinessType.FIXED_INCOME_BUY.value);
                } else {
                    tradeMessage.setBusiType(BusinessType.FIXED_INCOME_SELL.value);
                }
            } else {
                logger.warn("未找到对应指令信息: {}, 成交编号 :{}", trade.getInstructionId(), trade.getId());
            }
            SimpleRabbitMessage defaultRabbitMessage = new SimpleRabbitMessage();
            defaultRabbitMessage.setId(trade.getId());
            String tradeMessageJson = JsonUtils.getJsonFromObject(tradeMessage);
            defaultRabbitMessage.setMessage(tradeMessageJson);
            logger.info("推送手工交手成交消息 {}", JsonUtils.getJsonFromObject(tradeMessageJson));
            return defaultRabbitMessage;
        }
        return null;
    }

    private void createTradeMsg(Trade trade, Instruction instruction, EntrustInterface entrust, TradeMessage tradeMessage) {
        tradeMessage.setAcctNo(instruction.getAcctNo());
        tradeMessage.setSecAcctId(instruction.getSecAcctId());
        tradeMessage.setPortfolioId(instruction.getPortfolioId());
        tradeMessage.setTradeMarket(instruction.getTradeMarket());
        tradeMessage.setTradePlat(instruction.getTradePlat());
        tradeMessage.setPS(instruction.getPs());
        tradeMessage.setCcy(instruction.getCcy());
        tradeMessage.setTradingDay(trade.getTradingDay());
        if (InvestmentAssetType1.BIDDING_REPO.value.equals(instruction.getAssetType1())) {
            //投管要求质押式回购这里传入期限品种
            tradeMessage.setFinprodMarketId(trade.getStockName());
        } else if (InvestmentAssetType.BOND.value.equals(instruction.getAssetType())) {
            //投管要求债券买卖，成交价格统一填净价价格
            tradeMessage.setDealPrice(entrust.getEntrustPrice());
        }
        String finprodId = instruction.getFinprodId();
        if (BusinessFlag.PROTOCOL_BACK.value.equals(trade.getBusinessFlag())) {
            tradeMessage.setValueDate(((IntegratedEntrust) entrust).getFirstSettleDate());
            tradeMessage.setMaturityDate(((IntegratedEntrust) entrust).getBackDate());
            tradeMessage.setCounterId((((IntegratedEntrust) entrust).getLiaisonName()));
            // 确保协议回购，无抵押券时才组装相关的finprodId
            InstructionPledge instructionPledge = instructionPledgeService.queryInstructionPledgesByInstructionIdAndStockCode(instruction.getEntrustId(), entrust.getStockCode());
            if (Objects.isNull(instructionPledge) && Strings.isNullOrEmpty(finprodId)) {
                finprodId = IntegratedEntrustUtils.formatFinprodId(entrust.getAssetType(), entrust.getStockCode());
                logger.info("未找到相关抵押券信息,填入 FinprodId {}", finprodId);
            }
            //当期续作首期交易编号，更新为当前笔成交编号+
            if (EntrustProp.isRepurchase_First(entrust.getEntrustProp())) {
                tradeMessage.setTrdMatchID(trade.getBrokerTradeId());
            } else {
                tradeMessage.setTrdMatchID(((IntegratedEntrust) entrust).getOrigSerialNo());
                // 中途换券，投管要求传入折算比列
                if (EntrustProp.isRepurchaseExchange(trade.getEntrustProp())) {
                    tradeMessage.setPledgeRatio(trade.getDiscount());
                }
            }
        }
        tradeMessage.setFinprodId(finprodId);
    }

    @MessageSender(exchange = "exchange.fanout.exchange.progress", routingKey = "exchange.progress.update")
    public SimpleRabbitMessage pushProgressToMQ(Instruction instruction) {
        try {
            if (Objects.nonNull(instruction) && !Strings.isNullOrEmpty(instruction.getEntrustId())) {
                ProgressMessage progressMessage = ProgressMessage.createTradeMessage(instruction);
                if (Objects.nonNull(progressMessage)) {
                    logger.info("pushProgressToMQ progressMessage {}", JSONObject.toJSONString(progressMessage));
                    SimpleRabbitMessage defaultRabbitMessage = new SimpleRabbitMessage();
                    defaultRabbitMessage.setId(UUID.randomUUID().toString().replace("-", ""));
                    defaultRabbitMessage.setMessage(JsonUtils.getJsonFromObject(progressMessage));
                    return defaultRabbitMessage;
                }
            }
        } catch (Exception e) {
            logger.info("pushProgressToMQ error: ", e);
        }
        return null;
    }
}
