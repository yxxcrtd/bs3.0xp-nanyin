package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskEventParaRelKey extends BaseEntity {
    /**
     * 参数代码
     */
    private String paraCode;

    /**
     * 事件代码
     */
    private String eventCode;

    /**
     * 参数粒度
     */
    private String paraGranularity;

    /**
     * 取得 参数代码
     * @return 参数代码
     */
    public String getParaCode() {
        return paraCode;
    }

    /**
     * 设置 参数代码
     * @param paraCode 参数代码
     */
    public void setParaCode(String paraCode) {
        this.paraCode = paraCode == null ? null : paraCode.trim();
    }

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
     * 取得 参数粒度
     * @return 参数粒度
     */
    public String getParaGranularity() {
        return paraGranularity;
    }

    /**
     * 设置 参数粒度
     * @param paraGranularity 参数粒度
     */
    public void setParaGranularity(String paraGranularity) {
        this.paraGranularity = paraGranularity == null ? null : paraGranularity.trim();
    }
}