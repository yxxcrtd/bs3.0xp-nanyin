package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class RepFtpDetailMsg extends BaseEntity {
    /**
     * 主键
     */
    private String id;

    /**
     * 记录主键
     */
    private String detailId;

    /**
     * 发送时间
     */
    private Date sendDate;

    /**
     * 发送状态
     */
    private String sendStatus;

    /**
     * 反馈时间
     */
    private Date receiveDate;

    /**
     * 反馈状态
     */
    private String receiveStatus;

    /**
     * 披露文件名称
     */
    private String fileName;

    /**
     * 披露渠道
     */
    private String channelCode;

    /**
     * 备选字段1
     */
    private String att1;

    /**
     * 备选字段2
     */
    private String att2;

    /**
     * 备选字段3
     */
    private String att3;

    /**
     * 备选字段4
     */
    private String att4;

    /**
     * 取得 主键
     * @return 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置 主键
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 取得 记录主键
     * @return 记录主键
     */
    public String getDetailId() {
        return detailId;
    }

    /**
     * 设置 记录主键
     * @param detailId 记录主键
     */
    public void setDetailId(String detailId) {
        this.detailId = detailId == null ? null : detailId.trim();
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
     * 取得 反馈时间
     * @return 反馈时间
     */
    public Date getReceiveDate() {
        return receiveDate;
    }

    /**
     * 设置 反馈时间
     * @param receiveDate 反馈时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    /**
     * 取得 反馈状态
     * @return 反馈状态
     */
    public String getReceiveStatus() {
        return receiveStatus;
    }

    /**
     * 设置 反馈状态
     * @param receiveStatus 反馈状态
     */
    public void setReceiveStatus(String receiveStatus) {
        this.receiveStatus = receiveStatus == null ? null : receiveStatus.trim();
    }

    /**
     * 取得 披露文件名称
     * @return 披露文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置 披露文件名称
     * @param fileName 披露文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * 取得 披露渠道
     * @return 披露渠道
     */
    public String getChannelCode() {
        return channelCode;
    }

    /**
     * 设置 披露渠道
     * @param channelCode 披露渠道
     */
    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    /**
     * 取得 备选字段1
     * @return 备选字段1
     */
    public String getAtt1() {
        return att1;
    }

    /**
     * 设置 备选字段1
     * @param att1 备选字段1
     */
    public void setAtt1(String att1) {
        this.att1 = att1 == null ? null : att1.trim();
    }

    /**
     * 取得 备选字段2
     * @return 备选字段2
     */
    public String getAtt2() {
        return att2;
    }

    /**
     * 设置 备选字段2
     * @param att2 备选字段2
     */
    public void setAtt2(String att2) {
        this.att2 = att2 == null ? null : att2.trim();
    }

    /**
     * 取得 备选字段3
     * @return 备选字段3
     */
    public String getAtt3() {
        return att3;
    }

    /**
     * 设置 备选字段3
     * @param att3 备选字段3
     */
    public void setAtt3(String att3) {
        this.att3 = att3 == null ? null : att3.trim();
    }

    /**
     * 取得 备选字段4
     * @return 备选字段4
     */
    public String getAtt4() {
        return att4;
    }

    /**
     * 设置 备选字段4
     * @param att4 备选字段4
     */
    public void setAtt4(String att4) {
        this.att4 = att4 == null ? null : att4.trim();
    }
}