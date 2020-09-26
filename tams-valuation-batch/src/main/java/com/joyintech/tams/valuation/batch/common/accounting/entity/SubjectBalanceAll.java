/*
 * 文件名：SubjectBalanceAll.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.common.accounting.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 查询科目余额-历史表（实体）
 * @author weipeng
 * @version 1.0 2018年5月30日
 * @see SubjectBalanceAll
 * @since 1.0
 */
public class SubjectBalanceAll {
    /**
     * 账套代码
     */
    private String booksetId;
    /**
     * 账务明细代码
     */
    private String bokDetailId;
    /**
     * 场景代码
     */
    private String tableId;
    /**
     * 基础科目性质
     */
    private String baseSubjectNature;
    /**
     * 原币
     */
    private String oCcy;
    /**
     * 科目余额（发生日）
     */
    private BigDecimal oAmtHappenDate;
    /**
     * 数量余额（发生日）
     */
    private BigDecimal numAmtHappenDate;
    /**
     * 余额方向（发生日）
     */
    private String balFlagHappenDate;
    /**
     * 科目余额（入账日）
     */
    private BigDecimal oAmtBookDate;
    /**
     * 数量余额（入账日）
     */
    private BigDecimal numAmtBookDate;
    /**
     * 余额方向（入账日）
     */
    private String balFlagBookDate;
    /**
     * 入账日
     */
    private Date bookDate;
    
    /**
     * 账套日期
     */
    private Date booksetDate;

    /**
     * 本位币科目余额（发生日）
     */
    private BigDecimal bAmtHappenDate;

    /**
     * 本币科目余额（入账日）
     */
    private BigDecimal bAmtBookDate;

    
    /**
     * 获取 账套日期
     * @return booksetDate.
     */
    public Date getBooksetDate() {
        return booksetDate;
    }

    
    /**
     * 设置 账套日期
     * @param booksetDate 账套日期
     */
    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }

    /**
     * 取得 账套日期
     * 
     * @return 账套日期
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套日期
     * 
     * @param booksetId
     *            账套日期
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    /**
     * 取得 账务明细代码
     * 
     * @return 账务明细代码
     */
    public String getBokDetailId() {
        return bokDetailId;
    }

    /**
     * 设置 账务明细代码
     * 
     * @param bokDetailId
     *            账务明细代码
     */
    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId;
    }

    /**
     * 取得 场景代码
     * 
     * @return 场景代码
     */
    public String getTableId() {
        return tableId;
    }

    /**
     * 设置 场景代码
     * 
     * @param tableId
     *            场景代码
     */
    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    /**
     * 取得 基础科目性质
     * 
     * @return 基础科目性质
     */
    public String getBaseSubjectNature() {
        return baseSubjectNature;
    }

    /**
     * 设置 基础科目性质
     * 
     * @param baseSubjectNature
     *            基础科目性质
     */
    public void setBaseSubjectNature(String baseSubjectNature) {
        this.baseSubjectNature = baseSubjectNature;
    }

    /**
     * 取得 原币
     * 
     * @return 原币
     */
    public String getOCcy() {
        return oCcy;
    }

    /**
     * 设置 原币
     * 
     * @param oCcy
     *            原币
     */
    public void setOCcy(String oCcy) {
        this.oCcy = oCcy;
    }

    /**
     * 取得 科目余额（发生日）
     * 
     * @return 科目余额（发生日）
     */
    public BigDecimal getOAmtHappenDate() {
        return oAmtHappenDate;
    }

    /**
     * 设置 科目余额（发生日）
     * 
     * @param oAmtHappenDate
     *            科目余额（发生日）
     */
    public void setOAmtHappenDate(BigDecimal oAmtHappenDate) {
        this.oAmtHappenDate = oAmtHappenDate;
    }

    /**
     * 取得 数量余额（发生日）
     * 
     * @return 数量余额（发生日）
     */
    public BigDecimal getNumAmtHappenDate() {
        return numAmtHappenDate;
    }

    /**
     * 设置 数量余额（发生日）
     * 
     * @param numAmtHappenDate
     *            数量余额（发生日）
     */
    public void setNumAmtHappenDate(BigDecimal numAmtHappenDate) {
        this.numAmtHappenDate = numAmtHappenDate;
    }

    /**
     * 取得 余额方向（发生日）
     * 
     * @return 余额方向（发生日）
     */
    public String getBalFlagHappenDate() {
        return balFlagHappenDate;
    }

    /**
     * 设置 余额方向（发生日）
     * 
     * @param balFlagHappenDate
     *            余额方向（发生日）
     */
    public void setBalFlagHappenDate(String balFlagHappenDate) {
        this.balFlagHappenDate = balFlagHappenDate;
    }

    /**
     * 取得 科目余额（入账日）
     * 
     * @return 科目余额（入账日）
     */
    public BigDecimal getOAmtBookDate() {
        return oAmtBookDate;
    }

    /**
     * 设置 科目余额（入账日）
     * 
     * @param oAmtBookDate
     *            科目余额（入账日）
     */
    public void setOAmtBookDate(BigDecimal oAmtBookDate) {
        this.oAmtBookDate = oAmtBookDate;
    }

    /**
     * 取得 数量余额（入账日）
     * 
     * @return 数量余额（入账日）
     */
    public BigDecimal getNumAmtBookDate() {
        return numAmtBookDate;
    }

    /**
     * 设置 数量余额（入账日）
     * 
     * @param numAmtBookDate
     *            数量余额（入账日）
     */
    public void setNumAmtBookDate(BigDecimal numAmtBookDate) {
        this.numAmtBookDate = numAmtBookDate;
    }

    /**
     * 取得 余额方向（入账日）
     * 
     * @return 余额方向（入账日）
     */
    public String getBalFlagBookDate() {
        return balFlagBookDate;
    }

    /**
     * 设置 余额方向（入账日）
     * 
     * @param balFlagBookDate
     *            余额方向（入账日）
     */
    public void setBalFlagBookDate(String balFlagBookDate) {
        this.balFlagBookDate = balFlagBookDate;
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


    public BigDecimal getBAmtHappenDate() {
        return bAmtHappenDate;
    }


    public void setBAmtHappenDate(BigDecimal bAmtHappenDate) {
        this.bAmtHappenDate = bAmtHappenDate;
    }


    public BigDecimal getbAmtBookDate() {
        return bAmtBookDate;
    }


    public void setbAmtBookDate(BigDecimal bAmtBookDate) {
        this.bAmtBookDate = bAmtBookDate;
    }


}
