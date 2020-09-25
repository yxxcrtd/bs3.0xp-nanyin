package com.joyintech.tams.disclosure.busicommon.common.calculation;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.disclosure.busicommon.common.dto.RepProdIndex;



/**
 * 阶段业绩比较基准增长率计算 共通
 * 
 * @author dongxiangyang
 * @version 1.0
 * @since 2018-01-07
 */
public class FloatValueGrowthRate {
	/**
	 * 阶段业绩比较基准增长率计算
	 * 
	 * @param
	 *
	 * @throws Exception 
	 * @retun 阶段业绩比较基准增长率计算
	 */
	public static BigDecimal getStageValueGrowthRate(Date startDate, Date endDate, Map<Date, RepProdIndex> map,String prodId,Connection conn) throws Exception {
		// 定义 阶段业绩比较基准= 1
		BigDecimal stageRate = BigDecimal.ONE;

		// 循环 开始日期 - 结束日期
		// 定义日期
		List<Date> listDate = getTaskDate(startDate,endDate,prodId,conn);
//		if (startDate.compareTo(endDate)==0) {
//			listDate.add(startDate);
//		} else {
//			// 获得开始日和结束日之间的日期集合
//			listDate = getDatesBetweenTwoDate(startDate, endDate);
//		}
		for (Date da : listDate) {
			if(map.get(da).getFloatBaseRate()!=null){
				// 业绩比较基准 = 报告期内产品指标MAP.get(循环日期).业绩比较基准
				BigDecimal growthRate = map.get(da).getFloatBaseRate();
				// 阶段业绩比较基准 = 阶段业绩比较基准(1+ (业绩比较基准))
				stageRate = stageRate.multiply(growthRate.add(BigDecimal.ONE));
			}
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
	/**
	 * 获得开始日到结束日的产品跑批日期
	 * 
	 */
	public static List<Date>  getTaskDate(Date startDate, Date endDate,String prodId,Connection conn)
	      {
		
		List<Date>   list=new ArrayList<Date>();
		//编写sql
		String   sql="select value_date from rep_prod_index where prod_id=?  and value_date  BETWEEN  ?  and ? order by value_date asc";
		
		  //创建执行语句
			  PreparedStatement ps= null;
			  ResultSet rs = null;
			  try {
				  ps = conn.prepareStatement(sql);
				  //传参
				  ps.setString(1, prodId);
				  ps.setDate(2, new java.sql.Date(startDate.getTime()));
				  ps.setDate(3, new java.sql.Date(endDate.getTime()));
				  //执行语句
				  rs = ps.executeQuery();
				  //获取结果集
				  while(rs.next()){
					  Date vALUE_DATE=rs.getDate("VALUE_DATE");
					  list.add(vALUE_DATE);
				  }
			  } catch (SQLException e) {
				  e.printStackTrace();
			  }
			  finally {
				  //释放连接
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



		return list;
	}
	
//	public static void main(String[] args) throws Exception {
//		
//		Connection  conn=Dao.getConnection(null);
//		List<Date>  list=getTaskDate(new Date((DateUtil.parse("2018-05-09")).getTime()), new Date((DateUtil.parse("2018-09-30")).getTime()), "DK4LK180004", conn);
//	     System.out.println(list.size());
//	}
}
