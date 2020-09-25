package com.jixu.msg.service.info;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.util.DateUtil;
import com.jixu.job.service.ContractualSetService;
import com.jixu.msg.service.impl.MsgExecuteService;

/** 
 * @ClassName 		Rcpy2Cash
 * @author  		Pan Wei 
 * @Date 			2018年5月4日 下午3:51:39 	
 * @Description		[]  
 */

@Service
public class Rcpy2CashMsg extends MsgExecuteService{
	
	@Autowired
	private ContractualSetService contractualSetService;
	
	@Override
	public void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		HashMap<String, Object> fieldsHM=msgcontainer.getFieldsHM();
		String operation=getFieldValue(fieldsHM, MsgFields.operation);
		
		int id=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.id));
		if(operation.equals(add)){
			int userid=msgcontainer.getUserID();
			double setCash=Double.valueOf(getFieldValue(fieldsHM, MsgFields.settleCash));
			String settleDateStr=getFieldValue(fieldsHM, MsgFields.settleDate);
			Date settleDate=DateUtil.convertStr2Date(settleDateStr.replaceAll("-", ""));
			ResultContainer container=contractualSetService.settleToCash(userid, id, setCash, settleDate);
			
			BeanUtils.copyProperties(container, msgcontainer);
		}else if(operation.equals(delete)){
			ResultContainer container=contractualSetService.deleteCashFlow(id);
			BeanUtils.copyProperties(container, msgcontainer);
		}
	}
	
	
	@Override
	public String messageForm() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String messageName() {
		return "结转应收应付到实收实付";
	}
	
	@Override
	public String msgHeader() {
		// TODO Auto-generated method stub
		return null;
	}
}
