package com.jixu.msg.service.config;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.jixu.mvc.control.service.ConfigService;
import com.orm.obj.ManagerGroup;
import com.orm.obj.Managers;

/** 
 * @ClassName 		AssembleBNK
 * @author  		Pan Wei 
 * @Date 			2016年8月12日 下午2:01:02 	
 * @Description		[返回关联银行账户]  
 */
@Service
public class ManagerMsg extends MsgExecuteService{
	
	@Resource(name="configService")
	private ConfigService configService;
	
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
		
		int managerID=0;
		if(fieldsHM.containsKey(MsgFields.id)){
			managerID=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.id));
		}
		if(operation.toUpperCase().equals(delete)){
			if(managerID==0){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, getFieldValue(fieldsHM, MsgFields.header),String.valueOf(delete)));
			}else{
				configService.removeManager(managerID);
			}
			return;
		}
		
		String shortName=getFieldValue(fieldsHM, "shortName");
		
		String name=getFieldValue(fieldsHM, MsgFields.name);
		
		int mgrGroupID=Integer.valueOf(getFieldValue(fieldsHM, "mgrGroupID"));
		
		
		Managers manager=new Managers();
		manager.setShortCode(shortName);
		manager.setManagerName(name);
		manager.setManagerGroup(mgrGroupID);
		
		if(operation.toUpperCase().equals(add)){
			configService.createManager(manager);
			msgcontainer.setObjID(manager.getId());
			msgcontainer.setEntityObj(manager);
		}else{
			manager.setId(managerID);
			configService.updateManager(manager);
			msgcontainer.setObjID(manager.getId());
			msgcontainer.setEntityObj(manager);
		}
	}

	@Override
	public String messageForm() {
		// TODO Auto-generated method stub
		return "/forms/config/manager_add_form";
	}
	
	public String messageName(){
		return "基金经理操作";
	}
	
}
