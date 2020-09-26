package com.joyintech.tams.valuation.report.common.calculation;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.report.common.dto.RepProdIndex;

/**
 * 累计净值收益率计算共通
 * 
 * @author dongxiangyang
 * @version 1.0
 * @since 2018-12-28
 */
public class AccumulatedValueRateCa {

	/**
	 * 累计净值收益率计算方法
	 * 
	 * @param 开始月份startMonth
	 *            结束月份endMonth 报告期内产品指标MAP
	 * @retun 累计净值收益率
	 */
	public static BigDecimal getAccValueRate(String startDate ,String endDate, Map<String, BigDecimal> map) {
		// 定义 累计净值收益率 = 1
		BigDecimal accValueRate = BigDecimal.ONE;

		//获取间隔月份
		Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        try {
			bef.setTime(sdf.parse(startDate));
	        aft.setTime(sdf.parse(endDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        Calendar curr = bef;

        //循环开始月-结束月计算累计净值收益率
        //开始月计算
    	// 万份收益 = 报告期内产品指标MAP.get(循环月份).万份收益
		BigDecimal porfit = map.get(sdf.format(bef.getTime()));
		// 调用净值收益率方法
		BigDecimal currentValue = NetRightCurrentValue.getRightCurrentValue(porfit);
		// 累计净值收益率 = 累计净值收益率*(1+ 净值收益率(万份收益))
		accValueRate = accValueRate.multiply(currentValue.add(BigDecimal.ONE));
		
        String month = "";
        //开始月-结束月中间月份
        while (curr.before(aft)){
        	month = sdf.format(curr.getTime());
			porfit = map.get(month);
			currentValue = NetRightCurrentValue.getRightCurrentValue(porfit);
			accValueRate = accValueRate.multiply(currentValue.add(BigDecimal.ONE));
        	curr.add(Calendar.MONTH, 1);
		};
		//结束月计算
		porfit = map.get(sdf.format(aft.getTime()));
		currentValue = NetRightCurrentValue.getRightCurrentValue(porfit);
		accValueRate = accValueRate.multiply(currentValue.add(BigDecimal.ONE));	
      
		
		return accValueRate;
	}
	
	/**
	 * 批调用累积收益率计算方法
	 * @param growthRate
	 * @param yGrowthRate
	 * @return
	 */
	public static BigDecimal getAccValueRate( BigDecimal growthRate,BigDecimal yGrowthRate) {
		
		//累计净值收益率 =(1+前一日累计净值收益率)*(1+ 当日净值收益率)
		BigDecimal  result=(BigDecimal.ONE.add(yGrowthRate)).multiply((growthRate.add(BigDecimal.ONE)));
		
		result=result.subtract(BigDecimal.ONE);
		return result;
		
	}
	/**
	 * 累计净值收益率计算方法
	 * 
	 * @param 开始日期
	 *           结束日期  报告期内产品指标MAP
	 * @retun 累计净值收益率
	 */
	public static BigDecimal getAccValueRateByDay(Date  startDate,Date endDate,Map<Date, RepProdIndex> map){
		// 定义 累计净值收益率 = 1
		BigDecimal accValueRate = BigDecimal.ONE;
		//定义万份收益
		BigDecimal	porfit=BigDecimal.ZERO;
		//循环开始-结束日计算累计净值收益率
		Calendar std = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        std.setTime(startDate);
        end.setTime(endDate);
    	boolean bContinue = true;
    	//std.getTime().before(endDate)
		while(bContinue){
			// 万份收益 = 报告期内产品指标MAP.get(循环日期).万份收益
			porfit=map.get(std.getTime()).getwYield();
			//累计净值收益率 = 累计净值收益率*(1+ 净值收益率(万份收益))
			accValueRate=accValueRate.multiply(BigDecimal.ONE.add(NetRightCurrentValue.getRightCurrentValue(porfit)));
			std.add(Calendar.DAY_OF_MONTH, 1);
			if(endDate.before(std.getTime())){
				break;
			}
		}
		accValueRate=accValueRate.subtract(BigDecimal.ONE);
		return accValueRate;
	}
	
	
}
