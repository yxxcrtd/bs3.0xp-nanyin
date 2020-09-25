package com.joyintech.tams.disclosure.busicommon.common.calculation.DataSource.MProdTbThree;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.disclosure.busicommon.common.dto.FinProduct;
import com.joyintech.tams.disclosure.busicommon.common.service.DisTargetIndexService;


/**
 * 货币型表三 本报告期产品份额净值收益率及其与同期业绩比较基准收益率的比较 数据源
 * 
 * @author shizheng
 *
 */
public class TbThreeCurrencyDataRepository {

	
	/**
	 * 输出货币型表三的值
	 * @param startDate
	 * @param endDate
	 * @param prodId
	 * @param conn
	 * @return
	 * @throws Exception 
	 */
	public static List<Map<String,Object>> getCurrencyTypeTbThreeDate(Date startDate, Date endDate, String prodId,Connection conn) throws Exception {
		//定义返回值
		List<Map<String,Object>> list=new ArrayList<>();
		DisTargetIndexService service=new DisTargetIndexService();
		//获取金融产品标的信息
		FinProduct finProduct=DisTargetIndexService.getFinProductByJdbc(prodId, conn);
		int month = getMonth(startDate, endDate);
		// 季报
		if (month <= 3) {
			Map<String,Object> resultmapOne=service.printTargetCurrencyTypeTbThree(startDate, endDate, prodId, conn);
			resultmapOne.put("report", "过去三个月");
			list.add(resultmapOne);

		} else if (month > 3 && month <= 6) {
			// 半年报

			// 过去一个月
			Calendar enddatestance = Calendar.getInstance();
			enddatestance.setTime(endDate);
			enddatestance.add(Calendar.MONTH, -1);
			Date lastmonthdate = enddatestance.getTime();
			Map<String,Object> resultmapTwo=service.printTargetCurrencyTypeTbThree(lastmonthdate, endDate, prodId, conn);
			resultmapTwo.put("report", "过去一个月");
			list.add(resultmapTwo);


			// 过去三个月
			enddatestance.setTime(endDate);
			enddatestance.add(Calendar.MONTH, -3);
			Date lastthreemonth = enddatestance.getTime();
			if (lastthreemonth.before(startDate)) {
				lastthreemonth = startDate;
			}
			Map<String,Object> resultmapThree=service.printTargetCurrencyTypeTbThree(lastthreemonth, endDate, prodId, conn);
			resultmapThree.put("report", "过去三个月");
			list.add(resultmapThree);

			
			// 过去六个月
            Map<String,Object> resultmapFour=service.printTargetCurrencyTypeTbThree(startDate, endDate, prodId, conn);
            resultmapFour.put("report", "过去六个月");
            list.add(resultmapFour);

			// 自产品合同生效起至今(产品起息日-到查询结束日)
            Map<String,Object> resultmapFive=service.printTargetCurrencyTypeTbThree(finProduct.getVdate(), endDate, prodId, conn);
            resultmapFive.put("report", "产品合同生效起至今");
            list.add(resultmapFive);

		} else if (month > 6) {
			// 年报
			// 过去三个月
			Calendar enddatestance = Calendar.getInstance();
			enddatestance.setTime(endDate);
			enddatestance.add(Calendar.MONTH, -3);
			Date lastthreemonth = enddatestance.getTime();
			Map<String,Object> resultmap2=service.printTargetCurrencyTypeTbThree(lastthreemonth, endDate, prodId, conn);
			resultmap2.put("report", "过去三个月");
			list.add(resultmap2);
			//			ResultTwoDto dto2 = service.printTargetCurrencyTypeTbThree(lastthreemonth, endDate, prodId);
//			list.add(dto2);
			//过去6个月
			enddatestance.setTime(endDate);
			enddatestance.add(Calendar.MONTH, -6);
			Date lastmonthdate = enddatestance.getTime();
			Map<String,Object> resultmap1=service.printTargetCurrencyTypeTbThree(lastmonthdate, endDate, prodId, conn);
			resultmap1.put("report", "过去六个月");
			list.add(resultmap1);
			
			
			// 过去一年
			enddatestance.setTime(endDate);
			enddatestance.add(Calendar.YEAR, -1);
			Date lastyear = enddatestance.getTime();
			if (lastyear.before(startDate)) {
				lastyear = startDate;
			}
			Map<String,Object> resultmap3=service.printTargetCurrencyTypeTbThree(startDate, lastyear, prodId, conn);
			resultmap3.put("report", "过去一年");
			list.add(resultmap3);

			// 过去三年
			enddatestance.setTime(endDate);
			enddatestance.add(Calendar.YEAR, -3);
			Date lasthreeyear = enddatestance.getTime();
			if (lasthreeyear.before(startDate)) {
				lasthreeyear = startDate;
			}
			Map<String,Object> resultmap4=service.printTargetCurrencyTypeTbThree(lasthreeyear, endDate, prodId, conn);
			resultmap4.put("report", "过去三年");
			list.add(resultmap4);

			// 过去五年
			enddatestance.setTime(endDate);
			enddatestance.add(Calendar.YEAR, -5);
			Date lasfiveyear = enddatestance.getTime();
			if (lasfiveyear.before(startDate)) {
				lasfiveyear = startDate;
			}
			Map<String,Object> resultmap5=service.printTargetCurrencyTypeTbThree(lasfiveyear, endDate, prodId, conn);
			resultmap5.put("report", "过去五年");
			list.add(resultmap5);
			
			// 自产品合同生效起至今(产品起息日-到查询结束日)
			Map<String,Object> resultmap6=service.printTargetCurrencyTypeTbThree(finProduct.getVdate(), endDate, prodId, conn);
			resultmap6.put("report", "产品合同生效起至今");
			list.add(resultmap6);
		}
		return list;

	}

	// 获取一个日期段横跨月数
	public static int getMonth(Date start, Date end) {
		if (start.after(end)) {
			Date t = start;
			start = end;
			end = t;
		}
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(start);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);
		Calendar temp = Calendar.getInstance();
		temp.setTime(end);
		temp.add(Calendar.DATE, 1);

		int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
		if (year > 0) {
			return year * 12 + month + 1;
		} else {
			return month + 1;
		}

	}

}
