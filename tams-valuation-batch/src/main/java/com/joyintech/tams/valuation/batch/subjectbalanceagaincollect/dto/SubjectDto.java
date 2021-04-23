/*
 * 文件名：SubjectBalanceCollectDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.batch.subjectbalanceagaincollect.dto;

/**
 * 账套科目Dto
 *
 * @author lby
 * @version 1.0 2020年12月29日
 * @see SubjectDto
 * @since 1.0
 */
public class SubjectDto {

    /**
     * 账套代码
     */
    private String booksetId;
    /**
     * 科目号
     */
    private String subjectNo;
    /**
     * 上级科目号
     */
    private String fsubjectNo;
    /**
     * 科目级别
     */
    private String subjectLevel;
    /**
     * 余额方向
     */
    private String balFlag;

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
     * 取得 科目号
     * 
     * @return 科目号
     */
    public String getSubjectNo() {
        return subjectNo;
    }

    /**
     * 设置 科目号
     * 
     * @param subjectNo
     *            科目号
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }

    /**
     * 取得 上级科目号
     * 
     * @return 上级科目号
     */
    public String getFsubjectNo() {
        return fsubjectNo;
    }

    /**
     * 设置 上级科目号
     * 
     * @param fsubjectNo
     *            上级科目号
     */
    public void setFsubjectNo(String fsubjectNo) {
        this.fsubjectNo = fsubjectNo;
    }

    /**
     * 取得 科目级别
     * 
     * @return 科目级别
     */
    public String getSubjectLevel() {
        return subjectLevel;
    }

    /**
     * 设置 科目级别
     * 
     * @param subjectLevel
     *            科目级别
     */
    public void setSubjectLevel(String subjectLevel) {
        this.subjectLevel = subjectLevel;
    }

    /**
     * 获取 余额方向
     * 
     * @return 余额方向.
     */
    public String getBalFlag() {
        return balFlag;
    }

    /**
     * 设置 余额方向
     * 
     * @param balFlag
     *            余额方向
     */
    public void setBalFlag(String balFlag) {
        this.balFlag = balFlag;
    }
}
