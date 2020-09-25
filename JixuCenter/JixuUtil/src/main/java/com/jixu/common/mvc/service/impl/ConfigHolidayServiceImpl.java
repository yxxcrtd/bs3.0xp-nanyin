package com.jixu.common.mvc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jixu.common.mvc.service.ConfigHolidayService;
import com.jixu.common.pojo.HolidayLS;
import com.jixu.common.pojo.HolidaySD;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.framework.dao.intf.HolidayLSDao;
import com.jixu.framework.dao.intf.HolidaySDDao;
import com.joyintech.framework.base.intf.DataDict;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.util.CacheUtil;

@Service("configHolidayService")
public class ConfigHolidayServiceImpl implements ConfigHolidayService{
	
	@Autowired
	private HolidaySDDao holidaySDDao;
	
	@Autowired
	private HolidayLSDao holidayLSDao;
	
	@Override
	@Transactional(value="jixuTXManager", rollbackFor = Exception.class)
	public void createHolidaySD(HolidaySD holidaySD){
		holidaySDDao.saveHolidaySD(holidaySD);
	}
	
	@Override
	public HashMap<String, String> loadHolidaySchedule() {
		List<HolidaySD> holidayList=null;
		if(!JixuUserUtil.isJoyinTablesFlag()){
			holidayList=holidaySDDao.loadHolidaySchedule();
		}else {
			holidayList=loadJoyinHolidaySchedule();
		}
		HashMap<String, String> holidayHM=new HashMap<String, String>();
		for(HolidaySD holiday:holidayList){
			holidayHM.put(holiday.getId(), holiday.getName());
		}
		return holidayHM;
	}
	
	@Override
	public List<HolidayLS> loadHolidayLSList(String holidaySDNum) {
		return holidayLSDao.loadHolidayLSList(holidaySDNum);
	}
	
	/**
	 * 获取日历列表
	 * @return
	 */
	private List<HolidaySD> loadJoyinHolidaySchedule(){
		List<HolidaySD> holidayList=new ArrayList<HolidaySD>();
		DataDict dataDict = CacheUtil.getDictCode("TRANSACTION_CALENDAR"); 
		List<? extends DataDictItem> itemList = dataDict.getItems(); 
		for (DataDictItem dictItem : itemList) { 
			HolidaySD cur=new HolidaySD();
			cur.setId(dictItem.getItemCode()); 
			cur.setName(dictItem.getItemValue());
			holidayList.add(cur);
		}
		return holidayList;
	}
}
