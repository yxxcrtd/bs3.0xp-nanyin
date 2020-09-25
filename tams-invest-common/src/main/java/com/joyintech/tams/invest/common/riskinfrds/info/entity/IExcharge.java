package com.joyintech.tams.invest.common.riskinfrds.info.entity;

import java.math.BigDecimal;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.base.entity.IBusiness;

/**
 * 汇率行情存储结构
 * 
 * @author zy_zhangrui
 *
 */
public interface IExcharge extends IBusiness {

	@Override
	default String getKey() {
		return RedisKeyEnum.汇率行情信息.key();
	}

	@Override
	default String getField() {
		return getBasisCcy() + GlobalConstant.COLON + getQuotCcy();
	}

	/**
	 * 取得 基础币种
	 * 
	 * @return 基础币种
	 */
	String getBasisCcy();

	/**
	 * 取得 报价币种
	 * 
	 * @return 报价币种
	 */
	String getQuotCcy();
	
	/**
	 * 取得 汇率值
	 * 
	 * @return 汇率值
	 */
	BigDecimal getExchargeVal();
}
