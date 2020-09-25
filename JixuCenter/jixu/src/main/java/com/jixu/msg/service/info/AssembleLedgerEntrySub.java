package com.jixu.msg.service.info;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgTemplate;
import com.jixu.msg.model.LedgerEntryModel;
import com.jixu.msg.service.impl.MsgExecuteService;

/** 
 * @ClassName 		AssembleCollat
 * @author  		Pan Wei 
 * @Date 			2016年8月22日 下午3:28:42 	
 * @Description		[]  
 */

@Service("ledgerEntrySub")
public class AssembleLedgerEntrySub extends MsgExecuteService{
	
	@Override
	public String msgHeader() {
		return MsgTemplate.LedgerEntrySub_Header;
	}
	
	@Override
	public String msgFields() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException {
		List<String> fields=msgcontainer.getFields();
		
		//Glacct,DrFunc,CrFunc,Cur(),DrLocal,CrLocal,FXRate
		
		String glacct=fields.get(0);
		
		String drfuncStr=fields.get(1);
		double drfunc=0;
		if(!drfuncStr.equals("")){
			drfunc=Double.valueOf(drfuncStr);
		}
		
		String crfuncStr=fields.get(2);
		double crfunc=0;
		if(!crfuncStr.equals("")){
			crfunc=Double.valueOf(crfuncStr);
		}
		
		genMsgContainer(MsgTemplate.CURRENCY_Header,fields.get(3),msgcontainer);
		String currency=null;
		if(msgcontainer.isCorrectFlag()){
			currency=msgcontainer.getEntityObj().toString();
		}else{
			return;
		}
		
		String drlocalStr=fields.get(4);
		double drlocal=0;
		if(!drlocalStr.equals("")){
			drlocal=Double.valueOf(drlocalStr);
		}
		
		String crlocalStr=fields.get(5);
		double crlocal=0;
		if(!crlocalStr.equals("")){
			crlocal=Double.valueOf(crlocalStr);
		}
		
		String fxrateStr=fields.get(6);
		double fxrate=0;
		if(!fxrateStr.equals("")){
			fxrate=Double.valueOf(fxrateStr);
		}
		
		LedgerEntryModel ledger=new LedgerEntryModel();
		ledger.setCrfunc(crfunc);
		ledger.setCrlocal(crlocal);
		ledger.setCur(currency);
		ledger.setDrfunc(drfunc);
		ledger.setDrlocal(drlocal);
		ledger.setFxrate(fxrate);
		ledger.setGlacct(glacct);
			
		msgcontainer.setEntityObj(ledger);
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
