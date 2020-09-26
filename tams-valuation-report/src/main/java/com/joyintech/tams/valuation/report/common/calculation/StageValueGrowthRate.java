package com.joyintech.tams.valuation.report.common.calculation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.report.common.dto.RepProdIndex;

/**
 * 阶段净值增长率计算 共通
 * 
 * @author dongxiangyang
 * @version 1.0
 * @since 2018-01-07
 */
public class StageValueGrowthRate {
	/**
	 * 阶段净值增长率计算
	 * 
	 * @param 开始日期startDate
	 *            结束日期endDate 报告期内产品指标MAP
	 * @retun 阶段净值增长率计算
	 */
	public static BigDecimal getStageValueGrowthRate(Date startDate, Date endDate, Map<Date, RepProdIndex> map) {
		// 定义 阶段净值增长率 = 1
		BigDecimal stageRate = BigDecimal.ONE;

		// 循环 开始日期 - 结束日期
		// 定义日期
		List<Date> listDate = new ArrayList<>();
		if (startDate.compareTo(endDate)==0) {
			listDate.add(startDate);
		} else {
			// 获得开始日和结束日之间的日期集合
			listDate = getDatesBetweenTwoDate(startDate, endDate);
		}
		for (Date da : listDate) {
			// 净值增长率 = 报告期内产品指标MAP.get(循环日期).净值增长率
			BigDecimal growthRate = map.get(da).getNetvalueTdyIncre();
			// 阶段净值增长率 = 阶段净值增长率(1+ (净值增长率))
			stageRate = stageRate.multiply(growthRate.add(BigDecimal.ONE));
		}
		stageRate = stageRate.subtract(BigDecimal.ONE);
		return stageRate;

	}
	public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {
		List<Date> lDate = new ArrayList<Date>();
		lDate.add(beginDate);// 把开始时间加入集合
		Calendar cal = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		cal.setTime(beginDate);
		boolean bContinue = true;
		while (bContinue) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			cal.add(Calendar.DAY_OF_MONTH, 1);
			// 测试此日期是否在指定日期之后
			if (endDate.after(cal.getTime())) {
				lDate.add(cal.getTime());
			} else {
				break;
			}
		}
		lDate.add(endDate);// 把结束时间加入集合
		return lDate;
	}
}
