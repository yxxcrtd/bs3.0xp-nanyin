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
import com.orm.obj.CashFlowPayment;

/** 
 * @ClassName 		CashFlowMsg
 * @author  		Pan Wei 
 * @Date 			2018年5月4日 下午3:51:39 	
 * @Description		[]  
 * @return  
 */

@Service
public class CashFlowPaymentMsg extends MsgExecuteService{
	
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
		
		CashFlowPayment oldcfp=configService.loadCashFlowPayment(id);
		if(operation.toUpperCase().equals(delete)){
			if(oldcfp==null){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6,getFieldValue(fieldsHM, MsgFields.header),String.valueOf(delete)));
			}else{
				configService.deleteCashFlowPayment(oldcfp);;
			}
			return;
		}
		
		
		String payDate=getFieldValue(fieldsHM, "settleDate");
		
		String cfID=getFieldValue(fieldsHM, "pid");
		
		
		CashFlowPayment cfp=new CashFlowPayment();
		cfp.setPayDate(DateUtil.convertStr2Date2(payDate));
		cfp.setCfid(cfID);
		
		
		if(operation.toUpperCase().equals(add)){
			configService.createCashFlowPayment(cfp);;
			msgcontainer.setObjID(cfp.getId());
			msgcontainer.setEntityObj(cfp);
		}else{
			oldcfp.setCfid(cfp.getCfid());
			oldcfp.setPayDate(cfp.getPayDate());
			configService.updateCashFlowPayment(oldcfp);;
			msgcontainer.setObjID(oldcfp.getId());
			msgcontainer.setEntityObj(oldcfp);
		}
	}
	
	
	@Override
	public String messageForm() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String messageName() {
		return "现金流付款计划消息";
	}
	
	@Override
	public String msgHeader() {
		// TODO Auto-generated method stub
		return null;
	}
}
