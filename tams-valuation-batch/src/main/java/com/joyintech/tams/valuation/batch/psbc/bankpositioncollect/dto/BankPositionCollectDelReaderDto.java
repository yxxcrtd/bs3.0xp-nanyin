/*
 * 文件名：BooksetTodoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.psbc.bankpositioncollect.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

import java.io.Serializable;

/**
 * 银行账待处理账套Dto
 * 
 * @author wangyang1
 * @version 1.0 2019年8月28日
 * @see BankPositionCollectDelReaderDto
 * @since 1.0
 */
public class BankPositionCollectDelReaderDto extends NeedOperateEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public BankPositionCollectDelReaderDto(String taskId) {
        this.taskId = taskId;
    }

    public BankPositionCollectDelProcessorDto toResult(){
        return new BankPositionCollectDelProcessorDto(taskId);
    }
    
    /**
     * 任务代码
     */
    private String taskId;
    /**
     * 获取 taskId
     * @return taskId.
     */
    public String getTaskId() {
        return taskId;
    }

    
    /**
     * 设置 taskId
     * @param taskId taskId
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

}
