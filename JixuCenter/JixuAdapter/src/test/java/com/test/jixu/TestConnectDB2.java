package com.test.jixu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class TestConnectDB2 {

	/**
	 * 寤虹珛鏁版嵁搴撹繛鎺�
	 */
	public Connection getConnection(String userName, String password) throws Exception{
		String dBDriver = "com.ibm.db2.jcc.DB2Driver";
		Class.forName(dBDriver);
		//Connection conn = DriverManager.getConnection("jdbc:db2://"+host+":"+port+"/tamsdb:currentSchema="+dataBaseName+";", userName, password);
		Connection conn = DriverManager.getConnection("jdbc:db2://192.168.70.62:50000/tamsdb:currentSchema=REVEAL_MAST_TEST", userName, password);
		return conn;
	}
	
	public void Test(){
		List<Connection> connList=new ArrayList<Connection>();
		try{
			for(int i=0;i<500;i++){
				Connection conn=getConnection("tamsinst", "tamsinst");
				connList.add(conn);
				System.out.println(i+1);
				Thread.sleep(1L);
			}
			
			System.out.println(1111);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println(22222);
		
		try{
			for(int i=0;i<500;i++){
				connList.get(i).close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println(11111);
	}
	
	public static void main(String[] args){
		TestConnectDB2 test=new TestConnectDB2();
		test.Test();
	}
}
