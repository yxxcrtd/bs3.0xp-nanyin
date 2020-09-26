package com.joyintech.tams.valuation.report.common.util;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JDBC连接oracle数据库
 * @author Dongxiangyang
 *
 */
public class Dao {
	/**
	 * 获取连接
	 * @param object
	 * @throws Exception
	 */
	  public static Connection getConnection(String object) throws Exception{
		  //读取配置文件连接信息
		  Properties prop= new Properties();
		  InputStream is= Dao.class.getClassLoader().getResourceAsStream("resources/jdbc.properties");
		  try {
				prop.load(is);
			} catch (Exception e) {
				is= Dao.class.getClassLoader().getResourceAsStream("jdbc.properties");
				prop.load(is);
			}
		  //加载驱动
		  Class.forName(prop.getProperty("driver"));
		  //获取地址
		  String url=prop.getProperty("url");
		  //获取用户名
		  String userName=prop.getProperty("userName");
		 //获取密码
		  String passWord=prop.getProperty("passWord");

		//获取连接对象
		  Connection conn=DriverManager.getConnection(url, userName, passWord);
		  return conn;

	  }

	public static void main(String[] args) {
		//读取配置文件连接信息
		Properties prop = new Properties();
		InputStream is = Dao.class.getClassLoader().getResourceAsStream("application.properties");
		Connection conn = null;
		try {
			prop.load(is);
			//加载驱动
			Class.forName(prop.getProperty("driver"));
			//获取地址
			String url = prop.getProperty("url");
			//获取用户名
			String userName = prop.getProperty("userName");
			//获取密码
			String passWord = prop.getProperty("passWord");

			//获取连接对象
			conn = DriverManager.getConnection(url, userName, passWord);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (conn!=null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
