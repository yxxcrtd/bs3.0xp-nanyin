package com.jixu.adapter.reader;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.stereotype.Service;

import com.jixu.adapter.model.SQLPageOrder;
import com.jixu.common.type.InterfaceConfig;
/** 
 * @ClassName 		ReadCSVFile
 * @author  		Pan Wei 
 * @Date 			2016年12月5日 下午3:08:08 	
 * @Description		[]  
 */

@Service
public class ReadDBDB2 extends ReadDBAbstract {

	public int getFileType() {
		return InterfaceConfig.db2;
	}

	/**
	 * 建立数据库连接
	 */
	public Connection getConnection(String urlpath, String userName, String password) throws Exception{
		String dBDriver = "com.ibm.db2.jcc.DB2Driver";
		Class.forName(dBDriver);
		String[] dbArrs=urlpath.split(",");
		String connpath="";
		if(dbArrs.length==3) {
			connpath="jdbc:db2://"+dbArrs[0]+":"+dbArrs[1]+"/"+dbArrs[2];
		}else {
			connpath=urlpath;
		}
		
		Connection conn = DriverManager.getConnection(connpath, userName, password);
		logger.info("数据库连接成功! " + urlpath);
		return conn;
	}
	
	@Override
	public SQLPageOrder createPeriodSQL(String sql, int startnum, int endnum) {
		SQLPageOrder sqlPageOrder=new SQLPageOrder();
		sql="select * from (select row__.*, ROW_NUMBER() OVER() AS rownum__ from("+sql+") row__) where rownum__<=? and rownum__>?";
		sqlPageOrder.setSql(sql);
		sqlPageOrder.setOrders(new int[]{endnum,startnum});
		return sqlPageOrder;
	}
}
