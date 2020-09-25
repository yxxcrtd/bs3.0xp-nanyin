package com.jixu.msg.service.config;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgTemplate;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.orm.obj.RPSecType;

/** 
 * 	@ClassName 		AssembleIssuerType.java
 *	@author  		Pan Wei 
 *	@Date 			2016年8月9日 下午3:37:13
 *	@Description	[]
 */
@Service("issuerTypeMsg")
public class AssembleIssuerType extends MsgExecuteService{
	
	@Override
	public String msgHeader() {
		return MsgTemplate.IssuerType_Header;
	}
	
	@Override
	public String msgFields() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException {
		List<String> fields = msgcontainer.getFields();
		int typeCode=Integer.valueOf(fields.get(0));
		RPSecType issuerType = rpSecTypeDao.loadIssuerType(typeCode);
		if (issuerType == null) {
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, msgcontainer.getHeader(), msgcontainer.getFields()));
		}else{
			msgcontainer.setEntityObj(issuerType);
		}
		return;
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
