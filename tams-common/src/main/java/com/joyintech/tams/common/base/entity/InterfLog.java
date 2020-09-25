package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class InterfLog extends BaseEntity {
    /**
     * 序列号
     */
    private String seqNo;

    /**
     * 接口ID
     */
    private String interfId;

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
     * 处理识别号
     */
    private String uuid;

    /**
     * 报文流水号
     */
    private String bizSeqNo;

    /**
     * 本地文件全路径
     */
    private String localFullPath;

    /**
     * 备用字段1
     */
    private String remark1;

    /**
     * 备用字段2
     */
    private String remark2;

    /**
     * 取得 序列号
     * @return 序列号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 序列号
     * @param seqNo 序列号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 接口ID
     * @return 接口ID
     */
    public String getInterfId() {
        return interfId;
    }

    /**
     * 设置 接口ID
     * @param interfId 接口ID
     */
    public void setInterfId(String interfId) {
        this.interfId = interfId == null ? null : interfId.trim();
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
     * 取得 处理识别号
     * @return 处理识别号
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置 处理识别号
     * @param uuid 处理识别号
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 取得 报文流水号
     * @return 报文流水号
     */
    public String getBizSeqNo() {
        return bizSeqNo;
    }

    /**
     * 设置 报文流水号
     * @param bizSeqNo 报文流水号
     */
    public void setBizSeqNo(String bizSeqNo) {
        this.bizSeqNo = bizSeqNo == null ? null : bizSeqNo.trim();
    }

    /**
     * 取得 本地文件全路径
     * @return 本地文件全路径
     */
    public String getLocalFullPath() {
        return localFullPath;
    }

    /**
     * 设置 本地文件全路径
     * @param localFullPath 本地文件全路径
     */
    public void setLocalFullPath(String localFullPath) {
        this.localFullPath = localFullPath == null ? null : localFullPath.trim();
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