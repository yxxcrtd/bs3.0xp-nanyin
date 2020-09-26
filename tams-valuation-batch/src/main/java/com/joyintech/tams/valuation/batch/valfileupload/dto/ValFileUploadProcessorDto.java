/*
 * 文件名：ValSchemeBatchDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年6月4日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.valfileupload.dto;

import java.io.Serializable;
import java.util.Date;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.util.BeanUtil;
//import com.joyintech.tams.common.base.entity.BokFileGen;

/**
 * 批处理文件待上传Dto
 * @author weipeng
 * @version 1.0 2018年12月20日
 * @see ValFileUploadProcessorDto
 * @since 1.0
 */
public class ValFileUploadProcessorDto extends NeedToWriteEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 生成日期
     */
    private Date genDate;

    /**
     * 文件类型
     */
    private String fileType;
    
    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 上传状态（是否已上传）
     */
    private String uploadStatus;

    /**
     * 取得 账套代码
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    /**
     * 取得 生成日期
     * @return 生成日期
     */
    public Date getGenDate() {
        return genDate;
    }

    /**
     * 设置 生成日期
     * @param genDate 生成日期
     */
    public void setGenDate(Date genDate) {
        this.genDate = genDate;
    }

    /**
     * 取得 文件类型
     * @return 文件类型
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * 设置 文件类型
     * @param fileType 文件类型
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    
    /**
     * 取得 文件名
     * @return 文件名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置 文件名
     * @param fileName 文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 取得 文件路径
     * @return 文件路径
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 设置 文件路径
     * @param filePath 文件路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 取得 上传状态（是否已上传）
     * @return 上传状态（是否已上传）
     */
    public String getUploadStatus() {
        return uploadStatus;
    }

    /**
     * 设置 上传状态（是否已上传）
     * @param uploadStatus 上传状态（是否已上传）
     */
    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }
    
//    public BokFileGen toBokFileGen() {
//        BokFileGen b = new BokFileGen();
//        BeanUtil.copyPropertiesIgnoreNull(b, this);
//        return b;
//    }
    
}
