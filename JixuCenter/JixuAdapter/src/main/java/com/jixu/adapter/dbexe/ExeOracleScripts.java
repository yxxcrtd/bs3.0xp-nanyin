package com.jixu.adapter.dbexe;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.common.pojo.InterfaceColConfig;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.InterfaceConfig;

@Component
public class ExeOracleScripts extends ExecuteDBAbstract{
	
	@Override
	public int dbType() {
		return InterfaceConfig.oracle;
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor=Exception.class)
	public void exeMergerData(InterfaceSource source, String tableName) throws Exception{
		String tmpTableName=tableName+tmpTableAppend;	//临时存放数据表
		
		String sql="select count(*) from " + tmpTableName;
		List<Object> sourcerecordsList=targetDAO.executeSQL(sql, null);
		int sourceRecords=Integer.parseInt(sourcerecordsList.get(0).toString());
		logger.info(source.getSourceName() + "		临时表数据量: " + sourceRecords);
		
		//获取字段名
		List<String> headers=getTargetColumns(source, tableName);
		
		List<String> keys=new ArrayList<String>();
		List<InterfaceColConfig> configList=interfaceColConfigDao.loadInterfaceColConfig(source.getSourceID());
		
		boolean mergeDisNull=true;
		for(InterfaceColConfig config:configList){
			if(config.getColType()==InterfaceConfig.mergeKey || config.getColType()==InterfaceConfig.mergeKeyNullEnable){
				if(config.getColname().contains(".")) {
					if(config.getColname().startsWith(tableName+".")) {
						keys.add(config.getColname().replace(tableName+".", ""));
					}
				}else {
					keys.add(config.getColname());
				}
			}
			
			if(config.getColType()==InterfaceConfig.mergeKeyNullEnable)
				mergeDisNull=false;
		}
		
		String executesql="";
		if(keys.size()==0){
			executesql="insert into " + tableName + " (select * from "+ tmpTableName +")";
		}else{
			StringBuilder builder=new StringBuilder();
			//每个字段名
			int headernum=headers.size();
			
			builder.append("MERGE into "+tableName+" TARGET \r\n");
			builder.append("USING "+tmpTableName+" SOURCE \r\n");
			builder.append("	ON ( \r\n");
			
			for(int i=0;i<keys.size();i++){
				String key=keys.get(i);
				if(i==0)
					builder.append("	TARGET."+key+"=SOURCE."+key+" \r\n");
				else
					builder.append("	and TARGET."+key+"=SOURCE."+key+" \r\n");
			}
			
			builder.append("	) \r\n");
			
			if(checkUpdateOrIgnore(source)) {	
				builder.append("when matched \r\n");
				builder.append("	then \r\n");
				builder.append("		update \r\n");
				builder.append("		set \r\n");
				
				//获取需要更新的字段
				List<String> updateColumns=new ArrayList<String>();
				for(int i=0;i<headers.size();i++){
					String header=headers.get(i);
					if(keys.contains(header)){
						//如果key包括,则不更新
						continue;
					}
					updateColumns.add(header);
				}
				
				int columnnum=updateColumns.size();
				for(int i=0;i<updateColumns.size();i++){
					String columnname=updateColumns.get(i);
					if(mergeDisNull) {
						if(i!=columnnum-1 && columnnum>1)
							builder.append("	TARGET."+columnname+"=NVL(SOURCE."+columnname+",TARGET."+columnname+"), \r\n");
						else
							builder.append("	TARGET."+columnname+"=NVL(SOURCE."+columnname+",TARGET."+columnname+") \r\n");
					}else {
						if(i!=columnnum-1 && columnnum>1)
							builder.append("	TARGET."+columnname+"=SOURCE."+columnname+", \r\n");
						else
							builder.append("	TARGET."+columnname+"=SOURCE."+columnname+" \r\n");
					}
				}
			}
			
			builder.append("when not matched \r\n");
			builder.append("	then \r\n");
			builder.append("		insert ( \r\n");
			
			for(int i=0;i<headers.size();i++){
				String header=headers.get(i);
				if(i!=headernum-1 && headernum>1)
					builder.append("	"+header+", \r\n");
				else
					builder.append("	"+header+" \r\n");
			}
			
			builder.append("		) values ( \r\n");
			
			for(int i=0;i<headers.size();i++){
				String header=headers.get(i);
				if(i!=headernum-1 && headernum>1)
					builder.append("	SOURCE."+header+", \r\n");
				else
					builder.append("	SOURCE."+header+" \r\n");
			}
			
			builder.append("		)");
		
			executesql=builder.toString();
		}
		
		int exerecords=targetDAO.executeSQLInsert(executesql);
		logger.info(source.getSourceName() + "		目标表更新数据量: " + exerecords);
		if(checkUpdateOrIgnore(source)) {
			if(sourceRecords!=exerecords){
				String errormsg="临时表数量与目标表处理数量不一致，回滚数据";
				logger.info(errormsg);
				logger.info(executesql);
				throw new SQLException(errormsg);
			}
		}else {
			int ignorenum=sourceRecords-exerecords;
			logger.info(source.getSourceName() + "		目标表过滤数据量: " + ignorenum);
		}
		//清空临时表
		//cleanTmpTableRecords(source);
	}
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void cleanTmpTableRecords(String tableName) {
		String tmpTableName=tableName+tmpTableAppend;	//临时存放数据表
		String delSQL="truncate table "+tmpTableName;
		targetDAO.executeSQLUpdate(delSQL);
	}
	
	/**
	 * 在复合主键下，发现目标数据库有数据存在，则先全部删除，再做插入。
	 * 使用场景：撤账之后重新做账，
	 * @param source
	 */
	@Override
	@Transactional(value="jixuTXManager", rollbackFor=Exception.class)
	public void exeMergerDeleteData(InterfaceSource source, String tableName) {
		//edit by wangqidong
		String tmpTableName=tableName+tmpTableAppend;	//临时存放数据表
		
		List<String> delKeys=new ArrayList<String>();
		List<InterfaceColConfig> configList=interfaceColConfigDao.loadInterfaceColConfig(source.getSourceID());
		String mergeDelKeys="";
		for(InterfaceColConfig config:configList){
			if(config.getColType()==InterfaceConfig.mergeDelKey){
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
			return;
		}
		
		logger.info(source.getSourceName()+"       删除记录,复合主键: " + mergeDelKeys);
		
		//获取不是key的值，oracle必须要求merger matched之后，有update语句
		StringBuilder builder=new StringBuilder();
		
		builder.append("delete from "+tableName+" t1 where exists (select 1 from " +tmpTableName + " t2 where ");
		
		for(int i=0;i<delKeys.size();i++){
			String delkey=delKeys.get(i);
			if(i==0 || (i==0 && i==delKeys.size()-1))
				builder.append(" t1."+delkey+"=t2."+delkey);
			else
				builder.append(" and t1."+delkey+"=t2."+delkey);
		}
		builder.append(")");
		targetDAO.executeSQLUpdate(builder.toString());
	}
	
	/**
	 * 判断关键词相同是更新还是忽略
	 * true 更新
	 * false 忽略
	 */
	private boolean checkUpdateOrIgnore(InterfaceSource source) {
		String checkFlag=source.getExpSourceClass();
		//02表示忽略
		if(checkFlag!=null && checkFlag.equals("02")) {
			return false;
		}else {
			return true;
		}
	}
}
