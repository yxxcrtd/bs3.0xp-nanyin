/*
 * 文件名：MockCashChangingKey.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2020年4月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.poc.portfolio.ptlmock.dto.mock.trade;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;

/**
 * 费用交易MAP的key
 * 〈功能详细描述〉
 * @author yjm
 * @version 1.0 2020年4月21日
 * @see FeeTradeKey
 * @since 1.0
 */
public class FeeTradeKey {
	
	public FeeTradeKey(String feeType, String ccy) {
		this.feeType = feeType;
		this.ccy = ccy;
	}
	
	 
	
	/**
	 * 费用类型
	 */
	private String feeType;
	
	/**
	 * 币种
	 */
	private String ccy;

	 

	/**
	 * 获取 feeType
	 * @return feeType.
	 */
	public String getFeeType() {
		return feeType;
	}

	/**
	 * 设置 feeType
	 * @param feeType feeType
	 */
	public void setFeeType(String feeType) {
		this.feeType = feeType;
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
		FeeTradeKey mockCashChangingKey = (FeeTradeKey) obj;
		
		if(DateUtil.compareDate(mockCashChangingKey.feeType, feeType)!=0) {
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
		result = 31*result + (feeType != null ? feeType.hashCode() : 0);
		result = 31*result + (ccy != null ? ccy.hashCode() : 0);
		return result;
	}

	
}
