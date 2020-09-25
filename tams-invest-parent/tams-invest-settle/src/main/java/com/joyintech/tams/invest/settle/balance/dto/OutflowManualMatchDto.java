package com.joyintech.tams.invest.settle.balance.dto;

import java.util.List;

import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.invest.settle.balance.validator.OutflowManualMatchDtoValidator;

/**
 * @program: tams-invest-parent
 * @description:
 * @author: 强成西
 * @create: 2018-12-04 10:34
 **/
@ValidLogic(targetClass = OutflowManualMatchDtoValidator.class, groups = {ValidateGroupEdit.class})
public class OutflowManualMatchDto {

    /**
     * 系统流水
     */
    List<OutflowFundsSystemFlowDto> sysFlow;

    /**
     * 托管流水
     */
    List<OutflowFundsSystemFlowDto> enFlow;

    /**
     * 备注
     */
    private String remark;

    /**
     * 匹配组编号
     */
    private String groupId;

    /**
     * 是否是取消匹配
     */
    private boolean isCancel;

    /**
     * 获取 isCancel
     *
     * @return isCancel
     */
    public boolean isCancel() {
        return isCancel;
    }

    /**
     * 设置 isCancel
     *
     * @param isCancel isCancel
     */
    public void setCancel(boolean cancel) {
        isCancel = cancel;
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

    /**
     * 获取 sysFlow
     *
     * @return sysFlow
     */
    public List<OutflowFundsSystemFlowDto> getSysFlow() {
        return sysFlow;
    }

    /**
     * 设置 sysFlow
     *
     * @param sysFlow sysFlow
     */
    public void setSysFlow(List<OutflowFundsSystemFlowDto> sysFlow) {
        this.sysFlow = sysFlow;
    }

    /**
     * 获取 enFlow
     *
     * @return enFlow
     */
    public List<OutflowFundsSystemFlowDto> getEnFlow() {
        return enFlow;
    }

    /**
     * 设置 enFlow
     *
     * @param enFlow enFlow
     */
    public void setEnFlow(List<OutflowFundsSystemFlowDto> enFlow) {
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
}
