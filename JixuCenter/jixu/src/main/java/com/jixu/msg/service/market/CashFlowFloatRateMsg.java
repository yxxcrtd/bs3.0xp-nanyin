package com.jixu.msg.service.market;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.util.DateUtil;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.jixu.mvc.control.service.ConfigService;
import com.orm.obj.CashFlowFloatRate;

/** 
 * @ClassName 		CashFlowMsg
 * @author  		Pan Wei 
 * @Date 			2018年5月4日 下午3:51:39 	
 * @Description		[]  
 * @return  
 */

@Service
public class CashFlowFloatRateMsg extends MsgExecuteService{
	
	@Autowired
	private ConfigService configService;
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		HashMap<String, Object> fieldsHM=msgcontainer.getFieldsHM();
		
		String operation=getFieldValue(fieldsHM, MsgFields.operation);
		
		int id=0;
		if(fieldsHM.containsKey(MsgFields.id)){
			id=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.id));
		}
		CashFlowFloatRate oldCfcFloatRate=configService.loadCashFlowFloatRate(id);
		if(operation.toUpperCase().equals(delete)){
			if(oldCfcFloatRate==null){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6,getFieldValue(fieldsHM, MsgFields.header),String.valueOf(delete)));
			}else{
				configService.deleteCashFlowFloatRate(oldCfcFloatRate);
			}
			return;
		}
		
		String resetDate=getFieldValue(fieldsHM, "resetDate");
		
		double maxRate=Double.valueOf(getFieldValue(fieldsHM, MsgFields.max));
		
		double minRate=Double.valueOf(getFieldValue(fieldsHM, MsgFields.min));
		
		String basicClassID=getFieldValue(fieldsHM, "basisClass");
		
		String basicClass=commService.getFloatRateIDClassMap().get(basicClassID);
		
		String cfID=getFieldValue(fieldsHM, "pid");
		
		String rule=getFieldValue(fieldsHM, "rule");
		
		
		CashFlowFloatRate cffr=new CashFlowFloatRate();
		cffr.setCfID(cfID);
		cffr.setRateResetDate(DateUtil.convertStr2Date2(resetDate));
		cffr.setMaxRate(maxRate);
		cffr.setMinRate(minRate);
		cffr.setBasisClass(basicClass);
		cffr.setRuleParams(rule);
		
		if(operation.toUpperCase().equals(add)){
			configService.createCashFlowFloatRate(cffr);;
			msgcontainer.setObjID(cffr.getId());
			msgcontainer.setEntityObj(cffr);
		}else{
			oldCfcFloatRate.setRateResetDate(cffr.getRateResetDate());
			oldCfcFloatRate.setMaxRate(cffr.getMaxRate());
			oldCfcFloatRate.setMinRate(cffr.getMinRate());
			oldCfcFloatRate.setBasisClass(cffr.getBasisClass());
			oldCfcFloatRate.setRuleParams(cffr.getRuleParams());
			oldCfcFloatRate.setCfID(cffr.getCfID());
			configService.updateCashFlowFloatRate(oldCfcFloatRate);
			msgcontainer.setObjID(oldCfcFloatRate.getId());
			msgcontainer.setEntityObj(oldCfcFloatRate);
		}
	}
	
	
	@Override
	public String messageForm() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String messageName() {
		return "现金流利率消息";
	}
	
	@Override
	public String msgHeader() {
		// TODO Auto-generated method stub
		return null;
	}
}
