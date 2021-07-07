package com.jixu.msg.service.config;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.util.ReturnResult;
import com.jixu.dao.intf.BkInteAcctDao;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.orm.obj.BkInfo;
import com.orm.obj.BkInteAcct;
import com.orm.obj.CashFlowCfg;

/** 
 * @ClassName 		AssembleBNK
 * @author  		Pan Wei 
 * @Date 			2016年8月12日 下午2:01:02 	
 * @Description		[返回关联银行账户]  
 */
@Service
public class BankInterestMsg extends MsgExecuteService{
	
	@Autowired
	private BkInteAcctDao bkInteAcctDao;
	
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
		
		int inteID=0;
		BkInteAcct oldBkInteAcct=null;
		if(fieldsHM.containsKey(MsgFields.id)){
			inteID=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.id));
			oldBkInteAcct=bkInteAcctDao.loadEntity(BkInteAcct.class, inteID);
		}
		
		String name=getFieldValue(fieldsHM, "name");
		
		String bank=getFieldValue(fieldsHM, "bank");
		
		Integer delayDays=Integer.valueOf(getFieldValue(fieldsHM, "delayDays"));
		
		Integer settleMethod=Integer.valueOf(getFieldValue(fieldsHM, "settleMethod"));
		
		Integer dayCounts=Integer.valueOf(getFieldValue(fieldsHM, "dayCounts"));
		
		Integer period=Integer.valueOf(getFieldValue(fieldsHM, "period"));
		
		Integer frequency=Integer.valueOf(getFieldValue(fieldsHM, "frequency"));
		
		Integer month=0;
		if(fieldsHM.containsKey("month")){
			month=Integer.valueOf(getFieldValue(fieldsHM, "month"));
		}
		Integer day=Integer.valueOf(getFieldValue(fieldsHM, "day"));
		
		
		CashFlowCfg cashFlowCfg=new CashFlowCfg();
		cashFlowCfg.setCpnDelayDateNum(delayDays);
		cashFlowCfg.setCpnDelayDateType(settleMethod);
		cashFlowCfg.setIntCode(dayCounts);
		cashFlowCfg.setCpnFreq(frequency);
		cashFlowCfg.setCpnMo(month);
		cashFlowCfg.setCpnDate(day);
		
		BkInteAcct bkInteAcct=new BkInteAcct();
		bkInteAcct.setCashflowtype("I");
		bkInteAcct.setIntperiodconv(period);
		bkInteAcct.setMasterrecname(name);
		bkInteAcct.setBkinfo(new BkInfo(bank));
		bkInteAcct.setCashFlowCfg(cashFlowCfg);
		
		if(operation.toUpperCase().equals(add)){
			boolean checkNameExist=bkInteAcctDao.checkInteAcctNameExist(bkInteAcct.getBkinfo().getBankID(), bkInteAcct.getMasterrecname());
			if(checkNameExist){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorCode(ReturnResult.bkInteAcct_name_exist);
				msgcontainer.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.bkInteAcct_name_exist) + ": " + bkInteAcct.getMasterrecname());
			}
			cashFlowCfgDao.saveEntity(cashFlowCfg);
			bkInteAcctDao.saveEntity(bkInteAcct);
			msgcontainer.setObjID(bkInteAcct.getId());
			msgcontainer.setEntityObj(bkInteAcct);
		}else{
			if (oldBkInteAcct != null){
				oldBkInteAcct.setIntperiodconv(bkInteAcct.getIntperiodconv());
				oldBkInteAcct.setMasterrecname(bkInteAcct.getMasterrecname());
				oldBkInteAcct.setBkinfo(bkInteAcct.getBkinfo());
				bkInteAcctDao.updateEntity(oldBkInteAcct);

				CashFlowCfg oldCashFlowCfg=oldBkInteAcct.getCashFlowCfg();
				oldCashFlowCfg.setCpnDelayDateNum(cashFlowCfg.getCpnDelayDateNum());
				oldCashFlowCfg.setCpnDelayDateType(cashFlowCfg.getCpnDelayDateType());
				oldCashFlowCfg.setIntCode(cashFlowCfg.getIntCode());
				oldCashFlowCfg.setCpnFreq(cashFlowCfg.getCpnFreq());
				oldCashFlowCfg.setCpnMo(cashFlowCfg.getCpnMo());
				oldCashFlowCfg.setCpnDate(cashFlowCfg.getCpnDate());
				cashFlowCfgDao.updateEntity(oldCashFlowCfg);

				msgcontainer.setObjID(oldBkInteAcct.getId());
				msgcontainer.setEntityObj(oldBkInteAcct);
			}
		}
	}

	@Override
	public String messageForm() {
		return "/forms/config/bankInterest_add_form";
	}
	
	public String messageName(){
		return "银行利息操作";
	}
	
}
