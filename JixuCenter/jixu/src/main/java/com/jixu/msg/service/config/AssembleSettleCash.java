package com.jixu.msg.service.config;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgTemplate;
import com.jixu.msg.model.SettleCash;
import com.jixu.msg.service.impl.MsgExecuteService;

/** 
 * @ClassName 		AssembleSettleCash
 * @author  		Pan Wei 
 * @Date 			2016年8月12日 下午4:22:22 	
 * @Description		[]  
 * @msg				SETTLECASH(Cur(),FXRate,SettlementCash)
 */

@Service("settleCashMsg")
public class AssembleSettleCash extends MsgExecuteService {
	
	@Override
	public String msgHeader() {
		return MsgTemplate.Settle_Header;
	}
	
	@Override
	public String msgFields() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException{
		List<String> fields = msgcontainer.getFields();

		//外汇货币
		genMsgContainer(MsgTemplate.CURRENCY_Header,fields.get(0),msgcontainer);
		String currency="";
		if(msgcontainer.isCorrectFlag()){
			currency=msgcontainer.getEntityObj().toString();
		}else{
			return;
		}
	
		double fxrate=0;
		String fxrateStr=fields.get(1);
		if(!fxrateStr.equals("")){
			fxrate=Double.valueOf(fxrateStr);
		}
		
		double cash=0;
		String setcashStr=fields.get(2);
		if(!setcashStr.equals("")){
			cash=Double.valueOf(setcashStr);
		}
		
		SettleCash settleCash=new SettleCash();
		settleCash.setCurID(currency);
		settleCash.setFxrate(fxrate);
		settleCash.setCash(cash);
		msgcontainer.setEntityObj(settleCash);
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
