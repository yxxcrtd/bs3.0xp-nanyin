package com.joyintech.tams.report.system.chbfeedbackfilemanagement.dto;

import java.util.Date;

public class ChbFeedbackFileMatchDetailDto {

    /**
     * 反馈文件信息-报送流水号
     */
    private String fileRegSeqno;

    /**
     * 反馈文件信息-报送类型
     */
    private String fileRepType;

    /**
     * 反馈文件信息-反馈结果
     */
    private String fileFeedbackResult;

    /**
     * 登记表信息-报送流水号
     */
    private String regSeqno;

    /**
     * 唯一标识
     */
    private String keyCode;

    /**
     * 登记表信息-报送状态
     */
    private String regRepStatus;

    /**
     * 登记表信息-反馈状态
     */
    private String regFeedbackStatus;

    /**
     * 登记表信息-数据日期
     */
    private Date regDataDate;

    /**
     * 登记表信息-数据状态
     */
    private String regDataStatus;

    /**
     * 反馈信息
     */
    private String feedbackMsg;

    public String getFileRegSeqno() {
        return fileRegSeqno;
    }

    public void setFileRegSeqno(String fileRegSeqno) {
        this.fileRegSeqno = fileRegSeqno;
    }

    public String getFileRepType() {
        return fileRepType;
    }

    public void setFileRepType(String fileRepType) {
        this.fileRepType = fileRepType;
    }

    public String getFileFeedbackResult() {
        return fileFeedbackResult;
    }

    public void setFileFeedbackResult(String fileFeedbackResult) {
        this.fileFeedbackResult = fileFeedbackResult;
    }

    public String getRegSeqno() {
        return regSeqno;
    }

    public void setRegSeqno(String regSeqno) {
        this.regSeqno = regSeqno;
    }

    public String getRegRepStatus() {
        return regRepStatus;
    }

    public void setRegRepStatus(String regRepStatus) {
        this.regRepStatus = regRepStatus;
    }

    public String getRegFeedbackStatus() {
        return regFeedbackStatus;
    }

    public void setRegFeedbackStatus(String regFeedbackStatus) {
        this.regFeedbackStatus = regFeedbackStatus;
    }

    public Date getRegDataDate() {
        return regDataDate;
    }

    public void setRegDataDate(Date regDataDate) {
        this.regDataDate = regDataDate;
    }

    public String getRegDataStatus() {
        return regDataStatus;
    }

    public void setRegDataStatus(String regDataStatus) {
        this.regDataStatus = regDataStatus;
    }

    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    public String getFeedbackMsg() {
        return feedbackMsg;
    }

    public void setFeedbackMsg(String feedbackMsg) {
        this.feedbackMsg = feedbackMsg;
    }
}
