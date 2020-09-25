package com.joyintech.tams.disclosure.busicommon.common.calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;
 
/**
 * 产品份额累计净值增长率共通
 * @author dongxiangyang
 * @version 1.0
 * @since 2018-12-28
 */
public class AccumProductShareValue {

	
	/**
	 * 产品份额累计净值增长率
	 * @param 当日净值增长率 growthRate  前一日累计净值增长率yGrowthRate
	 * @retun 产品份额累计净值增长率
	 */
	public static BigDecimal getProductShareValue(BigDecimal growthRate,BigDecimal yGrowthRate){
		
		//产品份额累计净值增长率 = （当日净值增长率+1）*（前一日累计净值增长率+1）-1
		
		BigDecimal productShareValue=growthRate.add(BigDecimal.ONE).multiply(yGrowthRate.add(BigDecimal.ONE)).subtract(BigDecimal.ONE);
		return productShareValue;
	}
}
