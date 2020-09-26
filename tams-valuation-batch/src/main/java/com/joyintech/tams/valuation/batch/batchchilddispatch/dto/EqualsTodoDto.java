/*
 * 文件名：EqualsStringDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：yangjian
 * 修改时间：2018年5月29日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.batch.batchchilddispatch.dto;

import java.util.Date;

/**
 * 用于判断包含这几个字段的类是否同时相等（已重写hashcode和equals方法）
 * @author yangjian
 * @version 1.0 2018年5月29日
 * @see EqualsTodoDto
 * @since 1.0
 */
public class EqualsTodoDto {
	/**
	 * 账套代码
	 */
	private String bookSetId;
	
	/**
	 * 入账日
	 */
	private Date bookDate;
	
	/**
	 * 发生日
	 */
	private Date happenDate;
	
	/**
	 * 构造方法
	 * @param bookSetId
	 * @param bookDate
	 * @param happenDate
	 */
	public EqualsTodoDto(String bookSetId, Date bookDate, Date happenDate) {
		super();
		this.bookSetId = bookSetId;
		this.bookDate = bookDate;
		this.happenDate = happenDate;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookDate == null) ? 0 : bookDate.hashCode());
		result = prime * result + ((bookSetId == null) ? 0 : bookSetId.hashCode());
		result = prime * result + ((happenDate == null) ? 0 : happenDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EqualsTodoDto other = (EqualsTodoDto) obj;
		if (bookDate == null) {
			if (other.bookDate != null)
				return false;
		} else if (!bookDate.equals(other.bookDate))
			return false;
		if (bookSetId == null) {
			if (other.bookSetId != null)
				return false;
		} else if (!bookSetId.equals(other.bookSetId))
			return false;
		if (happenDate == null) {
			if (other.happenDate != null)
				return false;
		} else if (!happenDate.equals(other.happenDate))
			return false;
		return true;
	}


    
    /**
     * 获取 bookSetId
     * @return bookSetId.
     */
    public String getBookSetId() {
        return bookSetId;
    }


    
    /**
     * 设置 bookSetId
     * @param bookSetId bookSetId
     */
    public void setBookSetId(String bookSetId) {
        this.bookSetId = bookSetId;
    }


    
    /**
     * 获取 bookDate
     * @return bookDate.
     */
    public Date getBookDate() {
        return bookDate;
    }


    
    /**
     * 设置 bookDate
     * @param bookDate bookDate
     */
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }


    
    /**
     * 获取 happenDate
     * @return happenDate.
     */
    public Date getHappenDate() {
        return happenDate;
    }


    
    /**
     * 设置 happenDate
     * @param happenDate happenDate
     */
    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

}
