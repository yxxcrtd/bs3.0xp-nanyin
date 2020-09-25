package com.jixu.mvc.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jixu.common.controller.ExceptionControl;
import com.jixu.common.exception.NewFundException;
import com.jixu.common.func.CommFuncService;
import com.jixu.common.pojo.Users;
import com.jixu.common.tool.JixuUserUtil;
import com.jixu.common.type.FlagType;
import com.jixu.common.type.ResultContainer;
import com.jixu.common.types.NewFundType;
import com.jixu.common.util.DataConvert;
import com.jixu.common.util.DateUtil;
import com.jixu.common.util.JsonUtil;
import com.jixu.mvc.control.service.ProcessControllerService;
import com.orm.obj.NewFund;
import com.orm.obj.NewFundContent;
import com.orm.obj.PortfolioHdr;


@Controller
public class ProcessController extends ExceptionControl{
	
	@Resource(name="processControlerService")
	private ProcessControllerService processService;
	
	@Resource(name="commfuncService")
	private CommFuncService commService;
	
	@RequestMapping(value="/process.do", method = RequestMethod.GET, name="工作流程-页面显示")
	public String report() {
		return "process";
	}

	@RequestMapping(value="/newFundDetail.do", method = RequestMethod.GET)
	public String newFund(@RequestParam(value="id") int configID, ModelMap model) {
		NewFund newfund=processService.loadNewFund(configID);
		
		if(newfund!=null){
			model.addAttribute("configID", configID);
			model.addAttribute("configName", newfund.getContentName());
		}
		return "newFund";
	}
	
	@RequestMapping(value="/oldFundDetail.do", method = RequestMethod.GET)
	public String oldFund(@RequestParam(value="id") int configID, ModelMap model) {
		NewFund newfund=processService.loadNewFund(configID);
		
		model.addAttribute("configID", configID);
		model.addAttribute("configName", newfund.getContentName());
		
		PortfolioHdr portfolio=commService.loadPortfolio(newfund.getPortnum());
		//TODO: please return following 3 values;
		model.addAttribute("portID", portfolio.getId());
		model.addAttribute("portName", portfolio.getShortName());
		model.addAttribute("clientID", newfund.getClient());
		return "oldFund";
	}
	
