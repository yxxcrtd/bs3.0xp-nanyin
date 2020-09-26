/*
 * 文件名：BookAccountGenerateDelResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年6月21日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.bookaccountgenerate.dto;

import java.util.Date;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;

public class BookAccountGenerateDelResult extends NeedToWriteEntity {

    /**
     * 入账日
     */
    private Date bookDate;

    /**
     * 任务代码
     */
    private String taskId;

    public BookAccountGenerateDelResult(Date bookDate, String taskId) {
        this.bookDate = bookDate;
        this.taskId = taskId;
    }

    /**
     * 获取 bookDate
     * 
     * @return bookDate.
     */
    public Date getBookDate() {
        return bookDate;
    }

    /**
     * 设置 bookDate
     * 
     * @param bookDate bookDate
     */
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
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

}
