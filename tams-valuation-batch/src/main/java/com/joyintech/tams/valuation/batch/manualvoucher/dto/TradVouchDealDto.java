/*
 * 文件名：TradVouchDealDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：chenggaoling
 * 修改时间：2018年8月14日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.batch.manualvoucher.dto;

import java.util.Date;

/**
 * 
 * 手工凭证交易DTO〉
 * 
 * @author chenggaoling
 * @version 1.0 2018年8月14日
 * @see TradVouchDealDto
 * @since 1.0
 */
public class TradVouchDealDto {
   
    /**
     * 交易编号
     */
    private String tradeid;

    /**
     * 账套代码
     */
    private String booksetid;

    /**
     * 分录类型
     */
    private String recordtype;

    /**
     * 记账日期
     */
    private Date bookdate;

    /**
     * 冲销标识
     */
    private String offsetflag;

    /**
     * 业务明细代码
     */
    private String busiid;

    /**
     * 业务明细类型
     */
    private String busidetailtype;

    /**
     * 业务类型
     */
    private String busitype;

    /**
     * 凭证编号
     */
    private String bookid;

    /**
     * 凭证类型
     */
    private String booktype;

    /**
     * 摘要
     */
    private String bookremark;

    /**
     * 原始交易编号
     */
    private String ptradeid;

    /**
     * 投资目的
     */
    private String invaim;

    /**
     * 取得 交易编号
     * 
     * @return 交易编号
     */
    public String getTradeid() {
        return tradeid;
    }

    /**
     * 设置 交易编号
     * 
     * @param tradeid 交易编号
     */
    public void setTradeid(String tradeid) {
        this.tradeid = tradeid;
    }

    /**
     * 取得 账套代码
     * 
     * @return 账套代码
     */
    public String getBooksetid() {
        return booksetid;
    }

    /**
     * 设置 账套代码
     * 
     * @param booksetid 账套代码
     */
    public void setBooksetid(String booksetid) {
        this.booksetid = booksetid;
    }

    /**
     * 取得 分录类型
     * 
     * @return 分录类型
     */
    public String getRecordtype() {
        return recordtype;
    }

    /**
     * 设置 分录类型
     * 
     * @param recordtype 分录类型
     */
    public void setRecordtype(String recordtype) {
        this.recordtype = recordtype;
    }

    /**
     * 取得 记账日期
     * 
     * @return 记账日期
     */
    public Date getBookdate() {
        return bookdate;
    }

    /**
     * 设置 记账日期
     * 
     * @param bookdate 记账日期
     */
    public void setBookdate(Date bookdate) {
        this.bookdate = bookdate;
    }

    /**
     * 取得 冲销标识
     * 
     * @return 冲销标识
     */
    public String getOffsetflag() {
        return offsetflag;
    }

    /**
     * 设置 冲销标识
     * 
     * @param offsetflag 冲销标识
     */
    public void setOffsetflag(String offsetflag) {
        this.offsetflag = offsetflag;
    }

    /**
     * 取得 业务明细代码
     * 
     * @return 业务明细代码
     */
    public String getBusiid() {
        return busiid;
    }

    /**
     * 设置 业务明细代码
     * 
     * @param busiid 业务明细代码
     */
    public void setBusiid(String busiid) {
        this.busiid = busiid;
    }

    /**
     * 取得 业务明细类型
     * 
     * @return 业务明细类型
     */
    public String getBusidetailtype() {
        return busidetailtype;
    }

    /**
     * 设置 业务明细类型
     * 
     * @param busidetailtype 业务明细类型
     */
    public void setBusidetailtype(String busidetailtype) {
        this.busidetailtype = busidetailtype;
    }

    /**
     * 取得 业务类型
     * 
     * @return 业务类型
     */
    public String getBusitype() {
        return busitype;
    }

    /**
     * 设置 业务类型
     * 
     * @param busitype 业务类型
     */
    public void setBusitype(String busitype) {
        this.busitype = busitype;
    }

    /**
     * 取得 凭证编号
     * 
     * @return 凭证编号
     */
    public String getBookid() {
        return bookid;
    }

    /**
     * 设置 凭证编号
     * 
     * @param bookid 凭证编号
     */
    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    /**
     * 取得 凭证类型
     * 
     * @return 凭证类型
     */
    public String getBooktype() {
        return booktype;
    }

    /**
     * 设置 凭证类型
     * 
     * @param booktype 凭证类型
     */
    public void setBooktype(String booktype) {
        this.booktype = booktype;
    }

    /**
     * 取得 摘要
     * 
     * @return 摘要
     */
    public String getBookremark() {
        return bookremark;
    }

    /**
     * 设置 摘要
     * 
     * @param bookremark 摘要
     */
    public void setBookremark(String bookremark) {
        this.bookremark = bookremark;
    }

    /**
     * 取得 原始交易编号
     * 
     * @return 原始交易编号
     */
    public String getPtradeid() {
        return ptradeid;
    }

    /**
     * 设置 原始交易编号
     * 
     * @param ptradeid 原始交易编号
     */
    public void setPtradeid(String ptradeid) {
        this.ptradeid = ptradeid;
    }

    /**
     * 取得 投资目的
     * 
     * @return 投资目的
     */
    public String getInvaim() {
        return invaim;
    }

    /**
     * 设置 投资目的
     * 
     * @param invaim 投资目的
     */
    public void setInvaim(String invaim) {
        this.invaim = invaim;
    }

}
