/*
 * 文件名：BatchDispatchDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年4月27日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.batchdispatch.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.base.entity.SysBatchJob;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.tams.common.base.entity.BokBatchTaskTodo;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzhiang
 * @version 1.0 2018年4月27日
 * @see BatchDispatchProcessorDto
 * @since 1.0
 */
public class BatchDispatchProcessorDto extends NeedToWriteEntity {
	
    /**
     * 三天前日期
     */
    private Date oldDate;

    /**
     * 任务组执行ID
     */
    private String detailSeqNo;

    private List<DimensionDto> dimensionDtoList;

    /**
     * 待处理任务
     */
    private List<BokBatchTaskTodo> bokBatchTaskTodoList;

    private SysBatchgrpExecInfo sysBatchgrpExecInfo;

    /**
     * 获取 dimensionDtoList
     * 
     * @return dimensionDtoList.
     */
    public List<DimensionDto> getDimensionDtoList() {
        return dimensionDtoList;
    }

    /**
     * 设置 dimensionDtoList
     * 
     * @param dimensionDtoList dimensionDtoList
     */
    public void setDimensionDtoList(List<DimensionDto> dimensionDtoList) {
        this.dimensionDtoList = dimensionDtoList;
    }

    /**
     * 获取 sysBatchgrpExecInfo
     * 
     * @return sysBatchgrpExecInfo.
     */
    public SysBatchgrpExecInfo getSysBatchgrpExecInfo() {
        return sysBatchgrpExecInfo;
    }

    /**
     * 设置 sysBatchgrpExecInfo
     * 
     * @param sysBatchgrpExecInfo sysBatchgrpExecInfo
     */
    public void setSysBatchgrpExecInfo(SysBatchgrpExecInfo sysBatchgrpExecInfo) {
        this.sysBatchgrpExecInfo = sysBatchgrpExecInfo;
    }

    /**
     * 获取 bokBatchTaskTodoList
     * 
     * @return bokBatchTaskTodoList.
     */
    public List<BokBatchTaskTodo> getBokBatchTaskTodoList() {
        return bokBatchTaskTodoList;
    }

    /**
     * 设置 bokBatchTaskTodoList
     * 
     * @param bokBatchTaskTodoList bokBatchTaskTodoList
     */
    public void setBokBatchTaskTodoList(List<BokBatchTaskTodo> bokBatchTaskTodoList) {
        this.bokBatchTaskTodoList = bokBatchTaskTodoList;
    }

    /**
     * 获取 detailSeqNo
     * 
     * @return detailSeqNo.
     */
    public String getDetailSeqNo() {
        return detailSeqNo;
    }

    /**
     * 设置 detailSeqNo
     * 
     * @param detailSeqNo detailSeqNo
     */
    public void setDetailSeqNo(String detailSeqNo) {
        this.detailSeqNo = detailSeqNo;
    }
    
    /**
     * 获取 oldDate
     * 
     * @return oldDate.
     */
    public  Date getOldDate() {
        return oldDate;
    }

    /**
     * 设置 oldDate
     * 
     * @param oldDate oldDate
     */
    public void setOldDate(Date oldDate) {
        this.oldDate = oldDate;
    }
}
