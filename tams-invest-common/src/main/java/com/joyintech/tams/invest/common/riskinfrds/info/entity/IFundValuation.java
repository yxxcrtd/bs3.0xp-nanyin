package com.joyintech.tams.invest.common.riskinfrds.info.entity;

import java.math.BigDecimal;

import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.base.entity.IBusiness;

/**
 * 基金估值信息存储结构
 * 
 * @author zy_zhangrui
 *
 */
public interface IFundValuation extends IBusiness {

	@Override
	default String getKey() {
		return RedisKeyEnum.基金估值行情信息.key();
	}

	@Override
	default String getField() {
		return getAssetCode();
	}

	/**
	 * 取得 资产代码
	 * 
	 * @return 资产代码
	 */
	String getAssetCode();

	/**
	 * 取得 估值日期
	 * 
	 * @return 估值日期
	 */
	String getVaDate();

	/**
	 * 取得 估值
	 * 
	 * @return 估值
	 */
	BigDecimal getValuation();
}
