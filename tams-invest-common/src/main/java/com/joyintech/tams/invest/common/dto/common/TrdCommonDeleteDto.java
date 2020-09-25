package com.joyintech.tams.invest.common.dto.common;

import java.util.List;

import com.joyintech.tams.common.base.entity.WflTrdFinProductDealTotal;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;

/**
 * @program: tams-invest-parent
 * @description: 流程作废时需要操作的dto
 * @author: 强成西
 * @create: 2018-10-25 16:35
 **/
public class TrdCommonDeleteDto {

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 汇总交易流程表
     */
    private WflTrdFinProductDealTotal wflTrdFinProductDealTotal;

    /**
     * 组合持仓
     */
    private List<PtlSecPositionFlowDto> ptlSecPositions;

    /**
     * 组合流水
     */
    private List<PtlCashPositionFlowDto> ptlCashPositions;

    /**
     * 获取 workflowId
     *
     * @return workflowId
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 workflowId
     *
     * @param workflowId workflowId
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    /**
     * 获取 wflTrdFinProductDealTotal
     *
     * @return wflTrdFinProductDealTotal
     */
    public WflTrdFinProductDealTotal getWflTrdFinProductDealTotal() {
        return wflTrdFinProductDealTotal;
    }

    /**
     * 设置 wflTrdFinProductDealTotal
     *
     * @param wflTrdFinProductDealTotal wflTrdFinProductDealTotal
     */
    public void setWflTrdFinProductDealTotal(WflTrdFinProductDealTotal wflTrdFinProductDealTotal) {
        this.wflTrdFinProductDealTotal = wflTrdFinProductDealTotal;
    }

    /**
     * 获取 ptlSecPositions
     *
     * @return ptlSecPositions
     */
    public List<PtlSecPositionFlowDto> getPtlSecPositions() {
        return ptlSecPositions;
    }

    /**
     * 设置 ptlSecPositions
     *
     * @param ptlSecPositions ptlSecPositions
     */
    public void setPtlSecPositions(List<PtlSecPositionFlowDto> ptlSecPositions) {
        this.ptlSecPositions = ptlSecPositions;
    }

    /**
     * 获取 ptlCashPositions
     *
     * @return ptlCashPositions
     */
    public List<PtlCashPositionFlowDto> getPtlCashPositions() {
        return ptlCashPositions;
    }

    /**
     * 设置 ptlCashPositions
     *
     * @param ptlCashPositions ptlCashPositions
     */
    public void setPtlCashPositions(List<PtlCashPositionFlowDto> ptlCashPositions) {
        this.ptlCashPositions = ptlCashPositions;
    }

    /**
     * tradeId
     * @return tradeId
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * tradeId
     * @param tradeId
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }
}
