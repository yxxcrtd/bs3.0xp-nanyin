package com.joyintech.tams.invest.common.riskinfrds.info.entity;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.base.entity.IBusiness;

public interface IAssetIndex extends IBusiness{

	@Override
	default String getKey() {
		return RedisKeyEnum.资产代码索引.key();
	}

	@Override
	default String getField() {
		return getMarketCode() + GlobalConstant.COLON + getTradePalce();
	}
	
	/**
	 * 取得 市场代码
	 * @return 市场代码
	 */
	String getMarketCode();
	
	/**
	 * 取得 交易场所
	 * @return 交易场所
	 */
	String getTradePalce();
	
	/**
	 * 取得 资产代码
	 * @return 资产代码
	 */
	String getAssetCode();
}
