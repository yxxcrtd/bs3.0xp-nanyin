/*
 * 文件名：DataFileUpload001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年11月14日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.fileupload.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.service.valuation.fileupload.service.DataFileUpload001Service;
import com.joyintech.tams.constant.DictCode;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangwei
 * @version 1.0 2018年11月14日
 * @see DataFileUpload001Service
 * @since 1.0
 */
public class DataFileUpload001Dto extends BaseEntity {

    /**
     * 文件ID
     */
    private String fileId;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String filePath;
    
    /**
     * 文件时间
     */
    private String fileTime;
    
    /**
     * 文件时间开始
     */
    private String fileTimeBegin;
    
    /**
     * 文件时间结束
     */
    private String fileTimeEnd;

    
    public String getFileId() {
        return fileId;
    }

    
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * 获取 fileType
     * @return fileType.
     */
    public String getFileType() {
        return fileType;
    }


    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    
    public String getFileName() {
        return fileName;
    }

    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    
    public String getFilePath() {
        return filePath;
    }

    
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    
    public String getFileTime() {
        return fileTime;
    }


    
    public void setFileTime(String fileTime) {
        this.fileTime = fileTime;
    }


    
    public String getFileTimeBegin() {
        return fileTimeBegin;
    }


    
    public void setFileTimeBegin(String fileTimeBegin) {
        this.fileTimeBegin = fileTimeBegin;
    }


    
    public String getFileTimeEnd() {
        return fileTimeEnd;
    }


    
    public void setFileTimeEnd(String fileTimeEnd) {
        this.fileTimeEnd = fileTimeEnd;
    }
    
    
    
    
}
