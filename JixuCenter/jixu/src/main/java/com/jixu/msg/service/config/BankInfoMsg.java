package com.jixu.msg.service.config;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.dao.intf.BkInfoDao;
import com.jixu.msg.dto.BankInfoDto;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.orm.obj.BkInfo;

/** 
 * @ClassName 		AssembleBNK
 * @author  		Pan Wei 
 * @Date 			2016年8月12日 下午2:01:02 	
 * @Description		[返回关联银行账户]  
 */
@Service
public class BankInfoMsg extends MsgExecuteService<BankInfoDto>{
	
	@Resource(name="bkInfoDao")
	private  BkInfoDao bkDao;

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
		BankInfoDto dto=JSONObject.parseObject(msgcontainer.getJsonMsg(), BankInfoDto.class);
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
		return "银行操作";
	}
	
	@Override
	public MsgContainer completeEntity(BankInfoDto entity, MsgContainer container) {
		BkInfo bank=new BkInfo();
		bank.setBankCode(entity.getCode());
		bank.setBankCode(entity.getCode());
		bank.setBankDesc(entity.getName());
		bank.setCountry(entity.getCountry());
		bank.setContractor(entity.getContact());
		bank.setAddress(entity.getAddress());
		bank.setPhoneNum(entity.getTel());
		bank.setPhoneFax(entity.getFax());
		
		String operation=entity.getOperation();
		if(operation.toUpperCase().equals(add)){
			bkDao.saveEntity(bank);
			container.setEntityID(String.valueOf(bank.getBankID()));
			container.setEntityObj(bank);
		}else if(operation.toUpperCase().equals(update)){
			BkInfo oldbank=bkDao.loadEntity(BkInfo.class, entity.getId());
			BeanUtils.copyProperties(bank, oldbank, "bankID");
			bkDao.updateEntity(oldbank);
			container.setEntityID(String.valueOf(oldbank.getBankID()));
			container.setEntityObj(oldbank);
		}
		return container;
	}
}
