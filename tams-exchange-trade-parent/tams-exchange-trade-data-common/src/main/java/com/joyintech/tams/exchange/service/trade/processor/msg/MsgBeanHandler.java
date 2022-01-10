package com.joyintech.tams.exchange.service.trade.processor.msg;

import com.joyintech.framework.rabbitmq.entity.SimpleRabbitMessage;
import com.joyintech.tams.exchange.common.bean.ExamineOrder;
import com.joyintech.tams.exchange.common.bean.TerminalId;
import com.joyintech.tams.exchange.common.db.domain.*;
import com.joyintech.tams.exchange.common.utils.JsonUtils;
import com.joyintech.tams.exchange.service.trade.processor.MessageConsumerService;
import com.joyintech.tams.exchange.service.trade.processor.TradeMsgProcessor;
import com.joyintech.tams.exchange.service.trade.transaction.TransactionHelper;
import com.lmax.disruptor.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MsgBeanHandler implements EventHandler<MsgBean> {
    private static final Logger logger = LoggerFactory.getLogger(MsgBeanHandler.class);
    @Autowired
    private TradeMsgProcessor tradeMsgProcessor;
    @Autowired
    private MessageConsumerService messageConsumerService;
    @Autowired
    private TransactionHelper transactionHelper;

    @Override
    public void onEvent(MsgBean msgBean, long l, boolean b) throws Exception {
        try {
            TradeMsgReceiver.consumerOffset.incrementAndGet();
            transactionHelper.clear();
            switch (msgBean.getMsgType()) {
                case MsgType.REQ_ENTRUST: {
                    tradeMsgProcessor.handleEntrust((Entrust) msgBean.getMsg(), msgBean.getTradeAgent(), MsgType.REQ_ENTRUST);
                    break;
                }
                case MsgType.RESP_ENTRUST:
                case MsgType.NOTIFY_ENTRUST_ERROR: {
                    tradeMsgProcessor.onRspEntrust((TerminalId) msgBean.getMsg());
                    break;
                }
                case MsgType.NOTIFY_ENTRUST: {
                    tradeMsgProcessor.onRtnEntrust((Entrust) msgBean.getMsg());
                    break;
                }
                case MsgType.NOTIFY_INTEGRATED: {
                    tradeMsgProcessor.onRtnIntegratedEntrust((IntegratedEntrust) msgBean.getMsg());
                    break;
                }
                case MsgType.NOTIFY_TRADE_RECORD: {
                    tradeMsgProcessor.onTrade((Trade) msgBean.getMsg());
                    break;
                }
                case MsgType.REQ_ENTRUST_CANCEL: {
                    tradeMsgProcessor.cancelEntrust((EntrustInterface) msgBean.getMsg());
                    break;
                }
                case MsgType.RESP_CANCEL: {
                    tradeMsgProcessor.onRspCancelEntrust((CancelEntrustBean) msgBean.getMsg());
                    break;
                }
                case MsgType.REQ_INTEGRATED: {
                    tradeMsgProcessor.handIntegratedEntrust((IntegratedEntrust) msgBean.getMsg(), msgBean.getTradeAgent(), MsgType.REQ_INTEGRATED);
                    break;
                }
                case MsgType.RESP_INTEGRATED: {
                    tradeMsgProcessor.onRspIntegratedEntrust((TerminalId) msgBean.getMsg());
                    break;
                }
                case MsgType.EXAMINE_ENTRUST: {
                    ExamineOrder order = (ExamineOrder) msgBean.getMsg();
                    tradeMsgProcessor.handleExamineEntrust((EntrustInterface) order.getOrder(), order.getInstructionsStatus(), order.getApproveReason(), order.getProcessType());
                    break;
                }
                case MsgType.EXAMINE_REVIEW: {
                    ExamineOrder order = (ExamineOrder) msgBean.getMsg();
                    tradeMsgProcessor.handleReviewExamine((EntrustInterface) order.getOrder(), order.getInstructionsStatus(), order.getApproveReason(), order.getProcessType());
                    break;
                }
                case MsgType.EXAMINE_REVOKE: {
                    ExamineOrder order = (ExamineOrder) msgBean.getMsg();
                    tradeMsgProcessor.handleRevokeExamine((EntrustInterface) order.getOrder(), order.getInstructionsStatus(), order.getApproveReason(), order.getProcessType());
                    break;
                }
                case MsgType.MQ_INSTRUCTION: {
                    SimpleRabbitMessage instructionKeyMsg = (SimpleRabbitMessage) msgBean.getMsg();
                    messageConsumerService.handleInstructionFromMQ(instructionKeyMsg);
                    break;
                }
                case MsgType.REQ_BATCH_ORDER: {
                    tradeMsgProcessor.genFirstBatchEntrust((BatchInfo) msgBean.getMsg());
                    break;
                }
                case MsgType.BATCH_ORDER_JOB: {
                    tradeMsgProcessor.handleBatchOrder((BatchInfo) msgBean.getMsg());
                    break;
                }
                case MsgType.FROZEN_PENDING_ENTRUST: {
                    tradeMsgProcessor.handleFrozenPendingApprovalEntrust((Entrust) msgBean.getMsg());
                    break;
                }
                case MsgType.FROZEN_PENDING_INTEGRATED: {
                    tradeMsgProcessor.handleFrozenPendingApprovalIntegratedEntrust((IntegratedEntrust) msgBean.getMsg());
                    break;
                }
                case MsgType.UNFROZEN_INNER: {
                    tradeMsgProcessor.unfrozenForInnerByCancelEntrust((EntrustInterface) msgBean.getMsg());
                    break;
                }
                case MsgType.REQ_CLOSE_ENTRUST: {
                    tradeMsgProcessor.closeEntrust((EntrustInterface) msgBean.getMsg());
                    break;
                }
                default: {
                    logger.warn("unknown msg type. msgType:{} msg:{}", msgBean.getMsgType(),
                            JsonUtils.getJsonFromObject(msgBean.getMsg()));
                }
            }
        } catch (Exception e) {
            TradeMsgReceiver.exceptionOffset.incrementAndGet();
            logger.warn("handle msg bean exception:{} ", e);
        }
        transactionHelper.save();
    }
}
