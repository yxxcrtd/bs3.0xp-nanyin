package com.jixu.adapter.msg.service.config;

import java.util.HashMap;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.adapter.dao.intf.AdapterDao;
import com.jixu.adapter.msg.service.impl.MsgExecuteService;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.framework.msg.MsgError;
import com.orm.obj.DataSource;

/** 
 * @ClassName 		DataSourceMsg
 * @author  		Pan Wei 
 * @Date 			2016年8月12日 下午2:01:02 	
 * @Description		[数据库操作]  
 */
@Service
public class DataSourceMsg extends MsgExecuteService{
	
	@Autowired
	private AdapterDao adapterDao;
	
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
		
		String operation=getFieldValue(fieldsHM, MsgFields.operation);
		
		DataSource olddataSource=null;
		if(fieldsHM.containsKey(MsgFields.id)){
			int id=Integer.valueOf(Integer.valueOf(getFieldValue(fieldsHM, MsgFields.id)));
			olddataSource=adapterDao.loadEntity(DataSource.class, id);
			
			if(id>0&&operation.equals(delete)){
				if(olddataSource==null){
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, getFieldValue(fieldsHM, MsgFields.header),String.valueOf(delete)));
				}else{
					adapterDao.deleteEntity(olddataSource);
					msgcontainer.setErrorMsg("id:"+id+" 删除成功");
				}
				return;
			}
		}
		
		String connName=getFieldValue(fieldsHM, "connName");
		int connType=Integer.valueOf(getFieldValue(fieldsHM, "dbFormat"));
		String hostAddress=getFieldValue(fieldsHM, "dbURL");
		String dbName=getFieldValue(fieldsHM, "dbName");
		String userName=getFieldValue(fieldsHM, "userName");
		String userPassword=getFieldValue(fieldsHM, "userPassword");
		String connDesc=getFieldValue(fieldsHM, "connDesc");
		
		DataSource dataSource=new DataSource();
		dataSource.setConnName(connName);
		dataSource.setConnType(connType);
		dataSource.setDbName(dbName);
		dataSource.setHostAddress(hostAddress);
		dataSource.setUserName(userName);
		dataSource.setUserPassword(userPassword);
		dataSource.setConnDesc(connDesc);
		
		if(operation.toUpperCase().equals(add)){
			adapterDao.saveEntityBean(dataSource);
			msgcontainer.setObjID(dataSource.getId());
			msgcontainer.setEntityObj(dataSource);
			msgcontainer.setErrorMsg("id:"+dataSource.getId()+" 保存成功");
		}else{
			BeanUtils.copyProperties(dataSource, olddataSource, "id");
			adapterDao.updateEntityBean(olddataSource);
			msgcontainer.setEntityObj(olddataSource);
			if (olddataSource != null){
				msgcontainer.setObjID(olddataSource.getId());
				msgcontainer.setErrorMsg("id:"+olddataSource.getId()+" 修改成功");
			}
		}
	}

	@Override
	public String messageForm() {
		// TODO Auto-generated method stub
		return "/forms/config/bank_add_form";
	}
	
	public String messageName(){
		return "数据库操作";
	}
	
}
