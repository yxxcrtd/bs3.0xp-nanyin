/*
 * 文件名：BooksetTodoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：lby
 * 修改时间：2020年12月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.subjectbalanceagaincollect.dto;

import java.io.Serializable;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;

/**
 * 待处理账套Dto
 *
 * @author lby
 * @version 1.0 2020年12月29日
 * @see SubjectBalanceCollectDelProcessorDto
 * @since 1.0
 */
public class SubjectBalanceCollectDelProcessorDto extends NeedToWriteEntity implements Serializable {

    private static final long serialVersionUID = -2983746897323157818L;

    public SubjectBalanceCollectDelProcessorDto(String taskId) {
        this.taskId = taskId;
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
