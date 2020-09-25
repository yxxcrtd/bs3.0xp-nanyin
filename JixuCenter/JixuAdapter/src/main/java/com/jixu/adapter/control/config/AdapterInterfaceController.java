package com.jixu.adapter.control.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.jixu.adapter.common.func.AdapterSysInit;
import com.jixu.adapter.control.service.ConfigAdptService;
import com.jixu.adapter.dao.intf.InterfaceAdapterDao;
import com.jixu.adapter.dao.intf.InterfaceLogDao;
import com.jixu.adapter.model.JobStatusDto;
import com.jixu.adapter.service.impl.DataInterfaceService;
import com.jixu.adapter.service.impl.DataServiceCenter;
import com.jixu.adapter.service.impl.ExecuteJobTask;
import com.jixu.adapter.types.AddUpdateDelType;
import com.jixu.adapter.types.FileEncodeType;
import com.jixu.adapter.types.InterfaceAutoType;
import com.jixu.common.constant.JixuDictCode;
import com.jixu.common.constant.JixuDictCodeList;
import com.jixu.common.controller.ExceptionControl;
import com.jixu.common.pojo.InterfaceColConfig;
import com.jixu.common.pojo.InterfaceMsgColMap;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.FlagType;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.type.InterfaceSourceType;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.MsgFields;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.JsonUtil;
import com.jixu.common.util.Pages;
import com.orm.obj.InterfaceLog;

@Controller
public class AdapterInterfaceController extends ExceptionControl{
	
	@Resource(name="configAdptService")
	private ConfigAdptService configService;
	
	@Resource(name="dataInterfaceService")
	private DataInterfaceService interfaceService;
	
	@Resource(name="interafaceDataCenter")
	private DataServiceCenter dataServiceCenter;
	
	@Resource(name="taskExecutor")
	private ThreadPoolTaskExecutor taskExecutor; 
	
	@Resource(name="interfaceAdapterDao")
	private InterfaceAdapterDao interfaceSourceDao;
	
	@Autowired
	private InterfaceLogDao interfaceLogDao;
	
	@RequestMapping(value="/dataAdapter.do", method = RequestMethod.GET, name="管理-数据适配器-页面显示")
	public String adapterPage(){
		return "dataAdapter";
	}
	
	@RequestMapping(value="/adapterPage.do", method = RequestMethod.GET, name="管理-数据适配器配置-详情页面显示")
	public String adapterPage(@RequestParam(value="id") int interfaceID, ModelMap model) {
		model.addAttribute("interfaceID", interfaceID);
		return "adapterPage";
	}
	
	@RequestMapping(value="/interface/interfaceCreate.do", method = RequestMethod.POST, name="管理-接口配置-创建")
	public void portfolioCreate(@RequestParam(value = "params", required = true) String params,
								@RequestParam(value = "json", required = true) String json,
								HttpServletRequest request,
								HttpServletResponse response) {
		MsgContainer container = executeMsg(request, json);
		controllerReturn(response, container);
		JsonUtil.writeObjToResponse(response, container.getObjID());
	}
	
	@RequestMapping(value="/interface/interfaceRemove.do", method = RequestMethod.POST)
	public void interfaceRemove(@RequestParam(value = "id", required = true) int interfaceID,
								HttpServletResponse response) {
		
		configService.deleteInterfaceSource(interfaceID);
		JsonUtil.writeObjToResponse(response, true);
	}
	
