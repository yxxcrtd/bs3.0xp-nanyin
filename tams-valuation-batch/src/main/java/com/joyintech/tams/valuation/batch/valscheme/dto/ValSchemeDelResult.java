/*
 * 文件名：ValNetConfirmDelDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年10月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.valscheme.dto;

import java.util.Date;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;

/**
 * 净值确认 删除批处理
 * 
 * @author weipeng
 * @version 1.0 2018年10月18日
 * @see ValSchemeDelResult
 * @since 1.0
 */
public class ValSchemeDelResult extends NeedToWriteEntity {

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 入账日
     */
    private Date bookDate;

    /**
     * 任务代码
     */
    private String taskId;

    /**
     * 获取 finprodId
     * 
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     * 
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
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
