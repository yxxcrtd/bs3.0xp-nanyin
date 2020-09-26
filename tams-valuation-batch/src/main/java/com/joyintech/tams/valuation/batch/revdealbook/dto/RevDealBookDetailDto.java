/*
 * 文件名：BookDetailCompareIntoReadResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.revdealbook.dto;

import java.util.Date;

import com.joyintech.tams.common.base.entity.TrdProductDeal;

/**
 * 撤销查询明细数据
 * 
 * @author chengzhong
 * @version 1.0 2018年6月7日
 * @see RevDealBookDetailDto
 * @since 1.0
 */
public class RevDealBookDetailDto extends TrdProductDeal {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 5677201097268785067L;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 任务代码
     */
    private String taskId;
    
    /**
     * 账套日期
     */
    private Date booksetDate;

    /**
     * 入账日期
     */
    private Date bookDate;

    /**
     * 发生日期
     */
    private Date happenDate;

    
    /**
     * 获取 booksetId
     * @return booksetId.
     */
    public String getBooksetId() {
        return booksetId;
    }

    
    /**
     * 设置 booksetId
     * @param booksetId booksetId
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    
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

    
    /**
     * 获取 booksetDate
     * @return booksetDate.
     */
    public Date getBooksetDate() {
        return booksetDate;
    }

    
    /**
     * 设置 booksetDate
     * @param booksetDate booksetDate
     */
    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }

    
    /**
     * 获取 bookDate
     * @return bookDate.
     */
    public Date getBookDate() {
        return bookDate;
    }

    
    /**
     * 设置 bookDate
     * @param bookDate bookDate
     */
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    
    /**
     * 获取 happenDate
     * @return happenDate.
     */
    public Date getHappenDate() {
        return happenDate;
    }

    
    /**
     * 设置 happenDate
     * @param happenDate happenDate
     */
    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }
    
}
