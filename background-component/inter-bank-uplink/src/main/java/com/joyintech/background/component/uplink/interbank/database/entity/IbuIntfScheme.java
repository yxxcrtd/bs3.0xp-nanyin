package com.joyintech.background.component.uplink.interbank.database.entity;

import com.joyintech.background.component.common.pack.common.base.BaseEntity;

public class IbuIntfScheme extends BaseEntity {
    /**
     * 方案代码
     */
    private String schemeId;

    /**
     * 方案名称
     */
    private String schemeName;

    /**
     * 接口发送方式
     */
    private String sendType;

    /**
     * 结果等待方式
     */
    private String resultWaitType;

    /**
     * 接口接收方式
     */
    private String receiveType;

    /**
     * 是否默认方案
     */
    private String isdefault;

    /**
     * 取得 方案代码
     * @return 方案代码
     */
    public String getSchemeId() {
        return schemeId;
    }

    /**
     * 设置 方案代码
     * @param schemeId 方案代码
     */
    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId == null ? null : schemeId.trim();
    }

    /**
     * 取得 方案名称
     * @return 方案名称
     */
    public String getSchemeName() {
        return schemeName;
    }

    /**
     * 设置 方案名称
     * @param schemeName 方案名称
     */
    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName == null ? null : schemeName.trim();
    }

    /**
     * 取得 接口发送方式
     * @return 接口发送方式
     */
    public String getSendType() {
        return sendType;
    }

    /**
     * 设置 接口发送方式
     * @param sendType 接口发送方式
     */
    public void setSendType(String sendType) {
        this.sendType = sendType == null ? null : sendType.trim();
    }

    /**
     * 取得 结果等待方式
     * @return 结果等待方式
     */
    public String getResultWaitType() {
        return resultWaitType;
    }

    /**
     * 设置 结果等待方式
     * @param resultWaitType 结果等待方式
     */
    public void setResultWaitType(String resultWaitType) {
        this.resultWaitType = resultWaitType == null ? null : resultWaitType.trim();
    }

    /**
     * 取得 接口接收方式
     * @return 接口接收方式
     */
    public String getReceiveType() {
        return receiveType;
    }

    /**
     * 设置 接口接收方式
     * @param receiveType 接口接收方式
     */
    public void setReceiveType(String receiveType) {
        this.receiveType = receiveType == null ? null : receiveType.trim();
    }

    /**
     * 取得 是否默认方案
     * @return 是否默认方案
     */
    public String getIsdefault() {
        return isdefault;
    }

    /**
     * 设置 是否默认方案
     * @param isdefault 是否默认方案
     */
    public void setIsdefault(String isdefault) {
        this.isdefault = isdefault == null ? null : isdefault.trim();
    }
}