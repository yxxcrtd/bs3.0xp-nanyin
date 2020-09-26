package com.joyintech.tams.valuation.report.common.calculation;

import java.math.BigDecimal;

/**
 * 产品份额净值增长率计算共通
 * @author dongxiangyang
 * @version 1.0
 * @since 2018-12-28
 */
public class ProductShareGrowthRateCa {
 
	/**
	 * 产品份额净值增长率计算
	 * @param 当日净值nowth  当日分红净值nBonuwth 前一日净值 yowth
	 * @retun 产品份额净值增长率计算
	 */
	
	public static BigDecimal getProShareWthRate(BigDecimal nowth,BigDecimal nBonuwth,BigDecimal yowth){
		
		//产品份额净值增长率计算= 当日净值/ (前一日净值–当日分红净值) -1
		BigDecimal  proShareWthRate=(nowth.divide(yowth.subtract(nBonuwth),20,BigDecimal.ROUND_HALF_UP)).subtract(BigDecimal.ONE);
		return proShareWthRate;
	}
}
