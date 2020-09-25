package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.servermanage.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * 外汇交易中心服务器管理dto
 *
 * @author zcf
 * @version 1.0
 * @date 2020-01-13
 */
public class ServerManage001Dto extends BaseEntity {

    /**
     * 接口连接状态
     */
    private String status;

    /**
     * 上次心跳报文连接时间
     */
    private String beatTime;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件大小
     */
    private String fileSize;

    /**
     * 文件url
     */
    private String fileUrl;

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    public String getBeatTime() {
        return beatTime;
    }

    public void setBeatTime(String beatTime) {
        this.beatTime = beatTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}