package com.jixu.mvc.control.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.jixu.common.controller.ExceptionControl;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.util.JsonUtil;
import com.jixu.framework.msg.MsgFactory;
import com.jixu.mvc.control.service.ConfigService;
import com.orm.obj.ManagerGroup;
import com.orm.obj.Managers;


@Controller
public class ManagerController extends ExceptionControl{
	@Resource(name="configService")
	private ConfigService configService;
	
	@Resource(name = "commfuncService")
	private CommFuncService commService;
	
	@Resource(name="msgFactory")
	private MsgFactory msgFactory;
	
	@RequestMapping(value="/config/managerGroupList.do", method = RequestMethod.POST, name="管理-经理组-查询")
	 public void holidayList(HttpServletResponse response){
		HashMap<Integer, String> result =configService.loadManagerGroupList();
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/manager.do", method = RequestMethod.GET, name = "管理-经理组-页面显示")
    public String info() {
        return "manager";
    }
	
	@RequestMapping(value="/config/managerGroupCreate.do", method = RequestMethod.POST, name="管理-经理组-创建")
	 public void managerGroupCreate(@RequestParam(value = "name", required = true) String name, HttpServletResponse response){
        ManagerGroup mg=new ManagerGroup();
        mg.setGroupname(name);
        configService.createManagerGroup(mg);
		JsonUtil.writeObjToResponse(response, mg.getId());
	}
	
	@RequestMapping(value = "/config/managerGroupDetail.do", method = RequestMethod.POST, name="管理-经理组-详情")
	public void holidayDetail(@RequestParam(value = "id", required = true) int mgrGroupID, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
        List<String> titles = Arrays.asList("编号", "简称", "名称");
        result.put("titles", titles);
        List<Object> data = new ArrayList<Object>();
        
        List<Managers> managersList=configService.loadManagersListByGroupID(mgrGroupID);
        for(Managers manager:managersList){
	        List<Object> item1 = new ArrayList<Object>();
	        item1.add(manager.getId());
	        item1.add(manager.getShortCode());
	        item1.add(manager.getManagerName());
	        data.add(item1);
        }
        
        result.put("data", data);
        JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value = "/config/managerCreate.do", method = RequestMethod.POST, name="管理-经理-创建")
	public void holidayDayCreate(@RequestParam(value = "pid", required = true) int mgrGroupID,
								 @RequestParam(value = "params", required = true) String params,
								 @RequestParam(value = "json", required = true) String json,
								 HttpServletRequest request,
								 HttpServletResponse response) {
		
        HashMap<String, Object> fieldsHM=JSONObject.parseObject(json, HashMap.class);
		fieldsHM.put("mgrGroupID", String.valueOf(mgrGroupID));
		fieldsHM.put(MsgFields.header, "managerMsg");
		int userID=JixuUserUtil.getUserIDbyRequest(request);
		
		MsgContainer container=new MsgContainer();
		container.setFieldsHM(fieldsHM);
		container.setUserID(userID);
		container=msgFactory.executeMsg(container);
		JsonUtil.writeObjToResponse(response, container.getObjID());
	}
	
	@RequestMapping(value = "/config/managerUpdate.do", method = RequestMethod.POST, name="管理-经理-更新")
	public void holidayDayUpdate(@RequestParam(value = "pid", required = true) int mgrGroupID,
								 @RequestParam(value = "id", required = true) int mgrID,
								 @RequestParam(value = "params", required = true) String params,
								 @RequestParam(value = "json", required = true) String json,
								 HttpServletRequest request,
								 HttpServletResponse response) {
		
		HashMap<String, Object> fieldsHM=JSONObject.parseObject(json, HashMap.class);
		fieldsHM.put(MsgFields.header, "managerMsg");
		fieldsHM.put("mgrGroupID", String.valueOf(mgrGroupID));
		fieldsHM.put(MsgFields.id, String.valueOf(mgrID));
		int userID=JixuUserUtil.getUserIDbyRequest(request);
		
		MsgContainer container=new MsgContainer();
		container.setFieldsHM(fieldsHM);
		container.setUserID(userID);
		container=msgFactory.executeMsg(container);
		JsonUtil.writeObjToResponse(response, container.getObjID());
	}
	
	@RequestMapping(value = "/config/managerRemove.do", method = RequestMethod.POST, name="管理-经理-删除")
	public void holidayDayRemove(@RequestParam(value = "pid", required = true) int mgrGroupID,
								  @RequestParam(value = "id", required = true) int mgrID,
								  HttpServletRequest request,
								  HttpServletResponse response) {
		int userID = JixuUserUtil.getUserIDbyRequest(request);

		MsgContainer container = new MsgContainer();
		HashMap<String, Object> fieldsHM = new HashMap<String, Object>();
		String header = "managerMsg";
		fieldsHM.put(MsgFields.operation, MsgFields.delete);
		fieldsHM.put(MsgFields.header, header);
		fieldsHM.put(MsgFields.id, String.valueOf(mgrID));
		container.setFieldsHM(fieldsHM);
		container.setUserID(userID);

		container = MsgFactory.executeMsgBean(container);
        JsonUtil.writeObjToResponse(response, container.getObjID());
	}
}
