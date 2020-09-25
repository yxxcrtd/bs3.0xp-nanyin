package com.jixu.msg.service.config;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgTemplate;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.orm.obj.SecAcct;

/** 
 * @ClassName 		AssembleSecAcct
 * @author  		Pan Wei 
 * @Date 			2016年9月30日 下午1:21:30 	
 * @Description		[]  
 */

@Service("secAcctMsg")
public class AssembleSecAcct extends MsgExecuteService{
	
	@Override
	public String msgHeader() {
		return MsgTemplate.SecAcct_Header;
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
		SecAcct secacct = (SecAcct)secAcctDao.loadEntity(SecAcct.class, acctid);
		if (secacct == null) {
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.error6);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, msgcontainer.getHeader(), msgcontainer.getFields()));
		}else{
			msgcontainer.setEntityObj(secacct);
		}
	}

	@Override
	public String messageForm() {
		// TODO Auto-generated method stub
		return "/forms/config/secAcct_edit_form"; 
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "证券账户";
	}
}