	@RequestMapping(value="/process/processConfigList.do", method = RequestMethod.POST, name="工作流程-流程配置查询")
	public void processConfigList(@RequestParam(value = "client", required = true) int client,
								  @RequestParam(value = "port", required = true) String port,
								  @RequestParam(value = "processType", required = true) int type,
			 				      HttpServletRequest request,
			 				      HttpServletResponse response){
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号","配置名称", "配置类型", "完成度", "是否执行", "创建时间", "最后更新时间", "创建人");
		result.put("titles", titles);
		List<Object> data = new ArrayList<Object>();
		
		List<NewFund> newFundList=processService.loadNewFundList(client, type);
		for(NewFund fund:newFundList){
			if(type==NewFundType.copyOldFund.getIndex()){
				if(!fund.getPortnum().equals(port)){
					continue;
				}
			}
			List<Object> item = new ArrayList<Object>();
			item.add(fund.getId());
			item.add(fund.getContentName());
			item.add(NewFundType.getName(fund.getNewFundType()));
			item.add(DataConvert.convertCash2US(processService.getComletePercent(fund.getId()))+"%");
			item.add(FlagType.operationsHM.get(fund.isExecuteFlag()));
			item.add(DateUtil.convertD2YYYY_MM_DDHHMMSS(fund.getCreateTime()));
			item.add(DateUtil.convertD2YYYY_MM_DDHHMMSS(fund.getUpdateTime()));
			item.add(fund.getUsers().getUsername());
			data.add(item);
		}
		
		result.put("data", data);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/process/processConfigExe.do", method = RequestMethod.POST, name="工作流程-流程配置执行")
	public void processConfigExe(@RequestParam(value = "id", required = true) int id,
								  @RequestParam(value = "processType", required = true) int type,
								  @RequestParam(value = "initDate", required = false) String initDate,
								  @RequestParam(value = "initShares", required = false) double initShares,
			 				      HttpServletRequest request,
			 				      HttpServletResponse response){
		int userID=JixuUserUtil.getUserIDbyRequest(request);
		
		try{
			Date executeDate=new Date();
			if(initDate!=null)
				executeDate=DateUtil.convertStr2Date2(initDate);
			ResultContainer container=processService.executeNewFundProcess(userID, id, executeDate,initShares);
			if(container.isCorrectFlag()){
				JsonUtil.writeObjToResponse(response, true);
			}else{
				HashMap<String, Object> result = new HashMap<String, Object>();
				HashMap<String, Object> issue = new HashMap<String, Object>();
				issue.put("code", container.getErrorCode());
				issue.put("level", "fail");
				issue.put("detail", container.getErrorMsg());
				result.put("issue", issue);
				JsonUtil.writeObjToResponse(response, result);
			}
		}catch(NewFundException ne){
			HashMap<String, Object> result = new HashMap<String, Object>();
			HashMap<String, Object> issue = new HashMap<String, Object>();
			issue.put("code", ne.getExceptionType());
			issue.put("level", "fail");
			issue.put("detail", ne.getMessage());
			result.put("issue", issue);
			JsonUtil.writeObjToResponse(response, result);
		}
		
		
	}
	
	@RequestMapping(value="/process/processConfigLoad.do", method = RequestMethod.POST)
	public void processConfigLoad(@RequestParam(value = "id", required = true) int id,
								  @RequestParam(value = "processType", required = true) int type,
			 				      HttpServletRequest request,
			 				      HttpServletResponse response){
		NewFundContent newFundContent=processService.loadNewFundContent(id);
		HashMap<String, Object> result=new HashMap<String, Object>();
		if(newFundContent!=null){
			result=(HashMap<String, Object>) JsonUtil.stringToMap(newFundContent.getNewFundContent());
		}else{
			if(type==NewFundType.newFund.getIndex()){
				result.put("port", new HashMap<String, Object>());
				result.put("interests", new HashMap<String, Object>());
				result.put("bankAccts", new HashMap<String, Object>());
				result.put("secAccts", new HashMap<String, Object>());
				result.put("expenses", new HashMap<String, Object>());
				result.put("interfaces", new HashMap<String, Object>());
			}else if(type==NewFundType.copyOldFund.getIndex()){
				result.put("nav", new HashMap<String, Object>());
				result.put("tb", new HashMap<String, Object>());
				result.put("secPos", new HashMap<String, Object>());
				result.put("rcpy", new HashMap<String, Object>());
			}
			
		}
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/process/processConfigSave.do", method = RequestMethod.POST)
	public void processConfigSave(@RequestParam(value = "id", required = true) int id,
								  @RequestParam(value = "config", required = true) String config,
			 				      HttpServletRequest request,
			 				      HttpServletResponse response){
		processService.saveNewFundConfig(id, config);
		JsonUtil.writeObjToResponse(response, true);
	}
	
	@RequestMapping(value="/process/processConfigCreate.do", method = RequestMethod.POST)
	public void processConfigCreate(@RequestParam(value = "processType", required = true) int type,
									@RequestParam(value="name", required=true) String name,
									@RequestParam(value="clientID", required=true) int clientID,
									@RequestParam(value="portID", required=false) String portID,
									HttpServletRequest request,
			 				        HttpServletResponse response){
		NewFund newfund=new NewFund();
		newfund.setClient(clientID);
		newfund.setContentName(name);
		newfund.setNewFundType(type);
		newfund.setCreateTime(new Date());
		newfund.setUpdateTime(new Date());
		newfund.setExecuteFlag(false);
		newfund.setPortnum(portID);
		newfund.setUsers(new Users(JixuUserUtil.getUserIDbyRequest(request)));
		processService.createNewFund(newfund);
		
		JsonUtil.writeObjToResponse(response, newfund.getId());
	}
	
	@RequestMapping(value="/process/importTB.do", method = RequestMethod.POST, name="工作流程-余额表导入")
	 public void importTB(@RequestParam(value = "portID", required = true) String portID,
						    @RequestParam(value = "configID", required = true) int configID,
			 			    MultipartHttpServletRequest request, 
			 			    HttpServletResponse response){
		
		List<MultipartFile> mpfList=new ArrayList<MultipartFile>();
		Iterator<String> itr = request.getFileNames();
		while(itr.hasNext()){
			mpfList.add(request.getFile(itr.next()));
		}
		ResultContainer container=new ResultContainer();
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<List<String>> fieldsContainer=processService.loadCustomCoa(portID, configID, mpfList, container);
		if(container.isCorrectFlag()){
			List<String> titles = Arrays.asList("编号", "导入科目号", "导入科目名称", "当前余额", "系统映射科目");
			result.put("title", titles);
			
			List<Object> data = new ArrayList<Object>();
			
			for(List<String> fields:fieldsContainer){
				List<Object> item = new ArrayList<Object>();
				for(String field:fields){
					item.add(field);
				}
				data.add(item);
			}
			result.put("info", data);
		}else{
			HashMap<String, Object> issue = new HashMap<String, Object>();
			issue.put("code", container.getErrorCode());
			issue.put("level", "fail");
			issue.put("detail", container.getErrorMsg());
			result.put("issue", issue);
		}
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/process/importCashPosition.do", method = RequestMethod.POST, name="工作流程-现金头寸导入")
	 public void importCashPosition(@RequestParam(value = "portID", required = true) String portID,
						    		@RequestParam(value = "configID", required = true) int configID,
						    		MultipartHttpServletRequest request, 
						    		HttpServletResponse response){
		
		HashMap<String, Object> result= new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号", "银行账户名称", "币种", "当前余额(本币)", "当前余额(原币)", "系统映射银行账户");
		result.put("title", titles);
		
		ArrayList<Object> data = new ArrayList<Object>();
		ArrayList<Object> item = new ArrayList<Object>();
		item.add("b1");
		item.add("导入银行");
		item.add("人民币");
		item.add(100);
		item.add(100);
		item.add("");
		
		data.add(item);
		result.put("info", data);
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/process/importSecPosition.do", method = RequestMethod.POST, name="工作流程-证券头寸导入")
	 public void importSecPosition(@RequestParam(value = "portID", required = true) String portID,
						    		@RequestParam(value = "configID", required = true) int configID,
						    		MultipartHttpServletRequest request, 
						    		HttpServletResponse response){
		List<MultipartFile> mpfList=new ArrayList<MultipartFile>();
		Iterator<String> itr = request.getFileNames();
		while(itr.hasNext()){
			mpfList.add(request.getFile(itr.next()));
		}
		ResultContainer container=new ResultContainer();
		List<List<String>> fieldsContainer=processService.loadCustomSecPosition(portID, configID, mpfList, container);
		
		
		HashMap<String, Object> result= new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号", "证券代码", "证券名称", "到期时间", "数量", "币种", "成本(原 币)", "利息(原币)", "利率", "估值方法", "系统映射证券", "系统提示");
		result.put("title", titles);

		ArrayList<Object> data = new ArrayList<Object>();
		if(container.isCorrectFlag()){
			for(List<String> fieldsList:fieldsContainer){
				List<Object> item = new ArrayList<Object>();
				for(String field:fieldsList){
					item.add(field);
				}
				data.add(item);
			}
			result.put("info", data);
		}else{
			HashMap<String, Object> issue = new HashMap<String, Object>();
			issue.put("code", container.getErrorCode());
			issue.put("level", "fail");
			issue.put("detail", container.getErrorMsg());
			result.put("issue", issue);
		}
		JsonUtil.writeObjToResponse(response, result);
	}
	
	@RequestMapping(value="/process/importRCPY.do", method = RequestMethod.POST, name="工作流程-应收应付记录导入")
	 public void importRCPY(@RequestParam(value = "portID", required = true) String portID,
						    		@RequestParam(value = "configID", required = true) int configID,
						    		MultipartHttpServletRequest request, 
						    		HttpServletResponse response){
		HashMap<String, Object> result= new HashMap<String, Object>();
		List<String> titles = Arrays.asList("编号", "到期", "应收应付类型", "描述", "币种", "剩余金额", "原始金额", "系统映射科目", "系统结算银行账户");
		result.put("title", titles);

		List<MultipartFile> mpfList=new ArrayList<MultipartFile>();
		Iterator<String> itr = request.getFileNames();
		while(itr.hasNext()){
			mpfList.add(request.getFile(itr.next()));
		}
		ResultContainer container=new ResultContainer();
		
		
		List<List<String>> fieldsContainer=processService.loadCustomRcpy(portID, configID, mpfList, container);
		
		List<Object> data = new ArrayList<Object>();
		
		if(container.isCorrectFlag()){
			for(List<String> fieldsList:fieldsContainer){
				List<Object> item = new ArrayList<Object>();
				for(String field:fieldsList){
					item.add(field);
				}
				data.add(item);
			}
			result.put("info", data);
		}else{
			HashMap<String, Object> issue = new HashMap<String, Object>();
			issue.put("code", container.getErrorCode());
			issue.put("level", "fail");
			issue.put("detail", container.getErrorMsg());
			result.put("issue", issue);
		}
		
		JsonUtil.writeObjToResponse(response, result);
	}
}