	@RequestMapping(value="/interface/interfaceDetail.do", method = RequestMethod.POST, name="管理-接口配置-详情")
	public void interfaceDetail(@RequestParam(value = "id", required = true) int interfaceID,
								HttpServletResponse response) {
		//Construct Portfolio Basic Information
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> basicTitles = Arrays.asList("接口名称", "接口描述", "接口类型", "证券类型", "传输类型", "用户名", "密码", 
											     "文件格式", "文件路径(支持公式)", "文件名(支持通配符)", "支持多文件", 
											     "表或工单名", "开始行", "分隔符", "结束符",
											     "节假日安排", "自动导入", "自动导入时间安排", "导入时间(HH:MM:SS)", "导入后移至");
		
		HashMap<String, Object> basicData=configService.loadBasicColumns(interfaceID);
		
		result.put("titles", basicTitles);
		result.put("data", basicData);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/interface/interfaceUpdate.do", method = RequestMethod.POST, name="管理-接口配置-更新")
	public void interfaceUpdate(@RequestParam(value = "id", required = true) int interfaceID,
							    @RequestParam(value = "params", required = true) String params,
							    @RequestParam(value = "json", required = true) String json,
							    HttpServletRequest request,
								HttpServletResponse response) {
		MsgContainer container = executeMsg(request, json);
		controllerReturn(response, container);
		JsonUtil.writeObjToResponse(response, container.getObjID());
	}
	
	@RequestMapping(value="/interface/adapterInterfaceList.do", method = RequestMethod.POST, name="管理-接口配置-查询")
	 public void interfaceListList(@RequestParam(value = "name", required = false) String name,
			 					  @RequestParam(value = "category", required = true) int category,
			 					  @RequestParam(value = "type", required = false) String type,
			 					  HttpServletResponse response){
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号","接口名称", "接口描述", "接口类型", "是否自动导入", "自动导入时间");
		result.put("titles", titles);
		
		List<Object> data = new ArrayList<Object>();
		List<InterfaceSource> sourceList=configService.loadInterfaceSourceList(name, category, type);
		
		for(InterfaceSource source:sourceList){
			List<Object> item = new ArrayList<Object>();
			item.add(source.getSourceID());
			item.add(source.getSourceName());
			item.add(source.getSourceDesc());
			if(source.getChannelType()==InterfaceConfig.dbType){
				item.add(InterfaceConfig.dbTypeOption.get(source.getFileType()));
			}else{
				item.add(InterfaceConfig.fileTypeOption.get(source.getFileType()));
			}
			//item.add(FlagType.operationsHM.get(source.isFileMultiFlag()));
			item.add(FlagType.operationsHM.get(source.isAutoFlag()));
			item.add(source.getAutoTime());
			data.add(item);
		}
		
		result.put("data", data);
		
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value = "/interface/deleteInterfaceSource.do", method = RequestMethod.POST, name="管理-接口配置-删除")
	public void deleteInterfaceSourceLogic(@RequestParam(value = "id", required = true) int interfaceID,
								  HttpServletResponse response) {
		configService.deleteInterfaceSource(interfaceID);
        JsonUtil.writeObjToResponse(response, "");
	}
	
	@RequestMapping(value = "/interface/basicColDetail.do", method = RequestMethod.POST, name="管理-接口配置-基础列详情")
	public void basicColDetail(@RequestParam(value = "id", required = true) int interfaceID, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
        List<String> titles = Arrays.asList("编号", "列名称", "列数", "起始位", "结束位", "验证函数");
        result.put("titles", titles);
        List<Object> data = new ArrayList<Object>();
        
        List<InterfaceColConfig> configList=configService.loadInterfaceColCfgList(interfaceID, InterfaceConfig.baseType);
        
        if(configList.size()==0){
        	configService.autoGenerateSQLBasicColumn(interfaceID);
        	configList=configService.loadInterfaceColCfgList(interfaceID, InterfaceConfig.baseType);
        }
        
        for(InterfaceColConfig cfg:configList){
        	List<Object> item1 = new ArrayList<Object>();
	        item1.add(cfg.getId());
	        item1.add(cfg.getColname());
	        item1.add(cfg.getColNO());
	        item1.add(cfg.getColStartNO());
	        item1.add(cfg.getColEndNo());
	        if(cfg.getFuncClass()==null || cfg.getFuncClass()=="-"){
	        	item1.add(0);
	        }else{
	        	item1.add(interfaceService.getClassIDMap().get(cfg.getFuncClass()));
	        }
	        data.add(item1);
        }
        
        result.put("data", data);
        JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value = "/interface/basicColCreate.do", method = RequestMethod.POST, name="管理-接口配置-基础列创建")
	public void basicColCreate(@RequestParam(value = "pid", required = true) int interfaceID,
								 @RequestParam(value = "params", required = true) String params,
								 HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		ResultContainer container=configService.createInterfaceBasicCol(interfaceID, param_list);
		
		if(container.isCorrectFlag()){
			JsonUtil.writeObjToResponse(response, container.getObjID());
		}else{
			HashMap<String, Object> result = new HashMap<String, Object>();
			HashMap<String, Object> issue = new HashMap<String, Object>();
			//issue.put("code", container.getErrorCode());
			issue.put("level", "fail");
			issue.put("detail", container.getErrorMsg());
			result.put("issue", issue);
			JsonUtil.writeObjToResponse(response, result);
		}
	}
	
	@RequestMapping(value = "/interface/basicColUpdate.do", method = RequestMethod.POST, name="管理-接口配置-基础列更新")
	public void basicColUpdate(@RequestParam(value = "pid", required = true) int interfaceID,
								 @RequestParam(value = "id", required = true) int basicColID,
								 @RequestParam(value = "params", required = true) String params,
								 HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		ResultContainer container=configService.updateInterfaceBasicCol(interfaceID, basicColID, param_list);
		if(container.isCorrectFlag()){
			JsonUtil.writeObjToResponse(response, container.getObjID());
		}else{
			HashMap<String, Object> result = new HashMap<String, Object>();
			HashMap<String, Object> issue = new HashMap<String, Object>();
			//issue.put("code", container.getErrorCode());
			issue.put("level", "fail");
			issue.put("detail", container.getErrorMsg());
			result.put("issue", issue);
			JsonUtil.writeObjToResponse(response, result);
		}
	}
	
	@RequestMapping(value = "/interface/basicColRemove.do", method = RequestMethod.POST, name="管理-接口配置-基础列删除")
	public void basicColRemove(@RequestParam(value = "pid", required = true) int interfaceID,
								  @RequestParam(value = "id", required = true) int basicColID,
								  HttpServletResponse response) {
		ResultContainer container=configService.deleteInterfaceCol(basicColID);
		controllerReturn(response,container);
	}
	
	@RequestMapping(value = "/interface/advanceColDetail.do", method = RequestMethod.POST, name="管理-接口配置-增强列详情")
	public void advanceColDetail(@RequestParam(value = "id", required = true) int interfaceID, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
        List<String> titles = Arrays.asList("编号", "增强列名称", "功能函数", "功能参数(表达式)");
        result.put("titles", titles);
        List<Object> data = new ArrayList<Object>();
        
        List<InterfaceColConfig> configList=configService.loadInterfaceAdvanceColCfgList(interfaceID);
        
        for(InterfaceColConfig cfg:configList){
        	List<Object> item1 = new ArrayList<Object>();
	        item1.add(cfg.getId());
	        item1.add(cfg.getColname());
	        if(cfg.getFuncClass()==null || cfg.getFuncClass()=="-"){
	        	item1.add(0);
	        }else{
	        	item1.add(interfaceService.getClassIDMap().get(cfg.getFuncClass()));
	        }
	        item1.add(cfg.getFuncParams());
	        data.add(item1);
        }
        
        result.put("data", data);
        JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value = "/interface/advanceColCreate.do", method = RequestMethod.POST, name="管理-接口配置-增强列创建")
	public void advanceColCreate(@RequestParam(value = "pid", required = true) int interfaceID,
								 @RequestParam(value = "params", required = true) String params,
								 HttpServletResponse response) {
		List<List<String>> param_list = JsonUtil.stringToList2(params);
		ResultContainer container=configService.createInterfaceAdvanceCol(interfaceID, param_list);
		if(container.isCorrectFlag()){
			JsonUtil.writeObjToResponse(response, container.getObjID());
		}else{
			HashMap<String, Object> result = new HashMap<String, Object>();
			HashMap<String, Object> issue = new HashMap<String, Object>();
			//issue.put("code", container.getErrorCode());
			issue.put("level", "fail");
			issue.put("detail", container.getErrorMsg());
			result.put("issue", issue);
			JsonUtil.writeObjToResponse(response, result);
		}
	}
	
	@RequestMapping(value = "/interface/advanceColUpdate.do", method = RequestMethod.POST, name="管理-接口配置-增强列更新")
	public void advanceColUpdate(@RequestParam(value = "pid", required = true) int interfaceID,
								 @RequestParam(value = "id", required = true) int advanceColID,
								 @RequestParam(value = "params", required = true) String params,
								 HttpServletResponse response) {
		List<List<String>> param_list = JsonUtil.stringToList2(params);
		//ResultContainer container=configService.updateInterfaceAdvanceCol(interfaceID, advanceColID, param_list);
		ResultContainer container=configService.createInterfaceAdvanceCol(interfaceID, param_list);
		if(container.isCorrectFlag()){
			JsonUtil.writeObjToResponse(response, "");
		}else{
			HashMap<String, Object> result = new HashMap<String, Object>();
			HashMap<String, Object> issue = new HashMap<String, Object>();
			//issue.put("code", container.getErrorCode());
			issue.put("level", "fail");
			issue.put("detail", container.getErrorMsg());
			result.put("issue", issue);
			JsonUtil.writeObjToResponse(response, result);
		}
	}
	
	@RequestMapping(value = "/interface/advanceColRemove.do", method = RequestMethod.POST, name="管理-接口配置-增强列删除")
	public void advanceColRemove(@RequestParam(value = "pid", required = true) int interfaceID,
								  @RequestParam(value = "id", required = true) int advanceColID,
								  HttpServletResponse response) {
		ResultContainer container=configService.deleteInterfaceCol(advanceColID);
		controllerReturn(response,container);
	}
	
	@RequestMapping(value = "/interface/mapColDetail.do", method = RequestMethod.POST)
	public void mapColDetail(@RequestParam(value = "id", required = true) int interfaceID, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
        List<String> titles = Arrays.asList("编号", "基础列|增强列名称", "转换字符", "匹配字符");
        result.put("titles", titles);
        List<Object> data = new ArrayList<Object>();
        
        List<InterfaceColConfig> colList=configService.loadInterfaceMapColList(interfaceID);
        
        for(InterfaceColConfig cfg:colList){
        	List<Object> item1 = new ArrayList<Object>();
            item1.add(cfg.getId());
            item1.add(cfg.getId());
            item1.add(cfg.getCharFrom());
            item1.add(cfg.getCharTo());
            data.add(item1);
        }
        
        result.put("data", data);
        JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value = "/interface/mapColCreate.do", method = RequestMethod.POST)
	public void mapColCreate(@RequestParam(value = "pid", required = true) int interfaceID,
								 @RequestParam(value = "params", required = true) String params,
								 HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		int cfgID=Integer.valueOf(param_list.get(0).toString());
		String from=param_list.get(1).toString();
		String to=param_list.get(2).toString();
		ResultContainer container=configService.createMapCharCol(cfgID, from, to);
		if(container.isCorrectFlag()){
			JsonUtil.writeObjToResponse(response, container.getObjID() + "");
		}else{
			HashMap<String, Object> result = new HashMap<String, Object>();
			HashMap<String, Object> issue = new HashMap<String, Object>();
			//issue.put("code", container.getErrorCode());
			issue.put("level", "fail");
			issue.put("detail", container.getErrorMsg());
			result.put("issue", issue);
			JsonUtil.writeObjToResponse(response, result);
		}
	}
	
	@RequestMapping(value = "/interface/mapColUpdate.do", method = RequestMethod.POST)
	public void mapColUpdate(@RequestParam(value = "pid", required = true) int interfaceID,
								 @RequestParam(value = "id", required = true) int mapColID,
								 @RequestParam(value = "params", required = true) String params,
								 HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		int cfgID=Integer.valueOf(param_list.get(0).toString());
		String from=param_list.get(1).toString();
		String to=param_list.get(2).toString();
		ResultContainer container=configService.createMapCharCol(cfgID, from, to);
		if(container.isCorrectFlag()){
			JsonUtil.writeObjToResponse(response, container.getObjID() + "");
		}else{
			HashMap<String, Object> result = new HashMap<String, Object>();
			HashMap<String, Object> issue = new HashMap<String, Object>();
			//issue.put("code", container.getErrorCode());
			issue.put("level", "fail");
			issue.put("detail", container.getErrorMsg());
			result.put("issue", issue);
			JsonUtil.writeObjToResponse(response, result);
		}
	}
	
	@RequestMapping(value = "/interface/mapColRemove.do", method = RequestMethod.POST)
	public void mapColRemove(@RequestParam(value = "pid", required = true) int interfaceID,
								  @RequestParam(value = "id", required = true) int mapColID,
								  HttpServletResponse response) {
		ResultContainer container=configService.createMapCharCol(mapColID, "", "");
		if(container.isCorrectFlag()){
			JsonUtil.writeObjToResponse(response, container.getObjID() + "");
		}else{
			HashMap<String, Object> result = new HashMap<String, Object>();
			HashMap<String, Object> issue = new HashMap<String, Object>();
			//issue.put("code", container.getErrorCode());
			issue.put("level", "fail");
			issue.put("detail", container.getErrorMsg());
			result.put("issue", issue);
			JsonUtil.writeObjToResponse(response, result);
		}
	}
	
	@RequestMapping(value = "/interface/msgColDetail.do", method = RequestMethod.POST)
	public void msgColDetail(@RequestParam(value = "id", required = true) int interfaceID, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
        List<String> titles = Arrays.asList("编号", "消息关键字", "基础列|增强列");
        result.put("titles", titles);
        List<Object> data = new ArrayList<Object>();
        
//        List<Object> item1 = new ArrayList<Object>();
//        item1.add(0);
//        item1.add("TRADE");
//        item1.add(1);
//        data.add(item1);
        
        List<InterfaceMsgColMap> msgmapList=configService.loadInterfaceMsgColMapList(interfaceID);
        for(InterfaceMsgColMap msgMap:msgmapList){
        	List<Object> item2 = new ArrayList<Object>();
        	item2.add(msgMap.getId());
        	item2.add(msgMap.getMsgCol());
        	item2.add(msgMap.getColConfig().getId());
            data.add(item2);
        }
        
        result.put("data", data);
        JsonUtil.writeObjToResponse(response, result);
	}
	@RequestMapping(value = "/interface/filterColDetail.do", method = RequestMethod.POST, name="管理-接口配置-筛选列详情")
	public void filterColDetail(@RequestParam(value = "id", required = true) int interfaceID, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
        List<String> titles = Arrays.asList("编号", "基础列|增强列", "筛选条件", "比较结果", "逻辑关系");
        result.put("titles", titles);
        List<Object> data = new ArrayList<Object>();
        
        List<InterfaceColConfig> configList=configService.loadInterfaceColCfgFilterList(interfaceID);
        
        for(InterfaceColConfig cfg:configList){
        	List<Object> item1 = new ArrayList<Object>();
	        item1.add(cfg.getId());
	        item1.add(cfg.getId());
	        item1.add(interfaceService.getClassIDMap().get(cfg.getFilterFuncClass()));
	        item1.add(cfg.getFilterFieldValue());
	        item1.add(cfg.getFilterType());
	        data.add(item1);
        }
        
        result.put("data", data);
        JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value = "/interface/filterColCreate.do", method = RequestMethod.POST, name="管理-接口配置-筛选列创建")
	public void filterColCreate(@RequestParam(value = "pid", required = true) int interfaceID,
								 @RequestParam(value = "params", required = true) String params,
								 HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		ResultContainer container=configService.createInterfaceFilterCol(interfaceID, param_list, InterfaceConfig.filterType);
		
		if(container.isCorrectFlag()){
			JsonUtil.writeObjToResponse(response, container.getObjID());
		}else{
			HashMap<String, Object> result = new HashMap<String, Object>();
			HashMap<String, Object> issue = new HashMap<String, Object>();
			//issue.put("code", container.getErrorCode());
			issue.put("level", "fail");
			issue.put("detail", container.getErrorMsg());
			result.put("issue", issue);
			JsonUtil.writeObjToResponse(response, result);
		}
	}
	
	@RequestMapping(value = "/interface/filterColUpdate.do", method = RequestMethod.POST, name="管理-接口配置-筛选列更新")
	public void filterColUpdate(@RequestParam(value = "pid", required = true) int interfaceID,
								 @RequestParam(value = "id", required = true) int filterColID,
								 @RequestParam(value = "params", required = true) String params,
								 HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		configService.updateInterfaceFilterByID(filterColID, param_list);
		JsonUtil.writeObjToResponse(response, "");
	}
	
	@RequestMapping(value = "/interface/filterColRemove.do", method = RequestMethod.POST, name="管理-接口配置-筛选列删除")
	public void filterColRemove(@RequestParam(value = "pid", required = true) int interfaceID,
								  @RequestParam(value = "id", required = true) int filterColID,
								  HttpServletResponse response) {
		configService.deleteInterfaceFilterByID(filterColID);
        JsonUtil.writeObjToResponse(response, "");
	}
	
	@RequestMapping(value = "/interface/msgColCreate.do", method = RequestMethod.POST, name="管理-接口配置-消息列创建")
	public void msgColCreate(@RequestParam(value = "pid", required = true) int interfaceID,
								 @RequestParam(value = "params", required = true) String params,
								 HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		ResultContainer container=configService.createMsgColMap(interfaceID, param_list);
		
		if(container.isCorrectFlag()){
			JsonUtil.writeObjToResponse(response, container.getObjID() + "");
		}else{
			HashMap<String, Object> result = new HashMap<String, Object>();
			HashMap<String, Object> issue = new HashMap<String, Object>();
			//issue.put("code", container.getErrorCode());
			issue.put("level", "fail");
			issue.put("detail", container.getErrorMsg());
			result.put("issue", issue);
			JsonUtil.writeObjToResponse(response, result);
		}
	}
	
	@RequestMapping(value = "/interface/msgColUpdate.do", method = RequestMethod.POST, name="管理-接口配置-消息列更新")
	public void msgColUpdate(@RequestParam(value = "pid", required = true) int interfaceID,
								 @RequestParam(value = "id", required = true) int msgColID,
								 @RequestParam(value = "params", required = true) String params,
								 HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		ResultContainer container=configService.updateInterfaceMsgColMap(interfaceID, msgColID, param_list);
		
		if(container.isCorrectFlag()){
			JsonUtil.writeObjToResponse(response, container.getObjID() + "");
		}else{
			HashMap<String, Object> result = new HashMap<String, Object>();
			HashMap<String, Object> issue = new HashMap<String, Object>();
			issue.put("level", "fail");
			issue.put("detail", container.getErrorMsg());
			result.put("issue", issue);
			JsonUtil.writeObjToResponse(response, result);
		}
	}
	
	@RequestMapping(value = "/interface/msgColRemove.do", method = RequestMethod.POST, name="管理-接口配置-消息列删除")
	public void msgColRemove(@RequestParam(value = "pid", required = true) int interfaceID,
						     @RequestParam(value = "id", required = true) int msgColID,
							 HttpServletResponse response) {
		configService.deleteInterfaceMsgColMap(msgColID);
        JsonUtil.writeObjToResponse(response, "");
	}
	
	@RequestMapping(value = "/interface/allMsgColsRemove.do", method = RequestMethod.POST)
	public void removeAllMsgCols(@RequestParam(value = "id", required = true) int interfaceID,
							 HttpServletResponse response) {
		System.out.println("Remove all message cols in interface -" + interfaceID);
        JsonUtil.writeObjToResponse(response, "");
	}
	
	@RequestMapping(value="/interface/message.do", method = RequestMethod.POST)
	public void message(@RequestParam(value = "id", required = false) int interfaceID, 
				        HttpServletResponse response){
		String msg=configService.getConfigMsg(interfaceID);
		JsonUtil.writeObjToResponse(response, msg);
	}
	
	@RequestMapping(value="/interface/messageUpdate.do", method = RequestMethod.POST,name="管理-接口配置-消息更新")
	public void messageUpdate(@RequestParam(value = "id", required = false) int interfaceID,
						      @RequestParam(value = "msg", required = false) String msg,
				              HttpServletResponse response){
		configService.updateInterfaceSource(interfaceID, msg);
		JsonUtil.writeObjToResponse(response, "");
	}
	
	@RequestMapping(value = "/interface/msgKeyWords.do", method = RequestMethod.POST)
	public void msgKeyWords(@RequestParam(value = "id", required = true) int interfaceID,
							 HttpServletResponse response) {
		List<String> words = configService.getMsgParams(interfaceID);
        JsonUtil.writeObjToResponse(response, words);
	}
	
	@RequestMapping(value="/config/interfaceTypes.do", method = RequestMethod.POST)
	public void interfaceTypes(HttpServletResponse response){
		JsonUtil.writeObjToResponse(response, InterfaceSourceType.interfaceImporttypeHM);
	}
	
	@RequestMapping(value="/config/importData.do", method = RequestMethod.POST, name="接口-导入数据")
	 public void importData(@RequestParam(value = "id", required = true) int sourceID,
			 				@RequestParam(value = "port", required = true) int portID,
			 				@RequestParam(value = "workflowStep", required = true) int workflowStep,
			 				@RequestParam(value = "date", required = true) String date,
			 			    HttpServletRequest request, 
			 			    HttpServletResponse response){
		HashMap<String, Object> result = new HashMap<String, Object>();
		int userID=JixuUserUtil.getUserIDbyRequest(request);
		
		List<String> titles = Arrays.asList("状态","数量", "错误详情");		
		result.put("titles", titles);
		List<Object> data = new ArrayList<Object>();
		
		//Date ledgerDate=DateUtil.convertStr2Date2(date);
		/*if(ledgerDate==null){
			controllerReturn(response, false, "请选择数据导入日期");
			return;
		}*/
		
		//开启新线程之前,添加如下代码 :将RequestAttributes对象设置为子线程共享
		/*ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		RequestContextHolder.setRequestAttributes(sra, true);*/
		
		ExecuteJobTask jobtask=new ExecuteJobTask(dataServiceCenter, userID, sourceID);
		taskExecutor.execute(jobtask);
		
		List<Object> item1 = new ArrayList<Object>();
		item1.add("适配器已开始运行，请到任务面板查看执行状态");
		item1.add("");
		item1.add("");
		data.add(0, item1);
		
		result.put("data", data);
		result.put("resultFlag", true);
		JsonUtil.writeObjToResponse(response, result);
		
		/*ResultContainer container=dataServiceCenter.importData(userID, sourceID, ledgerDate, workflowStep);
	
		if(container.isCorrectFlag()){
			List<MsgContainer> containerList=(List<MsgContainer>)container.getEntityObj();
			int successnum=0;
			if(containerList!=null){
				for(MsgContainer msgcontainer:containerList){
					if(msgcontainer.isCorrectFlag()){
						successnum++;
					}else{
						List<Object> item = new ArrayList<Object>();
						item.add("失败");
						item.add(1);
						item.add(msgcontainer.getErrorMsg());
						data.add(item);
					}
				}
			}
			List<Object> item1 = new ArrayList<Object>();
			item1.add("成功");
			item1.add(successnum);
			item1.add("");
			data.add(0, item1);
		
			result.put("data", data);
			result.put("resultFlag", container.isCorrectFlag());
			JsonUtil.writeObjToResponse(response, result);
		}else{
			HashMap<String, Object> issue = new HashMap<String, Object>();
			issue.put("level", "fail");
			issue.put("detail", container.getErrorMsg());
			result.put("issue", issue);
			result.put("resultFlag", container.isCorrectFlag());
			JsonUtil.writeObjToResponse(response, result);
		}*/
	}
	
	//TODO
	@RequestMapping(value="/config/importDataDebug.do", method = RequestMethod.POST, name="接口-调试导入数据")
	 public void importDataDebug(@RequestParam(value = "id", required = true) int sourceID,
			 				@RequestParam(value = "port", required = true) int portID,
			 				@RequestParam(value = "workflowStep", required = true) int workflowStep,
			 				@RequestParam(value = "date", required = true) String date,
			 			    HttpServletRequest request, 
			 			    HttpServletResponse response){
		HashMap<String, Object> result = new HashMap<String, Object>();
		int userID=JixuUserUtil.getUserIDbyRequest(request);
		
		List<String> titles = Arrays.asList("状态","数量", "错误详情");		
		result.put("titles", titles);
		List<Object> data = new ArrayList<Object>();
		
		Date ledgerDate=DateUtil.convertStr2Date2(date);
		/*if(ledgerDate==null){
			controllerReturn(response, false, "请选择数据导入日期");
			return;
		}*/
		ResultContainer container=dataServiceCenter.importData(userID, sourceID, ledgerDate, workflowStep);
	
		if(container.isCorrectFlag()){
			List<MsgContainer> containerList=(List<MsgContainer>)container.getEntityObj();
			int successnum=0;
			for(MsgContainer msgcontainer:containerList){
				if(msgcontainer.isCorrectFlag()){
					successnum++;
				}else{
					List<Object> item = new ArrayList<Object>();
					item.add("失败");
					item.add(1);
					item.add(msgcontainer.getErrorMsg());
					data.add(item);
				}
			}
			List<Object> item1 = new ArrayList<Object>();
			item1.add("成功");
			item1.add(successnum);
			item1.add("");
			data.add(0, item1);
		
			result.put("data", data);
			result.put("resultFlag", container.isCorrectFlag());
			JsonUtil.writeObjToResponse(response, result);
		}else{
			HashMap<String, Object> issue = new HashMap<String, Object>();
			issue.put("level", "fail");
			issue.put("detail", container.getErrorMsg());
			result.put("issue", issue);
			result.put("resultFlag", container.isCorrectFlag());
			JsonUtil.writeObjToResponse(response, result);
		}
	}
	
	@RequestMapping(value="/config/uploadImportData.do", method = RequestMethod.POST, name="接口-上传并导入数据")
	 public void uploadImportData(@RequestParam(value = "id", required = true) int sourceID,
						    @RequestParam(value = "port", required = true) int portID,
						    @RequestParam(value = "workflowStep", required = true) int workflowStep,
						    @RequestParam(value = "date", required = true) String date,
			 			    MultipartHttpServletRequest request, 
			 			    HttpServletResponse response){
		int userID=JixuUserUtil.getUserIDbyRequest(request);
		List<MultipartFile> mpfList=new ArrayList<MultipartFile>();
		Iterator<String> itr = request.getFileNames();
		while(itr.hasNext()){
			mpfList.add(request.getFile(itr.next()));
		}
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("状态","数量", "错误详情");		
		result.put("titles", titles);
		
		List<Object> data = new ArrayList<Object>();
		
		Date ledgerDate=DateUtil.convertStr2Date2(date);
		ResultContainer container=dataServiceCenter.uploadData(userID, sourceID, ledgerDate, workflowStep, mpfList);
		
		if(container.isCorrectFlag()){
			List<MsgContainer> containerList=(List<MsgContainer>)container.getEntityObj();
			int successnum=0;
			for(MsgContainer msgcontainer:containerList){
				if(msgcontainer.isCorrectFlag()){
					successnum++;
				}else{
					List<Object> item = new ArrayList<Object>();
					item.add("失败");
					item.add(1);
					item.add(msgcontainer.getErrorMsg());
					data.add(item);
				}
			}
			List<Object> item1 = new ArrayList<Object>();
			item1.add("成功");
			item1.add(successnum);
			item1.add("");
			data.add(0, item1);
			
			result.put("data", data);	
			JsonUtil.writeObjToResponse(response, result);
		}else{
			HashMap<String, Object> issue = new HashMap<String, Object>();
			issue.put("level", "fail");
			issue.put("detail", container.getErrorMsg());
			result.put("issue", issue);
			JsonUtil.writeObjToResponse(response, result);
		}
	}
	
	@RequestMapping(value="/config/uploadImportDataDebug.do", method = RequestMethod.POST, name="接口-调试上传并导入数据")
	 public void uploadImportDataDebug(@RequestParam(value = "id", required = true) int sourceID,
						    @RequestParam(value = "port", required = true) int portID,
						    @RequestParam(value = "workflowStep", required = true) int workflowStep,
						    @RequestParam(value = "date", required = true) String date,
			 			    MultipartHttpServletRequest request, 
			 			    HttpServletResponse response){
		int userID=JixuUserUtil.getUserIDbyRequest(request);
		List<MultipartFile> mpfList=new ArrayList<MultipartFile>();
		Iterator<String> itr = request.getFileNames();
		while(itr.hasNext()){
			mpfList.add(request.getFile(itr.next()));
		}
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("状态","数量", "错误详情");		
		result.put("titles", titles);
		
		List<Object> data = new ArrayList<Object>();
		
		Date ledgerDate=DateUtil.convertStr2Date2(date);
		ResultContainer container=dataServiceCenter.uploadData(userID, sourceID, ledgerDate, workflowStep, mpfList);
		
		if(container.isCorrectFlag()){
			List<MsgContainer> containerList=(List<MsgContainer>)container.getEntityObj();
			int successnum=0;
			for(MsgContainer msgcontainer:containerList){
				if(msgcontainer.isCorrectFlag()){
					successnum++;
				}else{
					List<Object> item = new ArrayList<Object>();
					item.add("失败");
					item.add(1);
					item.add(msgcontainer.getErrorMsg());
					data.add(item);
				}
			}
			List<Object> item1 = new ArrayList<Object>();
			item1.add("成功");
			item1.add(successnum);
			item1.add("");
			data.add(0, item1);
			
			result.put("data", data);	
			JsonUtil.writeObjToResponse(response, result);
		}else{
			HashMap<String, Object> issue = new HashMap<String, Object>();
			issue.put("level", "fail");
			issue.put("detail", container.getErrorMsg());
			result.put("issue", issue);
			JsonUtil.writeObjToResponse(response, result);
		}
	}
	
	@RequestMapping(value="/interface/deliverTypes.do", method = RequestMethod.POST)
	public void deliverTypes(HttpServletResponse response){
		HashMap<Integer, String> result = InterfaceConfig.channelTypeOption;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/interface/autoDateFormats.do", method = RequestMethod.POST)
	public void autoDateFormats(HttpServletResponse response){
		JsonUtil.writeObjToResponse(response, InterfaceAutoType.autoTypeHM);
	}
	
	@RequestMapping(value="/interface/fileFormats.do", method = RequestMethod.POST)
	public void fileFormats(HttpServletResponse response){
		HashMap<Integer, String> result = InterfaceConfig.fileTypeOption;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/interface/dbFormats.do", method = RequestMethod.POST)
	public void dbFormats(HttpServletResponse response){
		HashMap<Integer, String> result = InterfaceConfig.dbTypeOption;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	//TODO: File encodes list
	@RequestMapping(value="/interface/fileEncodes.do", method = RequestMethod.POST)
	public void fileEncodes(HttpServletResponse response){
		HashMap<Integer, String> result = FileEncodeType.optionHM;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/interface/validators.do", method = RequestMethod.POST)
	public void validators(HttpServletResponse response){
		HashMap<Integer, String> result = interfaceService.getValidateMap();
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/interface/functions.do", method = RequestMethod.POST)
	public void functions(HttpServletResponse response){
		HashMap<Integer, String> result = interfaceService.getCalculateMap();
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/interface/paramFunctions.do", method = RequestMethod.POST)
	public void paramFunctions(HttpServletResponse response){
		//HashMap<Integer, String> result = interfaceService.getCalculateMap();
		HashMap<Integer, String> result = interfaceService.getParamsMap();
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/interface/interfaceCols.do", method = RequestMethod.POST)
	public void interfaceCols(@RequestParam(value = "id", required = true) int interfaceID,
							  HttpServletResponse response){
		HashMap<Integer, String> result = configService.loadInterfaceColumnList(interfaceID);
		result.put(0, "-");
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/interface/messageTypes.do", method = RequestMethod.POST)
	public void messageTypes(HttpServletResponse response){
		HashMap<Integer, String> result = AddUpdateDelType.optionHM;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/interface/filterOperators.do", method = RequestMethod.POST)
	public void filterOperators(HttpServletResponse response){
		HashMap<Integer, String> result = interfaceService.getFilterMap();
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/interface/logicOperators.do", method = RequestMethod.POST)
	public void logicOperators(HttpServletResponse response){
		HashMap<Integer, String> result = InterfaceConfig.filterTypeOption;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/interface/functionMap.do", method = RequestMethod.POST)
	public void functionMap(HttpServletResponse response){
		HashMap<Integer, String> result = interfaceService.getDescriptionMap();
		JsonUtil.writeObjToResponse(response, result);
	}
	
	//仿照functionMap，给出每个function的提示信息
	@RequestMapping(value="/interface/paramFunctionMap.do", method = RequestMethod.POST)
	public void paramFunctionMap(HttpServletResponse response){
		HashMap<Integer, String> result = interfaceService.getDescriptionMap();
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/config/adapterTypes.do", method = RequestMethod.POST)
	public void adapterTypes(HttpServletResponse response){
//		HashMap <Integer, String> tps = new HashMap <Integer, String>();
//		tps.put(1, "资管系统适配器");
//		tps.put(2, "第三方适配器");
		List<String> adapterType = JixuDictCodeList.valuesfromDictCode(JixuDictCode.ADAPTER_TYPE);
		
		JsonUtil.writeObjToResponse(response, adapterType);
	}
	
	@RequestMapping(value="/config/dbTest.do", method = RequestMethod.POST)
	public void dbTest(@RequestParam(value = "dbURL", required = true) String dbURL,
					   @RequestParam(value = "dbFormat", required = true) int dbFormat,
					   @RequestParam(value = "dbUser", required = true) String dbUser,
					   @RequestParam(value = "dbPwd", required = true) String dbPwd,
					   HttpServletRequest request,
					   HttpServletResponse response) {
		int status=1;
		status=dataServiceCenter.createDBReadBean(dbFormat).dbTest(dbURL, dbUser,dbPwd,status);
		JsonUtil.writeObjToResponse(response, status);
	}
	
	@RequestMapping(value = "/interface/paramColDetail.do", method = RequestMethod.POST, name="管理-接口配置-参数详情")
	public void paramColDetail(@RequestParam(value = "id", required = true) int interfaceID, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
        List<String> titles = Arrays.asList("编号", "增强列名称", "功能函数", "功能参数(表达式)");
        result.put("titles", titles);
        List<Object> data = new ArrayList<Object>();
        
        List<InterfaceColConfig> configList=configService.loadInterfaceParamsColCfgList(interfaceID);
        
        if(configList.size()==0){
            configService.autoGenerateSQLParamsColumn(interfaceID);
            configList=configService.loadInterfaceParamsColCfgList(interfaceID);
        }
        
        for(InterfaceColConfig cfg:configList){
        	List<Object> item1 = new ArrayList<Object>();
	        item1.add(cfg.getId());
	        item1.add(cfg.getColname());
	        if(cfg.getFuncClass()==null || cfg.getFuncClass()=="-"){
	        	item1.add(0);
	        }else{
	        	item1.add(interfaceService.getClassIDMap().get(cfg.getFuncClass()));
	        }
	        item1.add(cfg.getFuncParams());
	        data.add(item1);
        }
        
        result.put("data", data);
        JsonUtil.writeObjToResponse(response, result);
	}

	@RequestMapping(value = "/interface/paramColCreate.do", method = RequestMethod.POST, name="管理-接口配置-参数创建")
	public void paramColCreate(@RequestParam(value = "pid", required = true) int interfaceID,
							   @RequestParam(value = "params", required = true) String params,
							   HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		ResultContainer container=configService.createInterfaceParamsCol(interfaceID, param_list);
		controllerReturn(response, container);
	}

	@RequestMapping(value = "/interface/paramColUpdate.do", method = RequestMethod.POST, name="管理-接口配置-参数更新")
	public void paramColUpdate(@RequestParam(value = "pid", required = true) int interfaceID,
							   @RequestParam(value = "id", required = true) int paramColID,
							   @RequestParam(value = "params", required = true) String params,
							   HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		ResultContainer container=configService.updateInterfaceParamsCol(interfaceID, paramColID, param_list);
		controllerReturn(response, container);
	}

	@RequestMapping(value = "/interface/paramColRemove.do", method = RequestMethod.POST, name="管理-接口配置-参数删除")
	public void paramColRemove(@RequestParam(value = "pid", required = true) int interfaceID,
							   @RequestParam(value = "id", required = true) int paramColID,
							   HttpServletResponse response) {
		ResultContainer container=configService.deleteInterfaceCol(paramColID);
		controllerReturn(response,container);
	}
	
	@RequestMapping(value="/interface/dataSourceCreate.do", method = RequestMethod.POST, name="管理-数据库-创建")
	public void dataSourceCreate(@RequestParam(value = "json", required = true) String json,
								HttpServletRequest request,
								HttpServletResponse response) {
		MsgContainer container = executeMsg(request, json);
		controllerReturn(response, container);
		JsonUtil.writeObjToResponse(response, container.getObjID());
	}
	
	@RequestMapping(value="/interface/dataSourceUpdate.do", method = RequestMethod.POST, name="管理-数据库-更新")
	public void dataSourceUpdate(@RequestParam(value = "json", required = true) String json,
								HttpServletRequest request,
								HttpServletResponse response) {
		MsgContainer container = executeMsg(request, json);
		controllerReturn(response, container);
		JsonUtil.writeObjToResponse(response, container.getObjID());
	}
	
	@RequestMapping(value="/interface/dataSourceRemove.do", method = RequestMethod.POST, name="管理-数据库-删除")
	public void dataSourceRemove(@RequestParam(value = "id", required = true) String id,
								HttpServletRequest request,
								HttpServletResponse response) {
		HashMap<String, String> msg=new HashMap<String, String>();
		msg.put(MsgFields.header, "dataSourceMsg");
		msg.put(MsgFields.operation, "DELETE");
		msg.put(MsgFields.id, id);
		MsgContainer container=executeMsg(request, JSONObject.toJSONString(msg));
		controllerReturn(container);
		JsonUtil.writeObjToResponse(response, container.getObjID());
	}
	//todo
	@RequestMapping(value="/interface/dataSourceList.do", method = RequestMethod.POST, name="管理-数据库-查询")
	public void dataSourceList(@RequestParam(value = "connType", required = true) String connType,
								HttpServletRequest request,
								HttpServletResponse response) {
		
	}
	
	@RequestMapping(value="/interface/jobLatestStatus.do", method = RequestMethod.POST, name="管理-接口配置-任务最新状态")
	public void getJobLatestStatus(String category,String name,
								HttpServletRequest request,
								HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<JobStatusDto> dtoList=configService.getLatestJobStatus(1, category, name);
		
		List<Object> data = new ArrayList<Object>();
		for(JobStatusDto dto:dtoList){
        	List<Object> item1 = new ArrayList<Object>();
	        item1.add(dto.getSourceID());
	        item1.add(dto.getJobName());
	        item1.add(dto.getJobStatus());
	        item1.add(dto.getLastStartTime());
	        item1.add(dto.getLastEndTime());
	        item1.add(dto.getLastStatus());
	        item1.add(dto.getNextStartTime());
	        item1.add(dto.getDepenceAdapter());
	        data.add(item1);
        }
		
		result.put("data", data);
		List<String> titles = Arrays.asList("适配器编号", "适配器名称", "当前状态", "上一次运行开始时间", "上一次运行结束时间", "上一次运行状态", "下一次运行时间","父级适配器");
        result.put("titles", titles);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	
	@RequestMapping(value="/interface/deleteColDetail.do", method = RequestMethod.POST)
	public void deleteColDetail(
								@RequestParam(value = "id", required = true) String id,
								HttpServletRequest request,
								HttpServletResponse response) {
		
		int interfaceSourceID=Integer.valueOf(id);
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号","字段名", "更新关键字");
        result.put("titles", titles);
        List<Object> data = new ArrayList<Object>();
        
        List<InterfaceColConfig> configList=configService.loadInterfaceColCfgList(interfaceSourceID, InterfaceConfig.mergeDelKey);
        
        for(InterfaceColConfig cfg:configList){
        	List<Object> item1 = new ArrayList<Object>();
	        item1.add(cfg.getId());
	        item1.add(cfg.getColname());
	        item1.add(1);
	        data.add(item1);
        }
        
        result.put("data", data);
        JsonUtil.writeObjToResponse(response, result);
	}
	

	@RequestMapping(value = "/interface/deleteColRemove.do", method = RequestMethod.POST)
	public void deleteColRemove(@RequestParam(value = "pid", required = true) int interfaceID,
								@RequestParam(value = "id", required = true) int deleteColID,
								HttpServletResponse response) {
		ResultContainer container=configService.deleteInterfaceCol(deleteColID);
		controllerReturn(response,container);
	}
	
	@RequestMapping(value="/interface/deleteColUpdate.do", method = RequestMethod.POST)
	public void deleteColUpdate(@RequestParam(value = "pid", required = true) int interfaceID, 
								@RequestParam(value = "id", required = true) int updateColID,
								@RequestParam(value = "params", required = true) String params,
								HttpServletRequest request,
								HttpServletResponse response) {

		List<Object> param_list = JsonUtil.stringToList(params);
		ResultContainer container=configService.updateDeleteColParamsCol(interfaceID, updateColID, param_list);
		controllerReturn(response, container);
	}
	
	
	@RequestMapping(value = "/interface/deleteColCreate.do", method = RequestMethod.POST)
	public void deleteColCreate(@RequestParam(value = "pid", required = true) int interfaceID,
							   @RequestParam(value = "params", required = true) String params,
							   HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		ResultContainer container=configService.createDeleteColParamsCol(interfaceID, param_list);
		controllerReturn(response, container);
	}
	
	
	@RequestMapping(value="/interface/updateColDetail.do", method = RequestMethod.POST)
	public void updateColDetail(
								@RequestParam(value = "id", required = true) String interfaceID,
								HttpServletRequest request,
								HttpServletResponse response) {
		
		int interfaceSourceID=Integer.valueOf(interfaceID);
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号","字段名", "更新关键字");
        result.put("titles", titles);
        List<Object> data = new ArrayList<Object>();
        
        List<InterfaceColConfig> configList=configService.loadInterfaceColCfgList(interfaceSourceID, new int[] {InterfaceConfig.mergeKey, InterfaceConfig.mergeKeyNullEnable});
        
        for(InterfaceColConfig cfg:configList){
        	List<Object> item1 = new ArrayList<Object>();
	        item1.add(cfg.getId());
	        item1.add(cfg.getColname());
	        item1.add(cfg.getColType());
	        data.add(item1);
        }
        
        result.put("data", data);
        JsonUtil.writeObjToResponse(response, result);
	}

	
	@RequestMapping(value="/interface/updateColUpdate.do", method = RequestMethod.POST)
	public void updateColDetail(@RequestParam(value = "pid", required = true) int interfaceID, 
											@RequestParam(value = "id", required = true) int updateColID,
			 								@RequestParam(value = "params", required = true) String params,
			 								HttpServletRequest request,
			 								HttpServletResponse response) {

		List<Object> param_list = JsonUtil.stringToList(params);
		ResultContainer container=configService.updateColParamsCol(interfaceID, updateColID, param_list);
		controllerReturn(response, container);
	}
	

	@RequestMapping(value = "/interface/updateColRemove.do", method = RequestMethod.POST)
	public void removeMergeUpdateCol(@RequestParam(value = "pid", required = true) int interfaceID,
								  @RequestParam(value = "id", required = true) int updateColID,
								  HttpServletResponse response) {
		ResultContainer container=configService.deleteInterfaceCol(updateColID);
		controllerReturn(response,container);
	}
	
	
	@RequestMapping(value = "/interface/updateColCreate.do", method = RequestMethod.POST)
	public void updateColCreate(@RequestParam(value = "pid", required = true) int interfaceID,
							   @RequestParam(value = "params", required = true) String params,
							   HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		ResultContainer container=configService.createUpdateColParamsCol(interfaceID, param_list);
		controllerReturn(response, container);
	}
	
	//目标库删除关键字段和目标库更新关键字段获取
	@RequestMapping(value="/interface/getDBTableFields.do", method = RequestMethod.POST)
	public void getDBTableFields(
								@RequestParam(value = "id", required = true) String interfaceID,
								HttpServletRequest request,
								HttpServletResponse response) {
		HashMap<String, String> fields = configService.getDBTableFields(interfaceID);
		JsonUtil.writeObjToResponse(response, fields);
	}
	
	//TODO - 返回所有接口（适配器）key为适配器id，value为适配器名称
	@RequestMapping(value="/interface/getInterfaces.do", method = RequestMethod.POST)
	public void getInterfaces(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, String> interfaces = new HashMap<String, String>();
		interfaces.put("0", "-");
		List<InterfaceSource> list=interfaceSourceDao.loadAllInterfaceSourceList();
		for (InterfaceSource is : list) {
			interfaces.put(String.valueOf(is.getSourceID()), is.getSourceName());
		}
		JsonUtil.writeObjToResponse(response, interfaces);
	}
	
	@RequestMapping(value="/interface/interfaceLog.do", method = RequestMethod.POST)
	public void getInterfaceLog(
								@RequestParam(value = "id", required = true) String interfaceID,
								@RequestParam(value = "startDate", required = false) String startDateStr,
								@RequestParam(value = "endDate", required = false) String endDateStr,
								@RequestParam(value = "num", required = false) int num,
								HttpServletRequest request,
								HttpServletResponse response) {

		int sourceID=Integer.valueOf(interfaceID);
		Date startDate=null;
		if(startDateStr!=null && !startDateStr.equals("")){
			startDate=DateUtil.convertStr2Date2(startDateStr);
		}
		
		Date endDate=null;
		if(endDateStr!=null && !endDateStr.equals("")){
			endDate=DateUtil.convertStr2Date2(endDateStr);
		}
		
		Pages<InterfaceLog> pages=new Pages<InterfaceLog>(num);
		HashMap <String, Object> result = new HashMap<String, Object>();
		ArrayList <Object> data = new ArrayList<Object>();
		
		pages=interfaceLogDao.getLatestErrorMsg(sourceID,startDate, endDate,pages);
		List<InterfaceLog> list=pages.getResultList();
		if(list!=null&&list.size()>0){
			for (InterfaceLog interfaceLog : list) {
				ArrayList <String> item1 = new ArrayList <String>();
				item1.add(DateUtil.convertD2YYYY_MM_DDHHMMSS(interfaceLog.getStartTime()));
				item1.add(DateUtil.convertD2YYYY_MM_DDHHMMSS(interfaceLog.getEndTime()));
				if(interfaceLog.isResultFlag()){
					item1.add("成功");
				}else{
					item1.add("失败");
				}
				item1.add(String.valueOf(interfaceLog.getDelRecords()));
				item1.add(String.valueOf(interfaceLog.getSuccessNum()));
				if(interfaceLog.getErrorMsg()==null){
					item1.add("-");
				}else{
					item1.add(interfaceLog.getErrorMsg());
				}
				data.add(item1);
			}
		}
		
		List<String> titles = Arrays.asList("开始时间","结束时间","状态","删除数量","新增/更新数量","日志详情");
		result.put("titles", titles);
		result.put("data", data);
		result.put("pages", pages.getTotalPages());
		JsonUtil.writeObjToResponse(response, result);
	}
	
	/**
	 * 手动去设值当天的job已经运行成功，并获取数据，给下游系统作为标记位
	 * @param interfaceID
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/interface/setManualSetFinishFlag.do", method = RequestMethod.POST)
	public void setManualSetFinishFlag(@RequestParam(value = "id", required = true) String interfaceID,
								HttpServletRequest request,
								HttpServletResponse response) {

		/*int interfaceSourceID=Integer.valueOf(interfaceID);
		int newID=configService.setManualSetFinishFlag(interfaceSourceID);
		JsonUtil.writeObjToResponse(response, newID);*/
	}
	
	@RequestMapping(value="/interface/callClassNamesList.do", method = RequestMethod.POST)
	public void getCallClassNamesList(HttpServletResponse response) {
		HashMap<String, String> result = AdapterSysInit.callExeClassNameMap;
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/trade/adaptersecTypes.do", method = RequestMethod.POST)
	public void secTypes(HttpServletResponse response){
		//TODO 需要移除
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/trade/adapterallTradeTypes.do", method = RequestMethod.POST)
	public void allTradeTypes(HttpServletResponse response){
		//TODO 需要移除
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		JsonUtil.writeObjToResponse(response, result);
	}
}
