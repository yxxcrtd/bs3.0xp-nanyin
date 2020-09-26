/*
 * 文件名：ValSubjectBalanceAbnormalQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2019年1月28日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.financemanage.valsubjectbalanceabnormalquery.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 到期产品科目余额异常查询-dto
 * 
 * @author niuqianyu
 * @version 1.0 2019年1月28日
 * @see ValSubjectBalanceAbnormalQuery001Dto
 * @since 1.0
 */
public class ValSubjectBalanceAbnormalQuery001Dto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 日期
     */
    private String balanceDate;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 账套名称
     */
    private String booksetName;

    /**
     * 异常科目代码
     */
    private String subjectNo;

    /**
     * 异常科目名称
     */
    private String subjectName;

    /**
     * 余额方向
     */
    private String balFlag;

    /**
     * 余额方向Code
     */
    private String balFlagFmt;

    /**
     * 科目余额
     */
    private BigDecimal oAmt;

    /**
     * 获取 balanceDate
     * 
     * @return balanceDate.
     */
    public String getBalanceDate() {
        return balanceDate;
    }

    /**
     * 设置 balanceDate
     * 
     * @param balanceDate balanceDate
     */
    public void setBalanceDate(String balanceDate) {
        this.balanceDate = balanceDate;
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
     * 获取 booksetName
     * 
     * @return booksetName.
     */
    public String getBooksetName() {
        return booksetName;
    }

    /**
     * 设置 booksetName
     * 
     * @param booksetName booksetName
     */
    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName;
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
     * 获取 subjectName
     * 
     * @return subjectName.
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * 设置 subjectName
     * 
     * @param subjectName subjectName
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * 获取 balFlag
     * 
     * @return balFlag.
     */
    public String getBalFlag() {
        return balFlag;
    }

    /**
     * 设置 balFlag
     * 
     * @param balFlag balFlag
     */
    public void setBalFlag(String balFlag) {
        this.balFlag = balFlag;
    }

    /**
     * 获取 balFlagCode
     * 
     * @return balFlagCode.
     */
    public String getBalFlagFmt() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.余额方向, this.balFlag))?
                SysDataUtil.getNameFromDataDict(DictCode.余额方向, this.balFlag):this.balFlag;
    }

    /**
     * 获取 oAmt
     * 
     * @return oAmt.
     */
    public BigDecimal getoAmt() {
        return oAmt;
    }

    /**
     * 设置 oAmt
     * 
     * @param oAmt oAmt
     */
    public void setoAmt(BigDecimal oAmt) {
        this.oAmt = oAmt;
    }

}
