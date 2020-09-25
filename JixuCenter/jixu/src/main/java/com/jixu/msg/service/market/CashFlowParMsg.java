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
import com.orm.obj.CashFlowCfg;
import com.orm.obj.CashFlowPar;

/** 
 * @ClassName 		CashFlowParMsg
 * @author  		Pan Wei 
 * @Date 			2018年5月4日 下午3:51:39 	
 * @Description		[]  
 * @return  
 */

@Service
public class CashFlowParMsg extends MsgExecuteService{
	
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
		
		CashFlowPar oldcfp=configService.loadCashFlowPar(id);
		if(operation.toUpperCase().equals(delete)){
			if(oldcfp==null){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6,getFieldValue(fieldsHM, MsgFields.header),String.valueOf(delete)));
			}else{
				configService.deleteCashFlowPar(oldcfp);;
			}
			return;
		}
		
		
		String parDate=getFieldValue(fieldsHM, "startDate").replaceAll("-", "");
		checkDateFormat(msgcontainer,msgHeader(),parDate);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		String settleDate=getFieldValue(fieldsHM, MsgFields.settleDate).replaceAll("-", "");
		checkDateFormat(msgcontainer,msgHeader(),settleDate);
		if(!msgcontainer.isCorrectFlag())
			return;
		
		int tranType=Integer.valueOf(getFieldValue(fieldsHM, "parMethod"));
		
		String cfID=getFieldValue(fieldsHM, "pid");
		
		double parRate=Double.valueOf(getFieldValue(fieldsHM, "parPayment"));
		
		
		CashFlowPar cfp=new CashFlowPar();
		cfp.setPaymentDate(DateUtil.convertStr2Date(parDate));
		cfp.setSettleDate(DateUtil.convertStr2Date(settleDate));
		cfp.setPaymentRate(parRate);
		cfp.setTranType(tranType);
		cfp.setCfid(cfID);
		
		
		if(operation.toUpperCase().equals(add)){
			configService.createCashFlowPar(cfp);;
			msgcontainer.setObjID(cfp.getId());
			msgcontainer.setEntityObj(cfp);
		}else{
			oldcfp.setCfid(cfp.getCfid());
			oldcfp.setPaymentDate(cfp.getPaymentDate());
			oldcfp.setPaymentRate(cfp.getPaymentRate());
			configService.updateCashFlowPar(oldcfp);;
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
