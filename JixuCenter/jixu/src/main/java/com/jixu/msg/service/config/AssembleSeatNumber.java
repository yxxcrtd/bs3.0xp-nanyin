package com.jixu.msg.service.config;

import java.util.HashMap;
import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.util.ReturnResult;
import com.jixu.dao.intf.SeatNumberDao;
import com.jixu.framework.msg.MsgError;
import com.jixu.msg.service.impl.MsgExecuteService;
import com.jixu.mvc.control.service.ConfigService;
import com.orm.obj.ManagerGroup;
import com.orm.obj.Managers;
import com.orm.obj.SeatNumber;

/** 
 * @ClassName 		AssembleSeatNumber
 * @author  		Pan Wei 
 * @Date 			2016年8月12日 下午2:01:02 	
 * @Description		[返回关联银行账户]  
 */
@Service
public class AssembleSeatNumber extends MsgExecuteService{
	
	@Autowired
	private SeatNumberDao seatNumberDao;
	
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
		HashMap<String, Object> fieldsHM=msgcontainer.getFieldsHM();
		
		String operation=getFieldValue(fieldsHM, MsgFields.operation);
		
		int seatNumberID=0;
		if(fieldsHM.containsKey(MsgFields.id)){
			seatNumberID=Integer.valueOf(getFieldValue(fieldsHM, MsgFields.id));
		}
		SeatNumber oldsn=seatNumberDao.loadEntity(SeatNumber.class, seatNumberID);
		
		if(operation.toUpperCase().equals(delete)){
			if(oldsn!=null){
				seatNumberDao.deleteEntity(oldsn);
			}else{
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(MsgError.genErrorMsg(MsgError.error6, getFieldValue(fieldsHM, MsgFields.header),String.valueOf(delete)));
			}
			return;
		}
		
		String acctnumber=getFieldValue(fieldsHM, "acctnumber");
		
		String acctname=getFieldValue(fieldsHM, "acctname");
		
		String acctDesc=getFieldValue(fieldsHM, "acctDesc");
		
		boolean flag=false;
		String brokerID="";
		if(operation.toUpperCase().equals(add)){
			brokerID=getFieldValue(fieldsHM, MsgFields.broker);
			flag=seatNumberDao.loadSeatNumberByCodeName(brokerID,acctnumber);
			if(!flag){
				msgcontainer.setCorrectFlag(false);
				msgcontainer.setErrorMsg(ReturnResult.getResultHM().get(ReturnResult.seat_numbername_exist));
				return;
			}
		}
		
		
		SeatNumber sn=new SeatNumber();
		sn.setSeatNumberAcct(acctnumber);
		sn.setSeatNumberName(acctname);
		sn.setSeatNumberDesc(acctDesc);
		
		if(operation.toUpperCase().equals(add)){
			sn.setBrokerID(brokerID);
			seatNumberDao.saveEntity(sn);
		}else{
			BeanUtils.copyProperties(sn, oldsn, new String[]{"id","brokerID"});
			seatNumberDao.updateEntity(oldsn);
		}
	}

	@Override
	public String messageForm() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String messageName(){
		return "券商席位号操作";
	}
	
}
