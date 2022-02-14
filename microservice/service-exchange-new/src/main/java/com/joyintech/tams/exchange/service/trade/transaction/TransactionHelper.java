package com.joyintech.tams.exchange.service.trade.transaction;


import com.joyintech.redis.core.base.PipeWriteService;
import com.joyintech.tams.exchange.common.cache.config.DataServiceConfig;
import com.joyintech.tams.exchange.common.constant.DBSaveType;
import com.joyintech.tams.exchange.common.db.domain.*;
import com.joyintech.tams.exchange.datacommon.db.service.*;
import com.joyintech.tams.exchange.datacommon.sync.SyncPersistenceWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class TransactionHelper {
    private static final Logger logger = LoggerFactory.getLogger(TransactionHelper.class);
    private ThreadLocal<ParamContext> context = new ThreadLocal<ParamContext>() {
        public ParamContext initialValue() {
            return new ParamContext();
        }
    };

    @Autowired
    private OrderService orderService;
    @Autowired
    private TradeService tradeService;
    @Autowired
    private InstructionService instructionService;
    @Autowired
    private InstructionPledgeService instructionPledgeService;
    @Autowired
    private EntrustService entrustService;
    @Autowired
    private IntegratedEntrustService integratedEntrustService;
    @Autowired
    private BatchInfoService batchInfoService;
    @Autowired
    private PipeWriteService pipeWriteService;
    @Autowired
    private DataServiceConfig dataServiceConfig;
    @Autowired
    private SyncPersistenceWorker syncPersistenceWorker;
    @Autowired
    private PositionBeanService positionBeanService;

    public Map<String, Order> getOrderMap() {
        return context.get().getOrderMap();
    }

    public void addOrder(Order order) {
        context.get().addOrder(order);
    }

    public Trade getTradeRecord() {
        return context.get().getTradeRecord();
    }

    public void setTradeRecord(Trade tradeRecord) {
        if (Objects.nonNull(tradeRecord)) {
            tradeRecord.setId(tradeService.genDBId());
            context.get().setTradeRecord(tradeRecord);
        }
    }

    public Map<String, Entrust> getEntrustMap() {
        return context.get().getEntrustMap();
    }

    public void addEntrust(Entrust entrust) {
        context.get().addEntrust(entrust);
    }

    public Map<String, IntegratedEntrust> getIntegratedEntrustMap() {
        return context.get().getIntegratedEntrustMap();
    }

    public void addIntegratedEntrust(IntegratedEntrust integratedEntrust) {
        context.get().addIntegratedEntrust(integratedEntrust);
    }

    public Instruction getInstructionBean() {
        return context.get().getInstruction();
    }

    public void setInstructionBean(Instruction instruction) {
        context.get().setInstruction(instruction);
    }

    public InstructionPledge getInstructionPledge() {
        return context.get().getInstructionPledge();
    }

    public void setInstructionPledge(InstructionPledge instructionPledge) {
        context.get().setInstructionPledge(instructionPledge);
    }

    public BatchInfo getBatchInfo() {
        return context.get().getBatchInfo();
    }

    public void setBatchInfo(BatchInfo batchInfo) {
        context.get().setBatchInfo(batchInfo);
    }

    public void addPositionBean(PositionBean positionBean) {
        context.get().addPositionBeanMap(positionBean);
    }

    public Map<String, PositionBean> getPositionBean() {
        return context.get().getPositionBeanMap();
    }

    public void save() {
        try {
            ParamContext paramContext = context.get();
            int dbSaveType = dataServiceConfig.isAsyncDb() ? DBSaveType.ASYNC.value : DBSaveType.SYNC_BATCH.value;
            //暂时都是String的主键
            Map<String, Map<String, ?>> redisMap = new HashMap<>();
            Map<String, Order> orderMap = paramContext.getOrderMap();
            if (!orderMap.isEmpty()) {
                orderService.asyncUpdate(orderMap.values(), dbSaveType);
                redisMap.put(Order.class.getName(), orderMap);
            }
            if (Objects.nonNull(paramContext.getTradeRecord())) {
                Map<String, Trade> tradeMap = new HashMap<>();
                tradeService.asyncUpdate(paramContext.getTradeRecord(), dbSaveType);
                tradeMap.put(paramContext.getTradeRecord().getId(), paramContext.getTradeRecord());
                redisMap.put(Trade.class.getName(), tradeMap);
            }
            if (Objects.nonNull(paramContext.getInstruction())) {
                Map<String, Instruction> instructionBeanMap = new HashMap<>();
                instructionService.asyncUpdate(paramContext.getInstruction(), dbSaveType);
                instructionBeanMap.put(paramContext.getInstruction().getEntrustId(), paramContext.getInstruction());
                redisMap.put(Instruction.class.getName(), instructionBeanMap);
            }
            if (Objects.nonNull(paramContext.getInstructionPledge())) {
                Map<String, InstructionPledge> InstructionPledgeMap = new HashMap<>();
                instructionPledgeService.asyncUpdate(paramContext.getInstructionPledge(), dbSaveType);
                InstructionPledgeMap.put(paramContext.getInstruction().getEntrustId(), paramContext.getInstructionPledge());
                redisMap.put(InstructionPledge.class.getName(), InstructionPledgeMap);
            }
            Map<String, Entrust> entrustMap = paramContext.getEntrustMap();
            if (!entrustMap.isEmpty()) {
                entrustService.asyncUpdate(entrustMap.values(), dbSaveType);
                redisMap.put(Entrust.class.getName(), entrustMap);
            }
            Map<String, IntegratedEntrust> integratedEntrustMap = paramContext.getIntegratedEntrustMap();
            if (!integratedEntrustMap.isEmpty()) {
                integratedEntrustService.asyncUpdate(integratedEntrustMap.values(), dbSaveType);
                redisMap.put(IntegratedEntrust.class.getName(), integratedEntrustMap);
            }
            if (Objects.nonNull(paramContext.getBatchInfo())) {
                Map<String, BatchInfo> batchInfoMap = new HashMap<>();
                batchInfoMap.put(paramContext.getBatchInfo().getId(), paramContext.getBatchInfo());
                batchInfoService.asyncUpdate(paramContext.getBatchInfo(), dbSaveType);
                redisMap.put(BatchInfo.class.getName(), batchInfoMap);
            }
            Map<String, PositionBean> positionBeanMap = paramContext.getPositionBeanMap();
            if (!positionBeanMap.isEmpty()) {
                positionBeanService.asyncUpdate(positionBeanMap.values(), dbSaveType);
                redisMap.put(PositionBean.class.getName(), positionBeanMap);
            }
            // 是否同步写入redis
            if (dataServiceConfig.isUseRedis()) {
                pipeWriteService.writeList(redisMap);
            }
            // 是否同步写入db
            if (!dataServiceConfig.isAsyncDb()) {
                syncPersistenceWorker.syncDb();
            }
        } catch (Exception e) {
            logger.error("transaction save error: {}", e);
        }
        clear();
    }

    public void clear() {
        context.get().clear();
    }

}
