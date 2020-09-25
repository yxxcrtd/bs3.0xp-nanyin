package com.jixu.msg.service.config;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgTemplate;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.orm.obj.CashFlowCfg;

/** 
 * @ClassName 		AssembleBNK
 * @author  		Pan Wei 
 * @Date 			2016年8月12日 下午2:01:02 	
 * @Description		[返回关联银行账户]  
 */

@Service("cashFlowCfgIDMsg")
public class AssembleCashFlowCfgID extends MsgExecuteService{

	@Override
	public String msgHeader() {
		return MsgTemplate.CashFlowCfgID_Header;
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
		CashFlowCfg cfg = cashFlowCfgDao.loadEntity(CashFlowCfg.class, acctid);
		if (cfg == null) {
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.error6);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, msgcontainer.getHeader(), msgcontainer.getFields()));
		}else{
			msgcontainer.setEntityObj(cfg);
		}
	}

	@Override
	public String messageForm() {
		// TODO Auto-generated method stub
		return "/forms/cf/cf_edit_form";
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "现金流操作";
	}
}
