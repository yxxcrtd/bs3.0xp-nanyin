package com.joyintech.tams.invest.common.dto.settle.balance;

import java.util.List;

/**
 * @program: tams-invest-parent
 * @description:
 * @author: 强成西
 * @create: 2018-12-04 10:34
 **/
public class OutflowManualMatchDto {

    /**
     * 系统流水
     */
    List<BaseOutflowFundsSystemFlowDto> sysFlow;

    /**
     * 托管流水
     */
    List<BaseOutflowFundsSystemFlowDto> enFlow;

    /**
     * 匹配组编号
     */
    private String groupId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 获取 sysFlow
     *
     * @return sysFlow
     */
    public List<BaseOutflowFundsSystemFlowDto> getSysFlow() {
        return sysFlow;
    }

    /**
     * 设置 sysFlow
     *
     * @param sysFlow sysFlow
     */
    public void setSysFlow(List<BaseOutflowFundsSystemFlowDto> sysFlow) {
        this.sysFlow = sysFlow;
    }

    /**
     * 获取 enFlow
     *
     * @return enFlow
     */
    public List<BaseOutflowFundsSystemFlowDto> getEnFlow() {
        return enFlow;
    }

    /**
     * 设置 enFlow
     *
     * @param enFlow enFlow
     */
    public void setEnFlow(List<BaseOutflowFundsSystemFlowDto> enFlow) {
        this.enFlow = enFlow;
    }

    /**
     * 获取 remark
     *
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 remark
     *
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取 groupId
     *
     * @return groupId
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置 groupId
     *
     * @param groupId groupId
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
