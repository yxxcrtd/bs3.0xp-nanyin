package com.jixu.adapter.control.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jixu.adapter.control.service.ConfigAdptService;
import com.jixu.adapter.model.ExpInterfaceSourceDto;
import com.jixu.adapter.model.JobStatusDto;
import com.jixu.adapter.service.export.ExpAssembleOutput;
import com.jixu.adapter.service.export.ExportDataSource;
import com.jixu.adapter.service.impl.DataInterfaceService;
import com.jixu.common.controller.ExceptionControl;
import com.jixu.common.pojo.InterfaceColConfig;
import com.jixu.common.pojo.InterfaceSource;
import com.jixu.common.type.DataReadResult;
import com.jixu.common.type.FlagType;
import com.jixu.common.type.InterfaceConfig;
import com.jixu.common.type.InterfaceSourceType;
import com.jixu.common.type.MsgContainer;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.util.AESUtil;
import com.jixu.common.util.JsonUtil;

@Controller
public class ExportController extends ExceptionControl{

	@Resource(name="configAdptService")
	private ConfigAdptService configService;
	
	@Resource(name="dataInterfaceService")
	private DataInterfaceService interfaceService;
	
	@Resource(name="exportDataSource")
	private ExportDataSource exportSource;
	
	@Resource(name="expAssembleOutput")
	private ExpAssembleOutput exeExport;
	
	@RequestMapping(value="/export.do", method = RequestMethod.GET, name="管理-导出配置-页面显示")
	public String export(){
		return "export";
	}
	
	@RequestMapping(value="/adpExport.do", method = RequestMethod.GET, name="管理-适配器导出配置-页面显示")
	public String adpExport(){
		return "adpExport";
	}
	
	@RequestMapping(value="/exportPage.do", method = RequestMethod.GET, name="管理-导出配置-详情页面显示")
	public String exportPage(@RequestParam(value="id") int exportID, ModelMap model) {
		model.addAttribute("exportID", exportID);
		return "exportPage";
	}
	
	@RequestMapping(value="/adptExportPage.do", method = RequestMethod.GET, name="管理-导出配置-适配器详情页面显示")
	public String adptExportPage(@RequestParam(value="id") int exportID, ModelMap model) {
		model.addAttribute("exportID", exportID);
		return "adpExportPage";
	}
	
