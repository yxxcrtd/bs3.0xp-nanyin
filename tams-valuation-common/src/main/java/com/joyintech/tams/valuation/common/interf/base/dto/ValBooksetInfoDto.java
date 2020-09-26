/*
 * 文件名：ValBooksetInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年7月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.dto;

import java.util.Date;

/**
 * 账套信息DTO
 * 
 * @author 王波
 * @version 1.0 2018年7月3日
 * @see ValBooksetInfoDto
 * @since 1.0
 */
public class ValBooksetInfoDto {
    
	/**
     * 账套代码
     */
    private String booksetId;
    
    /**
     * 账套名称
     */
    private String booksetName;
    
    /**
     * 账套类别
     */
    private String booksetType;
    
    /**
     * 核算方式
     */
    private String acctMode;
    
    /**
     * 核算主体代码
     */
    private String acctSubjectId;
    
    /**
     * 核算主体类别
     */
    private String acctSubjectType;
    
    /**
     * 本位币
     */
    private String standardCcy;
    
    /**
     * 模板账套代码
     */
    private String booksetIdTmpl;
    
    /**
     * 会计准则
     */
    private String acctStandard;
    
    /**
     * 账套开始日
     */
    private Date startDate;
    
    /**
     * 账套结束日
     */
    private Date endDate;
    
    /**
     * 当前日期
     */
    private Date booksetDate;

    /**
     * 特殊核算模型
     */
    private String bokSpecMode;
    
    /**
     * 账套全称
     */
    private String booksetFullname;
    
    public String getBooksetFullname() {
		return booksetFullname;
	}


	public void setBooksetFullname(String booksetFullname) {
		this.booksetFullname = booksetFullname;
	}


	public String getBooksetId() {
        return booksetId;
    }

    
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    
    public String getBooksetName() {
        return booksetName;
    }

    
    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName;
    }

    
    public String getBooksetType() {
        return booksetType;
    }

    
    public void setBooksetType(String booksetType) {
        this.booksetType = booksetType;
    }

    
    public String getAcctMode() {
        return acctMode;
    }

    
    public void setAcctMode(String acctMode) {
        this.acctMode = acctMode;
    }

    
    public String getAcctSubjectId() {
        return acctSubjectId;
    }

    
    public void setAcctSubjectId(String acctSubjectId) {
        this.acctSubjectId = acctSubjectId;
    }

    
    public String getAcctSubjectType() {
        return acctSubjectType;
    }

    
    public void setAcctSubjectType(String acctSubjectType) {
        this.acctSubjectType = acctSubjectType;
    }

    
    public String getStandardCcy() {
        return standardCcy;
    }

    
    public void setStandardCcy(String standardCcy) {
        this.standardCcy = standardCcy;
    }

    
    public String getBooksetIdTmpl() {
        return booksetIdTmpl;
    }

    
    public void setBooksetIdTmpl(String booksetIdTmpl) {
        this.booksetIdTmpl = booksetIdTmpl;
    }

    
    public String getAcctStandard() {
        return acctStandard;
    }

    
    public void setAcctStandard(String acctStandard) {
        this.acctStandard = acctStandard;
    }

    
    public Date getStartDate() {
        return startDate;
    }

    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    
    public Date getEndDate() {
        return endDate;
    }

    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    
    public Date getBooksetDate() {
        return booksetDate;
    }

    
    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }


	public String getBokSpecMode() {
		return bokSpecMode;
	}


	public void setBokSpecMode(String bokSpecMode) {
		this.bokSpecMode = bokSpecMode;
	}
    
}
