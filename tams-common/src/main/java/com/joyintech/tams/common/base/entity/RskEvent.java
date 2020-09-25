package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskEvent extends BaseEntity {
    /**
     * 事件代码
     */
    private String eventCode;

    /**
     * 事件名称
     */
    private String eventName;

    /**
     * 是否计算指标
     */
    private String isCalcIndicator;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 事件代码
     * @return 事件代码
     */
    public String getEventCode() {
        return eventCode;
    }

    /**
     * 设置 事件代码
     * @param eventCode 事件代码
     */
    public void setEventCode(String eventCode) {
        this.eventCode = eventCode == null ? null : eventCode.trim();
    }

    /**
     * 取得 事件名称
     * @return 事件名称
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * 设置 事件名称
     * @param eventName 事件名称
     */
    public void setEventName(String eventName) {
        this.eventName = eventName == null ? null : eventName.trim();
    }

    /**
     * 取得 是否计算指标
     * @return 是否计算指标
     */
    public String getIsCalcIndicator() {
        return isCalcIndicator;
    }

    /**
     * 设置 是否计算指标
     * @param isCalcIndicator 是否计算指标
     */
    public void setIsCalcIndicator(String isCalcIndicator) {
        this.isCalcIndicator = isCalcIndicator == null ? null : isCalcIndicator.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}