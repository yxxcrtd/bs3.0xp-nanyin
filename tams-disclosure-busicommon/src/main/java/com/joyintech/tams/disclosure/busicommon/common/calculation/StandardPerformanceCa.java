package com.joyintech.tams.disclosure.busicommon.common.calculation;

import java.math.BigDecimal;
import java.util.List;

/**
 * 业绩比较基准收益率标准差
 * 
 * @author dongxiangyang
 * @version 1.0
 * @since 2018-01-02
 */
public class StandardPerformanceCa {
	/**
	 * 业绩比较基准收益率标准差计算
	 *
	 * @param List 区间业绩比较基准收益率
	 *
	 * @retun 业绩比较基准收益率标准差
	 */
	public  static BigDecimal getStandardPerformance(List<BigDecimal> list) {
		BigDecimal result=BigDecimal.ZERO;
		// 求得增长率总和
		BigDecimal sum = BigDecimal.ZERO;
		for (BigDecimal pl : list) {
			sum = sum.add(pl);
		}
		if(list.size()!=0){
			// 求得增长率平均值
			BigDecimal avGrowth = sum.divide(new BigDecimal(list.size()), 4, BigDecimal.ROUND_HALF_UP);
			// STDEV公式计算标准差
			BigDecimal denominator = BigDecimal.ZERO;
			BigDecimal molecule = BigDecimal.ZERO;
			// 计算分子
			for (BigDecimal li : list) {
				molecule = molecule.add(li.subtract(avGrowth).multiply(li.subtract(avGrowth)));
			}
			// 计算分母
			denominator = new BigDecimal(list.size() - 1);
	        if(denominator==BigDecimal.ZERO||denominator.equals(BigDecimal.ZERO)){
	         	result=BigDecimal.ZERO;
	         	return result;
	        }
			// 利用公式计算
			Double sq = molecule.divide(denominator,20,BigDecimal.ROUND_HALF_UP).doubleValue();
			// 开根号
			 result = BigDecimal.valueOf(Math.sqrt(sq));

			result = result.setScale(4, BigDecimal.ROUND_HALF_UP);
		}
		
		return result;
	}
	
}
