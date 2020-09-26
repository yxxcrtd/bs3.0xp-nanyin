/*
 * 文件名：ValSchemeBatchDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年6月4日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.valfilegendel.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.valuation.batch.common.base.spring.annotation.BaseEntityClassName;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzhiang
 * @version 1.0 2018年7月10日
 * @see ValFileGenDelReaderDto
 * @since 1.0
 */
public class ValFileGenDelReaderDto extends NeedOperateEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 任务代码
     */
    private String taskId;

    /**
     * 生成日期
     */
    private String genDate;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * @param taskId
     * @param genDate
     * @param fileType
     */
    public ValFileGenDelReaderDto(String taskId, String genDate, String fileType) {
        super();
        this.taskId = taskId;
        this.genDate = genDate;
        this.fileType = fileType;
    }

    /**
     * @param taskId
     * @param fileType
     */
    public ValFileGenDelProcessorDto toResult() {
        return new ValFileGenDelProcessorDto(taskId, genDate, fileType);
    }

    /**
     * 获取 taskId
     * 
     * @return taskId.
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 设置 taskId
     * 
     * @param taskId taskId
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * 获取 fileType
     * 
     * @return fileType.
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * 设置 fileType
     * 
     * @param fileType fileType
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    
    /**
     * 获取 genDate
     * @return genDate.
     */
    public String getGenDate() {
        return genDate;
    }

    
    /**
     * 设置 genDate
     * @param genDate genDate
     */
    public void setGenDate(String genDate) {
        this.genDate = genDate;
    }
    
}
