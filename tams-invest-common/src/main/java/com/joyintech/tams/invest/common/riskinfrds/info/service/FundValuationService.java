package com.joyintech.tams.invest.common.riskinfrds.info.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.base.service.RedisBaseService;
import com.joyintech.tams.invest.common.riskinfrds.info.entity.IFundValuation;

/**
 * 基金估值行情
 * 
 * @author zy_zhangrui
 *
 */
@Service
public class FundValuationService extends RedisBaseService<IFundValuation> {

	/**
	 * 取得 基金估值
	 *  
	 * @param assetCode 基金代码
	 * @return 基金估值
	 */
	public IFundValuation getFundValuationInfo(String assetCode){
		return this.getInfoFromRedis(RedisKeyEnum.基金估值行情信息.key(), assetCode);
	}

	/**
	 * 取得 基金估值
	 * 
	 * @param assetCode 基金代码
	 * @return 基金估值
	 */
	public BigDecimal getFundValuation(String assetCode){
		IFundValuation info = getFundValuationInfo(assetCode);
		if (info != null) {
			return info.getValuation();
		}
		return null;
	}
}
