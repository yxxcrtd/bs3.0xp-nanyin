package com.jixu.msg.service.config;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgTemplate;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.orm.obj.IssueInfo;

@Service("secIDMsg")
public class AssembleSecID extends MsgExecuteService {
	
	@Override
	public String msgHeader() {
		return MsgTemplate.SECID_Header;
	}
	
	@Override
	public String msgFields() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException{
		List<String> fields = msgcontainer.getFields();

		IssueInfo sec = issueInfoDao.loadSecurity(fields.get(0));
		if(sec==null){
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6,msgcontainer.getHeader(),fields));
		}else{
			msgcontainer.setCorrectFlag(true);
			msgcontainer.setEntityObj(sec);
		}
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
