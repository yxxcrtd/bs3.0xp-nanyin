package com.jixu.msg.service.config;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgTemplate;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.orm.obj.CoaaGroup;

/** 
 * 	@ClassName 		AssembleCoaGroup.java
 *	@author  		Pan Wei 
 *	@Date 			2016年8月10日 下午11:22:31
 *	@Description	[]
 */
@Service("coaGroupMsg")
public class AssembleCoaGroup  extends MsgExecuteService{
	
	@Override
	public String msgHeader() {
		return MsgTemplate.COAGROUP_Header;
	}
	
	@Override
	public String msgFields() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException {
		List<String> fields = msgcontainer.getFields();
		int groupCode=Integer.valueOf(fields.get(0));
		CoaaGroup coaGroup = coaaGroupDao.loadCoaaGroup(groupCode);
		if (coaGroup == null) {
			msgcontainer.setCorrectFlag(false);
			msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, msgcontainer.getHeader(), msgcontainer.getFields()));
		}else{
			msgcontainer.setEntityObj(coaGroup);
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

