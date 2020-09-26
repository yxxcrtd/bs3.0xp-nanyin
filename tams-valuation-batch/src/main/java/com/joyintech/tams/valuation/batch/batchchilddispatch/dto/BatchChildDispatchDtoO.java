/*
 * 文件名：BatchChildDispatchDtoO.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：maodongjin
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.batchchilddispatch.dto;

import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author maodongjin
 * @version 1.0 2018年5月5日
 * @see BatchChildDispatchDtoO
 * @since 1.0
 */
public class BatchChildDispatchDtoO extends NeedOperateEntity{

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * <意义，目的和功能，以及可能被用到的地方> 明细结果数据
     */
    private List<TodoDeal> deals;

    /**
     * 批处理任务
     */
    private List<TodoTask> tasks;

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
     * 获取 deals
     * 
     * @return deals.
     */
    public List<TodoDeal> getDeals() {
        return deals;
    }

    /**
     * 设置 deals
     * 
     * @param deals deals
     */
    public void setDeals(List<TodoDeal> deals) {
        this.deals = deals;
    }

    /**
     * 获取 tasks
     * 
     * @return tasks.
     */
    public List<TodoTask> getTasks() {
        return tasks;
    }

    /**
     * 设置 tasks
     * 
     * @param tasks tasks
     */
    public void setTasks(List<TodoTask> tasks) {
        this.tasks = tasks;
    }

}
