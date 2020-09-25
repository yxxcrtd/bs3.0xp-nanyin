package com.jixu.adapter.msg.service.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jixu.adapter.msg.service.impl.MsgExecuteService;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;

/** 
 * @ClassName 		AssembleBNK
 * @author  		Pan Wei 
 * @Date 			2016年8月12日 下午2:01:02 	
 * @Description		[返回关联银行账户]  
 */
@Service
public class ExecuteSQLMsg extends MsgExecuteService{
	
	private static final Logger logger = LoggerFactory.getLogger(ExecuteSQLMsg.class);
	
	@Override
	public String msgHeader() {
		return null;
	}
	
	@Override
	public String msgFields() {
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		HashMap<String, Object> fieldsHM=msgcontainer.getFieldsHM();
		
		//老版本用的是SQLBody,新版本用的是sqlBody,因此需要忽略大小写通用
		String sql=getObjectIgnoreCase(fieldsHM, InterfaceConfig.sqlBody).toString();
		//String sql=fieldsHM.get(InterfaceConfig.sqlBody).toString();
		if(!sql.contains("(") || !sql.contains(")")){
			logger.error("SQL语句不正确: " + sql);
			return;
		}
		String columns=sql.substring(sql.indexOf("(")+1, sql.length());
		columns=columns.substring(0,columns.indexOf(")"));
		
		String values=sql.substring(0, sql.lastIndexOf(")"));
		values=sql.substring(values.lastIndexOf("(")+1, values.length());
		
		String[] colArr=columns.split(",");
		String[] valueArr=values.split(",");
		
		if(colArr.length!=valueArr.length){
			logger.error("字段和值数量不符合");
			return;
		}
		
		if(fieldsHM.containsKey("Keys") && !fieldsHM.get("Keys").toString().equals("") 
				&& fieldsHM.containsKey("Action") && !fieldsHM.get("Action").toString().equals("")){
			HashMap<String, String> keysHM=new HashMap<String, String>();
			String keys=fieldsHM.get("Keys").toString();
			if(!keys.trim().equals("")){
				String[] keysArr=keys.split(",");
				for(int i=0;i<colArr.length;i++){
					for(String key:keysArr){
						if(key.trim().toLowerCase().equals(colArr[i].trim().toLowerCase())){
							keysHM.put(colArr[i].trim(), valueArr[i].trim());
						}
					}
				}
			}
			
			//获得keys
			if(keysHM.size()>0){
				String tablename=sql.substring(0, sql.indexOf("(")).trim();
				tablename=tablename.substring(tablename.lastIndexOf(" "), tablename.length());
				String sql1="select count(*) ";
				String sql2=" from " + tablename + " where ";
				String[] params=new String[keysHM.size()];
				String[] sqlValues=new String[keysHM.size()];
				
				for(Map.Entry<String, String> entry : keysHM.entrySet()){
					sql2=sql2+" " + entry.getKey()+"=:"+entry.getKey() + " and";
					params[0]=entry.getKey();
					sqlValues[0]=entry.getValue();
				}
				sql2=sql2.substring(0, sql2.length()-3);
				List<Object> objList=interfaceSourceDao.executeSQL(sql1+sql2, params, sqlValues, null);
				if(objList.size()>0){
					String action=fieldsHM.get("Action").toString().toLowerCase().trim();
					if(action.equals("s")){
						return;
					}else if(action.equals("u")){
						String sql4="delete " + sql2;
						interfaceSourceDao.executeSQLdelete(sql4, params, sqlValues);
						interfaceSourceDao.executeSQLInsert(sql);
						return;
					}else if(action.equals("c")){
						interfaceSourceDao.executeSQLInsert(sql);
						return;
					}
				}
				
			}
			
			
		}
		
		interfaceSourceDao.executeSQLInsert(sql);
	}

	@Override
	public String messageForm() {
		// TODO Auto-generated method stub
		return "/forms/config/bank_add_form";
	}
	
	public String messageName(){
		return "执行SQL脚本";
	}
	
	private Object getObjectIgnoreCase(Map<String, Object> map,String key){
		for(String k:map.keySet()){
			if(k.equalsIgnoreCase(key)){
				return map.get(k);
			}
		}
		return null;
	}
	
}
