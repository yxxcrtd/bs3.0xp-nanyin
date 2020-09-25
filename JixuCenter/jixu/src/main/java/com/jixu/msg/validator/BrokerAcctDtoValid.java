package com.jixu.msg.validator;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jixu.common.util.ReturnResult;
import com.jixu.dao.intf.BrokerDao;
import com.jixu.framework.validator.JixuBeanValidatorAbs;
import com.jixu.framework.validator.JixuValidMessage;
import com.jixu.msg.dto.BrokerAcctDto;

@Component
public class BrokerAcctDtoValid extends JixuBeanValidatorAbs {
	
	//@Autowired
	//private MsgService<BrokerAcctDto> jxBrokerAcctDtoMsg;
	
	@Autowired
	private BrokerDao brokerDao;

	@Override
	public List<JixuValidMessage> valid(Object value) {
		List<JixuValidMessage> validMessageList = new ArrayList<JixuValidMessage>();
		BrokerAcctDto dto = (BrokerAcctDto) value;
		
		if(brokerDao.checkBrokerCodeOrNameExist(dto.getCode(), dto.getName())) {
			JixuValidMessage errmsg=new JixuValidMessage(ReturnResult.getResultHM().get(ReturnResult.broker_name_exist));
			validMessageList.add(errmsg);
		}
		
//		boolean checkProcessExist=brokerAcctMsg.checkBusinessDataInFlow(dto);
//	    if(checkProcessExist)
//	    	validMessageList.add(new JixuValidMessage(ReturnResult.getResultHM().get(ReturnResult.process_entity_exist)+dto.getHeader()));


		return validMessageList;
	}
}
