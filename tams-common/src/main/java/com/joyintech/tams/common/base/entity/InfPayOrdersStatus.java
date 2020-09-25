package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class InfPayOrdersStatus extends BaseEntity {
    /**
     * 指令编号
     */
    private String orderId;

    /**
     * 发送业务流水号
     */
    private String sendSeqno;

    /**
     * 发送指令编号
     */
    private String sendOrderId;

    /**
     * 发送时间
     */
    private Date sendDate;

    /**
     * 发送状态
     */
    private String sendStatus;

    /**
     * 接收状态
     */
    private String orderRecStatus;

    /**
     * 处理状态
     */
    private String orderRetStatus;

    /**
     * 回执说明
     */
    private String sendRemark;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 指令编号
     * @return 指令编号
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置 指令编号
     * @param orderId 指令编号
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * 取得 发送业务流水号
     * @return 发送业务流水号
     */
    public String getSendSeqno() {
        return sendSeqno;
    }

    /**
     * 设置 发送业务流水号
     * @param sendSeqno 发送业务流水号
     */
    public void setSendSeqno(String sendSeqno) {
        this.sendSeqno = sendSeqno == null ? null : sendSeqno.trim();
    }

    /**
     * 取得 发送指令编号
     * @return 发送指令编号
     */
    public String getSendOrderId() {
        return sendOrderId;
    }

    /**
     * 设置 发送指令编号
     * @param sendOrderId 发送指令编号
     */
    public void setSendOrderId(String sendOrderId) {
        this.sendOrderId = sendOrderId == null ? null : sendOrderId.trim();
    }

    /**
     * 取得 发送时间
     * @return 发送时间
     */
    public Date getSendDate() {
        return sendDate;
    }

    /**
     * 设置 发送时间
     * @param sendDate 发送时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    /**
     * 取得 发送状态
     * @return 发送状态
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置 发送状态
     * @param sendStatus 发送状态
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 取得 接收状态
     * @return 接收状态
     */
    public String getOrderRecStatus() {
        return orderRecStatus;
    }

    /**
     * 设置 接收状态
     * @param orderRecStatus 接收状态
     */
    public void setOrderRecStatus(String orderRecStatus) {
        this.orderRecStatus = orderRecStatus == null ? null : orderRecStatus.trim();
    }

    /**
     * 取得 处理状态
     * @return 处理状态
     */
    public String getOrderRetStatus() {
        return orderRetStatus;
    }

    /**
     * 设置 处理状态
     * @param orderRetStatus 处理状态
     */
    public void setOrderRetStatus(String orderRetStatus) {
        this.orderRetStatus = orderRetStatus == null ? null : orderRetStatus.trim();
    }

    /**
     * 取得 回执说明
     * @return 回执说明
     */
    public String getSendRemark() {
        return sendRemark;
    }

    /**
     * 设置 回执说明
     * @param sendRemark 回执说明
     */
    public void setSendRemark(String sendRemark) {
        this.sendRemark = sendRemark == null ? null : sendRemark.trim();
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