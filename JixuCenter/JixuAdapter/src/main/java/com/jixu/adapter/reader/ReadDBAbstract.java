package com.jixu.adapter.reader;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jixu.adapter.dao.intf.InterfaceAdapterDao;
import com.jixu.adapter.dao.intf.InterfaceColConfigDao;
import com.jixu.adapter.model.ParamsSet;
import com.jixu.adapter.model.SQLPageOrder;
import com.jixu.adapter.service.AdditionFunc;
import com.jixu.adapter.service.impl.DataInterfaceService;
import com.jixu.common.exception.InterfaceException;
import com.jixu.common.pojo.InterfaceColConfig;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.DataCounter;
import com.jixu.common.type.DataReadResult;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.util.AESUtil;
import com.jixu.framework.dao.intf.CommFuncDAO;

public abstract class ReadDBAbstract implements ReadDataFile{
	@Resource(name="interfaceAdapterDao")
	protected InterfaceAdapterDao interfaceSourceDao;
	
	@Autowired
	protected InterfaceColConfigDao interfaceColConfigDao;
	
	@Resource(name="dataInterfaceService")
	private DataInterfaceService ineterfaceBeanService;
	
	@Autowired
	protected CommFuncDAO targetDAO;  
	
	protected static final Logger logger = LoggerFactory.getLogger(ReadDBAbstract.class);
	

