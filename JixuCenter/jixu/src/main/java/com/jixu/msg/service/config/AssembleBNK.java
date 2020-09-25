package com.jixu.msg.service.config;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgTemplate;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.orm.obj.BkCashAcct;

/** 
 * @ClassName 		AssembleBNK
 * @author  		Pan Wei 
 * @Date 			2016年8月12日 下午2:01:02 	
 * @Description		[返回关联银行账户]  
 */

@Service("bnkMsg")
public class AssembleBNK extends MsgExecuteService{

	@Override
	public String msgHeader() {
		return MsgTemplate.BNK_Header;
	}
	
	@Override
	public String msgFields() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException {
		List<String> fields = msgcontainer.getFields();
		int acctid=Integer.valueOf(fields.get(0));
		BkCashAcct acct = bkCashAcctDao.loadBankCashAcct(acctid);
		if (acct == null) {
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.error6);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, msgcontainer.getHeader(), msgcontainer.getFields()));
		}else{
			msgcontainer.setEntityObj(acct);
		}
	}

	@Override
	public String messageForm() {
		// TODO Auto-generated method stub
		return "/forms/config/bank_edit_form";
	}
	
	public String messageName(){
		return "银行操作";
	}
	
}
