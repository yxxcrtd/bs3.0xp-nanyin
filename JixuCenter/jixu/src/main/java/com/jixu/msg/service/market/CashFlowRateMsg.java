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
import com.orm.obj.CashFlowRate;

/** 
 * @ClassName 		CashFlowMsg
 * @author  		Pan Wei 
 * @Date 			2018年5月4日 下午3:51:39 	
 * @Description		[]  
 * @return  
 */

@Service
public class CashFlowRateMsg extends MsgExecuteService{
	
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
		CashFlowRate oldcfcRate=configService.loadCashFlowRate(id);
		if(operation.toUpperCase().equals(delete)){
			if(oldcfcRate==null){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6,getFieldValue(fieldsHM, MsgFields.header),String.valueOf(delete)));
			}else{
				configService.deleteCashFlowRate(oldcfcRate);
			}
			return;
		}
		
		String startDate=getFieldValue(fieldsHM, "startDate");
		
		double cfRate=Double.valueOf(getFieldValue(fieldsHM, "rate"));
		
		String cfID=getFieldValue(fieldsHM, "pid");
		
		
		CashFlowRate cfcRate=new CashFlowRate();
		cfcRate.setCfDate(DateUtil.convertStr2Date2(startDate));
		cfcRate.setCfRate(cfRate);
		cfcRate.setCfid(cfID);
		
		if(operation.toUpperCase().equals(add)){
			configService.createCashFlowRate(cfcRate);
			msgcontainer.setObjID(cfcRate.getId());
			msgcontainer.setEntityObj(cfcRate);
		}else{
			oldcfcRate.setCfDate(cfcRate.getCfDate());
			oldcfcRate.setCfRate(cfcRate.getCfRate());
			oldcfcRate.setCfid(cfcRate.getCfid());
			configService.updateCashFlowRate(oldcfcRate);
			msgcontainer.setObjID(oldcfcRate.getId());
			msgcontainer.setEntityObj(oldcfcRate);
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
