/*
 * 文件名：BatchDimensionDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年8月14日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.batchdispatch.dto;

import java.util.Date;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzhiang
 * @version 1.0 2018年8月14日
 * @see BatchDimension
 * @since 1.0
 */
public class BatchDimension {
    /**
     * 账套日期
     */
    private String bookSetDate;

    /**
     * 入账日
     */
    private String bookDate;

    /**
     * 发生日
     */
    private String happenDate;

    
    /**
     * 获取 bookSetDate
     * @return bookSetDate.
     */
    public String getBookSetDate() {
        return bookSetDate;
    }

    
    /**
     * 设置 bookSetDate
     * @param bookSetDate bookSetDate
     */
    public void setBookSetDate(String bookSetDate) {
        this.bookSetDate = bookSetDate;
    }

    
    /**
     * 获取 bookDate
     * @return bookDate.
     */
    public String getBookDate() {
        return bookDate;
    }

    
    /**
     * 设置 bookDate
     * @param bookDate bookDate
     */
    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    
    /**
     * 获取 happenDate
     * @return happenDate.
     */
    public String getHappenDate() {
        return happenDate;
    }

    
    /**
     * 设置 happenDate
     * @param happenDate happenDate
     */
    public void setHappenDate(String happenDate) {
        this.happenDate = happenDate;
    }
}
