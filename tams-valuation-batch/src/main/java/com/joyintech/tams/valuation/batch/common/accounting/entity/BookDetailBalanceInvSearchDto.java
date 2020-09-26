/*
 * 文件名：SubjectBalanceSearchDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年6月12日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.accounting.entity;

import java.util.Date;
import java.util.List;

/**
 * 账务明细余额-区间 查询参数Dto
 * @author weipeng
 * @version 1.0 2018年6月12日
 * @see BookDetailBalanceInvSearchDto
 * @since 1.0
 */
public class BookDetailBalanceInvSearchDto {

    /**
     * 账套代码（必填）
     */
    private String booksetId;

    /**
     * 开始日（必填）
     */
    private Date beginDate;
    
    /**
     * 结束日（必填）
     */
    private Date endDate;

    /**
     * 入账日（必填）
     */
    private Date bookDate;


    /**
     * 基础科目性质集合
     */
    private List<String> baseSubjectNatureList;

    /**
     * 科目类型集合
     */
    private List<String> subjectTypeList;

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
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    /**
     * 获取 开始日
     * @return  开始日.
     */
    public Date getBeginDate() {
        return beginDate;
    }

    
    /**
     * 设置 开始日
     * @param beginDate 开始日
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    
    /**
     * 获取 结束日
     * @return 结束日.
     */
    public Date getEndDate() {
        return endDate;
    }

    
    /**
     * 设置 结束日
     * @param endDate 结束日
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 取得 入账日（必填）
     * 
     * @return 入账日（必填）
     */
    public Date getBookDate() {
        return bookDate;
    }

    /**
     * 设置 入账日（必填）
     * 
     * @param bookdate 入账日（必填）
     */
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    /**
     * 取得 基础科目性质集合
     * 
     * @return 基础科目性质集合
     */
    public List<String> getBaseSubjectNatureList() {
        return baseSubjectNatureList;
    }

    /**
     * 设置 基础科目性质集合
     * 
     * @param baseSubjectNatureList 基础科目性质集合
     */
    public void setBaseSubjectNatureList(List<String> baseSubjectNatureList) {
        this.baseSubjectNatureList = baseSubjectNatureList;
    }

    /**
     * 取得 科目类型集合
     * 
     * @return 科目类型集合
     */
    public List<String> getSubjectTypeList() {
        return subjectTypeList;
    }

    /**
     * 设置 科目类型集合
     * 
     * @param subjectTypeList 科目类型集合
     */
    public void setSubjectTypeList(List<String> subjectTypeList) {
        this.subjectTypeList = subjectTypeList;
    }

}
