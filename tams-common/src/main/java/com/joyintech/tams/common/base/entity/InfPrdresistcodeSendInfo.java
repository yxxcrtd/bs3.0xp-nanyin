package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class InfPrdresistcodeSendInfo extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 发送状态 为空的时候，是‘未发送’01 发送成功，02 发送失败，03 未发送
     */
    private String sendStatus;

    /**
     * 发送备注
     */
    private String sendRemark;

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 发送状态 为空的时候，是‘未发送’01 发送成功，02 发送失败，03 未发送
     * @return 发送状态 为空的时候，是‘未发送’01 发送成功，02 发送失败，03 未发送
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置 发送状态 为空的时候，是‘未发送’01 发送成功，02 发送失败，03 未发送
     * @param sendStatus 发送状态 为空的时候，是‘未发送’01 发送成功，02 发送失败，03 未发送
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 取得 发送备注
     * @return 发送备注
     */
    public String getSendRemark() {
        return sendRemark;
    }

    /**
     * 设置 发送备注
     * @param sendRemark 发送备注
     */
    public void setSendRemark(String sendRemark) {
        this.sendRemark = sendRemark == null ? null : sendRemark.trim();
    }
}