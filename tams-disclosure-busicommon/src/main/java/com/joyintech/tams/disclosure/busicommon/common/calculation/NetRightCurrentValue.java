package com.joyintech.tams.disclosure.busicommon.common.calculation;

import java.math.BigDecimal;
import java.util.List;

/**
 * 本期净值收益率计算 共通
 * @author dongxiangyang
 * @version 1.0
 * @since 2018-12-28
 */
public class NetRightCurrentValue {
	
	/**
	 * 本期净值收益率计算方法
	 * @param 万份收益 profit   
	 * @retun 本期净值收益率
	 */	
	public static BigDecimal  getRightCurrentValue(BigDecimal profit){
		
		//定义:本期净值收益率 返回值本期净值收益率 = 万份收益/10000
		BigDecimal resultValue=profit.divide(new BigDecimal(10000));
		return resultValue;
	}
}