	@Override
	public DataReadResult readFileData(InterfaceSource source, DataCounter dataCounter) throws Exception{
		DataReadResult result=new DataReadResult();
		
		String sql=source.getDbSQL();
		List<InterfaceColConfig> configList=interfaceColConfigDao.loadInterfaceColConfig(source.getSourceID());

		//获取需要执行的参数和值
		HashMap<String, Object> paramsMap=new HashMap<String, Object>();
		
		if(sql.contains(":")){
			for(InterfaceColConfig config:configList){
				if(config.getColType()==InterfaceConfig.paramsType || config.getColType()== InterfaceConfig.paramsCycleType || config.getColType()== InterfaceConfig.paramsOut){
					AdditionFunc func=ineterfaceBeanService.getAdditionFuncBean(config.getFuncClass());
					HashMap<String, Object> paramMap=new HashMap<String, Object>();
					if(config.getColType()== InterfaceConfig.paramsOut) {
						//传入外部参数
						paramMap=dataCounter.getOutParamsMap();
					}else {	
						paramMap.put(ParamsSet.interfaceColCfg, config);
					}
					Object paramValue=func.calFunc(config.getFuncParams(), paramMap);
					
					paramsMap.put(config.getColname(), paramValue);
				}
			}
		}
		
		//参数的值
		List<Object> valuesList=new ArrayList<Object>();
		while(sql.contains(":")){
			String param=sql.substring(sql.indexOf(":"),sql.length());
			if(param.contains(" ")){
				param=param.substring(0, param.indexOf(" "));
			}
			
			boolean paramFlag=false;
			for(InterfaceColConfig cfg:configList){
				if(cfg.getColname().toLowerCase().equals(param.replace(":", "").toLowerCase())){
					Object valobj=paramsMap.get(param.replace(":", ""));
					if((valobj.getClass().isArray()) || (valobj instanceof List)) {
						//参数是数组或者list，采用行专列的方式，把临时sql加入原有的sql，并增加两个输入参数
						sql=sql.replace(param, "(select regexp_substr( ? || ',','[^,]+',1,rownum) as jixu_test_val from dual connect by rownum<=length(regexp_replace( ? || ',', '[^,]', null)))");
						String tmpval="";
						if(valobj.getClass().isArray()) {
							Object[] osTmp = new Object[Array.getLength(valobj)];
							for(int i=0;i< osTmp.length;i++) {
								osTmp[i]=Array.get(valobj, i);
							}
							tmpval=StringUtils.join(osTmp, ",");
						}else if(valobj instanceof Collection) {
							tmpval=StringUtils.join((Collection)valobj, ",");
						}
						valuesList.add(tmpval);
						valuesList.add(tmpval);
					}else {
						sql=sql.replace(param, "?");
						valuesList.add(valobj);
					}
					paramFlag=true;
					break;
				}
			}
			if(!paramFlag){
				logger.error("没有找到匹配的SQL参数: " + param);
				throw(new InterfaceException("没有找到匹配的SQL参数: " + param));
			}
		}
		
		
		//一个适配器配置只连接一次，分段查询。在第一次查询建立jdbc连接，之后查询则使用之前的连接。发现中断则重连
		Connection conn=null;
		if(dataCounter.getConn()==null || dataCounter.getConn().isClosed()){
			conn=getConnection(source.getFilepath(), source.getChannelAccount(), AESUtil.Decrypt(source.getChannelPWD()));
		}else{
			conn=dataCounter.getConn();
		}
		dataCounter.setConn(conn);
		
		//查看是否已经取过数据
		String countsql=sql;
		if(dataCounter.getFetchRecords()==0){
			int countnum=getTotalRecords(countsql, valuesList, conn);
			logger.info(source.getSourceName()+"		准备获取数据数量: " + countnum);
			dataCounter.setTotalRecords(countnum);

			//第一次取数，发现有数据时，取最大值
			if(countnum>0){
				logger.info(source.getSourceName()+"		开始取数-开始获取参数最大值");
				HashMap<Integer, Object> initcycleMap=getMaxValue(configList, conn);
				dataCounter.setInitMaxCycleMap(initcycleMap);
				logger.info(source.getSourceName()+"		开始取数-结束获取参数最大值");
			}
		}
		
		if(dataCounter.getTotalRecords()==0){
			return result;
		}
		
		int toFetchNum=0;	//本页准备取的数据数量。 和实际取到的数量做对比，如果数量不一样，则判判定数据传输过程中丢失
		int startnum=0;	//本段取数的开始行	>startnum
		int endnum=0;	//本段取数的结束行	<=endnum
		if(dataCounter.getFetchRecords()==0 && dataCounter.getTotalRecords()<=dataCounter.getOnePageRecords()){
			//第一次查询就能取完所有的数据，即数量总数<一页的数量
			toFetchNum=dataCounter.getTotalRecords();
		}else{
			startnum=dataCounter.getFetchRecords();		//已经取了多少数据
			endnum=dataCounter.getFetchRecords()+dataCounter.getOnePageRecords();
			if(dataCounter.getTotalRecords()<endnum){
				//如果是最后一页，取的数可能是不足一页的。同时需要计算本页取了多少数据(endnum-startnum),和真实取到的数据做数量对比，来判定有没有数据丢失
				endnum=dataCounter.getTotalRecords();
			}
			toFetchNum=endnum-startnum;
		}
		
		//这里要区分，是oracle, mysql, db2等数据库是不同的
		SQLPageOrder sqlPageOrder=null;
		if(endnum>0){
			//如果endnum>0,则证明数据是分页查询的，需要设置startnum和endnum
			sqlPageOrder=createPeriodSQL(sql, startnum, endnum);
			sql=sqlPageOrder.getSql();
		}
		
		//这里采用PreparedStatement，主要是为了分段查询，实现预编译sql语句，每次分段查询获取相同的执行计划， 1.提高效率 2.分页的排序不会发生改变?
		PreparedStatement stmt=null;
		if(dataCounter.getPreparedStatement()==null || dataCounter.getPreparedStatement().isClosed()){
			//第一次查询或者中间间断了，则重新设值stmt
			stmt=conn.prepareStatement(sql);
			dataCounter.setPreparedStatement(stmt);
		}else{
			//如果是第一次之后的查询，发现实例还在，则获取原先的状态
			stmt=dataCounter.getPreparedStatement();
		}
		
		for(int i=0;i<valuesList.size();i++){
			if(valuesList.get(i) instanceof Timestamp){
				stmt.setTimestamp(i+1,(Timestamp)valuesList.get(i));
			}else if(valuesList.get(i) instanceof Date){
				stmt.setTimestamp(i+1,new Timestamp(((Date)valuesList.get(i)).getTime()));
			}else if(valuesList.get(i) instanceof Integer || valuesList.get(i) instanceof Double ||
					valuesList.get(i) instanceof BigDecimal || valuesList.get(i) instanceof Float){
				stmt.setDouble(i+1, Double.parseDouble(valuesList.get(i).toString()));
			}else{
				stmt.setString(i+1, valuesList.get(i).toString());
			}
		}
		
		//排序的参数需要在目前已经有的参数基础上执行
		int baseParamSize=valuesList.size();
		//分页查询，则设置两个参数
		if(endnum>0 && sqlPageOrder!=null){
			int[] orders=sqlPageOrder.getOrders();
			if(orders!=null){
				for(int i=0;i<orders.length;i++){
					stmt.setInt(baseParamSize+i+1, orders[i]);
				}
			}
		}
		
		ResultSet rs=stmt.executeQuery();
		ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据
		int columnCount = md.getColumnCount();   //获得列数 
		
		List<List<Object>> columnsList=new ArrayList<List<Object>>();
		List<String> headerList=new ArrayList<String>();	//用于获取查询的列名
		while (rs.next()) {
			List<Object> fieldsList=new ArrayList<Object>();
			for (int i = 1; i <= columnCount; i++) {
				Object obj=rs.getObject(i);
				fieldsList.add(obj);
			}
			
			if(headerList.size()==0){
				for (int i = 1; i <= columnCount; i++) {
					headerList.add(md.getColumnLabel(i));
				}
			}
			columnsList.add(fieldsList);
 
		}

		//设值截至目前总共取了多少数据
		int thisFetchSize=columnsList.size();
		
		result.setDataResult(columnsList);
		result.setDataHeader(headerList);
		
		if(thisFetchSize!=toFetchNum){
			dataCounter.setCheckFlag(false);
			StringBuilder builder=new StringBuilder();
			builder.append("本轮获取数量 :" +thisFetchSize);
			builder.append(" / ");
			builder.append("目标获取数量:"+toFetchNum);
			
			dataCounter.setErrorMsg(builder.toString());
			dataCounter.setFailedNum(dataCounter.getFailedNum()+1);
		}else{
			//执行成功，才把获取的条数累加进去
			dataCounter.setFetchRecords(dataCounter.getFetchRecords()+thisFetchSize);
			
			if(dataCounter.getFetchRecords()==dataCounter.getTotalRecords()){
				//核对运行前后数据的总量是否一致
				int aftercountnum=getTotalRecords(countsql, valuesList, conn);
				dataCounter.setAfterCheckTotalRecords(aftercountnum);
				logger.info(source.getSourceName()+"		重新验证数据数量: " + aftercountnum);
				
				String fetchFinalErrorMsg="";
				if(aftercountnum!=dataCounter.getTotalRecords()){
					fetchFinalErrorMsg="开始取数时查询的总量和结束取数时再次查询的总量不一致: " + dataCounter.getTotalRecords()+"/"+aftercountnum;
					logger.error(source.getSourceName()+"		"+fetchFinalErrorMsg);
					throw(new InterfaceException(fetchFinalErrorMsg));
				}
				
				//核对运行前后源表的数据最大时间是否一致
				//获取最大值时间
				logger.info(source.getSourceName()+"		取数完成-开始获取参数最大值");
				HashMap<Integer, Object> cycleMap=getMaxValue(configList, conn);
				dataCounter.setMaxCycleMap(cycleMap);
				logger.info(source.getSourceName()+"		取数完成-结束获取参数最大值");
				
				HashMap<Integer, Object> initCycleMap=dataCounter.getInitMaxCycleMap();
				for(Map.Entry<Integer, Object> entry : initCycleMap.entrySet()){
					Object aValue=entry.getValue();
					Object bValue=cycleMap.get(entry.getKey());
					
					boolean fetchCheckMax=true;
					if(aValue==null && bValue==null){
						fetchCheckMax=true;
					}else if(aValue!=null && bValue!=null){
						if(!aValue.equals(bValue)){
							fetchCheckMax=false;
						}
					}else{
						fetchCheckMax=false;
					}
					
					if(!fetchCheckMax){
						String failedCfgNameDesc="";
						for(InterfaceColConfig config:configList){
							if(config.getId()==entry.getKey()){
								failedCfgNameDesc=config.getColname()+"("+config.getFuncParams()+")";
								break;
							}
						}
						
						fetchFinalErrorMsg="开始取数时数据最大值与取数结束后的数据最大值不一致: "+failedCfgNameDesc+": "+ aValue+" / "+bValue;
						logger.error(source.getSourceName()+"		"+fetchFinalErrorMsg);
						throw(new InterfaceException(fetchFinalErrorMsg));
					}
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 每个数据库的分页查询语句不同,返回的SQL, 两个问号, 第一个占位endnun, 第二个占位startnum
	 * @param sql	
	 * @param startnum
	 * @param endnum
	 * @return
	 */
	protected abstract SQLPageOrder createPeriodSQL(String sql, int startnum, int endnum);
	
	/**
	 * 获取数据库列名
	 * @param source
	 * @return
	 */
	public List<String> getTableColumnNames(String tablename){
		List<String> columnList=new ArrayList<String>();
		if(tablename.trim().equals("")){
			//如果没有配置目标表,直接返回
			return columnList;
		}
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try{
			conn=targetDAO.getConnection();
			stmt=conn.createStatement();
			String sql="select * from " + tablename;
			rs=stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData(); //获得结果集结构信息,元数据
			for( int i=1; i<=rsmd.getColumnCount(); i++ ){
				String field = rsmd.getColumnName(i);
				/*int type=rsmd.getColumnType(i);
					columnList.add("'"+field+"'" );
				}else{
					columnList.add(field );
				}*/
				columnList.add(field );
			}
		}catch(Exception se){
			logger.error("Error", se);
		}finally{
			try{
				if(rs!=null) {
					rs.close();
				}
				if(stmt!=null){
					stmt.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch(SQLException se){
				logger.error("Error", se);
			}
		}
		return columnList;
	}
	
	/**
	 * 通过sql语句获取查询返回的列名
	 * @param source
	 * @return
	 */
	public List<String> getSourceTableColumnNames(InterfaceSource source){
		List<String> columnList=new ArrayList<String>();
		String connectInfo=source.getFilepath();
		Connection conn=null;
		Statement stmt=null;
		try{
			conn=getConnection(connectInfo, source.getChannelAccount(), AESUtil.Decrypt(source.getChannelPWD()));
			stmt=conn.createStatement();
			String sql=source.getDbSQL();
			
			while(sql.contains(":")){
				String param=sql.substring(sql.indexOf(":"),sql.length());
				if(param.contains(" ")){
					param=param.substring(0, param.indexOf(" "));
				}
				sql=sql.replace(param, "null");
			}
			
			ResultSet rs=stmt.executeQuery(sql);
			
			ResultSetMetaData rsmd = rs.getMetaData(); //获得结果集结构信息,元数据
			for( int i=1; i<=rsmd.getColumnCount(); i++ ){
				String field = rsmd.getColumnLabel(i);
				columnList.add( field );
			}
		}catch(Exception se){
			logger.error("Error", se);
		}finally{
			try{
				if(stmt!=null){
					stmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			}catch(SQLException se){
				logger.error("Error", se);
			}
		}
		return columnList;
	}
	
	public List<String> getSourceTableColumnNames(String urlpath,int dbFormat,String dbUser,String dbPwd,String sql){
		List<String> columnList=new ArrayList<String>();
		Connection conn=null;
		Statement stmt=null;
		try{
			conn=getConnection(urlpath, dbUser, dbPwd);
			stmt=conn.createStatement();
			
			while(sql.contains(":")){
				String param=sql.substring(sql.indexOf(":"),sql.length());
				if(param.contains(" ")){
					param=param.substring(0, param.indexOf(" "));
				}
				sql=sql.replace(param, "null");
			}
			
			ResultSet rs=stmt.executeQuery(sql);
			
			ResultSetMetaData rsmd = rs.getMetaData(); //获得结果集结构信息,元数据
			for( int i=1; i<=rsmd.getColumnCount(); i++ ){
				String field = rsmd.getColumnLabel(i);
				columnList.add( field );
			}
		}catch(Exception se){
			logger.error("Error", se);
		}finally{
			try{
				if(stmt!=null){
					stmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			}catch(SQLException se){
				logger.error("Error", se);
			}
		}
		return columnList;
	}
	
	private int getTotalRecords(String sql, List<Object> valuesList, Connection conn) throws SQLException{
		//取出要统计的总数据量
		String countSql="select count(*) from ("+sql+")";
		PreparedStatement cntStmt=conn.prepareStatement(countSql);
		for(int i=0;i<valuesList.size();i++){
			if(valuesList.get(i) instanceof Timestamp){
				cntStmt.setTimestamp(i+1,(Timestamp)valuesList.get(i));
			}else if(valuesList.get(i) instanceof Date){
				cntStmt.setTimestamp(i+1,new Timestamp(((Date)valuesList.get(i)).getTime()));
			}else if(valuesList.get(i) instanceof Integer || valuesList.get(i) instanceof Double ||
					valuesList.get(i) instanceof BigDecimal || valuesList.get(i) instanceof Float){
				cntStmt.setDouble(i+1, Double.parseDouble(valuesList.get(i).toString()));
			}else{
				cntStmt.setString(i+1, valuesList.get(i).toString());
			}
		}
		
		ResultSet rs=cntStmt.executeQuery();
		
		int countnum=0;
		while (rs.next()) {
			countnum=rs.getInt(1);
			break;
		}

		//关闭数量查询连接
		cntStmt.close();
		
		return countnum;
	}
	
	/**
	 * 获取最大值
	 * @param configList
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	private HashMap<Integer, Object> getMaxValue(List<InterfaceColConfig> configList, Connection conn) throws SQLException{
		HashMap<Integer, Object> cycleMap=new HashMap<Integer, Object>();
		for(InterfaceColConfig config:configList){
			if(config.getColType()==InterfaceConfig.paramsCycleType){
				String[] params=config.getFuncParams().split(",");
				if(params.length==3){
					String maxsql="select max("+params[1].trim()+") from " + params[0].trim();
					Statement maxstmt=conn.createStatement();
					ResultSet rsmax=maxstmt.executeQuery(maxsql);
					
					Object maxObj=null;
					while (rsmax.next()) {
						if(params[2].trim().toLowerCase().equals("d"))
							maxObj=rsmax.getTimestamp(1);
						else if(params[2].trim().toLowerCase().equals("n"))
							maxObj=rsmax.getDouble(1);
						else
							maxObj=rsmax.getString(1);
						break;
					}
					cycleMap.put(config.getId(), maxObj);
					rsmax.close();
				}
			}
		}
		return cycleMap;
	}

	public int dbTest(String urlpath, String dbUser, String dbPwd,int status){
		try {
			Connection conn = getConnection(urlpath, dbUser, dbPwd);
			conn.close();
		} catch (Exception e) {
			logger.error("Error", e);
			status=2;
		}
		return status;
	}
	
	public abstract Connection getConnection(String urlpath, String userName, String password) throws Exception;
}
