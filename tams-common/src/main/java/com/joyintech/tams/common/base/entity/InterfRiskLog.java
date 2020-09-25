package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class InterfRiskLog extends BaseEntity {
    /**
     * UUID
     */
    private String uuid;

    /**
     * 接口ID
     */
    private String infId;

    /**
     * 系统代码
     */
    private String sysCode;

    /**
     * 报文流水号
     */
    private String seqNo;

    /**
     * 用户IP
     */
    private String ipAddress;

    /**
     * 用户端口号
     */
    private Integer port;

    /**
     * 返回码
     */
    private String rtnCode;

    /**
     * 返回消息
     */
    private String rtnMsg;

    /**
     * 处理状态
     */
    private String doStatus;

    /**
     * 发送日期
     */
    private String tranDate;

    /**
     * 发送时间
     */
    private String tranTime;

    /**
     * 取得 UUID
     * @return UUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置 UUID
     * @param uuid UUID
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 取得 接口ID
     * @return 接口ID
     */
    public String getInfId() {
        return infId;
    }

    /**
     * 设置 接口ID
     * @param infId 接口ID
     */
    public void setInfId(String infId) {
        this.infId = infId == null ? null : infId.trim();
    }

    /**
     * 取得 系统代码
     * @return 系统代码
     */
    public String getSysCode() {
        return sysCode;
    }

    /**
     * 设置 系统代码
     * @param sysCode 系统代码
     */
    public void setSysCode(String sysCode) {
        this.sysCode = sysCode == null ? null : sysCode.trim();
    }

    /**
     * 取得 报文流水号
     * @return 报文流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 报文流水号
     * @param seqNo 报文流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 用户IP
     * @return 用户IP
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * 设置 用户IP
     * @param ipAddress 用户IP
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress == null ? null : ipAddress.trim();
    }

    /**
     * 取得 用户端口号
     * @return 用户端口号
     */
    public Integer getPort() {
        return port;
    }

    /**
     * 设置 用户端口号
     * @param port 用户端口号
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * 取得 返回码
     * @return 返回码
     */
    public String getRtnCode() {
        return rtnCode;
    }

    /**
     * 设置 返回码
     * @param rtnCode 返回码
     */
    public void setRtnCode(String rtnCode) {
        this.rtnCode = rtnCode == null ? null : rtnCode.trim();
    }

    /**
     * 取得 返回消息
     * @return 返回消息
     */
    public String getRtnMsg() {
        return rtnMsg;
    }

    /**
     * 设置 返回消息
     * @param rtnMsg 返回消息
     */
    public void setRtnMsg(String rtnMsg) {
        this.rtnMsg = rtnMsg == null ? null : rtnMsg.trim();
    }

    /**
     * 取得 处理状态
     * @return 处理状态
     */
    public String getDoStatus() {
        return doStatus;
    }

    /**
     * 设置 处理状态
     * @param doStatus 处理状态
     */
    public void setDoStatus(String doStatus) {
        this.doStatus = doStatus == null ? null : doStatus.trim();
    }

    /**
     * 取得 发送日期
     * @return 发送日期
     */
    public String getTranDate() {
        return tranDate;
    }

    /**
     * 设置 发送日期
     * @param tranDate 发送日期
     */
    public void setTranDate(String tranDate) {
        this.tranDate = tranDate == null ? null : tranDate.trim();
    }

    /**
     * 取得 发送时间
     * @return 发送时间
     */
    public String getTranTime() {
        return tranTime;
    }

    /**
     * 设置 发送时间
     * @param tranTime 发送时间
     */
    public void setTranTime(String tranTime) {
        this.tranTime = tranTime == null ? null : tranTime.trim();
    }
}