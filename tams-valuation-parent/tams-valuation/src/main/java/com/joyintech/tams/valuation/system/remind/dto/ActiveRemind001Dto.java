/*
 * 文件名：ActiveRemind001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：ww
 * 修改时间：2019年02月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.system.remind.dto;

import com.joyintech.framework.base.BaseEntity;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author ww
 * @version 1.0 2019年02月27日
 * @see ActiveRemind001Dto
 * @since 1.0
 */

public class ActiveRemind001Dto extends BaseEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 4335909839362986036L;
    
    private String bookSetId;
    
    private String bookSetName;
    
    private String bookDate;

    private String reminder;
   
    
    public String getBookSetId() {
		return bookSetId;
	}

	public void setBookSetId(String bookSetId) {
		this.bookSetId = bookSetId;
	}

	public String getBookSetName() {
		return bookSetName;
	}

	public void setBookSetName(String bookSetName) {
		this.bookSetName = bookSetName;
	}

	public String getBookDate() {
		return bookDate;
	}

	public void setBookDate(String bookDate) {
		this.bookDate = bookDate;
	}

	/**
     * 获取 提醒说明
     * @return reminder.
     */
    public String getReminder() {
        return reminder;
    }
    
    /**
     * 设置 提醒说明
     * @param reminder reminder
     */
    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    
}
