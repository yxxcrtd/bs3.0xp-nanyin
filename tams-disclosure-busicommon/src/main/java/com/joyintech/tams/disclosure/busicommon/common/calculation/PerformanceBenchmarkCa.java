package com.joyintech.tams.disclosure.busicommon.common.calculation;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 阶段业绩比较基准计算 共通
 * @author dongxiangyang
 * @version 1.0
 * @since 2018-12-28
 */
public class PerformanceBenchmarkCa {


    /**
     * 阶段业绩比较基准List
     * @param startDate
     * @param endDate
     * @param prodId
     * @param conn 
     * @throws SQLException 
     * @returnMap<BigDecimal, List<BigDecimal>>
     */
	
	public static  Map<String,Object>   getPhasePerformance(Date startDate,Date endDate,String prodId, Connection conn) {
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				if(ps!=null){
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(rs!=null){
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
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
		Map<String,Object> resultMap = new HashMap<>();
		//定义：期末业绩比较基准 = 0；
		BigDecimal  qmCompareValue=BigDecimal.ZERO;
		BigDecimal stageRate = BigDecimal.ONE;
		//定义：业绩比较基准LIST ；
		List<BigDecimal> list=new ArrayList<>();
		//定义：上一工作日；
		Date  ldate=null;
		//定义：工作日list
		List<Date> listMap=new ArrayList<Date>();
		//查询工作日
		listMap=getIsHoliday(conn, startDate, endDate, prodId);
		// 循环 开始日期 - 结束日期
		// 定义日期
		List<Date> listDate = new ArrayList<>();
		if (startDate.compareTo(endDate)==0) {
		listDate.add(startDate);
	    } else {
		// 获得开始日和结束日之间的日期集合
		listDate = getDatesBetweenTwoDate(startDate, endDate);
	    }
		SimpleDateFormat   format=new SimpleDateFormat("MM-dd");
		for (Date da : listDate){
			//工作日MAP.包含（查询日）或者  工作日=季末
			if(listMap.contains(da)
					||format.format(da).equals("03-31")
                    ||format.format(da).equals("06-30")
                    ||format.format(da).equals("09-30")
                    ||format.format(da).equals("12-31")
                    ){
				//如果上一工作日为空
				if(ldate==null){
				//查询上一工作日
					ldate=getMaxHoliday(conn, prodId, da);
				}
				//调用查询业绩比较基准
			    BigDecimal stmance=getClaCompareValue(prodId, da, ldate, conn);
			    //阶段业绩比较基准调用公式
			    BigDecimal growthRate =stmance;
			    if(growthRate!=null)
				{
					stageRate = stageRate.multiply(growthRate.add(BigDecimal.ONE));
				}

			    list.add(stmance);
			    //上一工作日 = 查询日
			    ldate=da;

			}
		}
	   stageRate = stageRate.subtract(BigDecimal.ONE);
	   resultMap.put("list", list);
	   resultMap.put("claCompareRule", stageRate);
	   
		
//		//调用阶段业绩比较基准List查询
//		Map<String,Object> resultMap=getPhasePerformance(startDate, endDate, prodId, conn);
//		//获取参数map
//		Map<Date,BigDecimal> map=(Map<Date, BigDecimal>) resultMap.get("map");
//		// 定义 阶段净值增长率 = 1
//		BigDecimal stageRate = BigDecimal.ONE;
//
//		// 循环 开始日期 - 结束日期
//		// 定义日期
//		List<Date> listDate = new ArrayList<>();
//		if (startDate.compareTo(endDate)==0) {
//			listDate.add(startDate);
//		} else {
//			// 获得开始日和结束日之间的日期集合
//			listDate = getDatesBetweenTwoDate(startDate, endDate);
//		}
//		for (Date da : listDate) {
//			// 净值增长率 = 报告期内产品指标MAP.get(循环日期).净值增长率
//			BigDecimal growthRate = map.get(da);
//			// 阶段净值增长率 = 阶段净值增长率(1+ (净值增长率))
//			stageRate = stageRate.multiply(growthRate.add(BigDecimal.ONE));
//		}
//		//阶段业绩收益率
//		stageRate = stageRate.subtract(BigDecimal.ONE);
//		
//		resultMap.put("claCompareRule", stageRate);
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
	
	/**
	 * 业绩比较基准的查询
	 * @param prodId
	 * @param cDate
	 * @param ldate
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static BigDecimal   getClaCompareValue(String prodId,Date cDate,Date  ldate,Connection conn) {
		//原生JDBC连接查询
		//第一步获取连接对象
		//编写sql
		String   sql="SELECT (NVL(MI.INT_RATE, 0) * A.COEFFICIENT + A.SPREAD_RATE)*(to_date(?,'yyyy-MM-dd')-to_date(?,'yyyy-MM-dd'))/365 AS stMance FROM FIN_BENCHMARK_DETAIL A LEFT JOIN MST_INT_RATE_INFO MI ON A.BASIC_RATE = MI.INT_RATE_ID WHERE   A.FINPROD_ID =? AND A.EFF_DATE =  (SELECT MAX(EFF_DATE)  FROM FIN_BENCHMARK_DETAIL  WHERE FINPROD_ID = ? AND EFF_DATE <= ?) AND MI.EFF_DATE =  (SELECT MAX(MS.EFF_DATE)  FROM MST_INT_RATE_INFO MS  WHERE MS.EFF_DATE <= ?  AND MS.INT_RATE_ID = A.BASIC_RATE)";
        //创建执行语句
		PreparedStatement ps= null;
		ResultSet rs = null;
		BigDecimal stMance = null;
		try {
			ps = conn.prepareStatement(sql);
			//传参
			SimpleDateFormat  format=new SimpleDateFormat("yyyy-MM-dd");
			ps.setString(1, format.format(cDate));
			ps.setString(2, format.format(ldate));
			ps.setString(3, prodId);
			ps.setString(4, prodId );
			ps.setDate(5, new java.sql.Date(cDate.getTime()));
			ps.setDate(6, new java.sql.Date(cDate.getTime()));
			//执行语句
			 rs = ps.executeQuery();
			//获取业绩比较基准
			stMance=BigDecimal.ZERO;
			if(rs.next()){
				stMance =rs.getBigDecimal("stMance");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}


		return stMance;
	}
	
	/**
	 * 获取工作日List
	 * @param conn
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws Exception 
	 */
	public static  List<Date>  getIsHoliday(Connection conn,Date startDate,Date endDate,String prodId) {
		List<Date>  listDate=new ArrayList<Date>();
		//原生JDBC连接查询
		//第一步获取连接对象
		//编写sql
		String   sql="select CDATE from MST_HOLIDAY A inner join FIN_PRODUCT B  ON A.CALENDAR_ID=B.CALENDAR_ID AND  B.FINPROD_ID=? AND A.CDATE>=? AND A.CDATE<=? AND  A.IS_HOLIDAY='0'";
        //创建执行语句
		PreparedStatement ps= null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			//传参
			ps.setString(1,prodId);
			ps.setDate(2, new java.sql.Date(startDate.getTime()));
			ps.setDate(3, new java.sql.Date(endDate.getTime()));
			//执行语句
			rs = ps.executeQuery();
			while(rs.next()){
				Date caDate=rs.getDate("CDATE");
				listDate.add(caDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return listDate;	
	}
	/**
	 * 查询上一工作日
	 * @param conn
	 * @param prodId
	 * @param endDate
	 * @return
	 * @throws Exception
	 */
	public  static Date getMaxHoliday(Connection conn,String prodId,Date endDate) {
		//定义返回值
		Date maxDate=new Date();
		//原生JDBC连接查询
		// 第一步获取连接对象
		// 编写sql
		String sql = "select max(VALUE_DATE) as VALUE_DATE from rep_prod_index where PROD_ID=? and VALUE_DATE<?";
		 //创建执行语句
		PreparedStatement ps= null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			//传参
			ps.setString(1,prodId);
			ps.setDate(2, new java.sql.Date(endDate.getTime()));

			//执行语句
			rs = ps.executeQuery();

			if (rs.next()) {
				maxDate = rs.getDate("VALUE_DATE");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return maxDate;
		
	}
}
