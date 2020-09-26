/*
 * 文件名：ValSubjectWaterQuery001SearchDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月12日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.financemanage.accountbook.valsubjectwaterquery.dto;

import java.util.Date;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author Administrator
 * @version 1.0 2018年7月12日
 * @see ValSubjectWaterQuery001SearchDto
 * @since 1.0
 */
public class ValSubjectWaterQuery001SearchDto {

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 账套类别
     */
    private String booksetType;

    /**
     * 业务类别
     */
    private String comTableId;

    /**
     * 资产/负债代码
     */
    private String busiId;

    /**
     * 记账日期
     */
    private String bookDate;

    /**
     * 记账日期开始时间
     */
    private Date bookDateBegin;

    /**
     * 记账日期结束时间
     */
    private Date bookDateEnd;

    /**
     * 科目号
     */
    private String subjectNo;

    private String subjectNo1;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 账套代码
     */
    private String[] booksetIdArr;

    /**
     * 业务类别
     */
    private String[] comTableIdArr;

    /**
     * 科目号
     */
    private String[] subjectNoArr;

    public String[] getBooksetIdArr() {
        return booksetIdArr;
    }

    public void setBooksetIdArr(String[] booksetIdArr) {
        this.booksetIdArr = booksetIdArr;
    }

    /**
     * 获取 comTableIdArr
     * 
     * @return comTableIdArr.
     */
    public String[] getComTableIdArr() {
        return comTableIdArr;
    }

    /**
     * 设置 comTableIdArr
     * 
     * @param comTableIdArr comTableIdArr
     */
    public void setComTableIdArr(String[] comTableIdArr) {
        this.comTableIdArr = comTableIdArr;
    }

    /**
     * 获取 subjectNoArr
     * 
     * @return subjectNoArr.
     */
    public String[] getSubjectNoArr() {
        return subjectNoArr;
    }

    /**
     * 设置 subjectNoArr
     * 
     * @param subjectNoArr subjectNoArr
     */
    public void setSubjectNoArr(String[] subjectNoArr) {
        this.subjectNoArr = subjectNoArr;
    }

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
     * 获取 booksetType
     * 
     * @return booksetType.
     */
    public String getBooksetType() {
        return booksetType;
    }

    /**
     * 设置 booksetType
     * 
     * @param booksetType booksetType
     */
    public void setBooksetType(String booksetType) {
        this.booksetType = booksetType;
    }

    /**
     * 获取 tableId
     * 
     * @return tableId.
     */
    public String getComTableId() {
        return comTableId;
    }

    /**
     * 设置 tableId
     * 
     * @param tableId tableId
     */
    public void setComTableId(String comTableId) {
        this.comTableId = comTableId;
    }

    /**
     * 获取 busiId
     * 
     * @return busiId.
     */
    public String getBusiId() {
        return busiId;
    }

    /**
     * 设置 busiId
     * 
     * @param busiId busiId
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    /**
     * 获取 bookDate
     * 
     * @return bookDate.
     */
    public String getBookDate() {
        return bookDate;
    }

    /**
     * 设置 bookDate
     * 
     * @param bookDate bookDate
     */
    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    /**
     * 获取 bookDateBegin
     * 
     * @return bookDateBegin.
     */
    public Date getBookDateBegin() {
        return bookDateBegin;
    }

    /**
     * 设置 bookDateBegin
     * 
     * @param bookDateBegin bookDateBegin
     */
    public void setBookDateBegin(Date bookDateBegin) {
        this.bookDateBegin = bookDateBegin;
    }

    /**
     * 获取 bookDateEnd
     * 
     * @return bookDateEnd.
     */
    public Date getBookDateEnd() {
        return bookDateEnd;
    }

    /**
     * 设置 bookDateEnd
     * 
     * @param bookDateEnd bookDateEnd
     */
    public void setBookDateEnd(Date bookDateEnd) {
        this.bookDateEnd = bookDateEnd;
    }

    /**
     * 获取 subjectNo
     * 
     * @return subjectNo.
     */
    public String getSubjectNo() {
        return subjectNo;
    }

    /**
     * 设置 subjectNo
     * 
     * @param subjectNo subjectNo
     */
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }

    /**
     * 获取 subjectNo1
     * 
     * @return subjectNo1.
     */
    public String getSubjectNo1() {
        return subjectNo1;
    }

    /**
     * 设置 subjectNo1
     * 
     * @param subjectNo1 subjectNo1
     */
    public void setSubjectNo1(String subjectNo1) {
        this.subjectNo1 = subjectNo1;
    }

    /**
     * 获取 ccy
     * 
     * @return ccy.
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 ccy
     * 
     * @param ccy ccy
     */
    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

}
