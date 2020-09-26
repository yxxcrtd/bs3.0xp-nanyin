/*
 * 文件名：InterfLog.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：lihuadong
 * 修改时间：2017年3月16日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.interf.common.dto;

import java.util.Date;

/**
 * 接口日志表
 * 
 * @author lihuadong
 * @version 1.0 2017年3月16日
 * @see InterfLog
 * @since 1.0
 */
public class InterfLog {

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
     * 创建时间
     */
    private Date createTime;

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
     * 获取 logId
     * 
     * @return logId.
     */
    public String getLogId() {
        return logId;
    }

    /**
     * 设置 logId
     * 
     * @param logId logId
     */
    public void setLogId(String logId) {
        this.logId = logId;
    }

    /**
     * 获取 interfId
     * 
     * @return interfId.
     */
    public String getInterfId() {
        return interfId;
    }

    /**
     * 设置 interfId
     * 
     * @param interfId interfId
     */
    public void setInterfId(String interfId) {
        this.interfId = interfId;
    }

    /**
     * 获取 interfType
     * 
     * @return interfType.
     */
    public String getInterfType() {
        return interfType;
    }

    /**
     * 设置 interfType
     * 
     * @param interfType interfType
     */
    public void setInterfType(String interfType) {
        this.interfType = interfType;
    }

    /**
     * 获取 fileName
     * 
     * @return fileName.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置 fileName
     * 
     * @param fileName fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取 taskStatus
     * 
     * @return taskStatus.
     */
    public String getTaskStatus() {
        return taskStatus;
    }

    /**
     * 设置 taskStatus
     * 
     * @param taskStatus taskStatus
     */
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     * 获取 createTime
     * 
     * @return createTime.
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置 createTime
     * 
     * @param createTime createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取 bizDate
     * 
     * @return bizDate.
     */
    public String getBizDate() {
        return bizDate;
    }

    /**
     * 设置 bizDate
     * 
     * @param bizDate bizDate
     */
    public void setBizDate(String bizDate) {
        this.bizDate = bizDate;
    }

    /**
     * 获取 errMessage
     * 
     * @return errMessage.
     */
    public String getErrMessage() {
        return errMessage;
    }

    /**
     * 设置 errMessage
     * 
     * @param errMessage errMessage
     */
    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    /**
     * 获取 isneedwarning
     * 
     * @return isneedwarning.
     */
    public String getIsneedwarning() {
        return isneedwarning;
    }

    /**
     * 设置 isneedwarning
     * 
     * @param isneedwarning isneedwarning
     */
    public void setIsneedwarning(String isneedwarning) {
        this.isneedwarning = isneedwarning;
    }

    /**
     * 获取 remark1
     * 
     * @return remark1.
     */
    public String getRemark1() {
        return remark1;
    }

    /**
     * 设置 remark1
     * 
     * @param remark1 remark1
     */
    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    /**
     * 获取 remark2
     * 
     * @return remark2.
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * 设置 remark2
     * 
     * @param remark2 remark2
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

}
