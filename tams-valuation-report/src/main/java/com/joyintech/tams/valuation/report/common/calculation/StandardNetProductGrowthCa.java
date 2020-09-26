package com.joyintech.tams.valuation.report.common.calculation;

import java.math.BigDecimal;
import java.util.List;

/**
 * 产品份额净值增长率标准差计算共通
 * 
 * @author dongxiangyang
 * @version 1.0
 * @since 2018-01-02
 */
public class StandardNetProductGrowthCa {

	/**
	 * 产品份额净值增长率标准差计算
	 * 
	 * @param 区间净值增长率List
	 * @retun 产品份额净值增长率标准差
	 */
	public static BigDecimal getStandardNetProductGrowth(List<BigDecimal> list) {
		if (list == null || list.size() == 0) {
			return BigDecimal.ZERO;
		}
		BigDecimal result=BigDecimal.ZERO;
		// 求得增长率总和
		BigDecimal sum = BigDecimal.ZERO;
		for (BigDecimal pl : list) {
			sum = sum.add(pl);
		}
		// 求得增长率平均值
		int i = list.size();
		BigDecimal avGrowth = sum.divide(new BigDecimal(i), 20, BigDecimal.ROUND_DOWN);
		// STDEV公式计算标准差
		BigDecimal denominator = BigDecimal.ZERO;
		BigDecimal molecule = BigDecimal.ZERO;
		// 计算分子
		for (BigDecimal li : list) {
			molecule = molecule.add(li.subtract(avGrowth).multiply(li.subtract(avGrowth)));
		}
		// 计算分母
		denominator = new BigDecimal(list.size() - 1);
        if(denominator.equals(BigDecimal.ZERO)||denominator==BigDecimal.ZERO){
        	return result=BigDecimal.ZERO;
        }
		// 利用公式计算
		Double sq = molecule.divide(denominator,20,BigDecimal.ROUND_HALF_UP).doubleValue();
		// 开根号
		 result = BigDecimal.valueOf(Math.sqrt(sq));
		 result= result.setScale(20, BigDecimal.ROUND_HALF_UP);
		return result;
	}

}
