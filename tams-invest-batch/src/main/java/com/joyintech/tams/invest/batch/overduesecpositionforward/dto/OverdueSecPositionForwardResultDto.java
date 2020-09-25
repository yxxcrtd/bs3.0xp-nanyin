package com.joyintech.tams.invest.batch.overduesecpositionforward.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.common.base.entity.EtrSecPositionFlow;
import com.joyintech.tams.common.base.entity.PtlRecePositionFlow;
import com.joyintech.tams.common.base.entity.PtlSecPositionFlow;

import java.util.List;

public class OverdueSecPositionForwardResultDto extends NeedToWriteEntity {
    /**
     *  组合持仓流水dto
     */
    private List<PtlSecPositionFlow> ptlSecPositionFlowList;
    /**
     *  应收款持仓流水dto
     */
    private List<PtlRecePositionFlow> ptlRecePositionFlowList;
    /**
     *  委托指令持仓流水dto
     */
    private List<EtrSecPositionFlow> etrSecPositionFlowList;

    /**
     * 获取 组合持仓流水dto
     *
     * @return ptlSecPositionFlowList 组合持仓流水dto
     */
    public List<PtlSecPositionFlow> getPtlSecPositionFlowList() {
        return this.ptlSecPositionFlowList;
    }
    /**
     * 设置 组合持仓流水dto
     *
     * @param ptlSecPositionFlowList 组合持仓流水dto
     */
    public void setPtlSecPositionFlowList(List<PtlSecPositionFlow> ptlSecPositionFlowList) {
        this.ptlSecPositionFlowList = ptlSecPositionFlowList;
    }

    /**
     * 获取 应收款持仓流水dto
     *
     * @return ptlRecePositionFlowList 应收款持仓流水dto
     */
    public List<PtlRecePositionFlow> getPtlRecePositionFlowList() {
        return this.ptlRecePositionFlowList;
    }

    /**
     * 设置 应收款持仓流水dto
     *
     * @param ptlRecePositionFlowList 应收款持仓流水dto
     */
    public void setPtlRecePositionFlowList(List<PtlRecePositionFlow> ptlRecePositionFlowList) {
        this.ptlRecePositionFlowList = ptlRecePositionFlowList;
    }

    /**
     * 获取 委托指令持仓流水dto
     *
     * @return etrSecPositionFlowList 委托指令持仓流水dto
     */
    public List<EtrSecPositionFlow> getEtrSecPositionFlowList() {
        return this.etrSecPositionFlowList;
    }

    /**
     * 设置 委托指令持仓流水dto
     *
     * @param etrSecPositionFlowList 委托指令持仓流水dto
     */
    public void setEtrSecPositionFlowList(List<EtrSecPositionFlow> etrSecPositionFlowList) {
        this.etrSecPositionFlowList = etrSecPositionFlowList;
    }
}
