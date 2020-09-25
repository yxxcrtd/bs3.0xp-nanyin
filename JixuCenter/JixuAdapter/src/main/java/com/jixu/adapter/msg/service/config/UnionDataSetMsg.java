package com.jixu.adapter.msg.service.config;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.adapter.dao.intf.AdapterDao;
import com.jixu.adapter.msg.service.impl.MsgExecuteService;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.framework.msg.MsgError;
import com.orm.obj.AdapterColConfig;
import com.orm.obj.DataSetColTemp;
import com.orm.obj.UnionDataSet;

/** 
 * @ClassName 		UnionDataSetMsg
 * @author  		Wang Zhongyuan
 * @Date 			2018年12月11日 下午2:01:02 	
 * @Description		[联合数据集操作]  
 */
@Service
public class UnionDataSetMsg extends MsgExecuteService{
	
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
		
		UnionDataSet oldUds=null;
		if(fieldsHM.containsKey(MsgFields.id)){
			int id=Integer.valueOf(Integer.valueOf(getFieldValue(fieldsHM, MsgFields.id)));
			oldUds=adapterDao.loadEntity(UnionDataSet.class, id);
			
			if(operation.equals(delete)){
				if(oldUds==null){
					msgcontainer.setCorrectFlag(false);
					msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, getFieldValue(fieldsHM, MsgFields.header),String.valueOf(delete)));
				}else{
					adapterDao.deleteEntity(oldUds);
					//删除临时表增强列数据
					List<DataSetColTemp> tempList=adapterDao.loadDataSetColTemp(id,new int[]{InterfaceConfig.advanceType});
					if(tempList!=null&&tempList.size()>0){
						for (DataSetColTemp dst : tempList) {
							adapterDao.deleteEntity(dst);
						}
					}
					//删除adaptercolconfig表数据
					List<AdapterColConfig> colcfgList=adapterDao.loadAdapterColConfig(id,new int[]{InterfaceConfig.advanceType});
					if(colcfgList!=null&&colcfgList.size()>0){
						for (AdapterColConfig acc : colcfgList) {
							adapterDao.deleteEntity(acc);
						}
					}
					msgcontainer.setErrorMsg("id:"+id+" 删除成功");
				}
				return;
			}
		}
		
		String name=getFieldValue(fieldsHM, "dsName");
		int basicDataSetId=Integer.valueOf(getFieldValue(fieldsHM, "basicDataSetId"));
		
		UnionDataSet uds=new UnionDataSet();
		uds.setName(name);
		uds.setBasicDataSetId(basicDataSetId);
		
		if(operation.toUpperCase().equals(add)){
			adapterDao.saveEntityBean(uds);
			msgcontainer.setObjID(uds.getId());
			msgcontainer.setEntityObj(uds);
			msgcontainer.setErrorMsg("id:"+uds.getId()+" 保存成功");
		}else{
			BeanUtils.copyProperties(uds, oldUds, "id");
			adapterDao.updateEntityBean(oldUds);
			msgcontainer.setObjID(oldUds.getId());
			msgcontainer.setEntityObj(oldUds);
			msgcontainer.setErrorMsg("id:"+oldUds.getId()+" 修改成功");
		}
	}

	@Override
	public String messageForm() {
		return "";
	}
	
	public String messageName(){
		return "联合数据集操作";
	}
	
}
