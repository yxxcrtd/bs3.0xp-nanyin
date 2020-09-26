/*
 * 文件名：AsynReportEntity.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：台德全
 * 修改时间：2017年05月30日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.report.interf.common.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 40消息 异步文件返回报文Entity
 *
 * @author 台德全
 * @version 1.0 2017年05月30日
 * @see AsynReport40Entity
 * @since 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Document")
public class AsynReport40Entity {

    /**
     * 子节点
     */
    @XmlElement(name = "SysXcptn")
    private AsynSysXcptnEntity sysXcptn;

    /**
     * 子节点
     */
    @XmlElement(name = "SysType")
    private String sysType;

    /**
     * 子节点
     */
    @XmlElement(name = "MsgType")
    private String msgType;

    /**
     * 子节点
     */
    @XmlElement(name = "FileId")
    private String fileId;

    /**
     * 子节点
     */
    @XmlElement(name = "FileName")
    private String fileName;

    /**
     * 子节点
     */
    @XmlElement(name = "OrigFileName")
    private String origFileName;

    /**
     * 子节点
     */
    @XmlElement(name = "SuccessCount")
    private String successCount;

    /**
     * 子节点
     */
    @XmlElement(name = "FailedCount")
    private String failedCount;

    /**
     * 子节点
     */
    @XmlElement(name = "TotalCount")
    private String totalCount;

    /**
     * 子节点
     */
    @XmlElement(name = "ErrorCode")
    private String errorCode;

    /**
     * 子节点
     */
    @XmlElement(name = "ErrorText")
    private String errorText;

    /**
     * 获取 sysXcptn
     *
     * @return sysXcptn.
     */
    public AsynSysXcptnEntity getSysXcptn() {
        return sysXcptn;
    }

    /**
     * 设置 sysXcptn
     *
     * @param sysXcptn sysXcptn
     */
    public void setSysXcptn(AsynSysXcptnEntity sysXcptn) {
        this.sysXcptn = sysXcptn;
    }

    /**
     * 获取 sysType
     *
     * @return sysType.
     */
    public String getSysType() {
        return sysType;
    }

    /**
     * 设置 sysType
     *
     * @param sysType sysType
     */
    public void setSysType(String sysType) {
        this.sysType = sysType;
    }

    /**
     * 获取 msgType
     *
     * @return msgType.
     */
    public String getMsgType() {
        return msgType;
    }

    /**
     * 设置 msgType
     *
     * @param msgType msgType
     */
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    /**
     * 获取 fileId
     *
     * @return fileId.
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * 设置 fileId
     *
     * @param fileId fileId
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
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
     * 获取 origFileName
     *
     * @return origFileName.
     */
    public String getOrigFileName() {
        return origFileName;
    }

    /**
     * 设置 origFileName
     *
     * @param origFileName origFileName
     */
    public void setOrigFileName(String origFileName) {
        this.origFileName = origFileName;
    }

    /**
     * 获取 successCount
     *
     * @return successCount.
     */
    public String getSuccessCount() {
        return successCount;
    }

    /**
     * 设置 successCount
     *
     * @param successCount successCount
     */
    public void setSuccessCount(String successCount) {
        this.successCount = successCount;
    }

    /**
     * 获取 failedCount
     *
     * @return failedCount.
     */
    public String getFailedCount() {
        return failedCount;
    }

    /**
     * 设置 failedCount
     *
     * @param failedCount failedCount
     */
    public void setFailedCount(String failedCount) {
        this.failedCount = failedCount;
    }

    /**
     * 获取 totalCount
     *
     * @return totalCount.
     */
    public String getTotalCount() {
        return totalCount;
    }

    /**
     * 设置 totalCount
     *
     * @param totalCount totalCount
     */
    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 获取 errorCode
     *
     * @return errorCode.
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * 设置 errorCode
     *
     * @param errorCode errorCode
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 获取 errorText
     *
     * @return errorText.
     */
    public String getErrorText() {
        return errorText;
    }

    /**
     * 设置 errorText
     *
     * @param errorText errorText
     */
    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }
}
