/*
 * 文件名：BooksetTodoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：lby
 * 修改时间：2020年12月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.subjectbalanceagaincollect.dto;

import java.io.Serializable;
import java.util.Date;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;

/**
 * 待处理账套Dto
 * 
 * @author lby
 * @version 1.0 2020年12月29日
 * @see BooksetTodoDto
 * @since 1.0
 */
public class BooksetTodoDto extends NeedOperateEntity implements Serializable {

    private static final long serialVersionUID = -1998677849280203146L;

    /**
     * 账套代码
     */
    private String booksetId;
    /**
     * 账套日期
     */
    private Date booksetDate;
    /**
     * 入账日
     */
    private Date bookDate;
    /**
     * 发生日
     */
    private Date happenDate;
    /**
     * 本位币
     */
    private String standardCcy;
    /**
     * 汇总开始日
     */
    private Date startBookDate;
    /**
     * 汇总结束日
     */
    private Date endBookDate;
    /**
     * 取得 账套代码
     * 
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * 
     * @param booksetId
     *            账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    /**
     * 取得 账套日期
     * 
     * @return 账套日期
     */
    public Date getBooksetDate() {
        return booksetDate;
    }

    /**
     * 设置 账套日期
     * 
     * @param booksetDate
     *            账套日期
     */
    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }

    /**
     * 取得 入账日
     * 
     * @return 入账日
     */
    public Date getBookDate() {
        return bookDate;
    }

    /**
     * 设置 入账日
     * 
     * @param bookDate
     *            入账日
     */
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    /**
     * 取得 发生日
     * 
     * @return 发生日
     */
    public Date getHappenDate() {
        return happenDate;
    }

    /**
     * 设置 发生日
     * 
     * @param happenDate
     *            发生日
     */
    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    /**
     * 取得 本位币
     * 
     * @return 本位币
     */
    public String getStandardCcy() {
        return standardCcy;
    }

    /**
     * 设置 本位币
     * 
     * @param standardCcy
     *            本位币
     */
    public void setStandardCcy(String standardCcy) {
        this.standardCcy = standardCcy;
    }

    public Date getStartBookDate() {
        return startBookDate;
    }

    public void setStartBookDate(Date startBookDate) {
        this.startBookDate = startBookDate;
    }

    public Date getEndBookDate() {
        return endBookDate;
    }

    public void setEndBookDate(Date endBookDate) {
        this.endBookDate = endBookDate;
    }
}
