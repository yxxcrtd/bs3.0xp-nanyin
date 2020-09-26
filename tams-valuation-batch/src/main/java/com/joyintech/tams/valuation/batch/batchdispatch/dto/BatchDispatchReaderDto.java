/*
 * 文件名：DepoAcctGroupInfoEntity.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统 修改人：caoguo 修改时间：2016年12月5日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.batch.batchdispatch.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.base.entity.SysBatchgrpExecInfo;
import com.joyintech.tams.common.base.entity.BokBatchTaskTodo;

/**
 * 调度批处理reader
 * 
 * @author caoguo
 * @version 1.0 2016年12月5日
 * @see BatchDispatchDtoO
 * @since 1.0
 */
public class BatchDispatchReaderDto extends NeedOperateEntity {
	
    /**
     * 三天前日期
     */
    private Date oldDate;

    /**
     * 任务组执行ID
     */
    private String detailSeqNo;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 入账信息
     */
    private List<DealAccountingTodo> dealAccountingTodoList;

    /**
     * 批处理信息
     */
    private List<BatchTaskInfo> batchTaskInfoList;

    private List<BokBatchTaskTodo> bokBatchTaskTodoList;

    private SysBatchgrpExecInfo sysBatchgrpExecInfo;

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
     * 获取 dealAccountingTodoList
     * 
     * @return dealAccountingTodoList.
     */
    public List<DealAccountingTodo> getDealAccountingTodoList() {
        return dealAccountingTodoList;
    }

    /**
     * 设置 dealAccountingTodoList
     * 
     * @param dealAccountingTodoList dealAccountingTodoList
     */
    public void setDealAccountingTodoList(List<DealAccountingTodo> dealAccountingTodoList) {
        this.dealAccountingTodoList = dealAccountingTodoList;
    }

    /**
     * 获取 booksetId
     * 
     * @return booksetId.
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 booksetId
     * 
     * @param booksetId booksetId
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    /**
     * 获取 batchTaskInfoList
     * 
     * @return batchTaskInfoList.
     */
    public List<BatchTaskInfo> getBatchTaskInfoList() {
        return batchTaskInfoList;
    }

    /**
     * 设置 batchTaskInfoList
     * 
     * @param batchTaskInfoList batchTaskInfoList
     */
    public void setBatchTaskInfoList(List<BatchTaskInfo> batchTaskInfoList) {
        this.batchTaskInfoList = batchTaskInfoList;
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
