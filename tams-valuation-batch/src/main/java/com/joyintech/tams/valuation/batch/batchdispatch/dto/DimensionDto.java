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

import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.framework.base.entity.SysBatchJob;
import com.joyintech.tams.common.base.entity.BokBatchTaskTodo;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author wangzhiang
 * @version 1.0 2018年4月27日
 * @see DimensionDto
 * @since 1.0
 */
public class DimensionDto extends NeedToWriteEntity {

    /**
     * 任务组执行ID
     */
    private String detailSeqNo;

    private List<DimensionValue> dimValueList;

    private BatchGrpPri batchGrpPri;

    private List<SysBatchJob> batchJobList;

    /**
     * 待处理任务
     */
    private List<BokBatchTaskTodo> bokBatchTaskTodoList;

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
     * @return detailSeqNo.
     */
    public String getDetailSeqNo() {
        return detailSeqNo;
    }

    
    /**
     * 设置 detailSeqNo
     * @param detailSeqNo detailSeqNo
     */
    public void setDetailSeqNo(String detailSeqNo) {
        this.detailSeqNo = detailSeqNo;
    }

    
    /**
     * 获取 dimValueList
     * @return dimValueList.
     */
    public List<DimensionValue> getDimValueList() {
        return dimValueList;
    }

    
    /**
     * 设置 dimValueList
     * @param dimValueList dimValueList
     */
    public void setDimValueList(List<DimensionValue> dimValueList) {
        this.dimValueList = dimValueList;
    }

    
    /**
     * 获取 batchGrpPri
     * @return batchGrpPri.
     */
    public BatchGrpPri getBatchGrpPri() {
        return batchGrpPri;
    }

    
    /**
     * 设置 batchGrpPri
     * @param batchGrpPri batchGrpPri
     */
    public void setBatchGrpPri(BatchGrpPri batchGrpPri) {
        this.batchGrpPri = batchGrpPri;
    }

    
    /**
     * 获取 batchJobList
     * @return batchJobList.
     */
    public List<SysBatchJob> getBatchJobList() {
        return batchJobList;
    }

    
    /**
     * 设置 batchJobList
     * @param batchJobList batchJobList
     */
    public void setBatchJobList(List<SysBatchJob> batchJobList) {
        this.batchJobList = batchJobList;
    }

}
