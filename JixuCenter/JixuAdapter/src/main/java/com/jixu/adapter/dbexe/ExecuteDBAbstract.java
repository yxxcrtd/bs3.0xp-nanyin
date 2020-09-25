package com.jixu.adapter.dbexe;

import java.io.Reader;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.jixu.adapter.dao.intf.AdapterUniqueRunDao;
import com.jixu.adapter.dao.intf.InterfaceColConfigDao;
import com.jixu.adapter.types.SQLMsgTemplate;
import com.jixu.common.pojo.InterfaceColConfig;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.type.MsgContainer;
import com.jixu.framework.dao.intf.CommFuncDAO;
import com.orm.obj.AdapterUniqueRun;
import com.orm.obj.InterfaceParamsLastMax;

public abstract class ExecuteDBAbstract implements ExecuteDBScripts{
	
	@Autowired
	protected CommFuncDAO targetDAO;
	
	@Autowired
	protected InterfaceColConfigDao interfaceColConfigDao;
	
	@Autowired
	private AdapterUniqueRunDao adapterUniqueRunDao;

	protected static final Logger logger = LoggerFactory.getLogger(ExecuteDBAbstract.class);
	
	protected String tmpTableAppend="_tmp";
	
	/**
	 * 获取要插入的字段名
	 * 从前面的insert语句提取字段名，如果失败，则从目标表提取字段名
	 * 如果从sql提取字段名，可以只插入或更新部分字段
	 * @param source
	 * @return
	 */
	protected List<String> getTargetColumns(InterfaceSource source, String tableName){
		//edit by wangqidong   如何循环使得每次获得该表的字段
		String sql=getTableMsg(source, tableName);
		List<String> headersList=new ArrayList<String>();
		int startnum=sql.indexOf("(");
		int endnum=sql.indexOf(")");
		int checknum=sql.toLowerCase().indexOf(" values ");
		if(sql.toLowerCase().startsWith("insert into") && startnum>0 && endnum>0 && startnum<checknum && endnum<checknum){
			String headerStr=sql.substring(startnum+1, endnum);
			String[] headers=headerStr.split(",");
			for(String header:headers){
				headersList.add(header.trim());
			}
		}
		
		if(headersList.size()==0){
			headersList=targetDAO.getColumnNames(tableName);
		}
		
		return headersList;
	}
	
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor=Exception.class)
	public MsgContainer executeBatch(List<MsgContainer> containerList) {
		MsgContainer mainContainer=new MsgContainer();
		for(MsgContainer container:containerList){
			//这里需要判定，有一条出错，是继续导入，还是结束导入
			execute(container);
			
			if(!container.isCorrectFlag()){
				//TODO 如果执行失败，需要回滚事务
				mainContainer.setCorrectFlag(false);
				mainContainer.setErrorMsg(container.getErrorMsg());
				return mainContainer;
			}
		}
		return mainContainer;
	}
	
	/**
	 * 解析消息
	 * @param msg
	 * @return
	 */
	protected HashMap<String ,Object> paraseMsg(String msg){
		HashMap<String,Object> msgMap=JSONObject.parseObject(msg, HashMap.class);
		return msgMap;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor=Exception.class)
	public void saveMaxCycleRmRunFlag(int sourceID, boolean importDataSuccessFlag, boolean childExeSuccessFlag, HashMap<Integer, Object> map) {
		if(importDataSuccessFlag && childExeSuccessFlag){
			Date lastExeTime=new Date();
			for (Map.Entry<Integer, Object> entry: map.entrySet()) {
				InterfaceParamsLastMax iplm=new InterfaceParamsLastMax();
				iplm.setLastExeTime(lastExeTime);
				iplm.setCfgID(entry.getKey());
				
				if(entry.getValue()==null){
					continue;
				}
				
				if(entry.getValue() instanceof Date){
					iplm.setLastValDate((Date)entry.getValue());
				}else if(entry.getValue() instanceof Double){
					iplm.setLastValDouble(Double.parseDouble(entry.getValue().toString()));
				}else{
					iplm.setLastValStr(entry.getValue().toString());
				}
				interfaceColConfigDao.saveEntityBean(iplm);
			}
			logger.info("记录本轮源表数据的最大值标记: " + sourceID);
		}
		
		AdapterUniqueRun aur=adapterUniqueRunDao.loadEntity(AdapterUniqueRun.class, sourceID);
		adapterUniqueRunDao.deleteAdapterUniqueRun(aur);
		logger.info("删除适配器数据库唯一判定标记: " + sourceID);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public int getTmpTableRecordsNum(InterfaceSource source, String tablename) {
		String tmpTableName=tablename+tmpTableAppend;	//临时存放数据表
		String sql="select count(*) from "+tmpTableName;
		List<Object> nums=targetDAO.executeSQL(sql, null);
		return Integer.parseInt(nums.get(0).toString());
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void execute(MsgContainer container){
		String msg = container.getMsg(); //获取待执行sql语句
		
		HashMap<String, Object> msgMap=paraseMsg(msg);
		HashMap<String, Object> fieldsMap=container.getFieldsHM();
		
		//老版本用的是SQLBody,新版本用的是sqlBody,因此需要忽略大小写通用
		String sql=getObjectIgnoreCase(msgMap, InterfaceConfig.sqlBody).toString();
		//String sql=msgMap.get(InterfaceConfig.sqlBody).toString();
		if(!sql.contains("(") || !sql.contains(")")){
			container.setCorrectFlag(false);
			container.setErrorMsg("SQL语句不正确: " + sql);
			return;
		}

		List<String> paramsList=new ArrayList<String>();
		List<Object> valuesList=new ArrayList<Object>();
		//拼装sql语句，并已参数形式传入变量和值
		for(Map.Entry<String, Object> entry : fieldsMap.entrySet()){
			if(entry.getValue()==null || entry.getValue().toString().toLowerCase().equals("null")){
				sql=sql.replace(InterfaceConfig.fieldStart+entry.getKey()+InterfaceConfig.fieldEnd, "null");
			}else{
				if(sql.contains(InterfaceConfig.fieldStart+entry.getKey()+InterfaceConfig.fieldEnd)){
					sql=sql.replace(InterfaceConfig.fieldStart+entry.getKey()+InterfaceConfig.fieldEnd, ":"+entry.getKey());
					paramsList.add(entry.getKey());
					Object valobj=entry.getValue();
					if(valobj!=null && valobj instanceof Clob) {
						Clob clob=(Clob)valobj;
						String clobStr="";
						try {
							Reader inStreamDoc =clob.getCharacterStream();
							char[] tempDoc = new char[(int) clob.length()];  
				            inStreamDoc.read(tempDoc);  
				            inStreamDoc.close();
				            clobStr=new String(tempDoc);
						}catch(Exception se) {
							logger.error(msg);
						}
						valuesList.add(clobStr);
					}else {
						valuesList.add(valobj);
					}
					
				}
			}
		}
		
		String[] params=paramsList.toArray(new String[paramsList.size()]);
		Object[] values=valuesList.toArray();
		
		logger.debug("SQL: " + sql);
		logger.debug("参数: " + Arrays.toString(params));
		logger.debug("传值: " + Arrays.toString(values));
		
		try{
			targetDAO.executeSQLInsert(sql, params, values);
		}catch(Exception e){
			logger.error("Error", e);
			container.setCorrectFlag(false);
			container.setErrorMsg(e.getMessage() + " | " + e.getCause().getMessage());
		}
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public int getMergeDeleteNums(InterfaceSource source, String tableName) {
		//修改传入参数 add by wangqidong
		String tmpTableName=tableName+tmpTableAppend;	//临时存放数据表
		List<String> delKeys=new ArrayList<String>();
		List<InterfaceColConfig> configList=interfaceColConfigDao.loadInterfaceColConfig(source.getSourceID());
		String mergeDelKeys="";
		for(InterfaceColConfig config:configList){
			if(config.getColType()==InterfaceConfig.mergeDelKey){
				//删除关键字，区分目标表
				if(config.getColname().contains(".")) {
					if(config.getColname().startsWith(tableName+".")) {
						delKeys.add(config.getColname().replace(tableName+".", ""));
						mergeDelKeys=mergeDelKeys+config.getColname()+"|";
					}
				}else {
					delKeys.add(config.getColname());
					mergeDelKeys=mergeDelKeys+config.getColname()+"|";
				}
			}
		}
		
		if(delKeys.size()==0){
			//如果没有复合主键配置，则不执行
			return 0;
		}
		
		//获取不是key的值，oracle必须要求merger matched之后，有update语句
		StringBuilder builder=new StringBuilder();
		
		builder.append("select count(*) from "+tableName+" t1 where exists (select 1 from " +tmpTableName + " t2 where ");
		
		for(int i=0;i<delKeys.size();i++){
			String delkey=delKeys.get(i);
			if(i==0 || (i==0 && i==delKeys.size()-1))
				builder.append(" t1."+delkey+"=t2."+delkey);
			else
				builder.append(" and t1."+delkey+"=t2."+delkey);
		}
		builder.append(")");
		List<Object> delNums=targetDAO.executeSQL(builder.toString(), null);
		return Integer.valueOf(delNums.get(0).toString());
	}
	
	private Object getObjectIgnoreCase(Map<String, Object> map,String key){
		for(String k:map.keySet()){
			if(k.equalsIgnoreCase(key)){
				return map.get(k);
			}
		}
		return null;
	}
	

	protected String getTableMsg(InterfaceSource source, String tmptablename){
		String sourcemsg="["+source.getMsg()+"]";
		//由于老的消息没有[],因此升级完多消息之后,还是在文本框里把[]去除,这样可以支持老的消息的格式
		List<SQLMsgTemplate> msgTmpList=JSONObject.parseArray(sourcemsg, SQLMsgTemplate.class);
		
		String msg="";
		for(SQLMsgTemplate msgtmp:msgTmpList) {
			String msgt=msgtmp.getSqlBody();
			if(msgt.contains(tmptablename)) {
				msg=msgt;
				break;
			}
		}
		return msg;
	}
}
