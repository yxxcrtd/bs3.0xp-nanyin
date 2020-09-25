package com.joyintech.tams.invest.common.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.PtlCashPositionFlow;
import com.joyintech.tams.common.base.entity.PtlSecPositionFlow;

/**
 * @program: tams-invest-parent
 * @description: 组合持仓流水、组合现金流水
 * @author: 强成西
 * @create: 2018-09-28 15:52
 **/
public class PtlPositionHoldDto {

    /**
     * 组合持仓流水
     */
    private List<PtlSecPositionFlowDto> ptlSecPositionFlows;

    /**
     * 组合现金流水
     */
    private List<PtlCashPositionFlowDto> ptlCashPositionFlows;

    /**
     * 获取 ptlSecPositionFlows
     *
     * @return ptlSecPositionFlows
     */
    public List<PtlSecPositionFlowDto> getPtlSecPositionFlows() {
        return ptlSecPositionFlows;
    }

    /**
     * 设置 ptlSecPositionFlows
     *
     * @param ptlSecPositionFlows ptlSecPositionFlows
     */
    public void setPtlSecPositionFlows(List<PtlSecPositionFlowDto> ptlSecPositionFlows) {
        this.ptlSecPositionFlows = ptlSecPositionFlows;
    }

    /**
     * 获取 ptlCashPositionFlows
     *
     * @return ptlCashPositionFlows
     */
    public List<PtlCashPositionFlowDto> getPtlCashPositionFlows() {
        return ptlCashPositionFlows;
    }

    /**
     * 设置 ptlCashPositionFlows
     *
     * @param ptlCashPositionFlows ptlCashPositionFlows
     */
    public void setPtlCashPositionFlows(List<PtlCashPositionFlowDto> ptlCashPositionFlows) {
        this.ptlCashPositionFlows = ptlCashPositionFlows;
    }
}
