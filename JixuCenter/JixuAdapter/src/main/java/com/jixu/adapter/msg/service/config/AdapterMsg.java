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
import com.orm.obj.Adapter;

/** 
 * @ClassName 		AdapterMsg
 * @author  		Wang Zhongyuan
 * @Date 			2018年12月11日 下午2:01:02 	
 * @Description		[适配器操作]  
 */
@Service
public class AdapterMsg extends MsgExecuteService{
	
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
		
		Adapter oldAdapter=null;
		if(fieldsHM.containsKey(MsgFields.id)){
			int id=Integer.valueOf(Integer.valueOf(getFieldValue(fieldsHM, MsgFields.id)));
			oldAdapter=adapterDao.loadEntity(Adapter.class, id);
			
			if(operation.equals(delete)){
				if(oldAdapter==null){
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, getFieldValue(fieldsHM, MsgFields.header),String.valueOf(delete)));
				}else{
					adapterDao.deleteEntity(oldAdapter);
					msgcontainer.setErrorMsg("id:"+id+" 删除成功");
				}
				return;
			}
		}
		
		String name=getFieldValue(fieldsHM, "daName");
		int basicDataSetId=Integer.valueOf(getFieldValue(fieldsHM, "sourceDsId"));
		int unionDataSetId=Integer.valueOf(getFieldValue(fieldsHM, "targetDsId"));
		String adapterSql=getFieldValue(fieldsHM, "SQLstatement");
		
		Adapter adapter=new Adapter();
		adapter.setAdapterSql(adapterSql);
		adapter.setBasicDataSetId(basicDataSetId);
		adapter.setName(name);
		adapter.setUnionDataSetId(unionDataSetId);
		
		if(operation.toUpperCase().equals(add)){
			adapterDao.saveEntityBean(adapter);
			msgcontainer.setObjID(adapter.getId());
			msgcontainer.setEntityObj(adapter);
			msgcontainer.setErrorMsg("id:"+adapter.getId()+" 保存成功");
		}else{
			if (oldAdapter != null){
				BeanUtils.copyProperties(adapter, oldAdapter, "id");
				adapterDao.updateEntityBean(oldAdapter);
				msgcontainer.setObjID(oldAdapter.getId());
				msgcontainer.setEntityObj(oldAdapter);
				msgcontainer.setErrorMsg("id:"+oldAdapter.getId()+" 修改成功");
			}
		}
	}

	@Override
	public String messageForm() {
		return "";
	}
	
	public String messageName(){
		return "适配器操作";
	}
	
}
