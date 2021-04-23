package com.joyintech.tams.exchange.service.trade.transaction;

import com.joyintech.tams.exchange.common.db.domain.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParamContext {
    private Map<String, Order> orderMap = new HashMap<>();
    private Trade tradeRecord = null;
    private Instruction instruction = null;
    private InstructionPledge instructionPledge = null;
    private BatchInfo batchInfo = null;
    private Map<String, PositionBean> positionBeanMap = new HashMap<>();
    private Map<String, Entrust> entrustMap = new HashMap<>();
    private Map<String, IntegratedEntrust> integratedEntrustMap = new HashMap<>();

    public void clear() {
        entrustMap.clear();
        integratedEntrustMap.clear();
        orderMap.clear();
        positionBeanMap.clear();
        tradeRecord = null;
        instruction = null;
        instructionPledge = null;
        batchInfo = null;
    }

    public Map<String, Order> getOrderMap() {
        return orderMap;
    }

    public void addOrder(Order order) {
        if (Objects.nonNull(order)) orderMap.put(order.getId(), order);
    }

    public Trade getTradeRecord() {
        return tradeRecord;
    }

    public void setTradeRecord(Trade tradeRecord) {
        this.tradeRecord = tradeRecord;
    }

    public Map<String, Entrust> getEntrustMap() {
        return entrustMap;
    }

    public void addEntrust(Entrust entrust) {
        if (Objects.nonNull(entrust)) entrustMap.put(entrust.getId(), entrust);
    }

    public Instruction getInstruction() {
        return instruction;
    }

    public void setInstruction(Instruction instruction) {
        this.instruction = instruction;
    }

    public InstructionPledge getInstructionPledge() {
        return instructionPledge;
    }

    public void setInstructionPledge(InstructionPledge instructionPledge) {
        this.instructionPledge = instructionPledge;
    }

    public Map<String, IntegratedEntrust> getIntegratedEntrustMap() {
        return integratedEntrustMap;
    }

    public void addIntegratedEntrust(IntegratedEntrust integratedEntrust) {
        if (Objects.nonNull(integratedEntrust)) integratedEntrustMap.put(integratedEntrust.getId(), integratedEntrust);
    }

    public BatchInfo getBatchInfo() {
        return batchInfo;
    }

    public void setBatchInfo(BatchInfo batchInfo) {
        this.batchInfo = batchInfo;
    }

    public Map<String, PositionBean> getPositionBeanMap() {
        return positionBeanMap;
    }

    public void addPositionBeanMap(PositionBean positionBean) {
        if (Objects.nonNull(positionBean)) positionBeanMap.put(positionBean.getId(), positionBean);
    }
}
