/*
 * 文件名：ValBatchTaskTodoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.valfilegendel.dto;

import java.io.Serializable;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;

/**
 * 待处理账套Dto
 * 
 * @author weipeng
 * @version 1.0 2018年5月28日
 * @see ValFileGenDelProcessorDto
 * @since 1.0
 */
public class ValFileGenDelProcessorDto extends NeedToWriteEntity implements Serializable {

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
     * 核算主体类别
     */
    private String acctSubjectType;
    
	/**
     * @param taskId
     * @param genDate
     * @param fileType
     */
    public ValFileGenDelProcessorDto(String taskId, String genDate, String fileType) {
        super();
        this.taskId = taskId;
        this.genDate = genDate;
        this.fileType = fileType;
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
     * 
     * @return genDate.
     */
    public String getGenDate() {
        return genDate;
    }

    /**
     * 设置 genDate
     * 
     * @param genDate genDate
     */
    public void setGenDate(String genDate) {
        this.genDate = genDate;
    }

	public String getAcctSubjectType() {
		return acctSubjectType;
	}


	public void setAcctSubjectType(String acctSubjectType) {
		this.acctSubjectType = acctSubjectType;
	}

}
