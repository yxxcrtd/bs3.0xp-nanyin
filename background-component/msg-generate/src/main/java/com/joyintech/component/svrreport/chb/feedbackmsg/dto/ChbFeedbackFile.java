package com.joyintech.component.svrreport.chb.feedbackmsg.dto;


import com.joyintech.background.component.common.pack.common.base.BaseEntity;

import java.util.Date;

/**
 * 描述:CHB_FEEDBACK_FILE表的实体类
 * @version
 * @author:  Hai
 * @创建时间: 2021-05-10
 */
public class ChbFeedbackFile extends BaseEntity {
    /**
     * 反馈-报文编码
     */
    private String rtnRepMesNo;

    /**
     * 发送-报文编码
     */
    private String sendRepMesNo;

    /**
     * 报文发送时间
     */
    private Date reportSendDate;

    /**
     * 报文接收时间
     */
    private Date reportAcceptDate;

    /**
     * 业务组类型
     */
    private String businessGroupType;

    /**
     * 报文指令类型
     */
    private String reportEtrType;

    /**
     * 文件ID
     */
    private String fileId;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 处理状态
     */
    private String handleStatus;

    /**
     * 是否迁移
     */
    private String moveFlag;

    /**
     * 迁移目录
     */
    private String movePath;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建部门
     */
    private String createDept;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 反馈消息
     */
    private String feedbackMsg;

    /**
     * 反馈-报文编码
     * @return RTN_REP_MES_NO 反馈-报文编码
     */
    public String getRtnRepMesNo() {
        return rtnRepMesNo;
    }

    /**
     * 反馈-报文编码
     * @param rtnRepMesNo 反馈-报文编码
     */
    public void setRtnRepMesNo(String rtnRepMesNo) {
        this.rtnRepMesNo = rtnRepMesNo == null ? null : rtnRepMesNo.trim();
    }

    /**
     * 发送-报文编码
     * @return SEND_REP_MES_NO 发送-报文编码
     */
    public String getSendRepMesNo() {
        return sendRepMesNo;
    }

    /**
     * 发送-报文编码
     * @param sendRepMesNo 发送-报文编码
     */
    public void setSendRepMesNo(String sendRepMesNo) {
        this.sendRepMesNo = sendRepMesNo == null ? null : sendRepMesNo.trim();
    }

    /**
     * 报文发送时间
     * @return REPORT_SEND_DATE 报文发送时间
     */
    public Date getReportSendDate() {
        return reportSendDate;
    }

    /**
     * 报文发送时间
     * @param reportSendDate 报文发送时间
     */
    public void setReportSendDate(Date reportSendDate) {
        this.reportSendDate = reportSendDate;
    }

    /**
     * 报文接收时间
     * @return REPORT_ACCEPT_DATE 报文接收时间
     */
    public Date getReportAcceptDate() {
        return reportAcceptDate;
    }

    /**
     * 报文接收时间
     * @param reportAcceptDate 报文接收时间
     */
    public void setReportAcceptDate(Date reportAcceptDate) {
        this.reportAcceptDate = reportAcceptDate;
    }

    /**
     * 业务组类型
     * @return BUSINESS_GROUP_TYPE 业务组类型
     */
    public String getBusinessGroupType() {
        return businessGroupType;
    }

    /**
     * 业务组类型
     * @param businessGroupType 业务组类型
     */
    public void setBusinessGroupType(String businessGroupType) {
        this.businessGroupType = businessGroupType == null ? null : businessGroupType.trim();
    }

    /**
     * 报文指令类型
     * @return REPORT_ETR_TYPE 报文指令类型
     */
    public String getReportEtrType() {
        return reportEtrType;
    }

    /**
     * 报文指令类型
     * @param reportEtrType 报文指令类型
     */
    public void setReportEtrType(String reportEtrType) {
        this.reportEtrType = reportEtrType == null ? null : reportEtrType.trim();
    }

    /**
     * 文件ID
     * @return FILE_ID 文件ID
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * 文件ID
     * @param fileId 文件ID
     */
    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    /**
     * 文件名称
     * @return FILE_NAME 文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 文件名称
     * @param fileName 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 文件路径
     * @return FILE_PATH 文件路径
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 文件路径
     * @param filePath 文件路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(String handleStatus) {
        this.handleStatus = handleStatus;
    }

    /**
     * 是否迁移
     * @return MOVE_FLAG 是否迁移
     */
    public String getMoveFlag() {
        return moveFlag;
    }

    /**
     * 是否迁移
     * @param moveFlag 是否迁移
     */
    public void setMoveFlag(String moveFlag) {
        this.moveFlag = moveFlag == null ? null : moveFlag.trim();
    }

    /**
     * 迁移目录
     * @return MOVE_PATH 迁移目录
     */
    public String getMovePath() {
        return movePath;
    }

    /**
     * 迁移目录
     * @param movePath 迁移目录
     */
    public void setMovePath(String movePath) {
        this.movePath = movePath == null ? null : movePath.trim();
    }

    /**
     * 反馈消息
     * @return FEEDBACK_MSG 反馈消息
     */
    public String getFeedbackMsg() {
        return feedbackMsg;
    }

    /**
     * 反馈消息
     * @param feedbackMsg 反馈消息
     */
    public void setFeedbackMsg(String feedbackMsg) {
        this.feedbackMsg = feedbackMsg == null ? null : feedbackMsg.trim();
    }
}