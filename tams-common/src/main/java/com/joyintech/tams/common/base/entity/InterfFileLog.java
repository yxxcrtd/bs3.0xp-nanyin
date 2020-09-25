package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class InterfFileLog extends BaseEntity {
    /**
     * 序列号
     */
    private String seqNo;

    /**
     * 处理识别号
     */
    private String uuid;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 端口
     */
    private String port;

    /**
     * 用户
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 编码方式
     */
    private String encode;

    /**
     * 操作类型
     */
    private String excuteType;

    /**
     * 本地文件全路径
     */
    private String localFullPath;

    /**
     * 是否通过文件系统
     */
    private String bftsFlg;

    /**
     * 文件路径
     */
    private String ftpPath;

    /**
     * 文件名称
     */
    private String ftpName;

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
     * 取得 IP地址
     * @return IP地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置 IP地址
     * @param ip IP地址
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * 取得 端口
     * @return 端口
     */
    public String getPort() {
        return port;
    }

    /**
     * 设置 端口
     * @param port 端口
     */
    public void setPort(String port) {
        this.port = port == null ? null : port.trim();
    }

    /**
     * 取得 用户
     * @return 用户
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置 用户
     * @param userName 用户
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 取得 密码
     * @return 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置 密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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
     * 取得 操作类型
     * @return 操作类型
     */
    public String getExcuteType() {
        return excuteType;
    }

    /**
     * 设置 操作类型
     * @param excuteType 操作类型
     */
    public void setExcuteType(String excuteType) {
        this.excuteType = excuteType == null ? null : excuteType.trim();
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
     * 取得 是否通过文件系统
     * @return 是否通过文件系统
     */
    public String getBftsFlg() {
        return bftsFlg;
    }

    /**
     * 设置 是否通过文件系统
     * @param bftsFlg 是否通过文件系统
     */
    public void setBftsFlg(String bftsFlg) {
        this.bftsFlg = bftsFlg == null ? null : bftsFlg.trim();
    }

    /**
     * 取得 文件路径
     * @return 文件路径
     */
    public String getFtpPath() {
        return ftpPath;
    }

    /**
     * 设置 文件路径
     * @param ftpPath 文件路径
     */
    public void setFtpPath(String ftpPath) {
        this.ftpPath = ftpPath == null ? null : ftpPath.trim();
    }

    /**
     * 取得 文件名称
     * @return 文件名称
     */
    public String getFtpName() {
        return ftpName;
    }

    /**
     * 设置 文件名称
     * @param ftpName 文件名称
     */
    public void setFtpName(String ftpName) {
        this.ftpName = ftpName == null ? null : ftpName.trim();
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