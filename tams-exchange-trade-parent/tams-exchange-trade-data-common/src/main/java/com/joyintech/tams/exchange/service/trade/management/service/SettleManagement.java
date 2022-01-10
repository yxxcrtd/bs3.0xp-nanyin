package com.joyintech.tams.exchange.service.trade.management.service;

import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.cache.impl.RedisCacheServiceImpl;
import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.datacommon.async.AsyncPersistenceWorker;
import com.joyintech.tams.exchange.service.trade.processor.HistoryHelper;
import com.joyintech.tams.exchange.service.trade.processor.TradeHelper;
import com.joyintech.tams.exchange.service.trade.processor.account.AccountPositionHelper;
import com.joyintech.tams.exchange.service.trade.processor.delay.DelayController;
import com.joyintech.tams.exchange.service.trade.processor.delay.OrderRateController;
import com.joyintech.tams.exchange.service.trade.processor.msg.MsgReceiverManager;
import com.joyintech.tams.exchange.service.trade.processor.msg.TradeMsgReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName SettleManagement
 * @Description 场内交易清算
 * @Author swwan
 * @Date 2020/9/1 14:12
 * @Version 1.0
 **/
@Component
public class SettleManagement {
    private static final Logger logger = LoggerFactory.getLogger(SettleManagement.class);

    @Autowired
    private HistoryHelper historyHelper;
    @Autowired
    private MsgReceiverManager msgReceiverManager;
    @Autowired
    private DelayController delayController;
    @Autowired
    private OrderRateController orderRateController;
    @Autowired
    private TradeHelper tradeHelper;
    @Autowired
    private CommonManagement commonManagement;
    @Autowired
    private AccountPositionHelper accountPositionHelper;

    private SimpleDateFormat tradingDayFormat = new SimpleDateFormat("yyyyMMdd");

    public String settleHistory() {
        Date date = commonManagement.getPlatDate();
        String nextDate = tradingDayFormat.format(date);
        ResultBean resultBean = commonManagement.getTradeDate("3", nextDate, 1);
        if (ErrorCode.isSuccess(resultBean.getErrorCode())) {
            nextDate = (String) resultBean.getData();
            logger.info("获取到下个交易日为：{}", nextDate);
        }
        logger.info("开始清算场内交易, 当前系统日期：{}", tradingDayFormat.format(date));
        //清空指令平台日小于当前平台日的指令数据,必须先删除，后面依赖此步骤
        historyHelper.deleteSysDateAfterInstructionDate(date);
        historyHelper.handleEntrustHistory(date, nextDate);
        historyHelper.handleOrderHistory(date);
        historyHelper.handleTradeHistory(date, nextDate);
        historyHelper.handleIntegratedEntrustHistory(date, nextDate);
        historyHelper.handleBatchInfoHistory(date);
        historyHelper.handleAlgorithmHistory(date);
        accountPositionHelper.clearPosition();
        //清空未通知成交和未完成的委托缓存
        tradeHelper.clearTradeAndEntrustCache();
        // 清空批量队列
        delayController.clear();
        // 清空账户流控队列
        orderRateController.clear();
        //清空指令队列映射
        msgReceiverManager.clearMsgReceiverMapping();
        logger.info("场内交易清算结束");

        logger.info("开始复位监控计数器");
        //清空监控缓存计数器
        TradeMsgReceiver.productOffset.set(0);
        TradeMsgReceiver.consumerOffset.set(0);
        TradeMsgReceiver.exceptionOffset.set(0);

        AsyncPersistenceWorker.productOffset.set(0);
        AsyncPersistenceWorker.consumerOffset.set(0);
        AsyncPersistenceWorker.exceptionOffset.set(0);

        RedisCacheServiceImpl.entrustRAMCount.set(0);
        RedisCacheServiceImpl.integratedEntrustRAMCount.set(0);
        RedisCacheServiceImpl.orderRAMCount.set(0);
        RedisCacheServiceImpl.tradeRAMCount.set(0);
        logger.info("监控计数器复位结束");

        return ErrorCode.SUCCESS.errorMsg;
    }
}
