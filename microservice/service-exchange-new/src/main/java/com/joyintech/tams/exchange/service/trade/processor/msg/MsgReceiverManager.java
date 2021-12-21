package com.joyintech.tams.exchange.service.trade.processor.msg;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.joyintech.tams.exchange.datacommon.db.service.InstructionService;
import com.joyintech.tams.exchange.common.db.domain.Entrust;
import com.joyintech.tams.exchange.common.db.domain.IntegratedEntrust;
import com.joyintech.tams.exchange.common.utils.SpringContextUtils;
import com.joyintech.tams.exchange.datacommon.db.service.EntrustService;
import com.joyintech.tams.exchange.datacommon.db.service.IntegratedEntrustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class MsgReceiverManager {
    @Value("${trade.thread.msg.num}")
    private int msgReceiverSize;

    @Autowired
    private EntrustService entrustService;
    @Autowired
    private IntegratedEntrustService integratedEntrustService;

    @Autowired
    private InstructionService instructionService;

    private List<TradeMsgReceiver> msgReceivers;

    // 账号ID映射线程
    private Map<String, Integer> msgReceiverMapping = Maps.newConcurrentMap();

    // 线程id对应数量
    private Map<Integer, Integer> msgCounts = Maps.newConcurrentMap();


    public void init() {
        msgReceivers = Lists.newArrayListWithCapacity(msgReceiverSize);
        for (int i = 0; i < msgReceiverSize; i++) {
            msgReceivers.add(SpringContextUtils.getBean(TradeMsgReceiver.class));
            msgCounts.put(i, 0);
        }
        initFundMsgReceiver();
    }

    public TradeMsgReceiver getMsgReceiverByInstruction(String instructionId) {
        Integer threadIndex = msgReceiverMapping.get(instructionId);
        if (Objects.isNull(threadIndex)) {
            threadIndex = 0;
            int minCount = msgCounts.get(0);
            for (Map.Entry<Integer, Integer> entry : msgCounts.entrySet()) {
                if (entry.getValue() < minCount) {
                    threadIndex = entry.getKey();
                    minCount = entry.getValue();
                }
            }
            msgReceiverMapping.put(instructionId, threadIndex);
            msgCounts.put(threadIndex, minCount + 1);
        }
        return msgReceivers.get(threadIndex);
    }

    public TradeMsgReceiver getReceiverByEntrustId(String entrustId) {
        Entrust entrust = entrustService.findById(entrustId);
        if (Objects.nonNull(entrust)) {
            return getMsgReceiverByInstruction(entrust.getInstructionId());
        }
        return null;
    }

    public TradeMsgReceiver getReceiverByIntegratedEntrustId(String entrustId) {
        IntegratedEntrust entrust = integratedEntrustService.findById(entrustId);
        if (Objects.nonNull(entrust)) {
            return getMsgReceiverByInstruction(entrust.getInstructionId());
        }
        return null;
    }

    public TradeMsgReceiver getTradeReceiverByEntrustId(String entrustId) {
        Entrust entrust = entrustService.findById(entrustId);
        if (Objects.nonNull(entrust)) {
            return getMsgReceiverByInstruction(entrust.getInstructionId());
        } else {
            IntegratedEntrust integratedEntrust = integratedEntrustService.findById(entrustId);
            if (Objects.nonNull(integratedEntrust)) {
                return getMsgReceiverByInstruction(integratedEntrust.getInstructionId());
            }
        }
        return null;
    }

    public void initFundMsgReceiver() {
        instructionService.findAll().forEach(instructionBean -> getMsgReceiverByInstruction(instructionBean.getEntrustId()));
    }

    public void clearMsgReceiverMapping() {
        msgReceiverMapping.clear();
    }
}
