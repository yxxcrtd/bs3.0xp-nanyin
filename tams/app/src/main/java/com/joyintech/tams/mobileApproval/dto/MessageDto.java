package com.joyintech.tams.mobileApproval.dto;

public class MessageDto {
    private String thirdpartyMessageId;//第三方消息主键（保证唯一）
    private String thirdpartyRegisterCode;//为第三方配置的系统注册编码
    private String messageContent;//消息内容
    private String thirdpartySenderId;//第三方消息发起人主键（保证唯一）
    private String thirdpartyReceiverId;//第三方消息接收人主键（保证唯一）
    private String creation_date;//消息创建时间（格式：yyyy-MM-dd HH:mm:ss）
    private String messageType;//0：PC；1：移动URL接入应用；2：本地H5应用；3：本地原生应用；4：PC&移动URL接入应用；5：PC&本地H5应用；6：PC&本地原生应用，致远提供
    private String downloadUrl;//原生应用下载地址（仅3和6类型可选）
    private String messageURL;//PC端穿透链接
    private String messageH5URL;//移动端穿透链接
    private String appParam;//原生应用穿透命令，穿透命令需要按这个顺序：iphone|ipad|android|wp

    public String getThirdpartyMessageId() {
        return thirdpartyMessageId;
    }

    public void setThirdpartyMessageId(String thirdpartyMessageId) {
        this.thirdpartyMessageId = thirdpartyMessageId;
    }

    public String getThirdpartyRegisterCode() {
        return thirdpartyRegisterCode;
    }

    public void setThirdpartyRegisterCode(String thirdpartyRegisterCode) {
        this.thirdpartyRegisterCode = thirdpartyRegisterCode;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getThirdpartySenderId() {
        return thirdpartySenderId;
    }

    public void setThirdpartySenderId(String thirdpartySenderId) {
        this.thirdpartySenderId = thirdpartySenderId;
    }

    public String getThirdpartyReceiverId() {
        return thirdpartyReceiverId;
    }

    public void setThirdpartyReceiverId(String thirdpartyReceiverId) {
        this.thirdpartyReceiverId = thirdpartyReceiverId;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getMessageURL() {
        return messageURL;
    }

    public void setMessageURL(String messageURL) {
        this.messageURL = messageURL;
    }

    public String getMessageH5URL() {
        return messageH5URL;
    }

    public void setMessageH5URL(String messageH5URL) {
        this.messageH5URL = messageH5URL;
    }

    public String getAppParam() {
        return appParam;
    }

    public void setAppParam(String appParam) {
        this.appParam = appParam;
    }
}
