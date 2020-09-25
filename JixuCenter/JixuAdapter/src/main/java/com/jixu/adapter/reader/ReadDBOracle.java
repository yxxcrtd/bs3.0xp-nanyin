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
public class ReadDBOracle extends ReadDBAbstract {

	public int getFileType() {
		return InterfaceConfig.oracle;
	}

	/**
	 * 建立数据库连接
	 */
	public Connection getConnection(String urlpath, String userName, String password) throws Exception{
		String dBDriver = "oracle.jdbc.driver.OracleDriver";
		Class.forName(dBDriver);
		
		String[] dbArrs=urlpath.split(",");
		String connpath="";
		if(dbArrs.length==3) {
			connpath="jdbc:oracle:thin:@" + dbArrs[0] + ":" + dbArrs[1] + "/" + dbArrs[2];
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
		sql="select * from (select row__.*, rownum as rownum__ from("+sql+") row__) where rownum__<=? and rownum__>?";
		sqlPageOrder.setSql(sql);
		sqlPageOrder.setOrders(new int[]{endnum,startnum});
		return sqlPageOrder;
	}
}
