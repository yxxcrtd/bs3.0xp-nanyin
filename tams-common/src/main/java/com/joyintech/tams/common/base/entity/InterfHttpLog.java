package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class InterfHttpLog extends BaseEntity {
    /**
     * 序列号
     */
    private String seqNo;

    /**
     * 处理识别号
     */
    private String uuid;

    /**
     * 请求URL
     */
    private String url;

    /**
     * 编码方式
     */
    private String encode;

    /**
     * 执行状态
     */
    private String taskStatus;

    /**
     * 异常信息
     */
    private String errMessage;

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
     * 取得 请求URL
     * @return 请求URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置 请求URL
     * @param url 请求URL
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 取得 编码方式
     * @return 编码方式
     */
    public String getEncode() {
        return encode;
    }

    /**
     * 设置 编码方式
     * @param encode 编码方式
     */
    public void setEncode(String encode) {
        this.encode = encode == null ? null : encode.trim();
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