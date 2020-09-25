package com.jixu.common.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.mvc.service.ConfigHolidayService;
import com.jixu.common.pojo.HolidayLS;
import com.jixu.common.pojo.HolidaySD;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.MsgTemplate;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.JsonUtil;
import com.jixu.framework.msg.MsgFactory;

@Controller
public class HolidayController extends ExceptionControl{

	@Autowired
	private ConfigHolidayService configHolidayService;
	
	@Resource(name="msgFactory")
	private MsgFactory msgFactory;
	
	/**
	 * 所有的节假日
	 * @param response
	 */
	@RequestMapping(value="/config/holidayList.do", method = RequestMethod.POST, name="管理-假期安排-查询")
	 public void holidayList(HttpServletResponse response){
		HashMap<String, String> result = configHolidayService.loadHolidaySchedule();
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/holiday.do", method = RequestMethod.GET, name="管理-假期安排-页面显示")
    public String info() {
        return "holiday";
    }
	
	@RequestMapping(value="/config/holidayCreate.do", method = RequestMethod.POST, name="管理-假期安排-创建")
	 public void holidayCreate(@RequestParam(value = "name", required = true) String name, HttpServletResponse response){
		HolidaySD holidaySD=new HolidaySD();
		holidaySD.setCode(name);
		holidaySD.setName(name);
		configHolidayService.createHolidaySD(holidaySD);
        JsonUtil.writeObjToResponse(response, holidaySD.getId() + "");
	}
	
	@RequestMapping(value = "/config/holidayDetail.do", method = RequestMethod.POST, name="管理-假期安排-详情")
	public void holidayDetail(@RequestParam(value = "id", required = true) String holidayID, @RequestParam(value = "num", required = false) int num,HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
        List<String> titles = Arrays.asList("编号", "日期", "描述");
        result.put("titles", titles);
        List<Object> data = new ArrayList<Object>();
        
        List<HolidayLS> holidayLSList=configHolidayService.loadHolidayLSList(holidayID);
        for(HolidayLS holidayls:holidayLSList){
	        List<Object> item1 = new ArrayList<Object>();
	        item1.add(holidayls.getId());
	        item1.add(DateUtil.convertD2YYYY_MM_DD(holidayls.getHolidayDate()));
	        item1.add(holidayls.getHolidayName());
	        data.add(item1);
        }
        
        result.put("data", data);
        result.put("pages", 10);
        JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value = "/config/holidayDayCreate.do", method = RequestMethod.POST, name="管理-节假日-创建")
	public void holidayDayCreate(@RequestParam(value = "pid", required = true) int holidayID,
								 @RequestParam(value = "params", required = true) String params,
								 @RequestParam(value = "json", required = true) String json,
								 HttpServletRequest request,
								 HttpServletResponse response) {
		HashMap<String, Object> fieldsHM=JSONObject.parseObject(json, HashMap.class);
		
		fieldsHM.put(MsgFields.header, MsgTemplate.HolidaysOperate_Header);
		fieldsHM.put(MsgFields.holidaySD, String.valueOf(holidayID));
		
		int userID=JixuUserUtil.getUserIDbyRequest(request);
		
		MsgContainer container=new MsgContainer();
		container.setFieldsHM(fieldsHM);
		container.setUserID(userID);
		container=msgFactory.executeMsg(container);
		
		HolidayLS holidayLS=(HolidayLS)container.getEntityObj();
		
        JsonUtil.writeObjToResponse(response, holidayLS.getId());
	}
	
	@RequestMapping(value = "/config/holidayDayUpdate.do", method = RequestMethod.POST, name="管理-节假日-更新")
	public void holidayDayUpdate(@RequestParam(value = "pid", required = true) int holidayID,
								 @RequestParam(value = "id", required = true) int holidayDayID,
								 @RequestParam(value = "params", required = true) String params,
								 @RequestParam(value = "json", required = true) String json,
								 HttpServletRequest request,
								 HttpServletResponse response) {
		
		HashMap<String, Object> fieldsHM=JSONObject.parseObject(json, HashMap.class);
		
		fieldsHM.put(MsgFields.header, MsgTemplate.HolidaysOperate_Header);
		fieldsHM.put(MsgFields.holidaySD, String.valueOf(holidayID));
		
		int userID=JixuUserUtil.getUserIDbyRequest(request);
		
		MsgContainer container=new MsgContainer();
		container.setFieldsHM(fieldsHM);
		container.setUserID(userID);
		container.setObjID(holidayDayID);
		container=msgFactory.executeMsg(container);
		
		HolidayLS holidayLS=(HolidayLS)container.getEntityObj();
		
        JsonUtil.writeObjToResponse(response, holidayLS.getId());
	}
	
	@RequestMapping(value = "/config/holidayDayRemove.do", method = RequestMethod.POST, name="管理-节假日-删除")
	public void holidayDayRemove(@RequestParam(value = "pid", required = true) int holidayID,
								  @RequestParam(value = "id", required = true) int dayID,
								  HttpServletRequest request,
								  HttpServletResponse response) {
		HashMap<String, Object> fieldsHM=new HashMap<String, Object>();
		
		fieldsHM.put(MsgFields.header, MsgTemplate.HolidaysOperate_Header);
		fieldsHM.put(MsgFields.operation, MsgFields.delete);
		
		int userID=JixuUserUtil.getUserIDbyRequest(request);
		
		MsgContainer container=new MsgContainer();
		container.setFieldsHM(fieldsHM);
		container.setUserID(userID);
		container.setObjID(dayID);
		container=msgFactory.executeMsg(container);
		
        JsonUtil.writeObjToResponse(response, "");
	}
}
