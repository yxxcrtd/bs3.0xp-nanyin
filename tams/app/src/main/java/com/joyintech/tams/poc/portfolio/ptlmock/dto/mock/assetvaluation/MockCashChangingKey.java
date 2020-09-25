/*
 * 文件名：MockCashChangingKey.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2020年4月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.assetvaluation;

import java.util.Date;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;

/**
 * 现金变动MAP的key
 * 〈功能详细描述〉
 * @author qiaominghui
 * @version 1.0 2020年4月21日
 * @see MockCashChangingKey
 * @since 1.0
 */
public class MockCashChangingKey {
	
	public MockCashChangingKey(Date date,String type, String ccy) {
		this.date = date;
		this.type = type;
		this.ccy = ccy;
	}
	
	public MockCashChangingKey(String type, String ccy) {
		this.type = type;
		this.ccy = ccy;
	}
	
	/**
	 * 日期
	 */
	private Date date;
	
	/**
	 * 类型（01：再投，02：融资，03:保证金）
	 */
	private String type;
	
	/**
	 * 币种
	 */
	private String ccy;

	/**
	 * 获取 date
	 * @return date.
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * 设置 date
	 * @param date date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * 获取 type
	 * @return type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置 type
	 * @param type type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取 ccy
	 * @return ccy.
	 */
	public String getCcy() {
		return ccy;
	}

	/**
	 * 设置 ccy
	 * @param ccy ccy
	 */
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MockCashChangingKey mockCashChangingKey = (MockCashChangingKey) obj;
		
		if(DateUtil.compareDate(mockCashChangingKey.date, date)!=0) {
			return false;
		}
		if (!StringUtil.equals(mockCashChangingKey.type, type)) {
			return false;
		}
		if (!StringUtil.equals(mockCashChangingKey.ccy, ccy)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = 2;
		result = 31*result + (date != null ? date.hashCode() : 0);
		result = 31*result + (type != null ? type.hashCode() : 0);
		result = 31*result + (ccy != null ? ccy.hashCode() : 0);
		return result;
	}

	
}
