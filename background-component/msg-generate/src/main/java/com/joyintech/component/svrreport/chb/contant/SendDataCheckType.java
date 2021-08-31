package com.joyintech.component.svrreport.chb.contant;

/**
 * 发送前数据状态校验
 */
public enum SendDataCheckType {

    报送("01","01", null),

    重新报送("01","02", "02");

    private String status;

    private String sendStatus;

    private String submitFeedbackStatus;

    SendDataCheckType(String status, String sendStatus, String submitFeedbackStatus){
        this.status = status;
        this.sendStatus = sendStatus;
        this.submitFeedbackStatus = submitFeedbackStatus;
    }

    public String getStatus() {
        return status;
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public String getSubmitFeedbackStatus() {
        return submitFeedbackStatus;
    }
}
