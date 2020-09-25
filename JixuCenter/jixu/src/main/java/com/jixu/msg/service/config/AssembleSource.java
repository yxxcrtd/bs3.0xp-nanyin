package com.jixu.msg.service.config;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgTemplate;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.service.impl.MsgExecuteService;

@Service("sourceMsg")
public class AssembleSource extends MsgExecuteService{
	
	@Override
	public String msgHeader() {
		return MsgTemplate.SOURCE_Header;
	}
	
	@Override
	public String msgFields() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException {
		List<String> fields = msgcontainer.getFields();
		int sourceID=Integer.valueOf(fields.get(0));
		InterfaceSource source = interfaceSourceDao.loadSource(sourceID);
		if (source == null) {
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, msgcontainer.getHeader(), msgcontainer.getFields()));
		}else{
			msgcontainer.setSource(source);
			msgcontainer.setEntityObj(source);
		}
	}

	@Override
	public String messageForm() {
		// TODO Auto-generated method stub
		return "/forms/interface/interface_edit_form";
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "导入接口操作";
	}
}
