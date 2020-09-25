package com.joyintech.tams.disclosure.busicommon.common.calculation.DataSource.CpValueConfig;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.disclosure.busicommon.common.service.DisTargetIndexService;




/**
 * 净值型表三 本报告期产品份额净值收益率及其与同期业绩比较基准收益率的比较 数据源
 * 
 * @author shizheng
 *
 */
public class TbCpValueConfigRepository {

	
	/**
	 * 过去五年产品每年净值增长率及其与同期业绩比较基准收益率的比较（图展示）
	 * @param startDate
	 * @param endDate
	 * @param prodId
	 * @param conn
	 * @return
	 * @throws Exception 
	 */
	public  static List<Map<String,Object>> getTableData(Date startDate, Date endDate, String prodId,Connection conn) throws Exception {
		//定义返回值
		List<Map<String,Object>> list=new ArrayList<>();
		
			
			Calendar enddatestance = Calendar.getInstance();						
			// 过去一年
			enddatestance.setTime(endDate);
			enddatestance.add(Calendar.YEAR, -1);
			Date lastyear = enddatestance.getTime();
			
			Map<String,Object> resultmap3=DisTargetIndexService.printTableCpValueConfig(lastyear, endDate, prodId, conn);
			resultmap3.put("year", "过去一年");
			list.add(resultmap3);
			
			//过去俩年
			enddatestance.setTime(endDate);
			enddatestance.add(Calendar.YEAR, -2);
			Date lastTwoyear = enddatestance.getTime();
			
			Map<String,Object> resultmap2=DisTargetIndexService.printTableCpValueConfig(lastTwoyear, endDate, prodId, conn);
			resultmap2.put("year", "过去二年");
			list.add(resultmap2);
			
			// 过去三年
			enddatestance.setTime(endDate);
			enddatestance.add(Calendar.YEAR, -3);
			Date lasthreeyear = enddatestance.getTime();
			
			Map<String,Object> resultmap4=DisTargetIndexService.printTableCpValueConfig(lasthreeyear, endDate, prodId, conn);
			resultmap4.put("year", "过去三年");
			list.add(resultmap4);
			
			//过去四年
			enddatestance.setTime(endDate);
			enddatestance.add(Calendar.YEAR, -3);
			Date lastFouryear = enddatestance.getTime();
			
			Map<String,Object> resultmapfour=DisTargetIndexService.printTableCpValueConfig(lastFouryear, endDate, prodId, conn);
			resultmapfour.put("year", "过去四年");
			list.add(resultmapfour);
			
			// 过去五年
			enddatestance.setTime(endDate);
			enddatestance.add(Calendar.YEAR, -5);
			Date lasfiveyear = enddatestance.getTime();
			
			Map<String,Object> resultmap5=DisTargetIndexService.printTableCpValueConfig(lasfiveyear, endDate, prodId, conn);
			resultmap5.put("year", "过去五年");
			list.add(resultmap5);
			
		
			
		
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
