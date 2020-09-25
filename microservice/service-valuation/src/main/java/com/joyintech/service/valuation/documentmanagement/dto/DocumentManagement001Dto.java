/*
 * 文件名：ValDocumentManagement001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.documentmanagement.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

public class DocumentManagement001Dto extends BaseEntity{
	/**
     * <意义，目的和功能，以及可能被用到的地方>
     */
	private static final long serialVersionUID = 1L;
    /**
     * 流水号
     */
    private String fileId;

    /**
     * 文档类型
     */
    private String fileType;

    /**
     * 文档名称
     */
    private String fileName;

    /**
     * 文档路径
     */
    private String filePath;
	/**
	 * 上传开始时间
	 */
	private String uploadTimeBegin;
	/**
	 * 上传结束时间
	 */
	private String uploadTimeEnd;
	/**
	 * 上传时间
	 */
	private Date uploadTime;
   	
	/**
	 * 获取 fileId
	 * @return fileId.
	 */
	public String getFileId() {
		return fileId;
	}
	/**
	 * 设置 fileId
	 * @param fileId fileId
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	/**
	 * 获取 fileType
	 * @return fileType.
	 */
	public String getFileType() {
		return  fileType;
	}

	/**
	 * 设置 fileType
	 * @param fileType fileType
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	/**
	 * 获取 fileName
	 * @return fileName.
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * 设置 fileName
	 * @param fileName fileName
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * 获取 filePath
	 * @return filePath.
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * 设置 filePath
	 * @param filePath filePath
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * 获取 uploadTime
	 * @return uploadTime.
	 */
	public String getUploadTime() {
		return DateUtil.format(uploadTime);
	}
	/**
	 * 设置 uploadTime
	 * @param uploadTime uploadTime
	 */
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	/**
	 * 获取 uploadTimeBegin
	 * @return uploadTimeBegin.
	 */
	public String getUploadTimeBegin() {
		return uploadTimeBegin;
	}
	/**
	 * 设置 uploadTimeBegin
	 * @param uploadTimeBegin uploadTimeBegin
	 */
	public void setUploadTimeBegin(String uploadTimeBegin) {
		this.uploadTimeBegin = uploadTimeBegin;
	}
	/**
	 * 获取 uploadTimeEnd
	 * @return uploadTimeEnd.
	 */
	public String getUploadTimeEnd() {
		return uploadTimeEnd;
	}
	/**
	 * 设置 uploadTimeEnd
	 * @param uploadTimeEnd uploadTimeEnd
	 */
	public void setUploadTimeEnd(String uploadTimeEnd) {
		this.uploadTimeEnd = uploadTimeEnd;
	}			
}
