package com.jixu.msg.service.config;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgTemplate;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.service.impl.MsgExecuteService;

@Service("curMsg")
public class AssembleCurrency extends MsgExecuteService{
	
	@Override
	public String msgHeader() {
		return MsgTemplate.CURRENCY_Header;
	}
	
	@Override
	public String msgFields() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException {
		List<String> fields = msgcontainer.getFields();
		String curCode=fields.get(0);
		String curID=commService.loadFxCurIDByCurCode(curCode);
		if (curID.equals("")) {
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, msgcontainer.getHeader(), msgcontainer.getFields()));
		}else{
			msgcontainer.setEntityObj(curCode);
		}
	}

	@Override
	public String messageForm() {
		// TODO Auto-generated method stub
		return "/forms/config/currency_add_form";
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "货币操作";
	}
}
