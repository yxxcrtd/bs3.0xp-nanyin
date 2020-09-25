package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbInterfLog extends BaseEntity {
    /**
     * 日志编号
     */
    private String logId;

    /**
     * 接口系统ID
     */
    private String interfId;

    /**
     * 子接口类型
     */
    private String interfType;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 执行状态
     */
    private String taskStatus;

    /**
     * 业务日期
     */
    private String bizDate;

    /**
     * 异常信息
     */
    private String errMessage;

    /**
     * 预警标示
     */
    private String isneedwarning;

    /**
     * 备用字段1
     */
    private String remark1;

    /**
     * 备用字段2
     */
    private String remark2;

    /**
     * 取得 日志编号
     * @return 日志编号
     */
    public String getLogId() {
        return logId;
    }

    /**
     * 设置 日志编号
     * @param logId 日志编号
     */
    public void setLogId(String logId) {
        this.logId = logId == null ? null : logId.trim();
    }

    /**
     * 取得 接口系统ID
     * @return 接口系统ID
     */
    public String getInterfId() {
        return interfId;
    }

    /**
     * 设置 接口系统ID
     * @param interfId 接口系统ID
     */
    public void setInterfId(String interfId) {
        this.interfId = interfId == null ? null : interfId.trim();
    }

    /**
     * 取得 子接口类型
     * @return 子接口类型
     */
    public String getInterfType() {
        return interfType;
    }

    /**
     * 设置 子接口类型
     * @param interfType 子接口类型
     */
    public void setInterfType(String interfType) {
        this.interfType = interfType == null ? null : interfType.trim();
    }

    /**
     * 取得 文件名
     * @return 文件名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置 文件名
     * @param fileName 文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * 取得 执行状态
     * @return 执行状态
     */
    public String getTaskStatus() {
        return taskStatus;
    }

    /**
     * 设置 执行状态
     * @param taskStatus 执行状态
     */
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus == null ? null : taskStatus.trim();
    }

    /**
     * 取得 业务日期
     * @return 业务日期
     */
    public String getBizDate() {
        return bizDate;
    }

    /**
     * 设置 业务日期
     * @param bizDate 业务日期
     */
    public void setBizDate(String bizDate) {
        this.bizDate = bizDate == null ? null : bizDate.trim();
    }

    /**
     * 取得 异常信息
     * @return 异常信息
     */
    public String getErrMessage() {
        return errMessage;
    }

    /**
     * 设置 异常信息
     * @param errMessage 异常信息
     */
    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage == null ? null : errMessage.trim();
    }

    /**
     * 取得 预警标示
     * @return 预警标示
     */
    public String getIsneedwarning() {
        return isneedwarning;
    }

    /**
     * 设置 预警标示
     * @param isneedwarning 预警标示
     */
    public void setIsneedwarning(String isneedwarning) {
        this.isneedwarning = isneedwarning == null ? null : isneedwarning.trim();
    }

    /**
     * 取得 备用字段1
     * @return 备用字段1
     */
    public String getRemark1() {
        return remark1;
    }

    /**
     * 设置 备用字段1
     * @param remark1 备用字段1
     */
    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }

    /**
     * 取得 备用字段2
     * @return 备用字段2
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * 设置 备用字段2
     * @param remark2 备用字段2
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }
}