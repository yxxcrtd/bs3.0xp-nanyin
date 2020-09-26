/*
 * 文件名：BookAccountGenerateReadResult.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：杨智
 * 修改时间：2018年6月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.bookaccountgenerate.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;


public class BookAccountGenerateReadResult extends NeedOperateEntity {
    /**
     * 账套代码
     */
    private String booksetId;
    
    /**
     * 入账日期
     */
    private Date bookDate;
    
    private List<BookAccountDetail> bookAccountDetailList;

    
    public String getBooksetId() {
        return booksetId;
    }

    
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    
    public List<BookAccountDetail> getBookAccountDetailList() {
        return bookAccountDetailList;
    }

    
    public void setBookAccountDetailList(List<BookAccountDetail> bookAccountDetailList) {
        this.bookAccountDetailList = bookAccountDetailList;
    }


    public Date getBookDate() {
        return bookDate;
    }


    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }
    
}
