/*
 * 文件名：ValSubjectBalanceQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：wanggengyun
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.financemanage.accountbook.valsubjectbalancequery.dto;

import java.math.BigDecimal;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.BokBalance;
import com.joyintech.tams.constant.DictCode;

/**
 * 科目余额查询Dto
 * 
 * @author 王耕耘
 * @version 1.0 2018年5月16日
 * @see ValSubjectBalanceQuery001Dto
 * @since 1.0
 */
public class ValSubjectBalanceQuery001Dto extends BokBalance {

    private static final long serialVersionUID = 1L;
    
    /**
     * 本位币余额
     */
    private BigDecimal bAmt;

    /**
     * 账套日期
     */
    private String bookDateBegin;

    /**
     * 账套日期
     */
    private String bookDateEnd;

    /**
     * 账套名称
     */
    private String bookSetName;

    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 父科目名称
     */
    private String fsubjectName;

    /**
     * 获取 balanceDateStr
     * 
     * @return balanceDateStr.
     */
    public String getBalanceDateFmt() {
        return DateUtil.format(this.getBalanceDate(), "yyyy-MM-dd");
    }

    /**
     * 获取 bookDateBegin
     * 
     * @return bookDateBegin.
     */
    public String getBookDateBegin() {
        return bookDateBegin;
    }

    /**
     * 设置 bookDateBegin
     * 
     * @param bookDateBegin bookDateBegin
     */
    public void setBookDateBegin(String bookDateBegin) {
        this.bookDateBegin = bookDateBegin;
    }

    /**
     * 获取 bookDateEnd
     * 
     * @return bookDateEnd.
     */
    public String getBookDateEnd() {
        return bookDateEnd;
    }

    /**
     * 设置 bookDateEnd
     * 
     * @param bookDateEnd bookDateEnd
     */
    public void setBookDateEnd(String bookDateEnd) {
        this.bookDateEnd = bookDateEnd;
    }

    /**
     * 获取 bookSetName
     * 
     * @return bookSetName.
     */
    public String getBookSetName() {
        return bookSetName;
    }

    /**
     * 设置 bookSetName
     * 
     * @param bookSetName bookSetName
     */
    public void setBookSetName(String bookSetName) {
        this.bookSetName = bookSetName;
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
     * 获取 fsubjectName
     * 
     * @return fsubjectName.
     */
    public String getFsubjectName() {
        return fsubjectName;
    }

    /**
     * 设置 fsubjectName
     * 
     * @param fsubjectName fsubjectName
     */
    public void setFsubjectName(String fsubjectName) {
        this.fsubjectName = fsubjectName;
    }
    
    /**
     * 获取 本位币余额
     */
    public BigDecimal getbAmt(){
        if(this.bAmt == null){
            return BigDecimal.ZERO;
        }
        return this.bAmt;
    }

    /**
     * 设置 本位币余额
     * @param bAmt 本位币余额
     */
    public void setbAmt(BigDecimal bAmt) {
        this.bAmt = bAmt;
    }
    

}
