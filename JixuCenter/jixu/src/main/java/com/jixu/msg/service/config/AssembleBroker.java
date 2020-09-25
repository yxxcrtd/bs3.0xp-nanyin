package com.jixu.msg.service.config;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgTemplate;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.orm.obj.Broker;

/** 
 * @ClassName 		AssembleBroker
 * @author  		Pan Wei 
 * @Date 			2016年8月16日 下午5:06:08 	
 * @Description		[]  
 */

@Service("brokerMsg")
public class AssembleBroker extends MsgExecuteService{
	
	@Override
	public String msgHeader() {
		return MsgTemplate.BROKER_Header;
	}
	
	@Override
	public String msgFields() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException {
		List<String> fields = msgcontainer.getFields();
		String brokerCode=fields.get(0);
		Broker broker =brokerDao.loadBroker(brokerCode);
		if (broker == null) {
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, msgcontainer.getHeader(), msgcontainer.getFields()));
		}else{
			msgcontainer.setEntityObj(broker);
		}
	}

	@Override
	public String messageForm() {
		// TODO Auto-generated method stub
		return "/forms/config/broker_edit_form";
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "券商操作";
	}
}

