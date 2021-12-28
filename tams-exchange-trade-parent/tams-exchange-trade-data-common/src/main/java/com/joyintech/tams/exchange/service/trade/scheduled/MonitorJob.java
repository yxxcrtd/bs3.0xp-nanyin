package com.joyintech.tams.exchange.service.trade.scheduled;

import cn.hutool.core.util.ObjectUtil;
import com.joyintech.tams.exchange.common.constant.MonitorType;
import com.joyintech.tams.exchange.common.db.domain.Monitor;
import com.joyintech.tams.exchange.common.db.domain.MonitorHistory;
import com.joyintech.tams.exchange.datacommon.async.AsyncPersistenceWorker;
import com.joyintech.tams.exchange.datacommon.db.service.MonitorHistoryService;
import com.joyintech.tams.exchange.datacommon.db.service.MonitorService;
import com.joyintech.tams.exchange.service.trade.management.service.CommonManagement;
import com.joyintech.tams.exchange.service.trade.processor.msg.TradeMsgReceiver;
import com.joyintech.tams.exchange.service.trade.server.agent.AccountManager;
import com.joyintech.tams.exchange.service.trade.server.agent.TradeAgentManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 账号登录任务
 */

@Component
@EnableScheduling
public class MonitorJob {
    private static final Logger logger = LoggerFactory.getLogger(MonitorJob.class);
    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    private MonitorService monitorService;
    @Autowired
    private MonitorHistoryService monitorHistoryService;
    @Autowired
    private CommonManagement commonManagement;
    @Autowired
    private AccountManager accountManager;
    /**
     * 归档标识
     */
    private boolean isStartHistory;
    /**
     * 监控标识
     */
    private boolean isFinishSave;

    private String getCurrentTime() {
        return df.format(new Date());
    }

    @Scheduled(fixedRateString = "${scheduled.monitor.fixedRate:60000}")
    public void saveMonitor() {
        if (isStartHistory) {
            return;
        }

        isFinishSave = false;
        String currentTime = getCurrentTime();

        monitorService.insert(currentTime, MonitorType.BUSI_ACCOUNT_COUNT.value, getBusiAccountCount());
        monitorService.insert(currentTime, MonitorType.ACCUMULATE_PERSISTENCE_COUNT.value, getAccumulatePersistenceCount());
        monitorService.insert(currentTime, MonitorType.EXCEPTION_PERSISTENCE_COUNT.value, getExceptionPersistenceCount());
        monitorService.insert(currentTime, MonitorType.ACCUMULATE_TRADEMSG_COUNT.value, getAccumulateTradeMsgCount());
        monitorService.insert(currentTime, MonitorType.EXCEPTION_TRADEMSG_COUNT.value, getExceptionTradeMsgCount());

        isFinishSave = true;
    }

    @Scheduled(cron = "${scheduled.monitor.corn:0 0 0 * * ?}")
    public void handleMonitorHistory() {
        // 停止监控
        isStartHistory = true;
        while (!isFinishSave) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                logger.error("归档监控数据异常: " + Arrays.toString(e.getStackTrace()));
            }
        }

        // 开始归档
        String platDateStr = commonManagement.getPlatDateStr();
        logger.info("开始归档监控数据，当前系统平台日 {}", platDateStr);
        List<Monitor> monitors = monitorService.findAll();
        List<MonitorHistory> monitorHistories = new ArrayList<>();
        if (Objects.nonNull(monitors) && monitors.size() > 0) {
            int count = 0;
            for (Monitor monitor : monitors) {
                ++count;
                MonitorHistory monitorHistory = new MonitorHistory();
                BeanUtils.copyProperties(monitor, monitorHistory, "id");
                monitorHistory.setRecordId(monitor.getId());
                monitorHistories.add(monitorHistory);
                if (count % 1000 == 0 || count == monitors.size()) {
                    monitorHistoryService.saveMonitorHistory(monitorHistories);
                    monitorHistories.clear();
                }
            }
            monitorService.asyncDel(monitors);
            logger.info("监控数据归档完成,归历史监控数量 {}", monitors.size());
        }

        // 开始监控
        isStartHistory = false;
    }

    private int getBusiAccountCount() {
        return accountManager.getAccounts().size();
    }

    private int getAccumulatePersistenceCount() {
        return AsyncPersistenceWorker.productOffset.intValue() - AsyncPersistenceWorker.consumerOffset.intValue();
    }

    private int getAccumulateTradeMsgCount() {
        return TradeMsgReceiver.productOffset.intValue() - TradeMsgReceiver.consumerOffset.intValue();
    }

    private int getExceptionPersistenceCount() {
        return AsyncPersistenceWorker.exceptionOffset.intValue();
    }

    private int getExceptionTradeMsgCount() {
        return TradeMsgReceiver.exceptionOffset.intValue();
    }
}
