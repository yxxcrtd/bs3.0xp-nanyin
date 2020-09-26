/*
 * 文件名：ReportUpInterfMonitor001Dto.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统
 * 修改人：hujing
 * 修改时间：2017年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统
 */
package com.joyintech.tams.report.system.upinterfmonitor.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.tams.common.base.entity.ChbFileReadMonitorInfo;
import com.joyintech.tams.report.common.util.BigDecimalSerializer;

/**
 * 上游接口文件读取处理监控
 * 〈功能详细描述〉
 * @author hujing
 * @version 1.0 2018年8月10日
 * @see ReportUpInterfMonitor001Dto
 * @since 1.0
 */
public class ReportUpInterfMonitor001Dto extends ChbFileReadMonitorInfo{

	 /**
     * 文件处理序列号
     */
    private String handleSeqno;

    /**
     * 数据文件名
     */
    private String handleFileName;

    /**
     * 数据文件路径
     */
    private String handleFilePath;

    /**
     * 数据文件类别
     */
    private String handleFileType;

    /**
     * 数据文件读取开始时点
     */
    private Date fileReadStartTime;

    /**
     * 数据文件读取结束时点
     */
    private Date fileReadEndTime;

    /**
     * 文件数据条数
     */
    //@JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fileDataCount;

    /**
     * 文件处理状态
     */
    private String fileHandleStatus;

    /**
     * 文件处理结果说明
     */
    private String fileHandleResultMsg;

    /**
     * 取得 文件处理序列号
     * @return 文件处理序列号
     */
    public String getHandleSeqno() {
        return handleSeqno;
    }

    /**
     * 设置 文件处理序列号
     * @param handleSeqno 文件处理序列号
     */
    public void setHandleSeqno(String handleSeqno) {
        this.handleSeqno = handleSeqno;
    }

    /**
     * 取得 数据文件名
     * @return 数据文件名
     */
    public String getHandleFileName() {
        return handleFileName;
    }

    /**
     * 设置 数据文件名
     * @param handleFileName 数据文件名
     */
    public void setHandleFileName(String handleFileName) {
        this.handleFileName = handleFileName;
    }

    /**
     * 取得 数据文件路径
     * @return 数据文件路径
     */
    public String getHandleFilePath() {
        return handleFilePath;
    }

    /**
     * 设置 数据文件路径
     * @param handleFilePath 数据文件路径
     */
    public void setHandleFilePath(String handleFilePath) {
        this.handleFilePath = handleFilePath;
    }

    /**
     * 取得 数据文件类别
     * @return 数据文件类别
     */
    public String getHandleFileType() {
        return handleFileType;
    }

    /**
     * 设置 数据文件类别
     * @param handleFileType 数据文件类别
     */
    public void setHandleFileType(String handleFileType) {
        this.handleFileType = handleFileType;
    }

    /**
     * 取得 数据文件读取开始时点
     * @return 数据文件读取开始时点
     */
    public Date getFileReadStartTime() {
        return fileReadStartTime;
    }

    /**
     * 设置 数据文件读取开始时点
     * @param fileReadStartTime 数据文件读取开始时点
     */
    public void setFileReadStartTime(Date fileReadStartTime) {
        this.fileReadStartTime = fileReadStartTime;
    }

    /**
     * 取得 数据文件读取结束时点
     * @return 数据文件读取结束时点
     */
    public Date getFileReadEndTime() {
        return fileReadEndTime;
    }

    /**
     * 设置 数据文件读取结束时点
     * @param fileReadEndTime 数据文件读取结束时点
     */
    public void setFileReadEndTime(Date fileReadEndTime) {
        this.fileReadEndTime = fileReadEndTime;
    }

    /**
     * 取得 文件数据条数
     * @return 文件数据条数
     */
    public BigDecimal getFileDataCount() {
        return fileDataCount;
    }

    /**
     * 设置 文件数据条数
     * @param fileDataCount 文件数据条数
     */
    public void setFileDataCount(BigDecimal fileDataCount) {
        this.fileDataCount = fileDataCount;
    }

    /**
     * 取得 文件处理状态
     * @return 文件处理状态
     */
    public String getFileHandleStatus() {
        return fileHandleStatus;
    }

    /**
     * 设置 文件处理状态
     * @param fileHandleStatus 文件处理状态
     */
    public void setFileHandleStatus(String fileHandleStatus) {
        this.fileHandleStatus = fileHandleStatus;
    }

    /**
     * 取得 文件处理结果说明
     * @return 文件处理结果说明
     */
    public String getFileHandleResultMsg() {
        return fileHandleResultMsg;
    }

    /**
     * 设置 文件处理结果说明
     * @param fileHandleResultMsg 文件处理结果说明
     */
    public void setFileHandleResultMsg(String fileHandleResultMsg) {
        this.fileHandleResultMsg = fileHandleResultMsg;
    }
}
