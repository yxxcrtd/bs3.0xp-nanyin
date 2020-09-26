package com.joyintech.tams.valuation.report.common.calculation;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.report.common.dto.RepProdIndex;
import com.joyintech.tams.valuation.report.common.util.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 阶段业绩比较基准计算 共通
 * @author dongxiangyang
 * @version 1.0
 * @since 2018-12-28
 */
public class PerformanceBenchmarkCa {

	private static Logger logger = LoggerFactory.getLogger(PerformanceBenchmarkCa.class);

    /**
     * 阶段业绩比较基准List
     * @param startDate
     * @param endDate
     * @param prodId
     * @param conn 
     * @throws SQLException 
     * @returnMap<BigDecimal, List<BigDecimal>>
     */
	
	public static  Map<String,Object>   getPhasePerformance(Date startDate,Date endDate,String prodId, Connection conn)
		throws SQLException {
		//定义返回变量
		Map<String,Object> resultMap=new HashMap<>();
		Map<Date,BigDecimal> rmap=new HashMap<>();
		//定义阶段业绩比较基准List
		List<BigDecimal> list=new ArrayList<>();
		//循环 开始日期 - 结束日期
		// 定义日期
		List<Date> listDate = new ArrayList<>();
		if (startDate.compareTo(endDate)==0) {
			listDate.add(startDate);
		} else {
			// 获得开始日和结束日之间的日期集合
			listDate =StageValueGrowthRate.getDatesBetweenTwoDate(startDate, endDate);
		}
		
		//
		for(Date da:listDate){
			//调用共通方法：产品份额累计净值增长率计算
			//原生JDBC连接查询
			//第一步获取连接对象
			//编写sql
			String   sql="SELECT (NVL(MI.INT_RATE, 0) * A.COEFFICIENT + A.SPREAD_RATE)/360 AS stMance FROM FIN_BENCHMARK_DETAIL A LEFT JOIN MST_INT_RATE_INFO MI ON A.BASIC_RATE = MI.INT_RATE_ID WHERE   A.FINPROD_ID =? AND A.EFF_DATE =  (SELECT MAX(EFF_DATE)  FROM FIN_BENCHMARK_DETAIL  WHERE FINPROD_ID = ? AND EFF_DATE <= ?) AND MI.EFF_DATE =  (SELECT MAX(MS.EFF_DATE)  FROM MST_INT_RATE_INFO MS  WHERE MS.EFF_DATE <= ?  AND MS.INT_RATE_ID = A.BASIC_RATE)";
            //创建执行语句
			PreparedStatement ps= null;
			ResultSet rs = null;
			try {
				ps = conn.prepareStatement(sql);
				//传参
				ps.setString(1, prodId);
				ps.setString(2, prodId);
				ps.setDate(3, new java.sql.Date(da.getTime()));
				ps.setDate(4, new java.sql.Date(da.getTime()));
				//执行语句
				rs = ps.executeQuery();
				//获取业绩比较基准
				BigDecimal stMance=BigDecimal.ZERO;
				if(rs.next()){
					stMance =rs.getBigDecimal("stMance");
					//放入业绩比较基准list中
					list.add(stMance);
				}
				rmap.put(da, stMance);
			}
			catch (SQLException e) {
				throw e;
			}
			finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (Exception e) {
						logger.error("", e);
					}
				}
				if (ps != null) {
					try {
						ps.close();
					} catch (Exception e) {
						logger.error("", e);
					}
				}
			}


		}
		//如果阶段业绩比较基准为空情况
		if(list==null||list.size()==0){
			list.add(BigDecimal.ZERO);
		}	
		resultMap.put("map", rmap);
		resultMap.put("list", list);
		return resultMap;
	}
	
	  /**
     * 阶段业绩比较基准
     * @param startDate
     * @param endDate
     * @param prodId
     * @param conn 
     * @throws SQLException 
     * @returnMap<BigDecimal, List<BigDecimal>>
     */
	public static Map<String,Object> getClaCompareRule(Date startDate, Date endDate,String prodId, Connection conn) throws Exception {
		//调用阶段业绩比较基准List查询
		Map<String,Object> resultMap=getPhasePerformance(startDate, endDate, prodId, conn);
		//获取参数map
		Map<Date,BigDecimal> map=(Map<Date, BigDecimal>) resultMap.get("map");
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
			BigDecimal growthRate = map.get(da);
			// 阶段净值增长率 = 阶段净值增长率(1+ (净值增长率))
			stageRate = stageRate.multiply(growthRate.add(BigDecimal.ONE));
		}
		//阶段业绩收益率
		stageRate = stageRate.subtract(BigDecimal.ONE);
		
		resultMap.put("claCompareRule", stageRate);
		return resultMap;

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
