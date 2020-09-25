package com.joyintech.tams.invest.common.dto.etr;

import java.util.List;

/**
 * @program: tams-invest-parent
 * @description: 组合持仓流水、组合现金流水
 * @author: 强成西
 * @create: 2018-09-28 15:52
 **/
public class EtrPositionHoldDto {

    /**
     * 做委托发起、关闭为true
     */
    private boolean dealEtr;

    /**
     * 是否撤销
     */
    private boolean needNegative;

    /**
     * 组合持仓流水
     */
    private List<EtrSecPositionFlowDto> ptlSecPositionFlows;

    /**
     * 组合现金流水
     */
    private List<EtrCashPositionFlowDto> ptlCashPositionFlows;

    /**
     * 获取 ptlSecPositionFlows
     *
     * @return ptlSecPositionFlows
     */
    public List<EtrSecPositionFlowDto> getPtlSecPositionFlows() {
        return ptlSecPositionFlows;
    }

    /**
     * 设置 ptlSecPositionFlows
     *
     * @param ptlSecPositionFlows ptlSecPositionFlows
     */
    public void setPtlSecPositionFlows(List<EtrSecPositionFlowDto> ptlSecPositionFlows) {
        this.ptlSecPositionFlows = ptlSecPositionFlows;
    }

    /**
     * 获取 ptlCashPositionFlows
     *
     * @return ptlCashPositionFlows
     */
    public List<EtrCashPositionFlowDto> getPtlCashPositionFlows() {
        return ptlCashPositionFlows;
    }

    /**
     * 设置 ptlCashPositionFlows
     *
     * @param ptlCashPositionFlows ptlCashPositionFlows
     */
    public void setPtlCashPositionFlows(List<EtrCashPositionFlowDto> ptlCashPositionFlows) {
        this.ptlCashPositionFlows = ptlCashPositionFlows;
    }

    /**
     * 获取 dealEtr
     *
     * @return dealEtr
     */
    public boolean isDealEtr() {
        return dealEtr;
    }

    /**
     * 设置 dealEtr
     *
     * @param dealEtr dealEtr
     */
    public void setDealEtr(boolean dealEtr) {
        this.dealEtr = dealEtr;
    }

    /**
     * 获取 needNegative
     *
     * @return needNegative
     */
    public boolean isNeedNegative() {
        return needNegative;
    }

    /**
     * 设置 needNegative
     *
     * @param needNegative needNegative
     */
    public void setNeedNegative(boolean needNegative) {
        this.needNegative = needNegative;
    }
}
