/*
 * 文件名：TodoDeal.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：maodongjin
 * 修改时间：2018年5月5日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.batchchilddispatch.dto;

import java.util.Date;

/**
 * 子调度 - 倒起息交易数据 实体类
 * @author maodongjin
 * @version 1.0 2018年5月5日
 * @see TodoDeal
 * @since 1.0
 */
public class TodoDeal {
    /**
     * 账套代码
     */
    private String booksetId;
    
    /**
     * 账套日期
     */
    private Date bookSetDate;
    
    /**
     * 倒起息跑批范围（全量/差量）
     */
    private String backRange;
    
    /**
     * 业务类型
     */
    private String busiType;
    
    /**
     * 入账日
     */
    private Date bookDate;
    
    /**
     * 账务明细代码
     */
    private String bokDetailId;
    
    /**
     * 发生日
     */
    private Date happenDate;
    
    

	public String getBooksetId() {
		return booksetId;
	}

	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}

	public Date getBookSetDate() {
		return bookSetDate;
	}

	public void setBookSetDate(Date bookSetDate) {
		this.bookSetDate = bookSetDate;
	}

	public String getBackRange() {
		return backRange;
	}

	public void setBackRange(String backRange) {
		this.backRange = backRange;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}

	public String getBokDetailId() {
		return bokDetailId;
	}

	public void setBokDetailId(String bokDetailId) {
		this.bokDetailId = bokDetailId;
	}

	public Date getHappenDate() {
		return happenDate;
	}

	public void setHappenDate(Date happenDate) {
		this.happenDate = happenDate;
	}  
}
