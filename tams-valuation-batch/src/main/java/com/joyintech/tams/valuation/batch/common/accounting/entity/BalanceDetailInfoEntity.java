/*
 * 文件名：BalanceDetailInfoEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月31日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.common.accounting.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

/**
 * 余额明细- 实体类
 * 
 * @author ruanhongming
 * @version 1.0 2018年5月30日
 * @see BalanceDetailInfoEntity
 * @since 1.0
 */
public class BalanceDetailInfoEntity extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 账套代码
     */
    private String booksetId;
    
    /**
     * 入账日
     */
    private Date bookDate;
    
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
    private Long subjectLevel;
    
    /**
     * 原币
     */
    private String oCcy;
    
    /**
     * 科目余额
     */
    private BigDecimal balance;
    
    /**
     * 数量余额
     */
    private BigDecimal num;
    
    /**
     * 余额方向
     */
    private String balFlag;

    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public String getSubjectNo() {
        return subjectNo;
    }

    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }

    public String getFsubjectNo() {
        return fsubjectNo;
    }

    public void setFsubjectNo(String fsubjectNo) {
        this.fsubjectNo = fsubjectNo;
    }

    public Long getSubjectLevel() {
        return subjectLevel;
    }

    public void setSubjectLevel(Long subjectLevel) {
        this.subjectLevel = subjectLevel;
    }
    
    /**
     * 获取原币
     * @return String
     * @see
     * @since 1.0
     */
    public String getoCcy() {
        return oCcy;
    }
    /**
     * 设置原币
     * @param String
     * @see
     * @since 1.0
     */
    public void setoCcy(String oCcy) {
        this.oCcy = oCcy;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public String getBalFlag() {
        return balFlag;
    }

    public void setBalFlag(String balFlag) {
        this.balFlag = balFlag;
    }

}