	@RequestMapping(value="/export/list.do", method = RequestMethod.POST, name="管理-导出配置-查询")
	public void exportList(@RequestParam(value = "name", required = false) String name,
			 					  @RequestParam(value = "resource", required = false) String resource,
			 					  HttpServletResponse response){
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号","导出配置名称", "导出配置描述", "传输格式", "是否自动导出", "自动导出时间");
		result.put("titles", titles);
		
		List<InterfaceSource> sources=configService.loadExpInterfaceSourceList(name, resource);
		
		List<Object> data = new ArrayList<Object>();
		for(InterfaceSource source:sources){
		List<Object> item = new ArrayList<Object>();
			item.add(source.getSourceID());
			item.add(source.getSourceName());
			item.add(source.getSourceDesc());
			item.add(InterfaceConfig.fileTypeOption.get(source.getFileType()));
			item.add(FlagType.operationsHM.get(source.isAutoFlag()));
			item.add(source.getAutoTime());
			data.add(item);
		}
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/export/expJobLatestStatus.do", method = RequestMethod.POST, name="管理-导出配置-导出任务最新状态")
	public void expJobLatestStatus(String category,String name,HttpServletResponse response){
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号","适配器名称", "当前状态", "导出格式", "上次运行开始时间", "上次运行结束时间", "上次运行状态", "下次运行时间","父级适配器");
		result.put("titles", titles);
		List<JobStatusDto> dtoList=configService.getLatestJobStatus(InterfaceSourceType.exportType, category, name);
		
		List<Object> data = new ArrayList<Object>();
		for(JobStatusDto dto:dtoList){
        	List<Object> item1 = new ArrayList<Object>();
	        item1.add(dto.getSourceID());
	        item1.add(dto.getJobName());
	        item1.add(dto.getJobStatus());
	        item1.add(dto.getFileFormat());
	        item1.add(dto.getLastStartTime());
	        item1.add(dto.getLastEndTime());
	        item1.add(dto.getLastStatus());
	        item1.add(dto.getNextStartTime());
	        item1.add(dto.getDepenceAdapter());
	        data.add(item1);
        }
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/export/configList.do", method = RequestMethod.POST)
	 public void exportConfigList(@RequestParam(value = "resource", required = true) String resource,
			 					  HttpServletResponse response){
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		List<InterfaceSource> sources=configService.loadExpInterfaceSourceList("", resource);
		for(InterfaceSource source:sources){
			result.put(source.getSourceID(), source.getSourceName());
		}
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/export/create.do", method = RequestMethod.POST, name="管理-导出配置-创建")
	public void exportCreate(@RequestParam(value = "params", required = true) String params,
							 @RequestParam(value = "json", required = true) String json,
							 HttpServletRequest request,
							 HttpServletResponse response) {
		MsgContainer container = executeMsg(request, json);
		controllerReturn(response, container);
		JsonUtil.writeObjToResponse(response, container.getObjID());
//		List<Object> param_list = JsonUtil.stringToList(params);
//		System.out.println(json);
//		ResultContainer container=configService.createExpInterfaceSource(param_list);
//		
//		if(container.isCorrectFlag()){
//			JsonUtil.writeObjToResponse(response, container.getObjID() + "");
//		}else{
//			HashMap<String, Object> result = new HashMap<String, Object>();
//			HashMap<String, Object> issue = new HashMap<String, Object>();
//			//issue.put("code", container.getErrorCode());
//			issue.put("level", "fail");
//			issue.put("detail", container.getErrorMsg());
//			result.put("issue", issue);
//			JsonUtil.writeObjToResponse(response, result);
//		}
	}
	
	@RequestMapping(value="/export/detail.do", method = RequestMethod.POST, name="管理-导出配置-详情")
	public void exportDetail(@RequestParam(value = "id", required = true) int exportID,
								HttpServletResponse response) {
		HashMap<String, Object> basicConfig = new HashMap<String, Object>();
		List<String> basicTitles = Arrays.asList("导出名称", "导出描述", "导出数据源", "传输类型", "用户名", "密码", 
											     "文件格式", "文件编码", "导出路径(支持公式)", "文件名(支持通配符)", "组合处理", 
											     "表或工单名", "开始行", "列分隔符", "行结束符","标题名称", "包含列明",
											     "节假日安排", "自动导出", "自动导出时间安排", "导出时间(HH:MM:SS)");
		List<Object> basicData = new ArrayList<Object>();
		InterfaceSource is=configService.loadInterfaceSource(exportID);
		basicData.add(is.getSourceName());
		basicData.add(is.getSourceDesc());
		basicData.add(exportSource.getClassIDMap().get(is.getExpSourceClass()));
		basicData.add(is.getChannelType());
		basicData.add(is.getChannelAccount());
		basicData.add(AESUtil.Decrypt(is.getChannelPWD()));
		basicData.add(is.getFileType());
		basicData.add(is.getEnCodeType());
		basicData.add(is.getFilepath());
		basicData.add(is.getFileName());
		basicData.add(is.isFileMultiFlag());
		basicData.add(is.getTablename());
		basicData.add(is.getTableStartLine());
		basicData.add(is.getTableSpilitChar());
		basicData.add(is.getTableEndChar());
		basicData.add("");//to-do pending
		basicData.add(is.isBackupFlag());
		basicData.add(is.getHoliday());
		basicData.add(is.isAutoFlag());
		basicData.add(is.getAutoType());
		basicData.add(is.getAutoTime());
		
		basicConfig.put("titles", basicTitles);
		basicConfig.put("data", basicData);
		
		JsonUtil.writeObjToResponse(response, basicConfig);
		
		JsonUtil.writeObjToResponse(response, basicConfig);
	}
	
	@RequestMapping(value="/export/adptDetail.do", method = RequestMethod.POST, name="管理-导出配置-适配器详情")
	public void exportAdptDetail(@RequestParam(value = "id", required = true) int exportID,
								HttpServletResponse response) {
		ExpInterfaceSourceDto dto=configService.loadAdtpExpInterfaceDetail(exportID);
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("data", dto);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/export/update.do", method = RequestMethod.POST, name="管理-导出配置-更新")
	public void exportUpdate(@RequestParam(value = "id", required = true) int exportID,
							 @RequestParam(value = "params", required = true) String params,
							 @RequestParam(value = "json", required = true) String json,
							 HttpServletRequest request,
							 HttpServletResponse response) {
		MsgContainer container = executeMsg(request, json);
		controllerReturn(response, container);
		JsonUtil.writeObjToResponse(response, container.getObjID());
		//		System.out.println(json);
//		List<Object> param_list = JsonUtil.stringToList(params);
//		ResultContainer container=configService.updateExpInterfaceSource(exportID, param_list);
//		
//		if(container.isCorrectFlag()){
//			JsonUtil.writeObjToResponse(response, container.getObjID() + "");
//		}else{
//			HashMap<String, Object> result = new HashMap<String, Object>();
//			HashMap<String, Object> issue = new HashMap<String, Object>();
//			//issue.put("code", container.getErrorCode());
//			issue.put("level", "fail");
//			issue.put("detail", container.getErrorMsg());
//			result.put("issue", issue);
//			JsonUtil.writeObjToResponse(response, result);
//		}
	}
	
	@RequestMapping(value = "/export/basicColDetail.do", method = RequestMethod.POST)
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
	
	@RequestMapping(value = "/export/basicColCreate.do", method = RequestMethod.POST)
	public void basicColCreate(@RequestParam(value = "pid", required = true) int exportID,
							   @RequestParam(value = "params", required = true) String params,
							   HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		ResultContainer container=configService.createInterfaceBasicCol(exportID, param_list);
		
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
	
	@RequestMapping(value = "/export/basicColUpdate.do", method = RequestMethod.POST)
	public void basicColUpdate(@RequestParam(value = "pid", required = true) int exportID,
								 @RequestParam(value = "id", required = true) int basicColID,
								 @RequestParam(value = "params", required = true) String params,
								 HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		ResultContainer container=configService.updateInterfaceBasicCol(exportID, basicColID, param_list);
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
	
	@RequestMapping(value = "/export/basicColRemove.do", method = RequestMethod.POST)
	public void basicColRemove(@RequestParam(value = "pid", required = true) int exportID,
								  @RequestParam(value = "id", required = true) int basicColID,
								  HttpServletResponse response) {
		ResultContainer container=configService.deleteInterfaceCol(basicColID);
		controllerReturn(response,container);
	}
	
	@RequestMapping(value = "/export/advanceColDetail.do", method = RequestMethod.POST)
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
	
	@RequestMapping(value = "/export/advanceColCreate.do", method = RequestMethod.POST)
	public void advanceColCreate(@RequestParam(value = "pid", required = true) int exportID,
								 @RequestParam(value = "params", required = true) String params,
								 HttpServletResponse response) {
		//List<Object> param_list = JsonUtil.stringToList(params);
		List<List<String>> param_list = JsonUtil.stringToList2(params);
		ResultContainer container=configService.createInterfaceAdvanceCol(exportID, param_list);
		
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
	
	@RequestMapping(value = "/export/advanceColUpdate.do", method = RequestMethod.POST)
	public void advanceColUpdate(@RequestParam(value = "pid", required = true) int exportID,
								 @RequestParam(value = "id", required = true) int advanceColID,
								 @RequestParam(value = "params", required = true) String params,
								 HttpServletResponse response) {
		List<List<String>> param_list = JsonUtil.stringToList2(params);
		//ResultContainer container=configService.updateInterfaceAdvanceCol(interfaceID, advanceColID, param_list);
		ResultContainer container=configService.createInterfaceAdvanceCol(exportID, param_list);
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
	
	@RequestMapping(value = "/export/advanceColRemove.do", method = RequestMethod.POST)
	public void advanceColRemove(@RequestParam(value = "pid", required = true) int exportID,
								  @RequestParam(value = "id", required = true) int advanceColID,
								  HttpServletResponse response) {
		ResultContainer container=configService.deleteInterfaceCol(advanceColID);
		controllerReturn(response,container);
	}
	
	@RequestMapping(value = "/export/filterColDetail.do", method = RequestMethod.POST)
	public void filterColDetail(@RequestParam(value = "id", required = true) int exportID, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
        List<String> titles = Arrays.asList("编号", "基础列|增强列", "筛选条件", "比较结果", "逻辑关系");
        result.put("titles", titles);
        List<Object> data = new ArrayList<Object>();
        
        List<InterfaceColConfig> configList=configService.loadInterfaceColCfgFilterList(exportID);
        
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
	
	@RequestMapping(value = "/export/filterColCreate.do", method = RequestMethod.POST)
	public void filterColCreate(@RequestParam(value = "pid", required = true) int exportID,
								 @RequestParam(value = "params", required = true) String params,
								 HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		ResultContainer container=configService.createInterfaceFilterCol(exportID, param_list, InterfaceConfig.filterType);
		
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
	
	@RequestMapping(value = "/export/filterColUpdate.do", method = RequestMethod.POST)
	public void filterColUpdate(@RequestParam(value = "pid", required = true) int exportID,
								 @RequestParam(value = "id", required = true) int filterColID,
								 @RequestParam(value = "params", required = true) String params,
								 HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		configService.updateInterfaceFilterByID(filterColID, param_list);
		JsonUtil.writeObjToResponse(response, "");
	}
	
	@RequestMapping(value = "/export/filterColRemove.do", method = RequestMethod.POST)
	public void filterColRemove(@RequestParam(value = "pid", required = true) int exportID,
								  @RequestParam(value = "id", required = true) int filterColID,
								  HttpServletResponse response) {
		configService.deleteInterfaceFilterByID(exportID);
        JsonUtil.writeObjToResponse(response, "");
	}
	
	@RequestMapping(value = "/export/templateColDetail.do", method = RequestMethod.POST)
	public void templateColDetail(@RequestParam(value = "id", required = true) int exportID, HttpServletResponse response) {
		HashMap<String, Object> result = new HashMap<String, Object>();
        List<String> titles = Arrays.asList("编号", "导出列名", "列号", "基础列|增强列", "开始位", "结束位");
        result.put("titles", titles);
        List<Object> data = new ArrayList<Object>();
        
        List<InterfaceColConfig> configList=configService.loadInterfaceColCfgList(exportID, InterfaceConfig.exportFinalType);
        
        for(InterfaceColConfig cfg:configList){
        	List<Object> item = new ArrayList<Object>();
	        item.add(cfg.getId());
	        item.add(cfg.getColname());
	        item.add(cfg.getColNO());
	        item.add(cfg.getFuncParams());
	        data.add(item);
        }
        
        result.put("data", data);
        JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value = "/export/templateColCreate.do", method = RequestMethod.POST)
	public void templateColCreate(@RequestParam(value = "pid", required = true) int exportID,
								 @RequestParam(value = "params", required = true) String params,
								 HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		ResultContainer container=configService.createExpInterfaceOutputCol(exportID, param_list);
		
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
	
	@RequestMapping(value = "/export/templateColUpdate.do", method = RequestMethod.POST)
	public void templateColUpdate(@RequestParam(value = "pid", required = true) int exportID,
								 @RequestParam(value = "id", required = true) int templateColID,
								 @RequestParam(value = "params", required = true) String params,
								 HttpServletResponse response) {
		List<Object> param_list = JsonUtil.stringToList(params);
		ResultContainer container=configService.updateExpInterfaceOutputCol(exportID, templateColID, param_list);
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
	
	@RequestMapping(value = "/export/templateColRemove.do", method = RequestMethod.POST)
	public void templateColRemove(@RequestParam(value = "pid", required = true) int exportID,
								  @RequestParam(value = "id", required = true) int templateColID,
								  HttpServletResponse response) {
		ResultContainer container=configService.deleteInterfaceCol(templateColID);
		controllerReturn(response,container);
	}
	
	@RequestMapping(value="/export/exportCols.do", method = RequestMethod.POST)
	public void interfaceCols(@RequestParam(value = "id", required = true) int exportID,
							  HttpServletResponse response){
		HashMap<Integer, String> result = configService.loadExpTemplateBasicAdvanceList(exportID);
		result.put(0, "-");
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/export/functions.do", method = RequestMethod.POST)
	public void functions(HttpServletResponse response){
		HashMap<Integer, String> result = interfaceService.getCalculateMap();
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/export/functionMap.do", method = RequestMethod.POST)
	public void functionMap(HttpServletResponse response){
		HashMap<Integer, String> result = interfaceService.getDescriptionMap();
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/export/resources.do", method = RequestMethod.POST)
	public void autoDateFormats(HttpServletResponse response){
		HashMap<Integer, String> result = exportSource.getSourceNameHM();
		JsonUtil.writeObjToResponse(response, result);
	}
	
	/**
	 * 上游是dto class文件，这里反射可以选择导出的列
	 * @param exportID
	 * @param response
	 */
	@RequestMapping(value="/export/datasource.do", method = RequestMethod.POST)
	public void datasource(@RequestParam(value = "id", required = true) int exportID,
						   HttpServletResponse response){
		HashMap<Integer, String> result = exportSource.fetchModelFieldsDescHM(exportID);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	/**
	 * 适配器，通过sql语句，获取查询语句的列名
	 * @param exportID
	 * @param response
	 */
	@RequestMapping(value="/export/adpDatasource.do", method = RequestMethod.POST)
	public void adpDatasource(@RequestParam(value = "id", required = true) int exportID,
						   HttpServletResponse response){
		HashMap<String, String> result = configService.getSourceSQLColumnMap(exportID);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/export/multipleOptions.do", method = RequestMethod.POST)
	public void multipleOptions(HttpServletResponse response){
		HashMap<Integer, String> result = new HashMap<Integer, String>();
		result.put(0, "分开组合导出");
		result.put(1, "合并组合导出");
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/export/view.do", method = RequestMethod.POST, name="管理-导出配置-预览结果")
	 public void exportView(@RequestParam(value = "portID", required = true) String portID,
			 				@RequestParam(value = "configID", required = true) int configID,
			 				@RequestParam(value = "exportDate", required = true) String exportDate,
			 				HttpServletResponse response){
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		String[] params={String.valueOf(portID), exportDate};
		ResultContainer container=exeExport.preViewExport(configID, params);
		if(container != null){
			DataReadResult writeSourceData=(DataReadResult)container.getEntityObj();
			List<String> titles=writeSourceData.getDataHeader();

			ArrayList<Object> data = new ArrayList<Object>();

			List<List<Object>> dataList=writeSourceData.getDataResult();
			for(List<Object> fields:dataList){
				List<Object> item = new ArrayList<Object>();
				for(Object field:fields){
					item.add(field);
				}
				data.add(item);
			}
			result.put("titles", titles);
			result.put("data", data);
			JsonUtil.writeObjToResponse(response, result);
		}
	}
	
	@RequestMapping(value="/export/export.do", method = RequestMethod.POST, name="管理-导出配置-导出数据")
	 public void exportData(@RequestParam(value = "portID", required = true) String portID,
			 				@RequestParam(value = "configID", required = true) int configID,
			 				@RequestParam(value = "exportDate", required = true) String exportDate,
			 				HttpServletResponse response){
		String[] params={String.valueOf(portID), exportDate};
		ResultContainer container=exeExport.executeExport(configID, params);
		if(container.isCorrectFlag()){
			JsonUtil.writeObjToResponse(response, container.getEntityObj().toString());
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
	
	@RequestMapping(value="/export/adpExport.do", method = RequestMethod.POST, name="管理-导出配置-导出数据")
	public void exportData(@RequestParam(value = "id", required = true) int expID,
			 				HttpServletResponse response){
		exeExport.executeExport(expID, null);
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("状态","数量", "错误详情");
		result.put("titles", titles);
		List<Object> data = new ArrayList<Object>();
		List<Object> item1 = new ArrayList<Object>();
		item1.add("适配器已开始运行，请到任务面板查看执行状态");
		item1.add("");
		item1.add("");
		data.add(0, item1);
		
		result.put("data", data);
		result.put("resultFlag", true);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/export/adpExportRemove.do", method = RequestMethod.POST)
	public void interfaceRemove(@RequestParam(value = "id", required = true) int interfaceID,
								HttpServletResponse response) {
		
		configService.deleteInterfaceSource(interfaceID);
		JsonUtil.writeObjToResponse(response, true);
	}
}
