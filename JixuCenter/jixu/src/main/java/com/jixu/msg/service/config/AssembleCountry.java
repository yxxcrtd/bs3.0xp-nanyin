package com.jixu.msg.service.config;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgTemplate;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.orm.obj.Country;

/** 
 * 	@ClassName 		AssembleCountry.java
 *	@author  		Pan Wei 
 *	@Date 			2016年8月9日 下午3:16:01
 *	@Description	[]
 */
@Service("countryMsg")
public class AssembleCountry extends MsgExecuteService{
	
	@Override
	public String msgHeader() {
		return MsgTemplate.Country_Header;
	}
	
	@Override
	public String msgFields() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException {
		List<String> fields = msgcontainer.getFields();
		String countryID=fields.get(0);
		Country country = countryDao.loadCountry(countryID);
		if (country == null) {
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, msgcontainer.getHeader(), msgcontainer.getFields()));
		}else{
			msgcontainer.setEntityObj(country);
		}
	}

	@Override
	public String messageForm() {
		// TODO Auto-generated method stub
		return "/forms/config/country_add_form";
	}

	@Override
	public String messageName() {
		// TODO Auto-generated method stub
		return "国家";
	}
}
