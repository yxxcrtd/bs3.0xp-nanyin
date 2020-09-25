package com.jixu.msg.service.config;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.dao.intf.BrokerDao;
import com.jixu.msg.dto.BrokerAcctDto;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.orm.obj.Broker;

/** 
 * @ClassName 		AssembleBNK
 * @author  		Pan Wei 
 * @Date 			2016年8月12日 下午2:01:02 	
 * @Description		[返回关联银行账户]  
 */
@Service("jxBrokerAcctDtoMsg")
public class BrokerAcctDtoMsg extends MsgExecuteService<BrokerAcctDto>{
	
	@Autowired
	private  BrokerDao brokerDao;

	@Override
	public String msgHeader() {
		return null;
	}
	
	@Override
	public String msgFields() {
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		BrokerAcctDto dto=JSONObject.parseObject(msgcontainer.getJsonMsg(), BrokerAcctDto.class);
		validateDto(msgcontainer, dto);
		
		if(!msgcontainer.isCorrectFlag())
			return;
		
		//completeEntity(dto);
	}

	@Override
	public String messageForm() {
		return "/forms/config/bank_add_form";
	}
	
	public String messageName(){
		return "券商操作";
	}
	
	@Override
	public MsgContainer completeEntity(BrokerAcctDto entity, MsgContainer container) {
		Broker broker=new Broker();
		broker.setAddress(entity.getAddress());
		broker.setBicCode(entity.getBic());
		broker.setBrokerCode(entity.getCode());
		broker.setBrokerName(entity.getName());
		broker.setContractor(entity.getContact());
		broker.setFaxNum(entity.getFax());
		broker.setFinCode(entity.getFin());
		broker.setPhoneNum(entity.getTel());
		broker.setCountry(entity.getCountry());
		
		String operation=entity.getOperation();
		if(operation.toUpperCase().equals(add)){
			brokerDao.saveEntity(broker);
			container.setEntityID(String.valueOf(broker.getBrokerID()));
			container.setEntityObj(broker);
		}else if(operation.toUpperCase().equals(update)){
			Broker oldBroker=brokerDao.loadEntity(Broker.class, entity.getId());
			BeanUtils.copyProperties(broker, oldBroker, "brokerID");
			brokerDao.updateEntity(oldBroker);
			container.setEntityID(String.valueOf(oldBroker.getBrokerID()));
			container.setEntityObj(oldBroker);
		}
		return container;
	}
}
