package com.jixu.msg.service.config;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.util.JsonUtil;
import com.jixu.dao.intf.TranFeeTypeMapDao;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.orm.obj.TranFeeDef;
import com.orm.obj.TranFeeTypeMap;

/** 
 * @ClassName 		TaxFeeMsg
 * @author  		Pan Wei 
 * @Date 			2018年5月11日 下午1:33:12 	
 * @Description		[]  
 */

@Service
public class TranFeeConfigMsg extends MsgExecuteService{
	
	@Autowired
	private TranFeeTypeMapDao tranFeeTypeMapDao;
	
	@Override
	public String msgHeader() {
		return "ttranFeeConfigMsg";
	}
	
	@Override
	protected void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		HashMap<String, Object> fieldsHM=msgcontainer.getFieldsHM();
		
		TranFeeDef oldFeeDef=null;
		if(fieldsHM.containsKey(MsgFields.id)){
			int feeid=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.id));
			oldFeeDef=tranFeeDefDao.loadEntity(TranFeeDef.class, feeid);;
		}
		
		String operation=getFieldValue(fieldsHM, MsgFields.operation);
		if(operation.toUpperCase().equals(delete)){
			tranFeeDefDao.deleteEntity(oldFeeDef);
		}else{
			TranFeeDef feeDef=new TranFeeDef();
			feeDef.setFeename(getFieldValue(fieldsHM, MsgFields.name));
			feeDef.setOneTimeFeeFlag(Integer.valueOf(getFieldValue(fieldsHM, MsgFields.type)));
			
			String expenseRatioStr=getFieldValue(fieldsHM, "expenseRatio");
			double expenseRatio=0;
			if(expenseRatioStr!=null && !expenseRatioStr.equals("")){
				expenseRatio=Double.valueOf(expenseRatioStr);
			}
			feeDef.setUnCapitalizedPercent(expenseRatio);
			
			String cashRatioStr=getFieldValue(fieldsHM, "cashRatio");
			double cashRatio=0;
			if(cashRatioStr!=null && !cashRatioStr.equals("")){
				cashRatio=Double.valueOf(cashRatioStr);
			}
			feeDef.setCashPercent(cashRatio);
			
			feeDef.setCostGlCol(Integer.valueOf(getFieldValue(fieldsHM, "principalGL")));
		    feeDef.setFeeGlCol(Integer.valueOf(getFieldValue(fieldsHM, "expenseGL")));
		    feeDef.setSecPayGlCol(Integer.valueOf(getFieldValue(fieldsHM, "settleGL")));
		    feeDef.setRcpyGlCol(Integer.valueOf(getFieldValue(fieldsHM, "rcpyGL")));
		    feeDef.setFixedFlag(Integer.valueOf(getFieldValue(fieldsHM, "rateType")));
		    feeDef.setStatus(Integer.valueOf(getFieldValue(fieldsHM, "status")));
		    
		    String cashflowStr=getFieldValue(fieldsHM, "cf");
		    if(cashflowStr!=null && !cashflowStr.equals("")){
		    	feeDef.setCashFlowID(cashflowStr);	
		    }
		    
		    Object types=fieldsHM.get("assetTypes");
		    List<Object> tranTypesList=JsonUtil.stringToList(types.toString());
		    
			if(operation.toUpperCase().equals(add)){
				tranFeeDefDao.saveEntity(feeDef);
				msgcontainer.setObjID(feeDef.getId());
				msgcontainer.setEntityObj(feeDef);
				
				for(Object obj:tranTypesList){
			    	String assettype=obj.toString();
			    	
			    	TranFeeTypeMap typeMap=new TranFeeTypeMap();
			    	typeMap.setAssetType(assettype);
			    	typeMap.setTranFeeTypeCode(feeDef);
			    	tranFeeDefDao.saveEntity(typeMap);
			    }
			}else if(operation.toUpperCase().equals(update)){
				BeanUtils.copyProperties(feeDef, oldFeeDef, "id");
				
				tranFeeDefDao.updateEntity(oldFeeDef);
				msgcontainer.setObjID(oldFeeDef.getId());
				msgcontainer.setEntityObj(oldFeeDef);
				
				tranFeeTypeMapDao.deleteTranFeeDefMapByFeeID(oldFeeDef.getId());
				
				for(Object obj:tranTypesList){
			    	String assettype=obj.toString();
			    	
			    	TranFeeTypeMap typeMap=new TranFeeTypeMap();
			    	typeMap.setAssetType(assettype);
			    	typeMap.setTranFeeTypeCode(oldFeeDef);
			    	tranFeeTypeMapDao.saveEntity(typeMap);
			    }
			}
			
			
		}
	}
	
	@Override
	public String messageForm() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return null;
	}
}
