/*
 * 文件名：TaskTodoScopeEntry.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月8日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.accountingmethod.entity;

import java.util.Date;

/**
 * 跑批范围信息实体类
 * 
 * @author yangjian
 * @version 1.0 2018年5月8日
 * @see TaskTodoScopeEntry
 * @since 1.0
 */
public class TaskTodoScopeEntry {

    /**
     * 批次号(批处理任务组执行ID)
     */
    private String seqNo;

    /**
     * 任务代码
     */
    private String taskId;

    /**
     * 套账代码
     */
    private String booksetId;

    /**
     * 最小跑批日（发生日）
     */
    private Date minCdate;

    /**
     * 最大跑批日（入账日）
     */
    private Date maxCdate;

    /**
     * 金融产品代码
     */
    private String finprodId;

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public Date getMinCdate() {
        return minCdate;
    }

    public void setMinCdate(Date minCdate) {
        this.minCdate = minCdate;
    }

    public Date getMaxCdate() {
        return maxCdate;
    }

    public void setMaxCdate(Date maxCdate) {
        this.maxCdate = maxCdate;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }
}
