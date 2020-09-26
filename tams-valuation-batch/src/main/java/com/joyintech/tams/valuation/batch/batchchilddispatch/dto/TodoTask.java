/*
 * 文件名：TodoTask.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：maodongjin
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.batchchilddispatch.dto;

/**
 * 子调度中，批处理优先信息实体类
 * 
 * @author maodongjin
 * @version 1.0 2018年5月5日
 * @see TodoTask
 * @since 1.0
 */
public class TodoTask {

    /**
     * 批处理任务组执行ID
     */
    private String seqno;

    /**
     * 任务代码
     */
    private String taskId;

    /**
     * 前序任务代码
     */
    private String pTaskId;

    /**
     * 业务类型
     */
    private String tradeType;

    /**
     * 是否全量账务明细
     */
    private String allDetail;

    /**
     * 跑批规则
     */
    private String taskExeRule;
    
    /**
     * 获取 seqno
     * 
     * @return seqno.
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 seqno
     * 
     * @param seqno seqno
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno;
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
     * 获取 pTaskId
     * 
     * @return pTaskId.
     */
    public String getpTaskId() {
        return pTaskId;
    }

    /**
     * 设置 pTaskId
     * 
     * @param pTaskId pTaskId
     */
    public void setpTaskId(String pTaskId) {
        this.pTaskId = pTaskId;
    }

    /**
     * 获取 tradeType
     * 
     * @return tradeType.
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     * 设置 tradeType
     * 
     * @param tradeType tradeType
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    /**
     * 获取 allDetail
     * 
     * @return allDetail.
     */
    public String getAllDetail() {
        return allDetail;
    }

    /**
     * 设置 allDetail
     * 
     * @param allDetail allDetail
     */
    public void setAllDetail(String allDetail) {
        this.allDetail = allDetail;
    }

    
    /**
     * 获取 taskExeRule
     * @return taskExeRule.
     */
    public String getTaskExeRule() {
        return taskExeRule;
    }

    
    /**
     * 设置 taskExeRule
     * @param taskExeRule taskExeRule
     */
    public void setTaskExeRule(String taskExeRule) {
        this.taskExeRule = taskExeRule;
    }

}
