package com.jixu.msg.service.config;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jixu.common.exception.MsgFieldLackException;
import com.jixu.common.pojo.HolidayLS;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.MsgTemplate;
import com.jixu.common.util.DateUtil;
import com.jixu.framework.dao.intf.HolidayLSDao;
import com.jixu.msg.service.impl.MsgExecuteService;

/** 
 * @ClassName 		HolidaysOperate
 * @author  		Pan Wei 
 * @Date 			2018年2月11日 下午12:21:11 	
 * @Description		[]  
 */

@Service("MsgHolidaysOperate")
public class HolidaysOperate extends MsgExecuteService{
	
	@Autowired
	private HolidayLSDao holidayLSDao;
	
	@Override
	public String msgHeader() {
		return MsgTemplate.HolidaysOperate_Header;
	}
	
	@Override
	protected void execute(MsgContainer msgcontainer) throws NumberFormatException, MsgFieldLackException {
		HashMap<String, Object> fieldsHM=msgcontainer.getFieldsHM();
		
		String operation=getFieldValue(fieldsHM, MsgFields.operation);
		int holidayID=msgcontainer.getObjID();
		
		if(operation.toUpperCase().equals(delete)){
			HolidayLS oldHoliday=holidayLSDao.loadEntity(HolidayLS.class, holidayID);
			holidayLSDao.deleteHolidayLS(oldHoliday);
			
			oldHoliday.setId(oldHoliday.getId()*-1);
			//outDataCenter.exePublicDataSender(oldHoliday);
		}else{
			String recordDateStr=getFieldValue(fieldsHM, MsgFields.recordDate).replace("-", ""); 
			Date recordDate=DateUtil.convertStr2Date(recordDateStr);
			String recordName=getFieldValue(fieldsHM, MsgFields.recordName);
			String holidaySDID=getFieldValue(fieldsHM, MsgFields.holidaySD);
			
			HolidayLS holiday=new HolidayLS();
			holiday.setHolidaySD(holidaySDID);
			holiday.setHolidayDate(recordDate);
			holiday.setHolidayName(recordName);
			
			if(operation.toUpperCase().equals(add)){
				holidayLSDao.createHolidayLS(holiday);
				msgcontainer.setObjID(holiday.getId());
				msgcontainer.setEntityObj(holiday);
				//outDataCenter.exePublicDataSender(holiday);
			}else if(operation.toUpperCase().equals(update)){
				HolidayLS oldHoliday=holidayLSDao.loadEntity(HolidayLS.class, holidayID);
				BeanUtils.copyProperties(holiday, oldHoliday, "id");
				
				holidayLSDao.updateHolidayLS(oldHoliday);
				msgcontainer.setObjID(oldHoliday.getId());
				msgcontainer.setEntityObj(oldHoliday);
				//outDataCenter.exePublicDataSender(oldHoliday);
			}
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
