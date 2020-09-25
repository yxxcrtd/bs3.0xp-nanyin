package com.joyintech.tams.disclosure.busicommon.infodisclosure.reportSource.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DisTargetIndexServiceGroup {
	
	public Map<String,Object> printGS(Date startDate, Date endDate, String prodId,Connection conn)throws Exception {
		//定义返回值
		Map<String,Object> resultmap=new HashMap<>();
		// 期初总资产估值
		BigDecimal startValueTotal = getStartTotal(startDate, endDate, prodId, conn);
		// 期末总资产估值
		BigDecimal endValueTotal = getEndTotal(startDate, endDate, prodId, conn);
		// 期初资产
		if(startValueTotal != null){
			// 期初固收类权益资产估值
			BigDecimal gsStartValueNet = getStartGS(startDate, endDate, prodId, conn);
			// 期初权益类资产估值
			BigDecimal qyStartValueNet = getStartQY(startDate, endDate, prodId, conn);
			// 期初衍生品估值
			BigDecimal yspStartValueNet = getStartYSP(startDate, endDate, prodId, conn);
			// 期初债券估值
			BigDecimal zqStartValueNet = getStartZQ(startDate, endDate, prodId, conn);
			// 期初存款估值
			BigDecimal ckStartValueNet = getStartCK(startDate, endDate, prodId, conn);
			// 期初固收类权益资产占比
			BigDecimal gsStartScale = null;
			if(gsStartValueNet != null) {
				gsStartScale = gsStartValueNet.divide(startValueTotal, 2, BigDecimal.ROUND_HALF_UP);
			}
			
			// 期初权益类资产占比
			BigDecimal qyStartScale = null;
			if(qyStartValueNet != null) {
				qyStartScale = qyStartValueNet.divide(startValueTotal, 2, BigDecimal.ROUND_HALF_UP);
			}
			
			// 期初衍生品占比
			BigDecimal yspStartScale = null;
			if(yspStartValueNet != null) {
				yspStartScale = yspStartValueNet.divide(startValueTotal, 2, BigDecimal.ROUND_HALF_UP);
			}
			// 期初债券占比
			BigDecimal zqStartScale = null;
			if(zqStartValueNet != null) {
				zqStartScale = zqStartValueNet.divide(startValueTotal, 2, BigDecimal.ROUND_HALF_UP);
			}
			
			// 期初存款占比
			BigDecimal ckStartScale = null;
			if(ckStartValueNet != null) {
				ckStartScale = ckStartValueNet.divide(startValueTotal, 2, BigDecimal.ROUND_HALF_UP);
			}
			
			// 期初其他债券类资产占比
			BigDecimal qtStartScale = null;
			if(gsStartScale != null) {
				qtStartScale = gsStartScale.subtract(zqStartScale).subtract(ckStartScale);
			}
			
			resultmap.put("gsStartScale", gsStartScale);
			resultmap.put("qyStartScale", qyStartScale);
			resultmap.put("yspStartScale", yspStartScale);
			resultmap.put("zqStartScale", zqStartScale);
			resultmap.put("ckStartScale", ckStartScale);
			resultmap.put("qtStartScale", qtStartScale);
		}
		
		// 期末资产
		if(endValueTotal != null){
			// 期末固收类权益资产估值
			BigDecimal gsEndValueNet = getEndGS(startDate, endDate, prodId, conn);
			// 期末权益类资产估值
			BigDecimal qyEndValueNet = getEndQY(startDate, endDate, prodId, conn);
			// 期末衍生品估值
			BigDecimal yspEndValueNet = getEndYSP(startDate, endDate, prodId, conn);
			// 期末债券估值
			BigDecimal zqEndValueNet = getEndZQ(startDate, endDate, prodId, conn);
			// 期末存款估值
			BigDecimal ckEndValueNet = getEndCK(startDate, endDate, prodId, conn);
			// 期末固收类权益资产占比
			BigDecimal gsEndScale = null;
			if(gsEndValueNet != null) {
				gsEndScale = gsEndValueNet.divide(endValueTotal, 2, BigDecimal.ROUND_HALF_UP);
			}
			
			// 期末权益类资产占比
			BigDecimal qyEndScale = null;
			if(qyEndValueNet != null) {
				qyEndScale = qyEndValueNet.divide(endValueTotal, 2, BigDecimal.ROUND_HALF_UP);
			}
			
			// 期末衍生品占比
			BigDecimal yspEndScale = null;
			if(yspEndValueNet != null) {
				yspEndScale = yspEndValueNet.divide(endValueTotal, 2, BigDecimal.ROUND_HALF_UP);
			}
			
			// 期末债券占比
			BigDecimal zqEndScale = null;
			if(zqEndValueNet != null) {
				zqEndScale = zqEndValueNet.divide(endValueTotal, 2, BigDecimal.ROUND_HALF_UP);
			}
			
			// 期末存款占比
			BigDecimal ckEndScale = null;
			if(ckEndValueNet != null) {
				ckEndScale = ckEndValueNet.divide(endValueTotal, 2, BigDecimal.ROUND_HALF_UP);
			}
			
			// 期末其他债券类资产占比
			BigDecimal qtEndScale = null;
			if(gsEndScale != null) {
				qtEndScale = gsEndScale.subtract(zqEndScale).subtract(ckEndScale);
			}
			
			resultmap.put("gsEndScale", gsEndScale);
			resultmap.put("qyEndScale", qyEndScale);
			resultmap.put("yspEndScale", yspEndScale);
			resultmap.put("zqEndScale", zqEndScale);
			resultmap.put("ckEndScale", ckEndScale);
			resultmap.put("qtEndScale", qtEndScale);
		}
		return resultmap;
	}
	/**
	 * 期初估值总和
	 * @param
	 * @throws Exception 
	 */
	private BigDecimal getStartTotal(Date startDate,Date endDate,String prodId, Connection conn) {
		
		String sql="select sum(rpa.ASSETS_VALUE_NET) startValueTotal from REP_PROD_ASSET rpa  where STATUS='E' and rpa.VAL_DATE=? and rpa.PROD_ID=?";
		 //创建执行语句
		PreparedStatement ps= null;
		ResultSet rs = null;
		BigDecimal startValueTotal = null;
		try {
			ps = conn.prepareStatement(sql);

			//传参
			ps.setDate(1,new java.sql.Date(startDate.getTime()));
			ps.setString(2,prodId);

			//执行语句
			 rs = ps.executeQuery();
			if(rs.next()){
				startValueTotal = rs.getBigDecimal("startValueTotal");
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


		return startValueTotal;
		
	}
	
	/**
	 * 期末估值总和
	 * @param
	 * @throws Exception 
	 */
	private BigDecimal getEndTotal(Date startDate,Date endDate,String prodId, Connection conn) {
		// 期末资产总和
		String sql="select sum(rpa.ASSETS_VALUE_NET) endValueTotal from REP_PROD_ASSET rpa  where STATUS='E' and rpa.VAL_DATE=? and rpa.PROD_ID=?";
		 //创建执行语句
		PreparedStatement ps= null;
		ResultSet rs = null;
		BigDecimal endValueTotal = null;
		try {
			ps = conn.prepareStatement(sql);
			//传参
			ps.setDate(1,new java.sql.Date(endDate.getTime()));
			ps.setString(2,prodId);

			//执行语句
			 rs = ps.executeQuery();
			if(rs.next()){
				endValueTotal = rs.getBigDecimal("endValueTotal");
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
		return endValueTotal;
	}
	
	/**
	 * 期初固定收益类资产
	 * @param
	 * @throws Exception 
	 */
	private BigDecimal getStartGS(Date startDate,Date endDate,String prodId, Connection conn) {
		// 固定收益类资产估值
		String sql="select sum(rpa.ASSETS_VALUE_NET) gsStartValueNet from REP_PROD_ASSET rpa left join REP_ASSET_SETTING ras on ras.EFFECTFLAG = 'E' and ras.ASSETCODE=rpa.ASSET_ID  where rpa.STATUS='E' and rpa.VAL_DATE=? and rpa.PROD_ID=? and ras.ASSETCLASS = '1'";
		 //创建执行语句
		PreparedStatement ps= null;
		ResultSet rs = null;
		BigDecimal gsStartValueNet = null;
		try {
			ps = conn.prepareStatement(sql);
			//传参
			ps.setDate(1,new java.sql.Date(startDate.getTime()));
			ps.setString(2,prodId);

			 gsStartValueNet = new BigDecimal(0);
			//执行语句
			 rs = ps.executeQuery();
			if(rs.next()){
				gsStartValueNet = rs.getBigDecimal("gsStartValueNet");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
		return gsStartValueNet;
	}
	
	/**
	 * 期初权益类资产
	 * @param
	 * @throws Exception 
	 */
	private BigDecimal getStartQY(Date startDate,Date endDate,String prodId, Connection conn) {
		
		// 查询期初权益类资产估值
		String sql="select sum(rpa.ASSETS_VALUE_NET) qyStartValueNet from REP_PROD_ASSET rpa left join REP_ASSET_SETTING ras on ras.EFFECTFLAG = 'E' and ras.ASSETCODE=rpa.ASSET_ID  where rpa.STATUS='E' and rpa.VAL_DATE=? and rpa.PROD_ID=? and ras.ASSETCLASS = '2'";
		 //创建执行语句
		PreparedStatement ps= null;
		ResultSet rs = null;
		BigDecimal qyStartValueNet = null;
		try {
			ps = conn.prepareStatement(sql);
			//传参
			ps.setDate(1,new java.sql.Date(startDate.getTime()));
			ps.setString(2,prodId);

			qyStartValueNet = new BigDecimal(0);
			//执行语句
			 rs = ps.executeQuery();
			if(rs.next()){
				qyStartValueNet = rs.getBigDecimal("qyStartValueNet");
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
		return qyStartValueNet;
	}
	
	/**
	 * 期初衍生品
	 * @param
	 * @throws Exception 
	 */
	private BigDecimal getStartYSP(Date startDate,Date endDate,String prodId, Connection conn) {
		
		// 查询期初衍生品估值
		String sql="select sum(rpa.ASSETS_VALUE_NET) yspStartValueNet from REP_PROD_ASSET rpa left join REP_ASSET_SETTING ras on ras.EFFECTFLAG = 'E' and ras.ASSETCODE=rpa.ASSET_ID  where rpa.STATUS='E' and rpa.VAL_DATE=? and rpa.PROD_ID=? and ras.ASSETCLASS = '3'";
		 //创建执行语句
		PreparedStatement ps= null;
		ResultSet rs = null;
		BigDecimal yspStartValueNet = null;
		try {
			ps = conn.prepareStatement(sql);
			//传参
			ps.setDate(1,new java.sql.Date(startDate.getTime()));
			ps.setString(2,prodId);

			 yspStartValueNet = new BigDecimal(0);
			//执行语句
			 rs = ps.executeQuery();
			if(rs.next()){
				yspStartValueNet = rs.getBigDecimal("yspStartValueNet");
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
		return yspStartValueNet;
	}
	
	/**
	 * 期初债券估值
	 * @param
	 * @throws Exception 
	 */
	private BigDecimal getStartZQ(Date startDate,Date endDate,String prodId, Connection conn) {
		
		// 查询期初债券估值
		String sql="select sum(rpa.ASSETS_VALUE_NET) zqStartValueNet from REP_PROD_ASSET rpa  where STATUS='E' and rpa.VAL_DATE=? and rpa.PROD_ID=? and rpa.ASSET_TYPE_ONE='01'";
		 //创建执行语句
		PreparedStatement ps= null;
		ResultSet rs = null;
		BigDecimal zqStartValueNet = null;
		try {
			ps = conn.prepareStatement(sql);

			//传参
			ps.setDate(1,new java.sql.Date(startDate.getTime()));
			ps.setString(2,prodId);

			 zqStartValueNet = new BigDecimal(0);
			//执行语句
			 rs = ps.executeQuery();
			if(rs.next()){
				zqStartValueNet = rs.getBigDecimal("zqStartValueNet");
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
		return zqStartValueNet;
	}
	
	/**
	 * 期初存款估值
	 * @param
	 * @throws Exception 
	 */
	private BigDecimal getStartCK(Date startDate,Date endDate,String prodId, Connection conn) {
		
		// 查询期初存款估值
		String sql="select sum(rpa.ASSETS_VALUE_NET) ckStartValueNet from REP_PROD_ASSET rpa  where STATUS='E' and rpa.VAL_DATE=? and rpa.PROD_ID=? and rpa.ASSET_TYPE_ONE='04' and rpa.ASSET_TYPE_TWO='0404' and rpa.IS_CASH='01'";
		 //创建执行语句
		PreparedStatement ps= null;
		ResultSet rs = null;
		BigDecimal ckStartValueNet = null;
		try {
			ps = conn.prepareStatement(sql);
			//传参
			ps.setDate(1,new java.sql.Date(startDate.getTime()));
			ps.setString(2,prodId);

			 ckStartValueNet = new BigDecimal(0);
			//执行语句
			 rs = ps.executeQuery();
			if(rs.next()){
				ckStartValueNet = rs.getBigDecimal("ckStartValueNet");
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
		return ckStartValueNet;
	}
	
	/**
	 * 期末固定收益类资产
	 * @param
	 * @throws Exception 
	 */
	private BigDecimal getEndGS(Date startDate,Date endDate,String prodId, Connection conn) {
		// 期末固定收益类资产估值
		String sql="select sum(rpa.ASSETS_VALUE_NET) gsEndValueNet from REP_PROD_ASSET rpa left join REP_ASSET_SETTING ras on ras.EFFECTFLAG = 'E' and ras.ASSETCODE=rpa.ASSET_ID  where rpa.STATUS='E' and rpa.VAL_DATE=? and rpa.PROD_ID=? and ras.ASSETCLASS = '1'";
		 //创建执行语句
		PreparedStatement ps= null;
		ResultSet rs = null;
		BigDecimal gsEndValueNet = null;
		try {
			ps = conn.prepareStatement(sql);
			//传参
			ps.setDate(1,new java.sql.Date(endDate.getTime()));
			ps.setString(2,prodId);

			 gsEndValueNet = new BigDecimal(0);
			//执行语句
			 rs = ps.executeQuery();
			if(rs.next()){
				gsEndValueNet = rs.getBigDecimal("gsEndValueNet");
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
		return gsEndValueNet;
	}
	
	/**
	 * 期末权益类资产
	 * @param
	 * @throws Exception 
	 */
	private BigDecimal getEndQY(Date startDate,Date endDate,String prodId, Connection conn) {
		
		// 查询期末权益类资产估值
		String sql="select sum(rpa.ASSETS_VALUE_NET) qyEndValueNet from REP_PROD_ASSET rpa left join REP_ASSET_SETTING ras on ras.EFFECTFLAG = 'E' and ras.ASSETCODE=rpa.ASSET_ID  where rpa.STATUS='E' and rpa.VAL_DATE=? and rpa.PROD_ID=? and ras.ASSETCLASS = '2'";
		 //创建执行语句
		PreparedStatement ps= null;
		BigDecimal qyEndValueNet = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			//传参
			ps.setDate(1,new java.sql.Date(endDate.getTime()));
			ps.setString(2,prodId);

			 qyEndValueNet = new BigDecimal(0);
			//执行语句
			 rs = ps.executeQuery();
			if(rs.next()){
				qyEndValueNet = rs.getBigDecimal("qyEndValueNet");
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
		return qyEndValueNet;
	}
	
	/**
	 * 期末衍生品
	 * @param
	 * @throws Exception 
	 */
	private BigDecimal getEndYSP(Date startDate,Date endDate,String prodId, Connection conn) {
		
		// 查询期末衍生品估值
		String sql="select sum(rpa.ASSETS_VALUE_NET) yspEndValueNet from REP_PROD_ASSET rpa left join REP_ASSET_SETTING ras on ras.EFFECTFLAG = 'E' and ras.ASSETCODE=rpa.ASSET_ID  where rpa.STATUS='E' and rpa.VAL_DATE=? and rpa.PROD_ID=? and ras.ASSETCLASS = '3'";
		 //创建执行语句
		PreparedStatement ps= null;
		BigDecimal yspEndValueNet = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			//传参
			ps.setDate(1,new java.sql.Date(endDate.getTime()));
			ps.setString(2,prodId);

			 yspEndValueNet = new BigDecimal(0);
			//执行语句
			 rs = ps.executeQuery();
			if(rs.next()){
				yspEndValueNet = rs.getBigDecimal("yspEndValueNet");
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
		return yspEndValueNet;
	}
	
	/**
	 * 期末债券估值
	 * @param
	 * @throws Exception 
	 */
	private BigDecimal getEndZQ(Date startDate,Date endDate,String prodId, Connection conn) {
		
		// 查询期末债券估值
		String sql="select sum(rpa.ASSETS_VALUE_NET) zqEndValueNet from REP_PROD_ASSET rpa  where STATUS='E' and rpa.VAL_DATE=? and rpa.PROD_ID=? and rpa.ASSET_TYPE_ONE='01'";
		 //创建执行语句
		PreparedStatement ps= null;
		BigDecimal zqEndValueNet = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			//传参
			ps.setDate(1,new java.sql.Date(endDate.getTime()));
			ps.setString(2,prodId);

			 zqEndValueNet = new BigDecimal(0);
			//执行语句
			 rs = ps.executeQuery();
			if(rs.next()){
				zqEndValueNet = rs.getBigDecimal("zqEndValueNet");
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
		return zqEndValueNet;
	}
	
	/**
	 * 期末存款估值
	 * @param
	 * @throws Exception 
	 */
	private BigDecimal getEndCK(Date startDate,Date endDate,String prodId, Connection conn) {
		
		// 查询期末存款估值
		String sql="select sum(rpa.ASSETS_VALUE_NET) ckEndValueNet from REP_PROD_ASSET rpa  where STATUS='E' and rpa.VAL_DATE=? and rpa.PROD_ID=? and rpa.ASSET_TYPE_ONE='04' and rpa.ASSET_TYPE_TWO='0404' and rpa.IS_CASH='01'";
		 //创建执行语句
		PreparedStatement ps= null;
		BigDecimal ckEndValueNet = null;
		ResultSet rs = null;

		try {
			ps = conn.prepareStatement(sql);
			//传参
			ps.setDate(1,new java.sql.Date(endDate.getTime()));
			ps.setString(2,prodId);

			 ckEndValueNet = new BigDecimal(0);
			//执行语句
			 rs = ps.executeQuery();
			if(rs.next()){
				ckEndValueNet = rs.getBigDecimal("ckEndValueNet");
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
		return ckEndValueNet;
	}
	
	/*public static void main(String[] args) {
		Connection conn;
		try {
			conn = Dao.getConnection("");
			TargetIndexService s = new TargetIndexService();
			Map<String,Object> resultmap=new HashMap<>();
			resultmap = s.printGS(DateUtils.parseDate("2019-02-25","yyyy-MM-dd"),DateUtils.parseDate("2019-02-25","yyyy-MM-dd"),"A170D4623",conn);
			System.out.println(resultmap.get("gsStartScale"));
			System.out.println(resultmap.get("qyStartScale"));
			System.out.println(resultmap.get("yspStartScale"));
			System.out.println(resultmap.get("ckStartScale"));
			System.out.println(resultmap.get("gsEndScale"));
			System.out.println(resultmap.get("qyEndScale"));
			System.out.println(resultmap.get("yspEndScale"));
			System.out.println(resultmap.get("ckEndScale"));
			System.out.println(resultmap.get("qtStartScale"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
