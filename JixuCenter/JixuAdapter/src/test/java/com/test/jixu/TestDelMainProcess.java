package com.test.jixu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class TestDelMainProcess {
	/**
	 * 寤虹珛鏁版嵁搴撹繛鎺�
	 */
	public Connection getConnection(String host, String port, String dataBaseName, String userName, String password) throws Exception{
		String dBDriver = "com.ibm.db2.jcc.DB2Driver";
		Class.forName(dBDriver);
		//Connection conn = DriverManager.getConnection("jdbc:db2://"+host+":"+port+"/tamsdb:currentSchema="+dataBaseName+";", userName, password);
		Connection conn = DriverManager.getConnection("jdbc:db2://"+host+":"+port+"/"+dataBaseName, userName, password);
		System.out.println("鏁版嵁搴撹繛鎺ユ垚鍔�! " + host + ":"+port+":"+dataBaseName);
		return conn;
	}
	
	public void deleteRecord(){
		String sql="delete from adpt_interfaceparamslastmax";
		String sql1="delete from rep_fee_yield";
		Connection conn=null;
		Statement stmt=null;
		try{
			conn=getConnection("192.168.70.62", "50000", "tamsdb:currentSchema=REVEAL_MAST_TEST", "tamsinst","tamsinst");
			stmt=conn.createStatement();
			stmt.executeUpdate(sql);
			stmt.executeUpdate(sql1);
			conn.commit();

			System.out.println("鏁版嵁宸插垹闄�	" + new Date());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(stmt!=null){
					stmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args){
		TestDelMainProcess test=new TestDelMainProcess();
		try{
			while(true){
				test.deleteRecord();
				Thread.sleep(600000L);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
