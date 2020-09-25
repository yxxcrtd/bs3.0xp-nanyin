/*
 * 文件名：BooksetInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年11月10日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.datatransplant.dto;

import java.util.Date;

/**
 * 数据移植账套信息
 *
 * @author chengzhong
 * @version 1.0 2018年11月10日
 * @see DataBooksetInfoDto
 * @since 1.0
 */
public class DataBooksetInfoDto {

    /**
     * 账套代码
     */
    private String booksetId;
 
    /**
     * 币种
     */
    private String oCcy;
    
    
    /**
     * 开始日
     */
    private Date startDate;
    
    /**
     * 结束日
     */
    private Date endDate;

	/**
	 * 获取 booksetId
	 * @return booksetId.
	 */
	public String getBooksetId() {
		return booksetId;
	}

	/**
	 * 设置 booksetId
	 * @param booksetId booksetId
	 */
	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}

	/**
	 * 获取 oCcy
	 * @return oCcy.
	 */
	public String getoCcy() {
		return oCcy;
	}

	/**
	 * 设置 oCcy
	 * @param oCcy oCcy
	 */
	public void setoCcy(String oCcy) {
		this.oCcy = oCcy;
	}

	/**
	 * 获取 startDate
	 * @return startDate.
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * 设置 startDate
	 * @param startDate startDate
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * 获取 endDate
	 * @return endDate.
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置 endDate
	 * @param endDate endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
    
    
}
