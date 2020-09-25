package com.jixu.msg.service.info;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgTemplate;
import com.jixu.common.util.RoundUtil;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.model.LedgerEntryModel;
import com.jixu.msg.service.impl.MsgExecuteService;

/** 
 * @ClassName 		AssembleCollat
 * @author  		Pan Wei 
 * @Date 			2016年8月22日 下午3:28:42 	
 * @Description		[]  
 */

@Service("ledgerEntry")
public class AssembleLedgerEntry extends MsgExecuteService{
	
	@Override
	public String msgHeader() {
		return MsgTemplate.LedgerEntry_Header;
	}
	
	@Override
	public String msgFields() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException {
		List<String> fields=msgcontainer.getFields();
		List<LedgerEntryModel> ledgerList=new ArrayList<LedgerEntryModel>();
		//获取费用分支
		for(String subMsg:fields){
			genMsgContainer(MsgTemplate.LedgerEntrySub_Header,subMsg,msgcontainer);
			if(msgcontainer.isCorrectFlag()){
				ledgerList.add((LedgerEntryModel)msgcontainer.getEntityObj());
			}
		}
		
		double dr_func_t=0;
		double cr_func_t=0;
		double dr_local_t=0;
		double cr_local_t=0;
		for(LedgerEntryModel ledger:ledgerList){
			dr_func_t=dr_func_t+ledger.getDrfunc();
			cr_func_t=cr_func_t+ledger.getCrfunc();
			dr_local_t=dr_local_t+ledger.getDrlocal();
			cr_local_t=cr_local_t+ledger.getCrlocal();
		}
		
		dr_func_t=RoundUtil.roundMoney2Dec(dr_func_t);
		cr_func_t=RoundUtil.roundMoney2Dec(cr_func_t);
		dr_local_t=RoundUtil.roundMoney2Dec(dr_local_t);
		cr_local_t=RoundUtil.roundMoney2Dec(cr_local_t);
		
		if(dr_func_t!=cr_func_t || dr_local_t!=cr_local_t){
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error19, msgcontainer.getHeader(), msgcontainer.getFields()));
		}else{
			msgcontainer.setEntityObj(ledgerList);
		}
	}

	@Override
	public String messageForm() {
		// TODO Auto-generated method stub
		return  "/forms/info/voucher_add_form";
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "凭证操作";
	}
}
