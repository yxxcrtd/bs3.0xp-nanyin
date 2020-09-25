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
import com.orm.obj.BasicDataSet;

/** 
 * @ClassName 		BasicDataSetMsg
 * @author  		Wang Zhongyuan
 * @Date 			2018年12月11日 下午2:01:02 	
 * @Description		[基础数据集操作]  
 */
@Service
public class BasicDataSetMsg extends MsgExecuteService{
	
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
		
		BasicDataSet oldBds=null;
		if(fieldsHM.containsKey(MsgFields.id)){
			int id=Integer.valueOf(Integer.valueOf(getFieldValue(fieldsHM, MsgFields.id)));
			oldBds=adapterDao.loadEntity(BasicDataSet.class, id);
			
			if(operation.equals(delete)){
				if(oldBds==null){
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, getFieldValue(fieldsHM, MsgFields.header),String.valueOf(delete)));
				}else{
					adapterDao.deleteEntity(oldBds);
					msgcontainer.setErrorMsg("id:"+id+" 删除成功");
				}
				return;
			}
		}
		
		String name=getFieldValue(fieldsHM, "DSBasicName");
		int dataSourceId=Integer.valueOf(getFieldValue(fieldsHM, "dataSourceId"));
		String sql=getFieldValue(fieldsHM, "basicDataSql");
		
		BasicDataSet bds=new BasicDataSet();
		bds.setName(name);
		bds.setDataSourceId(dataSourceId);
		bds.setBasicDataSql(sql);
		
		
		if(operation.toUpperCase().equals(add)){
			adapterDao.saveEntityBean(bds);
			msgcontainer.setObjID(bds.getId());
			msgcontainer.setEntityObj(bds);
			msgcontainer.setErrorMsg("id:"+bds.getId()+" 保存成功");
		}else{
			BeanUtils.copyProperties(bds, oldBds, "id");
			adapterDao.updateEntityBean(oldBds);
			msgcontainer.setObjID(oldBds.getId());
			msgcontainer.setEntityObj(oldBds);
			msgcontainer.setErrorMsg("id:"+oldBds.getId()+" 修改成功");
		}
	}

	@Override
	public String messageForm() {
		return "";
	}
	
	public String messageName(){
		return "基础数据集操作";
	}
	
}
