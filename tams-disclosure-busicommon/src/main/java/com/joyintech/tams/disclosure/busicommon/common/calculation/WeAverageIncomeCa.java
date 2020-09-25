package com.joyintech.tams.disclosure.busicommon.common.calculation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.disclosure.busicommon.common.dto.PowerDto;
import com.joyintech.tams.disclosure.busicommon.common.dto.RepProdIndex;


/**
 * 加权平均单位本期净收益计算共通
 * 
 * @author dongxiangyang
 * @version 1.0
 * @since 2018-01-02
 */
public class WeAverageIncomeCa {
	/**
	 * 加权平均单位本期净收益计算方法
	 * 
	 * @param 发行方式issueType
	 *            本期产品净利润pfitn 开始日期startDate 结束日期endDate 日历节假日dmap 报告期内产品指标map
	 * @retun 利润率
	 */
	public static BigDecimal getWeAverageCurrentValue(BigDecimal pfitn, Date startDate, Date endDate,
			Map<Date, String> dmap, Map<Date, RepProdIndex> map) {
		// 获取开始日的前一日
		Date beforeDate =WaverageProfitCa.getBeforeDate(startDate);
		// 定义返回结果
		BigDecimal result = BigDecimal.ZERO;
		//计算加权处理结果
		List<PowerDto> listPower=getDatesBetweenTwoDate(startDate, endDate, dmap, map);
		if (listPower != null && listPower.size() != 0 ) {
			// 调用 通用加权计算
			BigDecimal addPowerResult = AddPowerCa.addPoweCommen(listPower);
			if (BigDecimal.ZERO.compareTo(addPowerResult) == 0) {
				return result;
			}
			// 返回值.加权平均产品份额本期利润 = 本期基金净利润 / 加权结果
			result = pfitn.divide(addPowerResult, 4, BigDecimal.ROUND_HALF_UP);
		} else {
			result = pfitn.divide(map.get(beforeDate).getAssetValueCost(), 4, BigDecimal.ROUND_HALF_UP);
		}

		return result;
	}
	
	public static  List<PowerDto>   getDatesBetweenTwoDate(Date beginDate, Date endDate, Map<Date, String> dmap,Map<Date, RepProdIndex> map) {
		// 定义 List<权> 并初始化
		List<PowerDto> listPower = new ArrayList<>();
		List<Date> lDate = new ArrayList<Date>();
		lDate.add(beginDate);// 把开始时间加入集合
		Calendar cal = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		cal.setTime(beginDate);
		boolean bContinue = true;
		while (bContinue){
//			if (dmap.get(cal.getTime()).equals("0")) {
				// 定义权
			PowerDto powerDto = new PowerDto();
			// 权.权值 = 报告期内基金份额MAP.get(循环时间).实收资本
			powerDto.setPowerValue(map.get(cal.getTime()).getAssetValueCost());
			// 权.权重 = 1
			powerDto.setPowerWeight(BigDecimal.ONE);
			listPower.add(powerDto);
//			}
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			cal.add(Calendar.DAY_OF_MONTH, 1);
			// 测试此日期是否在指定日期之后
			if (endDate.before(cal.getTime())) {
				break;
			}
			
		}
//		while (bContinue) {
//			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
//			cal.add(Calendar.DAY_OF_MONTH, 1);
//			// 测试此日期是否在指定日期之后
//			if (endDate.after(cal.getTime())) {
//				// 是否工作日
//				if (dmap.get(cal.getTime()) == 0) {
//					// 定义权
//					PowerDto powerDto = new PowerDto();
//					// 权.权值 = 报告期内基金份额MAP.get(循环时间).实收资本
//					powerDto.setPowerValue(map.get(cal.getTime()).getAssetValueCost());
//					// 权.权重 = 1
//					powerDto.setPowerWeight(BigDecimal.ONE);
//					listPower.add(powerDto);
//				}
//			} else {
//				break;
//			}
//		}
		return listPower;
		
	}
	
}
