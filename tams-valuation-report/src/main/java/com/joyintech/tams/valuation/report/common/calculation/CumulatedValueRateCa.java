package com.joyintech.tams.valuation.report.common.calculation;

import java.math.BigDecimal;
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
public class CumulatedValueRateCa {

//	
//	public static BigDecimal getAccValueRate( BigDecimal growthRate,BigDecimal yGrowthRate) {
////		// 定义 累计净值收益率 = 1
////		BigDecimal accValueRate = BigDecimal.ONE;
////        //截取年
////		int  syear=Integer.parseInt(startDate.substring(0,startDate.indexOf("-")));
////		int  eyear=Integer.parseInt(endDate.substring(0, endDate.indexOf("-")));
////		//截取月
////		int  smonth=Integer.parseInt(startDate.substring(startDate.indexOf("-")+1));
////		int  emonth=Integer.parseInt(endDate.substring(endDate.indexOf("-")+1));
////		//如果同一年
////		if(syear==eyear){
////			//则循环月份
////			for(int i = smonth; i <= emonth; i++){
////				// 万份收益 = 报告期内产品指标MAP.get(循环月份).万份收益
////				BigDecimal porfit = map.get(String.valueOf(syear)+"-"+String.valueOf(i));
////				// 调用净值收益率方法
////				BigDecimal currentValue = NetRightCurrentValue.getRightCurrentValue(porfit);
////				// 累计净值收益率 = 累计净值收益率*(1+ 净值收益率(万份收益))
////				accValueRate = accValueRate.multiply(currentValue.add(BigDecimal.ONE));
////			}
////		}else if(syear<eyear){
////			for(int j=smonth;j<=12;j++){
////				// 万份收益 = 报告期内产品指标MAP.get(循环月份).万份收益
////				BigDecimal porfit = map.get(String.valueOf(syear)+"-"+String.valueOf(j));
////				// 调用净值收益率方法
////				BigDecimal currentValue = NetRightCurrentValue.getRightCurrentValue(porfit);
////				// 累计净值收益率 = 累计净值收益率*(1+ 净值收益率(万份收益))
////				accValueRate = accValueRate.multiply(currentValue.add(BigDecimal.ONE));
////			}
////			for(int m=1;m<=emonth;m++){
////				// 万份收益 = 报告期内产品指标MAP.get(循环月份).万份收益
////				BigDecimal porfit = map.get(String.valueOf(eyear)+"-"+String.valueOf(m));
////				// 调用净值收益率方法
////				BigDecimal currentValue = NetRightCurrentValue.getRightCurrentValue(porfit);
////				// 累计净值收益率 = 累计净值收益率*(1+ 净值收益率(万份收益))
////				accValueRate = accValueRate.multiply(currentValue.add(BigDecimal.ONE));
////				
////			}
////		}
////		
////		accValueRate = accValueRate.setScale(4, BigDecimal.ROUND_HALF_UP);
////		return accValueRate;
//		 
//		
//		//累计净值收益率 =(1+前一日累计净值收益率)*(1+ 当日净值收益率)
//		BigDecimal  result=(BigDecimal.ONE.add(yGrowthRate)).multiply((growthRate.add(BigDecimal.ONE)));
//		
//		result=result.subtract(BigDecimal.ONE);
//		return result;
//		
//	}

}
